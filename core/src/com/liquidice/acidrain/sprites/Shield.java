package com.liquidice.acidrain.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.liquidice.acidrain.managers.PropManager;
import com.liquidice.acidrain.utilities.SpriteUtil;

/**
 * When an Umbrella powerup is consumed, render an umbrella on the sides of the Bucket
 */
public class Shield {
    private static Texture image;
    private static Rectangle rect;

    public static void init(Texture beam) {
        image = beam;
        rect = new Rectangle(
                0,
                PropManager.BUCKET_HOVER - image.getHeight(),
                Gdx.graphics.getWidth(),
                SpriteUtil.middleOf(beam.getHeight()));
    }

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
