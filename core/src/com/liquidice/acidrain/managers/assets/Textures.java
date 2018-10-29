package com.liquidice.acidrain.managers.assets;

import com.badlogic.gdx.graphics.Texture;

public class Textures {
    ////////////////////////////////// TEXT TEXTURE ////////////////////////////////////////////////
    public static Texture logo = new Texture("text/logo.png");
    public static Texture gameOver = new Texture("text/gameOver.png");
    public static Texture levelComplete = new Texture("text/levelComplete.png");
    public static Texture perfectLevel = new Texture("text/perfectLevel.png");

    ///////////////////////////////// CLOUD TEXTURES ///////////////////////////////////////////////
    public static Texture clouds = new Texture("clouds.png");

    ////////////////////////////////// CITY TEXTURES ///////////////////////////////////////////////
    public static Texture city10 = new Texture("city/city10.png");
    private static Texture city9 = new Texture("city/city9.png");
    private static Texture city8 = new Texture("city/city8.png");
    private static Texture city7 = new Texture("city/city7.png");
    private static Texture city6 = new Texture("city/city6.png");
    private static Texture city5 = new Texture("city/city5.png");
    private static Texture city4 = new Texture("city/city4.png");
    private static Texture city3 = new Texture("city/city3.png");
    private static Texture city2 = new Texture("city/city2.png");
    public static Texture city1 = new Texture("city/city1.png");

    ////////////////////////////////// BACKGROUND TEXTURES /////////////////////////////////////////
    public static Texture stormBackground = new Texture("backgrounds/stormBackground.png");
    public static Texture lightningBackground = new Texture("backgrounds/lightningBackground.jpg");
    private static Texture sunnySkyBackground1 = new Texture("backgrounds/sunnySkyBackground1.png");
    private static Texture sunnySkyBackground2 = new Texture("backgrounds/sunnySkyBackground2.png");
    private static Texture sunnySkyBackground3 = new Texture("backgrounds/sunnySkyBackground3.png");
    private static Texture sunnySkyBackground4 = new Texture("backgrounds/sunnySkyBackground4.png");
    private static Texture sunnySkyBackground5 = new Texture("backgrounds/sunnySkyBackground5.png");
    private static Texture sunnySkyBackground6 = new Texture("backgrounds/sunnySkyBackground6.png");
    private static Texture sunnySkyBackground7 = new Texture("backgrounds/sunnySkyBackground7.png");
    private static Texture sunnySkyBackground8 = new Texture("backgrounds/sunnySkyBackground8.png");
    private static Texture sunnySkyBackground9 = new Texture("backgrounds/sunnySkyBackground9.png");
    private static Texture sunnySkyBackground10 = new Texture("backgrounds/sunnySkyBackground10.png");

    ////////////////////////////////// ACID TEXTURES ///////////////////////////////////////////////
    private static Texture acidDrop1 = new Texture("acid/drop/acid1.png");
    private static Texture acidDrop2 = new Texture("acid/drop/acid2.png");
    private static Texture acidDrop3 = new Texture("acid/drop/acid3.png");
    private static Texture acidDrop4 = new Texture("acid/drop/acid4.png");
    private static Texture acidDrop5 = new Texture("acid/drop/acid5.png");
    private static Texture acidDrop6 = new Texture("acid/drop/acid6.png");
    private static Texture acidDrop7 = new Texture("acid/drop/acid7.png");
    public static Texture acidSplash = new Texture("acid/splash/acidSplash.png");
    public static Texture acidSplashLeft = new Texture("acid/splash/acidSplashLeft.png");
    public static Texture acidSplashRight = new Texture("acid/splash/acidSplashRight.png");

