/*
 * Copyright (c) 2019. Witalij Bukatkin
 * Github profile: https://github.com/witalijbukatkin
 */

package com.github.witalijbukatkin.PhoneBook.repository.inmemory;

import com.github.witalijbukatkin.PhoneBook.model.Contact;
import com.github.witalijbukatkin.PhoneBook.repository.ContactRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static com.github.witalijbukatkin.PhoneBook.TestData.CONTACT_1;
import static com.github.witalijbukatkin.PhoneBook.TestData.CONTACT_2;

@Repository
public class InMemoryContactRepositoryImpl implements ContactRepository {
    private static AtomicLong counter = new AtomicLong(100);

    private final Map<Long, Contact> contacts = new ConcurrentHashMap<>();

    public InMemoryContactRepositoryImpl() {
        contacts.put(1L, CONTACT_1);
        contacts.put(2L, CONTACT_2);
    }

    @Override
    public Contact save(Contact contact, long userId) {
        Objects.requireNonNull(contact, "Contact is not be null");

        if (userId != contact.getUserId()) {
            return null;
        }

        if (contact.isNew()) {
            contact.setId(counter.getAndIncrement());
            contacts.put(contact.getId(), contact);
            return contact;
        }

        return contacts.computeIfPresent(contact.getId(), (id, To) -> contact);
    }

    @Override
    public boolean delete(long id, long userId) {
        Contact contact = get(id, userId);

        if (contact == null) {
            return false;
        }

        return contacts.remove(id, contact);
    }

    @Override
    public Contact get(long id, long userId) {
        Contact contact = contacts.get(id);

        if (userId != contact.getUserId()) {
            return null;
        }

        return contact;
    }

    @Override
    public List<Contact> getAll(long userId) {
        List<Contact> returnContacts = new ArrayList<>(contacts.values());

        return returnContacts.stream()
                .filter(contact -> contact.getUserId() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public Contact getByPhoneNumber(String phoneNumber, long userId) {
        if (StringUtils.isEmpty(phoneNumber)) {
            throw new IllegalArgumentException("PhoneNumber is not be empty");
        }

        return contacts.values().stream()
                .filter(contact ->
                        contact.getUserId() == userId &&
                                contact.getPhoneNumbers().contains(phoneNumber))
                .findFirst().get();
    }
}
