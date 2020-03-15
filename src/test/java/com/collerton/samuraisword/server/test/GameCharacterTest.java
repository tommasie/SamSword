/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collerton.samuraisword.server.test;

import com.collerton.samuraisword.server.model.GameCharacter;
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
 * @author thomas
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
        System.out.println("entro");
    }
    
    @AfterEach
    public void tearDown() {
        character = null;
    }


    @Test
    @DisplayName("╯°□°）╯")
    public void testBenkei() {
        player = new Player("name");
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
        player = new Player("name");
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
        player = new Player("name");
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
        player = new Player("name");
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
        player = new Player("name");
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
        player = new Player("name");
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
        player = new Player("name");
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
        player = new Player("name");
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
