/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collerton.samuraisword.server.model;

import com.collerton.samuraisword.server.model.characters.GameCharacter;
import com.collerton.samuraisword.server.config.ConfigFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

/**
 *
 * @author thomas
 */
public class GameSingleton {
    
    private static GameSingleton instance;
    
    private int numPlayers;
    
    private List<Player> players;
    
    private Stack<DeckCard> deck;
    
    private Stack<DeckCard> cemetery;
    
    private GameSingleton() {
        this.numPlayers = 0;
        this.players = new ArrayList<>();
        this.deck = new Stack<>();
        this.cemetery = new Stack<>();
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
    
    public List<Player> getPlayers() {
        return this.players;
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
    
    public DeckCard pickCardFromDeck() {
        if(deck.isEmpty()) {
            resetDeck();
        }
        return deck.pop();     
    }
    
    private void resetDeck() {
        deck.addAll(cemetery);
        Collections.shuffle(deck);
        cemetery.clear();
        
        // All players must give 1 honor point
        for (Player p : players) {
            p.decreseHonorPoints();
        }
    }
    
    public void addCardToCemetery(DeckCard card) {
        this.cemetery.push(card);
    }
    
    public DeckCard pickTopOfCemetery() {
        if(!this.cemetery.isEmpty()) {
            return this.cemetery.pop();
        }
        return null;
    }
    
    public DeckCard checkTopOfCemetery() {
        if(this.cemetery.isEmpty())
            return this.cemetery.peek();
        return null;
    }
    
    public void endGame() {
        StringBuilder sb = new StringBuilder();
        for(Player p : players) {
            // Check if player has the Daimyo
            for (DeckCard card : p.getCards()) {
                if(card.getName().equals("Daimyo")) {
                    p.increaseHonorPoints();
                }
            }
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
