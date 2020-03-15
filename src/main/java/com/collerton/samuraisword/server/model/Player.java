/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collerton.samuraisword.server.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author thomas
 */
public class Player {
    
    private final String name;
    private int honorPoints;
    private int resistancePoints;
    private int distanceBonus;
    private int attackBonus;
    private int weaponMultiplier;
    private boolean suffersLessDamage;
    private boolean canParryWithWeapon;
    private boolean canPickExtraCard;
    
    private Role role;
    private GameCharacter character;
    private final List<DeckCard> cards;
    private final List<Property> playedProperties;
    
    public Player(String name) {
        this.name = name;
        this .honorPoints = 0;
        this.resistancePoints = 0;
        this.attackBonus = 0;
        this.distanceBonus = 0;
        this.weaponMultiplier= 1;
        this.suffersLessDamage = false;
        this.canParryWithWeapon = false;
        this.canPickExtraCard = false;
        this.role = null;
        this.character = null;
        this.cards = new LinkedList<>();
        this.playedProperties = new ArrayList<>();
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
        this.character.play(this);
        resetResistancePoints();
    }

    public List<DeckCard> getCards() {
        return cards;
    }

    public List<Property> getPlayedProperties() {
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
    
    public boolean canParryWithWeapon() {
        return this.canParryWithWeapon;
    }
    
    public void setCanParryWithWeapon() {
        this.canParryWithWeapon = true;
    }
    
    public void giveCard(DeckCard card) {
        this.cards.add(card);
    }
    
    public void removeCardFromHand(int index) {
        this.cards.remove(index);
    }
    
    public void removeCardFromTable(Property property) {
        this.playedProperties.remove(property);
    }
    
    public void moveCardToTable(Property property) {
        this.cards.remove(property);
        this.playedProperties.add(property);
    }
    
    public void discardProperty(Property property) {
        property.decreasePlayerAttributes(this);
        //TODO
    }
    
}

