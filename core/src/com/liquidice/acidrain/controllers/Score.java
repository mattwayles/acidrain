package com.liquidice.acidrain.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.liquidice.acidrain.AcidRain;
import com.liquidice.acidrain.controllers.assets.Audio;
import com.liquidice.acidrain.controllers.assets.Textures;
import com.liquidice.acidrain.sprites.Bucket;

public class Score {

    //Increase winScore / loseScore to increase difficulty
    private static double winScore = AcidRain.getPreferences().getInteger("winScore", 50);
    private static double loseScore = AcidRain.getPreferences().getInteger("loseScore", 50);

    private static int caughtPercentage;
    private static int strengthPercentage = 100;
    private static double caughtScore;
    private static double strengthScore = loseScore;
    private static BitmapFont caughtLabelFont = new BitmapFont();
    private static BitmapFont caughtScoreFont = new BitmapFont();
    private static BitmapFont strengthLabelFont = new BitmapFont();
    private static BitmapFont strengthScoreFont = new BitmapFont();
    private static GlyphLayout caughtLabelLayout = new GlyphLayout();
    private static GlyphLayout caughtScoreLayout = new GlyphLayout();
    private static GlyphLayout strengthLabelLayout  = new GlyphLayout();
    private static GlyphLayout strengthScoreLayout  = new GlyphLayout();
    private static Texture fullBucketTexture = Textures.rainBucket9;


    public static void display(Batch batch) {
        calculateCaughtScore();
        calculateStrengthScore();

        drawCaughtScore(batch);
        drawStrengthScore(batch);
    }
    public static int getCaughtPercentage() { return caughtPercentage; }
    public static int getWinScore() { return (int) winScore; }
    public static int getLoseScore() { return (int) loseScore; }
    public static void increaseCaughtScore(int points) { caughtScore += points; }
    public static void decreaseStrengthScore(int points) { strengthScore -= points; }
    public static void resetStrength() { strengthScore = loseScore; }
    public static void increaseWinScore() { winScore++; }
    public static void decreaseLoseScore() { loseScore--; }
    public static void resetScore() { caughtScore = 0; }

    private static void calculateCaughtScore() {
        caughtPercentage = (int) ((caughtScore / winScore) * 100);
        caughtPercentage = caughtPercentage > 100 ? 100 : caughtPercentage;
        updateBucketImage(caughtPercentage);
    }

    private static void calculateStrengthScore() {
        strengthPercentage = (int) ((strengthScore / loseScore) * 100);
        strengthPercentage = strengthPercentage < 0 ? 0 : strengthPercentage;
        //TODO: UPDATE city image with RED gradient
    }

    private static void updateBucketImage(int percentage) {
        if (percentage > 9) {
            if (percentage >= 100) {
                Bucket.setImage(fullBucketTexture);
                Audio.playLevelWinSound();
                AcidRain.setGameState(3);
            } else {
                int bucketToRender = Integer.parseInt(Integer.toString(percentage).substring(0, 1));
                Bucket.setImage(Textures.findRainBucketTexture(bucketToRender));
            }
        }
    }

    private static void drawCaughtScore(Batch batch) {
        Color color = Color.valueOf("#99d9ea");

        formatFont(caughtLabelFont, color, 4);
        formatFont(caughtScoreFont, color, 7);

        String label = "Clean Water";
        String score = caughtPercentage + "%";

        caughtLabelLayout.setText(caughtLabelFont, label);
        caughtScoreLayout.setText(caughtScoreFont, score);
        caughtLabelFont.draw(batch, label, 50, Gdx.graphics.getHeight() - 50);
        caughtScoreFont.draw(batch, score, 50 + caughtLabelLayout.width / 2 - caughtScoreLayout.width / 2, Gdx.graphics.getHeight() - 150);
    }

    private static void drawStrengthScore(Batch batch) {
        Color color = Color.valueOf("#ff4646");

        formatFont(strengthLabelFont, color, 4);
        formatFont(strengthScoreFont, color, 7);

        String label = "City Strength";
        String score = strengthPercentage + "%";

        strengthLabelLayout.setText(strengthLabelFont, label);
        strengthScoreLayout.setText(strengthScoreFont, score);
        strengthLabelFont.draw(batch, label, Gdx.graphics.getWidth() - 400, Gdx.graphics.getHeight() - 50);
        strengthScoreFont.draw(batch, score, Gdx.graphics.getWidth() - 400 + strengthLabelLayout.width / 2 - strengthScoreLayout.width / 2, Gdx.graphics.getHeight() - 150);
    }

    private static void formatFont(BitmapFont font, Color color, int size) {
        font.getData().setScale(size);
        font.setColor(color);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }
}
