package com.liquidice.acidrain.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.liquidice.acidrain.managers.Counter;
import com.liquidice.acidrain.managers.Gameplay;
import com.liquidice.acidrain.managers.Powerup;
import com.liquidice.acidrain.managers.Properties;
import com.liquidice.acidrain.managers.Score;
import com.liquidice.acidrain.managers.assets.Font;
import com.liquidice.acidrain.managers.assets.Textures;
import com.liquidice.acidrain.screens.unlockables.UnlockedScreen;

public class LevelCompleteScreen {

    private static GlyphLayout nextLevelLayout = new GlyphLayout();

    public static void display(Batch batch) {
        if (Counter.getSunnyCount() == Properties.SUNNY_COUNTER &&
                (Gameplay.getLevel() == Properties.UNLOCK_1_LEVEL
                        || Gameplay.getLevel() == Properties.UNLOCK_2_LEVEL
                        || Gameplay.getLevel() == Properties.UNLOCK_3_LEVEL)) {
            if (Gameplay.getLevel() == Properties.UNLOCK_1_LEVEL) {
                UnlockedScreen.display(Textures.multipliers, "Score Multipliers!", "Earn up to x6 points from a Single Drop");
            } else if (Gameplay.getLevel() == Properties.UNLOCK_2_LEVEL) {
                UnlockedScreen.display(Textures.healthPack, "Health Pack!", "Increase City Strength by 25%");
            } else if (Gameplay.getLevel() == Properties.UNLOCK_3_LEVEL) {
                UnlockedScreen.display(Textures.umbrella, "Umbrella!", "Extra Protection for 30 seconds");
            }
        }
        else {
            Powerup.deactivateAllPowerups();
            String nextLevelText = "Touch anywhere to begin level " + (Gameplay.getLevel() + 1);
            nextLevelLayout.setText(Font.nextLevelFont, nextLevelText);
            batch.draw(Score.getStrengthPercentage() < 100 ? Textures.levelComplete : Textures.perfectLevel,
                    Gdx.graphics.getWidth() / 2 - Textures.levelComplete.getWidth() / 2,
                    Gdx.graphics.getHeight() / 2 - Textures.levelComplete.getHeight() / 2 + 50);
            Font.nextLevelFont.draw(batch, nextLevelText, Gdx.graphics.getWidth() / 2 - nextLevelLayout.width / 2, Gdx.graphics.getHeight() / 2 - Textures.levelComplete.getHeight() / 2);
        }
    }
}
