package com.niddelicious.botdelicious4jgui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginCredentialsController {
    BotConfiguration botConfig;

    @FXML
    private Label title;
    @FXML
    private TextField clientIdField;
    @FXML
    private TextField clientSecretField;
    @FXML
    private TextField refreshTokenField;
    @FXML
    private Button doLogin;

    public LoginCredentialsController(){
        botConfig = new BotConfiguration();
    }

    public void set(String key, String value){
        botConfig.add(key, value);
    }

    @FXML
    protected void onClientIdChanged() {
        this.set("clientId", clientIdField.getText());
    }

    @FXML
    protected void onClientSecretChanged() {
        this.set("clientSecret", clientSecretField.getText());
    }

    @FXML
    protected void onRefreshTokenChanged() {
        this.set("refreshToken", refreshTokenField.getText());
    }

}
