package com.liquidice.acidrain;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Intersector;
import com.liquidice.acidrain.controllers.Background;
import com.liquidice.acidrain.controllers.Counter;
import com.liquidice.acidrain.controllers.Gameplay;
import com.liquidice.acidrain.controllers.Gesture;
import com.liquidice.acidrain.controllers.Score;
import com.liquidice.acidrain.controllers.assets.Audio;
import com.liquidice.acidrain.controllers.assets.Textures;
import com.liquidice.acidrain.sprites.Bucket;
import com.liquidice.acidrain.sprites.drops.Drop;
import com.liquidice.acidrain.sprites.drops.RainDrop;
import com.liquidice.acidrain.screens.GameOverScreen;
import com.liquidice.acidrain.screens.LevelCompleteScreen;
import com.liquidice.acidrain.screens.StartScreen;
import com.liquidice.acidrain.sprites.drops.AcidDrop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


//TODO:
//Actual clouds, rainfall behind them
//Better font rendering using actual BMFont files/images
//Gradual fall back to storm after Level Complete (?)
//Perfect score! + badge
//Power-ups
//Badges
//Smooth down the touch and drags
// Shared pref for "Top score on this level"
//Find a way to make it MORE fun
//Clean N' Comment
//Artist attribution

public class AcidRain extends ApplicationAdapter {

	//Visual
	private SpriteBatch batch;

	// Management
	private static int gameState;
	private List<Drop> drops = new ArrayList<Drop>();

	//Misc
	private static Preferences prefs;
	private Random random = new Random();

	@Override
	public void create () {
		batch = new SpriteBatch();
		prefs = Gdx.app.getPreferences("MyPrefs");

		//TODO: Remove DEBUG:
		prefs.clear();
		prefs.flush();

		Gdx.input.setInputProcessor(new GestureDetector(new Gesture()));

		Audio.playMusic();
	}

	@Override
	public void render () {
		batch.begin();

		drawBackground();

		switch (gameState) {
			case 0:
				/* Waiting for Input */
				StartScreen.display(batch);
				if (Gdx.input.justTouched()) {
					gameState = 1;
				}
				break;
			case 1:
				/* Gameplay */
				randomizeDrops();

				//Update raindrop positions
				updateDropPositions();

				//Check for collision
                checkCollision();

				Score.display(batch);
				break;
			case 2:
				/* Game Over */
			    GameOverScreen.display(batch);
				Bucket.setImage(Textures.acidBucket0);
				if (Score.getCaughtPercentage() > Gameplay.getLevelBest()) {
					Gameplay.setLevelBest(Score.getCaughtPercentage());
				}
				if (Gdx.input.justTouched()) {
					gameState = 1;
					clearAll();
				}
				break;
            case 3:
                /* Level Complete */
				Audio.getBackgroundMusic().pause();
				Audio.playBirds();

				//TODO: Cleanup
				if (Counter.getSunnyCount() < 100) {
					int sunToRender = Integer.parseInt(String.valueOf(Counter.getSunnyCount()).substring(0, 1));
					batch.draw(Textures.findSunnyBackgroundTexture(sunToRender), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
					Counter.increaseSunnyCount();
				} else {
					batch.draw(Textures.findSunnyBackgroundTexture(10), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
				}



				if (Gdx.input.justTouched()) {
					gameState = 1;
					Audio.getBirdsMusic().stop();
					Audio.getBackgroundMusic().play();
					Gameplay.increaseLevel();
                	clearAll();
				}
				LevelCompleteScreen.display(batch);
				break;
		}

		batch.draw(Textures.city, 0, 0, Gdx.graphics.getWidth(), Bucket.getBucketHover() - 20);
		batch.draw(Bucket.getImage(), Bucket.getX(), Bucket.getBucketHover(), Bucket.getImage().getWidth(), Bucket.getImage().getHeight());

		registerTouch();

		batch.end();
	}

	public static Preferences getPreferences() { return prefs; }
	public static void setGameState(int state) { gameState = state; }

	private void drawBackground() {
		if (Counter.getBackgroundCount() < Background.LIGHTNING_FREQUENCY) {
			Counter.increaseBackgroundCount();
			Background.setBackground();
		} else {
			Counter.resetBackgroundCount();
			Background.setLightningBackground();
		}
		batch.draw(Background.getBackground(), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
        int minSize = 1;
        int maxSize = 7;

	    if (Counter.getRainCount() < Gameplay.getRainFreq()) {
			Counter.increaseRainCount();
		} else {
			Counter.resetRainCount();
			x = random.nextFloat() * Gdx.graphics.getWidth();
			//Catch corner drops
			x = (x < 50 ? 50 : (x > Gdx.graphics.getWidth() - 50 ? Gdx.graphics.getWidth() - 50 : x));
			speed = Gameplay.getMinSpeed() + random.nextFloat() * (Gameplay.getMaxSpeed() - Gameplay.getMinSpeed());
			size = random.nextInt((maxSize - minSize) + 1) + minSize;
			//Make the supersized drops more rare
			if (size == 7) {
				size = random.nextInt((maxSize - minSize) + 1) + minSize;
			}
			drops.add(new RainDrop(x, Gdx.graphics.getHeight(), size, speed));
		}

		if (Counter.getAcidCount() < Gameplay.getAcidFreq()) {
		    Counter.increaseAcidCount();
        } else {
		    Counter.resetAcidCount();
		    x = random.nextFloat() * Gdx.graphics.getWidth();
			//Catch corner drops
			x = (x < 50 ? 50 : (x > Gdx.graphics.getWidth() - 50 ? Gdx.graphics.getWidth() - 50 : x));
			speed = Gameplay.getMinSpeed() + random.nextFloat() * (Gameplay.getMaxSpeed() - Gameplay.getMinSpeed());
			size = random.nextInt((maxSize - minSize) + 1) + minSize;
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
				else {
					Bucket.setImage(Textures.rainBucket0);
					Audio.playAcidDropSound();
					Gdx.input.vibrate(250);
					gameState = 2;
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
