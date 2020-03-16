/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collerton.samuraisword.server.model.properties;

import com.collerton.samuraisword.server.model.DeckCard;
import com.collerton.samuraisword.server.model.Player;

/**
 *
 * @author thomas
 */
public abstract class Property extends DeckCard{
    
    private String description;
    
    public Property(String name, String description) {
        super(name);
        this.description = description;
    }
    
    public void play(Player player)
    {
        moveCardToTable(player);
        increasePlayerAttributes(player);
    }

    public String getDescription() {
        return description;
    }
    
    protected abstract void increasePlayerAttributes(Player player);
    
    public abstract void decreasePlayerAttributes(Player player);
    
    private void moveCardToTable(Player player) {
        player.movePropertyToTable(this);
    }
    
}
