package com.liquidice.acidrain.sprites.drops;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.liquidice.acidrain.managers.CounterMgr;
import com.liquidice.acidrain.managers.PowerupMgr;
import com.liquidice.acidrain.managers.PropertiesMgr;
import com.liquidice.acidrain.managers.ScoreMgr;

/**
 * Render a new Powerup drop
 */
public class PowerupDrop extends Drop {
    private int type;
    private int size;

    /**
     * Create a powerup without a size associated with it (e.g every powerup except multiplier)
     * @param manager   An AssetManager containing the textures required by this drop
     * @param powerup   # representing the powerup type
     * @param x The X position of the powerup drop
     * @param y The Y position of the powerup drop
     */
    public PowerupDrop(AssetManager manager, int powerup, float x, int y) {
        this(manager, powerup, x, y, 0);
    }

    /**
     * Create a powerup with a size associated with it
     * @param manager   An AssetManager containing the textures required by this drop
     * @param powerup   # representing the powerup type
     * @param x The X position of the powerup drop
     * @param y The Y position of the powerup drop
     * @param size The multiplier drop to render
     */
    public PowerupDrop(AssetManager manager, int powerup, float x, int y, int size) {
        Texture image;

        //Render a different Texture based on the powerup type
        switch (powerup) {
            case 0: //Multipliers
                type = 0;
                image = manager.get("unlockables/powerDrop/powerDrop" + size + ".png", Texture.class);
                this.size = size;
                break;
            case 1: //Health Pack
                type = 1;
                image = manager.get("unlockables/healthPack/healthPackDrop.png", Texture.class);
                break;
            case 2: //Umbrella
                type = 2;
                image = manager.get("unlockables/umbrella/umbrellaDrop.png", Texture.class);
                break;
            default:
                type = 0;
                image = manager.get("unlockables/powerDrop/powerDrop" + size + ".png", Texture.class);
        }

        //Set Drop properties
        super.setX(x);
        super.setY(y);
        super.setPoints(20);
        super.setSpeed(10);
        super.setImage(image);
        super.setSplash(manager.get("rain/splash/rainSplash.png", Texture.class));
        super.setLeftSplash(manager.get("rain/splash/rainSplashLeft.png", Texture.class));
        super.setRightSplash(manager.get("rain/splash/rainSplashRight.png", Texture.class));
        super.setRect(new Rectangle(x, y, image.getWidth(), image.getHeight()));
    }

    /**
     * Execute the Power associated with this Powerup drop if it's caught in the bucket
     */
    public void executePowerup() {
        //Execute a different power based on powerup type
        switch (type) {
            case 0: //Multipliers - Add drop.size * multiplier to score
                ScoreMgr.increaseCaughtScore(size * PropertiesMgr.UNLOCKABLE_SCORE_MULTIPLIER);
                break;
            case 1: // HealthPack - Add healthpack_multiplier to city strength percentage
                double strengthRestorePercent = ScoreMgr.getLoseScore() * PropertiesMgr.UNLOCKABLE_HEALTHPACK_MULTIPLIER;
                if (ScoreMgr.getStrengthScore() + strengthRestorePercent < ScoreMgr.getLoseScore()) {
                    ScoreMgr.setStrengthScore(ScoreMgr.getStrengthScore() + (int) strengthRestorePercent);
                } else {
                    ScoreMgr.setStrengthScore(ScoreMgr.getLoseScore());
                }
                break;
            case 2: //Umbrella - Activate umbrella and draw around bucket
                CounterMgr.resetUmbrellaCount();
                PowerupMgr.activateUmbrella();
                break;
        }
    }

}
