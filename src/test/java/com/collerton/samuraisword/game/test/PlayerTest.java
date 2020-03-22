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
package com.collerton.samuraisword.game.test;

import com.collerton.samuraisword.game.model.DeckCard;
import com.collerton.samuraisword.game.model.Player;
import com.collerton.samuraisword.game.model.Weapon;
import com.collerton.samuraisword.game.model.properties.Armour;
import com.collerton.samuraisword.game.model.properties.Property;
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
public class PlayerTest {

    public PlayerTest() {
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
    public void testCardsInHand() {
        Player player = new Player("name");
        DeckCard card = new Weapon("bokken", 1, 1);
        player.giveCard(card);
        assertEquals(1, player.getCards().size());
    }

    @Test
    public void testPlayProperty() {
        Player player = new Player("name");
        Property armour = new Armour();

        player.giveCard(armour);
        assertEquals(1, player.getCards().size());
        assertEquals(0, player.getPlayedProperties().keySet().size());
        assertEquals(0, player.getDistanceBonus());
        player.playProperty(armour);
        assertEquals(0, player.getCards().size());
        assertEquals(1, player.getPlayedProperties().get("Armour").size());
        assertEquals(1, player.getDistanceBonus());
    }

    @Test
    public void testDiscardProperty() {
        Player player = new Player("name");
        Property armour = new Armour();
        player.giveCard(armour);
        player.playProperty(armour);
        player.discardProperty(armour);
        assertEquals(0, player.getCards().size());
        assertEquals(0, player.getPlayedProperties().get("Armour").size());
        assertEquals(0, player.getDistanceBonus());
    }
}
