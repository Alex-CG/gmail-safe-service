package com.gsuitesafe.gmailsafe.services.gmail.integration;

import com.gsuitesafe.gmailsafe.services.gmail.models.GetAllMessagesResponse;
import com.gsuitesafe.gmailsafe.services.gmail.configs.GmailAPIProperties;
import com.gsuitesafe.gmailsafe.services.gmail.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GmailAPIService {

    @Autowired
    private GmailAPIProperties gmailAPIProperties;

    private static final String ACCESS_TOKEN = "";

    public GetAllMessagesResponse getAllMessages(final String userId) {
        final String url = String.format("%s/v1/users/%s/messages?maxResults=%d",
                gmailAPIProperties.getUrl(),
                userId,
                gmailAPIProperties.getDefaultMaxResults());

        return new RestTemplate()
                .exchange(url, HttpMethod.GET, getRequestConfig(), GetAllMessagesResponse.class)
                .getBody();
    }

    public Message getMessage(final String userId, final String messageId) {
        final String url = String.format("%s/v1/users/%s/messages/%s?format=raw",
                gmailAPIProperties.getUrl(),
                userId,
                messageId);

        return new RestTemplate()
                .exchange(url, HttpMethod.GET, getRequestConfig(), Message.class)
                .getBody();
    }

    private HttpEntity<?> getRequestConfig() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(ACCESS_TOKEN);
        return new HttpEntity<>(headers);
    }

}
