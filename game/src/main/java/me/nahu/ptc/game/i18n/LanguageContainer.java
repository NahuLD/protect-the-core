package me.nahu.ptc.game.i18n;

import de.themoep.minedown.MineDown;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;

@SuppressWarnings("rawtypes")
public class LanguageContainer<M extends Enum & Translatable> {
    private final Map<M, String> keys = new HashMap<>();

    //TODO: Add Configurator for language file.
    @NotNull
    private final Configuration configuration;

    @NotNull
    private final Locale locale;

    public LanguageContainer(
            @NotNull Locale locale,
            @NotNull Configuration configuration,
            @NotNull LocaleStore<M> localeStore
    ) {
        this.configuration = configuration;
        this.locale = locale;
        var type = localeStore.getMessageKeysClass();
        Stream.of(type.getEnumConstants()).forEach(key -> keys.putIfAbsent(key, configuration.getString(key.getKey())));
    }

    @NotNull
    public Locale getLocale() {
        return locale;
    }

    @NotNull
    public String getValue(@NotNull M key) {
        return keys.getOrDefault(key, "No value found!");
    }

    @NotNull
    public Configuration getConfiguration() {
        return configuration;
    }

    @NotNull
    public Map<M, String> getKeys() {
        return keys;
    }

    @NotNull
    public BaseComponent[] translate(@NotNull M key) {
        return MineDown.parse(getValue(key));
    }

    @NotNull
    public BaseComponent[] translate(@NotNull M key, Object... placeholders) {
        return MineDown.parse(getValue(key), Arrays.stream(placeholders).map(String::valueOf).toArray(String[]::new));
    }
}
