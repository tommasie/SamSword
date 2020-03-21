/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collerton.samuraisword.server;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author tommasie
 */
public class LoginFacade {

    private static Set<String> usernames = new HashSet<>();

    public static boolean checkUserConnected(String user) {
        return usernames.contains(user);
    }

    public static boolean checkPassword(String pass) {
        return pass.equals("password");
    }

    public static void storeUser(String user) {
        usernames.add(user);
    }

}
