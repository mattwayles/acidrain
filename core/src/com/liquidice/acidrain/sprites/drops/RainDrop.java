package com.liquidice.acidrain.sprites.drops;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.liquidice.acidrain.managers.assets.Textures;

public class RainDrop extends Drop {

    public RainDrop(float x, int y, int size, float speed) {
        Texture image = Textures.findRainDropTexture(size);
        super.setX(x);
        super.setY(y);
        super.setPoints(size == 7 ? 10 : size);
        super.setSpeed(speed);
        super.setImage(image);
        super.setSplash(Textures.rainSplash);
        super.setLeftSplash(Textures.rainSplashLeft);
        super.setRightSplash(Textures.rainSplashRight);
        super.setRect(new Rectangle(x, y, image.getWidth(), image.getHeight()));
    }
}
