package com.liquidice.acidrain.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

/**
 * Manage application properties, numbers and strings
 */
public class PropManager {

    //TODO: Organize this a little better, name things better, it'll be a big effort

    ////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// UNLOCKABLE PROPERTIES //////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static int UNLOCKABLE_MULTIPLIERS = 0;
    public static int UNLOCKABLE_HEALTHPACK = 1;
    public static int UNLOCKABLE_UMBRELLA = 2;
    public static int UNLOCKABLE_SHIELD = 3;
    public static int UNLOCKABLE_FILTRATION = 4;

    public static int UNLOCK_1_LEVEL = 5;
    public static int UNLOCK_2_LEVEL = 10;
    public static int UNLOCK_3_LEVEL = 15;
    public static int UNLOCK_4_LEVEL = 20;
    public static int UNLOCK_5_LEVEL = 25;
    public static int UNLOCKABLE_SCORE_MULTIPLIER = 10;
    public static double UNLOCKABLE_HEALTHPACK_MULTIPLIER = .25;


    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// MANAGER PROPERTIES //////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //GAMEPLAY PROPERTIES
     static int DEFAULT_START_LEVEL = 1;
     static float DEFAULT_MAX_SPEED = 4f;
     static float DEFAULT_MIN_SPEED = 1.5f;
     static int DEFAULT_RAIN_FREQUENCY = 50;
     static int DEFAULT_ACID_FREQUENCY = 140;
     static int DEFAULT_WIN_SCORE = 150;
     static int DEFAULT_LOSE_SCORE = 150;
     static int CUTOFF_LEVEL = 10;
     static float SPEED_L1_9_INCREASE = .3f;
     static float SPEED_L10_INCREASE = .05f;
     static float RAIN_L10_DECREASE = 1f;
     static float ACID_L1_9_INCREASE = 7f;
     static float ACID_L10_INCREASE = 1f;
     static int SCORE_L1_9_INCREASE = 10;
     static int SCORE_L10_INCREASE = 1;

    //BACKGROUND PROPERTIES
    public static final int LIGHTNING_FREQUENCY = 500;

    //COUNTER PROPERTIES
    public static final int SUNNY_COUNTER = 100;

    //SCORE PROPERTIES
     public static Color SCORE_BLUE_COLOR = Color.valueOf("#53c0e0");
     public static Color SCORE_RED_COLOR = Color.valueOf("#bf2020");


    ////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////// SCREEN PROPERTIES //////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Start ScreenManager
    public static final String BEST_SCORE_TEXT = "Best ";
    public static final String CURRENT_LEVEL_TEXT = "Level ";
    public static final String AVOID_RED_TEXT = "Smash the ACID rain!";
    public static final String CATCH_BLUE_TEXT = "Catch the CLEAN raindrops,";
    public static final int START_SCREEN_SPACING = 100;
    public static final int TABLE_TOP_PADDING = 1050;
    public static final int TABLE_VERTICAL_SPACING = 100;
    public static final int TABLE_HORIZONTAL_SPACING = 150;

    //Unlockable items screen
    public static int UNLOCKED_ITEMS_Y = Gdx.graphics.getHeight() - 200;
    public static int UNLOCKED_ITEMS_SCROLL_Y = 400;
    public static int UNLOCKED_ITEMS_SCROLL_OFFSET = 20;
    public static String UNLOCKED_ITEMS_TEXT = "Unlocked Items";

