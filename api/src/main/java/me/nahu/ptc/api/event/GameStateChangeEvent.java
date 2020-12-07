package me.nahu.ptc.api.event;

import me.nahu.ptc.api.GameState;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * This event is fired when the game state changes.
 * @since 0.1.0
 */
public class GameStateChangeEvent extends Event {
    private static final HandlerList HANDLER_LIST = new HandlerList();

    @NotNull
    private final GameState from;
    @NotNull
    private final GameState to;

    public GameStateChangeEvent(@NotNull GameState from, @NotNull GameState to) {
        this.from = from;
        this.to = to;
    }

    /**
     * Last game state.
     * @return Game state.
     */
    @NotNull
    public GameState getFrom() {
        return from;
    }

    /**
     * New game state.
     * @return Game state.
     */
    @NotNull
    public GameState getTo() {
        return to;
    }

    /**
     * Get all handlers that are listening to this event.
     * @return Handler list.
     */
    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    /**
     * Get all handlers that are listening to this event.
     * @return Handler list.
     */
    @NotNull
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}