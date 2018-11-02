package com.liquidice.acidrain.managers;

import com.badlogic.gdx.Gdx;
import com.liquidice.acidrain.AcidRain;

/**
 * GameplayMgr Controller - increase difficulty as levels progress
 */
public class GameplayMgr {
    private static int level = AcidRain.getPreferences().getInteger("level", PropertiesMgr.DEFAULT_START_LEVEL);
    private static int levelBest = AcidRain.getPreferences().getInteger("levelBest", 0);

    //Drop speed; increase to increase difficulty
    private static float maxSpeed = AcidRain.getPreferences().getFloat("maxSpeed", PropertiesMgr.DEFAULT_MAX_SPEED);
    private static float minSpeed = AcidRain.getPreferences().getFloat("minSpeed", PropertiesMgr.DEFAULT_MIN_SPEED);

    //Drop frequency; increase to increase difficulty
    private static float rainFreq = AcidRain.getPreferences().getFloat("rainFreq", PropertiesMgr.DEFAULT_RAIN_FREQUENCY);
    private static float acidFreq = AcidRain.getPreferences().getFloat("acidFreq", PropertiesMgr.DEFAULT_ACID_FREQUENCY);

    //Manage gameplay
    private static boolean paused;
    //Manage Level
    public static int getLevel() { return level; }
    public static int getLevelBest() { return levelBest; }
    public static void setLevelBest(int best) {
        levelBest = best;
        AcidRain.getPreferences().putInteger("levelBest", best);
        AcidRain.getPreferences().flush();
    }
    public static void increaseLevel() {
        // This is the creme-de-la-creme of the gameplay. The next level needs to slightly increase difficulty, in a way that is fun yet challenging
        // Methods of increasing difficulty:
        // 		Rain/Acid Drop speed
        //		Rain/Acid drop frequency
        //		Higher win score/ Lower lose score
        //		(Future) less time to get winScore before storm clears


        //TODO: DEBUG, remove
        float origMaxSpeed = maxSpeed;
        float origMinSpeed = minSpeed;
        float origRainFreq = rainFreq;
        float origAcidFreq = acidFreq;
        float origWinScore = ScoreMgr.getWinScore();
        float origLoseScore = ScoreMgr.getLoseScore();

        level++;
        increaseMaxSpeed(level < PropertiesMgr.CUTOFF_LEVEL ? maxSpeed + PropertiesMgr.SPEED_L1_9_INCREASE : maxSpeed + PropertiesMgr.SPEED_L10_INCREASE);
        increaseMinSpeed(level < PropertiesMgr.CUTOFF_LEVEL ? minSpeed + PropertiesMgr.SPEED_L1_9_INCREASE : minSpeed + PropertiesMgr.SPEED_L10_INCREASE);

        if (level % 2 == 0) {
            decreaseRainFreq(level < PropertiesMgr.CUTOFF_LEVEL ? rainFreq - PropertiesMgr.RAIN_L1_9_INCREASE : rainFreq + PropertiesMgr.RAIN_L10_DECREASE);
        }
        increaseAcidFreq(level < PropertiesMgr.CUTOFF_LEVEL ? acidFreq - PropertiesMgr.ACID_L1_9_INCREASE : acidFreq - PropertiesMgr.ACID_L10_INCREASE);
        ScoreMgr.increaseWinScore(level < PropertiesMgr.CUTOFF_LEVEL ? PropertiesMgr.SCORE_L1_9_INCREASE : PropertiesMgr.SCORE_L10_INCREASE);
        ScoreMgr.increaseLoseScore(level < PropertiesMgr.CUTOFF_LEVEL ? PropertiesMgr.SCORE_L1_9_INCREASE : PropertiesMgr.SCORE_L10_INCREASE);
        AcidRain.getPreferences().putInteger("levelBest", 0);
        AcidRain.getPreferences().putInteger("level", level);
        AcidRain.getPreferences().putFloat("maxSpeed", maxSpeed);
        AcidRain.getPreferences().putFloat("minSpeed", minSpeed);
        AcidRain.getPreferences().putFloat("rainFreq", rainFreq);
        AcidRain.getPreferences().putFloat("acidFreq", acidFreq);
        AcidRain.getPreferences().putInteger("winScore", ScoreMgr.getWinScore());
        AcidRain.getPreferences().putInteger("loseScore", ScoreMgr.getLoseScore());
        AcidRain.getPreferences().flush();

        Gdx.app.log("STARTING LEVEL", String.valueOf(level));
        Gdx.app.log("Max Speed Increased: ", origMaxSpeed + " -> " + maxSpeed);
        Gdx.app.log("Min Speed Increased: ", origMinSpeed + " -> " + minSpeed);
        Gdx.app.log("Rain Freq Decreased: ", origRainFreq + " -> " + rainFreq);
        Gdx.app.log("Acid Freq Increased:", origAcidFreq + " -> " + acidFreq);
        Gdx.app.log("Win ScoreMgr Increased: ", origWinScore + " -> " + ScoreMgr.getWinScore());
        Gdx.app.log("Lose ScoreMgr Decreased: ", origLoseScore + " -> " + ScoreMgr.getLoseScore());

    }

    //Manage Max Speed variable
    public static float getMaxSpeed() { return maxSpeed; }
    private  static void increaseMaxSpeed(float speed) { maxSpeed = speed; }

    //Manage Min Speed variable
    public static float getMinSpeed() { return minSpeed; }
    private static void increaseMinSpeed(float speed) { minSpeed = speed; }

    //Manage Rain Frequency variable
    public static float getRainFreq() {
        return rainFreq;
    }
    private static void decreaseRainFreq(float freq) { rainFreq = freq; }

    //Manage Acid Frequency variable
    public static float getAcidFreq() { return acidFreq; }
    private static void increaseAcidFreq(float freq) { acidFreq = freq; }

    //Manage paused state
    public static boolean isPaused() { return paused; }
    public static void pause() { paused = true; }
    public static void resume() { paused = false; }

}
