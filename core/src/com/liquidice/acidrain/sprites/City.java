package com.liquidice.acidrain.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.liquidice.acidrain.managers.assets.Textures;

public class City {
    private static Texture image = Textures.city10;

    public static Texture getImage() { return image; }
    public static void setImage(Texture img) { image = img; }

}
