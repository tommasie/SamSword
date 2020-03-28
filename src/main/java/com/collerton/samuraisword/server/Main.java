/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collerton.samuraisword.server;

import com.collerton.samuraisword.game.model.GameSingleton;
import java.net.InetSocketAddress;

/**
 *
 * @author tommasie
 */
public class Main {

    public static void main(String... args) {
        GameSingleton GAME = GameSingleton.getInstance();
        Server server = new Server("localhost", 1337);
        server.start();
        //GameSocketServer server = new GameSocketServer(new InetSocketAddress("localhost", 1337));
        //server.run();
    }

}
