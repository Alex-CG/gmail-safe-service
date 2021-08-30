package com.gsuitesafe.gmailsafe.services.gmail.models;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class Message {

    private BigInteger historyId;
    private String id;
    private Long internalDate;
    private MessagePart payload;
    private List<String> labelIds;
    private String raw;
    private Integer sizeEstimate;
    private String snippet;
    private String threadId;
}
