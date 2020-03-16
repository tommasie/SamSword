/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collerton.samuraisword.server.test;

import com.collerton.samuraisword.server.model.DeckCard;
import com.collerton.samuraisword.server.model.Player;
import com.collerton.samuraisword.server.model.Weapon;
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
