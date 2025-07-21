package com.example.demo.Accounts.Controller;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {
    @GetMapping("/token")
    public String getToken(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client) {
        return "Access Token: " + client.getAccessToken().getTokenValue() +
                "\nRefresh Token: " + (client.getRefreshToken() != null ? client.getRefreshToken().getTokenValue() : "N/A");
    }
}
