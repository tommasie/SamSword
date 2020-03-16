/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collerton.samuraisword.server.config;

import com.collerton.samuraisword.server.model.Role;
import com.collerton.samuraisword.server.model.Weapon;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

/**
 *
 * @author thomas
 */
public class ConfigFactory {
    
    private static ConfigFactory instance;
    
    private int numPlayers;
    private String configPath;
    private static String weaponsFilePath = "weapons.yml";
    
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
