package com.liquidice.acidrain;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.liquidice.acidrain.managers.Background;
import com.liquidice.acidrain.managers.Counter;
import com.liquidice.acidrain.managers.Gameplay;
import com.liquidice.acidrain.managers.Gesture;
import com.liquidice.acidrain.managers.Score;
import com.liquidice.acidrain.managers.assets.Audio;
import com.liquidice.acidrain.managers.assets.Textures;
import com.liquidice.acidrain.screens.unlockables.HealthPackScreen;
import com.liquidice.acidrain.sprites.Bucket;
import com.liquidice.acidrain.sprites.City;
import com.liquidice.acidrain.sprites.Clouds;
import com.liquidice.acidrain.sprites.drops.Drop;
import com.liquidice.acidrain.sprites.drops.Powerup;
import com.liquidice.acidrain.sprites.drops.RainDrop;
import com.liquidice.acidrain.screens.GameOverScreen;
import com.liquidice.acidrain.screens.LevelCompleteScreen;
import com.liquidice.acidrain.screens.StartScreen;
import com.liquidice.acidrain.sprites.drops.AcidDrop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


//TODO:
//Unlockables screen, unlockables button, "You've unlocked..." page
//Power-ups: Umbrella, Shield, turn all blue, multiplier
//Earn points to be used for upgrades
//Badges: Perfect scores, raindrops smashed, raindrops caught, tainted water
//Tutorial
//Sound off / help buttons on main screen
//Smooth down the touch and drags
// Shared pref for "Top score on this level"
//Find a way to make it MORE /fun
//Clean N' Comment
//Artist attribution

public class AcidRain extends ApplicationAdapter {

	//Visual
	private SpriteBatch batch;

	// Management
	private static int gameState = 0;
	private List<Drop> drops = new ArrayList<Drop>();

	//Misc
	private static Preferences prefs;
	private Random random = new Random();

	@Override
	public void create () {
		batch = new SpriteBatch();
		prefs = Gdx.app.getPreferences("MyPrefs");

		Gdx.input.setInputProcessor(new GestureDetector(new Gesture()));
		Score.initialize();
		Audio.playMusic();
	}

	@Override
	public void render () {
		batch.begin();

		drawBackground();
        batch.draw(City.getImage(), 0, 0, Gdx.graphics.getWidth(), Bucket.getBucketHover() - 20);

		switch (gameState) {
			case 0:
				/* Waiting for Input */
				StartScreen.display(batch);

				//TODO: Unlockable window
				//HealthPackScreen.display();
				if (Gdx.input.justTouched()) {
					gameState = 1;
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
				if (Gdx.input.justTouched()) {
					Audio.getBirdsMusic().stop();
					Audio.getBackgroundMusic().play();
					Audio.playThunderSound();
					Gameplay.increaseLevel();
					clearAll();
                	gameState = 1;
				}
				LevelCompleteScreen.display(batch);
				break;
		}
		batch.draw(Bucket.getImage(), Bucket.getX(), Bucket.getBucketHover(), Bucket.getImage().getWidth(), Bucket.getImage().getHeight());
		drawCloudsAndScore();
		registerTouch();

		batch.end();
	}

	public static Preferences getPreferences() { return prefs; }
	public static void setGameState(int state) { gameState = state; }
	public static int getGameState() { return gameState; }

	private void drawBackground() {
		if (Counter.getBackgroundCount() < Background.LIGHTNING_FREQUENCY) {
			Counter.increaseBackgroundCount();
			Background.setBackground();
		} else {
			Counter.resetBackgroundCount();
			Background.setLightningBackground();
		}
		batch.draw(Background.getBackground(), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		drawSunnyBackground(gameState == 3);
	}

	private void drawCloudsAndScore() {
		if (gameState != 0) {
			batch.draw(Clouds.getImage(), Clouds.getX(), Clouds.getY(),
					Gdx.graphics.getWidth() + 200, Clouds.getImage().getHeight());

			if (Counter.getSunnyCount() == 0 && gameState != 2) {
				Score.display(batch);
			}
		}
	}

	private void drawSunnyBackground(boolean increase) {
		if (Counter.getSunnyCount() < 100) {
			int sunToRender = Integer.parseInt(String.valueOf(Counter.getSunnyCount()).substring(0, 1));
			batch.draw(Textures.findSunnyBackgroundTexture(sunToRender), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			if (increase) {
				Counter.increaseSunnyCount();
				Clouds.setX(Clouds.getX() - 12);
			} else if (Counter.getSunnyCount() > 0) {
				Counter.decreaseSunnyCount();
				Clouds.setX(Counter.getSunnyCount() == 0 ? Clouds.getX() + 24 : Clouds.getX() + 12);
			}
		} else {
			batch.draw(Textures.findSunnyBackgroundTexture(10), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			if (!increase) {
				Counter.decreaseSunnyCount();
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
				drop.setY(drop.getY() - drop.getSpeed());
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
			//Make the supersized drops more rare
			if (size == 7) {
                size = random.nextInt((maxSize - minSize) + 1) + minSize;
            }
            //Powerups
			//TODO: How often does a health pack come up?
            if (Gameplay.getLevel() > 5 && size == 2 ) {
				x = random.nextFloat() * Gdx.graphics.getWidth();
				if (x > 4) {
					drops.add(new Powerup(0, x, Gdx.graphics.getHeight()));
				}
            } else {
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
            drops.add(new AcidDrop(x, Gdx.graphics.getHeight(), size, speed));
        }
	}

	private void renderSplash(Drop drop) {
		batch.draw(drop.getSplash(), drop.getX(), drop.getY());
		if (Counter.getSplashCount() == 0 && drop instanceof AcidDrop) {
			Score.decreaseStrengthScore(drop.getPoints());
		}
		if (Counter.getSplashCount() < Counter.getSplashLength()) {
			Counter.increaseSplashCount();
		} else {
			Counter.resetSplashCount();
			drops.remove(drop);
		}
	}

	private void checkCollision() {
		for (int i = 0; i < drops.size(); i++) {
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
					//TODO: Powerup sound
					Gdx.input.vibrate(100);
					Powerup drop = (Powerup) drops.get(i);
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
