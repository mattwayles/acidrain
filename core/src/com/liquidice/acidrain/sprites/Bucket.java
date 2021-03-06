package com.liquidice.acidrain.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.liquidice.acidrain.managers.AssetLoader;
import com.liquidice.acidrain.managers.CountManager;
import com.liquidice.acidrain.managers.GameplayManager;
import com.liquidice.acidrain.managers.PowerupManager;
import com.liquidice.acidrain.managers.PropManager;
import com.liquidice.acidrain.utilities.SpriteUtil;

/**
 * Render the bucket object used to catch or smash raindrops
 */
public class Bucket {
    private static AssetLoader assetLoader;
    private static Texture image;
    private static float x;
    private static Rectangle topRect;
    private static Rectangle leftRect;
    private static Rectangle rightRect;

    public static void init(AssetLoader loader) {
        assetLoader = loader;
        image = assetLoader.getManager().get(PropManager.TEXTURE_BUCKET_0, Texture.class);

        x = SpriteUtil.middleOf(Gdx.graphics.getWidth()) - SpriteUtil.middleOf(image.getWidth());
        topRect = new Rectangle(
                x + PropManager.BUCKET_TOP_OFFSET,
                PropManager.BUCKET_HOVER + image.getHeight(),
                image.getWidth() - PropManager.BUCKET_TOP_OFFSET,
                PropManager.BUCKET_RECT_TOP_HEIGHT);
        leftRect = new Rectangle(
                x,
                PropManager.BUCKET_HOVER,
                SpriteUtil.middleOf(image.getWidth()),
                image.getHeight());
        rightRect= new Rectangle(
                x + SpriteUtil.middleOf(image.getWidth()),
                PropManager.BUCKET_HOVER,
                SpriteUtil.middleOf(image.getWidth()),
                image.getHeight());
    }

    /**
     * Retrieve the currentX position of the bucket
     * @return The current X position of the bucket
     */
    public static float getX() {
        return x;
    }

    /**
     * Set a new X position of the bucket
     * @param bucketX The new X position of the bucket
     */
    public static void setX(float bucketX) {
        x = bucketX;
        topRect.set(
                x + PropManager.BUCKET_TOP_OFFSET,
                PropManager.BUCKET_HOVER + image.getHeight(),
                image.getWidth() - PropManager.BUCKET_TOP_OFFSET * 2,
                PropManager.BUCKET_RECT_TOP_HEIGHT);
        leftRect.set(
                x,
                PropManager.BUCKET_HOVER,
                SpriteUtil.middleOf(image.getWidth()),
                image.getHeight() - PropManager.BUCKET_SIDE_OFFSET);
        rightRect.set(
                x + image.getWidth() - SpriteUtil.middleOf(image.getWidth()),
                PropManager.BUCKET_HOVER,
                SpriteUtil.middleOf(image.getWidth()),
                image.getHeight() - PropManager.BUCKET_SIDE_OFFSET);
    }

    /**
     * Retrieve the current bucket image
     * @return The current bucket image
     */
    public static Texture getImage() { return image; }

    /**
     * Set a new bucket image
     * @param bucketImg The new bucket image
     */
    public static void setImage(Texture bucketImg) { image = bucketImg; }

    /**
     * Retrieve the top bucket rectangle for collision purposes
     * @return The top bucket rectangle
     */
    public static Rectangle getTopRect() { return topRect; }

    /**
     * Retrieve the left bucket rectangle for collision purposes
     * @return The left bucket rectangle
     */
    public static Rectangle getLeftRect() {
        return leftRect;
    }

    /**
     * Retrieve the right bucket rectangle for collision purposes
     * @return The right bucket rectangle
     */
    public static Rectangle getRightRect() {
        return rightRect;
    }

    /**
     * Draw the bucket on the active screen
     * @param batch The active sprite batch to include the bucket in
     */
    public static void draw(Batch batch) {
        //Check umbrella powerup activity
        if (PowerupManager.isUmbrellaActive()) {

            //Add umbrella if powerup is active
            if (CountManager.getUmbrellaCount() <= PropManager.UMBRELLA_ACTIVATION_TIME) {
                if (Umbrella.getLeftImage() == null) {
                    Umbrella.init(assetLoader);
                } else {
                    Umbrella.loadAssets();
                }
                Umbrella.draw(batch);
                CountManager.increaseUmbrellaCount();
                PowerupManager.checkCountdown(batch, PropManager.UMBRELLA_ACTIVATION_TIME);
            } else { //Remove umbrella if powerup expired
                PowerupManager.deactivateUmbrella();
                CountManager.resetUmbrellaCount();
            }
        }
        //Add shield if powerup is active
        if (PowerupManager.isShieldActive()) {
            if (CountManager.getShieldCount() <= PropManager.SHIELD_ACTIVATION_TIME) {
                if (Shield.getImage() == null) {
                    Shield.init(assetLoader);
                } else {
                    Shield.loadAssets();
                }
                Shield.draw(batch);
                CountManager.increaseShieldCount();
                PowerupManager.checkCountdown(batch, PropManager.SHIELD_ACTIVATION_TIME);
            } else { //Remove shield if powerup expired
                PowerupManager.deactivateShield();
                CountManager.resetShieldCount();
            }
        }
        //Add teamwork hands if powerup is active
        if (PowerupManager.isTeamworkActive()) {
            if (CountManager.getTeamworkCount() <= PropManager.TEAMWORK_ACTIVATION_TIME) {
                if (Teamwork.getImage() == null) {
                    Teamwork.init(assetLoader);
                } else {
                    Teamwork.loadAssets();
                }
                Teamwork.draw(batch);
                CountManager.increaseTeamworkCount();
                PowerupManager.checkCountdown(batch, PropManager.TEAMWORK_ACTIVATION_TIME);
            } else { //Remove teamwork if powerup expired
                PowerupManager.deactivateTeamwork();
                CountManager.resetTeamworkCount();
            }
        }

        if (GameplayManager.getGameState() == PropManager.GAME_PLAY_STATE) {
            batch.draw(image, x, PropManager.BUCKET_HOVER, image.getWidth(), image.getHeight());
        }
    }
}
