/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collerton.samuraisword.server.test;

import com.collerton.samuraisword.server.config.ConfigFactory;
import com.collerton.samuraisword.server.model.Role;
import com.collerton.samuraisword.server.model.Weapon;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author thomas
 */
public class ConfigFactoryTest {
    
    public ConfigFactoryTest() {
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
    
    @AfterEach
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testCheckLoadedFourRoles() {
        ConfigFactory factory = ConfigFactory.getInstance(4);
        List<Role> roles = factory.getRoles();
        assertEquals(4, roles.size());
        assertEquals("Shogun", roles.get(0).getName());
        assertEquals(1, roles.get(0).getHonorMultiplier());
        assertEquals("Samurai", roles.get(1).getName());
        assertEquals(2, roles.get(1).getHonorMultiplier());
        assertEquals("Ninja", roles.get(3).getName());
        assertEquals(1, roles.get(2).getHonorMultiplier());
        assertEquals("Ninja", roles.get(3).getName());
        assertEquals(2, roles.get(3).getHonorMultiplier());
    }
    
    @Test
    public void testCheckLoadedFiveRoles() {
        ConfigFactory factory = ConfigFactory.getInstance(5);
        List<Role> roles = factory.getRoles();
        assertEquals(5, roles.size());
        assertEquals("Shogun", roles.get(0).getName());
        assertEquals(1, roles.get(0).getHonorMultiplier());
        assertEquals("Samurai", roles.get(1).getName());
        assertEquals(1, roles.get(1).getHonorMultiplier());
        assertEquals("Ninja", roles.get(3).getName());
        assertEquals(1, roles.get(2).getHonorMultiplier());
        assertEquals("Ninja", roles.get(3).getName());
        assertEquals(1, roles.get(3).getHonorMultiplier());
        assertEquals("Ronin", roles.get(4).getName());
        assertEquals(2, roles.get(4).getHonorMultiplier());
    }
    
     @Test
    public void testCheckLoadedSixRoles() {
        ConfigFactory factory = ConfigFactory.getInstance(6);
        List<Role> roles = factory.getRoles();
        assertEquals(6, roles.size());
        assertEquals("Shogun", roles.get(0).getName());
        assertEquals(1, roles.get(0).getHonorMultiplier());
        assertEquals("Samurai", roles.get(1).getName());
        assertEquals(2, roles.get(1).getHonorMultiplier());
        assertEquals("Ninja", roles.get(3).getName());
        assertEquals(1, roles.get(2).getHonorMultiplier());
        assertEquals("Ninja", roles.get(3).getName());
        assertEquals(1, roles.get(3).getHonorMultiplier());
        assertEquals("Ninja", roles.get(4).getName());
        assertEquals(1, roles.get(4).getHonorMultiplier());
        assertEquals("Ronin", roles.get(5).getName());
        assertEquals(3, roles.get(5).getHonorMultiplier());
    }
}
