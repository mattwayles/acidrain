package com.liquidice.acidrain.screens.unlockables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.liquidice.acidrain.managers.assets.Textures;

public class HealthPackScreen {
    private static SpriteBatch batch = new SpriteBatch();

    public static void display() {
        batch.begin();
        batch.draw(Textures.goldBorder, Gdx.graphics.getWidth() / 2 - Textures.goldBorder.getWidth(),
                Gdx.graphics.getHeight() / 2 - Textures.goldBorder.getHeight(), Textures.goldBorder.getWidth() * 2, Textures.goldBorder.getHeight() * 2);
        batch.end();

    }
}
