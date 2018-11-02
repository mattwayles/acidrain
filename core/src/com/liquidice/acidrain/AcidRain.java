package com.liquidice.acidrain;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.liquidice.acidrain.managers.AssetMgr;
import com.liquidice.acidrain.sprites.Background;
import com.liquidice.acidrain.managers.CounterMgr;
import com.liquidice.acidrain.managers.GameplayMgr;
import com.liquidice.acidrain.managers.GestureMgr;
import com.liquidice.acidrain.managers.PropertiesMgr;
import com.liquidice.acidrain.managers.ScreenMgr;
import com.liquidice.acidrain.managers.SpriteMgr;
import com.liquidice.acidrain.sprites.Bucket;
import com.liquidice.acidrain.sprites.City;
import com.liquidice.acidrain.utilities.SpriteUtil;



//TODO:
//
// BUG: Pause button not showing up!
// BUG: Can't catch water, or decrease city strength!
// BUG Play sounds only if user-selected soundOn!
// BUG: Game Over ScreenMgr causes app to fail
// BUG: Exiting the app and resuming doesn't render most of the page
// BUG: Multipliers shows up even when not unlocked
// BUG: Shared pref for "Top score on this level", update when paused or stopped
// CLEANUP: Magic numbers to PropertiesMgr class
// CLEANUP: If Properties only used in one class, make them final in that class. If shared, move to SharedProperties manager
// CLEANUP: Put some time into code cleanup and commenting, fool!
// CLEANUP: Avoid long wait time by using AssetMgr asynchronously
// FEATURE: Power-ups: Shield, turn all blue, Audience Participation
// FEATURE: Badges: Perfect scores, raindrops smashed, raindrops caught, tainted water
// FEATURE: Tutorial
// FEATURE: (Meh..?) Earn points to be used for upgrades
// FEATURE:Purchase powerups with in-app purchases
// FEATURE: New buckets, new methods of catching drops
// FEATURE: Purchase new buckets with in-app purchases
// FEATURE: Leaderboard, multiplayer, otherwise nobody will play!
//Find a way to make it MORE fun
//Artist attribution

/**
 * Play Acid Rain, a game where you protect your city by filling a bucket of clean water while
 * simultaneously crushing acid raindrops before they fall.
 */
public class AcidRain extends ApplicationAdapter {

	//Visual
	private Batch batch;

	// Gameplay Management
	private static int gameState = 0;
	private ScreenMgr screenManager;
	private com.badlogic.gdx.assets.AssetManager assetManager;
	//Misc
	private static Preferences prefs;

	//Input
	private static GestureDetector inputProcessor;

	/**
	 * Retrieve application preferences
	 * @return	Application preferences
	 */
	public static Preferences getPreferences() { return prefs; }

	/**
	 * Retrieve current game state
	 * @return The current game state
	 */
	public static int getGameState() { return gameState; }

	/**
	 * Set the new game state
	 * @param state	The new game state
	 */
	public static void setGameState(int state) { gameState = state; }

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

		//Retrieve shared prefs
		prefs = Gdx.app.getPreferences("MyPrefs");

		//Set the input processor
		inputProcessor = new GestureDetector(new GestureMgr());

		//Load assets
		AssetMgr assetMgr = new AssetMgr();
		this.assetManager = assetMgr.getManager();
		screenManager = new ScreenMgr(this.assetManager);
		SpriteMgr.init(this.assetManager);

		//Play background music
		if (prefs.getBoolean("soundOn")) {
			assetManager.get("sounds/thunderstorm.mps", Music.class).play();
		}
	}

	/**
	 * Render - consistently called function that renders the elements seen on the screen
	 */
	@Override
	public void render () {
		batch.begin();

		//On every screen, draw the background, city, and bucket
		Background.draw(batch);
		City.draw(batch);
		Bucket.draw(batch);

		//Render different Screens based on game state
		switch (gameState) {
			case 0: /* Waiting for Input - Display StartScreen */
				screenManager.getStartScreen().display();
				if (Gdx.input.justTouched()) {
					screenManager.getGameplayScreen().clearAll();
				}
				break;
			case 1: /* Gameplay */
				if (CounterMgr.getSunnyCount() == 0) {
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
					gameState = 0;
				}
				break;
            case 3:
                /* Level Complete */
				screenManager.getLevelCompleteScreen().display(batch);
				if (Gdx.input.justTouched()) { //Start new Level
					assetManager.get("sounds/birds.wav", Music.class).stop();
					assetManager.get("sounds/thunderstorm.mp3", Music.class).play();
					assetManager.get("sounds/thunderCrack.wav", Sound.class).play();
					screenManager.getGameplayScreen().clearAll();
					GameplayMgr.increaseLevel();
                	gameState = 1;
				}
				break;
		}

		//Render overlays (clouds, score, buttons)
        if (gameState > 0) {
            screenManager.getGameplayOverlay().display(batch);
			registerTouch();
        }

		batch.end();
	}

	/**
	 * Register a touch event that moves the bucket slowly instead of transporting it
	 */
	private void registerTouch() {
		if (Gdx.input.isTouched() && Gdx.input.getX() != Bucket.getX()) {
			if (Gdx.input.getX() > (Bucket.getX() + PropertiesMgr.BUCKET_SPEED + SpriteUtil.middleOf(Bucket.getImage().getWidth()))) {
				Bucket.setX(Bucket.getX() + PropertiesMgr.BUCKET_SPEED);
			} else if (Gdx.input.getX() < (Bucket.getX() - PropertiesMgr.BUCKET_SPEED + SpriteUtil.middleOf(Bucket.getImage().getWidth()))) {
				Bucket.setX(Bucket.getX() - PropertiesMgr.BUCKET_SPEED);
			}
		}
	}

	/**
	 * On application pause, dispose the assets
	 */
	@Override
	public void pause() {
		assetManager.dispose();
	}

	/**
	 * On application end, dispose the batch
	 */
	@Override
	public void dispose () {
		batch.dispose();
	}
}
