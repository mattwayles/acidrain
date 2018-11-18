package com.liquidice.acidrain.sprites;

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
public class Umbrella {
    private static AssetLoader assetLoader;
    private static Texture leftImage;
    private static Texture rightImage;
    private static float leftX;
    private static float rightX;
    private static Rectangle leftRect;
    private static Rectangle rightRect;

    public static void init(AssetLoader loader) {
        assetLoader = loader;
        leftImage = loader.getManager().get(PropManager.TEXTURE_UMBRELLA_LEFT);
        rightImage = loader.getManager().get(PropManager.TEXTURE_UMBRELLA_RIGHT);
        leftX = Bucket.getX() - leftImage.getWidth() + PropManager.UMBRELLA_OFFSET;
        rightX = Bucket.getX() + Bucket.getImage().getWidth() - PropManager.UMBRELLA_OFFSET;
        leftRect = new Rectangle(
                leftX,
                PropManager.BUCKET_HOVER + SpriteUtil.middleOf(leftImage.getHeight()),
                leftImage.getWidth(),
                PropManager.UMBRELLA_HEIGHT);
        rightRect = new Rectangle(
                rightX,
                PropManager.BUCKET_HOVER + SpriteUtil.middleOf(rightImage.getHeight()),
                rightImage.getWidth(),
                PropManager.UMBRELLA_HEIGHT);
        AudioManager.setUmbrellaSplatAudio(loader.getManager().get(PropManager.AUDIO_UMBRELLA_SPLAT, Sound.class));
    }

    /**
     * Load assets
     */
    public static void loadAssets() {
        leftImage = assetLoader.getManager().get(PropManager.TEXTURE_UMBRELLA_LEFT);
        rightImage = assetLoader.getManager().get(PropManager.TEXTURE_UMBRELLA_RIGHT);
        AudioManager.setUmbrellaSplatAudio(assetLoader.getManager().get(PropManager.AUDIO_UMBRELLA_SPLAT, Sound.class));
    }

    /**
     * Retrieve the left umbrella image
     * @return The left umbrella image
     */
    static Texture getLeftImage() { return leftImage; }

    /**
     * Retrieve the left umbrella rectangle for collision purposes
     * @return  The left umbrella rectangle
     */
    public static Rectangle getLeftRect() { return leftRect; }

    /**
     * Retrieve the right umbrella rectangle for collision purposes
     * @return  The right umbrella rectangle
     */
    public static Rectangle getRightRect() { return rightRect; }

    /**
     * Draw the umbrella onto the screen
     * @param batch The current sprite batch to add the umbrella to
     */
    public static void draw(Batch batch) {
        leftX = Bucket.getX() - leftImage.getWidth() + PropManager.UMBRELLA_OFFSET;
        rightX = Bucket.getX() + Bucket.getImage().getWidth() - PropManager.UMBRELLA_OFFSET;

        //Set rectangle X positions
        leftRect.setX(leftX);
        rightRect.setX(rightX);

        //Draw the umbrella wings
        batch.draw(leftImage, leftX, PropManager.BUCKET_HOVER, leftImage.getWidth(), leftImage.getHeight());
        batch.draw(rightImage, rightX, PropManager.BUCKET_HOVER, rightImage.getWidth(), rightImage.getHeight());
    }

}
