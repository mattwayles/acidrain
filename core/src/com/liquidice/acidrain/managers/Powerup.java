package com.liquidice.acidrain.managers;

public class Powerup {
    private static boolean umbrellaActive;

    public static boolean isUmbrellaActive() { return umbrellaActive; }
    public static void activateUmbrella() { umbrellaActive = true; }
    public static void deactivateUmbrella() { umbrellaActive = false; }

    public static void deactivateAllPowerups() {
        umbrellaActive = false;
    }
}
