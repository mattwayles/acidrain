package com.liquidice.acidrain.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.liquidice.acidrain.sprites.Bucket;

public class Properties {

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
    static final int LIGHTNING_FREQUENCY = 500;

    //COUNTER PROPERTIES
    public static final int SUNNY_COUNTER = 100;

    //SCORE PROPERTIES
     public static Color SCORE_BLUE_COLOR = Color.valueOf("#53c0e0");
     public static Color SCORE_RED_COLOR = Color.valueOf("#bf2020");
     public static int LABEL_FONT_SIZE = 56;
     public static int SCORE_FONT_SIZE = 82;
     static int STRENGTH_WARNING_LEVEL = 10;
     static int CAUGHT_SCORE_X = 70;
     static int LABEL_Y = 30;
     static int SCORE_Y = 120;
     static int STRENGTH_SCORE_X = Gdx.graphics.getWidth() - 425;


    ////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// UNLOCKABLE PROPERTIES //////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static int UNLOCKABLE_MULTIPLIERS = 0;
    public static int UNLOCKABLE_HEALTHPACK = 1;
    public static int UNLOCKABLE_UMBRELLA = 2;
    public static int UNLOCK_1_LEVEL = 1;
    public static int UNLOCK_2_LEVEL = 5;
    public static int UNLOCK_3_LEVEL = 7;
    public static int UNLOCK_4_LEVEL = 20;


    ////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////// SCREEN PROPERTIES //////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Start Screen
    public static final String BEST_SCORE_TEXT = "Best ";
    public static final String CURRENT_LEVEL_TEXT = "Level ";
    public static final String AVOID_RED_TEXT = "Smash the ACID rain!";
    public static final String CATCH_BLUE_TEXT = "Catch the CLEAN raindrops,";
    public static final int START_SCREEN_TEXT_SIZE = 56;
    public static final Color START_SCREEN_BLUE = Color.valueOf("#99d9ea");
    public static final Color START_SCREEN_RED = Color.valueOf("#ff4646");
    public static final int START_SCREEN_SPACING = 100;

    //Unlockable items screen
    public static int UNLOCKED_ITEMS_FONT_SIZE = 100;
    public static int UNLOCKED_ITEMS_Y = Gdx.graphics.getHeight() - 200;
    public static int UNLOCK_1_Y = 400;
    public static int UNLOCK_2_Y = 710;
    public static int UNLOCK_3_Y = 1020;
    public static int UNLOCK_4_Y = 1330;
    public static String UNLOCKED_ITEMS_TEXT = "Unlocked Items";

    ////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////// SPRITE PROPERTIES //////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //CLOUD PROPERTIES
     static final int CLOUD_MOVE_SPEED = 12;
     static final int DEFAULT_CLOUD_TEXTURE = 10;

    //DROP PROPERTIES
    public static final int SPLASH_LENGTH = 20;

    //BUCKET PROPERTIES
    public static final int BUCKET_HOVER = 200;

    //CITY PROPERTIES
    public static final int CITY_HEIGHT = BUCKET_HOVER - 20;


    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// POWERUP PROPERTIES //////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //UMBRELLA PROPERTIES
    public static final int UMBRELLA_ACTIVATION_TIME = 1800;

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
