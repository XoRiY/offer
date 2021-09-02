package com.airfrance.offer.domain.common.exception;

import lombok.Getter;

/**
 * ResourceNotFoundException
 *
 * @author Tahar Kerdoud
 */
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Resource Not Found Exception Message
     */
    @Getter
    private final String errorMessage;


    /**
     * @param msg
     */
    public ResourceNotFoundException(String msg) {
        super(msg);
        this.errorMessage = msg;
    }
}