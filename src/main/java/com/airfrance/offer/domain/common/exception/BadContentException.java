package com.airfrance.offer.domain.common.exception;

import lombok.Getter;

import java.util.SortedSet;

/**
 * BadContentException
 *
 * @author Tahar Kerdoud
 */
public class BadContentException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    @Getter
    private final SortedSet<String> msgs;

    /**
     * @param msgs
     */
    public BadContentException(SortedSet<String> msgs) {
        super(msgs.toString());
        this.msgs = msgs;
    }

}