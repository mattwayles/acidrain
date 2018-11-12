package com.liquidice.acidrain.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.liquidice.acidrain.managers.AudioManager;
import com.liquidice.acidrain.managers.CountManager;
import com.liquidice.acidrain.managers.GameplayManager;
import com.liquidice.acidrain.managers.PowerupManager;
import com.liquidice.acidrain.managers.PropManager;
import com.liquidice.acidrain.managers.ScoreManager;
import com.liquidice.acidrain.sprites.Bucket;
import com.liquidice.acidrain.sprites.City;
import com.liquidice.acidrain.sprites.Clouds;
import com.liquidice.acidrain.utilities.SpriteUtil;

/**
 * Overlay the main gameplay screen with clouds, score, and different city/bucket images
 */
public class GameplayOverlay {
    private AssetManager manager;
    private BitmapFont caughtLabelFont;
    private BitmapFont caughtScoreFont;
    private BitmapFont strengthLabelFont;
    private BitmapFont strengthScoreFont;
    private GameplayButtonOverlay gameplayButtonOverlay;
    private GlyphLayout caughtLabelLayout = new GlyphLayout();
    private GlyphLayout caughtScoreLayout = new GlyphLayout();
    private GlyphLayout strengthLabelLayout  = new GlyphLayout();
    private GlyphLayout strengthScoreLayout  = new GlyphLayout();

    private boolean sirenPlayed;

    /**
     * Create a new GameplayManager Overlay
     * @param manager   The AssetLoader containing fonts and textures used in this overlay
     */
    public GameplayOverlay(AssetManager manager) {
        this.manager = manager;
        setFonts();
        gameplayButtonOverlay = new GameplayButtonOverlay(manager);
    }

    /**
     * Display the clouds and scores in real-time
     * @param batch The sprite batch to display the overlay on top of
     */
    public void display(Batch batch) {

        //Set layouts with latest data
        caughtLabelLayout.setText(caughtLabelFont, PropManager.CLEAN_WATER_TEXT);
        caughtScoreLayout.setText(caughtScoreFont, ScoreManager.getCaughtPercentage() + "%");
        strengthLabelLayout.setText(strengthLabelFont, PropManager.CITY_STRENGTH_TEXT);
        strengthScoreLayout.setText(strengthScoreFont, ScoreManager.getStrengthPercentage() + "%");

        //Draw clouds
        batch.draw(
                Clouds.getImage(),
                Clouds.getX(),
                Clouds.getY(),
                Gdx.graphics.getWidth() + PropManager.CLOUD_EXTRA_WIDTH,
                Clouds.getImage().getHeight());

        //Draw ScoreManager if not on LevelComplete/GameOver screens
        if (CountManager.getSunnyCount() == 0 && GameplayManager.getGameState() != PropManager.GAME_OVER_STATE) {
            //Draw "Clean Water" label
            caughtLabelFont.draw(
                    batch,
                    PropManager.CLEAN_WATER_TEXT,
                    PropManager.CAUGHT_SCORE_X,
                    Gdx.graphics.getHeight() - PropManager.LABEL_Y);

            //Draw current clean water percentage
            caughtScoreFont.draw(
                    batch,
                    ScoreManager.getCaughtPercentage() + "%",
                    PropManager.CAUGHT_SCORE_X + SpriteUtil.middleOf(caughtLabelLayout.width) - SpriteUtil.middleOf(caughtScoreLayout.width),
                    Gdx.graphics.getHeight() - PropManager.SCORE_Y);

            //Draw "City Strength" label
            strengthLabelFont.draw(
                    batch,
                    PropManager.CITY_STRENGTH_TEXT,
                    PropManager.STRENGTH_SCORE_X,
                    Gdx.graphics.getHeight() - PropManager.LABEL_Y);

            //Draw current city strength percentage
            strengthScoreFont.draw(
                    batch,
                    ScoreManager.getStrengthPercentage() + "%",
                    PropManager.STRENGTH_SCORE_X + SpriteUtil.middleOf(strengthLabelLayout.width) - SpriteUtil.middleOf(strengthScoreLayout.width),
                    Gdx.graphics.getHeight() - PropManager.SCORE_Y);

            //Draw gameplay buttons
            if (GameplayManager.getGameState() != 3) {
                //Placeholder required because button stage overwrites last batch draw
                batch.draw(manager.get(PropManager.TEXTURE_PLACEHOLDER, Texture.class), 0, 0, 0, 0);
                gameplayButtonOverlay.display();
            }
        }


        //Update Bucket & City based on current percentages
        if (checkLevelAlive(ScoreManager.getCaughtPercentage(), ScoreManager.getStrengthPercentage())) {
            updateBucketImage(ScoreManager.getCaughtPercentage());
            updateCityImage(ScoreManager.getStrengthPercentage());
        }
    }

