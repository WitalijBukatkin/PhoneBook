/*
 * Copyright (c) 2019. Witalij Bukatkin
 * Github profile: https://github.com/witalijbukatkin
 */

package com.github.witalijbukatkin.PhoneBook.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Contact {
    private Long id;
    private Long userId;
    private String firstName;
    private String secondName;
    private String email;

    private List<String> phoneNumbers;

    public Contact() {
    }

    public Contact(long userId, String firstName, String secondName, String email) {
        this.userId = userId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.phoneNumbers = new ArrayList<>();
    }

    public Contact(long id, long userId, String firstName, String secondName, String email) {
        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.phoneNumbers = new ArrayList<>();
    }

    public Contact(long id, String firstName, String secondName, String email, List<String> phoneNumbers) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.phoneNumbers = phoneNumbers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(id, contact.id) &&
                Objects.equals(userId, contact.userId) &&
                Objects.equals(firstName, contact.firstName) &&
                Objects.equals(secondName, contact.secondName) &&
                Objects.equals(email, contact.email) &&
                Objects.equals(phoneNumbers, contact.phoneNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, firstName, secondName, email, phoneNumbers);
    }
}