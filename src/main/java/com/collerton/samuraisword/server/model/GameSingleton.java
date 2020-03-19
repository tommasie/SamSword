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
import com.collerton.samuraisword.server.config.YamlLoader;
import com.collerton.samuraisword.server.list.PlayerNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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
    
    private int mNumberPlayers;
    
    private final List<Player> mPlayers;
    
    private PlayerNode mPlayersRoundHead;
    
    private final Stack<DeckCard> mDeck;
    
    private final Stack<DeckCard> mCemetery;
    
    private GameSingleton() {
        this.mNumberPlayers = 0;
        this.mPlayers = new ArrayList<>();
        this.mPlayersRoundHead = null;
        this.mDeck = new Stack<>();
        this.mCemetery = new Stack<>();
    }
    
    public static synchronized GameSingleton getInstance() {
        if(instance == null)
            instance = new GameSingleton();
        return instance;
    }
    
    public void newGame(int players) {
        this.mNumberPlayers = players;
        
    }
    
    //To be called when user logs in
    public void addPlayer(Player player) {
        this.mPlayers.add(player);
    }
    
    public List<Player> getPlayers() {
        return this.mPlayers;
    }
    
    public void startGame() {
        giveRoles();
        giveCharacters();
        distributeCards();
    }
    
    private void giveRoles() {
        ConfigFactory factory = ConfigFactory.getInstance(4);
        List<Role> roles = factory.getRoles();
        Collections.shuffle(roles);

        for(Player player : mPlayers) {
            player.chooseRole(roles);
        }
    }
    
    private void giveCharacters() {
        List<GameCharacter> characters = new ArrayList<>();
        Collections.shuffle(characters);
        
        for(Player player : mPlayers) {
            player.chooseCharacter(characters);
        }
    }
    
    private void distributeCards() {
        YamlLoader cardsLoader = new YamlLoader();
        mDeck.addAll(cardsLoader.getConcreteWeapons());
        mDeck.addAll(cardsLoader.getConcreteActions());
        mDeck.addAll(cardsLoader.getConcreteProperties());
        
        Iterator<Player> it;
        it = mPlayers.iterator();
        
        for (Player p : mPlayers) {
            //int cards = 
        }
    }
    /*
    private void setUpRound() {
        // TOD use deque
        int idx = 0, idxPrev = 0, idxNext = 0;
        for(int i = 0; i < mPlayers.size(); i++) {
            if(mPlayers.get(i).getRole().getName().equals("Shogun")) {
                idx = i;
                break;
            }
        }
        if(idx == 0) {
            idxPrev = mPlayers.size() - 1;
            idxNext = idx + 1;
        } else if (idx == mPlayers.size() - 1) {
            idxPrev = idx - 1;
            idxNext = 0;
        } else {
            idxPrev = idx - 1;
            idxNext = idx + 1;
        }
        mPlayersRoundHead = new PlayerNode(mPlayers.get(idx), mPlayers.get(idxNext), mPlayers.get(idxPrev));
        idxPrev = idx;
        idx = idxNext;
        idxNext = (idxNext == mPlayers.size() - 1) ? 0 : idxNext + 1;
    }
    */
    
    public DeckCard pickCardFromDeck() {
        if(mDeck.isEmpty()) {
            resetDeck();
        }
        return mDeck.pop();     
    }
    
    private void resetDeck() {
        mDeck.addAll(mCemetery);
        Collections.shuffle(mDeck);
        mCemetery.clear();
        
        // All players must give 1 honor point
        for (Player p : mPlayers) {
            p.decreseHonorPoints();
        }
    }
    
    public void addCardToCemetery(DeckCard card) {
        this.mCemetery.push(card);
    }
    
    public DeckCard pickTopOfCemetery() {
        if(!this.mCemetery.isEmpty()) {
            return this.mCemetery.pop();
        }
        return null;
    }
    
    public DeckCard checkTopOfCemetery() {
        if(this.mCemetery.isEmpty())
            return this.mCemetery.peek();
        return null;
    }
    
    public void endGame() {
        StringBuilder sb = new StringBuilder();
        for(Player p : mPlayers) {
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
