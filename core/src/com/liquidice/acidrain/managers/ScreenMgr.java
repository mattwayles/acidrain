package com.liquidice.acidrain.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.liquidice.acidrain.screens.GameOverScreen;
import com.liquidice.acidrain.screens.GameplayButtonOverlay;
import com.liquidice.acidrain.screens.GameplayOverlay;
import com.liquidice.acidrain.screens.GameplayScreen;
import com.liquidice.acidrain.screens.LevelCompleteScreen;
import com.liquidice.acidrain.screens.StartScreen;

/**
 * Manage the various screesn that need to be rendered in the application, and intialize default textures
 */
public class ScreenMgr {
    private StartScreen startScreen;
    private GameplayScreen gameplayScreen;
    private GameOverScreen gameOverScreen;
    private GameplayOverlay gameplayOverlay;
    private GameplayButtonOverlay gameplayButtonOverlay;
    private LevelCompleteScreen levelCompleteScreen;

    /**
     * Create a Screen Manager instance
     * @param manager   The AssetManager used by this Screen Manager
     */
    public ScreenMgr(AssetManager manager) {
        startScreen = new StartScreen(manager);
        gameplayScreen = new GameplayScreen(manager);
        gameOverScreen = new GameOverScreen(manager);
        gameplayOverlay = new GameplayOverlay((manager));
        gameplayButtonOverlay = new GameplayButtonOverlay((manager));
        levelCompleteScreen = new LevelCompleteScreen(manager);
   }

    /**
     * Retrieve the application Start Screen
     * @return The application Start Screen
     */
   public StartScreen getStartScreen() { return startScreen; }

    /**
     * Retrieve the application Gameplay Screen
     * @return The application Gameplay Screen
     */
   public GameplayScreen getGameplayScreen() { return gameplayScreen; }

    /**
     * Retrieve the gameplay overlay
     * @return The gameplay overlay
     */
    public GameplayOverlay getGameplayOverlay() { return gameplayOverlay; }

    /**
     * Retrieve the gameplay button overlay
     * @return The gameplaybutton  overlay
     */
    public GameplayButtonOverlay getGameplayButtonOverlay() { return gameplayButtonOverlay; }

    /**
     * Retrieve the application Game Over Screen
     * @return The application Game Over Screen
     */
   public GameOverScreen getGameOverScreen() { return gameOverScreen; }

    /**
     * Retrieve the application Level Complete Screen
     * @return The application Level Complete Screen
     */
   public LevelCompleteScreen getLevelCompleteScreen() { return levelCompleteScreen; }
}
