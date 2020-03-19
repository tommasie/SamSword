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

import com.collerton.samuraisword.server.exceptions.GameException;
import com.collerton.samuraisword.server.model.properties.Property;
import com.collerton.samuraisword.server.model.characters.GameCharacter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * This class models the player of the game.
 * Each player has a set of properties defined by the character he is given
 * and by the Property cards he can play throughout the game.
 * 
 * @author tommasie
 */
public class Player {
    
    private static final GameSingleton GAME = GameSingleton.getInstance();
    
    // Username, unique for each game
    private final String name;
    
    // Main player points
    private int honorPoints;
    private int resistancePoints;
    
    // List of properties affected by the character and Property cards
    private int distanceBonus;
    private int attackBonus;
    private int weaponMultiplier;
    private boolean suffersLessDamage;
    private boolean damageOnlyFromWeapons;
    private boolean canParryWithWeapon;
    private boolean canPickExtraCard;
    private boolean canPickFromCemetery;
    private boolean ignoresDifficulty;
    
    // Role given at the beginning of the game
    private Role role;
    
    // Character given at the beginning of the game
    private GameCharacter character;
    
    // Cards in the player's hand
    private final List<DeckCard> cards;
    
    // Property cards played the player and shown to everyone
    private Map<String, Stack<Property>> playedProperties;
    
    public Player(String name) {
        this.name = name;
        this .honorPoints = 0;
        this.resistancePoints = 0;
        this.attackBonus = 0;
        this.distanceBonus = 0;
        this.weaponMultiplier= 1;
        this.suffersLessDamage = false;
        this.damageOnlyFromWeapons = false;
        this.canParryWithWeapon = false;
        this.canPickExtraCard = false;
        this.canPickFromCemetery = false;
        this.ignoresDifficulty = false;
        this.role = null;
        this.character = null;
        this.cards = new LinkedList<>();
        this.playedProperties = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public int getHonorPoints() {
        return honorPoints;
    }

    public int getResistancePoints() {
        return resistancePoints;
    }

    public int getDistanceBonus() {
        return distanceBonus;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    public int getWeaponMultiplier() {
        return weaponMultiplier;
    }

    public Role getRole() {
        return role;
    }
    
    public void chooseRole(List<Role> roles) {
        // Ask for a number between 1 and roles.size
        int index = 0;
        setRole(roles.remove(index));
    }
    
    private void setRole(Role role) {
        this.role = role;
    }

    public GameCharacter getCharacter() {
        return character;
    }
    
    public void chooseCharacter(List<GameCharacter> characters)
    {
        int index = 0;
        setCharacter(characters.remove(index));
    }

    public void setCharacter(GameCharacter character) {
        this.character = character;
        this.character.setOwner(this);
        // Bump the player's properties
        this.character.play();
        resetResistancePoints();
    }

    public List<DeckCard> getCards() {
        return cards;
    }

    public Map<String, Stack<Property>> getPlayedProperties() {
        return playedProperties;
    }
    
    public void setHonorPoints(int points) {
        this.honorPoints = points;
    }
    
    public void increaseHonorPoints() {
        this.honorPoints += 1;
    }
    
    public void decreseHonorPoints() {
        this.honorPoints -= 1;
        if(this.honorPoints == 0) {
            System.out.println("I am a loser");
            GAME.endGame();
        }
    }
    
    public void resetResistancePoints() {
        this.resistancePoints = character.getResistancePoints();
    }
    
    public void increaseAttackBonus() {
        this.attackBonus += 1;
    }
    
    public void decreaseAttackBonus() {
        this.attackBonus -= 1;
    }
    
    public void increaseDistanceBonus() {
        this.distanceBonus += 1;
    }
    
    public void decreaseDistanceBonus() {
        this.distanceBonus -= 1;
    }
    
    public void increaseWeaponMultiplier() {
        this.weaponMultiplier += 1;
    }
    
    public void decreaseWeaponMultiplier() {
        this.weaponMultiplier -=1;
    }
    
    public boolean getSuffersLessDamage() {
        return this.suffersLessDamage;
    }
    
    public void setSuffersLessDamage() {
        this.suffersLessDamage = true;
    }
    
    public boolean getDamageOnlyFromWeapons() {
        return this.damageOnlyFromWeapons;
    }
    
    public void setDamageOnlyFromWeapons() {
        this.damageOnlyFromWeapons = true;
    }
    
    public boolean canParryWithWeapon() {
        return this.canParryWithWeapon;
    }
    
    public void setCanParryWithWeapon() {
        this.canParryWithWeapon = true;
    }
    
    public boolean canPickExtraCard() {
        return this.canPickExtraCard;
    }
    
    public void setCanPickExtraCard() {
        this.canPickExtraCard = true;
    }
    
    public boolean canPickFromCemetery() {
        return this.canPickFromCemetery;
    }
    
    public void setCanPickFromCemetery() {
        this.canPickFromCemetery = true;
    }
    
    public boolean ignoresDifficulty() {
        return this.ignoresDifficulty;
    }
    
    public void setIgnoresDifficulty() {
        this.ignoresDifficulty = true;
    }
    
    public void giveCard(DeckCard card) {
        card.setOwner(this);
        this.cards.add(card);
    }
    
    public void removeCardFromHand(int index) {
        this.cards.remove(index);
    }
    
    public void movePropertyToTable(Property property) throws GameException {
        if (!this.cards.contains(property)) {
            throw new GameException("This card is not in the player's hand");
        }
        this.cards.remove(property);
        this.playedProperties.putIfAbsent(property.getName(), new Stack<>());
        Stack<Property> properties = playedProperties.get(property.getName());
        if(properties == null)
            System.out.println("mozzarella");
        properties.add(property);
    }
    
    public Property discardProperty(String propertyName) {
        Stack<Property> propertyList = playedProperties.get(propertyName);
        if(!propertyList.isEmpty()) {
            Property p = propertyList.pop();
            p.decreasePlayerAttributes();
            p.setOwner(null);
            return p;
        }
        // Player should not choose a non-existing card
        return null;
    }
    
    public void playProperty(Property property) {
        try {
            movePropertyToTable(property);
            property.play();
        } catch(GameException e) {
            e.printStackTrace();
        }
        
        
    }
    
    public void discardProperty(Property property) {
        discardProperty(property.getName());
    }
    
    public DeckCard discardCard(int index) {
        return cards.remove(index);
    }
    
    public void discardCard(DeckCard card) {
        cards.remove(card);
        GAME.addCardToCemetery(card);
    }
    
    /** 
     * Check that the player can be attacked or skipped for the distance count
     * @return boolean
     */
    public boolean canPlay() {
        return resistancePoints == 0 || getCards().isEmpty();
    }
    
    public void playJujitsu() {
        /* Ideally, the user should choose wether to give a weapon or a 
         * resistance point, and perhaps forget he can not give anything (like I usually do)
         */
        if (canPlay() && !damageOnlyFromWeapons) {
            //TODO pseudo AI?
        } else {
            System.out.println(name + " is not affected");
        }
    }
    
    public void playBattlecry() {
        /* Ideally, the user should choose wether to give a parry or a 
         * resistance point, and perhaps forget he can not give anything (like I usually do)
         */
        if (canPlay() && !damageOnlyFromWeapons) {
            //TODO pseudo AI?
        } else {
            System.out.println(name + " is not affected");
        }
    }
    
}
