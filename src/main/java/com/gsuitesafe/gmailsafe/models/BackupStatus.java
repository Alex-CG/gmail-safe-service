package com.gsuitesafe.gmailsafe.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum BackupStatus {

    OK("OK"), IN_PROGRESS("In Progress"), FAILED("Failed");

    private String description;

}
