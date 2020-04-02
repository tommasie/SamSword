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
package com.collerton.samuraisword.game.config;

import com.collerton.samuraisword.game.model.DeckCard;
import com.collerton.samuraisword.game.model.Role;
import com.collerton.samuraisword.game.model.Weapon;
import com.collerton.samuraisword.game.model.characters.Benkei;
import com.collerton.samuraisword.game.model.characters.Chiyome;
import com.collerton.samuraisword.game.model.characters.GameCharacter;
import com.collerton.samuraisword.game.model.characters.Ginchiyo;
import com.collerton.samuraisword.game.model.characters.Goemon;
import com.collerton.samuraisword.game.model.characters.Hanzo;
import com.collerton.samuraisword.game.model.characters.Hideyoshi;
import com.collerton.samuraisword.game.model.characters.Ieyasu;
import com.collerton.samuraisword.game.model.characters.Kojiro;
import com.collerton.samuraisword.game.model.characters.Musachi;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

/**
 *
 * @author tommasie
 */
public class GameConfig {

    private static final Logger logger = LoggerFactory.getLogger(GameConfig.class);

    private int players;

    private List<Role> roles;
    private List<GameCharacter> characters;

    public GameConfig(int players) {
        this.players = players;
        this.roles = new ArrayList<>();
        this.characters = new ArrayList<>();
    }

    public List<Role> loadRoles() {
        if(!roles.isEmpty()) {
            return roles;
        }
        String filePath = String.format("/config%d.yml", players);
        Yaml yaml = new Yaml(new Constructor(Role.class));
        InputStream inputStream = this.getClass()
          .getResourceAsStream(filePath);

        for (Object object : yaml.loadAll(inputStream)) {
            Role role = (Role)object;
            roles.add(role);
        }
        logger.info("{} roles loaded", roles.size());
        return roles;
    }

    public List<GameCharacter> loadCharacters() {
        if(!characters.isEmpty()) {
            return characters;
        }
        characters = new ArrayList<GameCharacter>() {{
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

        logger.info("{} characters loaded", characters.size());
        return characters;
    }

    private List<Weapon> loadWeapons() {
        Yaml yaml = new Yaml(new Constructor(Weapon.class));
        InputStream inputStream = this.getClass()
          .getResourceAsStream("/weapons.yml");

        List<Weapon> weapons = new ArrayList<>();
        for (Object object : yaml.loadAll(inputStream)) {
            Weapon weapon = (Weapon)object;
            weapons.add(weapon);
        }
        return weapons;
    }


}
