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

import com.collerton.samuraisword.server.model.characters.GameCharacter;
import com.collerton.samuraisword.server.model.Player;
import com.collerton.samuraisword.server.model.characters.Benkei;
import com.collerton.samuraisword.server.model.characters.Chiyome;
import com.collerton.samuraisword.server.model.characters.Ginchiyo;
import com.collerton.samuraisword.server.model.characters.Goemon;
import com.collerton.samuraisword.server.model.characters.Hanzo;
import com.collerton.samuraisword.server.model.characters.Hideyoshi;
import com.collerton.samuraisword.server.model.characters.Ieyasu;
import com.collerton.samuraisword.server.model.characters.Kojiro;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

/**
 *
 * @author tommasie
 */
public class GameCharacterTest {

    Player player;
    GameCharacter character;

    public GameCharacterTest() {
    }

    @BeforeAll
    public static void setUpClass() {

    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        player = new Player("name");
    }

    @AfterEach
    public void tearDown() {
        character = null;
    }


    @Test
    @DisplayName("╯°□°）╯")
    public void testBenkei() {
        character = new Benkei();
        player.setCharacter(character);
        assertEquals(1, player.getDistanceBonus());
        assertEquals(0, player.getAttackBonus());
        assertEquals(0, player.getHonorPoints());
        assertEquals(1, player.getWeaponMultiplier());
        assertFalse(player.getSuffersLessDamage());
        assertFalse(player.canParryWithWeapon());
        assertFalse(player.canPickExtraCard());
        assertFalse(player.canPickFromCemetery());
        assertFalse(player.ignoresDifficulty());
        assertEquals(5, player.getResistancePoints());
    }

    @Test
    @DisplayName("╯°□°）╯")
    public void testChiyome() {
        character = new Chiyome();
        player.setCharacter(character);
        assertEquals(0, player.getDistanceBonus());
        assertEquals(0, player.getAttackBonus());
        assertEquals(0, player.getHonorPoints());
        assertEquals(1, player.getWeaponMultiplier());
        assertFalse(player.getSuffersLessDamage());
        assertFalse(player.canParryWithWeapon());
        assertFalse(player.canPickExtraCard());
        assertFalse(player.canPickFromCemetery());
        assertFalse(player.ignoresDifficulty());
        assertEquals(4, player.getResistancePoints());
    }

    @Test
    @DisplayName("╯°□°）╯")
    public void testGoemon() {
        character = new Goemon();
        player.setCharacter(character);
        assertEquals(0, player.getDistanceBonus());
        assertEquals(0, player.getAttackBonus());
        assertEquals(0, player.getHonorPoints());
        assertEquals(2, player.getWeaponMultiplier());
        assertFalse(player.getSuffersLessDamage());
        assertFalse(player.canParryWithWeapon());
        assertFalse(player.canPickExtraCard());
        assertFalse(player.canPickFromCemetery());
        assertFalse(player.ignoresDifficulty());
        assertEquals(5, player.getResistancePoints());
    }

    @Test
    @DisplayName("╯°□°）╯")
    public void testGinchiyo() {
        character = new Ginchiyo();
        player.setCharacter(character);
        assertEquals(0, player.getDistanceBonus());
        assertEquals(0, player.getAttackBonus());
        assertEquals(0, player.getHonorPoints());
        assertEquals(1, player.getWeaponMultiplier());
        assertTrue(player.getSuffersLessDamage());
        assertFalse(player.canParryWithWeapon());
        assertFalse(player.canPickExtraCard());
        assertFalse(player.canPickFromCemetery());
        assertFalse(player.ignoresDifficulty());
        assertEquals(4, player.getResistancePoints());
    }

    @Test
    @DisplayName("╯°□°）╯")
    public void testHanzo() {
        character = new Hanzo();
        player.setCharacter(character);
        assertEquals(0, player.getDistanceBonus());
        assertEquals(0, player.getAttackBonus());
        assertEquals(0, player.getHonorPoints());
        assertEquals(1, player.getWeaponMultiplier());
        assertFalse(player.getSuffersLessDamage());
        assertTrue(player.canParryWithWeapon());
        assertFalse(player.canPickExtraCard());
        assertFalse(player.canPickFromCemetery());
        assertFalse(player.ignoresDifficulty());
        assertEquals(4, player.getResistancePoints());
    }

    @Test
    @DisplayName("╯°□°）╯")
    public void testHideyoshi() {
        character = new Hideyoshi();
        player.setCharacter(character);
        assertEquals(0, player.getDistanceBonus());
        assertEquals(0, player.getAttackBonus());
        assertEquals(0, player.getHonorPoints());
        assertEquals(1, player.getWeaponMultiplier());
        assertFalse(player.getSuffersLessDamage());
        assertFalse(player.canParryWithWeapon());
        assertTrue(player.canPickExtraCard());
        assertFalse(player.canPickFromCemetery());
        assertFalse(player.ignoresDifficulty());
        assertEquals(4, player.getResistancePoints());
    }

    @Test
    @DisplayName("╯°□°）╯")
    public void testIeyasu() {
        character = new Ieyasu();
        player.setCharacter(character);
        assertEquals(0, player.getDistanceBonus());
        assertEquals(0, player.getAttackBonus());
        assertEquals(0, player.getHonorPoints());
        assertEquals(1, player.getWeaponMultiplier());
        assertFalse(player.getSuffersLessDamage());
        assertFalse(player.canParryWithWeapon());
        assertFalse(player.canPickExtraCard());
        assertTrue(player.canPickFromCemetery());
        assertFalse(player.ignoresDifficulty());
        assertEquals(5, player.getResistancePoints());
    }

    @Test
    @DisplayName("╯°□°）╯")
    public void testKojirō() {
        character = new Kojiro();
        player.setCharacter(character);
        assertEquals(0, player.getDistanceBonus());
        assertEquals(0, player.getAttackBonus());
        assertEquals(0, player.getHonorPoints());
        assertEquals(1, player.getWeaponMultiplier());
        assertFalse(player.getSuffersLessDamage());
        assertFalse(player.canParryWithWeapon());
        assertFalse(player.canPickExtraCard());
        assertFalse(player.canPickFromCemetery());
        assertTrue(player.ignoresDifficulty());
        assertEquals(5, player.getResistancePoints());
    }

    //TODO finish characters (musachi, nobunaga, tomoe, ushiwaka)
}