    //Unlocked Screen
    public static String POWERUP_MULTIPLIER_TITLE = "SCORE MULTIPLIERS";
    public static String POWERUP_MULTIPLIER_DESC = "Earn up to x6 points from a Single Drop";
    public static String POWERUP_HEALTHPACK_TITLE = "HEALTH PACK";
    public static String POWERUP_HEALTHPACK_DESC = "Increase City Strength by 25%";
    public static String POWERUP_UMBRELLA_TITLE = "UMBRELLA";
    public static String POWERUP_UMBRELLA_DESC = "Extra Protection for 30 seconds";
    public static String POWERUP_SHIELD_TITLE = "SHIELD";
    public static String POWERUP_SHIELD_DESC = "Total city Protection for 30 seconds";
    public static String POWERUP_FILTRATION_TITLE = "FILTRATION SYSTEM";
    public static String POWERUP_FILTRATION_DESC = "Filter acid from rain for 10 seconds";
    public static int POWERUP_ANIMATION_TIME = 10;
    public static String ITEM_UNLOCKED_TEXT = "Powerup Unlocked!";
    public static String UNLOCKED_HOLD_TEXT = "Touch anywhere to play Level ";
    public static int UNLOCKED_ITEM_ANIMATION_HEIGHT = 800;
    public static int UNLOCKED_ITEM_HEADER_HEIGHT = 200;
    public static int UNLOCKED_ITEM_TITLE_HEIGHT = 900;
    public static int UNLOCKED_ITEM_POWER_HEIGHT = 1150;
    public static int UNLOCKED_ITEM_HOLD_HEIGHT = 1450;
    public static int UNLOCKED_ITEM_SIZE_MULTIPLIER = 4;
    public static int UNLOCKED_ITEM_BOUNCE = 6;

    //Level Complete Screen
    public static String NEXT_LEVEL_TEXT = "Touch anywhere to begin Level ";
    public static int NORTH_OF_CENTER = 50;
    public static int PERFECT_SCORE = 100;

    //Game Over Screen
    public static String GAME_OVER_TEXT = "Touch anywhere for Main Screen";
    
    //Score Overlay Screen
    public static String CLEAN_WATER_TEXT = "Clean Water";
    public static String CITY_STRENGTH_TEXT = "City Strength";
    public static int STRENGTH_WARNING_LEVEL = 10;
    public static int CAUGHT_SCORE_X = 100;
    public static int LABEL_Y = 30;
    public static int SCORE_Y = 120;
    public static int STRENGTH_SCORE_X = Gdx.graphics.getWidth() - 400;


    ////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////// SPRITE PROPERTIES //////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //CLOUD PROPERTIES
     public static final int CLOUD_MOVE_SPEED = 12;
     public static final int DEFAULT_CLOUD_X = -100;
     public static final int CLOUD_EXTRA_WIDTH = 200;

    //DROP PROPERTIES
    public static final int SPLASH_LENGTH = 20;
    public static final int DROP_SIZE_MIN = 2;
    public static final int DROP_SIZE_MAX = 7;
    public static final int DROP_SIZE_MAX_POINTS = 10;
    public static final int DROP_SMASH_VIBRATE_TIME = 15;
    public static final int RAINDROP_CAUGHT_VIBRATE_TIME = 30;
    public static final int ACIDDROP_CAUGHT_VIBRATE_TIME = 250;
    public static final int POWERDROP_CAUGHT_VIBRATE_TIME = 100;

    //BUCKET PROPERTIES
    public static final int BUCKET_HOVER = 200;
    public static final int BUCKET_SPEED = 25;
    public static final int BUCKET_RECT_TOP_HEIGHT = 1;
    public static final int BUCKET_TOP_OFFSET = 55;
    public static final int BUCKET_SIDE_OFFSET = 2;


    //CITY PROPERTIES
    public static final int CITY_HEIGHT = BUCKET_HOVER - 20;

    //BUTTON PROPERTIES
    public static final String PAUSED = "Game Paused";
    public static final int PAUSE_BUTTON_HEIGHT = 40;
    public static final int PLAY_STOP_BUTTON_HEIGHT = 50;



    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// POWERUP PROPERTIES //////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //CHANCE
    public static int POWERUP_CHANCE_TOTAL = 25;
    public static int POWERUP_CHANCE = 15;
    public static int HEALTHPACK_CHANCE = 16;
    public static int UMBRELLA_CHANCE = 17;
    public static int SHIELD_CHANCE = 18;
    public static int FILTER_CHANCE = 19;

