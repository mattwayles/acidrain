package com.liquidice.acidrain.managers;

/**
 * Manage the various counters used to for timing and spacing in the app
 */
public class CountManager {
    //Store time between raindrops
    private static int rainCount;

    //Store time between acid drops
    private static int acidCount;

    //Store time before lightning strike
    private static int backgroundCount;

    //Storm time between sun
    private static int sunnyCount;

    //Umbrella activation time
    private static int umbrellaCount;
    
    //Shield activation time
    private static int shieldCount;

    //Filtration activation time
    private static int filterCount;

    //Teamwork activation time
    private static int teamworkCount;

    //Purple rain activation time
    private static int purpleRainCount;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////// RAIN COUNT ///////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Controls the frequency of Rain Drops
    /**
     * Retrieve the current RainCount counter value
     * @return  The current RainCount counter value
     */
    public static int getRainCount() { return rainCount; }

    /**
     * Increase the RainCount counter
     */
    public static void increaseRainCount() { rainCount = GameplayManager.isPaused() ? rainCount : rainCount + 1; }

    /**
     * Reset the RainCount counter
     */
    public static void resetRainCount() { rainCount = 0; }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////// ACID COUNT ///////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Controls the frequency of Acid Drops
    /**
     * Retreive the AcidCount counter value
     * @return  The AcidCount counter value
     */
    public static int getAcidCount() { return acidCount; }

    /**
     * Increase the AcidCount counter
     */
    public static void increaseAcidCount() { acidCount = GameplayManager.isPaused() ? acidCount : acidCount + 1; }

    /**
     * Reset the AcidCount counter
     */
    public static void resetAcidCount() { acidCount = 0; }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////// BACKGROUND COUNT /////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Controls the length of time between lightning flashes
    /**
     * Retrieve the current BackgroundCount counter value
     * @return  The current BackgroundCount counter value
     */
    public static int getBackgroundCount() { return backgroundCount; }

    /**
     * Increase the BackgroundCount counter
     */
    public static void increaseBackgroundCount() { backgroundCount = GameplayManager.isPaused() ? backgroundCount : backgroundCount + 1; }

    /**
     * Reset the BackgroundCount counter
     */
    public static void resetBackgroundCount() { backgroundCount = 0; }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// SUNNY COUNT ///////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Controls the steady production & dissipation of the sunny background at level completion
    /**
     * Retrieve the current SunnyCount counter value
     * @return The current SunnyCount counter value
     */
    public static int getSunnyCount() { return sunnyCount; }

    /**
     * Increase the SunnyCount counter
     */
    public static void increaseSunnyCount() { sunnyCount = GameplayManager.isPaused() ? sunnyCount : sunnyCount + 1; }

    /**
     * Decrease the SunnyCount counter
     */
    public static void decreaseSunnyCount() { sunnyCount--; }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// UMBRELLA COUNT //////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Controls the length of time of umbrella powerup is active
    /**
     * Retrieve the current UmbrellaCount counter value
     * @return  The current UmbrellaCount counter value
     */
    public static int getUmbrellaCount() { return umbrellaCount; }

    /**
     * Increase the UmbrellaCount counter
     */
    public static void increaseUmbrellaCount() { umbrellaCount = GameplayManager.isPaused() ? umbrellaCount : umbrellaCount + 1; }

    /**
     * Reset the UmbrellaCount counter
     */
    public static void resetUmbrellaCount() { umbrellaCount = 0; }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// SHIELD COUNT //////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Controls the length of time of shield powerup is active
    /**
     * Retrieve the current ShieldCount counter value
     * @return  The current ShieldCount counter value
     */
    public static int getShieldCount() { return shieldCount; }

    /**
     * Increase the ShieldCount counter
     */
    public static void increaseShieldCount() { shieldCount = GameplayManager.isPaused() ? shieldCount : shieldCount + 1; }

    /**
     * Reset the ShieldCount counter
     */
    public static void resetShieldCount() { shieldCount = 0; }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////// FILTRATION COUNT /////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Controls the length of time of filtration powerup is active
    /**
     * Retrieve the current FilterCount counter value
     * @return  The current FilterCount counter value
     */
    public static int getFilterCount() { return filterCount; }

    /**
     * Increase the FilterCount counter
     */
    public static void increaseFilterCount() { filterCount = GameplayManager.isPaused() ? filterCount : filterCount + 1; }

    /**
     * Reset the FilterCount counter
     */
    public static void resetFilterCount() { filterCount = 0; }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////// TEAMWORK COUNT /////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Controls the length of time of teamwork powerup is active
    /**
     * Retrieve the current TeamworkCount counter value
     * @return  The current TeamworkCount counter value
     */
    public static int getTeamworkCount() { return teamworkCount; }

    /**
     * Increase the TeamworkCount counter
     */
    public static void increaseTeamworkCount() { teamworkCount = GameplayManager.isPaused() ? teamworkCount : teamworkCount + 1; }

    /**
     * Reset the FilterCount counter
     */
    public static void resetTeamworkCount() { teamworkCount = 0; }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////// PURPLE RAIN COUNT //////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Controls the length of time of purple rain powerup is active
    /**
     * Retrieve the current PurpleRainCount counter value
     * @return  The current PurpleRainCount counter value
     */
    public static int getPurpleRainCount() { return purpleRainCount; }

    /**
     * Increase the TeamworkCount counter
     */
    public static void increasePurpleRainCount() { purpleRainCount = GameplayManager.isPaused() ? purpleRainCount : purpleRainCount + 1; }

    /**
     * Reset the FilterCount counter
     */
    public static void resetPurpleRainCount() { purpleRainCount = 0; }

    /**
     * Clear all counters
     */
    public static void clear() {
        rainCount = 0;
        acidCount = 0;
        backgroundCount = 0;
        umbrellaCount = 0;
        shieldCount = 0;
        filterCount = 0;
    }
}
