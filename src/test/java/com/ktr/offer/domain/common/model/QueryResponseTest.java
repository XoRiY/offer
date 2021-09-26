package com.ktr.offer.domain.common.model;

import org.agileware.test.PropertiesTester;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class QueryResponseTest {

    @Test
    void testProperties() throws Exception {
        PropertiesTester tester = new PropertiesTester();
        tester.addMapping(LocalDateTime.class, LocalDateTime.now());
        tester.testAll(QueryResponse.class);
    }

    @Test
    void testBuilder() throws Exception {
        PropertiesTester tester = new PropertiesTester();
        tester.addMapping(QueryResponse.class, QueryResponse.builder().build());
        tester.addMapping(LocalDateTime.class, LocalDateTime.now());
        tester.testAll(QueryResponse.class);
    }

}