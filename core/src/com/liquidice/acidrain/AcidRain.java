package com.liquidice.acidrain;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Intersector;
import com.liquidice.acidrain.managers.Background;
import com.liquidice.acidrain.managers.Counter;
import com.liquidice.acidrain.managers.Gameplay;
import com.liquidice.acidrain.managers.Gesture;
import com.liquidice.acidrain.managers.Powerup;
import com.liquidice.acidrain.managers.Properties;
import com.liquidice.acidrain.managers.Score;
import com.liquidice.acidrain.managers.assets.Audio;
import com.liquidice.acidrain.managers.assets.Font;
import com.liquidice.acidrain.managers.assets.Textures;
import com.liquidice.acidrain.screens.unlockables.UnlockedScreen;
import com.liquidice.acidrain.sprites.Bucket;
import com.liquidice.acidrain.sprites.City;
import com.liquidice.acidrain.sprites.Clouds;
import com.liquidice.acidrain.sprites.GameplayButtonStage;
import com.liquidice.acidrain.sprites.Umbrella;
import com.liquidice.acidrain.sprites.drops.Drop;
import com.liquidice.acidrain.sprites.drops.PowerupDrop;
import com.liquidice.acidrain.sprites.drops.RainDrop;
import com.liquidice.acidrain.screens.GameOverScreen;
import com.liquidice.acidrain.screens.LevelCompleteScreen;
import com.liquidice.acidrain.screens.StartScreen;
import com.liquidice.acidrain.sprites.drops.AcidDrop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


//TODO:
//
// BUG: Slight wait when first drop is consumed
// BUG: Smooth down the touch and drags. Right now you can touch and teleport
// BUG: Exiting the app and resuming doesn't render most of the page
// BUG: Shared pref for "Top score on this level", update when paused or stopped
// CLEANUP: Refactor stuff out of the Main AcidRain class.
// CLEANUP: Magic numbers to Properties class
// CLEANUP: Put some time into code cleanup and commenting, fool!
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

public class AcidRain extends ApplicationAdapter {

	//Visual
	private Batch batch;

	// Management
	private static int gameState = 0;
	private List<Drop> drops = new ArrayList<Drop>();

	//Misc
	private static Preferences prefs;
	private Random random = new Random();

	//Buttons
	private GestureDetector inputProcessor;

	@Override
	public void create () {
		batch = new SpriteBatch();
		prefs = Gdx.app.getPreferences("MyPrefs");
		inputProcessor = new GestureDetector(new Gesture());
		Font.initialize();
		if (prefs.getBoolean("soundOn")) {
			Audio.playMusic();
		}
	}

	@Override
	public void render () {
		batch.begin();

		Background.draw(batch);
        City.draw(batch);
		Bucket.draw(batch);

		switch (gameState) {
			case 0:
				/* Waiting for Input */
				StartScreen.display();
				if (Gdx.input.justTouched()) {
					clearAll();
				}
				break;
			case 1:
				/* Gameplay */
				if (Counter.getSunnyCount() == 0) {

					randomizeDrops();


					//Update raindrop positions
					updateDropPositions();

					//Check for collision
					checkCollision();
				}
				break;
			case 2:
				/* Game Over */
			    GameOverScreen.display(batch);
			    Bucket.setImage(Textures.rainBucket0);
				if (Score.getCaughtPercentage() > Gameplay.getLevelBest()) {
					Gameplay.setLevelBest(Score.getCaughtPercentage());
				}
				if (Gdx.input.justTouched()) {
					clearAll();
					gameState = 0;
				}
				break;
            case 3:
                /* Level Complete */
				Audio.getBackgroundMusic().pause();
				Audio.playBirds();
				City.setImage(Textures.city10);
				LevelCompleteScreen.display(batch);
				if (Gdx.input.justTouched()) {
					Audio.getBirdsMusic().stop();
					Audio.playMusic();
					Audio.playThunderSound();
					Gameplay.increaseLevel();
					clearAll();
                	gameState = 1;
				}
				break;
		}

		//TODO: Refactor to another class
		drawCloudsAndScore();
		registerTouch();

		batch.end();
	}

