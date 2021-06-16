package com.jitterted.ebp.blackjack.adapter.in.web;

import com.jitterted.ebp.blackjack.domain.Game;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.function.Supplier;

@Controller
public class BlackjackController {

    //Workaround, best to use a Service
    private final Supplier<Game> gameSupplier;
    private Game game;

    public BlackjackController(Supplier<Game> supplier) {
        this.gameSupplier = supplier;
    }

    @PostMapping("/start-game")
    public String startGame() {
        game = gameSupplier.get();
        game.initialDeal();
        return "redirect:/game";
    }

    @GetMapping("/game")
    public String viewGame(Model model) {
        model.addAttribute("gameView", GameView.of(game));
        return "blackjack";
    }

    @PostMapping("/hit")
    public String hitCommand() {
        game.playerHits();
        if (game.isPlayerDone()) {
            return "redirect:/done";
        }
        return "redirect:/game";
    }

    @GetMapping("/done")
    public String gameDone() {
        return "done";
    }
}
