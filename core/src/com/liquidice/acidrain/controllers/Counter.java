package com.liquidice.acidrain.controllers;

public class Counter {
    //Store time between raindrops
    private static int rainCount;

    //Store time between acid drops
    private static int acidCount;

    //Store time before removing splashes
    private static int splashCount;
    private final static int splashLength = 20;

    //Store time before lightning strike
    private static int backgroundCount;

    //Storm time between sun
    private static int sunnyCount;

    //Manage RainCount variable
    public static int getRainCount() { return rainCount; }
    public static void increaseRainCount() { rainCount++; }
    public static void resetRainCount() { rainCount = 0; }

    //Manage AcidCount variable
    public static int getAcidCount() { return acidCount; }
    public static void increaseAcidCount() { acidCount++; }
    public static void resetAcidCount() { acidCount = 0; }

    //Manage SplashCount variable
    public static int getSplashCount() { return splashCount; }
    public static int getSplashLength() { return splashLength; }
    public static void increaseSplashCount() { splashCount++; }
    public static void resetSplashCount() { splashCount = 0; }

    //Manage BackgroundCount variable
    public static int getBackgroundCount() { return backgroundCount; }
    public static void increaseBackgroundCount() { backgroundCount++; }
    public static void resetBackgroundCount() { backgroundCount = 0; }

    //Manage sunnyCount variable
    public static int getSunnyCount() { return sunnyCount; }
    public static void increaseSunnyCount() { sunnyCount++; }
    public static void resetSunnyCount() { sunnyCount = 0; }

    public static void clear() {
        rainCount = 0;
        acidCount = 0;
        splashCount = 0;
        sunnyCount = 0;
        backgroundCount = 0;
    }
}