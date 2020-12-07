package me.nahu.ptc.game;

import me.nahu.ptc.api.GameState;
import me.nahu.ptc.api.ProtectTheCore;
import me.nahu.ptc.api.event.GameStateChangeEvent;
import me.nahu.ptc.api.team.Core;
import me.nahu.ptc.api.team.Team;
import me.nahu.ptc.api.user.User;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ProtectTheCoreGame extends JavaPlugin implements ProtectTheCore {
    private GameState gameState = GameState.LOBBY;

    private int minPlayers;
    private int maxPlayers;
    private int coreLives;

    private Material coreMaterial;
    private Material destroyedCoreMaterial;

    private Set<Team> teams = new HashSet<>();

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    @Override
    public @NotNull GameState getGameState() {
        return gameState;
    }

    @Override
    public void setGameState(@NotNull GameState gameState) {
        Bukkit.getPluginManager().callEvent(
                new GameStateChangeEvent(this.gameState, gameState) // event fired !
        );
        this.gameState = gameState;
    }

    @Override
    public int getMinPlayers() {
        return minPlayers;
    }

    @Override
    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    @Override
    public int getMaxPlayers() {
        return maxPlayers;
    }

    @Override
    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    @Override
    public void abort() {
        setGameState(GameState.ENDED);
    }

    @Override
    public void start() {
        setGameState(GameState.STARTING);
    }

    @Override
    public void stop() {
        setGameState(GameState.ENDED);
    }

    @Override
    public @NotNull World getGameWorld() {
        return null;
    }

    @Override
    public void setCoreMaterial(@NotNull Material type) {
        this.coreMaterial = type;
    }

    @Override
    public void setDestroyedCoreMaterial(@NotNull Material type) {
        this.destroyedCoreMaterial = type;
    }

    @Override
    public int getCoreLives() {
        return coreLives;
    }

    @Override
    public void setCoreLives(int lives) {
        this.coreLives = lives;
    }

    @Override
    public @NotNull Optional<Core> getCoreBlock(@NotNull Block block) {
        return Optional.empty();
    }

    @Override
    public @NotNull Collection<Team> getTeams() {
        return teams;
    }

    @Override
    public @NotNull Collection<User> getAllUsers() {
        return null;
    }
}
