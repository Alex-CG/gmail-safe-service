package com.gsuitesafe.gmailsafe.services;

import com.gsuitesafe.gmailsafe.models.BackupDetails;
import com.gsuitesafe.gmailsafe.models.BackupId;
import com.gsuitesafe.gmailsafe.services.gmail.models.Backup;
import com.gsuitesafe.gmailsafe.services.gmail.GmailBackupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.gsuitesafe.gmailsafe.utils.BackupUtils.getBackupStatus;

@Service
public class GmailSafeService {

    @Autowired
    private GmailBackupService gmailBackupService;

    public BackupId initBackup() {
        final Backup backup = gmailBackupService.create();
        return new BackupId(backup.getId());
    }

    public List<BackupDetails> getAllBackups() {
        final Collection<Backup> backups = gmailBackupService.getAll();
        return backups.stream()
                .map(backup -> new BackupDetails(
                        backup.getId(), backup.getDate(), getBackupStatus(backup.getStatus()).getDescription()))
                .collect(Collectors.toList());
    }

    public Object getBackup(String backupId) {
        return "";
    }

    public Object getBackup(String backupId, String label) {
        return "";
    }
}
