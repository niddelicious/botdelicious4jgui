package com.niddelicious.botdelicious4jgui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginCredentialsController {
    BotConfiguration botConfig;

    @FXML
    private TextField clientIdField;
    @FXML
    private Label currentClientId;

    public LoginCredentialsController(){
        botConfig = new BotConfiguration();
    }

    public void set(String key, String value){
        botConfig.add(key, value);
    }

    @FXML
    protected void onClientIdChanged() {
        String clientIdFieldValue = clientIdField.getText();
        this.set("clientId", clientIdFieldValue);
        this.updateCurrentClientId();
    }

    @FXML
    protected void updateCurrentClientId() {
        currentClientId.setText(botConfig.get("clientId").toString());
    }
}
