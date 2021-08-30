package com.gsuitesafe.gmailsafe.utils;

import com.gsuitesafe.gmailsafe.models.BackupStatus;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class AppUtilsTest {

    @Test
    public void getBackupStatus_returnsInProgressStatus() {
        final int IN_PROGRESS_STATUS = 0;

        final BackupStatus status = AppUtils.getBackupStatus(IN_PROGRESS_STATUS);

        assertThat(status).isEqualTo(BackupStatus.IN_PROGRESS);
    }

    @Test
    public void getBackupStatus_returnsOkStatus() {
        final int OK_STATUS = 1;

        final BackupStatus status = AppUtils.getBackupStatus(OK_STATUS);

        assertThat(status).isEqualTo(BackupStatus.OK);
    }

    @Test
    public void getBackupStatus_returnsFailedStatus() {
        final int FAILED_STATUS = 2;

        final BackupStatus status = AppUtils.getBackupStatus(FAILED_STATUS);

        assertThat(status).isEqualTo(BackupStatus.FAILED);
    }

    @Test
    public void getRandomDuration_returnsValidNumber() {
        Stream.of(new int[10][]).forEach(x -> {
            final Integer duration = AppUtils.getRandomDuration();

            assertThat(duration).isGreaterThanOrEqualTo(10).isLessThanOrEqualTo(60);
        });
    }

}
