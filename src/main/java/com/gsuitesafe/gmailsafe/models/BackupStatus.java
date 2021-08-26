package com.gsuitesafe.gmailsafe.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BackupStatus {

    OK("OK"), IN_PROGRESS("In Progress"), FAILED("Failed");

    private final String description;

}
