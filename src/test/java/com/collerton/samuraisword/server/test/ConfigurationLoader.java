/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collerton.samuraisword.server.test;

import com.collerton.samuraisword.server.model.Role;
import java.io.InputStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

/**
 *
 * @author thomas
 */
public class ConfigurationLoader {
    
    public ConfigurationLoader() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @Test
    public void testYAML() {
        Yaml yaml = new Yaml(new Constructor(Role.class));
        InputStream inputStream = this.getClass()
          .getClassLoader()
          .getResourceAsStream("config4.yml");

        int count = 0;
        for (Object object : yaml.loadAll(inputStream)) {
            Role r = (Role)object;
            count++;
            assertTrue(object instanceof Role);
        }
        assertEquals(4,count);
    }
    
    @AfterEach
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
