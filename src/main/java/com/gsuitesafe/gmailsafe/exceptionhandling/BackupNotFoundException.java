package com.gsuitesafe.gmailsafe.exceptionhandling;

public class BackupNotFoundException extends RuntimeException {

    public BackupNotFoundException(final String message) {
        super(message);
    }

}
