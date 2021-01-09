package me.nahu.ptc.game.i18n;

import com.google.common.base.Preconditions;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.configuration.Configuration;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class LocaleStore<M extends Enum & Translatable> {
    private final Map<Locale, LanguageContainer<M>> locales = new HashMap<>();
    private Class<M> messageKeysClass;
    private Locale locale;

    public LocaleStore() {
        this(Locale.ENGLISH);
    }

    public LocaleStore(@NotNull Locale locale) {
        this.locale = locale;
    }

    public void setMessageKeysClass(@NotNull Class<M> messageKeysClass) {
        Preconditions.checkArgument(this.messageKeysClass == null, "the class has already been set!");
        this.messageKeysClass = messageKeysClass;
    }

    @NotNull
    public Class<M> getMessageKeysClass() {
        return messageKeysClass;
    }

    @NotNull
    public LanguageContainer<M> getLanguageContainer(@NotNull Locale locale) {
        Preconditions.checkNotNull(this.messageKeysClass, "class type has not been set!");
        return locales.get(locale);
    }

    public void registerLanguage(@NotNull Locale locale, @NotNull Configuration configuration) {
        Preconditions.checkNotNull(this.messageKeysClass, "class type has not been set!");
        var languageContainer = new LanguageContainer<>(locale, configuration, this);
        locales.putIfAbsent(languageContainer.getLocale(), languageContainer);
    }

    public boolean unregisterLanguage(@NotNull Locale locale) {
        Preconditions.checkNotNull(this.messageKeysClass, "class type has not been set!");
        return locales.remove(locale) != null;
    }

    @NotNull
    public Locale getLocale() {
        return locale;
    }

    public void setLocale(@NotNull Locale locale) {
        this.locale = locale;
    }

    @NotNull
    public BaseComponent[] translate(@NotNull M key) {
        return getLanguageContainer(locale).translate(key);
    }

    @NotNull
    public BaseComponent[] translate(@NotNull M key, Object... placeholders) {
        return getLanguageContainer(locale).translate(key, placeholders);
    }

    @NotNull
    public BaseComponent[] translate(@NotNull M key, @NotNull Locale locale) {
        return getLanguageContainer(locale).translate(key);
    }

    @NotNull
    public BaseComponent[] translate(@NotNull M key, @NotNull Locale locale, Object... placeholders) {
        return getLanguageContainer(locale).translate(key, placeholders);
    }
}
