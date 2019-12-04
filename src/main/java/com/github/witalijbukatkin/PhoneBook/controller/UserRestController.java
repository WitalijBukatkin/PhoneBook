/*
 * Copyright (c) 2019. Witalij Bukatkin
 * Github profile: https://github.com/witalijbukatkin
 */

package com.github.witalijbukatkin.PhoneBook.controller;


import com.github.witalijbukatkin.PhoneBook.model.User;
import com.github.witalijbukatkin.PhoneBook.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = UserRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {
    public static final String REST_URL = "/rest/users";
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserRepository repository;

    @GetMapping
    public List<User> getAll() {
        log.info("getAll");
        return repository.getAll();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable long id) {
        log.info("get {}", id);
        return repository.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> create(@RequestBody User user) {
        log.info("create {}", user);

        User created = repository.save(user);

        if (created == null) {
            throw new IllegalArgumentException();
        }

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        log.info("delete {}", id);

        if (!repository.delete(id)) {
            throw new IllegalArgumentException();
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody User user) {
        log.info("update {}", user);

        if (repository.save(user) == null) {
            throw new IllegalArgumentException();
        }
    }

    @GetMapping("/by")
    public List<User> getByName(@RequestParam String name) {
        log.info("getByName {}", name);
        return repository.getByName(name);
    }
}
