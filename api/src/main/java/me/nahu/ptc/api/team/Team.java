package me.nahu.ptc.api.team;

import com.google.common.collect.ImmutableCollection;
import me.nahu.ptc.api.user.User;
import org.bukkit.Color;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

/**
 * Protect the Core teams class.
 * @since 0.1.0
 */
public interface Team {
    /**
     * Get the team identifier.
     * @return Team identifier.
     */
    @NotNull
    String getId();

    /**
     * Get the team name.
     * @return Team name.
     */
    String getName();

    /**
     * Get the color that represents this team.
     * @return Color of the team.
     */
    @NotNull
    Color getColor();

    /**
     * Get the icon that represents this team.
     * @return Icon of the team.
     */
    @NotNull
    Material getIcon();

    /**
     * Get the members that have joined the team.
     * @return Collection of users.
     */
    @NotNull
    ImmutableCollection<User> getMembers();

    /**
     * Get the amount of members that have joined the team.
     * @return Amount of members.
     */
    default int getMemberAmount() {
        return getMembers().size();
    }
}
