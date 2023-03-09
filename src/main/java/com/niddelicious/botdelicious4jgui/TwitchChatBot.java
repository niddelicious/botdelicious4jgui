package com.niddelicious.botdelicious4jgui;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.ITwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.niddelicious.botdelicious4jgui.features.WriteChannelChatToConsole;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TwitchChatBot {

    /**
     * Holds the Bot Configuration
     */
    static Properties prop;

    /**
     * Twitch4J API
     */
    private ITwitchClient twitchClient;

    /**
     * Constructor
     */
    public TwitchChatBot() {
        loadProperties();

        TwitchClientBuilder clientBuilder = TwitchClientBuilder.builder();

        twitchClient = clientBuilder
                .withDefaultAuthToken(new OAuth2Credential("twitch", prop.getProperty("accessToken")))
                .build();
    }

    private void loadProperties() {
        prop = new Properties();
        InputStream in = getClass().getResourceAsStream("/properties/config.properties");
        try {
            prop.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to register all features
     */
    public void registerFeatures() {
        SimpleEventHandler eventHandler = twitchClient.getEventManager().getEventHandler(SimpleEventHandler.class);

        // Register Event-based features
        WriteChannelChatToConsole writeChannelChatToConsole = new WriteChannelChatToConsole(eventHandler);
    }


    public void start() {
        twitchClient.getChat().joinChannel(prop.getProperty("twitchChannel"));


        // Enable client helper for Stream GoLive / GoOffline / GameChange / TitleChange Events
        twitchClient.getClientHelper().enableStreamEventListener(prop.getProperty("twitchChannel"));
        // Enable client helper for Follow Event
        twitchClient.getClientHelper().enableFollowEventListener(prop.getProperty("twitchChannel"));
    }

    public ITwitchClient getTwitchClient() {
        return twitchClient;
    }
}