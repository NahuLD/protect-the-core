package me.nahu.ptc.game.listeners;

import me.nahu.ptc.api.state.GameState;
import me.nahu.ptc.api.ProtectTheCore;
import me.nahu.ptc.api.event.GameStateChangeEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class GameListener implements Listener {
    private ProtectTheCore protectTheCore;

    public GameListener(@NotNull ProtectTheCore protectTheCore) {
        this.protectTheCore = protectTheCore;
    }

    @EventHandler
    public void onGameStateChange(@NotNull GameStateChangeEvent event) {
        if (event.getTo() != GameState.STARTING) return;

        protectTheCore.getAllUsers().forEach(user -> user.asBukkitPlayer().teleport(protectTheCore.getLobbyLocation()));
    }
}
