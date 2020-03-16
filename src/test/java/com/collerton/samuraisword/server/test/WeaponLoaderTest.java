/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collerton.samuraisword.server.test;

import com.collerton.samuraisword.server.config.WeaponLoader;
import com.collerton.samuraisword.server.model.GameSingleton;
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
public class WeaponLoaderTest {
    
    public WeaponLoaderTest() {
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
    public void testLoadWeapons() {
        WeaponLoader loader = new WeaponLoader();
        List<Weapon> weapons = loader.loadWeapons();
        assertNotNull(weapons);
        assertEquals(9, weapons.size());
        assertEquals("Bokken", weapons.get(0).getName());
        assertEquals(1, weapons.get(0).getAttackPoints());
        assertEquals(1, weapons.get(0).getDifficulty());
    }
}
