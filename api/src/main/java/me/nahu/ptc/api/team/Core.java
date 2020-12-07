package me.nahu.ptc.api.team;

import com.google.common.base.Preconditions;
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
    int getHealth();

    /**
     * Set the health for the core.
     * @param health Updated amount of health.
     */
    void setHealth(int health);

    /**
     * Damage this core!
     * This method uses {@link #setHealth(int)} and decreases available health by one.
     */
    default void damage() {
        Preconditions.checkArgument(getHealth() > 0, "you cannot damage a broken core");
        setHealth(getHealth() - 1);
    }

    /**
     * Heal this core!
     * This method uses {@link #setHealth(int)} and increases available health by one.
     */
    default void heal() {
        Preconditions.checkArgument(!isDestroyed(), "core must not have been broken");
        setHealth(getHealth() + 1);
    }

    /**
     * Check if the core has already been destroyed.
     * @return Whether the core has been destroyed.
     */
    default boolean isDestroyed() {
        return getHealth() <= 0;
    }
}
