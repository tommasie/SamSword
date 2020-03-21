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

import com.collerton.samuraisword.game.model.Player;
import com.collerton.samuraisword.game.model.properties.Armour;
import com.collerton.samuraisword.game.model.properties.Concentration;
import com.collerton.samuraisword.game.model.properties.Property;
import com.collerton.samuraisword.game.model.properties.QuickExtraction;
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
    public void testArmourEffects() {
        Player player = new Player("name");
        Property armour = new Armour();
        armour.setOwner(player);
        armour.play();
        assertEquals(1, player.getDistanceBonus());
        assertEquals(0, player.getAttackBonus());
        assertEquals(1, player.getWeaponMultiplier());
    }

    @Test
    public void testArmourOnTable() {
        Player player = new Player("name");
        Property armour = new Armour();
        player.giveCard(armour);
        player.playProperty(armour);
        assertEquals(1, player.getPlayedProperties().get(armour.getName()).size());
    }

    @Test
    public void testConcentrationEffects() {
        Player player = new Player("name");
        Property concentration = new Concentration();
        concentration.setOwner(player);
        concentration.play();
        assertEquals(0, player.getDistanceBonus());
        assertEquals(0, player.getAttackBonus());
        assertEquals(2, player.getWeaponMultiplier());
    }

    @Test
    public void testConcentrationOnTable() {
        Player player = new Player("name");
        Property concentration = new Concentration();
        player.giveCard(concentration);
        player.playProperty(concentration);
        assertEquals(1, player.getPlayedProperties().get(concentration.getName()).size());
    }

    @Test
    public void testQuickExtractionEffects() {
        Player player = new Player("name");
        Property quickExtraction = new QuickExtraction();
        quickExtraction.setOwner(player);
        quickExtraction.play();
        assertEquals(0, player.getDistanceBonus());
        assertEquals(1, player.getAttackBonus());
        assertEquals(1, player.getWeaponMultiplier());
    }

    @Test
    public void testQuickExtractionOnTable() {
        Player player = new Player("name");
        Property quickExtraction = new QuickExtraction();
        player.giveCard(quickExtraction);
        player.playProperty(quickExtraction);
        assertEquals(1, player.getPlayedProperties().get(quickExtraction.getName()).size());
    }
}
