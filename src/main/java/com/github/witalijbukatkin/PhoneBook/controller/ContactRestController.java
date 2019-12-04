/*
 * Copyright (c) 2019. Witalij Bukatkin
 * Github profile: https://github.com/witalijbukatkin
 */

package com.github.witalijbukatkin.PhoneBook.controller;

import com.github.witalijbukatkin.PhoneBook.model.Contact;
import com.github.witalijbukatkin.PhoneBook.repository.ContactRepository;
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
@RequestMapping(value = ContactRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ContactRestController {
    public static final String REST_URL = "/rest/contacts";
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private ContactRepository repository;

    @GetMapping
    public List<Contact> getAll(@RequestParam long userId) {
        log.info("getAll for userId {}", userId);
        return repository.getAll(userId);
    }

    @GetMapping("/{id}")
    public Contact get(@PathVariable long id, @RequestParam long userId) {
        log.info("get {} for userId {}", id, userId);
        return repository.get(id, userId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contact> create(@RequestBody Contact contact, @RequestParam long userId) {
        log.info("create {} for userId {}", contact, userId);

        Contact created = repository.save(contact, userId);

        if (created == null) {
            throw new IllegalArgumentException();
        }

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}?userId={userId}")
                .buildAndExpand(created.getId(), userId).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id, @RequestParam long userId) {
        log.info("delete {} for userId {}", id, userId);

        if (!repository.delete(id, userId)) {
            throw new IllegalArgumentException();
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Contact contact, @RequestParam long userId) {
        log.info("update {} for userId {}", contact, userId);

        if (repository.save(contact, userId) == null) {
            throw new IllegalArgumentException();
        }
    }

    @GetMapping("/by")
    public Contact getByPhoneNumber(@RequestParam String phoneNumber, @RequestParam long userId) {
        log.info("getByName {} for userId {}", phoneNumber, userId);
        return repository.getByPhoneNumber(phoneNumber, userId);
    }
}
