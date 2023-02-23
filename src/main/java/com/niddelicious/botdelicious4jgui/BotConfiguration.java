package com.niddelicious.botdelicious4jgui;

import java.util.HashMap;
import java.util.Map;

public class BotConfiguration {

    private static Map<String, Object> botConfig;
    public BotConfiguration(){
        botConfig = new HashMap<>();
    }

    public void add(String key, Object value){
        botConfig.put(key, value);
    }

    public Object get(String key){
        return botConfig.get(key);
    }
}
