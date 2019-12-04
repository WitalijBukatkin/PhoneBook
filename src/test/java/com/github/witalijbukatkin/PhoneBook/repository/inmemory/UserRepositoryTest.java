/*
 * Copyright (c) 2019. Witalij Bukatkin
 * Github profile: https://github.com/witalijbukatkin
 */

package com.github.witalijbukatkin.PhoneBook.repository.inmemory;

import com.github.witalijbukatkin.PhoneBook.TestData;
import com.github.witalijbukatkin.PhoneBook.model.User;
import com.github.witalijbukatkin.PhoneBook.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static com.github.witalijbukatkin.PhoneBook.TestData.USER_0;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @BeforeAll
    static void before() {
        TestData.init();
    }

    @Test
    void create() {
        User expected = repository.save(new User("Name", "Email", "Password"));
        assertNotNull(expected);

        User actual = repository.get(expected.getId());
        assertEquals(expected, actual);

        assertTrue(repository.delete(actual.getId()));
    }

    @Test
    void update() {
        User updated = USER_0;
        updated.setName("New name");

        assertNotNull(repository.save(updated));
        assertEquals(updated, repository.get(updated.getId()));

        updated.setName("Test user");
        assertNotNull(repository.save(updated));
    }

    @Test
    void saveWithNull() {
        assertThrows(NullPointerException.class, () -> repository.save(null));
    }

    @Test
    void delete() {
        assertTrue(repository.delete(USER_0.getId()));

        USER_0.setId(null);
        assertNotNull(repository.save(USER_0));
    }

    @Test
    void get() {
        User actual = repository.get(USER_0.getId());
        assertEquals(USER_0, actual);
    }

    @Test
    void getAll() {
        List<User> expected = Collections.singletonList(USER_0);
        assertIterableEquals(expected, repository.getAll());
    }

    @Test
    void getByName() {
        List<User> expected = Collections.singletonList(USER_0);
        assertEquals(expected, repository.getByName("user"));
    }

    @Test
    void getByNameWithEmpty() {
        assertThrows(IllegalArgumentException.class, () -> repository.getByName(null));
    }
}