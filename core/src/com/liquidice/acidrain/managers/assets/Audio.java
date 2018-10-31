package com.liquidice.acidrain.managers.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.liquidice.acidrain.AcidRain;

public class Audio {
    private static Music backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/thunderstorm.mp3"));
    private static Music birdsMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/birds.wav"));
    private static Sound rainDropSound = Gdx.audio.newSound(Gdx.files.internal("sounds/rainDrop.mp3"));
    private static Sound acidDropSound = Gdx.audio.newSound(Gdx.files.internal("sounds/acidDrop.mp3"));
    private static Sound levelWinSound = Gdx.audio.newSound(Gdx.files.internal("sounds/levelWin.mp3"));
    private static Sound sideSplatSound = Gdx.audio.newSound(Gdx.files.internal("sounds/sideSplat.mp3"));
    private static Sound sirenSound = Gdx.audio.newSound(Gdx.files.internal("sounds/siren.wav"));
    private static Sound thunderSound = Gdx.audio.newSound(Gdx.files.internal("sounds/thunderCrack.wav"));
    private static Sound powerupSound = Gdx.audio.newSound(Gdx.files.internal("sounds/powerup.wav"));

    public static Music getBackgroundMusic() { return backgroundMusic; }
    public static Music getBirdsMusic() { return birdsMusic; }

    public static void playMusic() {
        if (AcidRain.getPreferences().getBoolean("soundOn")) {
            backgroundMusic.play();
            backgroundMusic.setLooping(true);
        }
    }

    public static void stopMusic() {
        if (!AcidRain.getPreferences().getBoolean("soundOn")) {
            backgroundMusic.stop();
        }
    }

    public static void playBirds() {
        if (AcidRain.getPreferences().getBoolean("soundOn")) {
            birdsMusic.play();
            birdsMusic.setLooping(true);
        }
    }
    public static void playRainDropSound() {
        if (AcidRain.getPreferences().getBoolean("soundOn")) {
            rainDropSound.play();
        } }
    public static void playAcidDropSound() {
        if (AcidRain.getPreferences().getBoolean("soundOn")) {
            acidDropSound.play();
        } }
    public static void playLevelWinSound() {  if (AcidRain.getPreferences().getBoolean("soundOn")) {
        levelWinSound.play();
    } }
    public static void playSideSplatSound() {  if (AcidRain.getPreferences().getBoolean("soundOn")) {
        sideSplatSound.play();
    } }
    public static void playSirenSound() {  if (AcidRain.getPreferences().getBoolean("soundOn")) {
        sirenSound.play();
    } }
    public static void playThunderSound() {  if (AcidRain.getPreferences().getBoolean("soundOn")) {
        thunderSound.play();
    } }

    public static void playPowerupSound() { if (AcidRain.getPreferences().getBoolean("soundOn")) {
        powerupSound.play();
    } }



}
