package com.liquidice.acidrain.sprites.drops;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Render a new Rain Drop
 */
public class RainDrop extends Drop {

    /**
     * Create a Rain Drop at a random X position within the speed bounds of the current level
     * @param manager   The Asset Manager containing the textures needed by this RainDrop
     * @param x The random X position of this raindrop
     * @param y The y position of this raindrop (initially top of screen)
     * @param size  The size value of this raindrop
     * @param speed The speed of this raindrop
     */
    public RainDrop(AssetManager manager, float x, int y, int size, float speed) {
        Texture image = manager.get("rain/drop/drop" + size + ".png", Texture.class);
        super.setX(x);
        super.setY(y);
        super.setPoints(size == 7 ? 10 : size);
        super.setSpeed(speed);
        super.setImage(image);
        super.setSplash(manager.get("rain/splash/rainSplash.png", Texture.class));
        super.setLeftSplash(manager.get("rain/splash/rainSplashLeft.png", Texture.class));
        super.setRightSplash(manager.get("rain/splash/rainSplashRight.png", Texture.class));
        super.setRect(new Rectangle(x, y, image.getWidth(), image.getHeight()));
    }
}
