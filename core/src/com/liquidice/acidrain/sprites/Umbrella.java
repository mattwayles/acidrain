package com.liquidice.acidrain.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.liquidice.acidrain.managers.assets.Textures;

public class Umbrella {
    private static Texture leftImage = Textures.umbrellaLeft;
    private static Texture rightImage = Textures.umbrellaRight;
    private static float leftX = Bucket.getX() - Textures.umbrellaLeft.getWidth() + 20;
    private static float rightX = Bucket.getX() + Bucket.getImage().getWidth() - 20;

    private static Rectangle leftRect = new Rectangle(leftX, Bucket.getBucketHover() + leftImage.getHeight() / 2, leftImage.getWidth(), 10);
    private static Rectangle rightRect = new Rectangle(rightX, Bucket.getBucketHover() + rightImage.getHeight() / 2, rightImage.getWidth(), 10);

    public static void draw(Batch batch) {
        leftX = Bucket.getX() - Textures.umbrellaLeft.getWidth() + 20;
        rightX = Bucket.getX() + Bucket.getImage().getWidth() - 20;

        leftRect.setX(leftX);
        rightRect.setX(rightX);

        batch.draw(Textures.umbrellaLeft, leftX, Bucket.getBucketHover(), leftImage.getWidth(), leftImage.getHeight());
        batch.draw(Textures.umbrellaRight, rightX, Bucket.getBucketHover(), rightImage.getWidth(), rightImage.getHeight());

    }

    public static Rectangle getLeftRect() { return leftRect; }
    public static Rectangle getRightRect() { return rightRect; }

}
