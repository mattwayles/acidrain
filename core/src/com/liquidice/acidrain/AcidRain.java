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
import com.liquidice.acidrain.managers.assets.Textures;
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
//Power-ups: Umbrella, Shield, turn all blue, multiplier
//Earn points to be used for upgrades
//Badges: Perfect scores, raindrops smashed, raindrops caught, tainted water
//Tutorial
//Magic Numbers to a 'properties' page
//Sound off / help buttons on main screen
//Smooth down the touch and drags
// Shared pref for "Top score on this level"
//Find a way to make it MORE /fun
//Clean N' Comment
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
		Score.initialize();
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
			//Supersized drops and powerups
			int count = 0;
			while (size == 7) {
				size = random.nextInt((maxSize - minSize) + 1) + minSize;
				if (count == 1 && size != 7) { //Seven hit twice, render large drop
					drops.add(new RainDrop(x, Gdx.graphics.getHeight(), 7, speed));
				}
				else if (Gameplay.getLevel() > Properties.UNLOCK_2_LEVEL && count == 2 && size == 6) { //Seven hit three times, render umbrella
					drops.add(new PowerupDrop(1, x, Gdx.graphics.getHeight()));
				}
				else if (Gameplay.getLevel() > Properties.UNLOCK_1_LEVEL && count == 2 && size == 5) { //Seven hit three times, render health pack ( ^ SAME ODDS)
					drops.add(new PowerupDrop(0, x, Gdx.graphics.getHeight()));
				}
				count++;
			}

			if (!Gameplay.isPaused()) {
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
			if(Powerup.isUmbrellaActive() && Intersector.overlaps(Umbrella.getLeftRect(), drops.get(i).getRect()) ||
					Intersector.overlaps(Umbrella.getRightRect(), drops.get(i).getRect())) {
				batch.draw(drops.get(i).getSplash(),  drops.get(i).getX(), drops.get(i).getY());
				drops.get(i).setSpeed(0);

			}
			if (Intersector.overlaps(Bucket.getLeftRect(), drops.get(i).getRect())) {
				batch.draw(drops.get(i).getLeftSplash(), drops.get(i).getX(), drops.get(i).getY());
				drops.get(i).setSpeed(0);
				Audio.playSideSplatSound();
				Gdx.input.vibrate(15);
			}
			else if (Intersector.overlaps(Bucket.getRightRect(), drops.get(i).getRect())) {
				batch.draw(drops.get(i).getRightSplash(), drops.get(i).getX(), drops.get(i).getY());
				drops.get(i).setSpeed(0);
				Audio.playSideSplatSound();
				Gdx.input.vibrate(15);
			}
			else if (Intersector.overlaps(Bucket.getTopRect(), drops.get(i).getRect())) {
				if (drops.get(i) instanceof RainDrop) {
					Score.increaseCaughtScore(drops.get(i).getPoints());
					Audio.playRainDropSound();
					Gdx.input.vibrate(40);
					drops.remove(i);
				}
				else if (drops.get(i) instanceof AcidDrop) {
					Bucket.setImage(Textures.rainBucket0);
					Audio.playAcidDropSound();
					Gdx.input.vibrate(250);
					Score.resetScore();
					drops.remove(i);
				}
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
