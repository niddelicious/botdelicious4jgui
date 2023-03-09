module com.niddelicious.botdelicious4jgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.swing;
    requires javafx.graphics;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires org.json;
    requires twitch4j;
    requires events4j.handler.simple;
    requires credentialmanager;
    requires com.fasterxml.jackson.databind;
    requires twitch4j.chat;
    requires twitch4j.common;
    requires events4j.core;


    opens com.niddelicious.botdelicious4jgui to javafx.fxml;
    exports com.niddelicious.botdelicious4jgui;
}