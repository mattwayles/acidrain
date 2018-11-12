package com.liquidice.acidrain;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
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
import com.liquidice.acidrain.managers.SpriteManager;
import com.liquidice.acidrain.sprites.Bucket;
import com.liquidice.acidrain.sprites.City;
import com.liquidice.acidrain.utilities.SpriteUtil;



//TODO:
// CLEANUP: Screen size management is all jacked up
// TEST: Correct countdown when multiple powerups are active
// TEST: Countdown sound?
// BUG: No city on Start Screen
// FEATURE: (Powerup) - Purple rain; turn acid purple, damage is halved for X seconds. Make fun with guitar drop & sounds
// FEATURE: (Powerup) - Super Healthpack, or something like that; completely restore city strength
// FEATURE: Badges: Perfect scores, raindrops smashed, raindrops caught, tainted water
// FEATURE: Snow levels
// FEATURE: Tornado; drops rotate, X position changes
// FEATURE:Purchase powerups with in-app purchases
// FEATURE: Implement Android Leaderboard API to track leader
// FEATURE: If no go on Android Leaderboard: Firebase integration, React website (leaderboard, awareness competition)
// FEATURE: Moar powerups
// FEATURE: New buckets, new methods of catching drops
// FEATURE: Purchase new buckets with in-app purchases
// FEATURE: Multiplayer, otherwise nobody will play!
// FEATURE: When I have a perfect, beautiful app with auto-updating React leaderboards, buy Mac & port to iOS
// Internal Discussion: Should points/coins be implemented in any way?
//Artist attribution

/**
 * Play Acid Rain, a game where you protect your city by filling a bucket of clean water while
 * simultaneously crushing acid raindrops before they fall.
 */
public class AcidRain extends ApplicationAdapter {

	//Visual
	private Batch batch;

	// Management
	private ScreenManager screenManager;
	private AssetManager assetManager;

	//Input
	private static GestureDetector inputProcessor;


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
		this.assetManager = new AssetLoader().getManager();
		screenManager = new ScreenManager(this.assetManager);
		SpriteManager.init(this.assetManager);

		//Play background music
		AudioManager.playThunderstorm();
	}

	/**
	 * Render - consistently called function that renders the elements seen on the screen
	 */
	@Override
	public void render () {
		batch.begin();

		//On every screen, draw the background, city, and bucket
		Background.draw(batch);
		Bucket.draw(batch);
		City.draw(batch);

		//Render different Screens based on game state
		switch (GameplayManager.getGameState()) {
			case 0: /* Waiting for Input - Display StartScreen */
				screenManager.getStartScreen().display();
				if (Gdx.input.justTouched()) {
					GameplayManager.setGameState(PropManager.GAME_START_STATE);
					screenManager.getGameplayScreen().clearAll();
				}
				break;
			case 1: /* Gameplay */
				if (CountManager.getSunnyCount() == 0) {
					//Create rain/acid/power drops
					screenManager.getGameplayScreen().randomizeDrops();

					//Update raindrop positions
					screenManager.getGameplayScreen().updateDropPositions(batch);

					//Check for collision
					screenManager.getGameplayScreen().checkCollision(batch);
				}
				break;
			case 2: /* Game Over */
				screenManager.getGameOverScreen().display(batch);
				if (Gdx.input.justTouched()) {
					screenManager.getGameplayScreen().clearAll();
					GameplayManager.setGameState(PropManager.GAME_START_STATE);
				}
				break;
			case 3:
				/* Level Complete */
				screenManager.getLevelCompleteScreen().display(batch);
				if (CountManager.getSunnyCount() == 100 && Gdx.input.justTouched()) { //Start new Level
					AudioManager.stopBirds();
					AudioManager.playThunderstorm();
					AudioManager.playThundercrack();
					screenManager.getGameplayScreen().clearAll();
					GameplayManager.resume();
					GameplayManager.setGameState(PropManager.GAME_PLAY_STATE);
				}
				break;
		}

		//Render overlays (clouds, score, buttons)
		if (GameplayManager.getGameState() > 0) {
			screenManager.getGameplayOverlay().display(batch);
			registerTouch();
		}

		batch.end();
	}

	/**
	 * Register a touch event that moves the bucket slowly instead of transporting it
	 */
	private void registerTouch() {
		if (!GameplayManager.isPaused() && Gdx.input.isTouched() && Gdx.input.getX() != Bucket.getX()) {
			if (Gdx.input.getX() > (Bucket.getX() + PropManager.BUCKET_SPEED + SpriteUtil.middleOf(Bucket.getImage().getWidth()))) {
				Bucket.setX(Bucket.getX() + PropManager.BUCKET_SPEED);
			} else if (Gdx.input.getX() < (Bucket.getX() - PropManager.BUCKET_SPEED + SpriteUtil.middleOf(Bucket.getImage().getWidth()))) {
				Bucket.setX(Bucket.getX() - PropManager.BUCKET_SPEED);
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
		assetManager.dispose();
		batch.dispose();
	}
}
