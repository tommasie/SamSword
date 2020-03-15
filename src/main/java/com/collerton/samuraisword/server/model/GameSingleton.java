/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collerton.samuraisword.server.model;

import com.collerton.samuraisword.server.config.ConfigFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author thomas
 */
public class GameSingleton {
    
    private static GameSingleton instance;
    
    private int numPlayers;
    
    private List<Player> players;
    
    private Stack<DeckCard> deck;
    
    private Stack<DeckCard> graveyard;
    
    private GameSingleton() {
        this.numPlayers = 0;
        this.players = new ArrayList<>();
        this.deck = new Stack<>();
        this.graveyard = new Stack<>();
    }
    
    public static synchronized GameSingleton getInstance() {
        if(instance == null)
            instance = new GameSingleton();
        return instance;
    }
    
    public void newGame(int players) {
        this.numPlayers = players;
        
    }
    
    //To be called when user logs in
    public void addPlayer(Player player) {
        this.players.add(player);
    }
    
    private void giveRoles() {
        ConfigFactory factory = ConfigFactory.getInstance(4);
        List<Role> roles = factory.getRoles();
        Collections.shuffle(roles);

        for(Player player : players) {
            player.chooseRole(roles);
        }
    }
    
    private void giveCharacters() {
        List<GameCharacter> characters = new ArrayList<>();
        
        Collections.shuffle(characters);
        
        for(Player player : players) {
            player.chooseCharacter(characters);
        }
    }
    
    public void endGame() {
        StringBuilder sb = new StringBuilder();
        for(Player p : players) {
            sb.append("Player ").append(p.getName())
                    .append(" is a ").append(p.getRole().getName())
                    .append(" and has collected a total of ")
                    .append(p.getHonorPoints() * p.getRole().getHonorMultiplier())
                    .append(" honor points");
            System.out.println(sb.toString());
            sb.setLength(0);
        }
    }
    
    
    
}
