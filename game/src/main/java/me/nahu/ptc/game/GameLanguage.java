package me.nahu.ptc.game;

import me.nahu.ptc.game.i18n.Translatable;
import org.jetbrains.annotations.NotNull;

public enum GameLanguage implements Translatable {

    ;

    private final String key;

    GameLanguage(@NotNull String key) {
        this.key = key;
    }

    @Override
    public @NotNull String getKey() {
        return key;
    }
}
