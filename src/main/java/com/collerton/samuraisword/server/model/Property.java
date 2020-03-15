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
public abstract class Property extends DeckCard{
    
    public Property(String name) {
        super(name);
    }
    
    public void play(Player player)
    {
        increasePlayerAttributes(player);
        moveCardToTable(player);
    }
    
    protected abstract void increasePlayerAttributes(Player player);
    
    protected abstract void decreasePlayerAttributes(Player player);
    
    private void moveCardToTable(Player player) {
        player.moveCardToTable(this);
    }
    
}
