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
package com.collerton.samuraisword.server.commands;

import com.collerton.samuraisword.game.model.GameSingleton;
import com.collerton.samuraisword.game.model.Player;
import java.io.IOException;
import java.net.Socket;
import java.util.Queue;
import org.java_websocket.WebSocket;

/**
 *
 * @author tommasie
 */
public abstract class Command {

    private String name;
    protected Queue<String> params;
    protected boolean resolved;
    protected boolean executionStatus;
    protected String okResponse;
    protected String errorResponse;
    protected Player player;
    protected static final GameSingleton GAME = GameSingleton.getInstance();

    public Command(String name) {
        this.name = name;
        resolved = false;
        executionStatus = false;
        player = null;
    }

    public Command(String name, Queue<String> params) {
        this(name);
        this.params = params;

    }

    public abstract void execute(Queue<String> params);

    public boolean getStatus() {
        return executionStatus;
    }

    public boolean isResolved() {
        return resolved;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getOkResponse() {
        return okResponse;
    }

    public String getErrorResponse() {
        return errorResponse;
    }


}
