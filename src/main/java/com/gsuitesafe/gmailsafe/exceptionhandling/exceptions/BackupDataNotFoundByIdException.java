package com.gsuitesafe.gmailsafe.exceptionhandling.exceptions;

public class BackupDataNotFoundByIdException extends RuntimeException {

    public BackupDataNotFoundByIdException(final String backupId) {
        super(backupId);
    }

}
