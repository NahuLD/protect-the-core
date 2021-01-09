package me.nahu.ptc.game.state;

import me.nahu.ptc.api.ProtectTheCore;
import me.nahu.ptc.api.state.GameState;
import me.nahu.ptc.api.state.GameStateAction;
import me.nahu.ptc.api.user.User;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.jetbrains.annotations.NotNull;

public class LobbyGameStateAction extends GameStateAction {
    public LobbyGameStateAction(@NotNull ProtectTheCore protectTheCore) {
        super(protectTheCore);
    }

    @Override
    public GameState getGameState() {
        return GameState.LOBBY;
    }

    @Override
    public void handlePlayer(@NotNull Player player) {
        player.getInventory().clear();
        player.setGameMode(GameMode.ADVENTURE);

        player.setHealth(20.0);
        player.setFoodLevel(20);
        player.setSaturation(20);

        player.setAllowFlight(false);

        player.teleport(getProtectTheCore().getLobbyLocation());
    }

    @Override
    protected void onStart() {
        this.<BlockBreakEvent>listen(event -> event.setCancelled(true));
        this.<BlockPlaceEvent>listen(event -> event.setCancelled(true));
        this.<CreatureSpawnEvent>listen(event -> event.setCancelled(true));
        this.<FoodLevelChangeEvent>listen(event -> event.setCancelled(true));
        this.<PlayerDropItemEvent>listen(event -> event.setCancelled(true));
        this.<EntityDamageEvent>listen(event -> event.setCancelled(true));
    }

    @Override
    protected void onEnd() { }

    @Override
    protected void update() {
        var actionBar = new TextComponent("Waiting for players!");
        actionBar.setColor(ChatColor.GREEN);

        getProtectTheCore().getAllUsers()
                .stream()
                .map(User::asBukkitPlayer)
                .forEach(player -> player.sendActionBar(actionBar));
    }
}
