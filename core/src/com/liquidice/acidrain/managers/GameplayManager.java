package com.liquidice.acidrain.managers;

/**
 * GameplayManager Controller - increase difficulty as levels progress
 */
public class GameplayManager {
    //Game State
    private static int gameState;
    private static boolean paused;

    //Manage Level
    public static int getLevel() { return PreferenceManager.getInt("level", PropManager.DEFAULT_START_LEVEL); }
    public static int getLevelBest() { return PreferenceManager.getInt("levelBest", 0); }
    public static void setLevelBest(int best) { PreferenceManager.putInt("levelBest", best); }

    public static void increaseLevel() {
        // This is the creme-de-la-creme of the gameplay. The next level needs to slightly increase difficulty, in a way that is fun yet challenging
        // Methods of increasing difficulty:
        // 		Rain/Acid Drop speed
        //		Rain/Acid drop frequency
        //		Higher win score/ Lower lose score
        //		(Future) less time to get winScore before storm clears

        increaseMaxSpeed(getLevel() < PropManager.CUTOFF_LEVEL ? getMaxSpeed() + PropManager.SPEED_L1_9_INCREASE : getMaxSpeed() + PropManager.SPEED_L10_INCREASE);
        increaseMinSpeed(getLevel() < PropManager.CUTOFF_LEVEL ? getMinSpeed() + PropManager.SPEED_L1_9_INCREASE : getMinSpeed() + PropManager.SPEED_L10_INCREASE);

        if (getLevel() % 2 == 0) {
            decreaseRainFreq(getLevel() < PropManager.CUTOFF_LEVEL ? getRainFreq() - PropManager.RAIN_L1_9_INCREASE : getRainFreq() + PropManager.RAIN_L10_DECREASE);
        }
        increaseAcidFreq(getLevel() < PropManager.CUTOFF_LEVEL ? getAcidFreq() - PropManager.ACID_L1_9_INCREASE : getAcidFreq() - PropManager.ACID_L10_INCREASE);
        ScoreManager.increaseWinScore(getLevel() < PropManager.CUTOFF_LEVEL ? PropManager.SCORE_L1_9_INCREASE : PropManager.SCORE_L10_INCREASE);
        ScoreManager.increaseLoseScore(getLevel() < PropManager.CUTOFF_LEVEL ? PropManager.SCORE_L1_9_INCREASE : PropManager.SCORE_L10_INCREASE);
        ScoreManager.increaseCaughtScore(getLevel() < PropManager.CUTOFF_LEVEL ? PropManager.SCORE_L1_9_INCREASE : PropManager.SCORE_L10_INCREASE);
        PreferenceManager.putInt("level", getLevel() + 1);
        setLevelBest(0);
    }



    //Manage Game State variable
    public static int getGameState() { return gameState; }
    public static void setGameState(int state) { gameState = state; }

    //Manage Max Speed variable
    public static float getMaxSpeed() { return PreferenceManager.getFloat("maxSpeed", PropManager.DEFAULT_MAX_SPEED); }
    private  static void increaseMaxSpeed(float speed) { PreferenceManager.putFloat("maxSpeed", speed); }

    //Manage Min Speed variable
    public static float getMinSpeed() { return PreferenceManager.getFloat("minSpeed", PropManager.DEFAULT_MIN_SPEED); }
    private static void increaseMinSpeed(float speed) { PreferenceManager.putFloat("minSpeed", speed); }

    //Manage Rain Frequency variable
    public static float getRainFreq() {
        return PreferenceManager.getFloat("rainFreq", PropManager.DEFAULT_RAIN_FREQUENCY);
    }
    private static void decreaseRainFreq(float freq) { PreferenceManager.putFloat("rainFreq", freq); }

    //Manage Acid Frequency variable
    public static float getAcidFreq() { return PreferenceManager.getFloat("acidFreq", PropManager.DEFAULT_ACID_FREQUENCY); }
    private static void increaseAcidFreq(float freq) { PreferenceManager.putFloat("acidFreq", freq); }

    //Manage paused state
    public static boolean isPaused() { return paused; }
    public static void pause() { paused = true; }
    public static void resume() { paused = false; }

}
