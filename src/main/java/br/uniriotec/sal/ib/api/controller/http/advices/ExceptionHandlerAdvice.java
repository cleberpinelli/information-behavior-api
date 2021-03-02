package br.uniriotec.sal.ib.api.controller.http.advices;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import br.uniriotec.sal.ib.domain.exception.BusinessException;
import br.uniriotec.sal.ib.domain.exception.NotFoundException;
import br.uniriotec.sal.ib.domain.exception.ValidationException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(RuntimeException ex, WebRequest request) {
        NotFoundException exception = (NotFoundException) ex;
        Map<String, Object> responseBody = exception.getError();

        if (responseBody == null) {
            responseBody = new HashMap<>();
            responseBody.put("errors", Arrays.asList(exception.getMessage()));
        }

        responseBody.put("code", HttpStatus.NOT_FOUND.value());
        responseBody.put("message", exception.getMessage());
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {BusinessException.class})
    public ResponseEntity<Object> handleBusinessException(RuntimeException ex, WebRequest request) {
        BusinessException exception = (BusinessException) ex;
        Map<String, Object> responseBody = exception.getError();

        if (responseBody == null) {
            responseBody = new HashMap<>();
            responseBody.put("errors", Arrays.asList(exception.getMessage()));
        }

        responseBody.put("code", HttpStatus.BAD_REQUEST.value());
        responseBody.put("message", exception.getMessage());
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<Object> handleValidationException(RuntimeException ex, WebRequest request) {
        ValidationException exception = (ValidationException) ex;
        Map<String, Object> responseBody = exception.getError();

        if (responseBody == null) {
            responseBody = new HashMap<>();
            responseBody.put("errors", Arrays.asList(exception.getMessage()));
        }
        responseBody.put("code", HttpStatus.NOT_ACCEPTABLE.value());
        responseBody.put("message", exception.getMessage());
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE, request);
    }
}
