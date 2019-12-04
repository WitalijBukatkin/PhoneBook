/*
 * Copyright (c) 2019. Witalij Bukatkin
 * Github profile: https://github.com/witalijbukatkin
 */

package com.github.witalijbukatkin.PhoneBook.repository;

import com.github.witalijbukatkin.PhoneBook.model.Contact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static com.github.witalijbukatkin.PhoneBook.TestData.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContactRepositoryTest {

    @Autowired
    private ContactRepository repository;

    @Test
    void create() {
        Contact expected = repository.save(
                new Contact(0L, "Name", "SecondName", "Email"),
                USER_0.getId());
        assertNotNull(expected);

        Contact actual = repository.get(expected.getId(), USER_0.getId());
        assertEquals(expected, actual);

        assertTrue(repository.delete(actual.getId(), USER_0.getId()));
    }

    @Test
    void update() {
        Contact updated = CONTACT_1;
        updated.setFirstName("New name");

        assertNotNull(repository.save(updated, USER_0.getId()));
        assertEquals(updated, repository.get(updated.getId(), USER_0.getId()));

        updated.setFirstName("Contact 1");
        assertNotNull(repository.save(updated, USER_0.getId()));
    }

    @Test
    void saveWithNull() {
        assertThrows(NullPointerException.class, () -> repository.save(null, USER_0.getId()));
    }

    @Test
    void delete() {
        assertTrue(repository.delete(CONTACT_1.getId(), USER_0.getId()));

        CONTACT_1.setId(null);
        assertNotNull(repository.save(CONTACT_1, USER_0.getId()));
    }

    @Test
    void get() {
        Contact actual = repository.get(CONTACT_1.getId(), USER_0.getId());
        assertEquals(CONTACT_1, actual);
    }

    @Test
    void getAll() {
        List<Contact> expected = Arrays.asList(CONTACT_2, CONTACT_1);
        assertIterableEquals(expected, repository.getAll(USER_0.getId()));
    }

    @Test
    void getByPhoneNumber() {
        assertEquals(CONTACT_1, repository.getByPhoneNumber("13", USER_0.getId()));
    }

    @Test
    void getByPhoneNumberWithEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
                repository.getByPhoneNumber(null, USER_0.getId()));
    }
}