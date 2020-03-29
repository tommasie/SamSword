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

import com.collerton.samuraisword.server.commands.Command;
import java.util.Queue;

/**
 *
 * @author tommasie
 */
public class Respond extends Command {

    public Respond() {
        super("Respond");
    }

    @Override
    public void execute(Queue<String> params) {
        if(params.size() != 1) {
            errorResponse = "Only one parameter allowed";
            executionStatus = false;
            return;
        }
        String response = params.remove();
        if(response.equalsIgnoreCase("life")) {
            if(player.getAwaitsAttack()) {
                player.sufferAttack();
            } else {
                player.decreaseResistancePoints();
            }
            okResponse = "Your resistance points are now " + player.getResistancePoints();
            executionStatus = true;
        } else if (response.equalsIgnoreCase("parry") && (player.getAwaitsBattlecry() || player.getAwaitsAttack())) {
            if(player.discardCard(player.getCardByName(response))) {
                okResponse = "Your lost a parry";
                executionStatus = true;
            } else {
                player.decreaseResistancePoints();
                errorResponse = "You don't have a parry in your hand, you lose a resistance point";
                executionStatus = false;
            }
        } else if (player.getAwaitsJujitsu()) {
            player.discardCard(player.getCardByName(response));
            okResponse = String.format("Your lost a %s", response) ;
            executionStatus = true;
        } else if (player.getAwaitsAttack()) {
            //In case the player is being attack and chose a not  parry nor life
            if(player.canParryWithWeapon() && player.discardCard(player.getCardByName(response))) {
                okResponse = String.format("Your lost a %s", response) ;
                executionStatus = true;
            } else {
                errorResponse = String.format("You don't have a %s in your hand, you lose a resistance point", response);
                executionStatus = false;
            }
        }
    }

}
