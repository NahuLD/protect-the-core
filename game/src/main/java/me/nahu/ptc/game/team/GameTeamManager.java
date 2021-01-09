package me.nahu.ptc.game.team;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.inject.name.Named;
import me.nahu.ptc.api.team.Team;
import me.nahu.ptc.game.arena.GameArenaManager;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public final class GameTeamManager {
    private static final Logger LOGGER = Logger.getLogger(GameTeamManager.class.getName());

    @NotNull
    private final ImmutableSet<Team> teams;

    @Inject
    @Named("data-folder")
    private File dataFolder;

    public GameTeamManager() {
        this.teams = loadGameTeams();
    }

    @NotNull
    public Optional<Team> getTeamById(@NotNull String id) {
        return teams.stream()
                .filter(team -> team.getId().equals(id))
                .findFirst();
    }

    @NotNull
    public ImmutableSet<Team> loadGameTeams() {
        var files = Arrays.asList(getTeamsFolder().listFiles());
        if (files.isEmpty()) {
            LOGGER.info("No teams found, remember to put all files in the '/teams' folder!");
            return ImmutableSet.of();
        }
        return ImmutableSet.copyOf(files.stream()
                .map(File::getName)
                .map(this::loadGameTeam)
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
    }

    @Nullable
    public GameTeam loadGameTeam(@NotNull String fileName) {
        var configuration = YamlConfiguration.loadConfiguration(new File(getTeamsFolder(), fileName));

        var id = configuration.getString("id");
        var name = configuration.getString("name");
        var color = Color.fromRGB(configuration.getInt("color"));
        var icon = Material.matchMaterial(Objects.requireNonNull(configuration.getString("icon")));

        if (id == null || name == null || icon == null) {
            LOGGER.warning("Invalid configuration settings for team: " + fileName);
            return null;
        }
        return new GameTeam(id, name, color, icon);
    }

    @NotNull
    public ImmutableSet<Team> getTeams() {
        return teams;
    }

    @NotNull
    private File getTeamsFolder() {
        Preconditions.checkNotNull(dataFolder, "data folder cannot be null!");
        var folder = new File(dataFolder, "teams");
        if (!folder.exists()) folder.mkdir();
        return folder;
    }
}
