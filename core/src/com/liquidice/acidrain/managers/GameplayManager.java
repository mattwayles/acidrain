package com.liquidice.acidrain.managers;

/**
 * GameplayManager Controller - Manage Gameplay elements and increase level difficulty
 */
public class GameplayManager {
    //Game State
    private static int gameState;
    private static boolean paused;


    /**
     * When a level is completed, increase difficulty and set up for new level
     */
    public static void increaseLevel() {
        // Methods of increasing difficulty:
        // 		Rain/Acid Drop speed
        //		Rain/Acid drop frequency
        //		Higher win score/ Lower lose score
        //		(Future, tentative) less time to get winScore before storm clears

        //Control Drop Speed
        increaseMaxSpeed(getLevel() < PropManager.CUTOFF_LEVEL ? getMaxSpeed() + PropManager.SPEED_L1_9_INCREASE : getMaxSpeed() + PropManager.SPEED_L10_INCREASE);
        increaseMinSpeed(getLevel() < PropManager.CUTOFF_LEVEL ? getMinSpeed() + PropManager.SPEED_L1_9_INCREASE : getMinSpeed() + PropManager.SPEED_L10_INCREASE);

        //Control Drop Frequency
        if (getLevel() % 2 == 0) {
            decreaseRainFreq(getLevel() < PropManager.CUTOFF_LEVEL ? getRainFreq() - PropManager.RAIN_L1_9_DECREASE : getRainFreq() + PropManager.RAIN_L10_DECREASE);
        }
        increaseAcidFreq(getLevel() < PropManager.CUTOFF_LEVEL ? getAcidFreq() - PropManager.ACID_L1_9_INCREASE : getAcidFreq() - PropManager.ACID_L10_INCREASE);

        //Control Win/Lose Scores
        ScoreManager.increaseWinScore(getLevel() < PropManager.CUTOFF_LEVEL ? PropManager.SCORE_L1_9_INCREASE : PropManager.SCORE_L10_INCREASE);
        ScoreManager.increaseLoseScore(getLevel() < PropManager.CUTOFF_LEVEL ? PropManager.SCORE_L1_9_INCREASE : PropManager.SCORE_L10_INCREASE);
        ScoreManager.increaseCaughtScore(getLevel() < PropManager.CUTOFF_LEVEL ? PropManager.SCORE_L1_9_INCREASE : PropManager.SCORE_L10_INCREASE);
        ScoreManager.increaseStrengthScore(getLevel() < PropManager.CUTOFF_LEVEL ? PropManager.SCORE_L1_9_INCREASE : PropManager.SCORE_L10_INCREASE);

        //Set New Level Data
        PreferenceManager.putInt(PropManager.PREF_LEVEL, getLevel() + 1);
        setLevelBest(0);
    }


    /**
     * Retrieve the current level
     * @return The current level
     */
    public static int getLevel() { return PreferenceManager.getInt(PropManager.PREF_LEVEL, PropManager.DEFAULT_START_LEVEL); }

    /**
     * Retrieve the current level best score
     * @return  The current level best score
     */
    public static int getLevelBest() { return PreferenceManager.getInt(PropManager.PREF_LEVEL_BEST, 0); }

    /**
     * Set the current level best score
     * @param best The new best score for the current level
     */
    public static void setLevelBest(int best) { PreferenceManager.putInt(PropManager.PREF_LEVEL_BEST, best); }

    /**
     * Retrieve the current Game State
     * @return The current Game State
     */
    public static int getGameState() { return gameState; }

    /**
     * Set a new Game State value
     * @param state The new Game State value
     */
    public static void setGameState(int state) { gameState = state; }

    /**
     * Retrieve the current Max Speed
     * @return The current Max Speed
     */
    public static float getMaxSpeed() { return PreferenceManager.getFloat(PropManager.PREF_MAX_SPEED, PropManager.DEFAULT_MAX_SPEED); }

    /**
     * Increase the Drop Max Speed
     * @param speed The new Drop Max Speed
     */
    private  static void increaseMaxSpeed(float speed) { PreferenceManager.putFloat(PropManager.PREF_MAX_SPEED, speed); }

    /**
     * Retrieve the current Min Speed
     * @return The current Min Speed
     */
    public static float getMinSpeed() { return PreferenceManager.getFloat(PropManager.PREF_MIN_SPEED, PropManager.DEFAULT_MIN_SPEED); }

    /**
     * Set the new Drop Min Speed
     * @param speed The new Drop Min Speed
     */
    private static void increaseMinSpeed(float speed) { PreferenceManager.putFloat(PropManager.PREF_MIN_SPEED, speed); }

    /**
     * Retrieve the Rain Frequency for the current level
     * @return The Rain Frequenct for the current level
     */
    public static float getRainFreq() {
        return PreferenceManager.getFloat(PropManager.PREF_RAIN_FREQ, PropManager.DEFAULT_RAIN_FREQUENCY);
    }

    /**
     * Decrease Rain Frequency
     * @param freq The new Rain Frequency value
     */
    private static void decreaseRainFreq(float freq) { PreferenceManager.putFloat(PropManager.PREF_RAIN_FREQ, freq); }

    /**
     * Retrieve the Acid Frequency for the current level
     * @return The Acid Frequency for the current level
     */
    public static float getAcidFreq() { return PreferenceManager.getFloat(PropManager.PREF_ACID_FREQ, PropManager.DEFAULT_ACID_FREQUENCY); }

    /**
     * Increase Acid Frequency
     * @param freq The new Acid Freqency
     */
    private static void increaseAcidFreq(float freq) { PreferenceManager.putFloat(PropManager.PREF_ACID_FREQ, freq); }

    //Manage paused state
    /**
     * Determine game pause status
     * @return Boolean indicating game pause status
     */
    public static boolean isPaused() { return paused; }

    /**
     * Pause the gameplay
     */
    public static void pause() { paused = true; }

    /**
     * Resume the gameplay
     */
    public static void resume() { paused = false; }

}
