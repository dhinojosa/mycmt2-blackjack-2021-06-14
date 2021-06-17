package com.jitterted.ebp.blackjack.domain;

import com.jitterted.ebp.blackjack.domain.port.GameMonitor;

import java.util.Objects;

public class Game {

    private final Deck deck;
    private final Hand dealerHand = new Hand();
    private final Hand playerHand = new Hand();
    private final GameMonitor gameMonitor;
    private boolean playerDone;

    public Game() {
        this(new Deck(), new DoNothingGameMonitor());
    }

    public Game(Deck deck) {
        this(deck, new DoNothingGameMonitor());
    }

    public Game(Deck deck, GameMonitor gameMonitor) {
        Objects.requireNonNull(gameMonitor, "Game Monitor cannot be null");
        this.deck = deck;
        this.gameMonitor = gameMonitor;
    }

    public void initialDeal() {
        dealRoundOfCards();
        dealRoundOfCards();
    }

    private void dealRoundOfCards() {
        // why: players first because this is the rule
        playerHand.drawFrom(deck());
        dealerHand.drawFrom(deck);
    }

    public GameOutcome determineOutcome() {
        if (playerHand.isBusted()) {
            return GameOutcome.PLAYER_BUSTED;
        } else if (dealerHand.isBusted()) {
            return GameOutcome.DEALER_BUSTED;
        } else if (playerHand.hasBlackJack()) {
            return GameOutcome.BLACKJACK;
        } else if (playerHand.beats(dealerHand)) {
            return GameOutcome.PLAYER_BEATS_DEALER;
        } else if (playerHand.pushes(dealerHand)) {
            return GameOutcome.PUSH;
        } else {
            return GameOutcome.DEALER_BEATS_PLAYER;
        }
    }

    public void dealerTurn() {
        // Dealer makes its choice automatically based on a simple heuristic
        // (<=16 must hit, =>17 must stand)
        if (!playerHand.isBusted()) {
            while (dealerHand.dealerMustDrawCard()) {
                dealerHand.drawFrom(deck);
            }
        }
    }

    public Deck deck() {
        return deck;
    }

    public Hand dealerHand() {
        return dealerHand;
    }

    public Hand playerHand() {
        return playerHand;
    }

    public void playerHits() {
        playerHand.drawFrom(deck);
        playerDone = playerHand.isBusted();
    }

    public void playerStands() {
        playerDone = true;
    }

    public boolean isPlayerDone() {
        return playerDone;
    }
}
