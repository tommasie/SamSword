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
 * This class model the Jujitsu cards,
 * where all players expect the owner have to discard
 * either a weapon resitance point
 * @author tommasie
 */
public class Jujitsu extends DeckCard {

    public Jujitsu() {
        super("Jujitsu");
    }

    @Override
    protected void playInternal() {
        for(Player p : GAME.getPlayers()) {
            if(!p.equals(owner)) {
                //TODO choose between life or weapon
            }
        }
    }

    @Override
    protected void playInternal(Player player) { }
    
}
