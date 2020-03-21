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
import com.collerton.samuraisword.server.list.PlayerListNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * This class is the main game class that handles the game execution,
 * as well as game stuff like the card deck and card cemetery.
 *
 * @author tommasie
 */
public class GameSingleton {

    private static GameSingleton instance;

    private YamlLoader configLoader;

    private int numberPlayers;

    private final Set<Player> players;

    private PlayerListNode currentPlayer;

    private final Stack<DeckCard> deck;

    private final Stack<DeckCard> cemetery;

    private GameSingleton() {
        this.numberPlayers = 0;
        this.players = new HashSet<>();
        this.currentPlayer = null;
        this.deck = new Stack<>();
        this.cemetery = new Stack<>();
    }

    public static synchronized GameSingleton getInstance() {
        if(instance == null)
            instance = new GameSingleton();
        return instance;
    }

    public void newGame(int players) {
        this.numberPlayers = players;

    }

    //To be called when user logs in
    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public Set<Player> getPlayers() {
        return this.players;
    }

    public void startGame() {
        configLoader = new YamlLoader();
        giveRoles();
        giveCharacters();
        initDeck();
    }

    private void giveRoles() {
        ConfigFactory factory = ConfigFactory.getInstance(players.size());
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

    public void initDeck() {
        YamlLoader cardsLoader = new YamlLoader();
        deck.addAll(cardsLoader.getConcreteWeapons());
        deck.addAll(cardsLoader.getConcreteActions());
        deck.addAll(cardsLoader.getConcreteProperties());
    }

    public void initRound() {
        PlayerListNode head = null, curr, prev = null;
        for (Player p : players) {
            curr = new PlayerListNode(p);
            if(head == null)
                head = curr;
            if (prev != null) {
                prev.setNext(curr);
                curr.setPrevious(prev);
            }
            prev = curr;
        }

        if (head != null && prev != null) {
            prev.setNext(head);
            head.setPrevious(prev);
            currentPlayer = head;
        }

        setShogunFirst();
    }

    private void setShogunFirst() {
        boolean shogunFound = false;
        PlayerListNode iter = currentPlayer;
        while(!shogunFound) {
            if(iter.getPlayer().getRole().getName().equals("Shogun"))
                shogunFound = true;
            else {
                iter = iter.getNext();
            }
        }
        currentPlayer = iter;
    }

    public void distributeCards() {
        // Assumption: first player in the list is the Shogun
        PlayerListNode iter = currentPlayer;
        int[] cardsPerPlayer = new int[]{4,5,5,6,6,7,7};
        int i = 0;
        while(iter.getPlayer().getCards().isEmpty()) {
            for(int j = 0; j < cardsPerPlayer[i]; j++) {
                iter.getPlayer().giveCard(deck.pop());
            }
            iter = iter.getNext();
            i++;
        }
    }

    public void distributeHonorPoints() {
        int honorPoints = (players.size() < 6) ? 4 : 3;
        for (Player p : players) {
            if (p.getRole().getName().equals("Shogun"))
                p.setHonorPoints(5);
            else p.setHonorPoints(honorPoints);
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer.getPlayer();
    }

    public void nextRound() {
        currentPlayer = currentPlayer.getNext();
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
            p.decreaseHonorPoints();
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

    public void removePlayers() {
        for (Player p : players) {
            deck.addAll(p.getCards());
            p.getCards().clear();
        }
        players.clear();
        // Garbage collector should de-allocate the list if head is set to null
        currentPlayer = null;
    }

    public void clearAll() {
        removePlayers();
        deck.clear();
        cemetery.clear();
    }

    public void printGameState() {
        System.out.printf("Current deck size: %d\n", deck.size());
        System.out.printf("Current cemetery size: %d\n", cemetery.size());
        System.out.printf("Current player: %s\n", currentPlayer.getPlayer().getName());
        System.out.printf("Previous player: %s\n", currentPlayer.getNext().getPlayer().getName());
        System.out.printf("Next player: %s\n", currentPlayer.getPrevious().getPlayer().getName());
    }

}
