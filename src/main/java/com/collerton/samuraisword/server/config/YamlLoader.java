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
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

/**
 *
 * @author tommasie
 */
public class YamlLoader {
    
    private YamlReader reader;
    
    public YamlLoader() {
        loadProperties();
    }
    
    private void loadProperties() {
        Yaml yaml = new Yaml(new Constructor(YamlReader.class));
        InputStream inputStream = this.getClass()
          .getClassLoader()
          .getResourceAsStream("properties.yml");
        
        reader = yaml.load(inputStream);
    }
    
       
    public List<DeckCard> getConcreteActions() {
        List<DeckCard> cards = new ArrayList<>();
        try {
            String pkg = "com.collerton.samuraisword.server.model.actions.";
            for(CardsYamlModel act : reader.getActions()) {
                for(int i = 0; i < act.getQuantity(); i++) {
                    Object o = Class.forName(pkg + act.getName()).newInstance();
                    DeckCard card = (DeckCard)o;
                    cards.add(card);
                }
            }
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return cards;
    }
    
    public List<DeckCard> getConcreteProperties() {
        List<DeckCard> cards = new ArrayList<>();
        try {
            String pkg = "com.collerton.samuraisword.server.model.properties.";
            for(CardsYamlModel prop : reader.getProperties()) {
                for(int i = 0; i < prop.getQuantity(); i++) {
                    Object o = Class.forName(pkg + prop.getName()).newInstance();
                    DeckCard card = (DeckCard)o;
                    cards.add(card);
                }
            }
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return cards;
    }
    
    public List<DeckCard> getConcreteWeapons() {
        WeaponLoader weaponsLoader = new WeaponLoader();
        List<DeckCard> cards = weaponsLoader.loadWeapons();
        
        return cards;
    }
}
