package com.liquidice.acidrain.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class GameOverScreen {
    private final String GAME_OVER_TEXT = "Game Over!";
    private final String START_OVER_TEXT = "Touch anywhere to Play Again";


    public GameOverScreen(Batch batch) {
        BitmapFont gameOverFont = setFont();
        BitmapFont startOverFont = setFont();
        GlyphLayout gameOverLayout = new GlyphLayout(gameOverFont, GAME_OVER_TEXT);
        GlyphLayout startOverLayout = new GlyphLayout(startOverFont, START_OVER_TEXT);
        gameOverFont.draw(batch, GAME_OVER_TEXT, Gdx.graphics.getWidth() / 2 - gameOverLayout.width / 2, Gdx.graphics.getHeight() / 2);
        startOverFont.draw(batch, START_OVER_TEXT, Gdx.graphics.getWidth() / 2 - startOverLayout.width / 2, Gdx.graphics.getHeight() / 2 - 100);
    }

    private BitmapFont setFont() {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(4);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        return font;
    }
}
