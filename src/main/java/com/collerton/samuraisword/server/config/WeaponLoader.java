/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collerton.samuraisword.server.config;

import com.collerton.samuraisword.server.model.Weapon;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

/**
 *
 * @author thomas
 */
public class WeaponLoader {
    
    public List<Weapon> loadWeapons() {
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
