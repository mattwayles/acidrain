package com.liquidice.acidrain.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.liquidice.acidrain.managers.Counter;
import com.liquidice.acidrain.managers.Powerup;
import com.liquidice.acidrain.managers.Properties;
import com.liquidice.acidrain.managers.assets.Textures;

public class Bucket {
    private static final int BUCKET_SPEED = 40;
    private static final int BUCKET_HOVER = 200;

    private static Texture image = Textures.rainBucket0;
    private static float x = Gdx.graphics.getWidth() / 2 - image.getWidth() / 2;
    private static Rectangle topRect = new Rectangle(x, BUCKET_HOVER + image.getHeight(), image.getWidth(), 1);
    private static Rectangle leftRect = new Rectangle(x, BUCKET_HOVER, 1, image.getHeight());
    private static Rectangle rightRect= new Rectangle(x + image.getWidth(), BUCKET_HOVER, 1, image.getHeight());

    public static float getX() {
        return x;
    }

    public static void setX(float bucketX) {
        x = bucketX;
        leftRect.set(x, BUCKET_HOVER, 10, image.getHeight() - 2);
        rightRect.set(x + image.getWidth() - 10, BUCKET_HOVER, 10, image.getHeight() - 2);
        topRect.set(x + 2, BUCKET_HOVER + image.getHeight()- 5, image.getWidth() - 2, 1);
    }

    public static Texture getImage() {
        return image;
    }

    public static void setImage(Texture bucketImg) {
        image = bucketImg;
    }

    public static Rectangle getTopRect() {
        return topRect;
    }


    public static Rectangle getLeftRect() {
        return leftRect;
    }

    public static Rectangle getRightRect() {
        return rightRect;
    }

    public static int getBucketSpeed() {
        return BUCKET_SPEED;
    }

    public static int getBucketHover() {
        return BUCKET_HOVER;
    }

    public static void draw(Batch batch) {
        batch.draw(image, x, BUCKET_HOVER, image.getWidth(), image.getHeight());
        if (Powerup.isUmbrellaActive()) {
            if (Counter.getUmbrellaCount() <= Properties.UMBRELLA_ACTIVATION_TIME) {
                Umbrella.draw(batch);
                Counter.increaseUmbrellaCount();
            }
            else {
                Powerup.deactivateUmbrella();
                Counter.resetUmbrellaCount();
            }
        }
    }
}
