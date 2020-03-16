/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collerton.samuraisword.server.actions;

import com.collerton.samuraisword.server.model.GameSingleton;
import com.collerton.samuraisword.server.model.Player;

/**
 *
 * @author thomas
 */
public class Respiration {
    
    private static final GameSingleton GAME = GameSingleton.getInstance();
    
    public void play(Player player, Player beneficiario) {
        player.resetResistancePoints();
        beneficiario.giveCard(GAME.pickCardFromDeck());
    }
    
}
