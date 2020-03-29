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

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author tommasie
 */
public class BroadcastMessageSingleton {

    private static BroadcastMessageSingleton instance;

    private Set<ServiceRequest> reqs;

    private BroadcastMessageSingleton() {
        reqs = new HashSet<>();
    }

    public static synchronized BroadcastMessageSingleton getInstance() {
        if(instance == null)
            instance = new BroadcastMessageSingleton();
        return instance;
    }

    public void addRequest(ServiceRequest rq) {
        reqs.add(rq);
    }

    public void sendMessage(String message) {
        StringBuilder sb = new StringBuilder();
        sb.append("\u001B[1;35m").append(message).append("\u001B[0m\n");
        String broadcastMessage = sb.toString();
        for(ServiceRequest rq : reqs) {
            rq.writeOnStream(broadcastMessage);
        }
    }

}
