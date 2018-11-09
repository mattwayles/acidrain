package com.liquidice.acidrain.managers;

import com.badlogic.gdx.Gdx;
import com.liquidice.acidrain.sprites.drops.PowerupDrop;

/**
 * Manager Powerup State
 */
public class PowerupManager {
    private static boolean umbrellaActive;
    private static boolean shieldActive;
    private static boolean filterActive;

    /**
     * Deactivate ALL Powerups
     */
    public static void deactivateAllPowerups() {
        umbrellaActive = false;
        shieldActive = false;
        filterActive = false;
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

    ////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////// FILTRATION /////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Determine Active status of the Filter Powerup
     * @return Boolean determining Active status of the Filter Powerup
     */
    public static boolean isFilterActive() { return filterActive; }

    /**
     * Activate the Filter Powerup
     */
    public static void activateFilter() { filterActive = true; }

    /**
     * Deactivate the Filter Powerup
     */
    public static void deactivateFilter() { filterActive = false; }

    /**
     * Determine if current level is immediately after a powerup unlock
     * @return int indicating if current level is immediately after a powerup unlock
     */
    public static int checkPowerupLevel() {
        int num = -1;

        int level = GameplayManager.getLevel();
        if (level == PropManager.UNLOCK_1_LEVEL) {
            num = 6;
        }
        else if (level == PropManager.UNLOCK_2_LEVEL) {
            num = PropManager.HEALTHPACK_CHANCE;
        }
        else if(level == PropManager.UNLOCK_3_LEVEL) {
            num = PropManager.UMBRELLA_CHANCE;
        }
        else if (level == PropManager.UNLOCK_4_LEVEL) {
            num = PropManager.SHIELD_CHANCE;
        }
        else if (level == PropManager.UNLOCK_5_LEVEL) {
            num = PropManager.FILTER_CHANCE;
        }

        return num;
    }
}
