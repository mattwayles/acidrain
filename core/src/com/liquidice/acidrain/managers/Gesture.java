package com.liquidice.acidrain.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.liquidice.acidrain.AcidRain;
import com.liquidice.acidrain.sprites.Bucket;

public class Gesture implements GestureDetector.GestureListener {
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) { return false; }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        if (x > Bucket.getImage().getWidth() && x < Gdx.graphics.getWidth() - Bucket.getImage().getWidth()) {
            Bucket.setX(x - Bucket.getImage().getWidth() / 2);
        }
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {

        //TODO: Remove reset
        Gdx.app.log("DEBUG: ", "Resetting Prefs");
        AcidRain.getPreferences().clear();
        AcidRain.getPreferences().flush();
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}