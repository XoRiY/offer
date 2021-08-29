package com.airfrance.offer.domain.common.model;

import org.agileware.test.PropertiesTester;
import org.junit.jupiter.api.Test;

class QueryResponseTest {

    @Test
    void testProperties() throws Exception {
        PropertiesTester tester = new PropertiesTester();
        tester.testAll(QueryResponse.class);
    }

    @Test
    void testBuilder() throws Exception {
        PropertiesTester tester = new PropertiesTester();
        tester.addMapping(QueryResponse.class, QueryResponse.builder().build());
        tester.testAll(QueryResponse.class);
    }

}