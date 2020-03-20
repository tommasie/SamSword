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
import com.collerton.samuraisword.server.model.Weapon;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

/**
 * YAML weapon loader
 * @author tommasie
 */
public class WeaponLoader {

    public List<DeckCard> loadWeapons() {
        Yaml yaml = new Yaml(new Constructor(Weapon.class));
        InputStream inputStream = this.getClass()
          .getClassLoader()
          .getResourceAsStream("weapons.yml");

        List<DeckCard> weapons = new ArrayList<>();
        for (Object object : yaml.loadAll(inputStream)) {
            Weapon weapon = (Weapon)object;
            weapons.add(weapon);
        }
        return weapons;
    }

}
