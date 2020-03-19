/*
 * Copyright (C) 2020 tommasie
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.collerton.samuraisword.server.test;

import com.collerton.samuraisword.server.config.WeaponLoader;
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
 * @author tommasie
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
        assertEquals(33, weapons.size());
        assertEquals("Bō", weapons.get(0).getName());
        assertEquals(1, weapons.get(0).getAttackPoints());
        assertEquals(2, weapons.get(0).getDifficulty());
    }
}
