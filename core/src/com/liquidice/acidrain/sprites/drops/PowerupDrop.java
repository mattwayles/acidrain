package com.liquidice.acidrain.sprites.drops;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.liquidice.acidrain.managers.Counter;
import com.liquidice.acidrain.managers.Powerup;
import com.liquidice.acidrain.managers.Score;
import com.liquidice.acidrain.managers.assets.Textures;

public class PowerupDrop extends Drop {
    private static Texture healthPack = Textures.healthPack;
    private int type;
    private int size;

    public PowerupDrop(int powerup, float x, int y) {
        this(powerup, x, y, 0);
    }

    public PowerupDrop(int powerup, float x, int y, int size) {
        Texture image;
        switch (powerup) {
            case 0: //Multipliers
                type = 0;
                image = Textures.findPowerDropTexture(size);
                this.size = size;
                break;
            case 1: //Health Pack
                type = 1;
                image = Textures.healthPack;
                break;
            case 2: //Umbrella
                type = 2;
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
            case 0: //Multipliers
                Score.increaseCaughtScore(size * 10);
                break;
            case 1: // HealthPack
                double twentyFivePercent = Score.getLoseScore() * .25;
                if (Score.getStrengthScore() + twentyFivePercent < Score.getLoseScore()) {
                    Score.setStrengthScore(Score.getStrengthScore() + (int) twentyFivePercent);
                } else {
                    Score.setStrengthScore(Score.getLoseScore());
                }
                break;
            case 2: //Umbrella
                Counter.resetUmbrellaCount();
                Powerup.activateUmbrella();
                break;
        }
    }

}
