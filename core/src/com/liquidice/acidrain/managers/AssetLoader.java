package com.liquidice.acidrain.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 * Asset Manager - Manage and asynchronously load all application assets
 */
public class AssetLoader {
    private AssetManager manager;

    /**
     * Retreive the AssetManager
     * @return  The AssetManager
     */
    public AssetManager getManager() { return manager; }

    /**
     * Create a new AssetLoader and load all assets
     */
    public AssetLoader() {
        manager  = new AssetManager();
        loadButtons();
        loadTextures();
        loadSounds();

        manager.finishLoading();
        AudioManager.init(manager);
    }

    /**
     * Load Texture assets
     */
    private void loadTextures() {
        manager.load(PropManager.ACID_DROP_PREFIX + 1 + PropManager.PNG, Texture.class);
        manager.load(PropManager.ACID_DROP_PREFIX + 2 + PropManager.PNG, Texture.class);
        manager.load(PropManager.ACID_DROP_PREFIX + 3 + PropManager.PNG, Texture.class);
        manager.load(PropManager.ACID_DROP_PREFIX + 4 + PropManager.PNG, Texture.class);
        manager.load(PropManager.ACID_DROP_PREFIX + 5 + PropManager.PNG, Texture.class);
        manager.load(PropManager.ACID_DROP_PREFIX + 6 + PropManager.PNG, Texture.class);
        manager.load(PropManager.ACID_DROP_PREFIX + 7 + PropManager.PNG, Texture.class);
        manager.load(PropManager.TEXTURE_ACID_SPLASH, Texture.class);
        manager.load(PropManager.TEXTURE_ACID_SPLASH_LEFT, Texture.class);
        manager.load(PropManager.TEXTURE_ACID_SPLASH_RIGHT, Texture.class);
        manager.load(PropManager.TEXTURE_SHIELD, Texture.class);
        manager.load(PropManager.TEXTURE_BG_STORM, Texture.class);
        manager.load(PropManager.TEXTURE_BG_LIGHTNING, Texture.class);
        manager.load(PropManager.SUNNY_SKY_PREFIX + 1 + PropManager.PNG, Texture.class);
        manager.load(PropManager.SUNNY_SKY_PREFIX + 2 + PropManager.PNG, Texture.class);
        manager.load(PropManager.SUNNY_SKY_PREFIX + 3 + PropManager.PNG, Texture.class);
        manager.load(PropManager.SUNNY_SKY_PREFIX + 4 + PropManager.PNG, Texture.class);
        manager.load(PropManager.SUNNY_SKY_PREFIX + 5 + PropManager.PNG, Texture.class);
        manager.load(PropManager.SUNNY_SKY_PREFIX + 6 + PropManager.PNG, Texture.class);
        manager.load(PropManager.SUNNY_SKY_PREFIX + 7 + PropManager.PNG, Texture.class);
        manager.load(PropManager.SUNNY_SKY_PREFIX + 8 + PropManager.PNG, Texture.class);
        manager.load(PropManager.SUNNY_SKY_PREFIX + 9 + PropManager.PNG, Texture.class);
        manager.load(PropManager.SUNNY_SKY_PREFIX + 10 + PropManager.PNG, Texture.class);
        manager.load(PropManager.BUCKET_PREFIX + 0 + PropManager.PNG, Texture.class);
        manager.load(PropManager.BUCKET_PREFIX + 1 + PropManager.PNG, Texture.class);
        manager.load(PropManager.BUCKET_PREFIX + 2 + PropManager.PNG, Texture.class);
        manager.load(PropManager.BUCKET_PREFIX + 3 + PropManager.PNG, Texture.class);
        manager.load(PropManager.BUCKET_PREFIX + 4 + PropManager.PNG, Texture.class);
        manager.load(PropManager.BUCKET_PREFIX + 5 + PropManager.PNG, Texture.class);
        manager.load(PropManager.BUCKET_PREFIX + 6 + PropManager.PNG, Texture.class);
        manager.load(PropManager.BUCKET_PREFIX + 7 + PropManager.PNG, Texture.class);
        manager.load(PropManager.BUCKET_PREFIX + 8 + PropManager.PNG, Texture.class);
        manager.load(PropManager.BUCKET_PREFIX + 9 + PropManager.PNG, Texture.class);
        manager.load(PropManager.CITY_PREFIX + 1 + PropManager.PNG, Texture.class);
        manager.load(PropManager.CITY_PREFIX + 2 + PropManager.PNG, Texture.class);
        manager.load(PropManager.CITY_PREFIX + 3 + PropManager.PNG, Texture.class);
        manager.load(PropManager.CITY_PREFIX + 4 + PropManager.PNG, Texture.class);
        manager.load(PropManager.CITY_PREFIX + 5 + PropManager.PNG, Texture.class);
        manager.load(PropManager.CITY_PREFIX + 6 + PropManager.PNG, Texture.class);
        manager.load(PropManager.CITY_PREFIX + 7 + PropManager.PNG, Texture.class);
        manager.load(PropManager.CITY_PREFIX + 8 + PropManager.PNG, Texture.class);
        manager.load(PropManager.CITY_PREFIX + 9 + PropManager.PNG, Texture.class);
        manager.load(PropManager.CITY_PREFIX + 10 + PropManager.PNG, Texture.class);
        manager.load(PropManager.TEXTURE_CLOUDS, Texture.class);
        manager.load(PropManager.TEXTURE_PLACEHOLDER, Texture.class);
        manager.load(PropManager.TEXTURE_FILTRATION_DROP, Texture.class);
        manager.load(PropManager.TEXTURE_FILTRATION_UNLOCK, Texture.class);
        manager.load(PropManager.RAIN_DROP_PREFIX + 1 + PropManager.PNG, Texture.class);
        manager.load(PropManager.RAIN_DROP_PREFIX + 2 + PropManager.PNG, Texture.class);
        manager.load(PropManager.RAIN_DROP_PREFIX + 3 + PropManager.PNG, Texture.class);
        manager.load(PropManager.RAIN_DROP_PREFIX + 4 + PropManager.PNG, Texture.class);
        manager.load(PropManager.RAIN_DROP_PREFIX + 5 + PropManager.PNG, Texture.class);
        manager.load(PropManager.RAIN_DROP_PREFIX + 6 + PropManager.PNG, Texture.class);
        manager.load(PropManager.RAIN_DROP_PREFIX + 7 + PropManager.PNG, Texture.class);
        manager.load(PropManager.TEXTURE_RAIN_SPLASH, Texture.class);
        manager.load(PropManager.TEXTURE_RAIN_SPLASH_LEFT, Texture.class);
        manager.load(PropManager.TEXTURE_RAIN_SPLASH_RIGHT, Texture.class);
        manager.load(PropManager.TEXTURE_SCREEN_UNLOCKED, Texture.class);
        manager.load(PropManager.TEXTURE_TEXT_GAME_OVER, Texture.class);
        manager.load(PropManager.TEXTURE_TEXT_LEVEL_COMPLETE, Texture.class);
        manager.load(PropManager.TEXTURE_TEXT_LOGO, Texture.class);
        manager.load(PropManager.TEXTURE_TEXT_PERFECT_LEVEL, Texture.class);
        manager.load(PropManager.TEXTURE_HEALTHPACK_DROP, Texture.class);
        manager.load(PropManager.TEXTURE_HEALTHPACK_UNLOCK, Texture.class);
        manager.load(PropManager.TEXTURE_MULTIPLIER_DROP, Texture.class);
        manager.load(PropManager.TEXTURE_MULTIPLIER_UNLOCK, Texture.class);
        manager.load(PropManager.TEXTURE_SHIELD_UNLOCK, Texture.class);
        manager.load(PropManager.TEXTURE_SHIELD_DROP, Texture.class);
        manager.load(PropManager.TEXTURE_TEAMWORK_DROP, Texture.class);
        manager.load(PropManager.TEXTURE_TEAMWORK_UNLOCK, Texture.class);
        manager.load(PropManager.TEXTURE_TEAMWORK, Texture.class);
        manager.load(PropManager.POWER_DROP_PREFIX + 2 + PropManager.PNG, Texture.class);
        manager.load(PropManager.POWER_DROP_PREFIX + 3 + PropManager.PNG, Texture.class);
        manager.load(PropManager.POWER_DROP_PREFIX + 4 + PropManager.PNG, Texture.class);
        manager.load(PropManager.POWER_DROP_PREFIX + 5 + PropManager.PNG, Texture.class);
        manager.load(PropManager.POWER_DROP_PREFIX + 6 + PropManager.PNG, Texture.class);
        manager.load(PropManager.TEXTURE_UMBRELLA_DROP, Texture.class);
        manager.load(PropManager.TEXTURE_UMBRELLA_LEFT, Texture.class);
        manager.load(PropManager.TEXTURE_UMBRELLA_RIGHT, Texture.class);
        manager.load(PropManager.TEXTURE_UMBRELLA_UNLOCK, Texture.class);
        manager.load(PropManager.TEXTURE_LOCKED_UNLOCK, Texture.class);
    }

