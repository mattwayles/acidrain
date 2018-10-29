package com.liquidice.acidrain.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.liquidice.acidrain.managers.Gameplay;
import com.liquidice.acidrain.managers.assets.Font;
import com.liquidice.acidrain.managers.assets.Textures;

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
    }
}
