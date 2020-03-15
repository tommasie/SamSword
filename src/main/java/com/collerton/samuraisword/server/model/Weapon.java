/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collerton.samuraisword.server.model;

/**
 *
 * @author thomas
 */
public class Weapon extends DeckCard{
    
    private int attackPoints;
    private int difficulty;

    public Weapon(String name, int attackPoints, int difficulty) {
        super(name);
        this.attackPoints = attackPoints;
        this.difficulty = difficulty;
    }
    
    public boolean attack(Player player) {
        return true;
    }

    
}
