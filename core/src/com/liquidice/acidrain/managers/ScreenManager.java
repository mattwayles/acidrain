package com.liquidice.acidrain.managers;

import com.liquidice.acidrain.screens.GameOverScreen;
import com.liquidice.acidrain.screens.GameplayOverlay;
import com.liquidice.acidrain.screens.GameplayScreen;
import com.liquidice.acidrain.screens.LevelCompleteScreen;
import com.liquidice.acidrain.screens.StartScreen;
import com.liquidice.acidrain.screens.unlockables.UnlockablesScreen;

/**
 * Manage the various screesn that need to be rendered in the application, and intialize default textures
 */
public class ScreenManager {
    private static AssetLoader assetLoader;
    private static StartScreen startScreen;
    private static UnlockablesScreen unlockablesScreen;
    private static GameplayScreen gameplayScreen;
    private static GameOverScreen gameOverScreen;
    private static GameplayOverlay gameplayOverlay;
    private static LevelCompleteScreen levelCompleteScreen;

    /**
     * Create a Screen Manager instance
     * @param loader   The AssetLoader used by this Screen Manager
     */
    public static void init(AssetLoader loader) {
        assetLoader = loader;
        createStartScreen();
    }

    /**
     * Create a StartScreen
     */
    private static void createStartScreen() { startScreen = new StartScreen(assetLoader); }

    /**
     * Create an UnlockablesScreen
     */
    public static void createUnlockablesScreen() { unlockablesScreen = new UnlockablesScreen(assetLoader); }

    /**
     * Create a GameplayScreen
     */
    public static void createGameplayScreen() {
        gameplayScreen = new GameplayScreen(assetLoader);
        gameplayOverlay = new GameplayOverlay(assetLoader); }

    /**
     * Create a GameOverScreen
     */
    public static void createGameOverScreen() {
        gameOverScreen = new GameOverScreen(assetLoader);
    }

    /**
     * Create a LevelComplete screen
     */
    public static void createLevelCompleteScreen() { levelCompleteScreen = new LevelCompleteScreen(assetLoader); }
    /**
     * Retrieve the application Start Screen
     * @return The application Start Screen
     */
   public static StartScreen getStartScreen() { return startScreen; }

    /**
     * Retrieve the application Unlockables Screen
     * @return The application Unlockables Screen
     */
    public static UnlockablesScreen getUnlockablesScreen() { return unlockablesScreen; }

    /**
     * Retrieve the application Gameplay Screen
     * @return The application Gameplay Screen
     */
   public static GameplayScreen getGameplayScreen() { return gameplayScreen; }

    /**
     * Retrieve the gameplay overlay
     * @return The gameplay overlay
     */
    public static GameplayOverlay getGameplayOverlay() { return gameplayOverlay; }

    /**
     * Retrieve the application Game Over Screen
     * @return The application Game Over Screen
     */
   public static GameOverScreen getGameOverScreen() { return gameOverScreen; }

    /**
     * Retrieve the application Level Complete Screen
     * @return The application Level Complete Screen
     */
   public static LevelCompleteScreen getLevelCompleteScreen() { return levelCompleteScreen; }
}
