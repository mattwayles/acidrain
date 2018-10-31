package com.liquidice.acidrain.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.liquidice.acidrain.AcidRain;
import com.liquidice.acidrain.managers.Gameplay;
import com.liquidice.acidrain.managers.assets.Audio;
import com.liquidice.acidrain.managers.assets.Font;
import com.liquidice.acidrain.managers.assets.Textures;
import com.liquidice.acidrain.screens.unlockables.UnlockablesScreen;
import com.liquidice.acidrain.sprites.Bucket;
import com.liquidice.acidrain.sprites.City;

public class StartScreen {

    //LOGO and FONTS
    private static final Texture LOGO = Textures.logo;
    private static final String BEST_SCORE_TEXT = "Best ";
    private static final String CURRENT_LEVEL_TEXT = "Level ";
    private static final String AVOID_RED_TEXT = "Smash the ACID rain!";
    private static final String CATCH_BLUE_TEXT = "Catch the CLEAN raindrops,";

    private static GlyphLayout catchBlueLayout = new GlyphLayout(Font.catchBlueFont, CATCH_BLUE_TEXT);
    private static GlyphLayout avoidRedLayout = new GlyphLayout(Font.avoidRedFont, AVOID_RED_TEXT);
    private static GlyphLayout currentLevelLayout = new GlyphLayout(Font.currentLevelFont, CURRENT_LEVEL_TEXT + Gameplay.getLevel());
    private static GlyphLayout bestScoreLayout = new GlyphLayout(Font.bestScoreFont, BEST_SCORE_TEXT + Gameplay.getLevelBest() + "%");

    //BUTTONS & STYLES
    private static Stage stage = new Stage();
    private static ImageButton.ImageButtonStyle unlockButtonStyle = new ImageButton.ImageButtonStyle();
    private static ImageButton.ImageButtonStyle soundOffButtonStyle = new ImageButton.ImageButtonStyle();
    private static ImageButton.ImageButtonStyle soundOnButtonStyle = new ImageButton.ImageButtonStyle();
    private static ImageButton.ImageButtonStyle startButtonStyle = new ImageButton.ImageButtonStyle();
    private static ImageButton.ImageButtonStyle helpButtonStyle = new ImageButton.ImageButtonStyle();
    private static ImageButton soundOffButton = new ImageButton(soundOffButtonStyle);
    private static ImageButton soundOnButton = new ImageButton(soundOnButtonStyle);
    private static ImageButton soundButton = new ImageButton(soundOnButtonStyle);
    private static ImageButton unlockButton = new ImageButton(unlockButtonStyle);
    private static ImageButton startButton = new ImageButton(startButtonStyle);
    private static ImageButton helpButton = new ImageButton(helpButtonStyle);

    private static boolean soundOn;
    private static boolean unlockedScreenOpen;

    public static void setUnlockScreenOpen(boolean open) { unlockedScreenOpen = open; }

    //Touch listeners
    static {
        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                AcidRain.setGameState(1);
            }
        });

        unlockButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                UnlockablesScreen.display();
                unlockedScreenOpen = true;
            }
        });
        //TODO: Help button is functioning as reset for the time being, change this
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
     * Display the StartScreen stage with all actors and batch elements
     */
    public static void display() {
        //Draw batch elements
        if (!unlockedScreenOpen) {
            stage.getBatch().begin();
            stage.getBatch().draw(LOGO, Gdx.graphics.getWidth() / 2 - LOGO.getWidth() / 2, Gdx.graphics.getHeight() / 2);
            if (Gameplay.getLevel() == 1 && Gameplay.getLevelBest() == 0) {
                Font.catchBlueFont.draw(stage.getBatch(), CATCH_BLUE_TEXT, Gdx.graphics.getWidth() / 2 - catchBlueLayout.width / 2, Gdx.graphics.getHeight() / 2);
                Font.avoidRedFont.draw(stage.getBatch(), AVOID_RED_TEXT, Gdx.graphics.getWidth() / 2 - avoidRedLayout.width / 2, Gdx.graphics.getHeight() / 2 - 100);
            } else {
                Font.currentLevelFont.draw(stage.getBatch(), CURRENT_LEVEL_TEXT + Gameplay.getLevel(), Gdx.graphics.getWidth() / 2 - currentLevelLayout.width / 2, Gdx.graphics.getHeight() / 2 - 50);
                Font.bestScoreFont.draw(stage.getBatch(), BEST_SCORE_TEXT + Gameplay.getLevelBest() + "%", Gdx.graphics.getWidth() / 2 - bestScoreLayout.width / 2, Gdx.graphics.getHeight() / 2 - 150);
            }
            stage.getBatch().draw(City.getImage(), 0, 0, Gdx.graphics.getWidth(), Bucket.getBucketHover() - 20);
            stage.getBatch().end();


            Gdx.input.setInputProcessor(stage);

            //Sound on/off button
            displaySoundButton();

            //Unlockable button
            unlockButtonStyle.up = Textures.unlockButtonStyleImage;

            //Help Button
            helpButtonStyle.up = Textures.helpButtonStyleImage;

            //Start Button
            startButtonStyle.up = Textures.startButtonStyleImage;

            //Create table, add to stage
            Table table = createButtonTable();
            stage.addActor(table);

            //Draw stage
            stage.draw();
        }
        else {
            UnlockablesScreen.display();
        }
    }

    private static void displaySoundButton() {
        soundOn = AcidRain.getPreferences().getBoolean("soundOn");
        if (!soundOn) {
            soundOffButtonStyle.up = Textures.soundOffButtonStyleImage;
            soundButton = soundOffButton;
            soundOnButtonStyle.up = null;
        } else {
            soundOnButtonStyle.up = Textures.soundOnButtonStyleImage;
            soundButton = soundOnButton;
            soundOffButtonStyle.up = null;
        }
        addSoundButtonListener();
    }

    private static Table createButtonTable() {
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

    private static void addSoundButtonListener() {
        soundButton.clearListeners();
        soundButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                soundOn = !soundOn;
                AcidRain.getPreferences().putBoolean("soundOn", soundOn);
                AcidRain.getPreferences().flush();

                if (soundOn) {
                    Audio.playMusic();
                } else {
                    Audio.stopMusic();
                }
                return false;
            }
        });
    }
}
