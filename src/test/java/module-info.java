module com.niddelicious.botdelicious4jgui_tests {
    requires junit;
    requires com.niddelicious.botdelicious4jgui;
    opens com.niddelicious.botdelicious4jgui_tests to javafx.fxml;
    exports com.niddelicious.botdelicious4jgui_tests;
}