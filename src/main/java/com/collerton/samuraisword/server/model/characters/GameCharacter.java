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
package com.collerton.samuraisword.server.model.characters;

import com.collerton.samuraisword.server.model.Player;

/**
 *
 * @author tommasie
 */
public abstract class GameCharacter {
    
    private String name;
    private int resistancePoints;
    protected Player owner;

    public GameCharacter(String name, int resistancePoints) {
        this.name = name;
        this.resistancePoints = resistancePoints;
        owner = null;
    }

    public int getResistancePoints() {
        return resistancePoints;
    }
    
    public void setOwner(Player player) {
        this.owner = player;
    }
    
    public abstract void play();
            
}
