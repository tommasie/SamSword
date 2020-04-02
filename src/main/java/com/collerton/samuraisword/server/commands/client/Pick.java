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
public class Pick extends Command {

    public Pick() {
        super("Pick");
    }

    @Override
    public void execute(Queue<String> params) {
        // By default, a player can pick from the deck
        // He can also specify to pick from the graveyard;
        if(params.size() > 1) {
            errorResponse = "Too many arguments";
            executionStatus = false;
        } else if(params.isEmpty()) {
            DeckCard card;
            int cards = player.getCardPickCounter();
            for(int i = 0; i < cards; i++) {
                card = GAME.pickCardFromDeck();
                player.giveCard(card);
            }
            okResponse = String.format("%d cards were picked", cards);
            executionStatus = true;
        } else if(params.remove().equals("graveyard")) {
            if(player.canPickFromGraveyard()) {
                DeckCard card = GAME.pickTopOfGraveyard();
                player.giveCard(card);
                card = GAME.pickCardFromDeck();
                player.giveCard(card);
                okResponse = "2 cards were picked";
                executionStatus = true;
            } else {
                errorResponse = "You cannot pick from the graveyard, cheater!";
                executionStatus = false;
            }
        } else {
            errorResponse = "Argument not recognized";
            executionStatus = false;
        }
    }

}
