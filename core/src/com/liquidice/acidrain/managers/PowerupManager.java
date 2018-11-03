package com.liquidice.acidrain.managers;

/**
 * Manager Powerup State
 */
public class PowerupManager {
    private static boolean umbrellaActive;

    /**
     * Deactivate ALL Powerups
     */
    public static void deactivateAllPowerups() {
        umbrellaActive = false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////// UMBRELLA /////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Determine Active status of the Umbrella Powerup
     * @return Boolean determining Active status of the Umbrella Powerup
     */
    public static boolean isUmbrellaActive() { return umbrellaActive; }



    /**
     * Activate the Umbrella Powerup
     */
    public static void activateUmbrella() { umbrellaActive = true; }

    /**
     * Deactivate the Umbrella Powerup
     */
    public static void deactivateUmbrella() { umbrellaActive = false; }
}
