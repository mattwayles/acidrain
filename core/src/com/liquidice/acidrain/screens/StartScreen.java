package com.liquidice.acidrain.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.liquidice.acidrain.managers.AssetLoader;
import com.liquidice.acidrain.managers.AudioManager;
import com.liquidice.acidrain.managers.GameplayManager;
import com.liquidice.acidrain.managers.PreferenceManager;
import com.liquidice.acidrain.managers.PropManager;
import com.liquidice.acidrain.managers.ScreenManager;
import com.liquidice.acidrain.sprites.Background;
import com.liquidice.acidrain.sprites.Bucket;
import com.liquidice.acidrain.sprites.City;
import com.liquidice.acidrain.sprites.Clouds;
import com.liquidice.acidrain.utilities.SpriteUtil;

/**
 * Render a Start ScreenManager containing the logo, session information, and buttons
 */
public class StartScreen {
    private Texture logo;
    private AssetLoader assetLoader;
    private Stage stage = new Stage();
    private BitmapFont redFont;
    private BitmapFont blueFont;
    private GlyphLayout avoidRedLayout;
    private GlyphLayout catchCleanLayout;
    private GlyphLayout currentLevelLayout;
    private GlyphLayout bestScoreLayout;
    private ImageButton soundOffButton;
    private ImageButton soundOnButton;
    private ImageButton soundButton;
    private ImageButton unlockButton;
    private ImageButton startButton;
    private ImageButton helpButton;
    private ImageButton.ImageButtonStyle soundOffButtonStyle;
    private ImageButton.ImageButtonStyle soundOnButtonStyle;
    private TextureRegionDrawable soundOffButtonStyleUp;
    private TextureRegionDrawable soundOnButtonStyleUp;
    private TextureRegion soundOffTextureRegion;
    private TextureRegion soundOnTextureRegion;
    private TextureRegion unlockTextureRegion;
    private TextureRegion helpTextureRegion;
    private TextureRegion startTextureRegion;
    private Table table;
    private boolean soundOn;
    private boolean unlockedScreenOpen;

    /**
     * Create the Start ScreenManager
     * @param loader   AssetLoader containing the assets required for this screen
     */
    public StartScreen(AssetLoader loader) {
        this.assetLoader = loader;
        this.assetLoader.loadStartScreenAssets();
        //Logo and Text
        logo = assetLoader.getManager().get(PropManager.TEXTURE_TEXT_LOGO, Texture.class);
        setFonts();

        //Buttons
        soundOffButtonStyle = new ImageButton.ImageButtonStyle();
        soundOnButtonStyle = new ImageButton.ImageButtonStyle();
        soundOffButton = new ImageButton(soundOffButtonStyle);
        soundOnButton = new ImageButton(soundOnButtonStyle);
        soundButton = new ImageButton(soundOnButtonStyle);
        ImageButton.ImageButtonStyle unlockButtonStyle = new ImageButton.ImageButtonStyle();
        unlockButton = new ImageButton(unlockButtonStyle);
        ImageButton.ImageButtonStyle startButtonStyle = new ImageButton.ImageButtonStyle();
        startButton = new ImageButton(startButtonStyle);
        ImageButton.ImageButtonStyle helpButtonStyle = new ImageButton.ImageButtonStyle();
        helpButton = new ImageButton(helpButtonStyle);

        //Texture Regions
        soundOffTextureRegion = new TextureRegion(assetLoader.getManager().get(PropManager.BUTTON_SOUND_OFF, Texture.class));
        soundOnTextureRegion = new TextureRegion(assetLoader.getManager().get(PropManager.BUTTON_SOUND_ON, Texture.class));
        unlockTextureRegion = new TextureRegion(assetLoader.getManager().get(PropManager.BUTTON_UNLOCK, Texture.class));
        helpTextureRegion = new TextureRegion(assetLoader.getManager().get(PropManager.BUTTON_HELP, Texture.class));
        startTextureRegion = new TextureRegion(assetLoader.getManager().get(PropManager.BUTTON_START, Texture.class));

        //Drawables
        TextureRegionDrawable soundOffDrawable = new TextureRegionDrawable(soundOffTextureRegion);
        TextureRegionDrawable soundOnDrawable = new TextureRegionDrawable(soundOnTextureRegion);
        TextureRegionDrawable unlockDrawable = new TextureRegionDrawable(unlockTextureRegion);
        TextureRegionDrawable helpDrawable = new TextureRegionDrawable(helpTextureRegion);
        TextureRegionDrawable startDrawable = new TextureRegionDrawable(startTextureRegion);

        soundOffButtonStyleUp = soundOffDrawable;
        soundOnButtonStyleUp = soundOnDrawable;
        unlockButtonStyle.up = unlockDrawable;
        helpButtonStyle.up = helpDrawable;
        startButtonStyle.up = startDrawable;
        table = new Table();

        //Listeners
        addButtonListeners();

        //Default sprites
        Background.init(assetLoader.getManager());
        Clouds.init(assetLoader.getManager().get(PropManager.TEXTURE_CLOUDS, Texture.class));
        Bucket.init(assetLoader);
        City.setImage(assetLoader.getManager().get(PropManager.TEXTURE_CITY_10, Texture.class));
        AudioManager.setThunderstormAudio(assetLoader.getManager().get(PropManager.AUDIO_THUNDERSTORM, Music.class));
    }