	public static Preferences getPreferences() { return prefs; }
	public static int getGameState() { return gameState; }
	public static void setGameState(int state) { gameState = state; }

	private void drawCloudsAndScore() {
		if (gameState != 0) {
			batch.draw(Clouds.getImage(), Clouds.getX(), Clouds.getY(),
					Gdx.graphics.getWidth() + 200, Clouds.getImage().getHeight());

			if (Counter.getSunnyCount() == 0 && gameState != 2) {
				Score.display(batch);
				if (gameState != 3) {
					//Buttons
					batch.draw(Textures.placeholder, 0, 0, 0, 0);
					GameplayButtonStage.display(inputProcessor);
				}
			}

		}
	}

	private void updateDropPositions() {
		for (int i = 0; i < drops.size(); i++) {
			Drop drop = drops.get(i);

			if (drop.getY() <= (Bucket.getBucketHover() - 20) / 2) {
				renderSplash(drop);
			} else if (drop.getSpeed() > 0) {
				batch.draw(drop.getImage(), drop.getX(), drop.getY());
				drop.setY(Gameplay.isPaused() ?  drop.getY() : drop.getY() - drop.getSpeed());
				drop.getRect().setY(drop.getY());
			} else {
				drops.remove(drop);
			}
		}
	}

	private void randomizeDrops() {
        float x;
        float speed;
        int size;
        int minSize = 2;
        int maxSize = 7;

	    if (Counter.getRainCount() < Gameplay.getRainFreq()) {
			Counter.increaseRainCount();
		} else {
			Counter.resetRainCount();
			speed = Gameplay.getMinSpeed() + random.nextFloat() * (Gameplay.getMaxSpeed() - Gameplay.getMinSpeed());
			size = random.nextInt((maxSize - minSize) + 1) + minSize;
			x = random.nextFloat() * Gdx.graphics.getWidth();
			//Catch corner drops
			int corner = Gdx.graphics.getWidth() -  Textures.findRainDropTexture(size).getWidth();
			x = x > corner ? corner : x;
			//TODO: Refactor supersized drops and powerups to own method/class
			if (size == 7 && !Gameplay.isPaused()) {
				int rand = random.nextInt((20 - 1) + 1) + 1;
				if (rand <= 10) { //15% chance after a 7 is picked
					if (Gameplay.getLevel() > Properties.UNLOCK_1_LEVEL && (rand > 1 && rand < 7)) { // 9% change of a multiplier
						drops.add(new PowerupDrop(Properties.UNLOCKABLE_MULTIPLIERS, x, Gdx.graphics.getHeight(), rand));
					}
					drops.add(new RainDrop(x, Gdx.graphics.getHeight(), size, speed));
				}
				else if (Gameplay.getLevel() > Properties.UNLOCK_2_LEVEL && (rand == 12)) { //5% Chance of healthpack after 7 is hit
					drops.add(new PowerupDrop(Properties.UNLOCKABLE_HEALTHPACK, x, Gdx.graphics.getHeight()));
				}
				else if (Gameplay.getLevel() > Properties.UNLOCK_3_LEVEL && (rand ==11)) { //5% Chance of umbrella after 7 is hit
					drops.add(new PowerupDrop(Properties.UNLOCKABLE_UMBRELLA, x, Gdx.graphics.getHeight()));
				}
				else {
					size = random.nextInt((maxSize - minSize) + 1) + minSize;
					drops.add(new RainDrop(x, Gdx.graphics.getHeight(), size, speed));
				}
			} else if (!Gameplay.isPaused()) {
				drops.add(new RainDrop(x, Gdx.graphics.getHeight(), size, speed));
			}
		}

		if (Counter.getAcidCount() < Gameplay.getAcidFreq()) {
		    Counter.increaseAcidCount();
        } else {
		    Counter.resetAcidCount();
			speed = Gameplay.getMinSpeed() + random.nextFloat() * (Gameplay.getMaxSpeed() - Gameplay.getMinSpeed());
			size = random.nextInt((maxSize - minSize) + 1) + minSize;
			x = random.nextFloat() * Gdx.graphics.getWidth();
			//Catch corner drops
			int corner = Gdx.graphics.getWidth() -  Textures.findAcidDropTexture(size).getWidth();
			x = x > corner ? corner : x;
			if (!Gameplay.isPaused()) {
				drops.add(new AcidDrop(x, Gdx.graphics.getHeight(), size, speed));
			}
        }
	}

