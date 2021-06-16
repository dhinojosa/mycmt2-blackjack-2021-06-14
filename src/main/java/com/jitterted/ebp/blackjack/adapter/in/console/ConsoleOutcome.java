package com.jitterted.ebp.blackjack.adapter.in.console;

import com.jitterted.ebp.blackjack.domain.GameOutcome;

public class ConsoleOutcome {
    public static String of(GameOutcome gameOutcome) {
        switch(gameOutcome) {
            case BLACKJACK: return "Congratulations! You got a Blackjack!. 💵";
            case DEALER_BEATS_PLAYER: return "You lost to the Dealer. 💸";
            case DEALER_BUSTED: return "Dealer went BUST, Player wins! Yay for you!! 💵";
            case PUSH: return "Push: Nobody wins, we'll call it even.";
            case PLAYER_BUSTED: return "You Busted, so you lose. 💸";
            case PLAYER_BEATS_DEALER: return "You beat the Dealer! 💵";
            default:
                throw new UnsupportedOperationException();
        }
    }
}
