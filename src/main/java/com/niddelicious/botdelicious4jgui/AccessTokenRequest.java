package com.niddelicious.botdelicious4jgui;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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


    public String getClientId() {
        return clientId;
    }
    public String getClientSecret() {
        return clientSecret;
    }
    public String getRefreshToken() {
        return refreshToken;
    }
    public String getRefreshUrl() {
        return refreshUrl;
    }


    public void refreshAccessToken() throws IOException, JSONException {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("client_id", clientId);
        parameters.put("client_secret", clientSecret);
        parameters.put("refresh_token", refreshToken);

        String response = sendPostRequest(refreshUrl, parameters, "x-www-form-urlencoded");
        JSONObject responseJson = new JSONObject(response);

        accessToken = responseJson.getString("access_token");
    }

    public String sendPostRequest(String url, Map<String, String> parameters, String contentType) {
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            // Set request method
            con.setRequestMethod("POST");

            // Set content type header
            con.setRequestProperty("Content-Type", contentType);

            // Build parameters as a string
            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String, String> param : parameters.entrySet()) {
                if (postData.length() != 0) {
                    postData.append("&");
                }
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append("=");
                postData.append(URLEncoder.encode(param.getValue(), "UTF-8"));
            }

            // Send the request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postData.toString());
            wr.flush();
            wr.close();

            // Get the response
            int responseCode = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Return the response as a string
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}

