package com.liquidice.acidrain.screens.unlockables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.liquidice.acidrain.managers.GameplayManager;
import com.liquidice.acidrain.managers.PropManager;
import com.liquidice.acidrain.utilities.SpriteUtil;

/**
 * Render a screen telling the user they have unlocked a powerup
 */
public class UnlockedScreen {
    private int count;
    private int bounceY;
    private boolean desc;
    private SpriteBatch batch;
    private AssetManager manager;
    private BitmapFont holdFont;
    private BitmapFont powerupFont;
    private BitmapFont powerupTypeFont;
    private BitmapFont unlockedItemFont;
    private GlyphLayout powerupTypeLayout = new GlyphLayout();
    private GlyphLayout holdLayout = new GlyphLayout();
    private GlyphLayout powerupLayout = new GlyphLayout();
    private GlyphLayout unlockedItemLayout = new GlyphLayout();

    /**
     * Create a new UnlockedScreen for the specific powerup that has been unlocked
     * @param manager   The AssetLoader Manager containing fonts and textures used in this screen
     */
    public UnlockedScreen(AssetManager manager) {
        this.manager = manager;
        batch = new SpriteBatch();
        setFonts();
        unlockedItemLayout.setText(unlockedItemFont, PropManager.ITEM_UNLOCKED_TEXT);
        bounceY = Gdx.graphics.getHeight() - PropManager.UNLOCKED_ITEM_ANIMATION_HEIGHT;
    }

    /**
     * Display the Unlocked Item screen
     * @param image The powerup image
     * @param title The popwerup title
     * @param power The powerup power
     */
    public void display(Texture image, String title, String power) {
        holdLayout.setText(holdFont, PropManager.UNLOCKED_HOLD_TEXT + GameplayManager.getLevel());

        batch.begin();

        //Draw background
        batch.draw(
                manager.get(PropManager.TEXTURE_SCREEN_UNLOCKED, Texture.class),
                0,
                0,
                Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());

        //Draw PowerupManager Unlocked header
        unlockedItemFont.draw(
                batch,
                PropManager.ITEM_UNLOCKED_TEXT,
                SpriteUtil.middleOf(Gdx.graphics.getWidth()) - SpriteUtil.middleOf(unlockedItemLayout.width),
                Gdx.graphics.getHeight() - PropManager.UNLOCKED_ITEM_HEADER_HEIGHT);

        //Set powerup information
        powerupTypeLayout.setText(powerupTypeFont, title);
        powerupLayout.setText(powerupFont, power);

        //Draw PowerupManager Title
        powerupTypeFont.draw(
                batch,
                title,
                SpriteUtil.middleOf(Gdx.graphics.getWidth()) - SpriteUtil.middleOf(powerupTypeLayout.width),
                Gdx.graphics.getHeight() - PropManager.UNLOCKED_ITEM_TITLE_HEIGHT);

        //Draw PowerupManager power
        powerupFont.draw(
                batch,
                power,
                SpriteUtil.middleOf(Gdx.graphics.getWidth()) - SpriteUtil.middleOf(powerupLayout.width),
                Gdx.graphics.getHeight() - PropManager.UNLOCKED_ITEM_POWER_HEIGHT);

        //Draw "Hold to Continue"
        holdFont.draw(
                batch,
                PropManager.UNLOCKED_HOLD_TEXT + GameplayManager.getLevel(),
                SpriteUtil.middleOf(Gdx.graphics.getWidth()) - SpriteUtil.middleOf(holdLayout.width),
                Gdx.graphics.getHeight() - PropManager.UNLOCKED_ITEM_HOLD_HEIGHT);

        animatePowerup();

        //Draw Bouncing PowerupManager Image
        batch.draw(
                image,
                (SpriteUtil.middleOf(Gdx.graphics.getWidth())) - (SpriteUtil.middleOf((image.getWidth() * PropManager.UNLOCKED_ITEM_SIZE_MULTIPLIER))),
                bounceY,
                image.getWidth() * PropManager.UNLOCKED_ITEM_SIZE_MULTIPLIER,
                image.getHeight() * PropManager.UNLOCKED_ITEM_SIZE_MULTIPLIER);

        batch.end();
    }

    /**
     * Set fonts and font colors
     */
    private void setFonts() {
        unlockedItemFont = new BitmapFont(Gdx.files.internal(PropManager.FONT_PLAY_100), Gdx.files.internal(PropManager.FONT_PLAY_100_PNG), false);
        powerupTypeFont = new BitmapFont(Gdx.files.internal(PropManager.FONT_PLAY_82), Gdx.files.internal(PropManager.FONT_PLAY_82_PNG), false);
        powerupFont = new BitmapFont(Gdx.files.internal(PropManager.FONT_PLAY_56), Gdx.files.internal(PropManager.FONT_PLAY_56_PNG), false);
        holdFont = new BitmapFont(Gdx.files.internal(PropManager.FONT_PLAY_56), Gdx.files.internal(PropManager.FONT_PLAY_56_PNG), false);
        unlockedItemFont.setColor(Color.GOLD);
        powerupTypeFont.setColor(Color.GOLD);
        powerupFont.setColor(Color.BLACK);
        holdFont.setColor(Color.BROWN);
    }

    /**
     * Animate the PowerupManager image by bouncing up and down
     */
    private void animatePowerup() {
        //Bounce Up
        if (!desc) {
            if (count < PropManager.POWERUP_ANIMATION_TIME){
                count++;
                bounceY += PropManager.UNLOCKED_ITEM_BOUNCE;
            } else {
                desc = true;
            }
        }
        //Bounce Down
        else {
            if (count > 0) {
                count --;
                bounceY -= PropManager.UNLOCKED_ITEM_BOUNCE;
            } else {
                desc = false;
            }
        }
    }
}
