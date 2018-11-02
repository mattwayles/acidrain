package com.liquidice.acidrain.managers;

import com.liquidice.acidrain.AcidRain;

/**
 * ScoreManager Manager - Keeps track of the current clean water & city strength scores
 */
public class ScoreManager {

    private static double winScore = PreferenceManager.getInt("winScore", 100);
    private static double loseScore = PreferenceManager.getInt("loseScore", 150);
    private static double caughtScore;
    private static double strengthScore = loseScore;
    private static int caughtPercentage;
    private static int strengthPercentage = 100;

    /**
     * Calculate the Caught ScoreManager percentage
     */
    private static void calculateCaughtPercentage() {
        caughtPercentage = (int) ((caughtScore / winScore) * 100);
        caughtPercentage = caughtPercentage > 100 ? 100 : caughtPercentage;
    }

    /**
     * Calculate the City Strength percentage
     */
    private static void calculateStrengthPercentage() {
        strengthPercentage = (int) ((strengthScore / loseScore) * 100);
        strengthPercentage = strengthPercentage < 0 ? 0 : strengthPercentage;
    }


    /**
     * Retrieve the winning score for the current level
     * @return  The winning score for the current level
     */
    static int getWinScore() { return (int) winScore; }

    /**
     * Increase the winning score by a specified number
     * @param num   The number to increase the winning score
     */
    static void increaseWinScore(int num) {
        winScore += num;
        calculateCaughtPercentage();
    }

    /**
     * Retrieve the losing score for the current level
     * @return  The losing score for the succren level
     */
    public static int getLoseScore() { return (int) loseScore; }

    /**
     * Increase the losing score by a specified number
     * @param num   The number to increase the losing score
     */
    static void increaseLoseScore(int num ) {
        loseScore += num;
        calculateStrengthPercentage();
    }

    /**
     * Retrieve the current City Strength score (not percentage)
     * @return  The current City Strength score
     */
    public static int getStrengthScore() { return (int) strengthScore; }

    /**
     * Set the City Strength score (not percentage)
     * @param score The new City Strength score
     */
    public static void setStrengthScore(int score) {
        strengthScore = score;
        calculateStrengthPercentage();
    }

    /**
     * Retrieve the current City Strength percentage
     * @return  The current City Strength percentage
     */
    public static int getStrengthPercentage() { return strengthPercentage; }

    /**
     * Reset the strength percentage back to 100% when a level has completed
     */
    public static void resetStrength() {
        strengthScore = loseScore;
        calculateStrengthPercentage();
    }

    /**
     * Increase the Caught ScoreManager by a specified number of points (not percentage)
     * @param points    The number of points to increase Caught ScoreManager
     */
    public static void increaseCaughtScore(int points) {
        caughtScore += points;
        calculateCaughtPercentage();
    }

    /**
     * Decrease the Caught ScoreManager by a specified number of points (not percentage)
     * @param points    The number of points to decrease Caught ScoreManager
     */
    public static void decreaseStrengthScore(int points) {
        strengthScore -= points;
        calculateStrengthPercentage();
    }

    /**
     * Retrieve the current Clean Water percentage
     * @return  The current Clean Water percentage
     */
    public static int getCaughtPercentage() { return caughtPercentage; }

    /**
     * Reset the caught score back to 0 when a level has completed
     */
    public static void resetScore() {
        caughtScore = 0;
        calculateCaughtPercentage();
    }

}
