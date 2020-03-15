/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collerton.samuraisword.server.model.characters;

import com.collerton.samuraisword.server.model.GameCharacter;
import com.collerton.samuraisword.server.model.Player;

/**
 *
 * @author thomas
 */
public class Goemon extends GameCharacter{

    public Goemon() {
        super("Goemon", 5);
    }

    @Override
    public void play(Player player) {
        player.increaseWeaponMultiplier();
    }
    
}
