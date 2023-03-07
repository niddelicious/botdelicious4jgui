package com.niddelicious.botdelicious4jgui;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AccessTokenRequest {
    private String clientId;
    private String clientSecret;
    private String refreshToken;
    private String refreshUrl;
    private String accessToken;

    public AccessTokenRequest(String clientId, String clientSecret, String refreshToken, String refreshUrl) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.refreshToken = refreshToken;
        this.refreshUrl = refreshUrl;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void refreshAccessToken() throws IOException, JSONException {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("grant_type", "refresh_token");
        parameters.put("client_id", clientId);
        parameters.put("client_secret", clientSecret);
        parameters.put("refresh_token", refreshToken);

        String response = sendPostRequest(refreshUrl, parameters);
        JSONObject responseJson = new JSONObject(response);

        accessToken = responseJson.getString("access_token");
    }

    public String sendPostRequest(String url, Map<String, String> parameters) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        JSONObject jsonObject = new JSONObject(parameters);
        con.setDoOutput(true);
        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
            wr.writeBytes(jsonObject.toString());
            wr.flush();
        }

        int responseCode = con.getResponseCode();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            return response.toString();
        }
    }


}

