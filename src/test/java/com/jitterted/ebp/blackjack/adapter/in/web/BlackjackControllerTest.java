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
         BlackjackController blackjackController = new BlackjackController(game);
         blackjackController.startGame();
         assertThat(game.playerHand().cards()).hasSize(2);
         assertThat(game.dealerHand().cards()).hasSize(2);
    }

    @Test
    void testThatWeHaveAModelToDisplayCards() {
        Game game = new Game(
            new StubDeck(List.of(new Card(Suit.CLUBS, Rank.QUEEN), new Card(Suit.CLUBS, Rank.THREE),
                new Card(Suit.HEARTS, Rank.KING), new Card(Suit.SPADES, Rank.TEN))));
        BlackjackController blackjackController = new BlackjackController(game);
        blackjackController.startGame();

        Model model = new ConcurrentModel();
        blackjackController.viewGame(model);
        GameView gameView = (GameView) model.getAttribute("gameView");
        assertThat(gameView).isNotNull();
        assertThat(gameView.getDealerCards()).containsExactly("3♣", "10♠");
        assertThat(gameView.getPlayerCards()).containsExactly("Q♣", "K♥");
    }
}
