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

import com.collerton.samuraisword.game.list.PlayersRound;
import com.collerton.samuraisword.game.model.Player;
import com.collerton.samuraisword.game.model.Role;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 *
 * @author tommasie
 */
public class PlayersRoundTest {

    public PlayersRoundTest() {
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
    public void testEmptiness() {
        PlayersRound round = new PlayersRound();
        assertTrue(round.isEmpty());
    }

    @Test
    public void testAddPlayer() {
        PlayersRound round = new PlayersRound();
        Player player = new Player("name");
        round.add(player);
        assertFalse(round.isEmpty());
        assertEquals(1, round.size());
        assertEquals(player, round.get(0));
    }

    @Test
    public void testAddSecondPlayer() {
        PlayersRound round = new PlayersRound();
        Player p1 = new Player("p1");
        round.add(p1);
        Player p2 = new Player("p2");
        round.add(p2);
        assertEquals(2, round.size());
        assertEquals(p2, round.get(1));
    }

    @Test
    public void testAddThirdPlayer() {
        PlayersRound round = new PlayersRound();
        Player p1 = new Player("p1");
        round.add(p1);
        Player p2 = new Player("p2");
        round.add(p2);
        Player p3 = new Player("p3");
        round.add(p3);
        assertEquals(3, round.size());
        assertEquals(p3, round.get(2));
    }

    @Test
    public void testGetIndexOutOfBoundsException() {
        PlayersRound round = new PlayersRound();
        Player p1 = new Player("p1");
        round.add(p1);
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
                round.get(1);
            });
        String expected = "There are less than 2 players";
        String received = exception.getMessage();
        assertEquals(expected, received);
    }

    @Test
    public void testForEachEmpty() {
        PlayersRound round = new PlayersRound();

        int expectedSize = 0;
        for(Player p : round) {
            expectedSize++;
        }
        assertEquals(0, expectedSize);
    }

    @Test
    public void testForEach1Player() {
        PlayersRound round = new PlayersRound();
        Player p1 = new Player("p1");
        round.add(p1);

        int expectedSize = 0;
        for(Player p : round) {
            assertNotNull(p);
            expectedSize++;
            assertEquals("p" + expectedSize, p.getName());
        }
        assertEquals(1, expectedSize);
    }

    @Test
    public void testForEach2Players() {
        PlayersRound round = new PlayersRound();
        Player p1 = new Player("p1");
        round.add(p1);
        Player p2 = new Player("p2");
        round.add(p2);

        int expectedSize = 0;
        for(Player p : round) {
            assertNotNull(p);
            expectedSize++;
            assertEquals("p" + expectedSize, p.getName());
        }
        assertEquals(2, expectedSize);
    }

    /**
     * For 3+ players the data structure has the same linking
     */
    @Test
    public void testForEach3Players() {
        PlayersRound round = new PlayersRound();
        Player p1 = new Player("p1");
        round.add(p1);
        Player p2 = new Player("p2");
        round.add(p2);
        Player p3 = new Player("p3");
        round.add(p3);

        int expectedSize = 0;
        for(Player p : round) {
            assertNotNull(p);
            expectedSize++;
            assertEquals("p" + expectedSize, p.getName());
        }
        assertEquals(3, expectedSize);
    }

    @Test
    public void testCreateFromList() {
        PlayersRound round = new PlayersRound();
        List<Player> list = new ArrayList<>();
        Player p1 = new Player("p1");
        list.add(p1);
        Player p2 = new Player("p2");
        list.add(p2);
        Player p3 = new Player("p3");
        list.add(p3);

        round.addAll(list);
        assertEquals(3, round.size());
        assertEquals(p1, round.get(0));
        assertEquals(p2, round.get(1));
        assertEquals(p3, round.get(2));
    }

    @Test
    public void testContainsPlayer() {
        PlayersRound round = new PlayersRound();
        Player p1 = new Player("p1");
        round.add(p1);

        assertTrue(round.contains(p1));
    }

    @Test
    public void testNotContainsPlayer() {
        PlayersRound round = new PlayersRound();
        Player p1 = new Player("p1");
        round.add(p1);

        Player p2 = new Player("p2");
        assertFalse(round.contains(p2));
    }

    @Test
    public void testNotContainsPlayerEmptyRound() {
        PlayersRound round = new PlayersRound();
        Player p1 = new Player("p1");

        assertFalse(round.contains(p1));
    }

    @Disabled
    @Test
    public void testRoundShuffle() {
        PlayersRound round = new PlayersRound();
        Player p1 = new Player("p1");
        round.add(p1);
        Player p2 = new Player("p2");
        round.add(p2);
        Player p3 = new Player("p3");
        round.add(p3);

        Collections.shuffle(round);

        boolean expected = round.get(0) != p1 || round.get(1) != p2 || round.get(2) != p3;
        assertTrue(expected);
    }

    @Test
    public void testShogunIsFirst() {
        PlayersRound round = new PlayersRound();
        Player p1 = new Player("p1");
        Role r1 = new Role("Samurai");
        p1.setRole(r1);
        round.add(p1);
        Player p2 = new Player("p2");
        Role r2 = new Role("Ninja");
        p2.setRole(r2);
        round.add(p2);
        Player p3 = new Player("p3");
        Role r3 = new Role("Shogun");
        p3.setRole(r3);
        round.add(p3);
        Player p4 = new Player("p4");
        Role r4 = new Role("Ninja");
        p4.setRole(r4);
        round.add(p4);

        round.resetHead();
        assertEquals("Shogun", round.get(0).getRole().getName());
    }

    @Test
    public void testCurrentPlayerChange() {
        PlayersRound round = new PlayersRound();
        Player p1 = new Player("p1");
        round.add(p1);
        Player p2 = new Player("p2");
        round.add(p2);
        Player p3 = new Player("p3");
        round.add(p3);

        assertEquals(p1, round.getCurrentPlayer());
        round.nextRound();
        assertEquals(p2, round.getCurrentPlayer());
        round.nextRound();
        assertEquals(p3, round.getCurrentPlayer());
        round.nextRound();
        assertEquals(p1, round.getCurrentPlayer());
    }

    @Test
    void testPlayersRoundCreation() {

    }
}
