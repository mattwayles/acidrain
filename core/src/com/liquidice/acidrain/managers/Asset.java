package com.liquidice.acidrain.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.liquidice.acidrain.utilities.FontGenerator;

public class Asset {
    private AssetManager manager;

    public AssetManager getManager() { return manager; }

    public Asset() {
        manager  = new AssetManager();
        loadFonts();
        loadButtons();
        loadTextures();
        manager.finishLoading();
    }

    //TODO: Continue on with loading Assets

    private void loadFonts() {
        FileHandleResolver resolver = new InternalFileHandleResolver();
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
        manager.load("blue56.ttf", BitmapFont.class, FontGenerator.generatePlayFont(Properties.START_SCREEN_TEXT_SIZE, Properties.START_SCREEN_BLUE ));
        manager.load("red56.ttf", BitmapFont.class, FontGenerator.generatePlayFont(Properties.START_SCREEN_TEXT_SIZE, Properties.START_SCREEN_RED));
        manager.load("unlockableItems.ttf", BitmapFont.class, FontGenerator.generatePlayFont(Properties.UNLOCKED_ITEMS_FONT_SIZE, Color.GOLD, 6, Color.BLACK));
    }

    private void loadTextures() {
        manager.load("text/logo.png", Texture.class);
        manager.load("unlockables/powerDrop/unlockableMultipliers.png", Texture.class);
        manager.load("unlockables/healthPack/unlockableHealthPack.png", Texture.class);
        manager.load("unlockables/umbrella/unlockableUmbrella.png", Texture.class);
        manager.load("unlockables/unlockableLocked.png", Texture.class);
        manager.load("city/city10.png", Texture.class);
        manager.load("rain/bucket/bucket0.png", Texture.class);

    }

    private void loadButtons() {
        manager.load("buttons/soundOffButton.png", Texture.class);
        manager.load("buttons/soundOnButton.png", Texture.class);
        manager.load("buttons/helpButton.png", Texture.class);
        manager.load("buttons/unlockButton.png", Texture.class);
        manager.load("buttons/startButton.png", Texture.class);
        manager.load("buttons/closeButton.png", Texture.class);
    }
}
