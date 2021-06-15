package com.jitterted.ebp.blackjack.domain;

import java.util.ArrayList;
import java.util.ListIterator;

public class StubDeck extends Deck {

    private final ListIterator<Card> iterator;

    public StubDeck(Rank... ranks) {
        ArrayList<Card> cards = new ArrayList<>();
        for (Rank rank : ranks) {
            cards.add(new Card(Suit.DIAMONDS, rank));
        }
        this.iterator = cards.listIterator();
    }

    @Override
    public Card draw() {
        return iterator.next();
    }
}
