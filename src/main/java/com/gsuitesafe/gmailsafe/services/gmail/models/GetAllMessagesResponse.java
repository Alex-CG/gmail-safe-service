package com.gsuitesafe.gmailsafe.services.gmail.models;

import lombok.Data;

import java.util.List;

@Data
public class GetAllMessagesResponse {

    private List<MessageInfo> messages;
    private String nextPageToken;
    private Integer resultSizeEstimate;

}
