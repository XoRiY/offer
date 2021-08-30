package com.airfrance.offer.domain.mapper;

public interface Mapper<S, T> {

    T map(S source);

}
