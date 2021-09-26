package com.ktr.offer.domain.common.model;

import lombok.*;

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

    private T objectBody;


}
