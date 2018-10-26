package com.liquidice.acidrain.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.liquidice.acidrain.controllers.assets.Textures;

public class GameOverScreen {
    private static final String START_OVER_TEXT = "Touch anywhere to Play Again";
    private static BitmapFont startOverFont = setFont();
    private static GlyphLayout startOverLayout = new GlyphLayout(startOverFont, START_OVER_TEXT);

    public static void display(Batch batch) {
        batch.draw(Textures.gameOver, Gdx.graphics.getWidth() / 2 - Textures.gameOver.getWidth() / 2, Gdx.graphics.getHeight() / 2 - Textures.gameOver.getHeight() / 2 + 50);
        startOverFont.draw(batch, START_OVER_TEXT, Gdx.graphics.getWidth() / 2 - startOverLayout.width / 2, Gdx.graphics.getHeight() / 2 - Textures.gameOver.getHeight() / 2);
    }

    private static BitmapFont setFont() {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(4);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        return font;
    }
}
