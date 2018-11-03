package com.liquidice.acidrain.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.liquidice.acidrain.managers.PropManager;

/**
 * Render Clouds at the top of the screen that dissipate when level is complete
 */
public class Clouds {
    private static Texture image;
    private static int x = PropManager.DEFAULT_CLOUD_X;
    private static int y;

    public static void init(Texture texture) {
        image = texture;
        y = Gdx.graphics.getHeight() - texture.getHeight();
    }

    /**
     * Retrieve the Texture image set for the clouds
     * @return  The clouds image
     */
    public static Texture getImage() { return image; }

    /**
     * Set a new Texture image for the clouds
     * @param img The new Texture image for the clouds
     */
    public static void setImage(Texture img) { image = img; }

    /**
     * Retrieve the current clouds X position
     * @return The current clouds X position
     */
    public static int getX() { return x; }

    /**
     * Retrieve the current clouds Y position
     * @return  The current clouds Y position
     */
    public static int getY() { return y; }

    /**
     * Set the clouds X position
     * @param newX  The new clouds X position
     */
    public static void setX(int newX) { x = newX; }
}
