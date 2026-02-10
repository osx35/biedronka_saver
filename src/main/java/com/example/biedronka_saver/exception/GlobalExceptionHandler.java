package com.example.biedronka_saver.exception;

import com.example.biedronka_saver.exception.implementation.GroupAlreadyExistsException;
import com.example.biedronka_saver.model.dto.JSendResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<JSendResponse<Map<String,String>>> handleBadCredentialsException(BadCredentialsException e) {
        log.error(e.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("authentication", e.getMessage());
        return ResponseEntity
                .status(401)
                .body(JSendResponse.fail("Authentication failed", errors));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<JSendResponse<Map<String,String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity
                .status(401)
                .body(JSendResponse.fail("Validation failed", errors));
    }

    @ExceptionHandler(GroupAlreadyExistsException.class)
    public ResponseEntity<JSendResponse<String>> handleGroupAlreadyExistsException(
            GroupAlreadyExistsException ex, HandlerMethod handlerMethod) {
        log.error(String.format("Group already exists error triggered by %s", getTriggerName(handlerMethod)), ex);
        return ResponseEntity.status(409)
                .body(JSendResponse.fail("Group already exists",ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<JSendResponse<String>> handleGenericException(
            Exception ex, HandlerMethod handlerMethod) {
        log.error(String.format("Unexpected error triggered by %s", getTriggerName(handlerMethod)), ex);
        return ResponseEntity.status(500)
                .body(JSendResponse.error("Unexpected error occurred",ex.getMessage()));
    }

    private String getTriggerName(HandlerMethod handlerMethod) {
        return handlerMethod.getBeanType().getSimpleName();
    }
}
