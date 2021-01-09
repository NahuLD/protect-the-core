package me.nahu.ptc.api.state;

import me.nahu.ptc.api.ProtectTheCore;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

/**
 * This object will be used to manage the actions that each {@link GameState} will have to manage.
 * @since 0.1.0
 */
public abstract class GameStateAction {
    @NotNull
    private final ProtectTheCore protectTheCore;

    private final Set<BukkitTask> tasks = new HashSet<>();
    private final Set<Listener> listeners = new HashSet<>();

    private boolean working = false;

    public GameStateAction(@NotNull ProtectTheCore protectTheCore) {
        this.protectTheCore = protectTheCore;
    }

    /**
     * Start this game state action.
     */
    public void start() {
        onStart();
        runTaskTimer(this::update);
        working = true;
    }

    /**
     * End this game state action.
     */
    public void end() {
        if (!working)
            return;

        working = false;

        tasks.forEach(BukkitTask::cancel);
        tasks.clear();

        listeners.forEach(HandlerList::unregisterAll);
        listeners.clear();
        onEnd();
    }

    /**
     * Handle a new player joining the game.
     * @param player Player that joins the game.
     */
    public abstract void handlePlayer(@NotNull Player player);

    /**
     * Get the {@link GameState} this action is intended for.
     * There must only be one per state.
     * @return Game state.
     */
    public abstract GameState getGameState();

    /**
     * Override this void to apply the actions you want to do when this action starts.
     */
    protected abstract void onStart();

    /**
     * Override this void to apply the actions you want to do when this action ends.
     */
    protected abstract void onEnd();

    /**
     * Use this if you want to perform tasks once every second.
     * This is automatically called every second.
     */
    protected abstract void update();

    /**
     * Register a listen without having to use ugly anonymous classes.
     * @param event Event you want to listen.
     * @param <E> Type of the event.
     */
    protected <E extends Event> void listen(ListenerWrapper<E> event) {
        protectTheCore.getPlugin()
                .getServer()
                .getPluginManager()
                .registerEvents(event, protectTheCore.getPlugin()); // thanks bukkit
        listeners.add(event);
    }

    /**
     * Run a task timer with an unlimited amount of repetitions (at least while this action is active) every second.
     * @param runnable Things you want to run.
     */
    protected void runTaskTimer(@NotNull Runnable runnable) {
        runTaskTimer(runnable, -1);
    }

    /**
     * Run a task timer with a specified amount of repetitions (at least while this action is active) every second.
     * @param runnable Things you want to run.
     * @param repetitions Amount of times you want this action to be performed.
     */
    protected void runTaskTimer(@NotNull Runnable runnable, int repetitions) {
        runTaskTimer(runnable, repetitions, 0L, 20L);
    }

    /**
     * Run a task timer with a specified amount of repetitions (at least while this action is active)
     * for every specified interval.
     * @param runnable Things you want to run.
     * @param repetitions Amount of times you want this action to be performed.
     * @param interval Interval of time in-between.
     */
    protected void runTaskTimer(@NotNull Runnable runnable, int repetitions, long interval) {
        runTaskTimer(runnable, repetitions, 0L, interval);
    }

    /**
     * Run a task timer with a specified amount of repetitions (at least while this action is active)
     * for every specified interval. Delay it as much time as you want.
     * @param runnable Things you want to run.
     * @param repetitions Amount of times you want this action to be performed.
     * @param delay Delay of time for this to be performed.
     * @param interval Interval of time in-between.
     */
    protected void runTaskTimer(@NotNull Runnable runnable, int repetitions, long delay, long interval) {
        var bukkitRunnable = new BukkitRunnable() {
            private int repeated = 0;
            @Override
            public void run() {
                if (repetitions <= -1)
                    return;
                if (repeated >= repetitions) {
                    cancel();
                    return;
                }
                runnable.run();
                repeated++;
            }
        };
        tasks.add(
            bukkitRunnable.runTaskTimer(protectTheCore.getPlugin(), delay, interval)
        );
    }

    /**
     * Get Protect The Core game interface.
     * @return Game interface.
     */
    @NotNull
    protected ProtectTheCore getProtectTheCore() {
        return protectTheCore;
    }

    /**
     * Listener wrapper so you can avoid using anonymous ugly classes.
     * @param <E> Type of event.
     */
    @FunctionalInterface
    public interface ListenerWrapper<E extends Event> extends Listener {
        void onEvent(E event);
    }
}
