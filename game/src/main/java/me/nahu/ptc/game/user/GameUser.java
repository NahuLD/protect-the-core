package me.nahu.ptc.game.user;

import me.nahu.ptc.api.team.Team;
import me.nahu.ptc.api.user.Role;
import me.nahu.ptc.api.user.User;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

public class GameUser implements User {
    private final Player player;
    private Role role;

    public GameUser(@NotNull Player player) {
        this.player = Objects.requireNonNull(player);
    }

    @Override
    public @NotNull UUID getUniqueId() {
        return player.getUniqueId();
    }

    @Override
    public @NotNull String getName() {
        return player.getName();
    }

    @Override
    public @NotNull Player asBukkitPlayer() {
        return player;
    }

    @Override
    public @NotNull OfflinePlayer asOfflinePlayer() {
        return player;
    }

    @Override
    public int getBalance() {
        // TODO: set up balance
        return 0;
    }

    @Override
    public @NotNull Role getRole() {
        return role;
    }

    @Override
    public void setRole(@NotNull Role role) {
        this.role = role;
    }

    @Override
    public @NotNull Team getTeam() {
        return null;
    }
}
