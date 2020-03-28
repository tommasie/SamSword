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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
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
    public boolean execute(Socket socket, Queue<String> params) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        boolean status;
        String user = params.remove();
        if (LoginFacade.checkUserConnected(user)) {
            writer.print("User already existing, bye!");
            writer.flush();
            writer.close();
            reader.close();
            socket.close();
            status = false;
        } else {
            System.out.println("User not exists, request room pass");
            writer.println("Room's password: ");
            writer.flush();
            String line = reader.readLine();
            System.out.println(line);
            if(LoginFacade.checkPassword(line)) {
                LoginFacade.storeUser(user);
                writer.println("Login successful, welcome!");
                status = true;
            } else {
                writer.println("Wrong password, bye!");
                status = false;
            }
            writer.flush();
        }
        return status;
    }

}
