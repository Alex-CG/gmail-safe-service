package com.gsuitesafe.gmailsafe.utils;

import com.gsuitesafe.gmailsafe.models.BackupStatus;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BackupUtils {

    public static BackupStatus getBackupStatus(final int status) {
        switch (status) {
            case 0:
                return BackupStatus.IN_PROGRESS;
            case 1:
                return BackupStatus.OK;
            default:
                return BackupStatus.FAILED;
        }
    }

}
