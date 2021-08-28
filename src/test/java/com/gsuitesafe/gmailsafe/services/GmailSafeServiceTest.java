package com.gsuitesafe.gmailsafe.services;

import com.gsuitesafe.gmailsafe.models.BackupDetails;
import com.gsuitesafe.gmailsafe.models.BackupId;
import com.gsuitesafe.gmailsafe.models.BackupStatus;
import com.gsuitesafe.gmailsafe.services.gmail.GmailBackupService;
import com.gsuitesafe.gmailsafe.services.gmail.models.Backup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class GmailSafeServiceTest {

    @Mock
    private GmailBackupService mockGmailBackupService;

    @InjectMocks
    private GmailSafeService gmailSafeService;

    private final int STATUS_IN_PROGRESS = 0;
    private final static String USER_ID = "xyz@gmail.com";

    @Test
    public void initBackup_returnsBackupId() {
        final Backup backupResult = Backup.builder()
                .id("38fa943b-6545-442e-8aa4-bac8ac70a8f2")
                .date(ZonedDateTime.now())
                .status(STATUS_IN_PROGRESS)
                .dateToComplete(ZonedDateTime.now().plus(5L, ChronoUnit.SECONDS))
                .build();
        when(mockGmailBackupService.create(USER_ID)).thenReturn(backupResult);

        final BackupId backupId = gmailSafeService.initBackup(USER_ID);

        assertThat(backupId).isEqualTo(new BackupId("38fa943b-6545-442e-8aa4-bac8ac70a8f2"));
    }

    @Test
    public void getAllBackups_returnsAnEmptyList() {
        when(mockGmailBackupService.getAll()).thenReturn(Collections.emptyList());

        assertThat(gmailSafeService.getAllBackups()).isEmpty();
    }

    @Test
    public void getAllBackups_returnsAList() {
        final String backup1Id = "38fa943b-6545-442e-8aa4-bac8ac70a8f2";
        final ZonedDateTime backup1Date = ZonedDateTime.now();
        final String backup2Id = "331c4814-c770-4188-bc9d-a4cf75470794";
        final ZonedDateTime backup2Date = ZonedDateTime.now();

        final List<Backup> backups = Arrays.asList(
                Backup.builder()
                        .id(backup1Id)
                        .date(backup1Date)
                        .status(STATUS_IN_PROGRESS)
                        .dateToComplete(ZonedDateTime.now().plus(5L, ChronoUnit.SECONDS))
                        .build(),
                Backup.builder()
                        .id(backup2Id)
                        .date(backup2Date)
                        .status(STATUS_IN_PROGRESS)
                        .dateToComplete(ZonedDateTime.now().plus(5L, ChronoUnit.SECONDS))
                        .build()
        );
        when(mockGmailBackupService.getAll()).thenReturn(backups);

        final List<BackupDetails> backupsDetails = gmailSafeService.getAllBackups();

        assertThat(backupsDetails).containsExactlyInAnyOrder(
                new BackupDetails(backup1Id, backup1Date, BackupStatus.IN_PROGRESS.getDescription()),
                new BackupDetails(backup2Id, backup2Date, BackupStatus.IN_PROGRESS.getDescription())
            );
    }
}
