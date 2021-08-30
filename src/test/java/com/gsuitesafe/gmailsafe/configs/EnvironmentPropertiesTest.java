package com.gsuitesafe.gmailsafe.configs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EnvironmentPropertiesTest {

    @Mock
    private Environment mockEnv;

    @InjectMocks
    private EnvironmentProperties environmentProperties;

    private final String ENABLE_GMAIL_API_PROPERTY = "enable-gmail-api";

    @Test
    public void isGmailAPiEnabled_returnsTrue() {
        when(mockEnv.getProperty(ENABLE_GMAIL_API_PROPERTY)).thenReturn("true");

        assertThat(environmentProperties.isGmailAPiEnabled()).isTrue();
    }

    @Test
    public void isGmailAPiEnabled_ignoreCase_returnsTrue() {
        when(mockEnv.getProperty(ENABLE_GMAIL_API_PROPERTY)).thenReturn("TRue");

        assertThat(environmentProperties.isGmailAPiEnabled()).isTrue();
    }

    @Test
    public void isGmailAPiEnabled_nonTrueValue_returnsFalse() {
        when(mockEnv.getProperty(ENABLE_GMAIL_API_PROPERTY)).thenReturn("123");

        assertThat(environmentProperties.isGmailAPiEnabled()).isFalse();
    }

}
