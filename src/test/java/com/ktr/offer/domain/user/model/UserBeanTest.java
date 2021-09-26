package com.ktr.offer.domain.user.model;

import org.agileware.test.PropertiesTester;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


class UserBeanTest {

    @Test
    void testProperties() throws Exception {
        PropertiesTester tester = new PropertiesTester();
        tester.addMapping(UserBean.class, new UserBean(1L,"",LocalDate.now(), "", "", Gender.M ));
        tester.addMapping(LocalDate.class, LocalDate.now());
        tester.testAll(UserBean.class);
    }

    @Test
    void testBuilder() throws Exception {
        PropertiesTester tester = new PropertiesTester();
        tester.addMapping(UserBean.class, UserBean.builder().build());
        tester.addMapping(LocalDate.class, LocalDate.now());
        tester.testAll(UserBean.class);
    }


}