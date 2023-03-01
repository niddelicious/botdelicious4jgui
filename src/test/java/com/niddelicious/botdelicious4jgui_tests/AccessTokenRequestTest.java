package com.niddelicious.botdelicious4jgui_tests;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.niddelicious.botdelicious4jgui.AccessTokenRequest;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class AccessTokenRequestTest {
    private static final String ACCESS_TOKEN = "1ssjqsqfy6bads1ws7m03gras79zfr";
    private static final String CLIENT_ID = "client_id";
    private static final String CLIENT_SECRET = "client_secret";
    private static final String REFRESH_TOKEN = "refresh_token";
    private static final String REFRESH_URL = "https://id.twitch.tv/oauth2/token";
    private static final String RESPONSE_BODY = "{\"access_token\":\"" + ACCESS_TOKEN + "\"," +
            "\"refresh_token\":\"eyJfMzUtNDU0OC4MWYwLTQ5MDY5ODY4NGNlMSJ9%asdfasdf=\"," +
            "\"scope\":[\"channel:read:subscriptions\",\"channel:manage:polls\"]," +
            "\"token_type\":\"bearer\"}";

    @Mock
    private AccessTokenRequest accessTokenRequest;

    @Before
    public void setUp() throws Exception {
        accessTokenRequest = new AccessTokenRequest(CLIENT_ID, CLIENT_SECRET, REFRESH_TOKEN, REFRESH_URL);
    }

    @Test
    public void testRefreshAccessToken() throws IOException, JSONException {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("client_id", CLIENT_ID);
        parameters.put("client_secret", CLIENT_SECRET);
        parameters.put("refresh_token", REFRESH_TOKEN);

        when(accessTokenRequest.sendPostRequest(REFRESH_URL, parameters, "x-www-form-urlencoded"))
                .thenReturn(RESPONSE_BODY);

        accessTokenRequest.refreshAccessToken();
        assertEquals(ACCESS_TOKEN, accessTokenRequest.getAccessToken());
    }
}
