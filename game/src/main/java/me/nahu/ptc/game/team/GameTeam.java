package me.nahu.ptc.game.team;

import com.google.common.collect.ImmutableCollection;
import me.nahu.ptc.api.team.Team;
import me.nahu.ptc.api.user.User;
import org.bukkit.Color;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class GameTeam implements Team {
    private final String id;
    private final String name;
    private final Color color;
    private final Material icon;

    public GameTeam(@NotNull String id, @NotNull String name, @NotNull Color color, @NotNull Material material) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.color = Objects.requireNonNull(color);
        this.icon = Objects.requireNonNull(material);
    }

    @Override
    public @NotNull String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
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
    public @NotNull ImmutableCollection<User> getMembers() {
        return null;
    }
}
