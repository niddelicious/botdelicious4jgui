package com.niddelicious.botdelicious4jgui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoginCredentialsController {
    BotConfiguration botConfig;
    static Properties prop;

    @FXML
    private Label titleLabel;
    @FXML
    private TextField clientIdField;
    @FXML
    private TextField clientSecretField;
    @FXML
    private TextField refreshTokenField;
    @FXML
    private Button loginButton;

    public LoginCredentialsController(){
        botConfig = new BotConfiguration();
        prop = new Properties();
    }

    @FXML
    private void initialize() {
        loadProperties();
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
        loadConfigToFields();
    }

    public void set(String key, String value){
        botConfig.add(key, value);
    }

    public void loadConfigToFields() {
        clientIdField.setText(prop.getProperty("clientId", ""));
        clientSecretField.setText(prop.getProperty("clientSecret", ""));
        refreshTokenField.setText(prop.getProperty("refreshToken", ""));
        this.set("clientId", clientIdField.getText());
        this.set("clientSecret", clientSecretField.getText());
        this.set("refreshToken", refreshTokenField.getText());
    }

    @FXML
    protected void onTextFieldChanged(KeyEvent event) {
        TextField source = (TextField) event.getSource();
        String configName = source.getId();
        String destinationName = configName.replace("Field", "");
        this.set(destinationName, source.getText());
    }

    @FXML
    protected void onLoginButtonPress() {
        titleLabel.setText("Attempting to log in...");
        loginButton.setText("Logging in...");
        setProperties();
        saveProperties();


    }
    private void setProperties() {
        prop.setProperty("clientId", clientIdField.getText());
        prop.setProperty("clientSecret", clientSecretField.getText());
        prop.setProperty("refreshToken", refreshTokenField.getText());
    }

    private void saveProperties() {
        try (FileOutputStream out = new FileOutputStream("src/main/resources/properties/config.properties")) {
            prop.store(out, null);
            titleLabel.setText("Properties saved");
            loginButton.setText("Save again");
        } catch (IOException e) {
            System.out.println("Failed to save config: " + e.getMessage());
            titleLabel.setText("Something went wrong, try again");
            loginButton.setText("Log in");
        }
    }


}
