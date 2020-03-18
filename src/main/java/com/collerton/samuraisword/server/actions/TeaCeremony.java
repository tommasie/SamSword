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
package com.collerton.samuraisword.server.actions;

import com.collerton.samuraisword.server.model.DeckCard;
import com.collerton.samuraisword.server.model.Player;

/**
 * This class model the Tea Ceremony card,
 * where the owner picks three cards
 * and the others just one
 * @author tommasie
 */
public class TeaCeremony extends DeckCard {

    public TeaCeremony() {
        super("TeaCeremony");
    }

    @Override
    protected void playInternal() {
        for(int i = 0; i < 3; i++) {
            owner.giveCard(GAME.pickCardFromDeck());
        }
        
        for(Player p : GAME.getPlayers()) {
            if (!p.equals(owner)) {
                p.giveCard(GAME.pickCardFromDeck());
            }
        }
    }

    @Override
    protected void playInternal(Player player) { }
}
