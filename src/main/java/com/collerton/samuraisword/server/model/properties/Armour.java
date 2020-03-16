/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collerton.samuraisword.server.model.properties;

import com.collerton.samuraisword.server.model.Player;

/**
 *
 * @author thomas
 */
public class Armour extends Property{

    public Armour() {
        super("Armour", "description");
    }

    @Override
    protected void increasePlayerAttributes(Player player) {
        player.increaseDistanceBonus();
    }

    @Override
    public void decreasePlayerAttributes(Player player) {
        player.decreaseDistanceBonus();
    }
    
}
