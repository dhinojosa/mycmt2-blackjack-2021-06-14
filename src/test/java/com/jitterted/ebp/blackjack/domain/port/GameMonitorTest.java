package com.jitterted.ebp.blackjack.domain.port;

import com.jitterted.ebp.blackjack.domain.Game;
import com.jitterted.ebp.blackjack.domain.Rank;
import com.jitterted.ebp.blackjack.domain.StubDeck;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class GameMonitorTest {


    @Test
    public void playerStandsCompletesGameSendsToMonitor() throws Exception {
        // creates the spy based on the interface
        GameMonitor gameMonitorSpy = spy(GameMonitor.class);

        Game game = new Game(gameMonitorSpy);
        game.initialDeal();
        game.playerStands();
        // verify that the roundCompleted method was called with any instance of a Game class
        verify(gameMonitorSpy).roundCompleted(any(Game.class));
    }

    @Test
    public void playerGameBustsSendsToMonitor() throws Exception {
        // creates the spy based on the interface
        GameMonitor gameMonitorSpy = spy(GameMonitor.class);

        StubDeck stubDeck = new StubDeck(Rank.JACK, Rank.FIVE, Rank.SEVEN, Rank.QUEEN, Rank.QUEEN);
        Game game = new Game(stubDeck, gameMonitorSpy);
        game.initialDeal();
        game.playerHits();

        // verify that the roundCompleted method was called with any instance of a Game class
        verify(gameMonitorSpy).roundCompleted(any(Game.class));
    }

    @Test
    public void playerGameBlackjackSendsToMonitor() throws Exception {
        // creates the spy based on the interface
        GameMonitor gameMonitorSpy = spy(GameMonitor.class);

        StubDeck stubDeck = new StubDeck(Rank.KING, Rank.FIVE, Rank.ACE, Rank.QUEEN);
        Game game = new Game(stubDeck, gameMonitorSpy);
        game.initialDeal();

        // verify that the roundCompleted method was called with any instance of a Game class
        verify(gameMonitorSpy).roundCompleted(any(Game.class));
    }

    @Test
    public void playerGameDoesNotBustNoSendsToMonitor() throws Exception {
        // creates the spy based on the interface
        GameMonitor gameMonitorSpy = spy(GameMonitor.class);

        StubDeck stubDeck = new StubDeck(Rank.JACK, Rank.FIVE, Rank.SEVEN, Rank.QUEEN, Rank.TWO);
        Game game = new Game(stubDeck, gameMonitorSpy);
        game.initialDeal();
        game.playerHits();

        // verify that the roundCompleted method was called with any instance of a Game class
        verify(gameMonitorSpy, never()).roundCompleted(any());
    }
}
