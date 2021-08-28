package com.gsuitesafe.gmailsafe.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gsuitesafe.gmailsafe.exceptionhandling.BackupNotFoundException;
import com.gsuitesafe.gmailsafe.exceptionhandling.ErrorResponse;
import com.gsuitesafe.gmailsafe.models.BackupDetails;
import com.gsuitesafe.gmailsafe.models.BackupId;
import com.gsuitesafe.gmailsafe.models.BackupStatus;
import com.gsuitesafe.gmailsafe.services.GmailSafeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = GmailSafeController.class)
public class GmailSafeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GmailSafeService service;

    private final static String USER_ID = "xyz@gmail.com";

    @Test
    public void notFound() throws Exception {
        mockMvc.perform(get("/v0.1/not-found"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void initBackup_returnsBackupId() throws Exception {
        final String backupId = "3fcb7e7b-9932-429f-9266-1ed609bfcce8";
        final BackupId expectedResponse = new BackupId(backupId);

        when(service.initBackup(USER_ID)).thenReturn(new BackupId(backupId));

        final MvcResult result = mockMvc.perform(post("/v0.1/backups"))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse().getContentAsString())
                .isEqualTo(objectMapper.writeValueAsString(expectedResponse));
    }

    @Test
    public void getAllBackups_returnsBackupsDetails() throws Exception {
        final String backup1Id = "7799898c-a54a-4aac-a576-0a4b0df559d7";
        final ZonedDateTime backup1Date = ZonedDateTime.now();
        final String backup2Id = "293edb7f-deb5-424a-8d09-90a5d6516566";
        final ZonedDateTime backup2Date = ZonedDateTime.now();
        final BackupDetails backupDetails1 = new BackupDetails(backup1Id, backup1Date, BackupStatus.IN_PROGRESS.getDescription());
        final BackupDetails backupDetails2 = new BackupDetails(backup2Id, backup2Date, BackupStatus.IN_PROGRESS.getDescription());

        final List<BackupDetails> expectedResponse = Arrays.asList(backupDetails1, backupDetails2);

        when(service.getAllBackups()).thenReturn(Arrays.asList(backupDetails1, backupDetails2));

        final MvcResult result = mockMvc.perform(get("/v0.1/backups"))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse().getContentAsString())
                .isEqualTo(objectMapper.writeValueAsString(expectedResponse));
    }

    @Test
    public void exportBackup_returnsNotFound() throws Exception {
        final String unknownId = "XYZ-123-ID";
        final BackupNotFoundException ex = new BackupNotFoundException(unknownId);
        when(service.exportBackup(anyString(), anyString())).thenThrow(ex);

        final MvcResult result = mockMvc.perform(get(String.format("/v0.1/exports/%s", unknownId)))
                .andExpect(status().isNotFound())
                .andReturn();
        assertThat(result.getResponse().getContentAsString())
                .isEqualTo(objectMapper.writeValueAsString(
                        new ErrorResponse(String.format("Backup not found for id: %s", unknownId))));
    }

    @Test
    public void exportBackupWithLabel_returnsNotFound() throws Exception {
        final String unknownId = "XYZ-123-ID";
        final BackupNotFoundException ex = new BackupNotFoundException(unknownId);
        when(service.exportBackup(anyString(), anyString(), anyString())).thenThrow(ex);

        final MvcResult result = mockMvc.perform(get(String.format("/v0.2/exports/%s/label", unknownId)))
                .andExpect(status().isNotFound())
                .andReturn();
        assertThat(result.getResponse().getContentAsString())
                .isEqualTo(objectMapper.writeValueAsString(
                        new ErrorResponse(String.format("Backup not found for id: %s", unknownId))));
    }
}
