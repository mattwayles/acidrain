package com.liquidice.acidrain.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class LevelCompleteScreen {

    //TODO: Clouds clear, sun comes in
    //TODO: Stop thunderstorm music, start birds chirping

    private final String NEXT_LEVEL_TEXT = "Touch anywhere to begin next level...";

    public LevelCompleteScreen(Batch batch, int level) {
        String levelCompleteText = "Level " + level + " Complete!";
        BitmapFont levelCompleteFont = setFont();
        BitmapFont nextLevelFont = setFont();

        GlyphLayout levelCompleteLayout = new GlyphLayout(levelCompleteFont, levelCompleteText);
        GlyphLayout nextLevelLayout = new GlyphLayout(nextLevelFont, NEXT_LEVEL_TEXT);
        levelCompleteFont.draw(batch, levelCompleteText, Gdx.graphics.getWidth() / 2 - levelCompleteLayout.width / 2, Gdx.graphics.getHeight() / 2);
        nextLevelFont.draw(batch, NEXT_LEVEL_TEXT, Gdx.graphics.getWidth() / 2 - nextLevelLayout.width / 2, Gdx.graphics.getHeight() / 2 - 100);
    }

    private BitmapFont setFont() {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(4);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        return font;
    }
}
