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
public class Hideyoshi extends GameCharacter{

    public Hideyoshi() {
        super("Hideyoshi", 4);
    }

    @Override
    public void play(Player player) {
        player.setCanPickExtraCard();
    }
    
}
