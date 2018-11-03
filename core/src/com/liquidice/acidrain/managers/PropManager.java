package com.liquidice.acidrain.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

public class PropManager {

    //TODO: PROPERTIES for all Magic Numbers

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// MANAGER PROPERTIES //////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //GAMEPLAY PROPERTIES
     static int DEFAULT_START_LEVEL = 1;
     static int DEFAULT_MAX_SPEED = 5;
     static int DEFAULT_MIN_SPEED = 2;
     static int DEFAULT_RAIN_FREQUENCY = 90;
     static int DEFAULT_ACID_FREQUENCY = 140;
     static int CUTOFF_LEVEL = 10;
     static float SPEED_L1_9_INCREASE = .3f;
     static float SPEED_L10_INCREASE = .1f;
     static float RAIN_L1_9_INCREASE = 5f;
     static float RAIN_L10_DECREASE = 2f;
     static float ACID_L1_9_INCREASE = 10f;
     static float ACID_L10_INCREASE = 2f;
     static int SCORE_L1_9_INCREASE = 10;
     static int SCORE_L10_INCREASE = 1;

    //BACKGROUND PROPERTIES
    public static final int LIGHTNING_FREQUENCY = 500;

    //COUNTER PROPERTIES
    public static final int SUNNY_COUNTER = 100;

    //SCORE PROPERTIES
     static Color SCORE_BLUE_COLOR = Color.valueOf("#53c0e0");
     static Color SCORE_RED_COLOR = Color.valueOf("#bf2020");
     static int SCORE_FONT_SIZE = 82;


    ////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// UNLOCKABLE PROPERTIES //////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static int UNLOCKABLE_MULTIPLIERS = 0;
    public static int UNLOCKABLE_HEALTHPACK = 1;
    public static int UNLOCKABLE_UMBRELLA = 2;
    public static int UNLOCK_1_LEVEL = 3;
    public static int UNLOCK_2_LEVEL = 5;
    public static int UNLOCK_3_LEVEL = 8;
    public static int UNLOCK_4_LEVEL = 20;
    public static int UNLOCKABLE_SCORE_MULTIPLIER = 10;
    public static double UNLOCKABLE_HEALTHPACK_MULTIPLIER = .25;


    ////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////// SCREEN PROPERTIES //////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Start ScreenManager
    public static final String BEST_SCORE_TEXT = "Best ";
    public static final String CURRENT_LEVEL_TEXT = "Level ";
    public static final String AVOID_RED_TEXT = "Smash the ACID rain!";
    public static final String CATCH_BLUE_TEXT = "Catch the CLEAN raindrops,";
    static final int START_SCREEN_TEXT_SIZE = 56;
    public static final int START_SCREEN_SPACING = 100;

    //Unlockable items screen
    static int UNLOCKED_ITEMS_FONT_SIZE = 100;
    public static int UNLOCKED_ITEMS_Y = Gdx.graphics.getHeight() - 200;
    public static int UNLOCK_1_Y = 400;
    public static int UNLOCK_2_Y = 710;
    public static int UNLOCK_3_Y = 1020;
    public static int UNLOCK_4_Y = 1330;
    public static String UNLOCKED_ITEMS_TEXT = "Unlocked Items";

