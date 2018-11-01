package com.liquidice.acidrain.managers.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.liquidice.acidrain.managers.Properties;

public class Font {
    private static FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Play.ttf"));

    //StartScreen
//    public static BitmapFont catchBlueFont = Font.generatePlayFont(56, Color.valueOf("#99d9ea"));
//    public static BitmapFont avoidRedFont = Font.generatePlayFont(56, Color.valueOf("#ff4646"));
//    public static BitmapFont bestScoreFont = Font.generatePlayFont(56, Color.valueOf("#ff4646"));
//    public static BitmapFont currentLevelFont = Font.generatePlayFont(56, Color.valueOf("#99d9ea"));

    //Score
    public static BitmapFont caughtLabelFont;
    public static BitmapFont caughtScoreFont;
    public static BitmapFont strengthLabelFont;
    public static BitmapFont strengthScoreFont;

    //UnlockablesScreen
    public static BitmapFont unlockedItemFont;

    //GameOverScreen
    public static BitmapFont startOverFont;

    //LevelCompleteScreen
    public static BitmapFont nextLevelFont;

    //UnlockedScreen
    public static BitmapFont youveUnlockedFont;
    public static BitmapFont powerupTypeFont;
    public static BitmapFont powerupFont;
    public static BitmapFont holdFont;



    public static void initialize() {
        //StartScreen
//        catchBlueFont = Font.generatePlayFont(56, Color.valueOf("#99d9ea"));
//        avoidRedFont = Font.generatePlayFont(56, Color.valueOf("#ff4646"));
//        bestScoreFont = Font.generatePlayFont(56, Color.valueOf("#ff4646"));
//        currentLevelFont = Font.generatePlayFont(56, Color.valueOf("#99d9ea"));

        //Score
        caughtLabelFont = Font.generatePlayFont(Properties.LABEL_FONT_SIZE, Properties.SCORE_BLUE_COLOR, 3, Color.BLACK);
        caughtScoreFont = Font.generatePlayFont(Properties.SCORE_FONT_SIZE, Properties.SCORE_BLUE_COLOR, 3, Color.BLACK);
        strengthLabelFont = Font.generatePlayFont(Properties.LABEL_FONT_SIZE, Properties.SCORE_RED_COLOR, 3, Color.BLACK);
        strengthScoreFont = Font.generatePlayFont(Properties.SCORE_FONT_SIZE, Properties.SCORE_RED_COLOR, 3, Color.BLACK);

        //UnlockablesScreen
        unlockedItemFont = Font.generatePlayFont(Properties.UNLOCKED_ITEMS_FONT_SIZE, Color.GOLD, 6, Color.BLACK);

        //GameOverScreen
        startOverFont = Font.generatePlayFont(56, Color.WHITE);

        //LevelCompleteScreen
        nextLevelFont = Font.generatePlayFont(56, Color.WHITE);

        //UnlockedScreen
        youveUnlockedFont = Font.generatePlayFont(100, Color.GOLD, 6, Color.BLACK);
        powerupTypeFont = Font.generatePlayFont(56, Color.GOLD, 6, Color.BLACK);
        powerupFont = Font.generatePlayFont(48, Color.BLACK);
        holdFont = Font.generatePlayFont(42, Color.WHITE, 3, Color.BLACK);
    }

    private static BitmapFont generatePlayFont(int size, Color color) {
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.color = color;
        return generator.generateFont(parameter);
    }

    private static BitmapFont generatePlayFont(int size, Color color, int border, Color borderColor) {
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.color = color;
        parameter.borderWidth = border;
        parameter.borderColor = borderColor;
        return generator.generateFont(parameter);
    }
}