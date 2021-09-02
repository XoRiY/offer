package com.airfrance.offer.domain.common.model;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @param <T>
 * @author Generic object used for Rest Controller response
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryResponse<T> {

    private HttpStatus status;

    private LocalDateTime timestamp;

    private T objectBody;

    private SortedSet<String> errors;


    /**
     * @param error
     * @return generic QueryResponse object of T
     * @apiNote add an error to existing list.
     */
    public QueryResponse<T> addError(String error) {

        if (errors == null) {
            errors = new TreeSet<>();
        }

        errors.add(error);

        return this;
    }

}
