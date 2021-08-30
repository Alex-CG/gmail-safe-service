package com.gsuitesafe.gmailsafe.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class EnvironmentProperties {

    @Autowired
    private Environment env;

    private final String ENABLE_GMAIL_API_PROPERTY = "enable-gmail-api";

    @Bean
    public boolean isGmailAPiEnabled() {
        return Boolean.parseBoolean(env.getProperty(ENABLE_GMAIL_API_PROPERTY));
    }

}
