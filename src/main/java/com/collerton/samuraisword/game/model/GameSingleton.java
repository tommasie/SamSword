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
package com.collerton.samuraisword.game.model;

import com.collerton.samuraisword.game.model.characters.GameCharacter;
import com.collerton.samuraisword.game.config.ConfigFactory;
import com.collerton.samuraisword.game.config.YamlLoader;
import com.collerton.samuraisword.game.list.PlayerListNode;
import com.collerton.samuraisword.game.model.characters.Benkei;
import com.collerton.samuraisword.game.model.characters.Chiyome;
import com.collerton.samuraisword.game.model.characters.Ginchiyo;
import com.collerton.samuraisword.game.model.characters.Goemon;
import com.collerton.samuraisword.game.model.characters.Hanzo;
import com.collerton.samuraisword.game.model.characters.Hideyoshi;
import com.collerton.samuraisword.game.model.characters.Ieyasu;
import com.collerton.samuraisword.game.model.characters.Kojiro;
import com.collerton.samuraisword.game.model.characters.Musachi;
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

    private boolean started;

    private int numberPlayers;

    private final Set<Player> players;

    private PlayerListNode currentPlayer;

    private final Stack<DeckCard> deck;

    private final Stack<DeckCard> cemetery;

    private GameSingleton() {
        started = false;
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
        players.add(player);
    }

    public void createPlayer(String playerName) {
        Player player = new Player(playerName);
        addPlayer(player);
    }

    public Set<Player> getPlayers() {
        return this.players;
    }

    public Player getPlayerByName(String playerName) {
        for(Player player : players) {
            if(player.getName().equals(playerName)) {
                return player;
            }
        }
        return null;
    }

    public boolean startGame() {
        if(players.size() > 3) {
            configLoader = new YamlLoader();
            giveRoles();
            System.out.println("roles given");
            giveCharacters();
            System.out.println("chars given");
            initDeck();
            System.out.println("deck setup");
            initRound();
            System.out.println("round setup");
            distributeCards();
            System.out.println("cards given");
            distributeHonorPoints();
            System.out.println("honor points given");
            return true;
        } else {
            System.out.println("players: " + players.size());
            return false;
        }
    }

    public boolean started() {
        return started;
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
        List<GameCharacter> characters = new ArrayList<GameCharacter>() {{
            add(new Benkei());
            add(new Chiyome());
            add(new Ginchiyo());
            add(new Goemon());
            add(new Hanzo());
            add(new Hideyoshi());
            add(new Ieyasu());
            add(new Kojiro());
            add(new Musachi());
        }};
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
        Collections.shuffle(deck);
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
        players.forEach(player -> {
            if (player.getRole().getName().equals("Shogun"))
                player.setHonorPoints(5);
            else player.setHonorPoints(honorPoints);
        });
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
            // Remove cards form player's hand
            deck.addAll(p.getCards());
            p.getCards().clear();
            // Remove properties from player's table
            for(String property : p.getPlayedProperties().keySet()) {
                deck.addAll(p.getPlayedProperties().remove(property));
            }
            p.getPlayedProperties().clear();

            p.removeRole();
            p.removeCharacter();
        }
        players.clear();
        // Garbage collector should de-allocate the list if head is set to null
        currentPlayer = null;
    }

    public void reset() {
        removePlayers();
        deck.addAll(cemetery);
        cemetery.clear();
        //deck.clear();
        //cemetery.clear();
    }

    public void printGameState() {
        System.out.printf("Current deck size: %d\n", deck.size());
        System.out.printf("Current cemetery size: %d\n", cemetery.size());
        if(currentPlayer != null) {
            System.out.printf("Current player: %s\n", currentPlayer.getPlayer().getName());
            System.out.printf("Previous player: %s\n", currentPlayer.getNext().getPlayer().getName());
            System.out.printf("Next player: %s\n", currentPlayer.getPrevious().getPlayer().getName());
        }
    }

    public String getGameState() {
        StringBuilder sb = new StringBuilder();
        sb.append("Current game status\n");
        sb.append("Players: ");
        for(Player p : players) {
            sb.append(p.getName()).append(" ");
        }
        sb.append("\n");

        PlayerListNode iter = currentPlayer;
        int i = 0;
        while(i < players.size()) {
            sb.append(iter.getPlayer().toString()).append("\n");
            iter = iter.getNext();
            i++;
        }
        return sb.toString();
    }

}
