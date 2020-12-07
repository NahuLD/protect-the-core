package me.nahu.ptc.game.team;

import com.google.common.base.Preconditions;
import me.nahu.ptc.api.event.CoreDamageEvent;
import me.nahu.ptc.api.team.Core;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class GameCore implements Core {
    private final Block block;
    private int health;

    public GameCore(@NotNull Block block) {
        this.block = Objects.requireNonNull(block);
    }

    @Override
    public @NotNull Block getBlock() {
        return block;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        Preconditions.checkArgument(health >= 0, "you cannot set a negative amount");
        this.health = health;
        Bukkit.getPluginManager().callEvent(
                new CoreDamageEvent(this) // fire event !
        );
    }
}
