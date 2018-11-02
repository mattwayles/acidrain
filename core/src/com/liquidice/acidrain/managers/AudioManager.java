package com.liquidice.acidrain.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioManager {
    private static Sound acidDrop;
    private static Sound levelWin;
    private static Sound powerup;
    private static Sound rainDrop;
    private static Sound sideSplat;
    private static Sound siren;
    private static Sound thundercrack;
    private static Music birds;
    private static Music thunderstorm;
    
    
    static void init(AssetManager manager) {
        acidDrop = manager.get("sounds/acidDrop.mp3", Sound.class);
        birds = manager.get("sounds/birds.wav", Music.class);
        levelWin = manager.get("sounds/levelWin.mp3", Sound.class);
        powerup = manager.get("sounds/powerup.wav", Sound.class);
        rainDrop = manager.get("sounds/rainDrop.mp3", Sound.class);
        sideSplat = manager.get("sounds/sideSplat.mp3", Sound.class);
        siren = manager.get("sounds/siren.wav", Sound.class);
        thundercrack = manager.get("sounds/thunderCrack.wav", Sound.class);
        thunderstorm = manager.get("sounds/thunderstorm.mp3", Music.class);
    }

    public static void playAcidDrop() {
        if (PreferenceManager.getBoolean("soundOn")) { acidDrop.play(); }
    }

    public static void playLevelWin() {
        if (PreferenceManager.getBoolean("soundOn")) { levelWin.play(); }
    }

    public static void playPowerup() {
        if (PreferenceManager.getBoolean("soundOn")) { powerup.play(); }
    }

    public static void playRainDrop() {
        if (PreferenceManager.getBoolean("soundOn")) { rainDrop.play(); }
    }

    public static void playSideSplat() {
        if (PreferenceManager.getBoolean("soundOn")) { sideSplat.play(); }
    }

    public static void playSiren() {
        if (PreferenceManager.getBoolean("soundOn")) { siren.play(); }
    }

    public static void playThundercrack() {
        if (PreferenceManager.getBoolean("soundOn")) { thundercrack.play(); }
    }

    public static void playBirds() {
        if (PreferenceManager.getBoolean("soundOn")) { birds.play(); }
    }

    public static void stopBirds() {
        birds.stop();
    }

    public static void playThunderstorm() {
        if (PreferenceManager.getBoolean("soundOn")) { thunderstorm.play(); }
    }

    public static void stopThunderstorm() {
        thunderstorm.stop();
    }
    
    
    
    
}
