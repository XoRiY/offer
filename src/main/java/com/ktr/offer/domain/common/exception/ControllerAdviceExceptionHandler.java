package com.ktr.offer.domain.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * @author Tahar Kerdoud
 * @apiNote for handling Exception
 */
@ControllerAdvice
public class ControllerAdviceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<QueryErrorResponse> resourceNotFoundException(ResourceNotFoundException ex) {
        return getResponseEntity(List.of(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadContentException.class)
    public ResponseEntity<QueryErrorResponse> badContentException(BadContentException ex) {
        return getResponseEntity(ex.getMsgs(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<QueryErrorResponse> illegalArgumentException(IllegalArgumentException ex) {
        return getResponseEntity(List.of(ex.getMessage()), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<QueryErrorResponse> globalExceptionHandler(Exception ex) {
        return getResponseEntity(List.of(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<QueryErrorResponse> getResponseEntity(Collection<String> c, HttpStatus httpStatus){
        QueryErrorResponse queryResponse = new QueryErrorResponse();
        queryResponse.setTimestamp(LocalDateTime.now());
        queryResponse.addErrors(c);
        return new ResponseEntity<>(queryResponse, httpStatus);
    }
}