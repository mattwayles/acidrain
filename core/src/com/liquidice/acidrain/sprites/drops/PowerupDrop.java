package com.liquidice.acidrain.sprites.drops;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.liquidice.acidrain.managers.CountManager;
import com.liquidice.acidrain.managers.GameplayManager;
import com.liquidice.acidrain.managers.PowerupManager;
import com.liquidice.acidrain.managers.PropManager;
import com.liquidice.acidrain.managers.ScoreManager;
import com.liquidice.acidrain.screens.GameplayScreen;

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
                image = manager.get(PropManager.POWER_DROP_PREFIX + size + PropManager.PNG, Texture.class);
                this.size = size;
                break;
            case 1: //Health Pack
                type = 1;
                image = manager.get(PropManager.TEXTURE_HEALTHPACK_DROP, Texture.class);
                break;
            case 2: //Umbrella
                type = 2;
                image = manager.get(PropManager.TEXTURE_UMBRELLA_DROP, Texture.class);
                break;
            case 3: //Shield
                type = 3;
                image = manager.get(PropManager.TEXTURE_SHIELD_DROP, Texture.class);
                break;
            case 4: //Filtration
                type = 4;
                image = manager.get(PropManager.TEXTURE_FILTRATION_DROP, Texture.class);
                break;
            case 5: //Teamwork
                type = 5;
                image = manager.get(PropManager.TEXTURE_TEAMWORK_DROP, Texture.class);
                break;
            default:
                type = 0;
                image = manager.get(PropManager.POWER_DROP_PREFIX + size + PropManager.PNG, Texture.class);
        }

        //Set Drop properties
        super.setX(x);
        super.setY(y);
        super.setPoints(0);
        super.setSpeed(GameplayManager.getMaxSpeed() * 1.8f);
        super.setImage(image);
        super.setSplash(manager.get(PropManager.TEXTURE_RAIN_SPLASH, Texture.class));
        super.setLeftSplash(manager.get(PropManager.TEXTURE_RAIN_SPLASH_LEFT, Texture.class));
        super.setRightSplash(manager.get(PropManager.TEXTURE_RAIN_SPLASH_RIGHT, Texture.class));
        super.setRect(new Rectangle(x, y, image.getWidth(), image.getHeight()));
    }

    /**
     * Execute the Power associated with this Powerup drop if it's caught in the bucket
     */
    public void executePowerup() {
        //Execute a different power based on powerup type
        switch (type) {
            case 0: //Multipliers - Add drop.size * multiplier to score
                ScoreManager.increaseCaughtScore(size * PropManager.UNLOCKABLE_SCORE_MULTIPLIER);
                break;
            case 1: // HealthPack - Add healthpack_multiplier to city strength percentage
                double strengthRestorePercent = ScoreManager.getLoseScore() * PropManager.UNLOCKABLE_HEALTHPACK_MULTIPLIER;
                if (ScoreManager.getStrengthScore() + strengthRestorePercent < ScoreManager.getLoseScore()) {
                    ScoreManager.setStrengthScore(ScoreManager.getStrengthScore() + (int) strengthRestorePercent);
                } else {
                    ScoreManager.setStrengthScore(ScoreManager.getLoseScore());
                }
                break;
            case 2: //Umbrella - Activate umbrella and draw around bucket
                CountManager.resetUmbrellaCount();
                PowerupManager.activateUmbrella();
                break;
            case 3: //Shield - Activate shield and draw under bucket
                CountManager.resetShieldCount();
                PowerupManager.activateShield();
                break;
            case 4: //Filtration: Turn all AcidDrops to RainDrops
                CountManager.resetFilterCount();
                PowerupManager.activateFilter();
                break;
            case 5: //Teamwork
                CountManager.resetTeamworkCount();
                PowerupManager.activateTeamwork();
                break;
        }
    }

}
