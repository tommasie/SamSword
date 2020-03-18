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

import com.collerton.samuraisword.server.config.ConfigFactory;
import com.collerton.samuraisword.server.model.Role;
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
