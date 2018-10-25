package com.liquidice.acidrain.sprites.drops;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Parent class for RainDrops, AcidDrops, and (future) SpecialDrops
 */
public class Drop {
    private float x;
    private float y;
    private int points;
    private float speed;
    private Rectangle rect;
    private Texture image;
    private Texture splashImage;
    private Texture leftSplashImage;
    private Texture rightSplashImage;

    public void moveY(int yLoc) { this.y = yLoc; }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }


    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }


    public Texture getImage() {
        return image;
    }

    public void setImage(Texture image) {
        this.image = image;
    }

    public Texture getSplash() {
        return splashImage;
    }

    public Texture getLeftSplash() { return leftSplashImage; }

    public Texture getRightSplash() { return rightSplashImage; }

    public void setSplash(Texture splashImage) { this.splashImage = splashImage; }

    public void setLeftSplash(Texture splashImage) { this.leftSplashImage = splashImage; }

    public void setRightSplash(Texture splashImage) { this.rightSplashImage = splashImage; }
}
