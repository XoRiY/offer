package com.ktr.offer.domain.common.exception;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryErrorResponse {

    private LocalDateTime timestamp;

    private SortedSet<String> errors;

    /**
     * @param c
     * @return generic QueryResponse object of T
     * @apiNote add an error to existing list.
     */
    public QueryErrorResponse addErrors(Collection<String> c) {

        if (errors == null) {
            errors = new TreeSet<>();
        }

        errors.addAll(c);

        return this;
    }

}
