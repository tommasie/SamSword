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
package com.collerton.samuraisword.server;

import com.collerton.samuraisword.game.model.Player;
import com.collerton.samuraisword.server.commands.Command;
import com.collerton.samuraisword.server.commands.CommandFactory;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author tommasie
 */
public class MessageInterface {

    private ServiceRequest req;

    private Player player;
    private PlayerSocketProxy proxy;

    public MessageInterface(ServiceRequest req) {
        this.req = req;
        player = null;
        proxy = null;
    }

    public void receive(String message) {
        CommandFactory commandFactory = CommandFactory.getInstance();
        System.out.println(message);
        Queue<String> commandLineQueue = new LinkedList<>();
        Arrays.stream(message.split(" ")).forEachOrdered(item -> {commandLineQueue.add(item);});
        Command command = commandFactory.getCommand(commandLineQueue.remove());
        if (command != null) {
            if(player != null) {
                command.setPlayer(player);
                command.execute(commandLineQueue);
            } else {
                command.execute(commandLineQueue);
                player = command.getPlayer();
                proxy = new PlayerSocketProxy(player, this);
            }
            if(command.getStatus()) {
                send(command.getOkResponse());
            } else {
                send(command.getErrorResponse());
            }
        } else {
            send("Command not recognized");
        }

    }

    public void send(String str) {
        req.writeOnStream(str + "\n");
    }

}
