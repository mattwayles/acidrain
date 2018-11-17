package com.liquidice.acidrain;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.liquidice.acidrain.managers.AssetLoader;
import com.liquidice.acidrain.managers.AudioManager;
import com.liquidice.acidrain.managers.GameplayManager;
import com.liquidice.acidrain.managers.PreferenceManager;
import com.liquidice.acidrain.managers.PropManager;
import com.liquidice.acidrain.managers.ScoreManager;
import com.liquidice.acidrain.sprites.Background;
import com.liquidice.acidrain.managers.CountManager;
import com.liquidice.acidrain.managers.GestureManager;
import com.liquidice.acidrain.managers.ScreenManager;
import com.liquidice.acidrain.sprites.Bucket;
import com.liquidice.acidrain.sprites.City;
import com.liquidice.acidrain.sprites.Clouds;
import com.liquidice.acidrain.utilities.SpriteUtil;



//TODO:

// CLEANUP: Screen size management is all jacked up

// BUG: Game appears to lag when not shut down for a while, indicating bad resource release

// FEATURE: (Powerup) - Super Healthpack, or something like that; completely restore city strength
// FEATURE: (Powerup) - Something that gives immunity against catching acid for the rest of the level!
// FEATURE: Snowstorm levels. SNOW STORM LEVELS!
// FEATURE: Tornado levels; drops rotate, X position changes
// FEATURE: Point system. Gain points when rain caught/levels completed/random coin drops/offline/etc.
	// that can be used to increase raindrop power, decrease acid power, increase coin worth, and increase offline earnings. Display points on main screen and gameplay screen
	// lifetime points can be a badge earner, leaderboard category
// FEATURE: Badges: Perfect scores, raindrops smashed, raindrops caught, tainted water - make them unlock backgrounds, raindrops, acid drops, buckets, cities, etc!
// FEATURE: Purchase powerups with in-app purchases
// FEATURE: Implement Android Leaderboard API to track leader
// FEATURE: "Overall leader", an average of all leaderboard rankings
// FEATURE: If no go on Android Leaderboard: Firebase integration, React website (leaderboard, awareness competition)
// FEATURE: Moar powerups
// FEATURE: New buckets, new methods of catching drops
// FEATURE: Purchase new buckets with in-app purchases
// FEATURE: Multiplayer, otherwise nobody will play!
// FEATURE: When I have a perfect, beautiful app with auto-updating React leaderboards, buy Mac & port to iOS
// Artist attribution

/**
 * Play Acid Rain, a game where you protect your city by filling a bucket of clean water while
 * simultaneously crushing acid raindrops before they fall.
 */
public class AcidRain extends ApplicationAdapter {

	//Visual
	private Batch batch;

	// Management
	private AssetLoader assetLoader;

	//Input
	private static GestureDetector inputProcessor;


	//TODO: Remove after analysis
	private long originalJavaHeap;
	private long originalNativeHeap;
	private int heapCount;




	/**
	 * Retrieve the application input processor
	 * @return	The application input processor
	 */
	public static GestureDetector getInputProcessor() { return inputProcessor; }


	/**
	 * Application startup, prior to first render
	 */
	@Override
	public void create () {
		//Create a new sprite batch
		batch = new SpriteBatch();

		//Set the input processor
		inputProcessor = new GestureDetector(new GestureManager());
		this.assetLoader = new AssetLoader();
		ScreenManager.init(assetLoader);

		//Play background music
		AudioManager.playThunderstorm();

		//TODO: Remove after analysis
		originalJavaHeap = Gdx.app.getJavaHeap();
		originalNativeHeap = Gdx.app.getNativeHeap();
	}

	/**
	 * Render - consistently called function that renders the elements seen on the screen
	 */
	@Override
	public void render () {
		//TODO: Remove after analysis
		if (heapCount == 60) {
			Gdx.app.error("Java Heap Delta: ", String.valueOf(Gdx.app.getJavaHeap() - originalJavaHeap));
			Gdx.app.error("Native Heap Delta: ", String.valueOf(Gdx.app.getNativeHeap() - originalNativeHeap));
			heapCount = 0;
		} else {
			heapCount++;
		}

		batch.begin();
		Background.draw(batch);
        City.draw(batch);
        Bucket.draw(batch);
		//Render different Screens based on game state
		switch (GameplayManager.getGameState()) {
			case 0: /* Waiting for Input - Display StartScreen */
				batch.draw(this.assetLoader.getManager().get(PropManager.TEXTURE_PLACEHOLDER, Texture.class), 0,0,0,0);
				ScreenManager.getStartScreen().display();
//				if (Gdx.input.justTouched()) {
//					GameplayManager.setGameState(PropManager.GAME_START_STATE);
//					ScreenManager.getGameplayScreen().clearAll();
//				}
				break;
			case 1: /* Gameplay */
				if (CountManager.getSunnyCount() == 0) {
					//Create rain/acid/power drops
					ScreenManager.getGameplayScreen().randomizeDrops();

					//Update raindrop positions
					ScreenManager.getGameplayScreen().updateDropPositions(batch);

					//Check for collision
					ScreenManager.getGameplayScreen().checkCollision(batch);
				}
				break;
			case 2: /* Game Over */
				ScreenManager.getGameOverScreen().display(batch);
				if (Gdx.input.justTouched()) {
					ScreenManager.getGameplayScreen().clearAll();
					GameplayManager.setGameState(PropManager.GAME_START_STATE);
				}
				break;
			case 3:
				/* Level Complete */
				ScreenManager.getLevelCompleteScreen().display(batch);
				if (CountManager.getSunnyCount() == 100 && Gdx.input.justTouched()) { //Start new Level
					AudioManager.stopBirds();
					AudioManager.playThunderstorm();
					AudioManager.playThundercrack();
					ScreenManager.getGameplayScreen().clearAll();
					GameplayManager.resume();
					GameplayManager.setGameState(PropManager.GAME_PLAY_STATE);
				}
				break;
		}

		//Render overlays (clouds, score, buttons)
		if (GameplayManager.getGameState() > 0) {
			ScreenManager.getGameplayOverlay().display(batch);
			registerTouch();
		}

		batch.end();
	}

	/**
	 * Register a touch event that moves the bucket slowly instead of transporting it
	 */
	private void registerTouch() {
		if (!GameplayManager.isPaused() && Gdx.input.isTouched()) {
		    if ((Gdx.graphics.getHeight() - Gdx.input.getY()) < Clouds.getY()) {
                if (Gdx.input.getX() > (Bucket.getX() + PropManager.BUCKET_SPEED + SpriteUtil.middleOf(Bucket.getImage().getWidth()))) {
                    Bucket.setX(Bucket.getX() + PropManager.BUCKET_SPEED);
                } else if (Gdx.input.getX() < (Bucket.getX() - PropManager.BUCKET_SPEED + SpriteUtil.middleOf(Bucket.getImage().getWidth()))) {
                    Bucket.setX(Bucket.getX() - PropManager.BUCKET_SPEED);
                }
            }
		}
	}

	/**
	 * On application pause, pause gameplay
	 */
	@Override
	public void pause() {
		GameplayManager.pause();
	}

	/**
	 * On application end, dispose the batch
	 */
	@Override
	public void dispose () {
		if (GameplayManager.getGameState() == PropManager.GAME_PLAY_STATE
				&& ScoreManager.getCaughtPercentage() > GameplayManager.getLevelBest()) {
			PreferenceManager.putInt(PropManager.PREF_LEVEL_BEST, ScoreManager.getCaughtPercentage());
		}
		this.assetLoader.getManager().dispose();
		batch.dispose();
	}
}
