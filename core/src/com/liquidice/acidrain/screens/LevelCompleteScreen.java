package com.liquidice.acidrain.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.liquidice.acidrain.controllers.assets.Textures;

public class LevelCompleteScreen {

    //TODO: Clouds clear, sun comes in
    //TODO: Stop thunderstorm music, start birds chirping

    private static BitmapFont nextLevelFont = setFont();
    private static GlyphLayout nextLevelLayout = new GlyphLayout();


    public static void display(Batch batch) {
        String nextLevelText = "Touch anywhere to begin next level...";
        nextLevelLayout.setText(nextLevelFont, nextLevelText);
//TODO: Sunny background
//        batch.draw(Textures.sunnyBackground, 0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch.draw(Textures.levelComplete, Gdx.graphics.getWidth() / 2 - Textures.levelComplete.getWidth() / 2, Gdx.graphics.getHeight() / 2 - Textures.levelComplete.getHeight() / 2 + 50);
        nextLevelFont.draw(batch, nextLevelText, Gdx.graphics.getWidth() / 2 - nextLevelLayout.width / 2, Gdx.graphics.getHeight() / 2 - Textures.levelComplete.getHeight() / 2);
    }

    private static BitmapFont setFont() {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(4);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        return font;
    }
}
