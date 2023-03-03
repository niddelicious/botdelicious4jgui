package com.niddelicious.botdelicious4jgui.tests;

import com.niddelicious.botdelicious4jgui.BotConfiguration;
import com.niddelicious.botdelicious4jgui.LoginCredentialsController;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;

public class LoginCredentialsControllerTest {

    BotConfiguration botConfig;

    @Before
    public void setBotConfig(){
        botConfig = new BotConfiguration();
    }

    @Test
    public void testSaveInputsToConfig(){
        LoginCredentialsController loginCredentialsController = new LoginCredentialsController();
        assertNotEquals("client_id_key", botConfig.get("clientId"));
        assertNotEquals("client_secret_key", botConfig.get("clientSecret"));
        assertNotEquals("refresh_token_key", botConfig.get("refreshToken"));
        loginCredentialsController.set("clientId", "client_id_key");
        loginCredentialsController.set("clientSecret", "client_secret_key");
        loginCredentialsController.set("refreshToken", "refresh_token_key");
        assertEquals("client_id_key", botConfig.get("clientId"));
        assertEquals("client_secret_key", botConfig.get("clientSecret"));
        assertEquals("refresh_token_key", botConfig.get("refreshToken"));
    }
}
