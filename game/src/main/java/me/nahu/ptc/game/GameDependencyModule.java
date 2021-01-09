package me.nahu.ptc.game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.name.Names;
import me.nahu.ptc.api.ProtectTheCore;
import me.nahu.ptc.game.i18n.LocaleStore;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public final class GameDependencyModule extends AbstractModule {
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .disableHtmlEscaping()
            .create();

    @NotNull
    private final ProtectTheCoreGame protectTheCoreGame;

    public GameDependencyModule(@NotNull ProtectTheCoreGame protectTheCoreGame) {
        this.protectTheCoreGame = protectTheCoreGame;
    }

    @NotNull
    public Injector createInjector() {
        return Guice.createInjector(this);
    }

    @Override
    protected void configure() {
        bind(JavaPlugin.class).toInstance(protectTheCoreGame);
        bind(ProtectTheCore.class).toInstance(protectTheCoreGame);
        bind(File.class).annotatedWith(Names.named("worlds-container"))
                .toInstance(Bukkit.getWorldContainer().getAbsoluteFile());
        bind(File.class).annotatedWith(Names.named("data-folder"))
                .toInstance(protectTheCoreGame.getDataFolder());
        bind(Gson.class).toInstance(gson);
        bind(LocaleStore.class).toInstance(protectTheCoreGame.getLocaleStore());
    }
}
