/*
 * Copyright (c) 2019. Witalij Bukatkin
 * Github profile: https://github.com/witalijbukatkin
 */

package com.github.witalijbukatkin.PhoneBook.repository;

import com.github.witalijbukatkin.PhoneBook.model.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    boolean delete(long id);

    User get(long id);

    List<User> getAll();

    List<User> getByName(String name);
}