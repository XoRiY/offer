package com.airfrance.offer.domain.common.model;

import lombok.*;

import java.util.SortedSet;
import java.util.TreeSet;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryResponse<T> {

    private Status status;

    private T objectBody;

    private SortedSet<String> errors;


    public QueryResponse<T> addError(String error) {

        if (errors == null || errors.isEmpty()) {
            errors = new TreeSet<>();
        }

        errors.add(error);

        return this;
    }

}
