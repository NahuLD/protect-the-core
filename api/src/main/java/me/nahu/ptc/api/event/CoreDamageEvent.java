package me.nahu.ptc.api.event;

import me.nahu.ptc.api.team.Core;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * This event is fired when the core is damaged.
 * @since 0.1.0
 */
public class CoreDamageEvent extends Event {
    private static final HandlerList HANDLER_LIST = new HandlerList();

    @NotNull
    private final Core core;

    public CoreDamageEvent(@NotNull Core core) {
        this.core = core;
    }

    /**
     * Get the core that is being destroyed.
     * @return Core API entity.
     */
    @NotNull
    public Core getCore() {
        return core;
    }

    /**
     * Get the lives left the core was left with.
     * @return Lives left.
     */
    public int getHealth() {
        return core.getHealth();
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