	private void renderSplash(Drop drop) {
		batch.draw(drop.getSplash(), drop.getX(), drop.getY());
		if (Counter.getSplashCount() == 0 && drop instanceof AcidDrop) {
			Score.decreaseStrengthScore(drop.getPoints());
		}
		if (Counter.getSplashCount() < Properties.SPLASH_LENGTH) {
			Counter.increaseSplashCount();
		} else {
			Counter.resetSplashCount();
			drops.remove(drop);
		}
	}

	private void checkCollision() {
		for (int i = 0; i < drops.size(); i++) {
			//Intersect with umbrella
			if(Powerup.isUmbrellaActive() && (Intersector.overlaps(Umbrella.getLeftRect(), drops.get(i).getRect()) ||
					Intersector.overlaps(Umbrella.getRightRect(), drops.get(i).getRect()))) {
				batch.draw(drops.get(i).getSplash(),  drops.get(i).getX(), drops.get(i).getY());
				drops.get(i).setSpeed(0);

			}
			//Intersect with LEFT bucket rectangle
			if (Intersector.overlaps(Bucket.getLeftRect(), drops.get(i).getRect())) {
				batch.draw(drops.get(i).getLeftSplash(), drops.get(i).getX(), drops.get(i).getY());
				drops.get(i).setSpeed(0);
				Audio.playSideSplatSound();
				Gdx.input.vibrate(15);
			}
			//Intersect with RIGHT bucket rectangle
			else if (Intersector.overlaps(Bucket.getRightRect(), drops.get(i).getRect())) {
				batch.draw(drops.get(i).getRightSplash(), drops.get(i).getX(), drops.get(i).getY());
				drops.get(i).setSpeed(0);
				Audio.playSideSplatSound();
				Gdx.input.vibrate(15);
			}
			//Intersect with TOP bucket rectangle
			else if (Intersector.overlaps(Bucket.getTopRect(), drops.get(i).getRect())) {
				//RainDrop - consume
				if (drops.get(i) instanceof RainDrop) {
					Score.increaseCaughtScore(drops.get(i).getPoints());
					Audio.playRainDropSound();
					Gdx.input.vibrate(40);
					drops.remove(i);
				}
				//AcidDrop - clear bucket
				else if (drops.get(i) instanceof AcidDrop) {
					Bucket.setImage(Textures.rainBucket0);
					Audio.playAcidDropSound();
					Gdx.input.vibrate(250);
					Score.resetScore();
					drops.remove(i);
				}
				//Powerup - execute powerup
				else {
					Audio.playPowerupSound();
					Gdx.input.vibrate(100);
					PowerupDrop drop = (PowerupDrop) drops.get(i);
					drop.executePowerup();
					drops.remove(i);
				}
				break;
			}
		}
	}

	private void registerTouch() {
		if (Gdx.input.isTouched() && Gdx.input.getX() != Bucket.getX()) {
			if (Gdx.input.getX() > (Bucket.getX() + Bucket.getBucketSpeed() + Bucket.getImage().getWidth() / 2)) {
				Bucket.setX(Bucket.getX() + Bucket.getBucketSpeed());
			} else if (Gdx.input.getX() < (Bucket.getX() - Bucket.getBucketSpeed() + Bucket.getImage().getWidth() / 2)) {
				Bucket.setX(Bucket.getX() - Bucket.getBucketSpeed());
			}
		}
	}

	private void clearAll() {
		drops.clear();
		Counter.clear();
		Score.resetScore();
		Score.resetStrength();
		Bucket.setImage(Textures.rainBucket0);
	}


	@Override
	public void dispose () {
		batch.dispose();
	}
}
