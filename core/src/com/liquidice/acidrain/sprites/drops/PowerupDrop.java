package com.liquidice.acidrain.sprites.drops;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.liquidice.acidrain.managers.Powerup;
import com.liquidice.acidrain.managers.Score;
import com.liquidice.acidrain.managers.assets.Textures;

public class PowerupDrop extends Drop {
    private static Texture healthPack = Textures.healthPack;
    private int type;
    public PowerupDrop(int powerup, float x, int y) {
        Texture image;
        switch (powerup) {
            case 0:
                type = 0;
                image = Textures.healthPack;
                break;
            case 1:
                type = 1;
                image = Textures.umbrella;
                break;
            default:
                type = 0;
                image = healthPack;
        }

        super.setX(x);
        super.setY(y);
        super.setPoints(20);
        super.setSpeed(10);
        super.setImage(image);
        super.setSplash(Textures.rainSplash);
        super.setLeftSplash(Textures.rainSplashLeft);
        super.setRightSplash(Textures.rainSplashRight);
        super.setRect(new Rectangle(x, y, image.getWidth(), image.getHeight()));
    }

    public void executePowerup() {
        switch (type) {
            case 0:
                double twentyFivePercent = Score.getLoseScore() * .25;
                if (Score.getStrengthScore() + twentyFivePercent < Score.getLoseScore()) {
                    Score.setStrengthScore(Score.getStrengthScore() + (int) twentyFivePercent);
                } else {
                    Score.setStrengthScore(Score.getLoseScore());
                }
                break;
            case 1:
                Powerup.activateUmbrella();
                break;
        }
    }

}
