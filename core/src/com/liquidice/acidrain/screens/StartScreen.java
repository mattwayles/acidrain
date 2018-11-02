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
import com.liquidice.acidrain.managers.GameplayMgr;
import com.liquidice.acidrain.managers.PropertiesMgr;
import com.liquidice.acidrain.screens.unlockables.UnlockablesScreen;
import com.liquidice.acidrain.utilities.SpriteUtil;

/**
 * Render a Start ScreenMgr containing the logo, session information, and buttons
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
     * Create the Start ScreenMgr
     * @param manager   AssetMgr containing the assets required for this screen
     */
    public StartScreen(AssetManager manager) {
        //Logo and Text
        logo = manager.get("text/logo.png", Texture.class);
        blueFont = manager.get("blue56.ttf", BitmapFont.class);
        redFont = manager.get("red56.ttf");
        catchCleanLayout = new GlyphLayout(blueFont, PropertiesMgr.CATCH_BLUE_TEXT);
        currentLevelLayout = new GlyphLayout(blueFont, PropertiesMgr.CURRENT_LEVEL_TEXT + GameplayMgr.getLevel());
        avoidRedLayout = new GlyphLayout(redFont, PropertiesMgr.AVOID_RED_TEXT);
        bestScoreLayout = new GlyphLayout(redFont, PropertiesMgr.BEST_SCORE_TEXT + GameplayMgr.getLevelBest() + "%");
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
        soundOffButtonStyleUp = new TextureRegionDrawable(new TextureRegion(manager.get("buttons/soundOffButton.png", Texture.class)));
        soundOnButtonStyleUp = new TextureRegionDrawable(new TextureRegion(manager.get("buttons/soundOnButton.png", Texture.class)));
        unlockButtonStyle.up = new TextureRegionDrawable(new TextureRegion(manager.get("buttons/unlockButton.png", Texture.class)));
        helpButtonStyle.up = new TextureRegionDrawable(new TextureRegion(manager.get("buttons/helpButton.png", Texture.class)));
        startButtonStyle.up = new TextureRegionDrawable(new TextureRegion(manager.get("buttons/startButton.png", Texture.class)));

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
            stage.getBatch().draw(logo, Gdx.graphics.getWidth() / 2 - logo.getWidth() / 2, Gdx.graphics.getHeight() / 2);
            if (GameplayMgr.getLevel() == 1 && GameplayMgr.getLevelBest() == 0) {
                blueFont.draw(stage.getBatch(), PropertiesMgr.CATCH_BLUE_TEXT, SpriteUtil.middleOf(Gdx.graphics.getWidth()) - SpriteUtil.middleOf(catchCleanLayout.width), SpriteUtil.middleOf(Gdx.graphics.getHeight()));
                redFont.draw(stage.getBatch(), PropertiesMgr.AVOID_RED_TEXT, SpriteUtil.middleOf(Gdx.graphics.getWidth()) - SpriteUtil.middleOf(avoidRedLayout.width), SpriteUtil.middleOf(Gdx.graphics.getHeight()) - PropertiesMgr.START_SCREEN_SPACING);
            } else {
                blueFont.draw(stage.getBatch(), PropertiesMgr.CURRENT_LEVEL_TEXT + GameplayMgr.getLevel(), SpriteUtil.middleOf(Gdx.graphics.getWidth()) - SpriteUtil.middleOf(currentLevelLayout.width), SpriteUtil.middleOf(Gdx.graphics.getHeight()));
                redFont.draw(stage.getBatch(), PropertiesMgr.BEST_SCORE_TEXT + GameplayMgr.getLevelBest() + "%", SpriteUtil.middleOf(Gdx.graphics.getWidth()) - SpriteUtil.middleOf(bestScoreLayout.width), SpriteUtil.middleOf(Gdx.graphics.getHeight()) - PropertiesMgr.START_SCREEN_SPACING);
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
            //TODO: Un-Staticize UnlockablesScreen
            unlockablesScreen.display();
        }
    }

    /**
     * Toggle the sound button based on user sound preferences
     */
    private void displaySoundButton() {
        soundOn = AcidRain.getPreferences().getBoolean(PropertiesMgr.SHARED_PREF_SOUND_ON);
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
     * @return  A table of all buttons needed for the Start ScreenMgr
     */
    private Table createButtonTable() {
        //Create a new table
        Table table = new Table();

        //Span the whole parent
        table.setFillParent(true);

        //Center the elements
        table.center();

        //Space the elements appropriately
        table.padTop(1050);

        //Create a new vertical group
        VerticalGroup group = new VerticalGroup();
        group.space(100);

        //Create a new horizontal group
        HorizontalGroup settingsGroup = new HorizontalGroup();
        settingsGroup.center();
        settingsGroup.space(150);

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
                AcidRain.setGameState(PropertiesMgr.GAME_PLAY_STATE);
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
                AcidRain.getPreferences().putBoolean(PropertiesMgr.SHARED_PREF_SOUND_ON, soundOn);
                AcidRain.getPreferences().flush();

                if (soundOn) {
                    manager.get("sounds/thunderstorm.mp3", Music.class).play();
                } else {
                    manager.get("sounds/thunderstorm.mp3", Music.class).stop();
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
                AcidRain.getPreferences().clear();
                AcidRain.getPreferences().flush();
            }
        });
    }
}
