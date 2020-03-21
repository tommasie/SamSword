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
 * This class models any Weapon card, as they don't have specific traits
 *
 * @author tommasie
 */
public class Weapon extends DeckCard {

    private int attackPoints;
    private int difficulty;

     public Weapon(String name, int attackPoints, int difficulty) {
        super(name);
        this.attackPoints = attackPoints;
        this.difficulty = difficulty;
    }

    public Weapon() {
        this("", 0, 0);
    }

    // For the Yaml parser
    public Weapon(String s) {
        this("", 0, 0);
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    protected void playInternal() { }

    @Override
    protected void playInternal(Player player) {
        //TODO how to handle weapons?
    }


}
