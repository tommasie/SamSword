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
package com.collerton.samuraisword.server.model.actions;

import com.collerton.samuraisword.server.model.DeckCard;
import com.collerton.samuraisword.server.model.Player;

/**
 * This class models the Daimyo card, which gives the player two extra cards
 * if player plays it or gives him an extra honor point if stil in its hands
 *
 * @author tommasie
 */
public class Daimyo extends DeckCard{
    
    public Daimyo() {
        super("Daimyo");
    }
    
    @Override
    public void playInternal() {
        owner.giveCard(GAME.pickCardFromDeck());
        owner.giveCard(GAME.pickCardFromDeck());
    }

    @Override
    protected void playInternal(Player player) { }
    
}
