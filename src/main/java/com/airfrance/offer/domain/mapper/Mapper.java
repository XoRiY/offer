package com.airfrance.offer.domain.mapper;

public interface Mapper<S, T> {

    public T map(S source);

}
