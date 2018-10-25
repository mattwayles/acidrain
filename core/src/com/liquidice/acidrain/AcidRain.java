package com.liquidice.acidrain;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.liquidice.acidrain.Controllers.Counter;
import com.liquidice.acidrain.Controllers.Gesture;
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

import static com.liquidice.acidrain.Controllers.Counter.getAcidCount;
import static com.liquidice.acidrain.Controllers.Counter.getRainCount;
import static com.liquidice.acidrain.Controllers.Counter.getSplashCount;
import static com.liquidice.acidrain.Controllers.Counter.getSplashLength;
import static com.liquidice.acidrain.Controllers.Counter.increaseAcidCount;
import static com.liquidice.acidrain.Controllers.Counter.increaseRainCount;
import static com.liquidice.acidrain.Controllers.Counter.increaseSplashCount;
import static com.liquidice.acidrain.Controllers.Counter.resetAcidCount;
import static com.liquidice.acidrain.Controllers.Counter.resetRainCount;
import static com.liquidice.acidrain.Controllers.Counter.resetSplashCount;
import static com.liquidice.acidrain.Controllers.Gameplay.getAcidFreq;
import static com.liquidice.acidrain.Controllers.Gameplay.getLevel;
import static com.liquidice.acidrain.Controllers.Gameplay.getLevelBest;
import static com.liquidice.acidrain.Controllers.Gameplay.getMaxSpeed;
import static com.liquidice.acidrain.Controllers.Gameplay.getMinSpeed;
import static com.liquidice.acidrain.Controllers.Gameplay.getRainFreq;
import static com.liquidice.acidrain.Controllers.Gameplay.increaseLevel;
import static com.liquidice.acidrain.Controllers.Gameplay.setLevelBest;
import static com.liquidice.acidrain.Controllers.Score.displayScore;
import static com.liquidice.acidrain.Controllers.Score.getCurrentPercentage;
import static com.liquidice.acidrain.Controllers.Score.increaseScore;
import static com.liquidice.acidrain.Controllers.Score.resetScore;


//TODO:
//Sounds
//Shared pref for "Top score on this level"
//Replace "Level Complete" and "Game Over" with A Lolita Scorned images
//Lightning flashes
//Gradual dissipation of storm
//Vibrations
//Power-ups
//Smooth down the touch and drags
//Find a way to make it fun
//Clean N' Comment
//Artist attribution

public class AcidRain extends ApplicationAdapter {

	//Visual
	private SpriteBatch batch;
	private Texture background;

	// Management
	private static int gameState;
	private List<Drop> drops = new ArrayList<Drop>();

	//Misc
	private static Preferences prefs;
	private Random random = new Random();

	@Override
	public void create () {
		batch = new SpriteBatch();
		background  = new Texture("background.jpg");
		prefs = Gdx.app.getPreferences("MyPrefs");

		//TODO: Remove DEBUG:
		prefs.clear();
		prefs.flush();


		Gdx.input.setInputProcessor(new GestureDetector(new Gesture()));
		Music music = Gdx.audio.newMusic(Gdx.files.internal("thunderstorm.mp3"));
		music.play();
	}

	@Override
	public void render () {
		batch.begin();

		//Draw background, bucket
		drawCoreSprites();

		switch (gameState) {
			case 0:
				/* Waiting for Input */
				new StartScreen(batch);
				if (Gdx.input.justTouched()) {
					gameState = 1;
				}
				break;
			case 1:
				/* Gameplay */
				displayScore(batch);

				randomizeDrops();

				//Update raindrop positions
				updateDropPositions();

				//Check for collision
                checkCollision();

				break;
			case 2:
				/* Game Over */
			    new GameOverScreen(batch);
				Bucket.setImage(new Texture("acid/bucket/bucket0.png"));
				if (getCurrentPercentage() > getLevelBest()) {
					setLevelBest(getCurrentPercentage());
				}
				if (Gdx.input.justTouched()) {
					gameState = 1;
					clearAll();
				}
				break;
            case 3:
                /* Level Complete */
				new LevelCompleteScreen(batch, getLevel());
                if (Gdx.input.justTouched()) {
					gameState = 1;
					prepareNextLevel();
                	clearAll();
				}
				break;
		}

		registerTouch();

		batch.end();
	}

