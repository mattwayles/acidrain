package com.liquidice.acidrain.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.liquidice.acidrain.utilities.FontGenerator;

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

    }

    public void load() {
        loadButtons();
        loadTextures();
        loadFonts();
        loadSounds();
    }

    /**
     * Load Font Assets
     */
    private void loadFonts() {
        FileHandleResolver resolver = new InternalFileHandleResolver();
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        FreetypeFontLoader.FreeTypeFontLoaderParameter params = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        params.fontFileName = MAIN_FONT;
        params.fontParameters.color = Color.RED;
        params.fontParameters.size = PropManager.SCORE_FONT_SIZE;

        manager.load("blue56.ttf", BitmapFont.class, params);
        manager.load("red56.ttf", BitmapFont.class, params);

        manager.load("gold56.ttf", BitmapFont.class, params);
        manager.load("white56.ttf", BitmapFont.class, params);
        manager.load("unlockables.ttf", BitmapFont.class, params);
        manager.load("powerup.ttf", BitmapFont.class, params);
        manager.load("caughtScore.ttf", BitmapFont.class, params);
        manager.load("strengthScore.ttf", BitmapFont.class, params);
    }


    private static final String MAIN_FONT = "font/Play.ttf";

    public static FreetypeFontLoader.FreeTypeFontLoaderParameter generatePlayFont(int size, Color color) {
        FreetypeFontLoader.FreeTypeFontLoaderParameter font = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        font.fontFileName = MAIN_FONT;
        font.fontParameters.size = size;
        font.fontParameters.color = color;
        return font;
    }

    public static FreetypeFontLoader.FreeTypeFontLoaderParameter generatePlayFont(int size, Color color, int border, Color borderColor) {
        FreetypeFontLoader.FreeTypeFontLoaderParameter font = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        font.fontFileName = MAIN_FONT;
        font.fontParameters.size = size;
        font.fontParameters.color = color;
        font.fontParameters.borderWidth = border;
        font.fontParameters.borderColor = borderColor;
        return font;
    }

    /**
     * Load Texture assets
     */
    private void loadTextures() {
        manager.load("acid/drop/acid1.png", Texture.class);
        manager.load("acid/drop/acid2.png", Texture.class);
        manager.load("acid/drop/acid3.png", Texture.class);
        manager.load("acid/drop/acid4.png", Texture.class);
        manager.load("acid/drop/acid5.png", Texture.class);
        manager.load("acid/drop/acid6.png", Texture.class);
        manager.load("acid/drop/acid7.png", Texture.class);
        manager.load("acid/splash/acidSplash.png", Texture.class);
        manager.load("acid/splash/acidSplashLeft.png", Texture.class);
        manager.load("acid/splash/acidSplashRight.png", Texture.class);
        manager.load("backgrounds/stormBackground.png", Texture.class);
        manager.load("backgrounds/lightningBackground.jpg", Texture.class);
        manager.load("backgrounds/sunnySkyBackground1.png", Texture.class);
        manager.load("backgrounds/sunnySkyBackground2.png", Texture.class);
        manager.load("backgrounds/sunnySkyBackground3.png", Texture.class);
        manager.load("backgrounds/sunnySkyBackground4.png", Texture.class);
        manager.load("backgrounds/sunnySkyBackground5.png", Texture.class);
        manager.load("backgrounds/sunnySkyBackground6.png", Texture.class);
        manager.load("backgrounds/sunnySkyBackground7.png", Texture.class);
        manager.load("backgrounds/sunnySkyBackground8.png", Texture.class);
        manager.load("backgrounds/sunnySkyBackground9.png", Texture.class);
        manager.load("backgrounds/sunnySkyBackground10.png", Texture.class);
        manager.load("bucket/bucket0.png", Texture.class);
        manager.load("bucket/bucket1.png", Texture.class);
        manager.load("bucket/bucket2.png", Texture.class);
        manager.load("bucket/bucket3.png", Texture.class);
        manager.load("bucket/bucket4.png", Texture.class);
        manager.load("bucket/bucket5.png", Texture.class);
        manager.load("bucket/bucket6.png", Texture.class);
        manager.load("bucket/bucket7.png", Texture.class);
        manager.load("bucket/bucket8.png", Texture.class);
        manager.load("bucket/bucket9.png", Texture.class);
        manager.load("city/city1.png", Texture.class);
        manager.load("city/city2.png", Texture.class);
        manager.load("city/city3.png", Texture.class);
        manager.load("city/city4.png", Texture.class);
        manager.load("city/city5.png", Texture.class);
        manager.load("city/city6.png", Texture.class);
        manager.load("city/city7.png", Texture.class);
        manager.load("city/city8.png", Texture.class);
        manager.load("city/city9.png", Texture.class);
        manager.load("city/city10.png", Texture.class);
        manager.load("clouds.png", Texture.class);
        manager.load("placeholder.png", Texture.class);
        manager.load("rain/drop/drop1.png", Texture.class);
        manager.load("rain/drop/drop2.png", Texture.class);
        manager.load("rain/drop/drop3.png", Texture.class);
        manager.load("rain/drop/drop4.png", Texture.class);
        manager.load("rain/drop/drop5.png", Texture.class);
        manager.load("rain/drop/drop6.png", Texture.class);
        manager.load("rain/drop/drop7.png", Texture.class);
        manager.load("rain/splash/rainSplash.png", Texture.class);
        manager.load("rain/splash/rainSplashLeft.png", Texture.class);
        manager.load("rain/splash/rainSplashRight.png", Texture.class);
        manager.load("screen/unlocked.jpg", Texture.class);
        manager.load("text/gameOver.png", Texture.class);
        manager.load("text/levelComplete.png", Texture.class);
        manager.load("text/logo.png", Texture.class);
        manager.load("text/perfectLevel.png", Texture.class);
        manager.load("unlockables/healthPack/healthPackDrop.png", Texture.class);
        manager.load("unlockables/healthPack/unlockableHealthPack.png", Texture.class);
        manager.load("unlockables/powerDrop/multipliersDrop.png", Texture.class);
        manager.load("unlockables/powerDrop/powerDrop2.png", Texture.class);
        manager.load("unlockables/powerDrop/powerDrop3.png", Texture.class);
        manager.load("unlockables/powerDrop/powerDrop4.png", Texture.class);
        manager.load("unlockables/powerDrop/powerDrop5.png", Texture.class);
        manager.load("unlockables/powerDrop/powerDrop6.png", Texture.class);
        manager.load("unlockables/powerDrop/unlockableMultipliers.png", Texture.class);
        manager.load("unlockables/umbrella/umbrellaDrop.png", Texture.class);
        manager.load("unlockables/umbrella/umbrellaLeft.png", Texture.class);
        manager.load("unlockables/umbrella/umbrellaRight.png", Texture.class);
        manager.load("unlockables/umbrella/unlockableUmbrella.png", Texture.class);
        manager.load("unlockables/unlockableLocked.png", Texture.class);

    }

    /**
     * Load Button assets
     */
    private void loadButtons() {
        manager.load("buttons/closeButton.png", Texture.class);
        manager.load("buttons/helpButton.png", Texture.class);
        manager.load("buttons/pauseButton.png", Texture.class);
        manager.load("buttons/playButton.png", Texture.class);
        manager.load("buttons/soundOffButton.png", Texture.class);
        manager.load("buttons/soundOnButton.png", Texture.class);
        manager.load("buttons/startButton.png", Texture.class);
        manager.load("buttons/stopButton.png", Texture.class);
        manager.load("buttons/unlockButton.png", Texture.class);
    }

    /**
     * Load Sound Assets
     */
    private void loadSounds() {
        manager.load("sounds/acidDrop.mp3", Sound.class);
        manager.load("sounds/birds.wav", Music.class);
        manager.load("sounds/levelWin.mp3", Sound.class);
        manager.load("sounds/powerup.wav", Sound.class);
        manager.load("sounds/rainDrop.mp3", Sound.class);
        manager.load("sounds/sideSplat.mp3", Sound.class);
        manager.load("sounds/siren.wav", Sound.class);
        manager.load("sounds/thunderCrack.wav", Sound.class);
        manager.load("sounds/thunderstorm.mp3", Music.class);

    }
}
