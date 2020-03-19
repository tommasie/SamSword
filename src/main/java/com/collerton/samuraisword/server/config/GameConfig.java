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
package com.collerton.samuraisword.server.config;

import com.collerton.samuraisword.server.model.DeckCard;
import com.collerton.samuraisword.server.model.Role;
import com.collerton.samuraisword.server.model.Weapon;
import com.collerton.samuraisword.server.model.characters.Benkei;
import com.collerton.samuraisword.server.model.characters.Chiyome;
import com.collerton.samuraisword.server.model.characters.GameCharacter;
import com.collerton.samuraisword.server.model.characters.Ginchiyo;
import com.collerton.samuraisword.server.model.characters.Goemon;
import com.collerton.samuraisword.server.model.characters.Hanzo;
import com.collerton.samuraisword.server.model.characters.Hideyoshi;
import com.collerton.samuraisword.server.model.characters.Ieyasu;
import com.collerton.samuraisword.server.model.characters.Kojiro;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

/**
 *
 * @author tommasie
 */
public abstract class GameConfig {
    
    protected String path;
    
    private List<Role> roles;
    
    public GameConfig(String path) {
        this.path = path;
    }
    
    public abstract List<Role> loadRoles();
    
    public List<GameCharacter> loadCharacters() {
        List<GameCharacter> characters = new ArrayList<>();
        characters.add(new Benkei());
        characters.add(new Chiyome());
        characters.add(new Ginchiyo());
        characters.add(new Goemon());
        characters.add(new Hanzo());
        characters.add(new Hideyoshi());
        characters.add(new Ieyasu());
        characters.add(new Kojiro());
        
        return characters;
    }
    
    public List<DeckCard> loadCards() {
        List<DeckCard> cards = new Stack<>();
        // Add the Weapons
        cards.addAll(new WeaponLoader().loadWeapons());
        // Add the Actions
        
        //Add the Properties
        
        return cards;
    }
    
    private List<Weapon> loadWeapons() {
        Yaml yaml = new Yaml(new Constructor(Weapon.class));
        InputStream inputStream = this.getClass()
          .getClassLoader()
          .getResourceAsStream("weapons.yml");
        
        List<Weapon> weapons = new ArrayList<>();
        for (Object object : yaml.loadAll(inputStream)) {
            Weapon weapon = (Weapon)object;
            weapons.add(weapon);
        }
        return weapons;
    }
    
    
}
