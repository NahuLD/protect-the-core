package me.nahu.ptc.api.user;

import me.nahu.ptc.api.team.Team;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Protect the Core API User entity.
 * @since 0.1.0
 */
public interface User {
    /**
     * Get the Bukkit UUID for this user.
     * @return Unique id.
     */
    @NotNull
    UUID getUniqueId();

    /**
     * Get the name for this user.
     * @return User name.
     */
    @NotNull
    String getName();

    /**
     * Get this user as a Bukkit player entity.
     * @return Bukkit API player.
     */
    @NotNull
    Player asBukkitPlayer();

    /**
     * Get this user as a Bukkit offline player entity.
     * @return Bukkit API offline player.
     */
    @NotNull
    OfflinePlayer asOfflinePlayer();

    /**
     * Get the balance of this user.
     * @return Balance.
     */
    int getBalance();

    /**
     * Get the role this user will take in the game.
     * @return Role of the user.
     */
    @NotNull
    Role getRole();

    /**
     * Set the role this user will take in the game.
     * @param role Role the user will take.
     */
    void setRole(@NotNull Role role);

    /**
     * Get the team this player has chosen. Might be assigned randomly if none is chosen.
     * @return Team the user has been assigned to.
     */
    @NotNull
    Team getTeam();
}
