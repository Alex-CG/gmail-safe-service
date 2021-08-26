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

    @Autowired
    private GmailSafeService service;

    @PostMapping("/v0.1/backups")
    public ResponseEntity<BackupId> initBackups() {
        return ResponseEntity.ok(service.initBackups());
    }

    @GetMapping("/v0.1/backups")
    public ResponseEntity<List<BackupDetails>> getAllBackups() {
        return ResponseEntity.ok(service.getAllBackups());
    }

    @GetMapping("/v0.1/exports/{backupId}")
    public ResponseEntity getBackup(@PathVariable("backupId") String backupId) {
        return ResponseEntity.ok(service.getBackup(backupId));
    }

    @GetMapping("/v0.2/exports/{backupId}/{label}")
    public ResponseEntity getBackup(@PathVariable("backupId") String backupId,
                                    @PathVariable("label") String label) {
        return ResponseEntity.ok(service.getBackup(backupId, label));
    }

}
