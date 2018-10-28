package com.liquidice.acidrain.managers;

import com.badlogic.gdx.graphics.Texture;
import com.liquidice.acidrain.managers.assets.Textures;

public class Background {
    public static final int LIGHTNING_FREQUENCY = 500;

    private static Texture background;

    public static Texture getBackground() { return background; }
    public static void setBackground() { background = Textures.stormBackground; }
    public static void setLightningBackground() { background = Textures.lightningBackground; }
}
