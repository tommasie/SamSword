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

import com.collerton.samuraisword.server.commands.client.Attack;
import com.collerton.samuraisword.server.commands.client.GameInfo;
import com.collerton.samuraisword.server.commands.client.Help;
import com.collerton.samuraisword.server.commands.client.List;
import com.collerton.samuraisword.server.commands.client.Login;
import com.collerton.samuraisword.server.commands.client.Pass;
import com.collerton.samuraisword.server.commands.client.Play;
import com.collerton.samuraisword.server.commands.client.Respond;
import com.collerton.samuraisword.server.commands.client.Start;

/**
 *
 * @author tommasie
 */
public class CommandFactory {

    private static CommandFactory instance;

    private CommandFactory() {
    }

    public static synchronized CommandFactory getInstance() {
        if(instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }

    public Command getCommand(String command) {
        if(command.equals("login"))
            return new Login();
        if(command.equals("help"))
            return new Help();
        if(command.equals("start"))
            return new Start();
        if(command.equals("info"))
            return new GameInfo();
        if(command.equals("list"))
            return new List();
        if(command.equals("play"))
            return new Play();
        if(command.equals("attack"))
            return new Attack();
        if(command.equals("respond"))
            return new Respond();
        if(command.equals("pass"))
            return new Pass();
        return null;
    }

}
