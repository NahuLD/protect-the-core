package me.nahu.ptc.game.arena;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import com.google.gson.annotations.Expose;
import me.nahu.ptc.api.arena.Arena;
import me.nahu.ptc.api.team.Core;
import me.nahu.ptc.api.team.Team;
import me.nahu.ptc.api.utilities.LazyValue;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GameArena implements Arena {
    private final GameArenaManager gameArenaManager;

    private final int id;
    private final String name;
    private final String worldName;
    private final String worldFileName;

    @Expose(deserialize = false, serialize = false)
    private final LazyValue<World> world;

    public GameArena(
            int id,
            @NotNull String name,
            @NotNull String worldName,
            @NotNull String worldFileName,
            @NotNull GameArenaManager gameArenaManager
    ) {
        this.id = id;
        this.name = name;
        this.worldName = worldName;
        this.worldFileName = worldFileName;
        this.gameArenaManager = gameArenaManager;
        this.world = new LazyValue<>(this::loadWorld);
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
        return world.getValue();
    }

    @Override
    public @NotNull Location getSpawnLocation(@NotNull Team team) {
        return null;
    }

    @Override
    public @NotNull ImmutableCollection<Location> getSpawnLocations() {
        return null;
    }

    @Override
    public @NotNull Core getCore(@NotNull Team team) {
        return null;
    }

    @Override
    public @NotNull ImmutableCollection<Core> getCores() {
        return null;
    }

    @Override
    public boolean isValid() {
        //TODO verify integrity
        return false;
    }

    @Nullable
    public World loadWorld() {
        try {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public @NotNull String getWorldName() {
        return worldName;
    }

    public @NotNull String getWorldFileName() {
        return worldFileName;
    }
}
