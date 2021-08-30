package com.gsuitesafe.gmailsafe.services.gmail.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
