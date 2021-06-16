package com.jitterted.ebp.blackjack.adapter.in.web;

import com.jitterted.ebp.blackjack.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackjackControllerTest {

    @Test
    void testInitialCardsAreDealt() {
        Game game = new Game();
        BlackjackController blackjackController =
            new BlackjackController(() -> game);
        blackjackController.startGame();
        assertThat(game.playerHand().cards()).hasSize(2);
        assertThat(game.dealerHand().cards()).hasSize(2);
    }

    @Test
    void testThatWeHaveAModelToDisplayCards() {
        Game game = new Game(
            new StubDeck(List.of(new Card(Suit.CLUBS, Rank.QUEEN),
                new Card(Suit.CLUBS, Rank.THREE),
                new Card(Suit.HEARTS, Rank.KING), new Card(Suit.SPADES,
                    Rank.TEN))));
        BlackjackController blackjackController =
            new BlackjackController(() -> game);
        blackjackController.startGame();

        Model model = new ConcurrentModel();
        blackjackController.viewGame(model);
        GameView gameView = (GameView) model.getAttribute("gameView");
        assertThat(gameView).isNotNull();
        assertThat(gameView.getDealerCards()).containsExactly("3♣", "10♠");
        assertThat(gameView.getPlayerCards()).containsExactly("Q♣", "K♥");
    }

    @Test
    void testPlayerHitsAndWeGetThreeCards() {
        Game game = new Game();
        BlackjackController blackjackController =
            new BlackjackController(() -> game);
        blackjackController.startGame();
        blackjackController.hitCommand();
        assertThat(game.playerHand().cards()).hasSize(3);
    }

    @Test
    void testThatWeAreDoneWhenPlayerBusts() {
        Game game = new Game(
            new StubDeck(
                List.of(
                    new Card(Suit.CLUBS, Rank.QUEEN),
                    new Card(Suit.CLUBS, Rank.THREE),
                    new Card(Suit.HEARTS, Rank.KING),
                    new Card(Suit.SPADES, Rank.TEN),
                    new Card(Suit.HEARTS, Rank.TEN))));

        BlackjackController blackjackController =
            new BlackjackController(() -> game);
        blackjackController.startGame();

        String result = blackjackController.hitCommand();
        assertThat(result).isEqualTo("redirect:/done");
    }


    @Test
    void testWhenDoneIsCalled() {
        Game game = new Game();
        BlackjackController blackjackController =
            new BlackjackController(() -> game);
        blackjackController.startGame();
        Model model = new ConcurrentModel();
//        blackjackController.gameDone(model);
//        GameView gameView = (GameView) model.getAttribute("gameView");
//        assertThat(gameView).isNotNull();
//        assertThat(gameView.getDealerCards()).containsExactly("3♣", "10♠");
//        assertThat(gameView.getPlayerCards()).containsExactly("Q♣", "K♥");
//        String outcome = (String) model.getAttribute("outcome");
//        assertThat(outcome).isEqualTo("PLAYER_WINS");

    }
}
