package com.gsuitesafe.gmailsafe.utils;

import com.gsuitesafe.gmailsafe.models.BackupStatus;
import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class AppUtils {

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

    public static Integer getRandomDuration() {
        Random r = new Random();
        int low = 10; // min duration that a backup can take
        int high = 60; // max duration that a backup can take
        return r.nextInt(high - low) + low;
    }

}
