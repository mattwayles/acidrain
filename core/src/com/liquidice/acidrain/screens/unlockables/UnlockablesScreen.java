package com.liquidice.acidrain.screens.unlockables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.liquidice.acidrain.managers.GameplayManager;
import com.liquidice.acidrain.managers.PropManager;
import com.liquidice.acidrain.screens.StartScreen;
import com.liquidice.acidrain.utilities.SpriteUtil;

/**
 * Render a list of App Unlockables
 */
public class UnlockablesScreen {

    private Stage stage = new Stage();
    private AssetManager manager;
    private StartScreen parent;
    private ImageButton closeButton;
    private BitmapFont unlockedItemFont;
    private GlyphLayout unlockedItemLayout = new GlyphLayout();

    /**
     * Initialize the screen with text, close button, and parent handlers
     * @param manager   The manager containing the assets to use in this screen
     */
    public UnlockablesScreen(AssetManager manager, StartScreen parent) {
        //Text
        unlockedItemFont = new BitmapFont(Gdx.files.internal(PropManager.FONT_PLAY_100),
                Gdx.files.internal(PropManager.FONT_PLAY_100_PNG), false);
        unlockedItemFont.setColor(Color.GOLD);
        unlockedItemLayout.setText(unlockedItemFont, PropManager.UNLOCKED_ITEMS_TEXT);

        //Close Button
        ImageButton.ImageButtonStyle closeButtonStyle = new ImageButton.ImageButtonStyle();
        closeButtonStyle.up = new TextureRegionDrawable(new TextureRegion(manager.get(PropManager.BUTTON_CLOSE, Texture.class)));
        closeButton = new ImageButton(closeButtonStyle);

        //Parent
        this.manager = manager;
        this.parent = parent;
    }

    /**
     * Render the list of unlockable/unlocked items for this screen
     */
    public void display() {
        //Draw the unlockable list
        stage.getBatch().begin();
        unlockedItemFont.draw(stage.getBatch(), PropManager.UNLOCKED_ITEMS_TEXT, SpriteUtil.middleOf(Gdx.graphics.getWidth()) - SpriteUtil.middleOf(unlockedItemLayout.width), PropManager.UNLOCKED_ITEMS_Y);

        //Unknown Unlockable
        Texture unlockableUnknown = manager.get(PropManager.TEXTURE_LOCKED_UNLOCK, Texture.class);


        //UNLOCK_1 - Multipliers
        Texture unlockableMultipliers = manager.get(PropManager.TEXTURE_MULTIPLIER_UNLOCK, Texture.class);
        drawUnlockable(GameplayManager.getLevel() >= PropManager.UNLOCK_1_LEVEL ? unlockableMultipliers : unlockableUnknown, PropManager.UNLOCK_1_Y);

        //UNLOCK_2 - Health Pack
        Texture unlockableHealthPack = manager.get(PropManager.TEXTURE_HEALTHPACK_UNLOCK, Texture.class);
        drawUnlockable(GameplayManager.getLevel() >= PropManager.UNLOCK_2_LEVEL ? unlockableHealthPack : unlockableUnknown,PropManager.UNLOCK_2_Y);

        //UNLOCK_3 - Umbrella
        Texture unlockableUmbrella = manager.get(PropManager.TEXTURE_UMBRELLA_UNLOCK, Texture.class);
        drawUnlockable(GameplayManager.getLevel() >= PropManager.UNLOCK_3_LEVEL ? unlockableUmbrella : unlockableUnknown,PropManager.UNLOCK_3_Y);

        //UNLOCK_4 - Shield
        Texture unlockableShield = manager.get(PropManager.TEXTURE_SHIELD_UNLOCK, Texture.class);
        drawUnlockable(GameplayManager.getLevel() >= PropManager.UNLOCK_4_LEVEL ? unlockableShield : unlockableUnknown,PropManager.UNLOCK_4_Y);

        //TODO: Scrollpane or 'Next' button
        //UNLOCK_5 - Filtration
//        Texture unlockableFilter = manager.get(PropManager.TEXTURE_FILTRATION_UNLOCK, Texture.class);
//        drawUnlockable(GameplayManager.getLevel() >= PropManager.UNLOCK_5_LEVEL ? unlockableFilter : unlockableUnknown,PropManager.UNLOCK_5_Y);
        stage.getBatch().end();

        drawCloseButton();
    }

    /**
     * Draw an unlockable item
     * @param image The image to draw
     * @param height    The placement of the image on the screen
     */
    private void drawUnlockable(Texture image, int height) {
        stage.getBatch().draw(image,
                SpriteUtil.middleOf(Gdx.graphics.getWidth()) - SpriteUtil.middleOf(image.getWidth()),
                Gdx.graphics.getHeight() - image.getHeight() - height,
                image.getWidth(),
                image.getHeight());
    }

    /**
     * Draw the close button in the top-right of the screen
     */
    private void drawCloseButton() {
        Gdx.input.setInputProcessor(stage);
        closeButton.setX(Gdx.graphics.getWidth() - closeButton.getWidth());
        closeButton.setY(Gdx.graphics.getHeight() - closeButton.getHeight());
        stage.addActor(closeButton);
        stage.draw();

        closeButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                parent.setUnlockScreenOpen(false);
                return false;
            }});
    }
}
