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

import com.collerton.samuraisword.game.config.YamlLoader;
import com.collerton.samuraisword.game.model.DeckCard;
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
public class YamlLoaderTest {

    private YamlLoader cardsLoader;

    public YamlLoaderTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        cardsLoader = new YamlLoader();
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testLoadConcreteActions() {
        List<DeckCard> cards = cardsLoader.getConcreteActions();
        assertEquals(43, cards.size());
        assertTrue(cards.get(0) instanceof DeckCard);
        assertEquals("Battlecry", cards.get(0).getName());

    }

    @Test
    public void testLoadConcreteProperties() {
        List<DeckCard> cards = cardsLoader.getConcreteProperties();
        assertEquals(15, cards.size());
        assertTrue(cards.get(0) instanceof DeckCard);
        assertEquals("Armour", cards.get(0).getName());
    }

    @Test
    public void testLoadConcreteWeapons() {
        List<DeckCard> cards = cardsLoader.getConcreteWeapons();
        assertEquals(33, cards.size());
        assertTrue(cards.get(0) instanceof DeckCard);
        assertEquals("Bō", cards.get(0).getName());
    }
}
