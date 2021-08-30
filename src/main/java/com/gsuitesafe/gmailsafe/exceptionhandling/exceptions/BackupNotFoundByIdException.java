package com.gsuitesafe.gmailsafe.exceptionhandling.exceptions;

public class BackupNotFoundByIdException extends RuntimeException {

    public BackupNotFoundByIdException(final String backupId) {
        super(backupId);
    }

}
