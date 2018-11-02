package com.liquidice.acidrain.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Intersector;
import com.liquidice.acidrain.managers.CounterMgr;
import com.liquidice.acidrain.managers.GameplayMgr;
import com.liquidice.acidrain.managers.PowerupMgr;
import com.liquidice.acidrain.managers.PropertiesMgr;
import com.liquidice.acidrain.managers.ScoreMgr;
import com.liquidice.acidrain.sprites.Bucket;
import com.liquidice.acidrain.sprites.Umbrella;
import com.liquidice.acidrain.sprites.drops.AcidDrop;
import com.liquidice.acidrain.sprites.drops.Drop;
import com.liquidice.acidrain.sprites.drops.PowerupDrop;
import com.liquidice.acidrain.sprites.drops.RainDrop;
import com.liquidice.acidrain.utilities.SpriteUtil;

import java.util.ArrayList;
import java.util.Random;

/**
 * The Gameplay Screen, where all of the gameplay drawing and actions occurs
 */
public class GameplayScreen {
    private AssetManager manager;
    private ArrayList<Drop> drops;
    private Random random;

    /**
     * Create a new gameplay screen
     * @param manager   The Asset Manager containing the textures needed by this screen
     */
    public GameplayScreen(AssetManager manager) {
        this.manager = manager;
        random = new Random();
        drops = new ArrayList<Drop>();
    }

    /**
     * Create raindrops/aciddrops/powerdrops, add them to the array, and draw them on the screen
     */
    public void randomizeDrops() {
        //Continue counting until it is time to release a RainDrop
        if (CounterMgr.getRainCount() < GameplayMgr.getRainFreq()) {
            CounterMgr.increaseRainCount();
        }
        else { //Time to release a RainDrop
            releaseDrop(true);
        }

        //Continue counting until it is time to release an AcidDrop
        if (CounterMgr.getAcidCount() < GameplayMgr.getAcidFreq()) {
            CounterMgr.increaseAcidCount();
        } else { //Time to release an AcidDrop
           releaseDrop(false);
        }
    }


    /**
     * For each drop in the array, update its position and re-draw it on the batch
     * @param batch The current sprite batch
     */
    public void updateDropPositions(Batch batch) {
        for (int i = 0; i < drops.size(); i++) {
            Drop drop = drops.get(i);

            //If drop has landed in the city
            if (drop.getY() <= SpriteUtil.middleOf(PropertiesMgr.CITY_HEIGHT)) {
                renderSplash(batch, drop);
            }
            //Drop is actively falling
            else if (drop.getSpeed() > 0) {
                batch.draw(drop.getImage(), drop.getX(), drop.getY());
                drop.setY(GameplayMgr.isPaused() ?  drop.getY() : drop.getY() - drop.getSpeed());
                drop.getRect().setY(drop.getY());
            }

            //Drop has landed somewhere
            else {
                drops.remove(drop);
            }
        }
    }

    /**
     * Compare the sprites to the registered drop rectangles to determine if a collision has occurred
     * @param batch The current sprite batch
     */
    public void checkCollision(Batch batch) {
        for (int i = 0; i < drops.size(); i++) {
            //Intersect with umbrella
            if(PowerupMgr.isUmbrellaActive() && (Intersector.overlaps(Umbrella.getLeftRect(), drops.get(i).getRect()) ||
                    Intersector.overlaps(Umbrella.getRightRect(), drops.get(i).getRect()))) {
                batch.draw(drops.get(i).getSplash(),  drops.get(i).getX(), drops.get(i).getY());
                drops.get(i).setSpeed(0);

            }
            //Intersect with LEFT bucket rectangle
            else if (Intersector.overlaps(Bucket.getLeftRect(), drops.get(i).getRect())) {
                batch.draw(drops.get(i).getLeftSplash(), drops.get(i).getX(), drops.get(i).getY());
                drops.get(i).setSpeed(0);
                manager.get("sounds/sideSplat.mp3", Sound.class).play();
                Gdx.input.vibrate(15);
            }
            //Intersect with RIGHT bucket rectangle
            else if (Intersector.overlaps(Bucket.getRightRect(), drops.get(i).getRect())) {
                batch.draw(drops.get(i).getRightSplash(), drops.get(i).getX(), drops.get(i).getY());
                drops.get(i).setSpeed(0);
                manager.get("sounds/sideSplat.mp3", Sound.class).play();
                Gdx.input.vibrate(15);
            }
            //Intersect with TOP bucket rectangle
            else if (Intersector.overlaps(Bucket.getTopRect(), drops.get(i).getRect())) {
                //RainDrop - consume
                if (drops.get(i) instanceof RainDrop) {
                    ScoreMgr.increaseCaughtScore(drops.get(i).getPoints());
                    manager.get("sounds/rainDrop.mp3", Sound.class).play();
                    Gdx.input.vibrate(40);
                    drops.remove(i);
                }
                //AcidDrop - clear bucket
                else if (drops.get(i) instanceof AcidDrop) {
                    Bucket.setImage(manager.get("bucket/bucket0.png", Texture.class));
                    manager.get("sounds/acidDrop.mp3", Sound.class).play();
                    Gdx.input.vibrate(250);
                    ScoreMgr.resetScore();
                    drops.remove(i);
                }
                //PowerupDrop - execute powerup
                else {
                    manager.get("sounds/powerup.wav", Sound.class).play();
                    Gdx.input.vibrate(100);
                    PowerupDrop drop = (PowerupDrop) drops.get(i);
                    drop.executePowerup();
                    drops.remove(i);
                }
                break;
            }
        }
    }

