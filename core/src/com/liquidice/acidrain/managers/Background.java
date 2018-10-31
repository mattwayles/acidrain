package com.liquidice.acidrain.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.liquidice.acidrain.AcidRain;
import com.liquidice.acidrain.managers.assets.Textures;
import com.liquidice.acidrain.sprites.Clouds;

public class Background {


    private static Texture background;

    private static Texture getBackground() { return background; }
    private static void setBackground() { background = Textures.stormBackground; }
    private static void setLightningBackground() { background = Textures.lightningBackground; }

    public static void draw(Batch batch) {
        if (Counter.getBackgroundCount() < Properties.LIGHTNING_FREQUENCY) {
            Counter.increaseBackgroundCount();
            Background.setBackground();
        } else {
            Counter.resetBackgroundCount();
            Background.setLightningBackground();
        }
        batch.draw(Background.getBackground(), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        drawSunnyBackground(batch, AcidRain.getGameState() == Properties.LEVEL_COMPLETE_STATE);
    }

    private static void drawSunnyBackground(Batch batch, boolean increase) {
        if (Counter.getSunnyCount() < Properties.SUNNY_COUNTER) {
            int sunToRender = Integer.parseInt(String.valueOf(Counter.getSunnyCount()).substring(0, 1));
            batch.draw(Textures.findSunnyBackgroundTexture(sunToRender), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            if (increase) {
                Counter.increaseSunnyCount();
                Clouds.setX(Clouds.getX() - Properties.CLOUD_MOVE_SPEED);
            } else if (Counter.getSunnyCount() > 0) {
                Counter.decreaseSunnyCount();
                Clouds.setX(Counter.getSunnyCount() == 0 ? Clouds.getX() + Properties.CLOUD_MOVE_SPEED * 2 : Clouds.getX() + Properties.CLOUD_MOVE_SPEED);
            }
        } else {
            batch.draw(Textures.findSunnyBackgroundTexture(Properties.DEFAULT_CLOUD_TEXTURE), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            if (!increase) {
                Counter.decreaseSunnyCount();
            }
        }
    }
}
