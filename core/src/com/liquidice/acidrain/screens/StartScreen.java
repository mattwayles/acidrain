package com.liquidice.acidrain.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.liquidice.acidrain.AcidRain;
import com.liquidice.acidrain.managers.Gameplay;
import com.liquidice.acidrain.managers.assets.Font;
import com.liquidice.acidrain.managers.assets.Textures;

import javax.swing.GroupLayout;

public class StartScreen {
    private static final Texture LOGO = Textures.logo;
    private static final String BEST_SCORE_TEXT = "Best ";
    private static final String CURRENT_LEVEL_TEXT = "Level ";
    private static final String AVOID_RED_TEXT = "Smash the ACID rain!";
    private static final String CATCH_BLUE_TEXT = "Catch the CLEAN raindrops,";
    private static final String TOUCH_ANYWHERE_TEXT = "Touch anywhere to begin";
    private static BitmapFont avoidRedFont = Font.generatePlayFont(56, Color.valueOf("#ff4646"));
    private static BitmapFont catchBlueFont = Font.generatePlayFont(56, Color.valueOf("#99d9ea"));
    private static BitmapFont bestScoreFont = Font.generatePlayFont(56, Color.valueOf("#ff4646"));
    private static BitmapFont currentLevelFont = Font.generatePlayFont(56, Color.valueOf("#99d9ea"));
    private static BitmapFont touchAnywhereFont = Font.generatePlayFont(56, Color.WHITE);
    private static GlyphLayout avoidRedLayout = new GlyphLayout(avoidRedFont, AVOID_RED_TEXT);
    private static GlyphLayout catchBlueLayout = new GlyphLayout(catchBlueFont, CATCH_BLUE_TEXT);
    private static GlyphLayout touchAnywhereLayout = new GlyphLayout(touchAnywhereFont, TOUCH_ANYWHERE_TEXT);
    private static GlyphLayout currentLevelLayout = new GlyphLayout(currentLevelFont, CURRENT_LEVEL_TEXT + Gameplay.getLevel());
    private static GlyphLayout bestScoreLayout = new GlyphLayout(bestScoreFont, BEST_SCORE_TEXT + Gameplay.getLevelBest() + "%");


    static Stage stage = new Stage();
    static ImageButton.ImageButtonStyle unlockButtonStyle = new ImageButton.ImageButtonStyle();
    static Drawable unlockButtonStyleImage = new TextureRegionDrawable(new TextureRegion(new Texture("buttons/unlockButton.png")));
    static ImageButton unlockButton = new ImageButton(unlockButtonStyle);
    static ImageButton.ImageButtonStyle soundOffButtonStyle = new ImageButton.ImageButtonStyle();
    static Drawable soundOffButtonStyleImage = new TextureRegionDrawable(new TextureRegion(new Texture("buttons/soundOffButton.png")));
    static ImageButton soundOffButton = new ImageButton(soundOffButtonStyle);
    static ImageButton.ImageButtonStyle soundOnButtonStyle = new ImageButton.ImageButtonStyle();
    static Drawable soundOnButtonStyleImage = new TextureRegionDrawable(new TextureRegion(new Texture("buttons/soundOnButton.png")));
    static ImageButton soundOnButton = new ImageButton(soundOnButtonStyle);

    private static boolean soundOn;

    public static void display(Batch batch) {
        batch.draw(LOGO, Gdx.graphics.getWidth() / 2 - LOGO.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        touchAnywhereFont.draw(batch, TOUCH_ANYWHERE_TEXT, Gdx.graphics.getWidth() / 2 - touchAnywhereLayout.width / 2, Gdx.graphics.getHeight() / 2 - LOGO.getHeight() / 2 - 20);
        if (Gameplay.getLevel() == 1 && Gameplay.getLevelBest() == 0) {
            catchBlueFont.draw(batch, CATCH_BLUE_TEXT, Gdx.graphics.getWidth() / 2 - catchBlueLayout.width / 2, Gdx.graphics.getHeight() / 2 - 50);
            avoidRedFont.draw(batch, AVOID_RED_TEXT, Gdx.graphics.getWidth() / 2 - avoidRedLayout.width / 2, Gdx.graphics.getHeight() / 2 - 150);
        } else {
            currentLevelFont.draw(batch, CURRENT_LEVEL_TEXT + Gameplay.getLevel(), Gdx.graphics.getWidth() / 2 - currentLevelLayout.width / 2, Gdx.graphics.getHeight() / 2 - 50);
            bestScoreFont.draw(batch, BEST_SCORE_TEXT + Gameplay.getLevelBest() + "%", Gdx.graphics.getWidth() / 2 - bestScoreLayout.width / 2, Gdx.graphics.getHeight() / 2 - 150);
        }

        //TODO: Seriously, clean this up.
        Gdx.input.setInputProcessor(stage);

        //Unlockable button
        unlockButtonStyle.up = unlockButtonStyleImage;

        ImageButton soundButton;

        //Sound on/off button
        Gdx.app.log("Sound ON? ", String.valueOf(soundOn));
        if (!soundOn) {
            soundOn = AcidRain.getPreferences().getBoolean("soundOn");
        }
        if (!soundOn) {
            soundOffButtonStyle.up = soundOffButtonStyleImage;
            soundButton = soundOffButton;
        } else {
            soundOnButtonStyle.up = soundOnButtonStyleImage;
            soundButton = soundOnButton;
        }

        Table table = new Table();
        //table.debug();
        table.padLeft(Gdx.graphics.getWidth() / 2);
        table.padBottom(1300);
        HorizontalGroup group = new HorizontalGroup();
        group.debug();
        group.clear();
        group.setWidth(Gdx.graphics.getWidth() / 2);
        group.center();
        group.space(100);
        group.addActor(unlockButton);
        group.addActor(soundButton);
        table.add(group).center();


        stage.addActor(table);
        stage.draw();

        soundButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                soundOn = !soundOn;
                AcidRain.getPreferences().putBoolean("soundOn", soundOn);
            }
        });

        unlockButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                //TODO: Open unlocked window
                System.out.println("Button Pressed");
            }
        });
    }
}
