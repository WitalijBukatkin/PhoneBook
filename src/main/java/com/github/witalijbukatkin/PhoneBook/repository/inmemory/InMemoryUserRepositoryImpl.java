/*
 * Copyright (c) 2019. Witalij Bukatkin
 * Github profile: https://github.com/witalijbukatkin
 */

package com.github.witalijbukatkin.PhoneBook.repository.inmemory;

import com.github.witalijbukatkin.PhoneBook.model.User;
import com.github.witalijbukatkin.PhoneBook.repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static com.github.witalijbukatkin.PhoneBook.TestData.USER_0;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static AtomicLong counter = new AtomicLong(100);

    private final Map<Long, User> users = new ConcurrentHashMap<>();

    public InMemoryUserRepositoryImpl() {
        users.put(0L, USER_0);
    }

    @Override
    public User save(User user) {
        if (user.isNew()) {
            user.setId(counter.getAndIncrement());
            users.put(user.getId(), user);
            return user;
        }

        return users.computeIfPresent(user.getId(), (id, To) -> user);
    }

    @Override
    public boolean delete(long id) {
        return users.remove(id) != null;
    }

    @Override
    public User get(long id) {
        return users.get(id);
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public List<User> getByName(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("Name is not be empty");
        }

        return users.values().stream()
                .filter(user -> user.getName().contains(name))
                .collect(Collectors.toList());
    }
}
