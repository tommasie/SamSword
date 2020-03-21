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
package com.collerton.samuraisword.game.list;

import com.collerton.samuraisword.game.model.Player;

/**
 * Node of the circular double-linked list of players (and Bushido)
 * @author tommasie
 */
public class PlayerListNode {

    private final Player player;
    private PlayerListNode next;
    private PlayerListNode previous;

    public PlayerListNode(Player player) {
        this.player = player;
        this.next = null;
        this.previous = null;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerListNode getNext() {
        return next;
    }

    public void setNext(PlayerListNode nextPlayer) {
        this.next = nextPlayer;
    }

    public PlayerListNode getPrevious() {
        return previous;
    }

    public void setPrevious(PlayerListNode previousPlayer) {
        this.previous = previousPlayer;
    }

}
