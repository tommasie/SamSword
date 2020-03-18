/*
 * Copyright (C) 2020 tommasie
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.collerton.samuraisword.server.model;

import com.collerton.samuraisword.server.model.characters.GameCharacter;
import com.collerton.samuraisword.server.config.ConfigFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * This class is the main game class that handles the game execution,
 * as well as game stuff like the card deck and card cemetery.
 * 
 * @author tommasie
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
