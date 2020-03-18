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

import com.collerton.samuraisword.server.actions.Daimyo;
import com.collerton.samuraisword.server.actions.Distraction;
import com.collerton.samuraisword.server.actions.Geisha;
import com.collerton.samuraisword.server.actions.Respiration;
import com.collerton.samuraisword.server.model.DeckCard;
import com.collerton.samuraisword.server.model.Player;
import com.collerton.samuraisword.server.model.Weapon;
import com.collerton.samuraisword.server.model.characters.Benkei;
import com.collerton.samuraisword.server.model.characters.GameCharacter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Unit test class for the Action cards
 * @author tommasie
 */
public class ActionTest {
    
    public ActionTest() {
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
    public void testDaimyo() {
        Player player = new Player("name");
        DeckCard daimyo = new Daimyo();
        daimyo.setOwner(player);
        assertEquals(0, player.getCards().size());
        daimyo.play();
        assertEquals(2, player.getCards().size());
    }
    
    @Test
    public void testRespiration() {
        Player player = new Player("name");
        Player receiver = new Player("receiver");
        GameCharacter benkei = new Benkei();
        player.setCharacter(benkei);
        DeckCard respiration = new Respiration();
        respiration.setOwner(player);
        respiration.play(receiver);
        assertEquals(5, player.getResistancePoints());
        assertEquals(1, receiver.getCards().size());
    }
    
    @Test
    public void testDistraction() {
        Player player = new Player("name");
        Player victim = new Player("victim");
        Weapon bokken = new Weapon("Bokken", 1, 1);
        victim.giveCard(bokken);
        DeckCard distraction = new Distraction();
        distraction.setOwner(player);
        distraction.play(victim);
        assertEquals(1, player.getCards().size());
        assertEquals("Bokken", player.getCards().get(0).getName());
        assertEquals(0, victim.getCards().size());
    }
    
    @Test
    public void testTeaCeremony() {
        Player player = new Player("name");
        assertTrue(true);
    }
    
    @Test
    public void testGeisha() {
        Player player = new Player("player");
        Player victim = new Player("victim");
        DeckCard geisha = new Geisha();
        geisha.setOwner(player);
        geisha.play(victim);
        
        assertTrue(true);
    }
}
