package com.jitterted.ebp.blackjack.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameOutcomeTest {
    @Test
    void testPlayerBustsAndLoses() {
        Deck fakeDeck =
            new StubDeck(Rank.KING, Rank.FIVE, Rank.SIX, Rank.TEN, Rank.QUEEN);
        Game game = new Game(fakeDeck);
        game.initialDeal();
        game.playerHits();

        assertThat(game.determineOutcome()).isEqualTo(GameOutcome.PLAYER_BUSTED);
    }

    @Test
    void testPlayerBeatsDealerAfterStanding() {
        Deck fakeDeck =
            new StubDeck(Rank.KING, Rank.KING, Rank.SIX, Rank.QUEEN, Rank.FIVE);
        Game game = new Game(fakeDeck);
        game.initialDeal();
        game.playerHits();

        assertThat(game.determineOutcome()).isEqualTo(GameOutcome.PLAYER_BEATS_DEALER);
    }
}
