package com.liquidice.acidrain.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.liquidice.acidrain.managers.CountManager;
import com.liquidice.acidrain.managers.PowerupManager;
import com.liquidice.acidrain.managers.PropManager;
import com.liquidice.acidrain.utilities.SpriteUtil;

/**
 * Render the bucket object used to catch or smash raindrops
 */
public class Bucket {
    private static Texture image;
    private static float x;
    private static Rectangle topRect;
    private static Rectangle leftRect;
    private static Rectangle rightRect;

    public static void init(Texture texture) {
        image = texture;
        x = SpriteUtil.middleOf(Gdx.graphics.getWidth()) - SpriteUtil.middleOf(image.getWidth());
        topRect = new Rectangle(x, PropManager.BUCKET_HOVER + image.getHeight(), image.getWidth(), PropManager.BUCKET_RECT_TOP_HEIGHT);
        leftRect = new Rectangle(x, PropManager.BUCKET_HOVER, PropManager.BUCKET_RECT_LEFT_WIDTH, image.getHeight());
        rightRect= new Rectangle(x + image.getWidth(), PropManager.BUCKET_HOVER, PropManager.BUCKET_RECT_RIGHT_WIDTH, image.getHeight());
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
        leftRect.set(x, PropManager.BUCKET_HOVER, PropManager.BUCKET_RECT_LEFT_WIDTH, image.getHeight() - 2);
        rightRect.set(x + image.getWidth() - PropManager.BUCKET_RECT_RIGHT_WIDTH, PropManager.BUCKET_HOVER, PropManager.BUCKET_RECT_RIGHT_WIDTH, image.getHeight() - 2);
        topRect.set(x + 2, PropManager.BUCKET_HOVER + image.getHeight()- 5, image.getWidth() - 2, 1);
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
        batch.draw(image, x, PropManager.BUCKET_HOVER, image.getWidth(), image.getHeight());

        //Check umbreall powerup activity
        if (PowerupManager.isUmbrellaActive()) {

            //Add umbrella if powerup is active
            if (CountManager.getUmbrellaCount() <= PropManager.UMBRELLA_ACTIVATION_TIME) {
                Umbrella.draw(batch);
                CountManager.increaseUmbrellaCount();
            }
            else { //Remove umbrella if powerup expired
                PowerupManager.deactivateUmbrella();
                CountManager.resetUmbrellaCount();
            }
        }
    }
}
