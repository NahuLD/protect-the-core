package me.nahu.ptc.game.arena;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.inject.name.Named;
import me.nahu.ptc.game.arena.world.generator.DefaultWorldChunkGenerator;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public final class GameArenaManager {
    private static final Logger LOGGER = Logger.getLogger(GameArenaManager.class.getName());

    @NotNull
    private final ImmutableSet<GameArena> arenas;

    @Inject
    private Gson gson;

    @Inject
    @Named("data-folder")
    private File dataFolder;

    @Inject
    @Named("world-container")
    private File worldContainer;

    public GameArenaManager() {
        this.arenas = loadGameArenas();
    }

    @NotNull
    public ImmutableSet<GameArena> loadGameArenas() {
        var files = Arrays.asList(getWorldsFolder().listFiles());
        if (files.isEmpty()) {
            LOGGER.info("No arenas found, remember to put all files in the '/worlds' folder!");
            return ImmutableSet.of();
        }
        return ImmutableSet.copyOf(files.stream()
                .map(File::getName)
                .map(this::loadGameArena)
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
    }

    @Nullable
    public GameArena loadGameArena(@NotNull String fileName) {
        LOGGER.info("Loading arena from file: " + fileName);
        var zipFile = new ZipFile(new File(getWorldsFolder(), fileName));
        try {
            var fileHeader = zipFile.getFileHeaders().stream()
                    .filter(header -> !header.isDirectory())
                    .filter(header -> header.getFileName().equals("arena.json"))
                    .findFirst();
            if (fileHeader.isEmpty()) {
                LOGGER.warning("No 'arena.json' configuration file found for this arena.");
                return null;
            }
            var inputStream = zipFile.getInputStream(fileHeader.get());
            return gson.fromJson(new JsonReader(new InputStreamReader(inputStream)), GameArena.class);
        } catch (IOException exception) {
            LOGGER.warning("An error occurred while loading map from file: " + fileName);
            return null;
        }
    }

    public @NotNull ImmutableSet<GameArena> getArenas() {
        return arenas;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @NotNull
    private File getWorldsFolder() {
        Preconditions.checkNotNull(dataFolder, "data folder cannot be null!");
        var folder = new File(dataFolder, "worlds");
        if (!folder.exists()) folder.mkdir();
        return folder;
    }
}
