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
    private boolean powerupAssetsLoaded;

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
        loadPersistentAssets();
    }

    /**
     * Load persistent resources
     */
    private void loadPersistentAssets() {
        manager.load(PropManager.AUDIO_THUNDERSTORM, Music.class);
        manager.load(PropManager.TEXTURE_BG_LIGHTNING, Texture.class);
        manager.load(PropManager.TEXTURE_CLOUDS, Texture.class);
        manager.load(PropManager.TEXTURE_PLACEHOLDER, Texture.class);
        manager.load(PropManager.TEXTURE_BUCKET_0, Texture.class);
        manager.load(PropManager.TEXTURE_CITY_10, Texture.class);
        manager.load(PropManager.SUNNY_SKY_PREFIX + 1 + PropManager.PNG, Texture.class);
        manager.finishLoading();
    }
    
    /**
     * Load StartScreen resources
     */
    public void loadStartScreenAssets() {
        manager.load(PropManager.BUTTON_HELP, Texture.class);
        manager.load(PropManager.BUTTON_SOUND_OFF, Texture.class);
        manager.load(PropManager.BUTTON_SOUND_ON, Texture.class);
        manager.load(PropManager.BUTTON_START, Texture.class);
        manager.load(PropManager.BUTTON_UNLOCK, Texture.class);
        manager.load(PropManager.TEXTURE_TEXT_LOGO, Texture.class);
        manager.finishLoading();
    }

    /**
     * Unload StartScreen resources
     */
    public void unloadStartScreenAssets() {
        manager.unload(PropManager.BUTTON_SOUND_OFF);
        manager.unload(PropManager.BUTTON_SOUND_ON);
        manager.unload(PropManager.BUTTON_UNLOCK);
        manager.unload(PropManager.BUTTON_HELP);
        manager.unload(PropManager.BUTTON_START);
        manager.unload(PropManager.TEXTURE_TEXT_LOGO);
        manager.finishLoading();
    }

    /**
     * Load UnlockablesScreen resources
     */
    public void loadUnlockablesScreenAssets() {
        manager.load(PropManager.BUTTON_CLOSE, Texture.class);
        manager.load(PropManager.TEXTURE_FILTRATION_UNLOCK, Texture.class);
        manager.load(PropManager.TEXTURE_HEALTHPACK_UNLOCK, Texture.class);
        manager.load(PropManager.TEXTURE_LOCKED_UNLOCK, Texture.class);
        manager.load(PropManager.TEXTURE_MULTIPLIER_UNLOCK, Texture.class);
        manager.load(PropManager.TEXTURE_PURPLE_RAIN_UNLOCK, Texture.class);
        manager.load(PropManager.TEXTURE_SHIELD_UNLOCK, Texture.class);
        manager.load(PropManager.TEXTURE_TEAMWORK_UNLOCK, Texture.class);
        manager.load(PropManager.TEXTURE_UMBRELLA_UNLOCK, Texture.class);

        manager.finishLoading();
    }

    /**
     * Unload UnlockablesScreen resources
     */
    public void unloadUnlockablesScreenAssets() {
        manager.unload(PropManager.BUTTON_CLOSE);
        manager.unload(PropManager.TEXTURE_FILTRATION_UNLOCK);
        manager.unload(PropManager.TEXTURE_HEALTHPACK_UNLOCK);
        manager.unload(PropManager.TEXTURE_LOCKED_UNLOCK);
        manager.unload(PropManager.TEXTURE_MULTIPLIER_UNLOCK);
        manager.unload(PropManager.TEXTURE_PURPLE_RAIN_UNLOCK);
        manager.unload(PropManager.TEXTURE_SHIELD_UNLOCK);
        manager.unload(PropManager.TEXTURE_TEAMWORK_UNLOCK);
        manager.unload(PropManager.TEXTURE_UMBRELLA_UNLOCK);

        manager.finishLoading();
    }

    /**
     * Load GameplayScreen resources
     */
    public void loadGameplayScreenAssets() {
        manager.load(PropManager.AUDIO_ACID_DROP, Sound.class);
        manager.load(PropManager.AUDIO_RAIN_DROP, Sound.class);
        manager.load(PropManager.AUDIO_SIDE_SPLAT, Sound.class);
        manager.load(PropManager.AUDIO_SIREN, Sound.class);
        manager.load(PropManager.BUTTON_STOP, Texture.class);
        manager.load(PropManager.BUTTON_PAUSE, Texture.class);
        manager.load(PropManager.BUTTON_PLAY, Texture.class);
        manager.load(PropManager.ACID_DROP_PREFIX + 1 + PropManager.PNG, Texture.class);
        manager.load(PropManager.ACID_DROP_PREFIX + 2 + PropManager.PNG, Texture.class);
        manager.load(PropManager.ACID_DROP_PREFIX + 3 + PropManager.PNG, Texture.class);
        manager.load(PropManager.ACID_DROP_PREFIX + 4 + PropManager.PNG, Texture.class);
        manager.load(PropManager.ACID_DROP_PREFIX + 5 + PropManager.PNG, Texture.class);
        manager.load(PropManager.ACID_DROP_PREFIX + 6 + PropManager.PNG, Texture.class);
        manager.load(PropManager.ACID_DROP_PREFIX + 7 + PropManager.PNG, Texture.class);
        manager.load(PropManager.CITY_PREFIX + 1 + PropManager.PNG, Texture.class);
        manager.load(PropManager.CITY_PREFIX + 2 + PropManager.PNG, Texture.class);
        manager.load(PropManager.CITY_PREFIX + 3 + PropManager.PNG, Texture.class);
        manager.load(PropManager.CITY_PREFIX + 4 + PropManager.PNG, Texture.class);
        manager.load(PropManager.CITY_PREFIX + 5 + PropManager.PNG, Texture.class);
        manager.load(PropManager.CITY_PREFIX + 6 + PropManager.PNG, Texture.class);
        manager.load(PropManager.CITY_PREFIX + 7 + PropManager.PNG, Texture.class);
        manager.load(PropManager.CITY_PREFIX + 8 + PropManager.PNG, Texture.class);
        manager.load(PropManager.CITY_PREFIX + 9 + PropManager.PNG, Texture.class);
        manager.load(PropManager.RAIN_DROP_PREFIX + 1 + PropManager.PNG, Texture.class);
        manager.load(PropManager.RAIN_DROP_PREFIX + 2 + PropManager.PNG, Texture.class);
        manager.load(PropManager.RAIN_DROP_PREFIX + 3 + PropManager.PNG, Texture.class);
        manager.load(PropManager.RAIN_DROP_PREFIX + 4 + PropManager.PNG, Texture.class);
        manager.load(PropManager.RAIN_DROP_PREFIX + 5 + PropManager.PNG, Texture.class);
        manager.load(PropManager.RAIN_DROP_PREFIX + 6 + PropManager.PNG, Texture.class);
        manager.load(PropManager.RAIN_DROP_PREFIX + 7 + PropManager.PNG, Texture.class);
        manager.load(PropManager.TEXTURE_ACID_SPLASH, Texture.class);
        manager.load(PropManager.TEXTURE_ACID_SPLASH_LEFT, Texture.class);
        manager.load(PropManager.TEXTURE_ACID_SPLASH_RIGHT, Texture.class);
        manager.load(PropManager.TEXTURE_RAIN_SPLASH, Texture.class);
        manager.load(PropManager.TEXTURE_RAIN_SPLASH_LEFT, Texture.class);
        manager.load(PropManager.TEXTURE_RAIN_SPLASH_RIGHT, Texture.class);

        if (!manager.isLoaded(PropManager.BUCKET_PREFIX + 1 + PropManager.PNG, Texture.class)) {
            manager.load(PropManager.BUCKET_PREFIX + 1 + PropManager.PNG, Texture.class);
            manager.load(PropManager.BUCKET_PREFIX + 2 + PropManager.PNG, Texture.class);
            manager.load(PropManager.BUCKET_PREFIX + 3 + PropManager.PNG, Texture.class);
            manager.load(PropManager.BUCKET_PREFIX + 4 + PropManager.PNG, Texture.class);
            manager.load(PropManager.BUCKET_PREFIX + 5 + PropManager.PNG, Texture.class);
            manager.load(PropManager.BUCKET_PREFIX + 6 + PropManager.PNG, Texture.class);
            manager.load(PropManager.BUCKET_PREFIX + 7 + PropManager.PNG, Texture.class);
            manager.load(PropManager.BUCKET_PREFIX + 8 + PropManager.PNG, Texture.class);
            manager.load(PropManager.BUCKET_PREFIX + 9 + PropManager.PNG, Texture.class);
        }
        
        if (!manager.isLoaded(PropManager.SUNNY_SKY_PREFIX + 2 + PropManager.PNG)) {
            manager.load(PropManager.SUNNY_SKY_PREFIX + 2 + PropManager.PNG, Texture.class);
            manager.load(PropManager.SUNNY_SKY_PREFIX + 3 + PropManager.PNG, Texture.class);
            manager.load(PropManager.SUNNY_SKY_PREFIX + 4 + PropManager.PNG, Texture.class);
            manager.load(PropManager.SUNNY_SKY_PREFIX + 5 + PropManager.PNG, Texture.class);
            manager.load(PropManager.SUNNY_SKY_PREFIX + 6 + PropManager.PNG, Texture.class);
            manager.load(PropManager.SUNNY_SKY_PREFIX + 7 + PropManager.PNG, Texture.class);
            manager.load(PropManager.SUNNY_SKY_PREFIX + 8 + PropManager.PNG, Texture.class);
            manager.load(PropManager.SUNNY_SKY_PREFIX + 9 + PropManager.PNG, Texture.class);
            manager.load(PropManager.SUNNY_SKY_PREFIX + 10 + PropManager.PNG, Texture.class);
        }

        loadGameplayPowerupAssets();
    }

    /**
     * Unload GameplayScreen resources
     */
    public void unloadGameplayScreenAssets() {
        manager.unload(PropManager.AUDIO_ACID_DROP);
        manager.unload(PropManager.AUDIO_RAIN_DROP);
        manager.unload(PropManager.AUDIO_SIDE_SPLAT);
        manager.unload(PropManager.AUDIO_SIREN);
        manager.unload(PropManager.BUTTON_STOP);
        manager.unload(PropManager.BUTTON_PAUSE);
        manager.unload(PropManager.BUTTON_PLAY);
        manager.unload(PropManager.ACID_DROP_PREFIX + 1 + PropManager.PNG);
        manager.unload(PropManager.ACID_DROP_PREFIX + 2 + PropManager.PNG);
        manager.unload(PropManager.ACID_DROP_PREFIX + 3 + PropManager.PNG);
        manager.unload(PropManager.ACID_DROP_PREFIX + 4 + PropManager.PNG);
        manager.unload(PropManager.ACID_DROP_PREFIX + 5 + PropManager.PNG);
        manager.unload(PropManager.ACID_DROP_PREFIX + 6 + PropManager.PNG);
        manager.unload(PropManager.ACID_DROP_PREFIX + 7 + PropManager.PNG);
        manager.unload(PropManager.CITY_PREFIX + 1 + PropManager.PNG);
        manager.unload(PropManager.CITY_PREFIX + 2 + PropManager.PNG);
        manager.unload(PropManager.CITY_PREFIX + 3 + PropManager.PNG);
        manager.unload(PropManager.CITY_PREFIX + 4 + PropManager.PNG);
        manager.unload(PropManager.CITY_PREFIX + 5 + PropManager.PNG);
        manager.unload(PropManager.CITY_PREFIX + 6 + PropManager.PNG);
        manager.unload(PropManager.CITY_PREFIX + 7 + PropManager.PNG);
        manager.unload(PropManager.CITY_PREFIX + 8 + PropManager.PNG);
        manager.unload(PropManager.CITY_PREFIX + 9 + PropManager.PNG);
        manager.unload(PropManager.RAIN_DROP_PREFIX + 1 + PropManager.PNG);
        manager.unload(PropManager.RAIN_DROP_PREFIX + 2 + PropManager.PNG);
        manager.unload(PropManager.RAIN_DROP_PREFIX + 3 + PropManager.PNG);
        manager.unload(PropManager.RAIN_DROP_PREFIX + 4 + PropManager.PNG);
        manager.unload(PropManager.RAIN_DROP_PREFIX + 5 + PropManager.PNG);
        manager.unload(PropManager.RAIN_DROP_PREFIX + 6 + PropManager.PNG);
        manager.unload(PropManager.RAIN_DROP_PREFIX + 7 + PropManager.PNG);
        manager.unload(PropManager.TEXTURE_ACID_SPLASH);
        manager.unload(PropManager.TEXTURE_ACID_SPLASH_LEFT);
        manager.unload(PropManager.TEXTURE_ACID_SPLASH_RIGHT);
        manager.unload(PropManager.TEXTURE_RAIN_SPLASH);
        manager.unload(PropManager.TEXTURE_RAIN_SPLASH_LEFT);
        manager.unload(PropManager.TEXTURE_RAIN_SPLASH_RIGHT);

        unloadGameplayPowerupAssets();
    }

    /**
     * Load Game Over assets
     */
    public void loadGameOverAssets() {
        manager.load(PropManager.AUDIO_GAME_OVER, Sound.class);
        manager.load(PropManager.CITY_PREFIX + 1 + PropManager.PNG, Texture.class);
        manager.load(PropManager.TEXTURE_TEXT_GAME_OVER, Texture.class);
        manager.finishLoading();
    }

    /**
     * Unoad Game Over assets
     */
    public void unloadGameOverAssets() {
        manager.unload(PropManager.AUDIO_GAME_OVER);
        manager.unload(PropManager.CITY_PREFIX + 1 + PropManager.PNG);
        manager.unload(PropManager.TEXTURE_TEXT_GAME_OVER);
        if (manager.isLoaded(PropManager.BUCKET_PREFIX + 1 + PropManager.PNG)) {
            manager.unload(PropManager.BUCKET_PREFIX + 1 + PropManager.PNG);
            manager.unload(PropManager.BUCKET_PREFIX + 2 + PropManager.PNG);
            manager.unload(PropManager.BUCKET_PREFIX + 3 + PropManager.PNG);
            manager.unload(PropManager.BUCKET_PREFIX + 4 + PropManager.PNG);
            manager.unload(PropManager.BUCKET_PREFIX + 5 + PropManager.PNG);
            manager.unload(PropManager.BUCKET_PREFIX + 6 + PropManager.PNG);
            manager.unload(PropManager.BUCKET_PREFIX + 7 + PropManager.PNG);
            manager.unload(PropManager.BUCKET_PREFIX + 8 + PropManager.PNG);
            manager.unload(PropManager.BUCKET_PREFIX + 9 + PropManager.PNG);
        }
        manager.finishLoading();
    }

    /**
     * Load Level Complete Assets
     */
    public void loadLevelCompleteAssets() {
        manager.load(PropManager.AUDIO_BIRDS, Music.class);
        manager.load(PropManager.AUDIO_LEVEL_WIN, Sound.class);
        manager.load(PropManager.AUDIO_THUNDERCRACK, Sound.class);
        manager.load(PropManager.TEXTURE_BUCKET_9, Texture.class);
        manager.load(PropManager.TEXTURE_TEXT_LEVEL_COMPLETE, Texture.class);
        manager.load(PropManager.TEXTURE_TEXT_PERFECT_LEVEL, Texture.class);
        manager.finishLoading();
    }

    /**
     * Unoad Level Complete Assets
     */
    public void unloadLevelCompleteAssets() {
        manager.unload(PropManager.AUDIO_BIRDS);
        manager.unload(PropManager.AUDIO_LEVEL_WIN);
        manager.unload(PropManager.AUDIO_THUNDERCRACK);
        manager.unload(PropManager.TEXTURE_BUCKET_9);
        manager.unload(PropManager.SUNNY_SKY_PREFIX + 2 + PropManager.PNG);
        manager.unload(PropManager.SUNNY_SKY_PREFIX + 3 + PropManager.PNG);
        manager.unload(PropManager.SUNNY_SKY_PREFIX + 4 + PropManager.PNG);
        manager.unload(PropManager.SUNNY_SKY_PREFIX + 5 + PropManager.PNG);
        manager.unload(PropManager.SUNNY_SKY_PREFIX + 6 + PropManager.PNG);
        manager.unload(PropManager.SUNNY_SKY_PREFIX + 7 + PropManager.PNG);
        manager.unload(PropManager.SUNNY_SKY_PREFIX + 8 + PropManager.PNG);
        manager.unload(PropManager.SUNNY_SKY_PREFIX + 9 + PropManager.PNG);
        manager.unload(PropManager.SUNNY_SKY_PREFIX + 10 + PropManager.PNG);
        manager.unload(PropManager.BUCKET_PREFIX + 1 + PropManager.PNG);
        manager.unload(PropManager.BUCKET_PREFIX + 2 + PropManager.PNG);
        manager.unload(PropManager.BUCKET_PREFIX + 3 + PropManager.PNG);
        manager.unload(PropManager.BUCKET_PREFIX + 4 + PropManager.PNG);
        manager.unload(PropManager.BUCKET_PREFIX + 5 + PropManager.PNG);
        manager.unload(PropManager.BUCKET_PREFIX + 6 + PropManager.PNG);
        manager.unload(PropManager.BUCKET_PREFIX + 7 + PropManager.PNG);
        manager.unload(PropManager.BUCKET_PREFIX + 8 + PropManager.PNG);
        manager.unload(PropManager.BUCKET_PREFIX + 9 + PropManager.PNG);
        manager.unload(PropManager.TEXTURE_TEXT_LEVEL_COMPLETE);
        manager.unload(PropManager.TEXTURE_TEXT_PERFECT_LEVEL);
        manager.finishLoading();
    }

    /**
     * Determine if powerup assets are loaded
     */
    public boolean arePowerupAssetsLoaded() { return powerupAssetsLoaded; }

    /**
     * Load UnlockedScreen resources
     */
    public void loadUnlockedScreenAssets() {
        manager.load(PropManager.TEXTURE_SCREEN_UNLOCKED, Texture.class);
        if (GameplayManager.getLevel() == PropManager.UNLOCK_1_LEVEL) { //Load Multiplier assets
            manager.load(PropManager.TEXTURE_MULTIPLIER_BOUNCE, Texture.class);
        }
        else if (GameplayManager.getLevel() == PropManager.UNLOCK_2_LEVEL) { //Load Teamwork assets
            manager.load(PropManager.TEXTURE_TEAMWORK_BOUNCE, Texture.class);
        }
        else if (GameplayManager.getLevel() == PropManager.UNLOCK_3_LEVEL) { //Load HealthPack assets
            manager.load(PropManager.TEXTURE_HEALTHPACK_BOUNCE, Texture.class);
        }
        else if (GameplayManager.getLevel() == PropManager.UNLOCK_4_LEVEL) { //Load Umbrella assets
            manager.load(PropManager.TEXTURE_UMBRELLA_BOUNCE, Texture.class);
        }
        else if (GameplayManager.getLevel() == PropManager.UNLOCK_5_LEVEL) { //Load Purple Rain assets
            manager.load(PropManager.TEXTURE_PURPLE_RAIN_BOUNCE, Texture.class);
        }
        else if (GameplayManager.getLevel() == PropManager.UNLOCK_6_LEVEL) { //Load Shield assets
            manager.load(PropManager.TEXTURE_SHIELD_BOUNCE, Texture.class);
        }
        else if (GameplayManager.getLevel() == PropManager.UNLOCK_7_LEVEL) { //Load Filter assets
            manager.load(PropManager.TEXTURE_FILTRATION_BOUNCE, Texture.class);
        }
        powerupAssetsLoaded = true;
        manager.finishLoading();
    }

    /**
     * Unload UnlockedScreen resources
     */
    public void unloadUnlockedScreenAssets() {
        manager.unload(PropManager.TEXTURE_SCREEN_UNLOCKED);
        if (GameplayManager.getLevel() == PropManager.UNLOCK_1_LEVEL) { //Load Multiplier assets
            manager.unload(PropManager.TEXTURE_MULTIPLIER_BOUNCE);
        }
        else if (GameplayManager.getLevel() == PropManager.UNLOCK_2_LEVEL) { //Load Teamwork assets
            manager.unload(PropManager.TEXTURE_TEAMWORK_BOUNCE);
        }
        else if (GameplayManager.getLevel() == PropManager.UNLOCK_3_LEVEL) { //Load HealthPack assets
            manager.unload(PropManager.TEXTURE_HEALTHPACK_BOUNCE);
        }
        else if (GameplayManager.getLevel() == PropManager.UNLOCK_4_LEVEL) { //Load Umbrella assets
            manager.unload(PropManager.TEXTURE_UMBRELLA_BOUNCE);
        }
        else if (GameplayManager.getLevel() == PropManager.UNLOCK_5_LEVEL) { //Load Purple Rain assets
            manager.unload(PropManager.TEXTURE_PURPLE_RAIN_BOUNCE);
        }
        else if (GameplayManager.getLevel() == PropManager.UNLOCK_6_LEVEL) { //Load Shield assets
            manager.unload(PropManager.TEXTURE_SHIELD_BOUNCE);
        }
        else if (GameplayManager.getLevel() == PropManager.UNLOCK_7_LEVEL) { //Load Filter assets
            manager.unload(PropManager.TEXTURE_FILTRATION_BOUNCE);
        }
        powerupAssetsLoaded = false;
        manager.finishLoading();
    }
    
    

    /**
     * Load gameplay powerups conditionally
     */
    private void loadGameplayPowerupAssets() {
        if (GameplayManager.getLevel() >= PropManager.UNLOCK_1_LEVEL) { //Load Multiplier assets
            manager.load(PropManager.AUDIO_POWERUP, Sound.class);
            manager.load(PropManager.TEXTURE_MULTIPLIER_DROP, Texture.class);
            manager.load(PropManager.POWER_DROP_PREFIX + 2 + PropManager.PNG, Texture.class);
            manager.load(PropManager.POWER_DROP_PREFIX + 3 + PropManager.PNG, Texture.class);
            manager.load(PropManager.POWER_DROP_PREFIX + 4 + PropManager.PNG, Texture.class);
            manager.load(PropManager.POWER_DROP_PREFIX + 5 + PropManager.PNG, Texture.class);
            manager.load(PropManager.POWER_DROP_PREFIX + 6 + PropManager.PNG, Texture.class);
        }
        if (GameplayManager.getLevel() >= PropManager.UNLOCK_2_LEVEL) { //Load Teamwork assets
            manager.load(PropManager.AUDIO_COUNTDOWN, Sound.class);
            manager.load(PropManager.TEXTURE_TEAMWORK_DROP, Texture.class);
            manager.load(PropManager.TEXTURE_TEAMWORK, Texture.class);
        }
        if (GameplayManager.getLevel() >= PropManager.UNLOCK_3_LEVEL) { //Load HealthPack assets
            manager.load(PropManager.TEXTURE_HEALTHPACK_DROP, Texture.class);
        }
        if (GameplayManager.getLevel() >= PropManager.UNLOCK_4_LEVEL) { //Load Umbrella assets
            manager.load(PropManager.TEXTURE_UMBRELLA_DROP, Texture.class);
            manager.load(PropManager.TEXTURE_UMBRELLA_LEFT, Texture.class);
            manager.load(PropManager.TEXTURE_UMBRELLA_RIGHT, Texture.class);
            manager.load(PropManager.AUDIO_UMBRELLA_SPLAT, Sound.class);
        }
        if (GameplayManager.getLevel() >= PropManager.UNLOCK_5_LEVEL) { //Load Purple Rain assets
            manager.load(PropManager.AUDIO_GUITAR, Sound.class);
            manager.load(PropManager.TEXTURE_PURPLE_RAIN, Texture.class);
            manager.load(PropManager.TEXTURE_PURPLE_RAIN_DROP, Texture.class);
            
        }
        if (GameplayManager.getLevel() >= PropManager.UNLOCK_6_LEVEL) { //Load Shield assets
            manager.load(PropManager.TEXTURE_SHIELD, Texture.class);
            manager.load(PropManager.TEXTURE_SHIELD_DROP, Texture.class);
            manager.load(PropManager.AUDIO_SHIELD_SPLAT, Sound.class);
        }
        if (GameplayManager.getLevel() >= PropManager.UNLOCK_7_LEVEL) { //Load Filter assets
            manager.load(PropManager.TEXTURE_FILTRATION_DROP, Texture.class);
        }
        manager.finishLoading();
    }


    /**
     * Unoad gameplay powerups conditionally
     */
    private void unloadGameplayPowerupAssets() {
        if (GameplayManager.getLevel() >= PropManager.UNLOCK_1_LEVEL) { //Load Multiplier assets
            manager.unload(PropManager.AUDIO_POWERUP);
            manager.unload(PropManager.TEXTURE_MULTIPLIER_DROP);
            manager.unload(PropManager.POWER_DROP_PREFIX + 2 + PropManager.PNG);
            manager.unload(PropManager.POWER_DROP_PREFIX + 3 + PropManager.PNG);
            manager.unload(PropManager.POWER_DROP_PREFIX + 4 + PropManager.PNG);
            manager.unload(PropManager.POWER_DROP_PREFIX + 5 + PropManager.PNG);
            manager.unload(PropManager.POWER_DROP_PREFIX + 6 + PropManager.PNG);
        }
        if (GameplayManager.getLevel() >= PropManager.UNLOCK_2_LEVEL) { //Load Teamwork assets
            manager.unload(PropManager.AUDIO_COUNTDOWN);
            manager.unload(PropManager.TEXTURE_TEAMWORK_DROP);
            manager.unload(PropManager.TEXTURE_TEAMWORK);
        }
        if (GameplayManager.getLevel() >= PropManager.UNLOCK_3_LEVEL) { //Load HealthPack assets
            manager.unload(PropManager.TEXTURE_HEALTHPACK_DROP);
        }
        if (GameplayManager.getLevel() >= PropManager.UNLOCK_4_LEVEL) { //Load Umbrella assets
            manager.unload(PropManager.TEXTURE_UMBRELLA_DROP);
            manager.unload(PropManager.TEXTURE_UMBRELLA_LEFT);
            manager.unload(PropManager.TEXTURE_UMBRELLA_RIGHT);
            manager.unload(PropManager.AUDIO_UMBRELLA_SPLAT);
        }
        if (GameplayManager.getLevel() >= PropManager.UNLOCK_5_LEVEL) { //Load Purple Rain assets
            manager.unload(PropManager.AUDIO_GUITAR);
            manager.unload(PropManager.TEXTURE_PURPLE_RAIN);
            manager.unload(PropManager.TEXTURE_PURPLE_RAIN_DROP);

        }
        if (GameplayManager.getLevel() >= PropManager.UNLOCK_6_LEVEL) { //Load Shield assets
            manager.unload(PropManager.TEXTURE_SHIELD);
            manager.unload(PropManager.TEXTURE_SHIELD_DROP);
            manager.unload(PropManager.AUDIO_SHIELD_SPLAT);
        }
        if (GameplayManager.getLevel() >= PropManager.UNLOCK_7_LEVEL) { //Load Filter assets
            manager.unload(PropManager.TEXTURE_FILTRATION_DROP);
        }
        manager.finishLoading();
    }
}