    public void loadAssets() {
        this.assetLoader.loadStartScreenAssets();

        //Logo and Text
        logo = assetLoader.getManager().get(PropManager.TEXTURE_TEXT_LOGO, Texture.class);

        //Regions
        soundOffTextureRegion.setTexture(assetLoader.getManager().get(PropManager.BUTTON_SOUND_OFF, Texture.class));
        soundOnTextureRegion.setTexture(assetLoader.getManager().get(PropManager.BUTTON_SOUND_ON, Texture.class));
        unlockTextureRegion.setTexture(assetLoader.getManager().get(PropManager.BUTTON_UNLOCK, Texture.class));
        helpTextureRegion.setTexture(assetLoader.getManager().get(PropManager.BUTTON_HELP, Texture.class));
        startTextureRegion.setTexture(assetLoader.getManager().get(PropManager.BUTTON_START, Texture.class));
    }

    /**
     * Set the Open status of the 'Unlocked Items' screen
     * @param open  Boolean value indicating whether the 'Unlocked Items' screen is open
     */
    public void setUnlockScreenOpen(boolean open) {
        if (!open) {
            loadAssets();
        }
        unlockedScreenOpen = open; }

    /**
     * Render the configured StartScreen sprites, actors, and stage
     */
    public void display() {

        //Draw Sprites
        if (!unlockedScreenOpen) {
            stage.getBatch().begin();

            //Logo
            stage.getBatch().draw(
                    logo,
                    SpriteUtil.middleOf(Gdx.graphics.getWidth()) - SpriteUtil.middleOf(logo.getWidth()),
                    SpriteUtil.middleOf(Gdx.graphics.getHeight()));
            if (GameplayManager.getLevel() == 1 && GameplayManager.getLevelBest() == 0) {
                //Catch the CLEAN Raindrops
                blueFont.draw(
                        stage.getBatch(),
                        PropManager.CATCH_BLUE_TEXT,
                        SpriteUtil.middleOf(Gdx.graphics.getWidth()) - SpriteUtil.middleOf(catchCleanLayout.width),
                        SpriteUtil.middleOf(Gdx.graphics.getHeight()));
                //Avoid the ACID Rain!
                redFont.draw(
                        stage.getBatch(),
                        PropManager.AVOID_RED_TEXT,
                        SpriteUtil.middleOf(Gdx.graphics.getWidth()) - SpriteUtil.middleOf(avoidRedLayout.width),
                        SpriteUtil.middleOf(Gdx.graphics.getHeight()) - PropManager.START_SCREEN_SPACING);
            } else {
                //Variable layout size
                currentLevelLayout.setText(blueFont, PropManager.CURRENT_LEVEL_TEXT + GameplayManager.getLevel());
                bestScoreLayout.setText(redFont, PropManager.BEST_SCORE_TEXT + GameplayManager.getLevelBest() + "%");

                //Level X
                blueFont.draw(
                        stage.getBatch(),
                        PropManager.CURRENT_LEVEL_TEXT + GameplayManager.getLevel(),
                        SpriteUtil.middleOf(Gdx.graphics.getWidth()) - SpriteUtil.middleOf(currentLevelLayout.width),
                        SpriteUtil.middleOf(Gdx.graphics.getHeight()));

                //Best: X%
                redFont.draw(
                        stage.getBatch(),
                        PropManager.BEST_SCORE_TEXT + GameplayManager.getLevelBest() + "%",
                        SpriteUtil.middleOf(Gdx.graphics.getWidth()) - SpriteUtil.middleOf(bestScoreLayout.width),
                        SpriteUtil.middleOf(Gdx.graphics.getHeight()) - PropManager.START_SCREEN_SPACING);
            }

            //City
            City.setImage(assetLoader.getManager().get(PropManager.TEXTURE_CITY_10, Texture.class));

            stage.getBatch().end();
            //Draw Stage w/ Buttons
            Gdx.input.setInputProcessor(stage);
            displaySoundButton();

            //Create table, add & draw stage
            table = createButtonTable();
            stage.addActor(table);
            stage.draw();
        } else {
            ScreenManager.getUnlockablesScreen().display();
        }
    }

