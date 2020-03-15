/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collerton.samuraisword.server.model;

import java.util.Objects;

/**
 *
 * @author thomas
 */
public class Role {
    
    public static enum Class {
        Ronin,
        Ninja,
        Shogun
    }
    
    private String name;
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
