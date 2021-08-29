package com.airfrance.offer.domain.repository.model;

import com.airfrance.offer.domain.model.Gender;
import org.agileware.test.PropertiesTester;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class UserTest {

    @Test
    void testProperties() throws Exception {
        PropertiesTester tester = new PropertiesTester();
        tester.addMapping(User.class, new User(null, "", LocalDate.now(), "", "", Gender.M));
        tester.addMapping(LocalDate.class, LocalDate.now());
        tester.testAll(User.class);
    }

    @Test
    void testBuilder() throws Exception {
        PropertiesTester tester = new PropertiesTester();
        tester.addMapping(User.class, User.builder().build());
        tester.addMapping(LocalDate.class, LocalDate.now());
        tester.testAll(User.class);
    }

}