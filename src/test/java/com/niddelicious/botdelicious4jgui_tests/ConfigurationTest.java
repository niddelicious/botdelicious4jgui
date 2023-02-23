package com.niddelicious.botdelicious4jgui_tests;

import com.niddelicious.botdelicious4jgui.BotConfiguration;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertArrayEquals;

public class ConfigurationTest {

    BotConfiguration botConfig;
    @Before
    public void getConfiguration(){
        botConfig = new BotConfiguration();
    }
    @Test
    public void testAddAndGet(){
        assertNotEquals("valueA", botConfig.get("configA"));
        assertNotEquals("valueB", botConfig.get("configB"));
        botConfig.add("configA", "valueA");
        assertEquals("valueA", botConfig.get("configA"));
        assertNotEquals("valueB", botConfig.get("configB"));
        botConfig.add("configB", "valueB");
        assertEquals("valueA", botConfig.get("configA"));
        assertEquals("valueB", botConfig.get("configB"));
    }

    @Test
    public void testAddAndGetSingleDimensionArray(){
        botConfig.add("arrayA", new String[]{"valueA", "valueB"});
        assertArrayEquals(new String[]{"valueA", "valueB"}, (String[]) botConfig.get("arrayA"));
    }

    @Test
    public void testAddAndGetMultidimensionalArray(){
        botConfig.add("arrayB", new String[][]{{"valueA", "valueB"}, {"valueC", "valueD"}});
        assertArrayEquals(new String[][]{{"valueA", "valueB"}, {"valueC", "valueD"}}, (String[][]) botConfig.get("arrayB"));
    }
}
