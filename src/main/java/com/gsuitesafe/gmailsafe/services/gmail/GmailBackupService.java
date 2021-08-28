package com.gsuitesafe.gmailsafe.services.gmail;

import com.gsuitesafe.gmailsafe.services.gmail.models.Backup;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GmailBackupService {

    private static Map<String, Backup> backups = new HashMap<>();
    private final int STATUS_IN_PROGRESS = 0;
    private final int STATUS_OK = 1;
    private final int STATUS_FAILED = 2;


    public Backup create() {
        final String id = UUID.randomUUID().toString();
        final ZonedDateTime date = ZonedDateTime.now();
        final Backup backup = Backup.builder()
                .id(id)
                .date(date)
                .status(STATUS_IN_PROGRESS)
                .dateToComplete(date.plus(getRandomDuration(), ChronoUnit.SECONDS))
                .build();
        backups.put(id, backup);
        return backup;
    }

    public List<Backup> getAll() {
        return backups.values().stream().peek(backup -> {
            if (backup.getStatus() == STATUS_IN_PROGRESS && ZonedDateTime.now().isAfter(backup.getDateToComplete())) {
                final boolean result = new Random().nextBoolean();
                backup.setStatus(result ? STATUS_OK : STATUS_FAILED);
            }
        }).collect(Collectors.toList());
    }

    public Object export(final String backupId) {
        final Object data = backups.get(backupId);
        return data;
    }

    public Object exportWithLabel(final String backupId, final String label) {
        final Object data = backups.get(backupId);
        return data;
    }

    private Integer getRandomDuration() {
        Random r = new Random();
        int low = 10;
        int high = 60;
        return r.nextInt(high - low) + low;
    }
}
