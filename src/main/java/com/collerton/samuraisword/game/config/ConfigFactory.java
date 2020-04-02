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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

/**
 * First attempt at a YAML config loader
 * Given the number of players, the factory chooses the appropriate game config
 *
 * @author tommasie
 */
public class ConfigFactory {

    private static final Logger logger = LoggerFactory.getLogger(ConfigFactory.class);

    private GameConfig gameConfig;

    private ConfigFactory() {}

    public static GameConfig getConfiguration(int players) {
        return new GameConfig(players);
    }

}
