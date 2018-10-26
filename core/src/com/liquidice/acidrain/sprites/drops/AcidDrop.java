package com.liquidice.acidrain.sprites.drops;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.liquidice.acidrain.controllers.assets.Textures;

public class AcidDrop extends Drop {
    public AcidDrop(float x, int y, int size, float speed) {
        Texture image = Textures.findAcidDropTexture(size);
        super.setX(x);
        super.setY(y);
        super.setPoints(size);
        super.setSpeed(speed);
        super.setImage(image);
        super.setSplash(Textures.acidSplash);
        super.setLeftSplash(Textures.acidSplashLeft);
        super.setRightSplash(Textures.acidSplashRight);
        super.setRect(new Rectangle(x, y, image.getWidth(), image.getHeight()));
    }


}
