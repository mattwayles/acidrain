package com.liquidice.acidrain.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.liquidice.acidrain.managers.AudioManager;
import com.liquidice.acidrain.managers.CountManager;
import com.liquidice.acidrain.managers.GameplayManager;
import com.liquidice.acidrain.managers.PropManager;
import com.liquidice.acidrain.managers.ScoreManager;
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
     * @param manager   The AssetLoader containing fonts and textures used by this screen
     */
    public LevelCompleteScreen(AssetManager manager) {
        this.manager = manager;
        unlockedScreen = new UnlockedScreen(manager);
        nextLevelFont = new BitmapFont(Gdx.files.internal(PropManager.FONT_PLAY_56),
                Gdx.files.internal(PropManager.FONT_PLAY_56_PNG), false);
    }

    /**
     * Display this Level Complete screen
     * @param batch The sprite batch to render when this screen is called
     */
    public void display(Batch batch) {
        //Asset Control
        if (manager.get(PropManager.AUDIO_THUNDERSTORM, Music.class).isPlaying()) {
            AudioManager.stopThunderstorm();
            AudioManager.playBirds();
            City.setImage(manager.get(PropManager.TEXTURE_CITY_10, Texture.class));
        }

        nextLevelLayout.setText(nextLevelFont, PropManager.NEXT_LEVEL_TEXT + GameplayManager.getLevel());

        //If a PowerupManager has been unlocked, display that window. If not, display Level Complete window
        if (!checkForPowerupUnlock()) {

            //Determine if level was completed with a perfect score
            Texture levelTexture = ScoreManager.getStrengthPercentage() < PropManager.PERFECT_SCORE ?
                    manager.get(PropManager.TEXTURE_TEXT_LEVEL_COMPLETE, Texture.class) :
                    manager.get(PropManager.TEXTURE_TEXT_PERFECT_LEVEL, Texture.class);

            //Draw appropriate texture
            batch.draw(
                    levelTexture,
                    SpriteUtil.middleOf(Gdx.graphics.getWidth()) - SpriteUtil.middleOf(levelTexture.getWidth()),
                    SpriteUtil.middleOf(Gdx.graphics.getHeight()) - SpriteUtil.middleOf(levelTexture.getHeight()) + PropManager.NORTH_OF_CENTER);

            //Draw "Touch for next level" text
            nextLevelFont.draw(
                    batch,
                    PropManager.NEXT_LEVEL_TEXT + GameplayManager.getLevel(),
                    SpriteUtil.middleOf(Gdx.graphics.getWidth()) - SpriteUtil.middleOf(nextLevelLayout.width),
                    SpriteUtil.middleOf(Gdx.graphics.getHeight()) - SpriteUtil.middleOf(levelTexture.getHeight()));
        }
    }

    /**
     * Determine if a PowerupManager Unlocked window should be rendered instead of the Level Complete window
     * @return  Boolean determining what type of window should be rendered
     */
    private boolean checkForPowerupUnlock() {
        boolean powerupLevel = false;

        //TODO: Maybe use a loop or something rather than this ugly conditional
        if (CountManager.getSunnyCount() == PropManager.SUNNY_COUNTER &&
                (GameplayManager.getLevel() == PropManager.UNLOCK_1_LEVEL
                        || GameplayManager.getLevel() == PropManager.UNLOCK_2_LEVEL
                        || GameplayManager.getLevel() == PropManager.UNLOCK_3_LEVEL
                        || GameplayManager.getLevel() == PropManager.UNLOCK_4_LEVEL
                        || GameplayManager.getLevel() == PropManager.UNLOCK_5_LEVEL
                        || GameplayManager.getLevel() == PropManager.UNLOCK_6_LEVEL
                )) {

            if (GameplayManager.getLevel() == PropManager.UNLOCK_1_LEVEL) {
                unlockedScreen.display(manager.get(PropManager.TEXTURE_MULTIPLIER_DROP, Texture.class), PropManager.POWERUP_MULTIPLIER_TITLE, PropManager.POWERUP_MULTIPLIER_DESC);
            }

            else if (GameplayManager.getLevel() == PropManager.UNLOCK_2_LEVEL) {
                unlockedScreen.display(manager.get(PropManager.TEXTURE_HEALTHPACK_DROP, Texture.class), PropManager.POWERUP_HEALTHPACK_TITLE, PropManager.POWERUP_HEALTHPACK_DESC);
            }

            else if (GameplayManager.getLevel() == PropManager.UNLOCK_3_LEVEL) {
                unlockedScreen.display(manager.get(PropManager.TEXTURE_UMBRELLA_DROP, Texture.class), PropManager.POWERUP_UMBRELLA_TITLE, PropManager.POWERUP_UMBRELLA_DESC);
            }
            else if (GameplayManager.getLevel() == PropManager.UNLOCK_4_LEVEL) {
                unlockedScreen.display(manager.get(PropManager.TEXTURE_SHIELD_DROP, Texture.class), PropManager.POWERUP_SHIELD_TITLE, PropManager.POWERUP_SHIELD_DESC);
            }
            else if (GameplayManager.getLevel() == PropManager.UNLOCK_5_LEVEL) {
                unlockedScreen.display(manager.get(PropManager.TEXTURE_FILTRATION_DROP, Texture.class), PropManager.POWERUP_FILTRATION_TITLE, PropManager.POWERUP_FILTRATION_DESC);
            }
            else if (GameplayManager.getLevel() == PropManager.UNLOCK_6_LEVEL) {
                unlockedScreen.display(manager.get(PropManager.TEXTURE_TEAMWORK_DROP, Texture.class), PropManager.POWERUP_TEAMWORK_TITLE, PropManager.POWERUP_TEAMWORK_DESC, PropManager.POWERUP_TEAMWORK_DESC2);
            }
            powerupLevel = true;
        }
        return powerupLevel;
    }
}