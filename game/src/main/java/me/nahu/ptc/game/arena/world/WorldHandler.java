package me.nahu.ptc.game.arena.world;

import com.google.common.base.Preconditions;
import com.google.inject.name.Named;
import me.nahu.ptc.game.arena.GameArena;
import me.nahu.ptc.game.arena.world.generator.DefaultWorldChunkGenerator;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.util.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.io.File;
import java.util.Objects;
import java.util.logging.Logger;

public class WorldHandler {
    private static final Logger LOGGER = Logger.getLogger(WorldHandler.class.getName());

    @Inject
    @Named("world-container")
    private File worldContainer;

    @NotNull
    public World loadWorld(@NotNull File worldsFolder, @NotNull String worldFileName, @NotNull String worldName) throws Exception {
        var worldFolder = new File(worldContainer, worldFileName);
        try {
            var zipFile = new ZipFile(new File(worldsFolder, worldFileName));
            zipFile.extractAll(worldFolder.getAbsolutePath());
            LOGGER.info("Extracting world files from zip file.");
            if (!new File(worldFolder, "uid.dat").delete()) {
                LOGGER.info("No 'uid.dat' file found for deletion, this may cause some issues.");
            }
        } catch (ZipException exception) {
            exception.printStackTrace();
            throw new Exception("Could not unzip world!");
        }

        var worldCreator = new WorldCreator(worldName);
        worldCreator.environment(World.Environment.NORMAL);
        worldCreator.generateStructures(false);
        worldCreator.type(WorldType.FLAT); // might change this in the future.
        worldCreator.generator(new DefaultWorldChunkGenerator());
        worldCreator.generatorSettings("");

        var world = Objects.requireNonNull(worldCreator.createWorld(), "world cannot be null!");
        world.setAutoSave(false);
        return world;
    }

    public void unloadWorld(@NotNull String worldName, @NotNull String worldFileName) {
        LOGGER.info("Unloading world: " + worldName);
        Bukkit.unloadWorld(worldName, false);
        if (!new File(worldContainer, worldFileName).delete()) {
            LOGGER.warning("Could not delete world file '" + worldFileName + "', manual action may be required");
        }
    }
}