    ////////////////////////////////// ACID TEXTURES ///////////////////////////////////////////////
    public static Texture rainBucket0 = new Texture("rain/bucket/bucket0.png");
    private static Texture rainBucket1 = new Texture("rain/bucket/bucket1.png");
    private static Texture rainBucket2 = new Texture("rain/bucket/bucket2.png");
    private static Texture rainBucket3 = new Texture("rain/bucket/bucket3.png");
    private static Texture rainBucket4 = new Texture("rain/bucket/bucket4.png");
    private static Texture rainBucket5 = new Texture("rain/bucket/bucket5.png");
    private static Texture rainBucket6 = new Texture("rain/bucket/bucket6.png");
    private static Texture rainBucket7 = new Texture("rain/bucket/bucket7.png");
    private static Texture rainBucket8 = new Texture("rain/bucket/bucket8.png");
    public static Texture rainBucket9 = new Texture("rain/bucket/bucket9.png");
    private static Texture rainDrop1 = new Texture("rain/drop/drop1.png");
    private static Texture rainDrop2 = new Texture("rain/drop/drop2.png");
    private static Texture rainDrop3 = new Texture("rain/drop/drop3.png");
    private static Texture rainDrop4 = new Texture("rain/drop/drop4.png");
    private static Texture rainDrop5 = new Texture("rain/drop/drop5.png");
    private static Texture rainDrop6 = new Texture("rain/drop/drop6.png");
    private static Texture rainDrop7 = new Texture("rain/drop/drop7.png");
    public static Texture rainSplash = new Texture("rain/splash/rainSplash.png");
    public static Texture rainSplashLeft = new Texture("rain/splash/rainSplashLeft.png");
    public static Texture rainSplashRight = new Texture("rain/splash/rainSplashRight.png");

    /////////////////////////////////// SCREEN TEXTURES ////////////////////////////////////////////
    public static Texture unlocked = new Texture("screen/unlocked.jpg");


    /////////////////////////////////// POWERUP TEXTURES ///////////////////////////////////////////
    public static Texture healthPack = new Texture("powerup/healthPack.png");

    public static Texture findRainDropTexture(int size) {
        switch (size) {
            case 1:
                return rainDrop1;
            case 2:
                return rainDrop2;
            case 3:
                return rainDrop3;
            case 4:
                return rainDrop4;
            case 5:
                return rainDrop5;
            case 6:
                return rainDrop6;
            case 7:
                return rainDrop7;
            default:
                return rainDrop1;
        }
    }

    public static Texture findAcidDropTexture(int size) {
        switch (size) {
            case 1:
                return acidDrop1;
            case 2:
                return acidDrop2;
            case 3:
                return acidDrop3;
            case 4:
                return acidDrop4;
            case 5:
                return acidDrop5;
            case 6:
                return acidDrop6;
            case 7:
                return acidDrop7;
            default:
                return acidDrop1;
        }
    }

    public static Texture findRainBucketTexture(int num) {
        switch (num) {
            case 1:
                return rainBucket1;
            case 2:
                return rainBucket2;
            case 3:
                return rainBucket3;
            case 4:
                return rainBucket4;
            case 5:
                return rainBucket5;
            case 6:
                return rainBucket6;
            case 7:
                return rainBucket7;
            case 8:
                return rainBucket8;
            case 9:
                return rainBucket9;
            default:
                return rainBucket0;
        }
    }

    public static Texture findSunnyBackgroundTexture(int num) {
        switch (num) {
            case 1:
                return sunnySkyBackground1;
            case 2:
                return sunnySkyBackground2;
            case 3:
                return sunnySkyBackground3;
            case 4:
                return sunnySkyBackground4;
            case 5:
                return sunnySkyBackground5;
            case 6:
                return sunnySkyBackground6;
            case 7:
                return sunnySkyBackground7;
            case 8:
                return sunnySkyBackground8;
            case 9:
                return sunnySkyBackground9;
            case 10:
                return sunnySkyBackground10;
            default:
                return sunnySkyBackground1;
        }
    }

    public static Texture findCityTexture(int num) {
        switch (num) {
            case 1:
                return city1;
            case 2:
                return city2;
            case 3:
                return city3;
            case 4:
                return city4;
            case 5:
                return city5;
            case 6:
                return city6;
            case 7:
                return city7;
            case 8:
                return city8;
            case 9:
                return city9;
            default:
                return city10;
        }
    }
}

