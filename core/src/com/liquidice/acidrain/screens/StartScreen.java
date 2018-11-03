package com.liquidice.acidrain.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
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
import com.liquidice.acidrain.AcidRain;
import com.liquidice.acidrain.managers.AudioManager;
import com.liquidice.acidrain.managers.GameplayManager;
import com.liquidice.acidrain.managers.PreferenceManager;
import com.liquidice.acidrain.managers.PropManager;
import com.liquidice.acidrain.screens.unlockables.UnlockablesScreen;
import com.liquidice.acidrain.utilities.SpriteUtil;

/**
 * Render a Start ScreenManager containing the logo, session information, and buttons
 */
public class StartScreen {
    private Texture logo;
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
    private boolean soundOn;
    private boolean unlockedScreenOpen;
    private UnlockablesScreen unlockablesScreen;
    private AssetManager manager;

    /**
     * Create the Start ScreenManager
     * @param manager   AssetLoader containing the assets required for this screen
     */
    public StartScreen(AssetManager manager) {
        //Logo and Text
        logo = manager.get(PropManager.TEXTURE_TEXT_LOGO, Texture.class);
        blueFont = manager.get(PropManager.FONT_BLUE56, BitmapFont.class);
        redFont = manager.get(PropManager.FONT_RED56, BitmapFont.class);
        catchCleanLayout = new GlyphLayout(blueFont, PropManager.CATCH_BLUE_TEXT);
        currentLevelLayout = new GlyphLayout(blueFont, PropManager.CURRENT_LEVEL_TEXT + GameplayManager.getLevel());
        avoidRedLayout = new GlyphLayout(redFont, PropManager.AVOID_RED_TEXT);
        bestScoreLayout = new GlyphLayout(redFont, PropManager.BEST_SCORE_TEXT + GameplayManager.getLevelBest() + "%");
        unlockablesScreen = new UnlockablesScreen(manager, this);

        //Buttons
        ImageButton.ImageButtonStyle unlockButtonStyle = new ImageButton.ImageButtonStyle();
        soundOffButtonStyle = new ImageButton.ImageButtonStyle();
        soundOnButtonStyle = new ImageButton.ImageButtonStyle();
        ImageButton.ImageButtonStyle startButtonStyle = new ImageButton.ImageButtonStyle();
        ImageButton.ImageButtonStyle helpButtonStyle = new ImageButton.ImageButtonStyle();
        soundOffButton = new ImageButton(soundOffButtonStyle);
        soundOnButton = new ImageButton(soundOnButtonStyle);
        soundButton = new ImageButton(soundOnButtonStyle);
        unlockButton = new ImageButton(unlockButtonStyle);
        startButton = new ImageButton(startButtonStyle);
        helpButton = new ImageButton(helpButtonStyle);

        //Images
        soundOffButtonStyleUp = new TextureRegionDrawable(new TextureRegion(manager.get(PropManager.BUTTON_SOUND_OFF, Texture.class)));
        soundOnButtonStyleUp = new TextureRegionDrawable(new TextureRegion(manager.get(PropManager.BUTTON_SOUND_ON, Texture.class)));
        unlockButtonStyle.up = new TextureRegionDrawable(new TextureRegion(manager.get(PropManager.BUTTON_UNLOCK, Texture.class)));
        helpButtonStyle.up = new TextureRegionDrawable(new TextureRegion(manager.get(PropManager.BUTTON_HELP, Texture.class)));
        startButtonStyle.up = new TextureRegionDrawable(new TextureRegion(manager.get(PropManager.BUTTON_START, Texture.class)));

        //Listeners
        addButtonListeners();

        this.manager = manager;
    }

    /**
     * Set the Open status of the 'Unlocked Items' screen
     * @param open  Boolean value indicating whether the 'Unlocked Items' screen is open
     */
    public void setUnlockScreenOpen(boolean open) { unlockedScreenOpen = open; }

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
            stage.getBatch().end();

            //Draw Stage w/ Buttons
            Gdx.input.setInputProcessor(stage);
            displaySoundButton();

            //Create table, add & draw stage
            Table table = createButtonTable();
            stage.addActor(table);
            stage.draw();
        } else {
            unlockablesScreen.display();
        }
    }

    /**
     * Toggle the sound button based on user sound preferences
     */
    private void displaySoundButton() {
        soundOn = PreferenceManager.getBoolean(PropManager.PREF_SOUND_ON);
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
        //Create a new table
        Table table = new Table();

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
        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameplayManager.setGameState(PropManager.GAME_PLAY_STATE);
            }
        });

        //Unlock Button Listener
        unlockButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                unlockablesScreen.display();
                unlockedScreenOpen = true;
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
