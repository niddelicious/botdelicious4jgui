package com.niddelicious.botdelicious4jgui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.*;
import java.net.URL;
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
        try {
            File file = getPropertiesFile();
            InputStream in = new FileInputStream(file);
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
        try (FileOutputStream out = new FileOutputStream(getPropertiesFile())) {
            prop.store(out, null);
            titleLabel.setText("Properties saved");
            loginButton.setText("Save again");
        } catch (IOException e) {
            System.out.println("Failed to save config: " + e.getMessage());
            titleLabel.setText("Something went wrong, try again");
            loginButton.setText("Log in");
        }
    }
    private File getPropertiesFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("properties/config.properties");
        File file;
        if (resource == null) {
            file = new File("src/main/resources/properties/config.properties");
            try {
                boolean fileCreated = file.createNewFile();
                if (!fileCreated) {
                    throw new IOException("Unable to create file at specified path.");
                }
            } catch (IOException e) {
                throw new IllegalArgumentException("Properties file could not be created: " + e.getMessage());
            }
        } else {
            file = new File(resource.getFile());
        }
        return file;
    }


}