	public static Preferences getPreferences() { return prefs; }
	public static void setGameState(int state) { gameState = state; }

	private void drawCoreSprites() {
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(Bucket.getImage(), Bucket.getX(), Bucket.getBucketHover(), Bucket.getImage().getWidth(), Bucket.getImage().getHeight());
	}

	private void updateDropPositions() {
		for (int i = 0; i < drops.size(); i++) {
			Drop drop = drops.get(i);

			if (drop.getY() <= 0) {
				renderSplash(drop);
			} else if (drop.getSpeed() > 0) {
				batch.draw(drop.getImage(), drop.getX(), drop.getY());
				drop.setY(drop.getY() - drop.getSpeed());
				drop.setRect(new Rectangle(drop.getX(), drop.getY(), drop.getImage().getWidth(), drop.getImage().getHeight()));
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

	    if (getRainCount() < getRainFreq()) {
			increaseRainCount();
		} else {
			resetRainCount();
			x = random.nextFloat() * Gdx.graphics.getWidth();
			speed = getMinSpeed() + random.nextFloat() * (getMaxSpeed() - getMinSpeed());
			size = random.nextInt((maxSize - minSize) + 1) + minSize;
			//Make the supersized drops more rare
			if (size == 7) {
				size = random.nextInt((maxSize - minSize) + 1) + minSize;
			}
			drops.add(new RainDrop(x, Gdx.graphics.getHeight(), size, speed));
		}

		if (getAcidCount() < getAcidFreq()) {
		    increaseAcidCount();
        } else {
		    resetAcidCount();
		    x = random.nextFloat() * Gdx.graphics.getWidth();
			speed = getMinSpeed() + random.nextFloat() * (getMaxSpeed() - getMinSpeed());
			size = random.nextInt((maxSize - minSize) + 1) + minSize;
            drops.add(new AcidDrop(x, Gdx.graphics.getHeight(), size, speed));
        }
	}

	private void renderSplash(Drop drop) {
		batch.draw(drop.getSplash(), drop.getX(), drop.getY());
		if (getSplashCount() < getSplashLength()) {
			increaseSplashCount();
		} else {
			resetSplashCount();
			drops.remove(drop);
		}
	}

	private void checkCollision() {
		for (int i = 0; i < drops.size(); i++) {
			if (Intersector.overlaps(Bucket.getTopRect(), drops.get(i).getRect())) {
				if (drops.get(i) instanceof RainDrop) {
					increaseScore(drops.get(i).getPoints());
					drops.remove(i);
				}
				else {
					Bucket.setImage(new Texture("acid/bucket/bucket0.png"));
					gameState = 2;
				}

				break;
			}
			else if (Intersector.overlaps(Bucket.getLeftRect(), drops.get(i).getRect())) {
				batch.draw(drops.get(i).getLeftSplash(), drops.get(i).getX(), drops.get(i).getY());
				drops.get(i).setSpeed(0);
			}
			else if (Intersector.overlaps(Bucket.getRightRect(), drops.get(i).getRect())) {
				batch.draw(drops.get(i).getRightSplash(), drops.get(i).getX(), drops.get(i).getY());
				drops.get(i).setSpeed(0);
			}
		}
	}

	private void prepareNextLevel() {
		//TODO: Prepare next level!
		// This is the creme-de-la-creme of the gameplay. The next level needs to slightly increase difficulty, in a way that is fun yet challenging
		// Methods of increasing difficulty:
		// 		Rain/Acid Drop speed
		//		Rain/Acid drop frequency
		//		Higher win score
		//		(Future) less time to get winScore before storm clears
		increaseLevel();
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
		resetScore();
		Bucket.setImage(new Texture("rain/bucket/bucket0.png"));
	}


	@Override
	public void dispose () {
		batch.dispose();
	}
}
