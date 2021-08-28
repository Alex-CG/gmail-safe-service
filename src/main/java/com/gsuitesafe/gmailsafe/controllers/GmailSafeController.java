package com.gsuitesafe.gmailsafe.controllers;

import com.gsuitesafe.gmailsafe.models.BackupDetails;
import com.gsuitesafe.gmailsafe.models.BackupId;
import com.gsuitesafe.gmailsafe.services.GmailSafeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class GmailSafeController {

    // TODO: this should be retrieve from an oAuth session
    private final static String USER_ID = "xyz@gmail.com";

    @Autowired
    private GmailSafeService service;

    @PostMapping("/v0.1/backups")
    public ResponseEntity<BackupId> initBackup() {
        return ResponseEntity.ok(service.initBackup(USER_ID));
    }

    @GetMapping("/v0.1/backups")
    public ResponseEntity<List<BackupDetails>> getAllBackups() {
        return ResponseEntity.ok(service.getAllBackups());
    }

    @GetMapping("/v0.1/exports/{backupId}")
    public ResponseEntity exportBackup(@PathVariable("backupId") String backupId) {
        return ResponseEntity.ok(service.exportBackup(backupId, USER_ID));
    }

    @GetMapping("/v0.2/exports/{backupId}/{label}")
    public ResponseEntity exportBackup(@PathVariable("backupId") String backupId,
                                       @PathVariable("label") String label) {
        return ResponseEntity.ok(service.exportBackup(backupId, USER_ID, label));
    }

}
