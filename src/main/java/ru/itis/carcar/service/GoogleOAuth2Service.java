package ru.itis.carcar.service;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

public interface GoogleOAuth2Service {
    String buildAuthUrl(String state, String nonce);
    JsonNode exchangeCodeForTokens(String code);
    Map<String, Object> decodeIdToken(String idToken) throws Exception;
}
