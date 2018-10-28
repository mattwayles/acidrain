package com.liquidice.acidrain.managers;

import com.badlogic.gdx.Gdx;
import com.liquidice.acidrain.AcidRain;

/**
 * Gameplay Controller - increase difficulty as levels progress
 */
public class Gameplay {
    private static int level = AcidRain.getPreferences().getInteger("level", 1);
    private static int levelBest = AcidRain.getPreferences().getInteger("levelBest", 0);

    //Drop speed; increase to increase difficulty
    private static float maxSpeed = AcidRain.getPreferences().getFloat("maxSpeed", 5);
    private static float minSpeed = AcidRain.getPreferences().getFloat("minSpeed", 2);

    //Drop frequency; increase to increase difficulty
    private static float rainFreq = AcidRain.getPreferences().getFloat("rainFreq", 70);
    private static float acidFreq = AcidRain.getPreferences().getFloat("acidFreq", 180);

    //Manage Level
    public static int getLevel() { return level; }
    public static int getLevelBest() { return levelBest; }
    public static void setLevelBest(int best) {
        levelBest = best;
        AcidRain.getPreferences().putInteger("levelBest", best);
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
        float origWinScore = Score.getWinScore();
        float origLoseScore = Score.getLoseScore();

        level++;
        increaseMaxSpeed(level < 10 ? maxSpeed + .25f : maxSpeed + .1f);
        increaseMinSpeed(level < 10 ? maxSpeed + .25f : minSpeed + .1f);

        if (level % 2 == 0) {
            decreaseRainFreq(level < 10 ? rainFreq + 5f : rainFreq + 2f);
        }
        increaseAcidFreq(level < 10 ? acidFreq - 10f : acidFreq - 2f);
        Score.increaseWinScore(level < 10 ? 10 : 1);
        Score.increaseLoseScore(level < 10 ? 10 : 1);
        AcidRain.getPreferences().putInteger("levelBest", 0);
        AcidRain.getPreferences().putInteger("level", level);
        AcidRain.getPreferences().putFloat("maxSpeed", maxSpeed);
        AcidRain.getPreferences().putFloat("minSpeed", minSpeed);
        AcidRain.getPreferences().putFloat("rainFreq", rainFreq);
        AcidRain.getPreferences().putFloat("acidFreq", acidFreq);
        AcidRain.getPreferences().putInteger("winScore", Score.getWinScore());
        AcidRain.getPreferences().putInteger("loseScore", Score.getLoseScore());
        AcidRain.getPreferences().flush();

        Gdx.app.log("STARTING LEVEL", String.valueOf(level));
        Gdx.app.log("Max Speed Increased: ", origMaxSpeed + " -> " + maxSpeed);
        Gdx.app.log("Min Speed Increased: ", origMinSpeed + " -> " + minSpeed);
        Gdx.app.log("Rain Freq Decreased: ", origRainFreq + " -> " + rainFreq);
        Gdx.app.log("Acid Freq Increased:", origAcidFreq + " -> " + acidFreq);
        Gdx.app.log("Win Score Increased: ", origWinScore + " -> " + Score.getWinScore());
        Gdx.app.log("Lose Score Decreased: ", origLoseScore + " -> " + Score.getLoseScore());

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
