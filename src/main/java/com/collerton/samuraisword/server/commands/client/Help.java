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
public class Help extends Command {

    public Help() {
        super("Help");
    }

    @Override
    public void execute(Queue<String> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("Available commands:\n");
        sb.append("- login <username>\tLogin\n");
        sb.append("- info\t\t\tDisplay current game state\n");
        sb.append("- list\t\t\tDisplay your cards\n");
        sb.append("- pick [graveyard]\tPick cards from the deck, or one card from the graveyard, if allowed\n");
        sb.append("- play <card> [user]\tPlay a card, eventually against another player\n");
        sb.append("- attack <card> <user>\tAtack another player with a card\n");
        sb.append("- respond <life | card>\tRespond to attack or jujitsu/battlecry\n");
        sb.append("- pass\t\t\tEnd your turn\n");
        sb.append("- quit\t\t\tExit the game\n");

        okResponse = sb.toString();
        executionStatus = true;
    }

}
