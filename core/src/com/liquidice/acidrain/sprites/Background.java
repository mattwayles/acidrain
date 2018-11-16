package com.liquidice.acidrain.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.liquidice.acidrain.managers.CountManager;
import com.liquidice.acidrain.managers.GameplayManager;
import com.liquidice.acidrain.managers.PropManager;
import com.liquidice.acidrain.utilities.GifDecoder;

/**
 * Render the appropriate background image
 */
public class Background {
    private static AssetManager manager;

    //Animation
    private static Animation<TextureRegion> animation;
    private static float elapsed = 0;

    public static void init(AssetManager mgr) {
        setManager(mgr);
        animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("backgrounds/rainBackground.gif").read());
        animation.setFrameDuration(0.1f);
    }

    /**
     * Set the asset manager to control the assets this class uses
     * @param mgr   The Asset Manager containing assets required for this class
     */
    public static void setManager(AssetManager mgr) { manager = mgr; }

    /**
     * Draw the background image on the batch
     * @param batch The current sprite batch
     */
    public static void draw(Batch batch) {

        //Draw the Storm background
        if (CountManager.getBackgroundCount() < PropManager.LIGHTNING_FREQUENCY) {
            CountManager.increaseBackgroundCount();
            elapsed += Gdx.graphics.getDeltaTime();
            batch.draw(animation.getKeyFrame(elapsed), 0, 0, Gdx.graphics.getWidth() + 50, Gdx.graphics.getHeight());
        }
        else { //Draw a lightning flash
            CountManager.resetBackgroundCount();
            batch.draw(manager.get(PropManager.TEXTURE_BG_LIGHTNING, Texture.class), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }

        //Check for level complete and draw sunny background
        drawSunnyBackground(batch, GameplayManager.getGameState() == PropManager.LEVEL_COMPLETE_STATE);
    }

    /**
     * When a level is completed, render a slowly-shifting sunny background
     * @param batch The current sprite batch
     * @param increase  Whether to slowly render the sunny background, or slowly take it away
     */
    private static void drawSunnyBackground(Batch batch, boolean increase) {
        //Render a different sunny background image based on the current counter
        if (CountManager.getSunnyCount() < PropManager.SUNNY_COUNTER) {
            int firstDigit = Integer.parseInt(String.valueOf(CountManager.getSunnyCount()).substring(0, 1));
            int sunToRender = firstDigit > 0 ? firstDigit : 1;
            batch.draw(manager.get(PropManager.SUNNY_SKY_PREFIX + sunToRender + PropManager.PNG, Texture.class), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

            //Sun is breaking through the clouds
            if (increase) {
                CountManager.increaseSunnyCount();
                Clouds.setX(Clouds.getX() - PropManager.CLOUD_MOVE_SPEED);
            }
            //Sun is being overtaken by clouds
            else if (CountManager.getSunnyCount() > 0) {
                CountManager.decreaseSunnyCount();
                Clouds.setX(CountManager.getSunnyCount() == 0 ? Clouds.getX() + PropManager.CLOUD_MOVE_SPEED * 2 : Clouds.getX() + PropManager.CLOUD_MOVE_SPEED);
            }
        } else { //Render the fully sunny sky background
            batch.draw(manager.get(PropManager.TEXTURE_SUNNY_SKY_10, Texture.class), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            if (!increase) {
                CountManager.decreaseSunnyCount();
            }
        }
    }
}
