module com.niddelicious.botdelicious4jgui.tests {
    requires com.niddelicious.botdelicious4jgui;
    requires junit;
    requires wiremock;
    requires org.json;
    opens com.niddelicious.botdelicious4jgui.tests to javafx.fxml;
    exports com.niddelicious.botdelicious4jgui.tests;
}