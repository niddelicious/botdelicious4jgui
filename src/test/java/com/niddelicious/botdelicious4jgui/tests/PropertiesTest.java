package com.niddelicious.botdelicious4jgui.tests;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class PropertiesTest {

    private static Properties prop;

    @Before
    public void loadProperties() {
        prop = new Properties();
        InputStream in = getClass().getResourceAsStream("/properties.test/fixed.properties");
        try {
            prop.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testProperties() {
        assertEquals("valueA", prop.getProperty("keyA"));
        assertEquals("valueB", prop.getProperty("keyB"));
        assertEquals("valueC", prop.getProperty("keyC"));
    }

}
