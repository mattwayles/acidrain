package com.liquidice.acidrain.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.liquidice.acidrain.managers.assets.Textures;

public class Clouds {
    private static Texture image = Textures.clouds;
    private static int x = -100;
    private static int y = Gdx.graphics.getHeight() - Textures.clouds.getHeight();

    public static Texture getImage() { return image; }
    public static int getX() { return x; }
    public static int getY() { return y; }
    public static void setX(int newX) { x = newX; }

}