    /**
     * Set fonts and font colors
     */
    private void setFonts() {
        caughtLabelFont = new BitmapFont(Gdx.files.internal(PropManager.FONT_PLAY_68), Gdx.files.internal(PropManager.FONT_PLAY_68_PNG), false);
        caughtScoreFont = new BitmapFont(Gdx.files.internal(PropManager.FONT_PLAY_100), Gdx.files.internal(PropManager.FONT_PLAY_100_PNG), false);
        strengthLabelFont = new BitmapFont(Gdx.files.internal(PropManager.FONT_PLAY_68), Gdx.files.internal(PropManager.FONT_PLAY_68_PNG), false);
        strengthScoreFont = new BitmapFont(Gdx.files.internal(PropManager.FONT_PLAY_100), Gdx.files.internal(PropManager.FONT_PLAY_100_PNG), false);
        caughtLabelFont.setColor(PropManager.SCORE_BLUE_COLOR);
        caughtScoreFont.setColor(PropManager.SCORE_BLUE_COLOR);
        strengthLabelFont.setColor(PropManager.SCORE_RED_COLOR);
        strengthScoreFont.setColor(PropManager.SCORE_RED_COLOR);
    }

    /**
     * Check the current score and city strength to determine if the level is complete or not
     * @param score The current clean water caught percentage
     * @param strength The current city strength percentage
     * @return  Boolean indicating whether the current level is complete
     */
    private boolean checkLevelAlive(int score, int strength) {
        boolean isAlive = false;

        if (score >= 100) { //Check if game won
            //Initiate level complete
            if (GameplayManager.getGameState() == PropManager.GAME_PLAY_STATE) {
                PowerupManager.deactivateAllPowerups();
                AudioManager.playLevelWin();
                AudioManager.stopSiren();
                GameplayManager.setGameState(PropManager.LEVEL_COMPLETE_STATE);
                GameplayManager.increaseLevel();
            }

            //Reset for next level
            sirenPlayed = false;
            City.setImage(manager.get(PropManager.TEXTURE_CITY_10, Texture.class));
            Bucket.setImage(manager.get(PropManager.TEXTURE_BUCKET_9, Texture.class));
        }
        else if (strength <= 0 && GameplayManager.getGameState() == PropManager.GAME_PLAY_STATE) { //Check if game lost
            sirenPlayed = false;
            AudioManager.stopSiren();
            AudioManager.playGameOver();
            PowerupManager.deactivateAllPowerups();
            City.setImage(manager.get(PropManager.TEXTURE_CITY_1, Texture.class));
            GameplayManager.setGameState(PropManager.GAME_OVER_STATE);
        }
        else if (strength <= PropManager.STRENGTH_WARNING_LEVEL && !sirenPlayed) { //Check if game is in 'warning mode' and sound siren (once!)
            sirenPlayed = true;
            AudioManager.playSiren();

            isAlive = true;
        }
        else { //Game still on
            isAlive = true;
        }

        return isAlive;
    }


    /**
     * Update the Bucket image based on current caught percentage.
     * Reset city if percentage is 100
     * @param percentage    The current caught percentage
     */
    private void updateBucketImage(int percentage) {
        //We use the tens digit to render images, so ignore 0-9
        if (percentage > 9) {
            //Render a bucket texture based on the tens digit of the caught percentage
            int bucketToRender = Integer.parseInt(Integer.toString(percentage).substring(0, 1));
            Bucket.setImage(manager.get(PropManager.BUCKET_PREFIX + bucketToRender +PropManager.PNG, Texture.class));
        }
    }

    /**
     * Update the City image based on current strength percentage
     * @param percentage    The current strength percentage
     */
    private void updateCityImage(int percentage) {
        //We use the tens digit to render images, so ignore 0-9
        if (percentage > 9) {
            if (percentage >= 100) {
                City.setImage(manager.get(PropManager.TEXTURE_CITY_10, Texture.class));
            } else {
                //Render a City texture based on the tens digit of the strength percentage
                int cityToRender = Integer.parseInt(String.valueOf(ScoreManager.getStrengthPercentage()).substring(0, 1));
                City.setImage(manager.get(PropManager.CITY_PREFIX + cityToRender + PropManager.PNG, Texture.class));
            }
        } else {
            City.setImage(manager.get(PropManager.TEXTURE_CITY_1, Texture.class));
        }
    }
}
