package com.liquidice.acidrain.controllers.assets;

import com.badlogic.gdx.graphics.Texture;

public class Textures {
    ////////////////////////////////// TEXT TEXTURE ////////////////////////////////////////////////
    public static Texture logo = new Texture("text/logo.png");
    public static Texture gameOver = new Texture("text/gameOver.png");
    public static Texture levelComplete = new Texture("text/levelComplete.png");

    ////////////////////////////////// CITY TEXTURES ///////////////////////////////////////////////
    public static Texture city = new Texture("city/city.png");

    ////////////////////////////////// BACKGROUND TEXTURES /////////////////////////////////////////
    public static Texture stormBackground = new Texture("backgrounds/stormBackground.jpg");
    public static Texture lightningBackground = new Texture("backgrounds/lightningBackground.jpg");
    public static Texture sunnyBackground = new Texture("backgrounds/sunnySkyBackground.jpg");

    ////////////////////////////////// ACID TEXTURES ///////////////////////////////////////////////
    public static Texture acidBucket0 = new Texture("acid/bucket/bucket0.png");
    public static Texture acidDrop1 = new Texture("acid/drop/acid1.png");
    public static Texture acidDrop2 = new Texture("acid/drop/acid2.png");
    public static Texture acidDrop3 = new Texture("acid/drop/acid3.png");
    public static Texture acidDrop4 = new Texture("acid/drop/acid4.png");
    public static Texture acidDrop5 = new Texture("acid/drop/acid5.png");
    public static Texture acidDrop6 = new Texture("acid/drop/acid6.png");
    public static Texture acidDrop7 = new Texture("acid/drop/acid7.png");
    public static Texture acidSplash = new Texture("acid/splash/acidSplash.png");
    public static Texture acidSplashLeft = new Texture("acid/splash/acidSplashLeft.png");
    public static Texture acidSplashRight = new Texture("acid/splash/acidSplashRight.png");

    ////////////////////////////////// ACID TEXTURES ///////////////////////////////////////////////
    public static Texture rainBucket0 = new Texture("rain/bucket/bucket0.png");
    public static Texture rainBucket1 = new Texture("rain/bucket/bucket1.png");
    public static Texture rainBucket2 = new Texture("rain/bucket/bucket2.png");
    public static Texture rainBucket3 = new Texture("rain/bucket/bucket3.png");
    public static Texture rainBucket4 = new Texture("rain/bucket/bucket4.png");
    public static Texture rainBucket5 = new Texture("rain/bucket/bucket5.png");
    public static Texture rainBucket6 = new Texture("rain/bucket/bucket6.png");
    public static Texture rainBucket7 = new Texture("rain/bucket/bucket7.png");
    public static Texture rainBucket8 = new Texture("rain/bucket/bucket8.png");
    public static Texture rainBucket9 = new Texture("rain/bucket/bucket9.png");
    public static Texture rainDrop1 = new Texture("rain/drop/drop1.png");
    public static Texture rainDrop2 = new Texture("rain/drop/drop2.png");
    public static Texture rainDrop3 = new Texture("rain/drop/drop3.png");
    public static Texture rainDrop4 = new Texture("rain/drop/drop4.png");
    public static Texture rainDrop5 = new Texture("rain/drop/drop5.png");
    public static Texture rainDrop6 = new Texture("rain/drop/drop6.png");
    public static Texture rainDrop7 = new Texture("rain/drop/drop7.png");
    public static Texture rainSplash = new Texture("rain/splash/rainSplash.png");
    public static Texture rainSplashLeft = new Texture("rain/splash/rainSplashLeft.png");
    public static Texture rainSplashRight = new Texture("rain/splash/rainSplashRight.png");


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
}
