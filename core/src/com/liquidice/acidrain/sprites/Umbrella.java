package com.liquidice.acidrain.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.liquidice.acidrain.managers.PropertiesMgr;
import com.liquidice.acidrain.utilities.SpriteUtil;

/**
 * When an Umbrella powerup is consumed, render an umbrella on the sides of the Bucket
 */
public class Umbrella {
    private static Texture leftImage;
    private static Texture rightImage;
    private static float leftX;
    private static float rightX;
    private static Rectangle leftRect;
    private static Rectangle rightRect;

    public static void init(Texture left, Texture right) {
        leftImage = left;
        rightImage = right;
        leftX = Bucket.getX() - leftImage.getWidth() + PropertiesMgr.UMBRELLA_OFFSET;
        rightX = Bucket.getX() + Bucket.getImage().getWidth() - PropertiesMgr.UMBRELLA_OFFSET;
        leftRect = new Rectangle(leftX, PropertiesMgr.BUCKET_HOVER + SpriteUtil.middleOf(leftImage.getHeight()), leftImage.getWidth(), PropertiesMgr.UMBRELLA_HEIGHT);
        rightRect = new Rectangle(rightX, PropertiesMgr.BUCKET_HOVER + SpriteUtil.middleOf(rightImage.getHeight()), rightImage.getWidth(), PropertiesMgr.UMBRELLA_HEIGHT);
    }

    /**
     * Set the left umbrella image
     * @param image The left umbrella image
     */
    public static void setLeftImage(Texture image) { leftImage = image; }

    /**
     * Set the right umbrella image
     * @param image The right umbrella image
     */
    public static void setRightImage(Texture image) { rightImage = image; }

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
        //Set rectangle X positions
        leftRect.setX(leftX);
        rightRect.setX(rightX);

        //Draw the umbrella wings
        batch.draw(leftImage, leftX, PropertiesMgr.BUCKET_HOVER, leftImage.getWidth(), leftImage.getHeight());
        batch.draw(rightImage, rightX, PropertiesMgr.BUCKET_HOVER, rightImage.getWidth(), rightImage.getHeight());
    }

}
