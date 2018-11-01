package com.liquidice.acidrain.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
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
import com.liquidice.acidrain.managers.Gameplay;
import com.liquidice.acidrain.managers.Properties;
import com.liquidice.acidrain.managers.assets.Audio;
import com.liquidice.acidrain.screens.unlockables.UnlockablesScreen;
import com.liquidice.acidrain.sprites.Bucket;
import com.liquidice.acidrain.sprites.City;

/**
 * Render a Start Screen containing the logo, session information, and buttons
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

    /**
     * Create the Start Screen
     * @param manager   AssetManager containing the assets required for this screen
     */
    public StartScreen(AssetManager manager) {
        //Logo and Text
        logo = manager.get("text/logo.png", Texture.class);
        blueFont = manager.get("blue56.ttf", BitmapFont.class);
        redFont = manager.get("red56.ttf");
        catchCleanLayout = new GlyphLayout(blueFont, Properties.CATCH_BLUE_TEXT);
        currentLevelLayout = new GlyphLayout(blueFont, Properties.CURRENT_LEVEL_TEXT + Gameplay.getLevel());
        avoidRedLayout = new GlyphLayout(redFont, Properties.AVOID_RED_TEXT);
        bestScoreLayout = new GlyphLayout(redFont, Properties.BEST_SCORE_TEXT + Gameplay.getLevelBest() + "%");
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
        City.setImage(manager.get("city/city10.png", Texture.class));
        Bucket.setImage(manager.get("rain/bucket/bucket0.png", Texture.class));

        //Listeners
        addButtonListeners();
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
            if (Gameplay.getLevel() == 1 && Gameplay.getLevelBest() == 0) {
                blueFont.draw(stage.getBatch(), Properties.CATCH_BLUE_TEXT, middleOf(Gdx.graphics.getWidth()) - middleOf(catchCleanLayout.width), middleOf(Gdx.graphics.getHeight()));
                redFont.draw(stage.getBatch(), Properties.AVOID_RED_TEXT, middleOf(Gdx.graphics.getWidth()) - middleOf(avoidRedLayout.width), middleOf(Gdx.graphics.getHeight()) - Properties.START_SCREEN_SPACING);
            } else {
                blueFont.draw(stage.getBatch(), Properties.CURRENT_LEVEL_TEXT + Gameplay.getLevel(), middleOf(Gdx.graphics.getWidth()) - middleOf(currentLevelLayout.width), middleOf(Gdx.graphics.getHeight()));
                redFont.draw(stage.getBatch(), Properties.BEST_SCORE_TEXT + Gameplay.getLevelBest() + "%", middleOf(Gdx.graphics.getWidth()) - middleOf(bestScoreLayout.width), middleOf(Gdx.graphics.getHeight()) - Properties.START_SCREEN_SPACING);
            }
            stage.getBatch().draw(City.getImage(), 0, 0, Gdx.graphics.getWidth(), Properties.CITY_HEIGHT);
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
        soundOn = AcidRain.getPreferences().getBoolean(Properties.SHARED_PREF_SOUND_ON);
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
     * @return  A table of all buttons needed for the Start Screen
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
                AcidRain.setGameState(Properties.GAME_PLAY_STATE);
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
                AcidRain.getPreferences().putBoolean(Properties.SHARED_PREF_SOUND_ON, soundOn);
                AcidRain.getPreferences().flush();

                if (soundOn) {
                    Audio.playMusic();
                } else {
                    Audio.stopMusic();
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

    /**
     * Utility method to return the X/Y coordinate representing the middle of the object's total height/width
     * @param fullSize  The object's total height OR width
     * @return  The midway point of the Objec'ts total height OR width
     */
    public float middleOf(float fullSize) {
        return fullSize / 2;
    }
}