    /**
     * Clear all drops and reset level preferences
     */
    public void clearAll() {
        drops.clear();
        CounterMgr.clear();
        ScoreMgr.resetScore();
        ScoreMgr.resetStrength();
        Bucket.setImage(manager.get("bucket/bucket0.png", Texture.class));
    }

    /**
     * When counter has been hit, release a drop
     */
    private void releaseDrop(boolean isRain) {
        float x;
        float speed;
        int size;

        if (isRain) {
            CounterMgr.resetRainCount();
        } else {
            CounterMgr.resetAcidCount();
        }

        //Set drop speed (randomly within bounds)
        speed = GameplayMgr.getMinSpeed() + random.nextFloat() * (GameplayMgr.getMaxSpeed() - GameplayMgr.getMinSpeed());

        //Set drop size (randomly within bounds)
        size = random.nextInt((PropertiesMgr.DROP_SIZE_MAX - PropertiesMgr.DROP_SIZE_MIN) + 1) +PropertiesMgr.DROP_SIZE_MIN;

        //Set drop X position (randomly within screen width)
        x = random.nextFloat() * Gdx.graphics.getWidth();

        //Reel in corner drops
        int corner = Gdx.graphics.getWidth() -  (isRain ? manager.get("rain/drop/drop" + size + ".png", Texture.class).getWidth()
                : manager.get("acid/drop/acid" + size + ".png", Texture.class).getWidth());
        x = x > corner ? corner : x;

        //Render Drop
        if (isRain) {
            renderDrop(size, x, speed);
        } else {
            drops.add(new AcidDrop(manager, x, Gdx.graphics.getHeight(), size, speed));
        }
    }

    /**
     * Determine the type of raindrop or powerup to render
     * @param size  The rain drop size
     * @param x The randomzied X position of the drop
     * @param speed The speed of the drop
     */
    private void renderDrop(int size, float x, float speed) {
        if (size == 7 && !GameplayMgr.isPaused()) { // 17% chance of hitting a 7 and entering powerup mode
            int rand = random.nextInt((PropertiesMgr.POWERUP_CHANCE - 1) + 1) + 1; //Retrieve random int 1-25
            if (rand <= 15) { //10.2% total chance of some sort of special drop
                if (GameplayMgr.getLevel() > PropertiesMgr.UNLOCK_1_LEVEL && (rand > 1 && rand < 7)) { // MULTIPLIER - 2% total chance
                    drops.add(new PowerupDrop(manager, PropertiesMgr.UNLOCKABLE_MULTIPLIERS, x, Gdx.graphics.getHeight(), rand));
                }
                else { // LARGE DROP - 4% total chance
                    drops.add(new RainDrop(manager, x, Gdx.graphics.getHeight(), size, speed));
                }
            }
            else if (GameplayMgr.getLevel() > PropertiesMgr.UNLOCK_2_LEVEL && (rand == 16)) { //HEALTHPACK - 0.2% total chance
                drops.add(new PowerupDrop(manager, PropertiesMgr.UNLOCKABLE_HEALTHPACK, x, Gdx.graphics.getHeight()));
            }
            else if (GameplayMgr.getLevel() > PropertiesMgr.UNLOCK_3_LEVEL && (rand == 17)) { //UMBRELLA - 0.2% total chance
                drops.add(new PowerupDrop(manager, PropertiesMgr.UNLOCKABLE_UMBRELLA, x, Gdx.graphics.getHeight()));
            }
            else { // No luck this time, render random rain drop
                size = random.nextInt((PropertiesMgr.DROP_SIZE_MAX - PropertiesMgr.DROP_SIZE_MIN) + 1) +PropertiesMgr.DROP_SIZE_MIN;
                drops.add(new RainDrop(manager, x, Gdx.graphics.getHeight(), size, speed));
            }
        }
        else if (!GameplayMgr.isPaused()) { //Render raindrop size 2-6
            drops.add(new RainDrop(manager, x, Gdx.graphics.getHeight(), size, speed));
        }
    }

    /**
     * Draw a splash to the batch when a rain drop hits the city, bucket, or umbrella
     * @param batch The current sprite batch
     * @param drop  The drop to be converted to a splash
     */
    private void renderSplash(Batch batch, Drop drop) {
        //Draw the splash
        batch.draw(drop.getSplash(), drop.getX(), drop.getY());

        //If splash is acid, decrease strength
        if (CounterMgr.getSplashCount() == 0 && drop instanceof AcidDrop) {
            ScoreMgr.decreaseStrengthScore(drop.getPoints());
        }

        //Continue rendering splash until counter hits max
        if (CounterMgr.getSplashCount() < PropertiesMgr.SPLASH_LENGTH) {
            CounterMgr.increaseSplashCount();
        }

        //Remove drop from array, stop rendering it
        else {
            CounterMgr.resetSplashCount();
            drops.remove(drop);
        }
    }
}
