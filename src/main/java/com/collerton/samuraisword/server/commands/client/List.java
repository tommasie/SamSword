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

import com.collerton.samuraisword.game.model.DeckCard;
import com.collerton.samuraisword.server.commands.Command;
import java.util.Queue;

/**
 *
 * @author tommasie
 */
public class List extends Command {

    public List() {
        super("List");
    }

    @Override
    public void execute(Queue<String> params) {
        if (params.size() > 1) {
            errorResponse = "Too many arguments";
            executionStatus = false;

        } else {
            okResponse = player.displayCards();
            executionStatus = true;
            /*
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("Cards in your hand: %d\n", player.getCards().size()));
            for (DeckCard card : player.getCards()) {
                sb.append(String.format("Name: %s\n", card.getName()));
                sb.append(String.format("Description: %s\n", "desc"));
            }
            */
        }

    }

}
