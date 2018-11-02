package com.liquidice.acidrain.sprites.drops;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Render a new Acid Drop
 */
public class AcidDrop extends Drop {
    /**
     * Create an Acid Drop at a random X position within the speed bounds of the current level
     * @param manager   The Asset Manager containing the textures needed by this AcidDrop
     * @param x The random X position of this aciddrop
     * @param y The y position of this aciddrop (initially top of screen)
     * @param size  The size value of this aciddrop
     * @param speed The speed of this aciddrop
     */
    public AcidDrop(AssetManager manager, float x, int y, int size, float speed) {
        Texture image = manager.get("acid/drop/acid" + size + ".png", Texture.class);
        super.setX(x);
        super.setY(y);
        super.setPoints(size);
        super.setSpeed(speed);
        super.setImage(image);
        super.setSplash(manager.get("acid/splash/acidSplash.png", Texture.class));
        super.setLeftSplash(manager.get("acid/splash/acidSplashLeft.png", Texture.class));
        super.setRightSplash(manager.get("acid/splash/acidSplashRight.png", Texture.class));
        super.setRect(new Rectangle(x, y, image.getWidth(), image.getHeight()));
    }


}
