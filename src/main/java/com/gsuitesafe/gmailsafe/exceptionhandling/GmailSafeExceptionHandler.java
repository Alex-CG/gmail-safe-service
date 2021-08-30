package com.gsuitesafe.gmailsafe.exceptionhandling;

import com.gsuitesafe.gmailsafe.exceptionhandling.exceptions.BackupNotFoundByIdException;
import com.gsuitesafe.gmailsafe.exceptionhandling.models.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GmailSafeExceptionHandler {

    @ExceptionHandler(BackupNotFoundByIdException.class)
    public ResponseEntity<ErrorResponse> handle(final BackupNotFoundByIdException ex) {
        final String message = String.format("Backup not found for id: %s", ex.getMessage());
        log.error(message, ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(message));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handle(final Exception ex) {
        final String message = "Internal server error";
        log.error(String.format("%s :", message), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(message));
    }

}
