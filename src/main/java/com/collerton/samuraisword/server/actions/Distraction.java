/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collerton.samuraisword.server.actions;

import com.collerton.samuraisword.server.model.Player;
import java.util.Random;

/**
 *
 * @author thomas
 */
public class Distraction {
    
    public void play(Player ladro, Player vittima, int index) {
        //vittima.getCards().size();
        ladro.giveCard(vittima.discardCard(index));
    }
    
    public void play(Player ladro, Player vittima) {
        //vittima.getCards().size();
        Random rn = new Random();
        int index = rn.nextInt(6);
        ladro.giveCard(vittima.discardCard(index));
    }
    
}
