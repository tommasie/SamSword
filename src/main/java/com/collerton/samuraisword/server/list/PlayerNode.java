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
package com.collerton.samuraisword.server.list;

import com.collerton.samuraisword.server.model.Player;

/**
 * Node of the circular linked list of players (and Bushido)
 * @author tommasie
 */
public class PlayerNode {
    
    private Player mPlayer;
    private Player mNextPlayer;
    private Player mPreviousPlayer;

    public PlayerNode(Player mPlayer, Player mNextPlayer, Player mPreviousPlayer) {
        this.mPlayer = mPlayer;
        this.mNextPlayer = mNextPlayer;
        this.mPreviousPlayer = mPreviousPlayer;
    }

    public Player getPlayer() {
        return mPlayer;
    }

    public Player getNextPlayer() {
        return mNextPlayer;
    }

    public Player getmPreviousPlayer() {
        return mPreviousPlayer;
    }

}
