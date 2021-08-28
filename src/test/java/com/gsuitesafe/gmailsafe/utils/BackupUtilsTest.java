package com.gsuitesafe.gmailsafe.utils;

import com.gsuitesafe.gmailsafe.models.BackupStatus;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BackupUtilsTest {

    @Test
    public void retrieve_InProgressStatus() {
        final int IN_PROGRESS_STATUS = 0;

        final BackupStatus status = BackupUtils.getBackupStatus(IN_PROGRESS_STATUS);

        assertThat(status).isEqualTo(BackupStatus.IN_PROGRESS);
    }

    @Test
    public void retrieve_OkStatus() {
        final int OK_STATUS = 1;

        final BackupStatus status = BackupUtils.getBackupStatus(OK_STATUS);

        assertThat(status).isEqualTo(BackupStatus.OK);
    }

    @Test
    public void retrieve_FailedStatus() {
        final int FAILED_STATUS = 2;

        final BackupStatus status = BackupUtils.getBackupStatus(FAILED_STATUS);

        assertThat(status).isEqualTo(BackupStatus.FAILED);
    }

}
