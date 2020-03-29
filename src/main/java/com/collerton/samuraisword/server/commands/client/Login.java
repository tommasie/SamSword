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
package com.collerton.samuraisword.server.commands.client;

import com.collerton.samuraisword.game.model.Player;
import com.collerton.samuraisword.server.LoginFacade;
import com.collerton.samuraisword.server.commands.Command;
import java.util.Queue;

/**
 *
 * @author tommasie
 */
public class Login extends Command {

    public Login() {
        super("Login");
    }

    @Override
    public void execute(Queue<String> params) {
        String user = params.remove();
        if (LoginFacade.checkUserConnected(user)) {
            errorResponse = "User already existing, bye!";
            executionStatus = false;
        } else {
            player = new Player(user);
            GAME.addPlayer(player);
            okResponse = "Welcome to the game!";
            executionStatus = true;
            /*
            System.out.println("User doesn't exist, request room pass");
            if(LoginFacade.checkPassword(params.remove())) {
                LoginFacade.storeUser(user);
                websocket.send("Login successful, welcome!");
                status = true;
            } else {
                websocket.send("Wrong password, bye!");
                status = false;
            }*/
        }
    }

}
