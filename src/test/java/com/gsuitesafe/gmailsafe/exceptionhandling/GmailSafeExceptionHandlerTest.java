package com.gsuitesafe.gmailsafe.exceptionhandling;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;
import com.gsuitesafe.gmailsafe.exceptionhandling.exceptions.BackupDataNotFoundByIdException;
import com.gsuitesafe.gmailsafe.exceptionhandling.models.ErrorResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GmailSafeExceptionHandlerTest {

    @Mock
    private Appender mockAppender;

    @Captor
    private ArgumentCaptor<LoggingEvent> captor;

    private GmailSafeExceptionHandler exceptionHandler;

    private Logger logger;

    @BeforeEach
    public void setup() {
        exceptionHandler = new GmailSafeExceptionHandler();
        logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        logger.addAppender(mockAppender);
    }

    @AfterEach
    public void teardown() {
        logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        logger.detachAppender(mockAppender);
    }

    @Test
    public void handleGenericException() {
        assertResponse(exceptionHandler.handle(new Exception()),
                HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error",  "Internal server error :", Level.ERROR);
    }

    @Test
    public void handleBackupNotFoundByIdException() {
        final String unknownBackupId = "unknownBackupId";
        final BackupDataNotFoundByIdException ex = new BackupDataNotFoundByIdException(unknownBackupId);
        final String message = String.format("Backup Data not found for id: %s", unknownBackupId);

        assertResponse(exceptionHandler.handle(ex),
                HttpStatus.NOT_FOUND, message,  message, Level.ERROR);
    }

    private void assertResponse(final ResponseEntity<ErrorResponse> response, final HttpStatus status,
                                final String responseMessage, final String logMessage, final Level level) {
        verify(mockAppender).doAppend(captor.capture());

        assertThat(response.getStatusCode())
                .isEqualTo(status);
        assertThat(response.getBody() == null ? null : response.getBody().getMessage())
                .isEqualTo(responseMessage);
        assertThat(captor.getValue().getFormattedMessage())
                .contains(logMessage);
        assertThat(captor.getValue().getLevel())
                .isEqualTo(level);
    }

}
