package com.liquidice.acidrain.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.liquidice.acidrain.AcidRain;
import com.liquidice.acidrain.managers.assets.Audio;
import com.liquidice.acidrain.managers.assets.Font;
import com.liquidice.acidrain.managers.assets.Textures;
import com.liquidice.acidrain.sprites.Bucket;
import com.liquidice.acidrain.sprites.City;

public class Score {

    //Increase winScore / loseScore to increase difficulty
    private static double winScore = AcidRain.getPreferences().getInteger("winScore", 100);
    private static double loseScore = AcidRain.getPreferences().getInteger("loseScore", 150);
    private static int caughtPercentage;
    private static int strengthPercentage = 100;
    private static double caughtScore;
    private static double strengthScore = loseScore;
    private static BitmapFont caughtLabelFont;
    private static BitmapFont caughtScoreFont;
    private static BitmapFont strengthLabelFont;
    private static BitmapFont strengthScoreFont;
    private static GlyphLayout caughtLabelLayout = new GlyphLayout();
    private static GlyphLayout caughtScoreLayout = new GlyphLayout();
    private static GlyphLayout strengthLabelLayout  = new GlyphLayout();
    private static GlyphLayout strengthScoreLayout  = new GlyphLayout();
    private static boolean sirenPlayed = false;

    public static void initialize() {
        caughtLabelFont = Font.generatePlayFont(56, Color.valueOf("#53c0e0"), 3, Color.BLACK);
        caughtScoreFont = Font.generatePlayFont(82, Color.valueOf("#53c0e0"), 3, Color.BLACK);
        strengthLabelFont = Font.generatePlayFont(56, Color.valueOf("#bf2020"), 3, Color.BLACK);
        strengthScoreFont = Font.generatePlayFont(82, Color.valueOf("#bf2020"), 3, Color.BLACK);
    }

    public static void display(Batch batch) {
        calculateCaughtScore();
        calculateStrengthScore();

        drawCaughtScore(batch);
        drawStrengthScore(batch);
    }
    public static int getCaughtPercentage() { return caughtPercentage; }
    public static int getStrengthPercentage() { return strengthPercentage; }
    public static int getWinScore() { return (int) winScore; }
    public static int getLoseScore() { return (int) loseScore; }
    public static int getStrengthScore() { return (int) strengthScore; }
    public static void setStrengthScore(int score) { strengthScore = score; }
    public static void increaseCaughtScore(int points) { caughtScore += points; }
    public static void decreaseStrengthScore(int points) { strengthScore -= points; }
    public static void resetStrength() {
        strengthScore = loseScore;
        calculateStrengthScore();}
    public static void increaseWinScore(int num) { winScore += num; }
    public static void increaseLoseScore(int num ) { loseScore += num; }
    public static void resetScore() { caughtScore = 0; }

    private static void calculateCaughtScore() {
        caughtPercentage = (int) ((caughtScore / winScore) * 100);
        caughtPercentage = caughtPercentage > 100 ? 100 : caughtPercentage;
        updateBucketImage(caughtPercentage);
    }

    private static void calculateStrengthScore() {
        strengthPercentage = (int) ((strengthScore / loseScore) * 100);
        if (strengthPercentage <= 0) {
            strengthPercentage = 0;
            AcidRain.setGameState(2);
            sirenPlayed = false;
        } else if (strengthPercentage < 10 && !sirenPlayed) {
            Audio.playSirenSound();
            sirenPlayed = true;
        }
        updateCityImage(strengthPercentage);
    }

    private static void updateBucketImage(int percentage) {
        if (percentage > 9) {
            if (percentage >= 100) {
                sirenPlayed = false;
                City.setImage(Textures.city10);
                Bucket.setImage(Textures.rainBucket9);
                Audio.playLevelWinSound();
                AcidRain.setGameState(3);
            } else {
                int bucketToRender = Integer.parseInt(Integer.toString(percentage).substring(0, 1));
                Bucket.setImage(Textures.findRainBucketTexture(bucketToRender));
            }
        }
    }

    private static void updateCityImage(int percentage) {
        if (percentage > 9) {
            if (percentage < 100) {
                int cityToRender = Integer.parseInt(String.valueOf(Score.getStrengthPercentage()).substring(0, 1));
                City.setImage(Textures.findCityTexture(cityToRender));
            } else {
                City.setImage(Textures.city10);
            }
        } else {
            City.setImage(Textures.city1);
        }
    }

    private static void drawCaughtScore(Batch batch) {
        String label = "Clean Water";
        String score = caughtPercentage + "%";

        caughtLabelLayout.setText(caughtLabelFont, label);
        caughtScoreLayout.setText(caughtScoreFont, score);
        caughtLabelFont.draw(batch, label, 70, Gdx.graphics.getHeight() - 30);
        caughtScoreFont.draw(batch, score, 70 + caughtLabelLayout.width / 2 - caughtScoreLayout.width / 2, Gdx.graphics.getHeight() - 120);
    }

    private static void drawStrengthScore(Batch batch) {
        String label = "City Strength";
        String score = strengthPercentage + "%";

        strengthLabelLayout.setText(strengthLabelFont, label);
        strengthScoreLayout.setText(strengthScoreFont, score);
        strengthLabelFont.draw(batch, label, Gdx.graphics.getWidth() - 425, Gdx.graphics.getHeight() - 30);
        strengthScoreFont.draw(batch, score, Gdx.graphics.getWidth() - 425 + strengthLabelLayout.width / 2 - strengthScoreLayout.width / 2, Gdx.graphics.getHeight() - 120);
    }
}
