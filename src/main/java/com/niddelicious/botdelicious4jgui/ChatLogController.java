package com.niddelicious.botdelicious4jgui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ChatLogController {

    @FXML
    private TextArea chatLogTextArea;

    private List<String> chatMessages = new ArrayList<>();

    public ChatLogController(){
        System.out.println("Loaded chat log class!");
    }
    public void addMessage(String message) {
        chatMessages.add(message);
        updateChatLog();
    }

    private void updateChatLog() {
        StringBuilder sb = new StringBuilder();
        for (String message : chatMessages) {
            sb.append(message).append("\n");
        }
        chatLogTextArea.setText(sb.toString());
    }
}

