package me.nahu.ptc.api.arena;

import com.google.common.collect.ImmutableCollection;
import me.nahu.ptc.api.team.Core;
import me.nahu.ptc.api.team.Team;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * Protect the core arena class.
 * @since 0.1.0
 */
public interface Arena {
    /**
     * Get the id of the arena.
     * @return Identification number.
     */
    int getId();

    /**
     * Get the name of the arena.
     * @return Arena name.
     */
    @NotNull
    String getName();

    /**
     * Get the world this arena is placed in.
     * @return Arena world.
     */
    @NotNull
    World getWorld();

    /**
     * Get the spawn location for a specific team.
     * @param team Team you want to get the spawn location from.
     * @return Spawn location.
     */
    @NotNull
    Location getSpawnLocation(@NotNull Team team);

    /**
     * Get all spawn locations.
     * @return Immutable collection containing spawn locations.
     */
    @NotNull
    ImmutableCollection<Location> getSpawnLocations();

    /**
     * Get the core entity for a specific team.
     * @param team Team you want to get the core from.
     * @return Core API entity.
     */
    @NotNull
    Core getCore(@NotNull Team team);

    /**
     * Get all cores.
     * @return Immutable collection with core objects.
     */
    @NotNull
    ImmutableCollection<Core> getCores();

    /**
     * Check if the map is valid.
     * @return Whether this arena is valid.
     */
    boolean isValid();
}
