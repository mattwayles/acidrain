package com.liquidice.acidrain.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.liquidice.acidrain.managers.GameplayManager;
import com.liquidice.acidrain.managers.PropManager;
import com.liquidice.acidrain.managers.ScoreManager;
import com.liquidice.acidrain.sprites.Bucket;
import com.liquidice.acidrain.utilities.SpriteUtil;

/**
 * Render a Game Over screen when the City Strength is reduced to zero
 */
public class GameOverScreen {
    private AssetManager manager;
    private BitmapFont gameOverFont;
    private GlyphLayout gameOverLayout = new GlyphLayout();

    /**
     * Create a new Game Over screen
     * @param manager   An AssetLoader Manager containing the fonts and textures used by this screen
     */
    public GameOverScreen(AssetManager manager) {
        this.manager = manager;
        gameOverFont = new BitmapFont(Gdx.files.internal(PropManager.FONT_PLAY_56),
                Gdx.files.internal(PropManager.FONT_PLAY_56_PNG), false);
        gameOverLayout.setText(gameOverFont, PropManager.GAME_OVER_TEXT);
    }

    /**
     * Display the Game Over screen
     * @param batch The sprite batch to use for displaying the screen
     */
    public void display(Batch batch) {
        //Reset Bucket image
        Bucket.setImage(manager.get(PropManager.TEXTURE_BUCKET_0,Texture.class));

        //Retrieve the Game Over image
        Texture gameOverImage = manager.get(PropManager.TEXTURE_TEXT_GAME_OVER, Texture.class);

        //Draw the Game Over image
        batch.draw(
                gameOverImage,
                SpriteUtil.middleOf(Gdx.graphics.getWidth()) - SpriteUtil.middleOf(gameOverImage.getWidth()),
                SpriteUtil.middleOf(Gdx.graphics.getHeight()) - SpriteUtil.middleOf(gameOverImage.getHeight()) + PropManager.NORTH_OF_CENTER);

        //Draw the "Touch Anywhere" font
        gameOverFont.draw(
                batch,
                PropManager.GAME_OVER_TEXT,
                SpriteUtil.middleOf(Gdx.graphics.getWidth()) - SpriteUtil.middleOf(gameOverLayout.width),
                SpriteUtil.middleOf(Gdx.graphics.getHeight()) - SpriteUtil.middleOf(gameOverImage.getHeight()));

        //Update Level Best, if applicable
        if (ScoreManager.getCaughtPercentage() > GameplayManager.getLevelBest()) {
            GameplayManager.setLevelBest(ScoreManager.getCaughtPercentage());
        }
    }
}
