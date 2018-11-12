package com.liquidice.acidrain.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.liquidice.acidrain.managers.PropManager;
import com.liquidice.acidrain.utilities.SpriteUtil;

/**
 * When a Teamwork powerup is consumed, render a raindow behind the city
 */
public class Teamwork {
    private static Texture image;
    private static Rectangle rect;

    public static void init(Texture rainbow) {
        image = rainbow;
        rect = new Rectangle(
                0,
                0,
                Gdx.graphics.getWidth(),
                rainbow.getHeight() - 10);
    }

    /**
     * Retrieve the rectangle for collision purposes
     * @return  The rainbow rectangle
     */
    public static Rectangle getRect() { return rect; }

    /**
     * Draw the rainbow onto the screen
     * @param batch The current sprite batch to add the rainbow to
     */
    public static void draw(Batch batch) {
        //Draw the rainbow
        batch.draw(
                image,
                0,
                0,
                Gdx.graphics.getWidth(),
                image.getHeight());
    }

}
