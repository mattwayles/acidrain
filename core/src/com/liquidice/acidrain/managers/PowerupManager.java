package com.liquidice.acidrain.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.liquidice.acidrain.sprites.Bucket;
import com.liquidice.acidrain.utilities.SpriteUtil;

/**
 * Manager Powerup State
 */
public class PowerupManager {
    private static boolean umbrellaActive;
    private static boolean shieldActive;
    private static boolean filterActive;
    private static BitmapFont countdown;

    /**
     * Initialize the countdown Font
     */
    public static void init() {
        countdown = new BitmapFont(
                Gdx.files.internal(PropManager.FONT_PLAY_100),
                Gdx.files.internal(PropManager.FONT_PLAY_100_PNG),
                false);
    }

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

    /**
     * Draw a countdown on the screen is powerup is nearing expiration
     * @param batch The batch to draw this font on
     * @param activationTime The activation time to use for countdown reference
     */
    public static void checkCountdown(Batch batch, int activationTime) {
        int count = PowerupManager.isUmbrellaActive() ? CountManager.getUmbrellaCount()
                : PowerupManager.isShieldActive() ? CountManager.getShieldCount() : CountManager.getFilterCount();

        if (count > activationTime - PropManager.ONE_SECOND) {
            countdown.draw(batch, PropManager.ONE, SpriteUtil.middleOf(Gdx.graphics.getWidth()) - PropManager.COUNTDOWN_OFFSET,
                    PropManager.BUCKET_HOVER + SpriteUtil.timesTwo(Bucket.getImage().getHeight()));
        }
        else if (count > activationTime - PropManager.TWO_SECONDS) {
            countdown.draw(batch, PropManager.TWO, SpriteUtil.middleOf(Gdx.graphics.getWidth()) - PropManager.COUNTDOWN_OFFSET,
                    PropManager.BUCKET_HOVER + SpriteUtil.timesTwo(Bucket.getImage().getHeight()));
        }
        else if (count > activationTime - PropManager.THREE_SECONDS) {
            countdown.draw(batch, PropManager.THREE, SpriteUtil.middleOf(Gdx.graphics.getWidth()) - PropManager.COUNTDOWN_OFFSET,
                    PropManager.BUCKET_HOVER + SpriteUtil.timesTwo(Bucket.getImage().getHeight()));
        }
    }
}
