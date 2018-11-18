package com.liquidice.acidrain.sprites.drops;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Parent class for RainDrops, AcidDrops, and PowerupDrops
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
    private int splashCount = 0;

    /**
     * Retrieve the X position for this Drop
     * @return  The X position for this Drop
     */
    public float getX() {
        return x;
    }

    /**
     * Set the X position for this drop
     * @param x The X position for this drop
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Retrieve the Y position for this Drop
     * @return  The Y position for this Drop
     */
    public float getY() {
        return y;
    }

    /**
     * Set the Y position for this drop
     * @param y The Y position for this drop
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Retrieve the point value of this Drop
     * @return  The point value of this Drop
     */
    public int getPoints() {
        return points;
    }

    /**
     * Set the point value for this Drop
     * @param points    The point value for this Drop
     */void setPoints(int points) {
        this.points = points;
    }

    /**
     * Retrieve the current splash count of this drop
     * @return  The current splash count of this drop
     */
    public int getSplashCount() {
        return splashCount;
    }

    /**
     * Increase the splash count for this drop
     */
    public void increaseSplashCount() {
        this.splashCount++;
    }

    /**
     * Retrieve the current speed of this drop
     * @return  The current speed of this drop
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Set the speed for this drop
     * @param speed The new speed for this drop
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /**
     * Retrieve the rectangle associated with this drop for collision purposes
     * @return  The rectangle associated with this drop
     */
    public Rectangle getRect() {
        return rect;
    }

    /**
     * Set a rectangle around this drop for collision purposes
     * @param rect  The rectangle to place around this drop
     */
    void setRect(Rectangle rect) {
        this.rect = rect;
    }

    /**
     * Retrieve the image associated with this drop
     * @return   The image associated with thie drop
     */
    public Texture getImage() {
        return image;
    }

    /**
     * Set this image for this drop
     * @param image The new image for this drop
     */
    public void setImage(Texture image) {
        this.image = image;
    }

    /**
     * Retrieve the splash image for this drop
     * @return  The splash image for this drop
     */
    public Texture getSplash() {
        return splashImage;
    }

    void setSplash(Texture splashImage) { this.splashImage = splashImage; }

    /**
     * Retrieve the left splash image for this drop
     * @return  The left splash image for this drop
     */
    public Texture getLeftSplash() { return leftSplashImage; }

    /**
     * Set the left splash image for this drop
     * @param splashImage   The new left splash image for this drop
     */
    void setLeftSplash(Texture splashImage) { this.leftSplashImage = splashImage; }


    /**
     * Retrieve the right splash image for this drop
     * @return  The right splash image for this drop
     */
    public Texture getRightSplash() { return rightSplashImage; }

    /**
     * Set the right splash image for this drop
     * @param splashImage   The new right splash image for this drop
     */
    void setRightSplash(Texture splashImage) { this.rightSplashImage = splashImage; }
}
