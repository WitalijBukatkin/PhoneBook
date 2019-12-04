/*
 * Copyright (c) 2019. Witalij Bukatkin
 * Github profile: https://github.com/witalijbukatkin
 */

package com.github.witalijbukatkin.PhoneBook;

import com.github.witalijbukatkin.PhoneBook.model.Contact;
import com.github.witalijbukatkin.PhoneBook.model.User;

import java.util.Arrays;

public class TestData {
    public static User USER_0;
    public static Contact CONTACT_1;
    public static Contact CONTACT_2;

    static {
        init();
    }

    public static void init() {
        USER_0 = new User(0, "Test user", "test.email@mail.com", "Password");
        CONTACT_1 = new Contact(1, USER_0.getId(), "Contact 1", "Test", "test.contact1@mail.com");
        CONTACT_2 = new Contact(1, USER_0.getId(), "Contact 2", "Test", "test.contact2@mail.com");

        CONTACT_1.setPhoneNumbers(Arrays.asList("12", "13"));
        CONTACT_2.setPhoneNumbers(Arrays.asList("14", "15"));
    }
}
