package com.liquidice.acidrain.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.liquidice.acidrain.AcidRain;
import com.liquidice.acidrain.managers.CounterMgr;
import com.liquidice.acidrain.managers.PropertiesMgr;
import com.liquidice.acidrain.managers.ScoreMgr;
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
     * Create a new GameplayMgr Overlay
     * @param manager   The AssetMgr containing fonts and textures used in this overlay
     */
    public GameplayOverlay(AssetManager manager) {
        this.manager = manager;
        caughtLabelFont = manager.get("blue56.ttf", BitmapFont.class);
        caughtScoreFont = manager.get("caughtScore.ttf", BitmapFont.class);
        strengthLabelFont = manager.get("red56.ttf", BitmapFont.class); 
        strengthScoreFont = manager.get("strengthScore.ttf", BitmapFont.class);

        gameplayButtonOverlay = new GameplayButtonOverlay(manager);
    }

    /**
     * Display the clouds and scores in real-time
     * @param batch The sprite batch to display the overlay on top of
     */
    public void display(Batch batch) {

        //Set layouts with latest data
        caughtLabelLayout.setText(caughtLabelFont, PropertiesMgr.CLEAN_WATER_TEXT);
        caughtScoreLayout.setText(caughtScoreFont, PropertiesMgr.CLEAN_WATER_PERCENT);
        strengthLabelLayout.setText(strengthLabelFont, PropertiesMgr.CITY_STRENGTH_TEXT);
        strengthScoreLayout.setText(strengthScoreFont, PropertiesMgr.CITY_STRENGTH_PERCENT);

        //Draw clouds
        batch.draw(
                Clouds.getImage(),
                Clouds.getX(),
                Clouds.getY(),
                Gdx.graphics.getWidth() + PropertiesMgr.CLOUD_EXTRA_WIDTH,
                Clouds.getImage().getHeight());

        //Draw ScoreMgr if not on LevelComplete/GameOver screens
        if (CounterMgr.getSunnyCount() == 0 && AcidRain.getGameState() != 2) {
            //Draw "Clean Water" label
            caughtLabelFont.draw(
                    batch,
                    PropertiesMgr.CLEAN_WATER_TEXT,
                    PropertiesMgr.CAUGHT_SCORE_X,
                    Gdx.graphics.getHeight() - PropertiesMgr.LABEL_Y);

            //Draw current clean water percentage
            caughtScoreFont.draw(
                    batch,
                    PropertiesMgr.CLEAN_WATER_PERCENT,
                    PropertiesMgr.CAUGHT_SCORE_X + SpriteUtil.middleOf(caughtLabelLayout.width) - SpriteUtil.middleOf(caughtScoreLayout.width),
                    Gdx.graphics.getHeight() - PropertiesMgr.SCORE_Y);

            //Draw "City Strength" label
            strengthLabelFont.draw(
                    batch,
                    PropertiesMgr.CITY_STRENGTH_TEXT,
                    PropertiesMgr.STRENGTH_SCORE_X,
                    Gdx.graphics.getHeight() - PropertiesMgr.LABEL_Y);

            //Draw current city strength percentage
            strengthScoreFont.draw(
                    batch,
                    PropertiesMgr.CITY_STRENGTH_PERCENT,
                    PropertiesMgr.STRENGTH_SCORE_X + SpriteUtil.middleOf(strengthLabelLayout.width) - SpriteUtil.middleOf(strengthScoreLayout.width),
                    Gdx.graphics.getHeight() - PropertiesMgr.SCORE_Y);

            //Draw gameplay buttons
            if (AcidRain.getGameState() != 3) {
                //Placeholder required because button stage overwrites last batch draw
                batch.draw(manager.get("placeholder.png", Texture.class), 0, 0, 0, 0);
                gameplayButtonOverlay.display();
            }
        }


        //Update Bucket & City based on current percentages
        if (checkLevelAlive(ScoreMgr.getCaughtPercentage(), ScoreMgr.getStrengthPercentage())) {
            updateBucketImage(ScoreMgr.getCaughtPercentage());
            updateCityImage(ScoreMgr.getStrengthPercentage());
        }
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
            manager.get("sounds/levelWin.mp3", Sound.class).play();
            AcidRain.setGameState(PropertiesMgr.LEVEL_COMPLETE_STATE);

            //Reset for next level
            sirenPlayed = false;
            City.setImage(manager.get("city/city10.png", Texture.class));
            Bucket.setImage(manager.get("bucket.bucket9.png", Texture.class));
        }
        else if (strength <= 0) { //Check if game lost
            sirenPlayed = false;
            AcidRain.setGameState(PropertiesMgr.GAME_OVER_STATE);
        }
        else if (strength <= PropertiesMgr.STRENGTH_WARNING_LEVEL && !sirenPlayed) { //Check if game is in 'warning mode' and sound siren (once!)
            sirenPlayed = true;
            manager.get("sounds/siren.wav", Sound.class).play();

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

            //If caught percentage = 100, level is complete
            if (percentage >= 100) {

                //Initiate level complete
                manager.get("sounds/levelWin.mp3", Sound.class).play();
                AcidRain.setGameState(PropertiesMgr.LEVEL_COMPLETE_STATE);

                //Reset for next level
                sirenPlayed = false;
                City.setImage(manager.get("city/city10.png", Texture.class));
                Bucket.setImage(manager.get("bucket.bucket9.png", Texture.class));
            } else {
                //Render a bucket texture based on the tens digit of the caught percentage
                int bucketToRender = Integer.parseInt(Integer.toString(percentage).substring(0, 1));
                Bucket.setImage(manager.get("bucket/bucket" + bucketToRender + ".png", Texture.class));
            }
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
                City.setImage(manager.get("city/city10.png", Texture.class));
            } else {
                //Render a City texture based on the tens digit of the strength percentage
                int cityToRender = Integer.parseInt(String.valueOf(ScoreMgr.getStrengthPercentage()).substring(0, 1));
                City.setImage(manager.get("city/city" + cityToRender + ".png", Texture.class));
            }
        } else {
            City.setImage(manager.get("city/city1.png", Texture.class));
        }
    }
}
