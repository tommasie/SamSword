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
package com.collerton.samuraisword.server.model.properties;

import com.collerton.samuraisword.server.model.DeckCard;
import com.collerton.samuraisword.server.model.Player;

/**
 *
 * @author tommasie
 */
public abstract class Property extends DeckCard {
    
    private String description;
    
    public Property(String name, String description) {
        super(name);
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }

    @Override
    protected void playInternal() {
        increasePlayerAttributes();
    }

    @Override
    protected void playInternal(Player player) { }

    protected abstract void increasePlayerAttributes();
    
    public abstract void decreasePlayerAttributes();
    
}
