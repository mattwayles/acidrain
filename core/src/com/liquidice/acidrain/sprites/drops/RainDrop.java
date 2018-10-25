package com.liquidice.acidrain.sprites.drops;

import com.badlogic.gdx.graphics.Texture;

public class RainDrop extends Drop {
    public RainDrop(float x, int y, int size, float speed) {
        super.setX(x);
        super.setY(y);
        super.setPoints(size == 7 ? 10 : size);
        super.setSpeed(speed);
        super.setImage(new Texture("rain/drop/drop" + size + ".png"));
        super.setSplash(new Texture("rain/splash/rainSplash.png"));
        super.setLeftSplash(new Texture("rain/splash/rainSplashLeft.png"));
        super.setRightSplash(new Texture("rain/splash/rainSplashRight.png"));
    }


}
