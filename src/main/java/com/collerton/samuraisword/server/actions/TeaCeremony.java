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
public class TeaCeremony {
    
    private static GameSingleton game = GameSingleton.getInstance();
    
    public void play(Player player) {
        for(int i = 0; i < 3; i++) {
            player.giveCard(game.pickCardFromDeck());
        }
        
        for(Player p : game.getPlayers()) {
            player.giveCard(game.pickCardFromDeck());
        }
    }
}
