package com.liquidice.acidrain.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.liquidice.acidrain.managers.PropManager;

/**
 * Render a city at the bottom of the screen. The city will get incresingly more red as it weakens
 */
public class City {
    private static Texture image;

    /**
     * Retrieve the current city image
     * @return The current city image
     */
    public static Texture getImage() { return image; }

    /**
     * Set a new city image
     * @param img   The new city image
     */
    public static void setImage(Texture img) { image = img; }

    /**
     * Draw the city to the current screen
     * @param batch The current sprite batch to include the city in
     */
    public static void draw(Batch batch) {
        batch.draw(City.getImage(), 0, 0, Gdx.graphics.getWidth(), PropManager.CITY_HEIGHT);
    }

}
