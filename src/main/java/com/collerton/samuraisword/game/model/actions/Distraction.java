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
package com.collerton.samuraisword.game.model.actions;

import com.collerton.samuraisword.game.model.DeckCard;
import com.collerton.samuraisword.game.model.Player;
import java.util.Random;

/**
 * This class model the Distraction card, where the owner can steal
 * one card from another player's hand
 * @author tommasie
 */
public class Distraction extends DeckCard {

    public Distraction() {
        super("Distraction");
    }

    @Override
    protected void playInternal() { }

    @Override
    protected void playInternal(Player player) {
        Random rn = new Random();
        int index = rn.nextInt(player.getCards().size());
        owner.giveCard(player.discardCard(index));
    }

}
