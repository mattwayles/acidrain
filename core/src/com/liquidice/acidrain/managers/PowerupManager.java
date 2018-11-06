package com.liquidice.acidrain.managers;

/**
 * Manager Powerup State
 */
public class PowerupManager {
    private static boolean umbrellaActive;
    private static boolean shieldActive;

    /**
     * Deactivate ALL Powerups
     */
    public static void deactivateAllPowerups() {
        umbrellaActive = false;
        shieldActive = false;
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

    ////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////// SHIELD /////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Determine Active status of the Shield Powerup
     * @return Boolean determining Active status of the Shield Powerup
     */
    public static boolean isShieldActive() { return shieldActive; }

    /**
     * Activate the Shield Powerup
     */
    public static void activateShield() { shieldActive = true; }

    /**
     * Deactivate the Shield Powerup
     */
    public static void deactivateShield() { shieldActive = false; }
}
