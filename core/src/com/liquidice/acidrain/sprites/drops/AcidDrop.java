package com.liquidice.acidrain.sprites.drops;

import com.badlogic.gdx.graphics.Texture;

public class AcidDrop extends Drop {
    public AcidDrop(float x, int y, int size, float speed) {
        super.setX(x);
        super.setY(y);
        super.setPoints(size);
        super.setSpeed(speed);
        super.setImage(new Texture("acid/drop/acid" + size + ".png"));
        super.setSplash(new Texture("acid/splash/acidSplash.png"));
        super.setLeftSplash(new Texture("acid/splash/acidSplashLeft.png"));
        super.setRightSplash(new Texture("acid/splash/acidSplashRight.png"));
    }


}
