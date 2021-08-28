package com.gsuitesafe.gmailsafe.services.gmail.integration;

import com.google.api.services.gmail.model.Message;
import com.gsuitesafe.gmailsafe.services.gmail.models.GetAllMessagesResponse;
import com.gsuitesafe.gmailsafe.services.gmail.models.GmailAPIProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GmailAPIService {

    @Autowired
    private GmailAPIProperties gmailAPIProperties;

    public GetAllMessagesResponse getAllMessages(final String userId) {
        final String url = String.format("%s/v1/users/%s/messages?maxResults=%d",
                gmailAPIProperties.getUrl(),
                userId,
                gmailAPIProperties.getDefaultMaxResults());
        return new RestTemplate().getForObject(url, GetAllMessagesResponse.class);
    }

    public Message getMessage(final String userId, final String messageId) {
        final String url = String.format("%s/v1/users/%s/messages/%s",
                gmailAPIProperties.getUrl(),
                userId,
                messageId);
        return new RestTemplate().getForObject(url, Message.class);
    }

}
