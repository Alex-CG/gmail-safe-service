package com.gsuitesafe.gmailsafe.utils;

import com.gsuitesafe.gmailsafe.models.BackupStatus;
import lombok.experimental.UtilityClass;

import java.util.Random;

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

    public static Integer getRandomDuration() {
        Random r = new Random();
        int low = 10;
        int high = 60;
        return r.nextInt(high - low) + low;
    }

}
