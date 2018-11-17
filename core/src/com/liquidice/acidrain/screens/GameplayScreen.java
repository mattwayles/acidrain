package com.liquidice.acidrain.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Intersector;
import com.liquidice.acidrain.managers.AudioManager;
import com.liquidice.acidrain.managers.CountManager;
import com.liquidice.acidrain.managers.GameplayManager;
import com.liquidice.acidrain.managers.PowerupManager;
import com.liquidice.acidrain.managers.PropManager;
import com.liquidice.acidrain.managers.ScoreManager;
import com.liquidice.acidrain.sprites.Bucket;
import com.liquidice.acidrain.sprites.Shield;
import com.liquidice.acidrain.sprites.Teamwork;
import com.liquidice.acidrain.sprites.Umbrella;
import com.liquidice.acidrain.sprites.drops.AcidDrop;
import com.liquidice.acidrain.sprites.drops.Drop;
import com.liquidice.acidrain.sprites.drops.PowerupDrop;
import com.liquidice.acidrain.sprites.drops.PurpleRainDrop;
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
        int powerupLevel = PowerupManager.checkPowerupLevel();

        if (powerupLevel > 0 && drops.isEmpty()) {
            renderPowerupDrop(powerupLevel, random.nextFloat() * Gdx.graphics.getWidth());
        }
        //Continue counting until it is time to release a RainDrop
        if (CountManager.getRainCount() < GameplayManager.getRainFreq()) {
            CountManager.increaseRainCount();
        } else { //Time to release a RainDrop
            releaseDrop(true);
        }

        //Continue counting until it is time to release an AcidDrop
        if (CountManager.getAcidCount() < GameplayManager.getAcidFreq()) {
            CountManager.increaseAcidCount();
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

            //Check Filtration status
            if (PowerupManager.isFilterActive() && drops.get(i) instanceof AcidDrop) {
                drops.set(i, new RainDrop(manager, drop.getX(), drop.getY(), drop.getPoints(), drop.getSpeed()));
            }

            //If drop has landed in the city
            if (drop.getY() <= SpriteUtil.middleOf(PropManager.CITY_HEIGHT)) {
                renderSplash(batch, drop);
            }
            //Drop is actively falling
            else if (drop.getSpeed() > 0) {
                batch.draw(drop.getImage(), drop.getX(), drop.getY());
                drop.setY(GameplayManager.isPaused() ?  drop.getY() : drop.getY() - drop.getSpeed());
                drop.getRect().setY(drop.getY());
            }

            //Drop has landed somewhere
            else {
                drops.remove(drop);
            }
        }

        managePowerupCount(batch);
    }

    /**
     * Compare the sprites to the registered drop rectangles to determine if a collision has occurred
     * @param batch The current sprite batch
     */
    public void checkCollision(Batch batch) {
        for (int i = 0; i < drops.size(); i++) {
            //Intersect with TOP bucket rectangle
            if (Intersector.overlaps(Bucket.getTopRect(), drops.get(i).getRect())) {
                //RainDrop - consume
                if (drops.get(i) instanceof RainDrop || drops.get(i) instanceof PurpleRainDrop) {
                    ScoreManager.increaseCaughtScore(drops.get(i).getPoints());
                    AudioManager.playRainDrop();
                    Gdx.input.vibrate(PropManager.RAINDROP_CAUGHT_VIBRATE_TIME);
                    drops.remove(i);
                }
                //AcidDrop - clear bucket
                else if (drops.get(i) instanceof AcidDrop) {
                    Bucket.setImage(manager.get(PropManager.TEXTURE_BUCKET_0, Texture.class));
                    AudioManager.playAcidDrop();
                    Gdx.input.vibrate(PropManager.ACIDDROP_CAUGHT_VIBRATE_TIME);
                    ScoreManager.resetScore();
                    drops.remove(i);
                }
                //PowerupDrop - execute powerup
                else {
                    AudioManager.playPowerup();
                    Gdx.input.vibrate(PropManager.POWERDROP_CAUGHT_VIBRATE_TIME);
                    PowerupDrop drop = (PowerupDrop) drops.get(i);
                    drop.executePowerup();
                    drops.remove(i);
                }
                break;
            }
            //Intersect with LEFT bucket rectangle
            else if (Intersector.overlaps(Bucket.getLeftRect(), drops.get(i).getRect())) {
                batch.draw(drops.get(i).getLeftSplash(), drops.get(i).getX(), drops.get(i).getY());
                drops.get(i).setSpeed(0);
                AudioManager.playSideSplat();
                Gdx.input.vibrate(PropManager.DROP_SMASH_VIBRATE_TIME);
            }
            //Intersect with RIGHT bucket rectangle
            else if (Intersector.overlaps(Bucket.getRightRect(), drops.get(i).getRect())) {
                batch.draw(drops.get(i).getRightSplash(), drops.get(i).getX(), drops.get(i).getY());
                drops.get(i).setSpeed(0);
                AudioManager.playSideSplat();
                Gdx.input.vibrate(PropManager.DROP_SMASH_VIBRATE_TIME);
            }
            //Intersect with umbrella
            else if(PowerupManager.isUmbrellaActive() && (Intersector.overlaps(Umbrella.getLeftRect(), drops.get(i).getRect()) ||
                    Intersector.overlaps(Umbrella.getRightRect(), drops.get(i).getRect()))) {
                AudioManager.playUmbrellaSplat();
                Gdx.input.vibrate(PropManager.DROP_SMASH_VIBRATE_TIME);
                batch.draw(drops.get(i).getSplash(),  drops.get(i).getX(), drops.get(i).getY());
                drops.get(i).setSpeed(0);

            }
            //Intersect with shield
            else if (PowerupManager.isShieldActive() && Intersector.overlaps(Shield.getRect(), drops.get(i).getRect())) {
                AudioManager.playShieldSplat();
                Gdx.input.vibrate(PropManager.DROP_SMASH_VIBRATE_TIME);
                batch.draw(drops.get(i).getSplash(),  drops.get(i).getX(), drops.get(i).getY());
                drops.get(i).setSpeed(0);
            }
            //Intersect with teamwork rainbow
            else if (PowerupManager.isTeamworkActive() && Intersector.overlaps(Teamwork.getRect(), drops.get(i).getRect())) {
                if (!(drops.get(i) instanceof AcidDrop)) {
                    if (drops.get(i) instanceof PowerupDrop) {
                        ((PowerupDrop) drops.get(i)).executePowerup();
                    } else {
                        ScoreManager.increaseCaughtScore(drops.get(i).getPoints());
                    }
                    AudioManager.playRainDrop();
                    Gdx.input.vibrate(PropManager.DROP_SMASH_VIBRATE_TIME);
                    drops.remove(i);
                }
            }
        }
    }

    /**
     * Clear all drops and reset level preferences
     */
    public void clearAll() {
        drops.clear();
        CountManager.clear();
        ScoreManager.resetScore();
        ScoreManager.resetStrength();
        Bucket.setImage(manager.get(PropManager.TEXTURE_BUCKET_0, Texture.class));
    }

    /**
     * When counter has been hit, release a drop
     */
    private void releaseDrop(boolean isRain) {
        float x;
        float speed;
        int size;

        if (isRain) {
            CountManager.resetRainCount();
        } else {
            CountManager.resetAcidCount();
        }

        //Set drop speed (randomly within bounds)
        speed = GameplayManager.getMinSpeed() + random.nextFloat() * (GameplayManager.getMaxSpeed() - GameplayManager.getMinSpeed());

        //Set drop size (randomly within bounds)
        size = random.nextInt((PropManager.DROP_SIZE_MAX - PropManager.DROP_SIZE_MIN) + 1) +PropManager.DROP_SIZE_MIN;

        //Set drop X position (randomly within screen width)
        x = random.nextFloat() * Gdx.graphics.getWidth();

        //Reel in corner drops
        int corner = Gdx.graphics.getWidth() -  PropManager.PAGE_WIDTH_OFFSET;
        x = x > corner ? corner : x;

        //Render Drop
        if (!GameplayManager.isPaused()) {
            if (isRain) {
                renderDrop(size, x, speed);
            }
            else {
                if (PowerupManager.isPurpleRainActive() && size <= PropManager.PURPLE_RAIN_OVERRIDE) {
                    drops.add(new PurpleRainDrop(manager, x, Gdx.graphics.getHeight(), speed));
                }
                else {
                    drops.add(new AcidDrop(manager, x, Gdx.graphics.getHeight(), size, speed));
                }
            }
        }
    }

    /**
     * Determine the type of raindrop or powerup to render
     * @param size  The rain drop size
     * @param x The randomzied X position of the drop
     * @param speed The speed of the drop
     */
    private void renderDrop(int size, float x, float speed) {
        if (size == 7) { // 17% chance of hitting a 7 and entering powerup mode
            int rand = random.nextInt((PropManager.POWERUP_CHANCE_TOTAL - 1) + 1) + 1;
            if (!renderPowerupDrop(rand, x)) {
                if (rand <= PropManager.POWERUP_CHANCE)
                 { // LARGE DROP
                    drops.add(new RainDrop(manager, x, Gdx.graphics.getHeight(), size, speed));
                } else {
                    size = random.nextInt((PropManager.DROP_SIZE_MAX - PropManager.DROP_SIZE_MIN) + 1) + PropManager.DROP_SIZE_MIN;
                    drops.add(new RainDrop(manager, x, Gdx.graphics.getHeight(), size, speed));
                }
            }
        }
        else { //Render raindrop size 2-6
            drops.add(new RainDrop(manager, x, Gdx.graphics.getHeight(), size, speed));
        }
    }

    /**
     * Render a powerup drop
     * @param rand value of
     * @param x The randomzied X position of the drop
     * @return Boolean value indicating whether a powerup was dropped
     */
    private boolean renderPowerupDrop(int rand, float x) {

        //Reel in corner drops
        int corner = Gdx.graphics.getWidth() -  PropManager.PAGE_WIDTH_OFFSET;
        x = x > corner ? corner : x;

        boolean isDropped = false;
        if (GameplayManager.getLevel() >= PropManager.UNLOCK_1_LEVEL && (rand > 1 && rand < 7)) {
            drops.add(new PowerupDrop(manager, PropManager.UNLOCKABLE_MULTIPLIERS_TYPE, x, Gdx.graphics.getHeight(), rand));
            isDropped = true;
        }
        else if (GameplayManager.getLevel() >= PropManager.UNLOCK_2_LEVEL && (rand == PropManager.TEAMWORK_CHANCE)) {
            drops.add(new PowerupDrop(manager, PropManager.UNLOCKABLE_TEAMWORK_TYPE, x, Gdx.graphics.getHeight()));
            isDropped = true;
        }
        else if (GameplayManager.getLevel() >= PropManager.UNLOCK_3_LEVEL && (rand == PropManager.HEALTHPACK_CHANCE)) {
            drops.add(new PowerupDrop(manager, PropManager.UNLOCKABLE_HEALTHPACK_TYPE, x, Gdx.graphics.getHeight()));
            isDropped = true;
        }
        else if (GameplayManager.getLevel() >= PropManager.UNLOCK_4_LEVEL && (rand == PropManager.UMBRELLA_CHANCE)) {
            drops.add(new PowerupDrop(manager, PropManager.UNLOCKABLE_UMBRELLA_TYPE, x, Gdx.graphics.getHeight()));
            isDropped = true;
        }
        else if (GameplayManager.getLevel() >= PropManager.UNLOCK_5_LEVEL && (rand == PropManager.PURPLE_RAIN_CHANCE)) {
            drops.add(new PowerupDrop(manager, PropManager.UNLOCKABLE_PURPLE_RAIN_TYPE, x, Gdx.graphics.getHeight()));
            isDropped = true;
        }
        else if (GameplayManager.getLevel() >= PropManager.UNLOCK_6_LEVEL && (rand == PropManager.SHIELD_CHANCE)) {
            drops.add(new PowerupDrop(manager, PropManager.UNLOCKABLE_SHIELD_TYPE, x, Gdx.graphics.getHeight()));
            isDropped = true;
        }
        else if (GameplayManager.getLevel() >= PropManager.UNLOCK_7_LEVEL && (rand == PropManager.FILTER_CHANCE)) {
            drops.add(new PowerupDrop(manager, PropManager.UNLOCKABLE_FILTRATION_TYPE, x, Gdx.graphics.getHeight()));
            isDropped = true;
        }
        return isDropped;
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
        if (CountManager.getSplashCount() == 0) {
            if (drop instanceof AcidDrop) {
                ScoreManager.decreaseStrengthScore(drop.getPoints());
            }
            else if (drop instanceof PurpleRainDrop) {
                //Purple Rain will only decrease half of a large acid drop
                ScoreManager.decreaseStrengthScore(3);
            }
        }

        //Continue rendering splash until counter hits max
        if (CountManager.getSplashCount() < PropManager.SPLASH_LENGTH) {
            CountManager.increaseSplashCount();
        }

        //Remove drop from array, stop rendering it
        else {
            CountManager.resetSplashCount();
            drops.remove(drop);
        }
    }


    /**
     * Check the status of active powerups and modify if necessary
     */
    private void managePowerupCount(Batch batch) {
        if (PowerupManager.isFilterActive()) {
            if (CountManager.getFilterCount() <= PropManager.FILTER_ACTIVATION_TIME) {
                CountManager.increaseFilterCount();
                PowerupManager.checkCountdown(batch, PropManager.FILTER_ACTIVATION_TIME);
            } else { //Remove Filter if powerup expired
                PowerupManager.deactivateFilter();
                CountManager.resetFilterCount();
            }
        }
        if (PowerupManager.isPurpleRainActive()) {
            if (CountManager.getPurpleRainCount() <= PropManager.PURPLE_RAIN_ACTIVATION_TIME) {
                CountManager.increasePurpleRainCount();
                PowerupManager.checkCountdown(batch, PropManager.PURPLE_RAIN_ACTIVATION_TIME);
            } else { //Remove Purple Rain if powerup expired
                PowerupManager.deactivatePurpleRain();
                CountManager.resetPurpleRainCount();
            }
        }
    }
}
