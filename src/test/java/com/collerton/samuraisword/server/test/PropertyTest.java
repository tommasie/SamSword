/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collerton.samuraisword.server.test;

import com.collerton.samuraisword.server.model.Player;
import com.collerton.samuraisword.server.model.properties.Armour;
import com.collerton.samuraisword.server.model.properties.Property;
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
public class PropertyTest {
    
    public PropertyTest() {
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

    @Test
    public void testArmour() {
        Player player = new Player("name");
        Property armour = new Armour();
        armour.play(player);
        assertEquals(1, player.getDistanceBonus());
    }
}
