package com.gsuitesafe.gmailsafe.repositories;

import com.gsuitesafe.gmailsafe.exceptionhandling.exceptions.BackupNotFoundByIdException;
import com.gsuitesafe.gmailsafe.services.gmail.models.Backup;
import com.gsuitesafe.gmailsafe.services.gmail.models.Message;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GmailBackupRepository {

    private final Map<String, Backup> backups = new HashMap<>();
    private final Map<String, List<Message>> backupsData = new HashMap<>();

    public Collection<Backup> getBackups() {
        return backups.values();
    }

    public List<Message> getBackupData(final String backupId) {
        final List<Message> messages = backupsData.get(backupId);
        if (messages == null) {
            throw new BackupNotFoundByIdException(backupId);
        }
        return messages;
    }

    public void save(final String id, final Backup backup) {
        backups.put(id, backup);
    }

    public void saveData(final String id, final List<Message> messages) {
        backupsData.put(id, messages);
    }

}
