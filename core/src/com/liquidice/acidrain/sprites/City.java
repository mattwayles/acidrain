package com.liquidice.acidrain.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.liquidice.acidrain.managers.assets.Textures;

public class City {
    private static Texture image;

    public static Texture getImage() { return image; }
    public static void setImage(Texture img) { image = img; }

    public static void draw(Batch batch) {
        batch.draw(City.getImage(), 0, 0, Gdx.graphics.getWidth(), Bucket.getBucketHover() - 20);
    }

}
