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
package com.collerton.samuraisword.game.list;

import com.collerton.samuraisword.game.model.Player;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * List interface implementation of the game round of players,
 * basically a looping double-linked list to support both a normal round and Bushido
 *
 * @author tommasie
 */
public class PlayersRound implements List<Player> {

    private PlayerListNode currentPlayerNode;
    private PlayerListNode head;
    private int size;

    public PlayersRound() {
        currentPlayerNode = null;
        head = null;
        size = 0;
    }

    /**
     * Set the head of the list to the player owning the Shogun role
     */
    public void resetHead() {
        PlayerListNode iter = head;
        boolean shogunFound = false;
        do {
            if(iter.getPlayer().getRole().getName().equals("Shogun")) {
                head = iter;
                shogunFound = true;
            }
            iter = iter.getNext();
        } while(iter != head && !shogunFound);
    }

    public Player getCurrentPlayer() {
        if(currentPlayerNode == null) {
            currentPlayerNode = head;
        }
        return currentPlayerNode.getPlayer();
    }

    public void nextRound() {
        currentPlayerNode = currentPlayerNode.getNext();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if(isEmpty()) {
            return false;
        }
        Player player = (Player) o;
        Iterator<Player> iter = iterator();
        while(iter.hasNext()) {
            if(iter.next().equals(player)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<Player> iterator() {
        return new PlayersRoundIterator();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(Player e) {
        PlayerListNode node = new PlayerListNode(e);

        if(head == null) {      // If the list is empty, set the new node as the head
            head = node;
        } else if (size == 1) { //If only the head is available, set the two items links
            head.setNext(node);
            head.setPrevious(node);
            node.setNext(head);
            node.setPrevious(head);
        } else {                //The last item is the previous of the head, insert the new one in the middle
            head.getPrevious().setNext(node);
            node.setPrevious(head.getPrevious());
            node.setNext(head);
            head.setPrevious(node);
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if(head == null) {
            return false;
        }
        Player p = (Player) o;
        PlayerListNode iter = head;
        while(p != iter.getPlayer()) {
            iter = iter.getNext();
        }
        iter.getPrevious().setNext(iter.getNext());
        iter.getNext().setPrevious(iter.getPrevious());
        iter.setNext(null);
        iter.setPrevious(null);
        size--;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> clctn) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Player> collection) {
        for(Player p : collection) {
            add(p);
        }
        return true;
    }

    @Override
    public boolean addAll(int i, Collection<? extends Player> clctn) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> clctn) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> clctn) {
        return false;
    }

    @Override
    public void clear() {
        head = null;
        currentPlayerNode = null;
        size = 0;
    }

    @Override
    public Player get(int i) throws IndexOutOfBoundsException {
        if(i >= size) {
            throw new IndexOutOfBoundsException(String.format("There are less than %d players", i + 1));
        }
        int j = 0;
        PlayerListNode iter = head;
        while(j < i) {
            iter = iter.getNext();
            j++;
        }
        return iter.getPlayer();
    }

    @Override
    public Player set(int i, Player e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(int i, Player e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Player remove(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<Player> listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<Player> listIterator(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Player> subList(int i, int i1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        StringBuilder toStringBuilder = new StringBuilder();
        toStringBuilder.append(String.format("Head of the round: %s\r\n", head.getPlayer().getName()));
        return toStringBuilder.toString();
    }

    public class PlayersRoundIterator implements Iterator<Player> {

        private int iteratedItems;
        private PlayerListNode iter;

        public PlayersRoundIterator() {
            iteratedItems = 0;
            iter = null;
        }

        @Override
        public boolean hasNext() {
            return iteratedItems < size;
        }

        @Override
        public Player next() {
            iteratedItems++;
            if(iter == null) {
                iter = head;
            } else {
                iter = iter.getNext();
            }
            return iter.getPlayer();
        }

    }

}
