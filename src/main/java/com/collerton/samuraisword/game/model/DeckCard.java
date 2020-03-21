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
package com.collerton.samuraisword.game.model;

/**
 * Abstract parent class for all the cards that can be taken from the deck
 * (weapons, actions, properties)
 *
 * @author tommasie
 */
public abstract class DeckCard {

    protected static final GameSingleton GAME = GameSingleton.getInstance();

    private String name;
    protected Player owner;

    public DeckCard(String name) {
        this.name = name;
        this.owner = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(Player player) {
        this.owner = player;
    }

    /**
     * Called by cards that have effects on zero or all other players
     */
    public void play() {
        if(owner != null) {
            playInternal();
            discard();
        } else {
            //throw new Exception("Card should have an owner before it's played");
            System.out.println("Card should have an owner before it's played");
        }
    }

    protected abstract void playInternal();

    /**
     * Called by cards that have effect on one player
     * @param player Player who is affected by this card
     */
    public void play(Player player) {
        if(owner != null) {
            playInternal(player);
            discard();
        } else {
            //throw new Exception("Card should have an owner before it's played");
            System.out.println("Card should have an owner before it's played");
        }
    }

    protected abstract void playInternal(Player player);

    private void discard() {
        owner.discardCard(this);
    }

}
