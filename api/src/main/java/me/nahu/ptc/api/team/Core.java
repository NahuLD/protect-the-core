package me.nahu.ptc.api.team;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

/**
 * This is the Core all teams will be targeting to destroy.
 * @since 0.1.0
 */
public interface Core {
    /**
     * Get the Bukkit API Block entity for this core.
     * @return Bukkit API Block.
     */
    @NotNull
    Block getBlock();

    /**
     * Get the location of this Core.
     * @return Location of this core.
     */
    @NotNull
    default Location getLocation() {
        return getBlock().getLocation();
    }

    /**
     * Get the lives this core has left.
     * Once it reaches zero it will be considered destroyed.
     * @return Lives left.
     */
    int getLivesLeft();

    /**
     * Check if the core has already been destroyed.
     * @return Whether the core has been destroyed.
     */
    default boolean isDestroyed() {
        return getLivesLeft() <= 0;
    }
}
