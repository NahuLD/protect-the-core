package me.nahu.ptc.game.arena;

import me.nahu.ptc.api.arena.Arena;
import me.nahu.ptc.api.team.Core;
import me.nahu.ptc.api.team.Team;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class GameArena implements Arena {
    private final int id;
    private final String name;

    private final World world;

    private final Map<Team, Location> spawnLocations = new HashMap<>();
    private final Map<Team, Core> cores = new HashMap<>();

    public GameArena(int id, @NotNull String name, @NotNull World world) {
        this.id = id;
        this.name = name;
        this.world = world;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public @NotNull World getWorld() {
        return world;
    }

    @Override
    public @NotNull Location getSpawnLocation(@NotNull Team team) {
        return spawnLocations.get(team);
    }

    @Override
    public @NotNull Map<Team, Location> getSpawnLocations() {
        return spawnLocations;
    }

    @Override
    public @NotNull Core getCore(@NotNull Team team) {
        return cores.get(team);
    }

    @Override
    public @NotNull Map<Team, Core> getCores() {
        return cores;
    }
}
