package com.liquidice.acidrain.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.liquidice.acidrain.managers.AssetLoader;
import com.liquidice.acidrain.managers.AudioManager;
import com.liquidice.acidrain.managers.PropManager;
import com.liquidice.acidrain.utilities.SpriteUtil;

/**
 * When an Umbrella powerup is consumed, render an umbrella on the sides of the Bucket
 */
public class Shield {
    private static AssetLoader assetLoader;
    private static Texture image;
    private static Rectangle rect;

    public static void init(AssetLoader loader) {
        assetLoader = loader;
        image = loader.getManager().get(PropManager.TEXTURE_SHIELD, Texture.class);
        AudioManager.setShieldSplatAudio(loader.getManager().get(PropManager.AUDIO_SHIELD_SPLAT, Sound.class));
        rect = new Rectangle(
                0,
                PropManager.BUCKET_HOVER - image.getHeight(),
                Gdx.graphics.getWidth(),
                SpriteUtil.middleOf(image.getHeight()));
    }

    /**
     * Load assets
     */
    public static void loadAssets() {
        image = assetLoader.getManager().get(PropManager.TEXTURE_SHIELD, Texture.class);
        AudioManager.setShieldSplatAudio(assetLoader.getManager().get(PropManager.AUDIO_SHIELD_SPLAT, Sound.class));
    }

    /**
     * Retrieve the Shield image
     * @return The Shield image
     */
    public static Texture getImage() { return image; }

    /**
     * Retrieve the rectangle for collision purposes
     * @return  The shield rectangle
     */
    public static Rectangle getRect() { return rect; }

    /**
     * Draw the shield onto the screen
     * @param batch The current sprite batch to add the shield to
     */
    public static void draw(Batch batch) {
        //Draw the umbrella wings
        batch.draw(
                image,
                0,
                PropManager.BUCKET_HOVER - image.getHeight(),
                Gdx.graphics.getWidth(),
                image.getHeight());
    }

}