    //Unlocked Screen
    public static String POWERUP_MULTIPLIER_TITLE = "Score Multipliers!";
    public static String POWERUP_MULTIPLIER_DESC = "Earn up to x6 points from a Single Drop";
    public static String POWERUP_HEALTHPACK_TITLE = "Health Pack!";
    public static String POWERUP_HEALTHPACK_DESC = "Increase City Strength by 25%";
    public static String POWERUP_UMBRELLA_TITLE = "Umbrella!";
    public static String POWERUP_UMBRELLA_DESC = "Extra Protection for 30 seconds";
    public static String ITEM_UNLOCKED_TEXT = "Powerup Unlocked!";
    public static String UNLOCKED_HOLD_TEXT = "Long touch to play Level " + (GameplayManager.getLevel() + 1);
    public static int UNLOCKED_ITEM_ANIMATION_HEIGHT = 800;
    public static int UNLOCKED_ITEM_HEADER_HEIGHT = 200;
    public static int UNLOCKED_ITEM_TITLE_HEIGHT = 900;
    public static int UNLOCKED_ITEM_POWER_HEIGHT = 1150;
    public static int UNLOCKED_ITEM_HOLD_HEIGHT = 1450;
    static int UNLOCKED_FONT_SIZE = 48;
    static int UNLOCKED_HOLD_FONT_SIZE = 42;
    public static int UNLOCKED_ITEM_SIZE_MULTIPLIER = 4;
    public static int UNLOCKED_ITEM_BOUNCE = 6;

    //Level Complete Screen
    public static String NEXT_LEVEL_TEXT = "Touch anywhere to begin Level " + (GameplayManager.getLevel() + 1);
    public static int NORTH_OF_CENTER = 50;
    public static int PERFECT_SCORE = 100;

    //Game Over Screen
    public static String GAME_OVER_TEXT = "Touch anywhere for Main Screen";
    
    //Score Overlay Screen
    public static String CLEAN_WATER_TEXT = "Clean Water";
    public static String CLEAN_WATER_PERCENT = ScoreManager.getCaughtPercentage() + "%";
    public static String CITY_STRENGTH_TEXT = "City Strength";
    public static String CITY_STRENGTH_PERCENT = ScoreManager.getStrengthPercentage() + "%";
    public static int STRENGTH_WARNING_LEVEL = 10;
    public static int CAUGHT_SCORE_X = 70;
    public static int LABEL_Y = 30;
    public static int SCORE_Y = 120;
    public static int STRENGTH_SCORE_X = Gdx.graphics.getWidth() - 425;


    ////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////// SPRITE PROPERTIES //////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //CLOUD PROPERTIES
     public static final int CLOUD_MOVE_SPEED = 12;
     public static final int DEFAULT_CLOUD_TEXTURE = 10;
     public static final int DEFAULT_CLOUD_X = -100;
     public static final int CLOUD_EXTRA_WIDTH = 200;

    //DROP PROPERTIES
    public static final int SPLASH_LENGTH = 20;
    public static final int DROP_SIZE_MIN = 2;
    public static final int DROP_SIZE_MAX = 7;

    //BUCKET PROPERTIES
    public static final int BUCKET_HOVER = 200;
    public static final int BUCKET_SPEED = 25;
    public static final int BUCKET_RECT_TOP_HEIGHT = 10;
    public static final int BUCKET_RECT_LEFT_WIDTH = 10;
    public static final int BUCKET_RECT_RIGHT_WIDTH = 10;


    //CITY PROPERTIES
    public static final int CITY_HEIGHT = BUCKET_HOVER - 20;

    //BUTTON PROPERTIES
    public static final String PAUSED = "Game Paused";
    public static final int PAUSE_BUTTON_HEIGHT = 40;
    public static final int PLAY_STOP_BUTTON_HEIGHT = 50;



    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// POWERUP PROPERTIES //////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //UMBRELLA PROPERTIES
    public static final int UMBRELLA_ACTIVATION_TIME = 1800;
    public static final int UMBRELLA_OFFSET = 20;
    public static final int UMBRELLA_HEIGHT = 10;

    public static int POWERUP_CHANCE = 25;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// STATE PROPERTIES ////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static int GAME_START_STATE = 0;
    public static int GAME_PLAY_STATE = 1;
    public static int GAME_OVER_STATE = 2;
    public static int LEVEL_COMPLETE_STATE =3;


    ////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////// SHARED PREF KEY PROPERTIES ///////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static String SHARED_PREF_SOUND_ON = "soundOn";
    
}
