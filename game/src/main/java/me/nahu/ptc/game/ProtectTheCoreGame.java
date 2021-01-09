package me.nahu.ptc.game;

import com.google.common.collect.ImmutableCollection;
import me.nahu.ptc.api.state.GameState;
import me.nahu.ptc.api.ProtectTheCore;
import me.nahu.ptc.api.event.GameStateChangeEvent;
import me.nahu.ptc.api.team.Core;
import me.nahu.ptc.api.team.Team;
import me.nahu.ptc.api.user.User;
import me.nahu.ptc.game.arena.GameArenaManager;
import me.nahu.ptc.game.i18n.LocaleStore;
import me.nahu.ptc.game.state.LobbyGameStateAction;
import me.nahu.ptc.game.team.GameTeamManager;
import me.nahu.ptc.game.user.GameUserManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ProtectTheCoreGame extends JavaPlugin implements ProtectTheCore {
    private GameState gameState = GameState.LOBBY;

    private GameTeamManager teamManager;
    private GameUserManager userManager;
    private GameArenaManager arenaManager;

    private LocaleStore<GameLanguage> localeStore;

    private int minPlayers;
    private int maxPlayers;
    private int coreLives;

    private Material coreMaterial;
    private Material destroyedCoreMaterial;

    private Location lobbyLocation;

    @Override
    public void onEnable() {
        var config = getConfig();
        minPlayers = config.getInt("min-players");
        maxPlayers = config.getInt("max-players");

        coreLives = config.getInt("core-lives");

        coreMaterial = Material.matchMaterial(
                Objects.requireNonNull(config.getString("core-material"), "configuration node 'core-material' cannot be null")
        );
        destroyedCoreMaterial = Material.matchMaterial(
                Objects.requireNonNull(config.getString("destroyed-core-material"), "configuration node 'destroyed-core-material' cannot be null")
        );

        GameState.registerGameStateActions(
                new LobbyGameStateAction(this)
        );
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
        this.gameState.stop();

        Bukkit.getPluginManager().callEvent(
                new GameStateChangeEvent(this.gameState, gameState) // event fired !
        );

        this.gameState = gameState;
        this.gameState.handle();
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
        return lobbyLocation.getWorld();
    }

    @Override
    public @NotNull Location getLobbyLocation() {
        return lobbyLocation;
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
    public @NotNull ImmutableCollection<Team> getTeams() {
        return teamManager.getTeams();
    }

    @Override
    public @NotNull ImmutableCollection<User> getAllUsers() {
        return userManager.getUsersMap().values();
    }

    @Override
    public @NotNull Plugin getPlugin() {
        return this;
    }

    @NotNull
    public LocaleStore<GameLanguage> getLocaleStore() {
        return localeStore;
    }
}
