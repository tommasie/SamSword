/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collerton.samuraisword.server.test;

import com.collerton.samuraisword.server.model.GameSingleton;
import com.collerton.samuraisword.server.model.Player;
import com.collerton.samuraisword.server.model.Role;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author tommasie
 */
public class GameSingletonTest {

    private static GameSingleton GAME;

    public GameSingletonTest() {
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
    }

    @AfterEach
    public void tearDown() {
        GAME.removePlayers();
    }

    /**
     * Check that the game starts from the Shogun
     */
    @Test
    public void testShogunStarts() {
        Player p1 = new Player("p1");
        Role r1 = new Role("Samurai");
        p1.setRole(r1);
        GAME.addPlayer(p1);
        Player p2 = new Player("p2");
        Role r2 = new Role("Ninja");
        p2.setRole(r2);
        GAME.addPlayer(p2);
        Player p3 = new Player("p3");
        Role r3 = new Role("Shogun");
        p3.setRole(r3);
        GAME.addPlayer(p3);
        Player p4 = new Player("p4");
        Role r4 = new Role("Ninja");
        p4.setRole(r4);
        GAME.addPlayer(p4);

        GAME.initRound();
        Player player = GAME.getCurrentPlayer();
        assertEquals("Shogun", player.getRole().getName());
    }

    @Test
    public void testLoopingRound() {
        Player p1 = new Player("p1");
        Role r1 = new Role("Samurai");
        p1.setRole(r1);
        GAME.addPlayer(p1);
        Player p2 = new Player("p2");
        Role r2 = new Role("Ninja");
        p2.setRole(r2);
        GAME.addPlayer(p2);
        Player p3 = new Player("p3");
        Role r3 = new Role("Shogun");
        p3.setRole(r3);
        GAME.addPlayer(p3);
        Player p4 = new Player("p4");
        Role r4 = new Role("Ninja");
        p4.setRole(r4);
        GAME.addPlayer(p4);

        GAME.initRound();
        Player player = GAME.getCurrentPlayer();
        assertEquals("p3", player.getName());
        GAME.nextRound();
        player = GAME.getCurrentPlayer();
        assertNotEquals("p3", player.getName());
        GAME.nextRound();
        player = GAME.getCurrentPlayer();
        assertNotEquals("p3", player.getName());
        GAME.nextRound();
        player = GAME.getCurrentPlayer();
        assertNotEquals("p3", player.getName());
        GAME.nextRound();
        player = GAME.getCurrentPlayer();
        assertEquals("p3", player.getName());
    }
}
