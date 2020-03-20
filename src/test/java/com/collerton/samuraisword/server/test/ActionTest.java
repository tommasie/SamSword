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

import com.collerton.samuraisword.server.model.actions.Daimyo;
import com.collerton.samuraisword.server.model.actions.Distraction;
import com.collerton.samuraisword.server.model.actions.Geisha;
import com.collerton.samuraisword.server.model.actions.Meditation;
import com.collerton.samuraisword.server.model.DeckCard;
import com.collerton.samuraisword.server.model.GameSingleton;
import com.collerton.samuraisword.server.model.Player;
import com.collerton.samuraisword.server.model.Weapon;
import com.collerton.samuraisword.server.model.actions.TeaCeremony;
import com.collerton.samuraisword.server.model.characters.Benkei;
import com.collerton.samuraisword.server.model.characters.GameCharacter;
import com.collerton.samuraisword.server.model.properties.Armour;
import com.collerton.samuraisword.server.model.properties.Property;
import jdk.nashorn.internal.ir.annotations.Ignore;
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

    private static GameSingleton GAME;

    public ActionTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        GAME = GameSingleton.getInstance();
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        GAME.initDeck();
    }

    @AfterEach
    public void tearDown() {
        GAME.clearAll();
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
    public void testMeditation() {
        Player player = new Player("name");
        Player receiver = new Player("receiver");
        GameCharacter benkei = new Benkei();
        player.setCharacter(benkei);
        DeckCard meditation = new Meditation();
        meditation.setOwner(player);
        meditation.play(receiver);
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
        Player p1 = new Player("p1");
        GAME.addPlayer(p1);
        Player p2 = new Player("p2");
        GAME.addPlayer(p2);
        Player p3 = new Player("p3");
        GAME.addPlayer(p3);
        DeckCard teaCeremony = new TeaCeremony();
        teaCeremony.setOwner(p1);
        teaCeremony.play();
        assertEquals(3, p1.getCards().size());
        assertEquals(1, p2.getCards().size());
        assertEquals(1, p3.getCards().size());
    }

    @Ignore
    @Test
    public void testGeisha() {
        Player player = new Player("player");
        Player victim = new Player("victim");
        Property armour = new Armour();
        victim.giveCard(armour);
        victim.playProperty(armour);

        DeckCard geisha = new Geisha();
        geisha.setOwner(player);
        geisha.play(victim);

        assertTrue(true);
    }
}