    //UMBRELLA PROPERTIES
    public static final int UMBRELLA_ACTIVATION_TIME = 1800;
    public static final int UMBRELLA_OFFSET = 20;
    public static final int UMBRELLA_HEIGHT = 10;

    //SHIELD PROPERTIES
    public static final int SHIELD_ACTIVATION_TIME = 1800;

    //FILTER PROPERTIES
    public static final int FILTER_ACTIVATION_TIME = 600;

    //COUNTDOWN
     static final int COUNTDOWN_OFFSET = 5;

    //SECONDS
     static final int ONE_SECOND = 60;
     static final String ONE = "1";
     static final int TWO_SECONDS = 120;
     static final String TWO = "2";
     static final int THREE_SECONDS = 180;
     static final String THREE = "3";



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
    public static String PREF_SOUND_ON = "soundOn";
    static String PREF_LEVEL = "level";
    public static String PREF_LEVEL_BEST = "levelBest";
    static String PREF_MAX_SPEED = "maxSpeed";
    static String PREF_MIN_SPEED = "minSpeed";
    static String PREF_RAIN_FREQ = "rainFreq";
    static String PREF_ACID_FREQ = "acidFreq";
    static String PREF_WIN_SCORE = "winScore";
    static String PREF_LOSE_SCORE = "loseScore";


    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// ASSET PATH PROPERTIES ///////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //Fonts
    public static String FONT_PLAY_56 = "font/play56.fnt";
    public static String  FONT_PLAY_56_PNG = "font/play56.png";
    public static String FONT_PLAY_68 = "font/play68.fnt";
    public static String  FONT_PLAY_68_PNG = "font/play68.png";
    public static String FONT_PLAY_82 = "font/play82.fnt";
    public static String  FONT_PLAY_82_PNG = "font/play82.png";
    public static String FONT_PLAY_100 = "font/play100.fnt";
    public static String  FONT_PLAY_100_PNG = "font/play100.png";


    //Prefix - Suffix
    public static String PNG = ".png";
    public static String RAIN_DROP_PREFIX = "rain/drop/drop";
    public static String ACID_DROP_PREFIX = "acid/drop/acid";
    public static String BUCKET_PREFIX = "bucket/bucket";
    public static String CITY_PREFIX = "city/city";
    public static String POWER_DROP_PREFIX = "unlockables/powerDrop/powerDrop";
    public static String SUNNY_SKY_PREFIX = "backgrounds/sunnySkyBackground";

