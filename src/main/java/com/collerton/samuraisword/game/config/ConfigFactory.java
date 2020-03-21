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

import com.collerton.samuraisword.game.model.Role;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

/**
 * First attempt at a YAML config loader
 * Given the number of players, the factory chooses the appropriate game config
 *
 * @author tommasie
 */
public class ConfigFactory {

    private static ConfigFactory instance;

    private int numPlayers;
    private String configPath;

    private List<Role> roles;

    private ConfigFactory(int numPlayers) {
        this.numPlayers = numPlayers;
        this.configPath = "config" + numPlayers + ".yml";
        roles = new ArrayList<>();
    }

    public static synchronized ConfigFactory getInstance(int numPlayers) {
        if(instance == null || instance.numPlayers != numPlayers)
            instance = new ConfigFactory(numPlayers);

        return instance;
    }

    private void parseConfiguration()
    {

        Yaml yaml = new Yaml(new Constructor(Role.class));
        InputStream inputStream = this.getClass()
          .getClassLoader()
          .getResourceAsStream(configPath);

        for (Object object : yaml.loadAll(inputStream)) {
            Role role = (Role)object;
            roles.add(role);
        }
    }

    public List<Role> getRoles()
    {
        parseConfiguration();
        return roles;
    }

}
