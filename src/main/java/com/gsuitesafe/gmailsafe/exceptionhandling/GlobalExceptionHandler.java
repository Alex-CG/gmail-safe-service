package com.gsuitesafe.gmailsafe.exceptionhandling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BackupNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(final BackupNotFoundException ex) {
        log.error("Backup Not Found: ", ex);
        final String message = String.format("Backup not found for id: %s", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(message));
    }

}
