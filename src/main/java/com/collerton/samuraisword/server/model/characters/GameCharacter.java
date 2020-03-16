/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collerton.samuraisword.server.model.characters;

import com.collerton.samuraisword.server.model.Player;

/**
 *
 * @author thomas
 */
public abstract class GameCharacter {
    
    private String name;
    private int resistancePoints;

    public GameCharacter(String name, int resistancePoints) {
        this.name = name;
        this.resistancePoints = resistancePoints;
    }

    public int getResistancePoints() {
        return resistancePoints;
    }
    
    public abstract void play(Player player);
            
}
