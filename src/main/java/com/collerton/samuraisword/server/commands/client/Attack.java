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
import com.collerton.samuraisword.game.model.Weapon;
import com.collerton.samuraisword.server.commands.Command;
import java.util.Queue;

/**
 *
 * @author tommasie
 */
public class Attack extends Command {

    public Attack() {
        super("Attack");
    }

    @Override
    public void execute(Queue<String> params) {
        if(params.size() != 2) {
            errorResponse = "Only two parameters allowed";
            executionStatus = false;
            return;
        }
        try {
            if(player != GAME.getCurrentPlayer()) {
                errorResponse = "Not your turn, cheater!";
                executionStatus = false;
                return;
            }
            String cardName = params.remove();
            Weapon card = (Weapon) player.getCardByName(cardName);
            if(card != null) {
                Player receiver = GAME.getPlayerByName(params.remove());
                if(receiver != null) {
                    BCAST.sendMessage(String.format("%s has attacked %s with %s",
                                                            player.getName(), receiver.getName(), card.getName()));
                    card.play(receiver);
                } else {
                    errorResponse = "Wrong player name";
                    executionStatus = false;
                }
            } else {
                errorResponse = "This card is not in your hand\nCheck what you have with the display command";
                executionStatus = false;
            }
        } catch(Exception e) {
            errorResponse = e.getMessage();
            executionStatus = false;
        }
    }

}
