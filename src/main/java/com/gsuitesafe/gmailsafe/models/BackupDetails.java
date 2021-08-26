package com.gsuitesafe.gmailsafe.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class BackupDetails {

    private String backupId;
    private ZonedDateTime date;
    private BackupStatus status;
}