    /**
     * Set the fonts and font colors
     */
    private void setFonts() {
        blueFont = new BitmapFont(Gdx.files.internal(PropManager.FONT_PLAY_82), Gdx.files.internal(PropManager.FONT_PLAY_82_PNG), false);
        redFont = new BitmapFont(Gdx.files.internal(PropManager.FONT_PLAY_82), Gdx.files.internal(PropManager.FONT_PLAY_82_PNG), false);
        blueFont.setColor(PropManager.SCORE_BLUE_COLOR);
        redFont.setColor(PropManager.SCORE_RED_COLOR);
        catchCleanLayout = new GlyphLayout(blueFont, PropManager.CATCH_BLUE_TEXT);
        avoidRedLayout = new GlyphLayout(redFont, PropManager.AVOID_RED_TEXT);
        currentLevelLayout = new GlyphLayout();
        bestScoreLayout = new GlyphLayout();
    }

    /**
     * Toggle the sound button based on user sound preferences
     */
    private void displaySoundButton() {
        soundOn = PreferenceManager.getBoolean(PropManager.PREF_SOUND_ON, true);
        if (!soundOn) {
            soundOffButtonStyle.up = soundOffButtonStyleUp;
            soundOnButtonStyle.up = null;
            soundButton = soundOffButton;
        } else {
            soundOnButtonStyle.up = soundOnButtonStyleUp;
            soundOffButtonStyle.up = null;
            soundButton = soundOnButton;
        }
        addButtonListeners();
    }

    /**
     * Create a table of properly-spaced buttons
     * @return  A table of all buttons needed for the Start ScreenManager
     */
    private Table createButtonTable() {
        table.clear();

        //Span the whole parent
        table.setFillParent(true);

        //Center the elements
        table.center();

        //Space the elements appropriately
        table.padTop(PropManager.TABLE_TOP_PADDING);

        //Create a new vertical group
        VerticalGroup group = new VerticalGroup();
        group.space(PropManager.TABLE_VERTICAL_SPACING);

        //Create a new horizontal group
        HorizontalGroup settingsGroup = new HorizontalGroup();
        settingsGroup.center();
        settingsGroup.space(PropManager.TABLE_HORIZONTAL_SPACING);

        //Add actors
        settingsGroup.addActor(soundButton);
        settingsGroup.addActor(unlockButton);
        settingsGroup.addActor(helpButton);

        //Add Horizontal group and start button to Vertical group
        group.addActor(settingsGroup);
        group.addActor(startButton);

        //Add vertical group to table
        table.add(group);

        return table;
    }

    /**
     * Add touch listeners to each button
     */
    private void addButtonListeners() {
        //Start Button Listener
        startButton.clearListeners();
        startButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                assetLoader.unloadStartScreenAssets();
                if (ScreenManager.getGameplayScreen() == null) {
                    ScreenManager.createGameplayScreen();
                } else {
                    ScreenManager.getGameplayScreen().loadAssets();
                }
                GameplayManager.resume();
                GameplayManager.setGameState(PropManager.GAME_PLAY_STATE);
                return false;
            }
        });

        //Unlock Button Listener
        unlockButton.clearListeners();
        unlockButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                assetLoader.unloadStartScreenAssets();
                if (ScreenManager.getUnlockablesScreen() == null) {
                    ScreenManager.createUnlockablesScreen();
                } else {
                    ScreenManager.getUnlockablesScreen().loadAssets();
                }
                unlockedScreenOpen = true;
                return false;
            }
        });

        //Sound On/Off Button Listener
        soundButton.clearListeners();
        soundButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                soundOn = !soundOn;
                PreferenceManager.putBoolean(PropManager.PREF_SOUND_ON, soundOn);

                if (soundOn) {
                    AudioManager.playThunderstorm();
                } else {
                    AudioManager.stopThunderstorm();
                }
                return false;
            }
        });

        //Help Button Listener
        helpButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //TODO: Remove reset
                Gdx.app.log("DEBUG: ", "Resetting Prefs");
                PreferenceManager.clear();
            }
        });
    }
}
