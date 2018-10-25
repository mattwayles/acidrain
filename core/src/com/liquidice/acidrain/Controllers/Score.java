package com.liquidice.acidrain.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.liquidice.acidrain.AcidRain;
import com.liquidice.acidrain.sprites.Bucket;

public class Score {
    private static double currentScore;
    private static int percentage;

    //Increase winScore to increase difficulty
    private static double winScore = AcidRain.getPreferences().getInteger("winScore", 30);


    public static void displayScore(Batch batch) {
        calculateScore();
        BitmapFont scoreFont = setScoreFont();
        String text = percentage + "%";
        GlyphLayout layout = new GlyphLayout(scoreFont, text);
        scoreFont.draw(batch, text, Gdx.graphics.getWidth() / 2 - layout.width / 2, Gdx.graphics.getHeight() - 50);
    }
    public static int getCurrentPercentage() { return percentage; }
    public static int getWinScore() { return (int) winScore; }
    public static void increaseScore(int points) { currentScore += points; }
    public static void increaseWinScore() { winScore++; }
    public static void resetScore() { currentScore = 0; }

    private static void calculateScore() {
        percentage = (int) ((currentScore / winScore) * 100);
        percentage = percentage > 100 ? 100 : percentage;
        updateBucketImage(percentage);
    }

    private static void updateBucketImage(int percentage) {
        if (percentage > 9) {
            if (percentage >= 100) {
                Bucket.setImage(new Texture("rain/bucket/bucket9.png"));
                AcidRain.setGameState(3);
            } else {
                Bucket.setImage(new Texture("rain/bucket/bucket" + Integer.toString(percentage).substring(0, 1) + ".png"));
            }
        }
    }

    private static BitmapFont setScoreFont() {
        BitmapFont font = new BitmapFont();

        font.setColor(Color.WHITE);
        font.getData().setScale(7);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        return font;
    }
}
