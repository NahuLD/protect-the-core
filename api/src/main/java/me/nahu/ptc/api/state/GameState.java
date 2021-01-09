package me.nahu.ptc.api.state;

import org.apache.commons.lang.enums.EnumUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * State of the game, will be changed dynamically. Hopefully.
 * @since 0.1.0
 */
public enum GameState {
    LOBBY,
    STARTING,
    PLAYING,
    ENDED
    ;
    private static final Map<GameState, GameStateAction> GAME_ACTIONS = new HashMap<>();

    @NotNull
    public GameState next() {
        var next = values()[this.ordinal() + 1];
        return (next != null) ? next : LOBBY;
    }

    @Nullable
    public GameStateAction getAction() {
        return GAME_ACTIONS.get(this);
    }

    public void stop() {
        if (getAction() == null)
            return;
        getAction().end();
    }

    public void handle() {
        if (getAction() == null)
            return;
        getAction().start();
    }

    public static void registerGameStateAction(@NotNull GameStateAction gameStateAction) {
        GAME_ACTIONS.put(gameStateAction.getGameState(), gameStateAction);
    }

    public static void registerGameStateActions(@NotNull GameStateAction... gameStateActions) {
        Arrays.stream(gameStateActions).forEach(GameState::registerGameStateAction);
    }
}
