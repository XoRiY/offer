package com.airfrance.offer.domain.common.exception;

import com.airfrance.offer.domain.common.model.QueryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.TreeSet;


/**
 *
 * @author  Tahar Kerdoud
 * @apiNote for handling Exception
 *
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public <T> ResponseEntity<QueryResponse<T>> resourceNotFoundException(ResourceNotFoundException ex) {
        QueryResponse<T> queryResponse = new QueryResponse<>();
        queryResponse.setStatus(HttpStatus.NOT_FOUND);
        queryResponse.setTimestamp(LocalDateTime.now());
        queryResponse.addError(ex.getErrorMessage());
        return new ResponseEntity<>(queryResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadContentException.class)
    public <T> ResponseEntity<QueryResponse<T>> badContentException(BadContentException ex) {
        QueryResponse<T> queryResponse = new QueryResponse<>();
        queryResponse.setStatus(HttpStatus.BAD_REQUEST);
        queryResponse.setTimestamp(LocalDateTime.now());
        queryResponse.setErrors(new TreeSet<>(ex.getMsgs()));

        return new ResponseEntity<>(queryResponse, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public <T> ResponseEntity<QueryResponse<T>> illegalArgumentException(IllegalArgumentException ex) {
        QueryResponse<T> queryResponse = new QueryResponse<>();
        queryResponse.setStatus(HttpStatus.BAD_REQUEST);
        queryResponse.setTimestamp(LocalDateTime.now());
        queryResponse.addError(ex.getMessage());

        return new ResponseEntity<>(queryResponse, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    public <T> ResponseEntity<QueryResponse<T>> globalExceptionHandler(Exception ex) {
        QueryResponse<T> queryResponse = new QueryResponse<>();
        queryResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        queryResponse.setTimestamp(LocalDateTime.now());
        queryResponse.addError(ex.getMessage());

        return new ResponseEntity<>(queryResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}