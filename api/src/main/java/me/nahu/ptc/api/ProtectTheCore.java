package me.nahu.ptc.api;

import me.nahu.ptc.api.event.GameStateChangeEvent;
import me.nahu.ptc.api.team.Core;
import me.nahu.ptc.api.team.Team;
import me.nahu.ptc.api.user.User;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Optional;

/**
 * Protect the Core main interface. From here we'll manage all things related to the game.
 * @since 0.1.0
 * @version 0.1.0
 */
public interface ProtectTheCore {
    /**
     * Get the game state.
     * @return Game state.
     */
    @NotNull
    GameState getGameState();

    /**
     * Set a new game state, this will fire {@link GameStateChangeEvent}.
     * @param gameState New game state.
     */
    void setGameState(@NotNull GameState gameState);

    /**
     * Get the minimum amount of players needed to start the game.
     * @return Minimum amount of players.
     */
    int getMinPlayers();

    /**
     * Set the minimum amount of players needed to start the game.
     * @param minPlayers Updated minimum amount.
     */
    void setMinPlayers(int minPlayers);

    /**
     * Get the maximum amount of players that may join the game.
     * @return Maximum amount of players.
     */
    int getMaxPlayers();

    /**
     * Set the maximum amount of players that may join the game.
     * @param maxPlayers Updated maximum amount.
     */
    void setMaxPlayers(int maxPlayers);

    /**
     * Abort the game. No team will be assigned as the winner.
     */
    void abort();

    /**
     * Start the game.
     */
    void start();

    /**
     * Stop the game.
     */
    void stop();

    /**
     * Get the world the game will be played in.
     * @return Game world.
     */
    @NotNull
    World getGameWorld();

    /**
     * Set the material the core will use.
     * @param type Updated material type.
     */
    void setCoreMaterial(@NotNull Material type);

    /**
     * Set the material that the destroyed core will use.
     * @param type Updated material type.
     */
    void setDestroyedCoreMaterial(@NotNull Material type);

    /**
     * Set the amount of lives every core will start with.
     * @param lives Updated amount of lives.
     */
    void setCoreLives(int lives);

    /**
     * Get a core block, it'll take any block and return an optional.
     * If it's not a core block, the optional will be empty. Filled otherwise.
     * @param block Block to check on.
     * @return Optional with the core block.
     */
    @NotNull
    Optional<Core> getCoreBlock(@NotNull Block block);

    /**
     * Get the teams that will partake in the game.
     * @return Collection of teams.
     */
    @NotNull
    Collection<Team> getTeams();

    /**
     * Get all users on the game.
     * @return All users.
     */
    @NotNull
    Collection<User> getAllUsers();
}
