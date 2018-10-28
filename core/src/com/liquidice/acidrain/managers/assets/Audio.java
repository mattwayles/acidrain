package com.liquidice.acidrain.managers.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Audio {
    private static Music backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/thunderstorm.mp3"));
    private static Music birdsMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/birds.wav"));
    private static Sound rainDropSound = Gdx.audio.newSound(Gdx.files.internal("sounds/rainDrop.mp3"));
    private static Sound acidDropSound = Gdx.audio.newSound(Gdx.files.internal("sounds/acidDrop.mp3"));
    private static Sound levelWinSound = Gdx.audio.newSound(Gdx.files.internal("sounds/levelWin.mp3"));
    private static Sound sideSplatSound = Gdx.audio.newSound(Gdx.files.internal("sounds/sideSplat.mp3"));
    private static Sound sirenSound = Gdx.audio.newSound(Gdx.files.internal("sounds/siren.wav"));
    private static Sound thunderSound = Gdx.audio.newSound(Gdx.files.internal("sounds/thunderCrack.wav"));

    public static Music getBackgroundMusic() { return backgroundMusic; }
    public static Music getBirdsMusic() { return birdsMusic; }

    public static void playMusic() {
        backgroundMusic.play();
        backgroundMusic.setLooping(true);
    }
    public static void playBirds() {
        birdsMusic.play();
        birdsMusic.setLooping(true);
    }
    public static void playRainDropSound() { rainDropSound.play(); }
    public static void playAcidDropSound() { acidDropSound.play(); }
    public static void playLevelWinSound() { levelWinSound.play(); }
    public static void playSideSplatSound() { sideSplatSound.play(); }
    public static void playSirenSound() { sirenSound.play(); }
    public static void playThunderSound() { thunderSound.play(); }



}
