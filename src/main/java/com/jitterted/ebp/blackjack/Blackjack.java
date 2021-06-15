package com.jitterted.ebp.blackjack;

public class Blackjack {

    // Assembly/Configuration/Bootstrap
    public static void main(String[] args) {
        Game game = new Game(); // Domain object
        ConsoleGame consoleGame = new ConsoleGame(game); // in general: Entities aren't directly passed in to Adapters
        consoleGame.start();
    }

}
