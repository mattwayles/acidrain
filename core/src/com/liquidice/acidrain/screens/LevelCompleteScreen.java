package com.liquidice.acidrain.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.liquidice.acidrain.managers.Gameplay;
import com.liquidice.acidrain.managers.Score;
import com.liquidice.acidrain.managers.assets.Font;
import com.liquidice.acidrain.managers.assets.Textures;

public class LevelCompleteScreen {
    private static BitmapFont nextLevelFont = Font.generatePlayFont(56, Color.WHITE);
    private static GlyphLayout nextLevelLayout = new GlyphLayout();


    public static void display(Batch batch) {
        String nextLevelText = "Touch anywhere to begin level " + (Gameplay.getLevel() + 1);
        nextLevelLayout.setText(nextLevelFont, nextLevelText);
        batch.draw(Score.getStrengthPercentage() < 100 ? Textures.levelComplete : Textures.perfectLevel,
                Gdx.graphics.getWidth() / 2 - Textures.levelComplete.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - Textures.levelComplete.getHeight() / 2 + 50);
        nextLevelFont.draw(batch, nextLevelText, Gdx.graphics.getWidth() / 2 - nextLevelLayout.width / 2, Gdx.graphics.getHeight() / 2 - Textures.levelComplete.getHeight() / 2);
    }
}
