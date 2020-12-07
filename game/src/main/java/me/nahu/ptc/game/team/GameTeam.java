package me.nahu.ptc.game.team;

import me.nahu.ptc.api.team.Team;
import me.nahu.ptc.api.user.User;
import org.bukkit.Color;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Objects;

public class GameTeam implements Team {
    private final Color color;
    private final Material icon;

    public GameTeam(@NotNull Color color, @NotNull Material material) {
        this.color = Objects.requireNonNull(color);
        this.icon = Objects.requireNonNull(material);
    }

    @Override
    public @NotNull Color getColor() {
        return color;
    }

    @Override
    public @NotNull Material getIcon() {
        return icon;
    }

    @Override
    public @NotNull Collection<User> getMembers() {
        return null;
    }
}
