package com.liquidice.acidrain.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import static com.liquidice.acidrain.Controllers.Gameplay.getLevel;
import static com.liquidice.acidrain.Controllers.Gameplay.getLevelBest;

public class StartScreen {
    private final Texture LOGO = new Texture("logo.png");
    private final String TOUCH_ANYWHERE_TEXT = "Touch anywhere to begin";
    private final String CURRENT_LEVEL_TEXT = "Level ";
    private final String BEST_SCORE_TEXT = "Best ";
    private final String CATCH_BLUE_TEXT = "Catch the CLEAN raindrops,";
    private final String AVOID_RED_TEXT = "Avoid the ACID rain!";


    public StartScreen(Batch batch) {
        BitmapFont touchAnywhereFont = setTouchAnywhereFont();
        BitmapFont catchBlueFont = setCatchBlueFont();
        BitmapFont avoidRedFont = setAvoidRedFont();
        BitmapFont currentLevelFont = setCurrentLevelFont();
        BitmapFont bestScoreFont = setBestScoreFont();

        GlyphLayout touchAnywhereLayout = new GlyphLayout(touchAnywhereFont, TOUCH_ANYWHERE_TEXT);
        GlyphLayout currentLevelLayout = new GlyphLayout(currentLevelFont, CURRENT_LEVEL_TEXT + getLevel());
        GlyphLayout bestScoreLayout = new GlyphLayout(bestScoreFont, BEST_SCORE_TEXT + getLevelBest() + "%");
        GlyphLayout catchBlueLayout = new GlyphLayout(catchBlueFont, CATCH_BLUE_TEXT);
        GlyphLayout avoidRedLayout = new GlyphLayout(avoidRedFont, AVOID_RED_TEXT);

        batch.draw(LOGO, Gdx.graphics.getWidth() / 2 - LOGO.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        touchAnywhereFont.draw(batch, TOUCH_ANYWHERE_TEXT, Gdx.graphics.getWidth() / 2 - touchAnywhereLayout.width / 2, Gdx.graphics.getHeight() / 2 - LOGO.getHeight() / 2 - 20);

        if (getLevel() == 1 && getLevelBest() == 0) {
            catchBlueFont.draw(batch, CATCH_BLUE_TEXT, Gdx.graphics.getWidth() / 2 - catchBlueLayout.width / 2, Gdx.graphics.getHeight() / 2 - 50);
            avoidRedFont.draw(batch, AVOID_RED_TEXT, Gdx.graphics.getWidth() / 2 - avoidRedLayout.width / 2, Gdx.graphics.getHeight() / 2 - 150);
        } else {
            currentLevelFont.draw(batch, CURRENT_LEVEL_TEXT + getLevel(), Gdx.graphics.getWidth() / 2 - currentLevelLayout.width / 2, Gdx.graphics.getHeight() / 2 - 50);
            bestScoreFont.draw(batch, BEST_SCORE_TEXT + getLevelBest() + "%", Gdx.graphics.getWidth() / 2 - bestScoreLayout.width / 2, Gdx.graphics.getHeight() / 2 - 150);
        }
    }

    private BitmapFont setTouchAnywhereFont() {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(4);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        return font;
    }

    private BitmapFont setCurrentLevelFont() {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.valueOf("#ff4646"));
        font.getData().setScale(5);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        return font;
    }

    private BitmapFont setBestScoreFont() {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.valueOf("#99d9ea"));
        font.getData().setScale(5);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        return font;
    }

    private BitmapFont setAvoidRedFont() {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.valueOf("#ff4646"));
        font.getData().setScale(4);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        return font;
    }

    private BitmapFont setCatchBlueFont() {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.valueOf("#99d9ea"));
        font.getData().setScale(4);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        return font;
    }
}
