package com.liquidice.acidrain.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Manage audio files by playing only if user preference 'soundOn' is TRUE
 */
public class AudioManager {
    //All app sound/music files
    private static Sound acidDrop;
    private static Sound countdown;
    private static Sound gameOver;
    private static Sound levelWin;
    private static Sound powerup;
    private static Sound rainDrop;
    private static Sound sideSplat;
    private static Sound shieldSplat;
    private static Sound siren;
    private static Sound thundercrack;
    private static Sound umbrellaSplat;
    private static Music birds;
    private static Music thunderstorm;

    /**
     * Initialize default files for each sound asset
     * @param manager   The AssetManager containing the sounds used by this class
     */
    static void init(AssetManager manager) {
        acidDrop = manager.get(PropManager.AUDIO_ACID_DROP, Sound.class);
        birds = manager.get(PropManager.AUDIO_BIRDS, Music.class);
        countdown = manager.get(PropManager.AUDIO_COUNTDOWN, Sound.class);
        gameOver = manager.get(PropManager.AUDIO_GAME_OVER, Sound.class);
        levelWin = manager.get(PropManager.AUDIO_LEVEL_WIN, Sound.class);
        powerup = manager.get(PropManager.AUDIO_POWERUP, Sound.class);
        rainDrop = manager.get(PropManager.AUDIO_RAIN_DROP, Sound.class);
        sideSplat = manager.get(PropManager.AUDIO_SIDE_SPLAT, Sound.class);
        shieldSplat = manager.get(PropManager.AUDIO_SHIELD_SPLAT, Sound.class);
        siren = manager.get(PropManager.AUDIO_SIREN, Sound.class);
        thundercrack = manager.get(PropManager.AUDIO_THUNDERCRACK, Sound.class);
        thunderstorm = manager.get(PropManager.AUDIO_THUNDERSTORM, Music.class);
        umbrellaSplat = manager.get(PropManager.AUDIO_UMBRELLA_SPLAT, Sound.class);
    }

    /**
     * Play the AcidDrop sound when an Acid Drop is caught
     */
    public static void playAcidDrop() {
        if (PreferenceManager.getBoolean(PropManager.PREF_SOUND_ON, true)) { acidDrop.play(); }
    }

    /**
     * Play the Countdown sound when an a countdown is occurring
     */
   static void playCountdown() {
        if (PreferenceManager.getBoolean(PropManager.PREF_SOUND_ON, true)) { countdown.play(); }
    }

    /**
     * Play the Game Over sound when game over
     */
    public static void playGameOver() {
        if (PreferenceManager.getBoolean(PropManager.PREF_SOUND_ON, true)) { gameOver.play(); }
    }

    /**
     * Play the Powerup sound when a powerup drop is caught
     */
    public static void playPowerup() {
        if (PreferenceManager.getBoolean(PropManager.PREF_SOUND_ON, true)) { powerup.play(); }
    }

    /**
     * Play the RainDrop sound when a Rain Drop is caught
     */
    public static void playRainDrop() {
        if (PreferenceManager.getBoolean(PropManager.PREF_SOUND_ON, true)) { rainDrop.play(); }
    }

    /**
     * Play the SideSplat sound when a Drop is smashed on the bucket
     */
    public static void playSideSplat() {
        if (PreferenceManager.getBoolean(PropManager.PREF_SOUND_ON, true)) { sideSplat.play(); }
    }

    /**
     * Play the ShieldSplat sound when a Drop is smashed on the shield
     */
    public static void playShieldSplat() {
        if (PreferenceManager.getBoolean(PropManager.PREF_SOUND_ON, true)) { shieldSplat.play(); }
    }

    /**
     * Play the Level Win sound when a level is completed
     */
    public static void playLevelWin() {
        if (PreferenceManager.getBoolean(PropManager.PREF_SOUND_ON, true)) { levelWin.play(); }
    }

    /**
     * Play the Siren sound when the ciy strength drops to warning levels
     */
    public static void playSiren() {
        if (PreferenceManager.getBoolean(PropManager.PREF_SOUND_ON, true)) { siren.play(); }
    }

    /**
     * Stop the Siren sound at level complete or game over
     */
    public static void stopSiren() {
        if (PreferenceManager.getBoolean(PropManager.PREF_SOUND_ON, true)) { siren.stop(); }
    }

    /**
     * Play the Thundercrack sound when a new level is started
     */
    public static void playThundercrack() {
        if (PreferenceManager.getBoolean(PropManager.PREF_SOUND_ON, true)) { thundercrack.play(); }
    }

    /**
     * Play the umbrella splat sound a drop lands on an umbrella
     */
    public static void playUmbrellaSplat() {
        if (PreferenceManager.getBoolean(PropManager.PREF_SOUND_ON, true)) { umbrellaSplat.play(); }
    }

    /**
     * Play Birds music on the Level Complete page
     */
    public static void playBirds() {
        if (PreferenceManager.getBoolean(PropManager.PREF_SOUND_ON, true)) {
            birds.play();
        birds.setLooping(true);
        }
    }

    /**
     * Stop Birds music when a new level begins
     */
    public static void stopBirds() {
        birds.stop();
    }

    /**
     * Play Thunderstorm as background music during gameplay
     */
    public static void playThunderstorm() {
        if (PreferenceManager.getBoolean(PropManager.PREF_SOUND_ON, true)) {
            thunderstorm.play();
            thunderstorm.setLooping(true);}
    }

    /**
     * Stop Thunderstorm when a level is completed
     */
    public static void stopThunderstorm() {
        thunderstorm.stop();
    }
}
