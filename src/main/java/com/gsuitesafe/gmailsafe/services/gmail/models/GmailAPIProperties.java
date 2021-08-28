package com.gsuitesafe.gmailsafe.services.gmail.models;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@Component
@ConfigurationProperties(prefix = "gmail.api")
public class GmailAPIProperties {

    @NotEmpty
    private String url;

    @Min(1)
    @Max(1_000)
    private Integer defaultMaxResults;

}
