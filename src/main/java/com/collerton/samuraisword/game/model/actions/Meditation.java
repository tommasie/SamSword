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

/**
 * This class model the Respiration card, where the owner resets its
 * resistance points and lets another player pick a card
 * @author tommasie
 */
public class Meditation extends DeckCard {

    public Meditation() {
        super("Respiration");
    }

    public void play(Player player, Player beneficiario) {
        player.resetResistancePoints();
        beneficiario.giveCard(GAME.pickCardFromDeck());
    }

    @Override
    protected void playInternal() { }

    @Override
    protected void playInternal(Player player) {
        owner.resetResistancePoints();
        player.giveCard(GAME.pickCardFromDeck());
    }

}
