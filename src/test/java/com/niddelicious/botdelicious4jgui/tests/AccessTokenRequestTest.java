package com.niddelicious.botdelicious4jgui.tests;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.niddelicious.botdelicious4jgui.AccessTokenRequest;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.Assert.assertEquals;



public class AccessTokenRequestTest {
    private static final String ACCESS_TOKEN = "1ssjqsqfy6bads1ws7m03gras79zfr";
    private static final String CLIENT_ID = "client_id";
    private static final String CLIENT_SECRET = "client_secret";
    private static final String REFRESH_TOKEN = "refresh_token";
    private static final String REFRESH_URL = "http://localhost:8080/refresh";
    private static final String RESPONSE_BODY = "{\"access_token\":\"" + ACCESS_TOKEN + "\"," +
            "\"refresh_token\":\"eyJfMzUtNDU0OC4MWYwLTQ5MDY5ODY4NGNlMSJ9%asdfasdf=\"," +
            "\"scope\":[\"channel:read:subscriptions\",\"channel:manage:polls\"]," +
            "\"token_type\":\"bearer\"}";
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(8080).httpsPort(8443));

    private AccessTokenRequest accessTokenRequest;

    @Before
    public void setUp() throws Exception {
        accessTokenRequest = new AccessTokenRequest(CLIENT_ID, CLIENT_SECRET, REFRESH_TOKEN, REFRESH_URL);
        try {
            configureFor("localhost", 8080);
            stubFor(post(urlEqualTo("/refresh"))
                    .willReturn(aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", "application/json")
                            .withBody(RESPONSE_BODY)));
        } catch (Exception e){
            throw new Exception("Error setting up WireMock server:" + e.getMessage());
        }
    }

    @Test
    public void testRefreshAccessToken() throws IOException, JSONException {
        accessTokenRequest.refreshAccessToken();
        assertEquals(ACCESS_TOKEN, accessTokenRequest.getAccessToken());
    }
}
