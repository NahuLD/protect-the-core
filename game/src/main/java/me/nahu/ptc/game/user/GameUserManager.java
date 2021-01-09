package me.nahu.ptc.game.user;

import com.google.common.collect.ImmutableMap;
import me.nahu.ptc.api.team.Team;
import me.nahu.ptc.api.user.User;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public final class GameUserManager {
    private final Map<Team, User> users;

    public GameUserManager() {
        users = new HashMap<>();
    }

    @NotNull
    private User fromBukkitPlayer(@NotNull Player player) {
        return new GameUser(player);
    }

    @NotNull
    public ImmutableMap<Team, User> getUsersMap() {
        return ImmutableMap.copyOf(users);
    }
}
