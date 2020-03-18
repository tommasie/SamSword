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
package com.collerton.samuraisword.server.model;

import java.util.Objects;

/**
 * This class models the Role card given at the beginning of the game.
 * @author tommasie
 */
public class Role {
    
    public static enum Class {
        Ronin,
        Ninja,
        Shogun
    }
    
    private String name;
    //TODO handle teams
    //private Class team;
    private int honorMultiplier;
    
    public Role() {
        this.name = "";
        this.honorMultiplier = 0;
    }
    public Role(String s) {
        this();
    }

    public Role(String name, int honorMultiplier) {
        this.name = name;
        this.honorMultiplier = honorMultiplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHonorMultiplier() {
        return honorMultiplier;
    }

    public void setHonorMultiplier(int honorMultiplier) {
        this.honorMultiplier = honorMultiplier;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.name);
        hash = 47 * hash + this.honorMultiplier;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Role other = (Role) obj;
        if (this.honorMultiplier != other.honorMultiplier) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Role{" + "name=" + name + ", honorMultiplier=" + honorMultiplier + '}';
    }
    
    
    
}
