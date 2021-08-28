package com.gsuitesafe.gmailsafe.repositories;

import com.google.api.services.gmail.model.Message;
import com.gsuitesafe.gmailsafe.services.gmail.models.Backup;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GmailBackupRepository {

    private Map<String, Backup> backups = new HashMap<>();
    private Map<String, List<Message>> backupsData = new HashMap<>();

    public Collection<Backup> getBackup() {
        return backups.values();
    }

    public List<Message> getBackupData(final String backupId) {
        return backupsData.get(backupId);
    }

    public void save(final String id, final Backup backup) {
        backups.put(id, backup);
    }

    public void save(final String id, final List<Message> messages) {
        final List<Message> data = backupsData.get(id);
        data.addAll(messages);
        backupsData.put(id, data);
    }
}
