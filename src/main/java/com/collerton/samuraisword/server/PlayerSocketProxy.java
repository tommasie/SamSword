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
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import org.javatuples.Pair;

/**
 *
 * @author tommasie
 */
public class PlayerSocketProxy {

    private Set<Pair<Player, Socket>> playerSocketSet;

    public PlayerSocketProxy() {
        this.playerSocketSet = new HashSet<>();
    }

    public void addPlayer(Player player, Socket socket) {
        Pair<Player, Socket> pair = Pair.with(player, socket);
        playerSocketSet.add(pair);
    }

    public String requestAction(String action) {
        // Send request message to "real" player and wait the answer
        //TODO
        return "";
    }

}