    //Texture Assets
    public static final String TEXTURE_ACID_SPLASH = "acid/splash/acidSplash.png";
    public static final String TEXTURE_ACID_SPLASH_LEFT = "acid/splash/acidSplashLeft.png";
    public static final String TEXTURE_ACID_SPLASH_RIGHT = "acid/splash/acidSplashRight.png";
    public static final String TEXTURE_BUCKET_0 = "bucket/bucket0.png";
    public static final String TEXTURE_BUCKET_9 = "bucket/bucket9.png";
    public static final String TEXTURE_BG_STORM = "backgrounds/stormBackground.png";
    public static final String TEXTURE_BG_LIGHTNING = "backgrounds/lightningBackground.jpg";
    public static final String TEXTURE_CITY_1 = "city/city1.png";
    public static final String TEXTURE_CITY_10 = "city/city10.png";
    static final String TEXTURE_CLOUDS = "clouds.png";
    public static final String TEXTURE_FILTRATION_DROP = "unlockables/filtration/filtrationDrop.png";
    public static final String TEXTURE_FILTRATION_UNLOCK = "unlockables/filtration/unlockableFiltration.png";
    public static final String TEXTURE_PLACEHOLDER = "placeholder.png";
    public static final String TEXTURE_RAIN_SPLASH = "rain/splash/rainSplash.png";
    public static final String TEXTURE_RAIN_SPLASH_LEFT = "rain/splash/rainSplashLeft.png";
    public static final String TEXTURE_RAIN_SPLASH_RIGHT = "rain/splash/rainSplashRight.png";
    public static final String TEXTURE_SCREEN_UNLOCKED = "screen/unlocked.jpg";
    public static final String TEXTURE_TEXT_GAME_OVER = "text/gameOver.png";
    public static final String TEXTURE_TEXT_LEVEL_COMPLETE = "text/levelComplete.png";
    public static final String TEXTURE_TEXT_LOGO = "text/logo.png";
    public static final String TEXTURE_TEXT_PERFECT_LEVEL = "text/perfectLevel.png";
    public static final String TEXTURE_HEALTHPACK_DROP = "unlockables/healthPack/healthPackDrop.png";
    public static final String TEXTURE_HEALTHPACK_UNLOCK = "unlockables/healthPack/unlockableHealthPack.png";
    public static final String TEXTURE_MULTIPLIER_DROP = "unlockables/powerDrop/multipliersDrop.png";
    public static final String TEXTURE_MULTIPLIER_UNLOCK = "unlockables/powerDrop/unlockableMultipliers.png";
    static final String TEXTURE_SHIELD = "unlockables/shield/shield.jpg";
    public static final String TEXTURE_SHIELD_DROP = "unlockables/shield/shieldDrop.png";
    public static final String TEXTURE_SHIELD_UNLOCK = "unlockables/shield/unlockableShield.png";
    public static final String TEXTURE_SUNNY_SKY_10 = "backgrounds/sunnySkyBackground10.png";
    public static final String TEXTURE_UMBRELLA_DROP = "unlockables/umbrella/umbrellaDrop.png";
    static final String TEXTURE_UMBRELLA_LEFT = "unlockables/umbrella/umbrellaLeft.png";
    static final String TEXTURE_UMBRELLA_RIGHT = "unlockables/umbrella/umbrellaRight.png";
    public static final String TEXTURE_UMBRELLA_UNLOCK = "unlockables/umbrella/unlockableUmbrella.png";
    public static final String TEXTURE_LOCKED_UNLOCK = "unlockables/unlockableLocked.png";


    //Button Assets
    public static final String BUTTON_CLOSE = "buttons/closeButton.png";
    public static final String BUTTON_HELP = "buttons/helpButton.png";
    public static final String BUTTON_PAUSE = "buttons/pauseButton.png";
    public static final String BUTTON_PLAY = "buttons/playButton.png";
    public static final String BUTTON_SOUND_OFF = "buttons/soundOffButton.png";
    public static final String BUTTON_SOUND_ON = "buttons/soundOnButton.png";
    public static final String BUTTON_START = "buttons/startButton.png";
    public static final String BUTTON_STOP = "buttons/stopButton.png";
    public static final String BUTTON_UNLOCK = "buttons/unlockButton.png";

    //Sound Assets
     static final String AUDIO_ACID_DROP ="sounds/acidDrop.mp3";
     static final String AUDIO_BIRDS = "sounds/birds.mp3";
     static final String AUDIO_GAME_OVER = "sounds/gameOver.mp3";
     static final String AUDIO_LEVEL_WIN = "sounds/levelWin.mp3";
     static final String AUDIO_POWERUP = "sounds/powerup.wav";
     static final String AUDIO_RAIN_DROP = "sounds/rainDrop.mp3";
     static final String AUDIO_SHIELD_SPLAT = "sounds/shieldSplat.wav";
     static final String AUDIO_SIDE_SPLAT = "sounds/sideSplat.mp3";
     static final String AUDIO_SIREN = "sounds/siren.wav";
     static final String AUDIO_THUNDERCRACK = "sounds/thunderCrack.wav";
    public static final String AUDIO_THUNDERSTORM = "sounds/thunderstorm.mp3";
    static final String AUDIO_UMBRELLA_SPLAT = "sounds/umbrellaSplat.wav";
}
