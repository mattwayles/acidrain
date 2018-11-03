package com.liquidice.acidrain.managers;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.liquidice.acidrain.sprites.Bucket;

/**
 * Custom Input Manager to execute operations on user input
 */
public class GestureManager implements GestureDetector.GestureListener {
    /**
     * When a user 'pans' (runs finger across screen), follow with the Bucket
     */
    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        if (!GameplayManager.isPaused()) {
            Bucket.setX(x - Bucket.getImage().getWidth() / 2);
        }
        return false;
    }

    //These methods are not needed, yet
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) { return true; }
    @Override
    public boolean tap(float x, float y, int count, int button) { return false; }
    @Override
    public boolean longPress(float x, float y) { return false; }
    @Override
    public boolean fling(float velocityX, float velocityY, int button) { return false; }
    @Override
    public boolean panStop(float x, float y, int pointer, int button) { return false; }
    @Override
    public boolean zoom(float initialDistance, float distance) { return false; }
    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) { return false; }
    @Override
    public void pinchStop() { }
}
