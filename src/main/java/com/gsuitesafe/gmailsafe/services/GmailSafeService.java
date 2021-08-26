package com.gsuitesafe.gmailsafe.services;

import com.gsuitesafe.gmailsafe.models.BackupDetails;
import com.gsuitesafe.gmailsafe.models.BackupStatus;
import com.gsuitesafe.gmailsafe.models.BackupId;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class GmailSafeService {

    public BackupId initBackups() {
        return new BackupId("1");
    }

    public List<BackupDetails> getAllBackups() {
        return Collections.singletonList(new BackupDetails("1", ZonedDateTime.now(), BackupStatus.OK));
    }

    public Object getBackup(String backupId) {
        return "";
    }

    public Object getBackup(String backupId, String label) {
        return "";
    }
}
