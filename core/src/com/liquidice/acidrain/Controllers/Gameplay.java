package com.liquidice.acidrain.Controllers;

import com.liquidice.acidrain.AcidRain;

/**
 * Gameplay Controller - increase difficulty as levels progress
 */
public class Gameplay {
    private static int level = AcidRain.getPreferences().getInteger("level", 1);
    private static int levelBest = AcidRain.getPreferences().getInteger("levelBest", 0);

    //Drop speed; increase to increase difficulty
    private static float maxSpeed = AcidRain.getPreferences().getFloat("maxSpeed", 12);
    private static float minSpeed = AcidRain.getPreferences().getFloat("minSpeed", 8);

    //Drop frequency; increase to increase difficulty
    private static float rainFreq = AcidRain.getPreferences().getFloat("rainFreq", 35);
    private static float acidFreq = AcidRain.getPreferences().getFloat("acidFreq", 25);

    //Manage Level
    public static int getLevel() { return level; }
    public static int getLevelBest() { return levelBest; }
    public static void setLevelBest(int best) {
        levelBest = best;
        AcidRain.getPreferences().putInteger("levelBest", best);
    }
    public static void increaseLevel() {
        level++;
        increaseMaxSpeed(maxSpeed + level * .1f);
        increaseMinSpeed(minSpeed + level * .1f);

        if (level % 4 == 0) {
            decreaseRainFreq(rainFreq + 1);
        }
        increaseAcidFreq(acidFreq - level * .1f);
        Score.increaseWinScore();
        AcidRain.getPreferences().putInteger("level", level);
        AcidRain.getPreferences().putFloat("maxSpeed", maxSpeed);
        AcidRain.getPreferences().putFloat("minSpeed", minSpeed);
        AcidRain.getPreferences().putFloat("rainFreq", rainFreq);
        AcidRain.getPreferences().putFloat("acidFreq", acidFreq);
        AcidRain.getPreferences().putInteger("winScore", Score.getWinScore());
        AcidRain.getPreferences().flush();
    }

    //Manage Max Speed variable
    public static float getMaxSpeed() { return maxSpeed; }
    public static void increaseMaxSpeed(float speed) { maxSpeed = speed; }

    //Manage Min Speed variable
    public static float getMinSpeed() { return minSpeed; }
    public static void increaseMinSpeed(float speed) { minSpeed = speed; }

    //Manage Rain Frequency variable
    public static float getRainFreq() {
        return rainFreq;
    }
    public static void decreaseRainFreq(float freq) { rainFreq = freq; }

    //Manage Acid Frequency variable
    public static float getAcidFreq() { return acidFreq; }
    public static void increaseAcidFreq(float freq) { acidFreq = freq; }

}
