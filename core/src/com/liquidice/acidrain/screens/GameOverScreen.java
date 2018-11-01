package com.liquidice.acidrain.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.liquidice.acidrain.managers.assets.Font;
import com.liquidice.acidrain.managers.assets.Textures;

public class GameOverScreen {
    private static final String START_OVER_TEXT = "Touch anywhere for Main Screen";

    private static GlyphLayout startOverLayout = new GlyphLayout(Font.startOverFont, START_OVER_TEXT);

    public static void display(Batch batch) {
        batch.draw(Textures.gameOver, Gdx.graphics.getWidth() / 2 - Textures.gameOver.getWidth() / 2, Gdx.graphics.getHeight() / 2 - Textures.gameOver.getHeight() / 2 + 50);
        Font.startOverFont.draw(batch, START_OVER_TEXT, Gdx.graphics.getWidth() / 2 - startOverLayout.width / 2, Gdx.graphics.getHeight() / 2 - Textures.gameOver.getHeight() / 2);
    }
}
