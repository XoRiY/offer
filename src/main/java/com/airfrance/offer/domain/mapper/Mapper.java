package com.airfrance.offer.domain.mapper;

public interface Mapper<S, T> {

    /**
     * @param source
     * @return T
     * @apiNote  s is an Alias for source and T is an alias for Target
     */
    T map(S source);

}
