package com.gsuitesafe.gmailsafe.services.gmail.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Backup {

    private String id;
    private ZonedDateTime date;
    private Integer status;
    private Object data;
    private ZonedDateTime dateToComplete;
}
