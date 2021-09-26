package com.ktr.offer.domain.common.mapper;

/**
 * @param <S>
 * @param <T>
 * @author Tahar Kerdoud
 * Inteface mapper
 * must be implemented by every Mapper
 */
public interface Mapper<S, T> {

    /**
     * @param source
     * @return T
     * @apiNote  s is an Alias for source and T is an alias for Target
     */
    T map(S source);

}
