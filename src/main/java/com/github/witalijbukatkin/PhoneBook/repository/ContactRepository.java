/*
 * Copyright (c) 2019. Witalij Bukatkin
 * Github profile: https://github.com/witalijbukatkin
 */

package com.github.witalijbukatkin.PhoneBook.repository;

import com.github.witalijbukatkin.PhoneBook.model.Contact;

import java.util.List;

public interface ContactRepository {
    Contact save(Contact contact, long userId);

    boolean delete(long id, long userId);

    Contact get(long id, long userId);

    List<Contact> getAll(long userId);

    Contact getByPhoneNumber(String phoneNumber, long userId);
}
