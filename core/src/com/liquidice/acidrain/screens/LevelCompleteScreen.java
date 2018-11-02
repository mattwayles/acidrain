package com.liquidice.acidrain.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.liquidice.acidrain.managers.CounterMgr;
import com.liquidice.acidrain.managers.GameplayMgr;
import com.liquidice.acidrain.managers.PowerupMgr;
import com.liquidice.acidrain.managers.PropertiesMgr;
import com.liquidice.acidrain.managers.ScoreMgr;
import com.liquidice.acidrain.screens.unlockables.UnlockedScreen;
import com.liquidice.acidrain.sprites.City;
import com.liquidice.acidrain.utilities.SpriteUtil;

/**
 * When a user completes a level, display a "Level Complete" screen. If the level was completed
 * with a perfect score, let the user know.
 *
 * If a powerup was unlocked at the completion of a particular level, display the unlock screen
 */
public class LevelCompleteScreen {

    private AssetManager manager;
    private BitmapFont nextLevelFont;
    private UnlockedScreen unlockedScreen;
    private GlyphLayout nextLevelLayout = new GlyphLayout();

    /**
     * Create a new LevelComplete screen
     * @param manager   The AssetMgr containing fonts and textures used by this screen
     */
    public LevelCompleteScreen(AssetManager manager) {
        this.manager = manager;
        unlockedScreen = new UnlockedScreen(manager);
        nextLevelFont = manager.get("white56.ttf", BitmapFont.class);
        nextLevelLayout.setText(nextLevelFont, PropertiesMgr.NEXT_LEVEL_TEXT);
    }

    /**
     * Display this Level Complete screen
     * @param batch The sprite batch to render when this screen is called
     */
    public void display(Batch batch) {
        //Asset Control
        if (manager.get("sounds/thunderstorm.mp3", Music.class).isPlaying()) {
            manager.get("sounds/thunderstorm.mp3", Music.class).pause();
            manager.get("sounds/birds.wav", Music.class).play();
            City.setImage(manager.get("city/city10.png", Texture.class));
        }

        //If a PowerupMgr has been unlocked, display that window. If not, display Level Complete window
        if (!checkForPowerupUnlock()) {

            //Deactivate any active powerups
            PowerupMgr.deactivateAllPowerups();

            //Determine if level was completed with a perfect score
            Texture levelTexture = ScoreMgr.getStrengthPercentage() < PropertiesMgr.PERFECT_SCORE ?
                    manager.get("text/levelComplete.png", Texture.class) :
                    manager.get("text/perfectLevel.png", Texture.class);

            //Draw appropriate texture
            batch.draw(
                    levelTexture,
                    SpriteUtil.middleOf(Gdx.graphics.getWidth()) - SpriteUtil.middleOf(levelTexture.getWidth()),
                    SpriteUtil.middleOf(Gdx.graphics.getHeight()) - SpriteUtil.middleOf(levelTexture.getHeight()) + PropertiesMgr.NORTH_OF_CENTER);

            //Draw "Touch for next level" text
            nextLevelFont.draw(
                    batch,
                    PropertiesMgr.NEXT_LEVEL_TEXT,
                    SpriteUtil.middleOf(Gdx.graphics.getWidth()) - SpriteUtil.middleOf(nextLevelLayout.width),
                    SpriteUtil.middleOf(Gdx.graphics.getHeight()) - SpriteUtil.middleOf(levelTexture.getHeight()));
        }
    }

    /**
     * Determine if a PowerupMgr Unlocked window should be rednered instead of the Level Complete window
     * @return  Boolean determinign what type of window should be rendered
     */
    private boolean checkForPowerupUnlock() {
        boolean powerupLevel = false;
        if (CounterMgr.getSunnyCount() == PropertiesMgr.SUNNY_COUNTER &&
                (GameplayMgr.getLevel() == PropertiesMgr.UNLOCK_1_LEVEL
                        || GameplayMgr.getLevel() == PropertiesMgr.UNLOCK_2_LEVEL
                        || GameplayMgr.getLevel() == PropertiesMgr.UNLOCK_3_LEVEL)) {

            if (GameplayMgr.getLevel() == PropertiesMgr.UNLOCK_1_LEVEL) {
                unlockedScreen.display(manager.get("unlockables/powerDrop/multipliersDrop.png", Texture.class), PropertiesMgr.POWERUP_MULTIPLIER_TITLE, PropertiesMgr.POWERUP_MULTIPLIER_DESC);
            }

            else if (GameplayMgr.getLevel() == PropertiesMgr.UNLOCK_2_LEVEL) {
                unlockedScreen.display(manager.get("unlockables/healthPack/healthPackDrop.png", Texture.class), PropertiesMgr.POWERUP_HEALTHPACK_TITLE, PropertiesMgr.POWERUP_HEALTHPACK_DESC);
            }

            else if (GameplayMgr.getLevel() == PropertiesMgr.UNLOCK_3_LEVEL) {
                unlockedScreen.display(manager.get("unlockables/umbrella/umbrellaDrop.png", Texture.class), PropertiesMgr.POWERUP_UMBRELLA_TITLE, PropertiesMgr.POWERUP_UMBRELLA_DESC);
            }
            powerupLevel = true;
        }
        return powerupLevel;
    }
}
