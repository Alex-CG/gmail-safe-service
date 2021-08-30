package com.gsuitesafe.gmailsafe.repositories;

import com.gsuitesafe.gmailsafe.exceptionhandling.exceptions.BackupNotFoundByIdException;
import com.gsuitesafe.gmailsafe.services.gmail.models.Backup;
import com.gsuitesafe.gmailsafe.services.gmail.models.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class GmailBackupRepositoryTest {

    private GmailBackupRepository repository;
    private final int STATUS_IN_PROGRESS = 0;

    @BeforeEach
    public void setup() {
        repository = new GmailBackupRepository();
    }

    @Test
    public void getBackups_returnsEmptyData() {
        final Collection<Backup> backups = repository.getBackups();

        assertThat(backups).isEmpty();
    }

    @Test
    public void getBackupData_unknownBackupId_throwsBackupNotFoundByIdException() {
        final String unknownBackupId = "unknownBackupId";

        assertThatThrownBy(() -> repository.getBackupData(unknownBackupId))
                .isInstanceOf(BackupNotFoundByIdException.class)
                .hasMessage(unknownBackupId);
    }

    @Test
    public void save_and_getBackups_returnsData() {
        final String backup1Id = "38fa943b-6545-442e-8aa4-bac8ac70a8f2";
        final ZonedDateTime backup1Date = ZonedDateTime.now();
        final String backup2Id = "331c4814-c770-4188-bc9d-a4cf75470794";
        final ZonedDateTime backup2Date = ZonedDateTime.now();

        final Backup backup1 = Backup.builder()
                .id(backup1Id)
                .date(backup1Date)
                .status(STATUS_IN_PROGRESS)
                .dateToComplete(ZonedDateTime.now().plus(5L, ChronoUnit.SECONDS))
                .build();
        final Backup backup2 = Backup.builder()
                .id(backup2Id)
                .date(backup2Date)
                .status(STATUS_IN_PROGRESS)
                .dateToComplete(ZonedDateTime.now().plus(5L, ChronoUnit.SECONDS))
                .build();

        repository.save(backup1Id, backup1);
        repository.save(backup2Id, backup2);
        final Collection<Backup> backups = repository.getBackups();

        assertThat(backups).hasSize(2).containsExactlyInAnyOrder(backup1, backup2);
    }

    @Test
    public void saveData_and_getBackupData_returnsData() {
        final String backup1Id = "38fa943b-6545-442e-8aa4-bac8ac70a8f2";

        final List<Message> messagesToSave = Arrays.asList(
                Message.builder().id("messageId1").build(),
                Message.builder().id("messageId2").build(),
                Message.builder().id("messageId3").build());

        repository.saveData(backup1Id, messagesToSave);
        final List<Message> savedMessages = repository.getBackupData(backup1Id);

        assertThat(savedMessages).hasSize(3).containsExactlyInAnyOrderElementsOf(messagesToSave);
    }

}
