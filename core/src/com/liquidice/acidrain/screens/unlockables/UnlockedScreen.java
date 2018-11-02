package com.liquidice.acidrain.screens.unlockables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.liquidice.acidrain.managers.PropertiesMgr;
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
     * @param manager   The AssetMgr Manager containing fonts and textures used in this screen
     */
    public UnlockedScreen(AssetManager manager) {
        this.manager = manager;
        batch = new SpriteBatch();
        holdFont = manager.get("white56.ttf", BitmapFont.class);
        powerupFont = manager.get("powerup.ttf", BitmapFont.class);
        powerupTypeFont = manager.get("gold56.ttf", BitmapFont.class);
        unlockedItemFont = manager.get("unlockables.ttf", BitmapFont.class);

        holdLayout.setText(holdFont, PropertiesMgr.UNLOCKED_HOLD_TEXT);
        unlockedItemLayout.setText(unlockedItemFont, PropertiesMgr.ITEM_UNLOCKED_TEXT);
        bounceY = Gdx.graphics.getHeight() - PropertiesMgr.UNLOCKED_ITEM_ANIMATION_HEIGHT;
    }

    /**
     * Display the Unlocked Item screen
     * @param image The powerup image
     * @param title The popwerup title
     * @param power The powerup power
     */
    public void display(Texture image, String title, String power) {
        batch.begin();

        //Draw background
        batch.draw(
                manager.get("screen/unlocked.jpg", Texture.class),
                0,
                0,
                Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());

        //Draw PowerupMgr Unlocked header
        unlockedItemFont.draw(
                batch,
                PropertiesMgr.ITEM_UNLOCKED_TEXT,
                SpriteUtil.middleOf(Gdx.graphics.getWidth()) - SpriteUtil.middleOf(unlockedItemLayout.width),
                Gdx.graphics.getHeight() - PropertiesMgr.UNLOCKED_ITEM_HEADER_HEIGHT);

        //Set powerup information
        powerupTypeLayout.setText(powerupTypeFont, title);
        powerupLayout.setText(powerupFont, power);

        //Draw PowerupMgr Title
        powerupTypeFont.draw(
                batch,
                title,
                SpriteUtil.middleOf(Gdx.graphics.getWidth()) - SpriteUtil.middleOf(powerupTypeLayout.width),
                Gdx.graphics.getHeight() - PropertiesMgr.UNLOCKED_ITEM_TITLE_HEIGHT);

        //Draw PowerupMgr power
        powerupFont.draw(
                batch,
                power,
                SpriteUtil.middleOf(Gdx.graphics.getWidth()) - SpriteUtil.middleOf(powerupLayout.width),
                Gdx.graphics.getHeight() - PropertiesMgr.UNLOCKED_ITEM_POWER_HEIGHT);

        //Draw "Hold to Continue"
        holdFont.draw(
                batch,
                PropertiesMgr.UNLOCKED_HOLD_TEXT,
                SpriteUtil.middleOf(Gdx.graphics.getWidth()) - SpriteUtil.middleOf(holdLayout.width),
                Gdx.graphics.getHeight() - PropertiesMgr.UNLOCKED_ITEM_HOLD_HEIGHT);

        animatePowerup();

        //Draw Bouncing PowerupMgr Image
        batch.draw(
                image,
                (SpriteUtil.middleOf(Gdx.graphics.getWidth())) - (SpriteUtil.middleOf((image.getWidth() * PropertiesMgr.UNLOCKED_ITEM_SIZE_MULTIPLIER))),
                bounceY,
                image.getWidth() * PropertiesMgr.UNLOCKED_ITEM_SIZE_MULTIPLIER,
                image.getHeight() * PropertiesMgr.UNLOCKED_ITEM_SIZE_MULTIPLIER);

        batch.end();
    }

    /**
     * Animate the PowerupMgr image by bouncing up and down
     */
    private void animatePowerup() {
        //Bounce Up
        if (!desc) {
            if (count < 10){
                count++;
                bounceY += PropertiesMgr.UNLOCKED_ITEM_BOUNCE;
            } else {
                desc = true;
            }
        }
        //Bounce Down
        else {
            if (count > 0) {
                count --;
                bounceY -= PropertiesMgr.UNLOCKED_ITEM_BOUNCE;
            } else {
                desc = false;
            }
        }
    }
}
