package me.nahu.ptc.game.state;

import com.destroystokyo.paper.Title;
import me.nahu.ptc.api.ProtectTheCore;
import me.nahu.ptc.api.state.GameState;
import me.nahu.ptc.api.user.User;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.jetbrains.annotations.NotNull;

public class CountdownGameStateAction extends LobbyGameStateAction {
    public CountdownGameStateAction(@NotNull ProtectTheCore protectTheCore) {
        super(protectTheCore);
    }

    private int countdown = 30;

    @Override
    protected void onStart() {
        super.onStart();

        runTaskTimer(() -> {
            countdown--;

            if (countdown <= 5) {
                getProtectTheCore().getAllUsers()
                        .stream()
                        .map(User::asBukkitPlayer)
                        .forEach(player -> player.sendTitle(Title.builder().title(String.valueOf(countdown)).build()));
            }

            if (countdown <= 0) {
                getProtectTheCore().setGameState(GameState.PLAYING);
            }
        }, 30);
    }

    @Override
    protected void update() {
        var actionBar = new TextComponent(String.format("Game is starting in %d seconds!", countdown));
        actionBar.setColor(ChatColor.GOLD);

        getProtectTheCore().getAllUsers()
                .stream()
                .map(User::asBukkitPlayer)
                .forEach(player -> player.sendActionBar(actionBar));
    }
}
