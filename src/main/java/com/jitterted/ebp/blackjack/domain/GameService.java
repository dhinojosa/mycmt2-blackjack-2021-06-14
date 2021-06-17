package com.jitterted.ebp.blackjack.domain;

import java.util.UUID;

public interface GameService {
    public UUID startGame();
    public Game getGameById(UUID uuid);
}
