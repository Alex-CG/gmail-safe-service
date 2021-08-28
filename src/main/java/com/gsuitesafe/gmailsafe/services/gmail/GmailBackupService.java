package com.gsuitesafe.gmailsafe.services.gmail;

import com.google.api.services.gmail.model.Message;
import com.gsuitesafe.gmailsafe.exceptionhandling.BackupNotFoundException;
import com.gsuitesafe.gmailsafe.repositories.GmailBackupRepository;
import com.gsuitesafe.gmailsafe.services.FileService;
import com.gsuitesafe.gmailsafe.services.gmail.integration.GmailAPIService;
import com.gsuitesafe.gmailsafe.services.gmail.models.Backup;
import com.gsuitesafe.gmailsafe.services.gmail.models.GetAllMessagesResponse;
import com.gsuitesafe.gmailsafe.utils.BackupUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GmailBackupService {

    @Autowired
    private GmailAPIService gmailAPIService;

    @Autowired
    private FileService fileService;

    @Autowired
    private GmailBackupRepository repository;

    @Autowired
    private Environment env;

    private final int STATUS_IN_PROGRESS = 0;
    private final int STATUS_OK = 1;
    private final int STATUS_FAILED = 2;

    public Backup create(final String userId) {
        final String id = UUID.randomUUID().toString();
        final ZonedDateTime date = ZonedDateTime.now();
        final Backup backup = Backup.builder()
                .id(id)
                .date(date)
                .status(STATUS_IN_PROGRESS)
                .dateToComplete(date.plus(BackupUtils.getRandomDuration(), ChronoUnit.SECONDS))
                .build();
        repository.save(id, backup);

        if (!isGmailAPiEnabled()) {
            return backup;
        }

        final GetAllMessagesResponse allMessages = gmailAPIService.getAllMessages(userId);

        // TODO: these calls should be made asynchronously
        final List<Message> messagesData = allMessages.getMessages().stream()
                .map(message -> gmailAPIService.getMessage(userId, message.getId()))
                .collect(Collectors.toList());
        repository.save(id, messagesData);

        return backup;
    }

    public List<Backup> getAll() {
        return repository.getBackup().stream().peek(backup -> {
            if (backup.getStatus() == STATUS_IN_PROGRESS && ZonedDateTime.now().isAfter(backup.getDateToComplete())) {
                final boolean result = new Random().nextBoolean();
                backup.setStatus(result ? STATUS_OK : STATUS_FAILED);
            }
        }).collect(Collectors.toList());
    }

    public byte[] export(final String backupId, final String userId) {
        final List<Message> messages = repository.getBackupData(backupId);
        if (messages == null) {
            throw new BackupNotFoundException(String.format("Backup not found for id: %s", backupId));
        }

        return fileService.createZipFile(messages, userId);
    }

    public byte[] export(final String backupId, final String userId, final String label) {
        List<Message> messages = repository.getBackupData(backupId);
        if (messages == null) {
            throw new BackupNotFoundException(String.format("Backup not found for id: %s", backupId));
        }

        return fileService.createZipFile(
                messages.stream()
                        .filter(message -> message.getLabelIds().contains(label))
                        .collect(Collectors.toList()),
                userId);
    }

    private boolean isGmailAPiEnabled() {
        return "true".equals(env.getProperty("enable-gmail-api"));
    }

}