    /**
     * Load Button assets
     */
    private void loadButtons() {
        manager.load(PropManager.BUTTON_CLOSE, Texture.class);
        manager.load(PropManager.BUTTON_HELP, Texture.class);
        manager.load(PropManager.BUTTON_PAUSE, Texture.class);
        manager.load(PropManager.BUTTON_PLAY, Texture.class);
        manager.load(PropManager.BUTTON_SOUND_OFF, Texture.class);
        manager.load(PropManager.BUTTON_SOUND_ON, Texture.class);
        manager.load(PropManager.BUTTON_START, Texture.class);
        manager.load(PropManager.BUTTON_STOP, Texture.class);
        manager.load(PropManager.BUTTON_UNLOCK, Texture.class);
    }

    /**
     * Load Sound Assets
     */
    private void loadSounds() {
        manager.load(PropManager.AUDIO_ACID_DROP, Sound.class);
        manager.load(PropManager.AUDIO_BIRDS, Music.class);
        manager.load(PropManager.AUDIO_COUNTDOWN, Sound.class);
        manager.load(PropManager.AUDIO_GAME_OVER, Sound.class);
        manager.load(PropManager.AUDIO_LEVEL_WIN, Sound.class);
        manager.load(PropManager.AUDIO_POWERUP, Sound.class);
        manager.load(PropManager.AUDIO_RAIN_DROP, Sound.class);
        manager.load(PropManager.AUDIO_SHIELD_SPLAT, Sound.class);
        manager.load(PropManager.AUDIO_SIDE_SPLAT, Sound.class);
        manager.load(PropManager.AUDIO_SIREN, Sound.class);
        manager.load(PropManager.AUDIO_THUNDERCRACK, Sound.class);
        manager.load(PropManager.AUDIO_THUNDERSTORM, Music.class);
        manager.load(PropManager.AUDIO_UMBRELLA_SPLAT, Sound.class);
    }
}
