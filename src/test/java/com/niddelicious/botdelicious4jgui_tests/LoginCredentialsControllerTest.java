package com.niddelicious.botdelicious4jgui_tests;

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
        loginCredentialsController.set("clientId", "client_id_key");
        assertEquals("client_id_key", botConfig.get("clientId"));
    }
}
