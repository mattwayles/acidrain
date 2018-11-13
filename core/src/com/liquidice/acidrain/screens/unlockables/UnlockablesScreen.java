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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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
        //Parent
        Gdx.app.log("Setting" , "manager and parents");
        this.manager = manager;
        this.parent = parent;

        //Text
        Gdx.app.log("Setting" , "font");
        unlockedItemFont = new BitmapFont(Gdx.files.internal(PropManager.FONT_PLAY_100),
                Gdx.files.internal(PropManager.FONT_PLAY_100_PNG), false);
        unlockedItemFont.setColor(Color.GOLD);
        unlockedItemLayout.setText(unlockedItemFont, PropManager.UNLOCKED_ITEMS_TEXT);

        //Close Button
        Gdx.app.log("Setting" , "close");
        ImageButton.ImageButtonStyle closeButtonStyle = new ImageButton.ImageButtonStyle();
        closeButtonStyle.up = new TextureRegionDrawable(new TextureRegion(manager.get(PropManager.BUTTON_CLOSE, Texture.class)));
        closeButton = new ImageButton(closeButtonStyle);

        //Create Scroll Table
        createScrollTable();
    }

    /**
     * Render the list of unlockable/unlocked items for this screen
     */
    public void display() {
        Gdx.input.setInputProcessor(stage);

        //Draw the unlockable list
        stage.getBatch().begin();

        unlockedItemFont.draw(stage.getBatch(), PropManager.UNLOCKED_ITEMS_TEXT, SpriteUtil.middleOf(Gdx.graphics.getWidth()) - SpriteUtil.middleOf(unlockedItemLayout.width), PropManager.UNLOCKED_ITEMS_Y);

        stage.getBatch().end();

        //Draw the stage scrollPane
        stage.draw();
        stage.act();

        //Draw the close button
        drawCloseButton();
    }

    /**
     * Create a scrolling list of Unlockable items
     */
    private void createScrollTable() {
        //Create a table container and set properties
        Table scrollableTable = new Table();
        scrollableTable.setFillParent(true);
        scrollableTable.padTop(PropManager.UNLOCKED_ITEMS_SCROLL_Y);
        scrollableTable.padBottom(PropManager.CITY_HEIGHT + PropManager.UNLOCKED_ITEMS_SCROLL_OFFSET);

        //Add container to stage
        stage.addActor(scrollableTable);
        
        //Create images for the unlockable textures
        Image unlockableMultipliers = new Image(manager.get(PropManager.TEXTURE_MULTIPLIER_UNLOCK, Texture.class));
        Image unlockableHealthPack = new Image(manager.get(PropManager.TEXTURE_HEALTHPACK_UNLOCK, Texture.class));
        Image unlockableUmbrella = new Image(manager.get(PropManager.TEXTURE_UMBRELLA_UNLOCK, Texture.class));
        Image unlockableShield = new Image(manager.get(PropManager.TEXTURE_SHIELD_UNLOCK, Texture.class));
        Image unlockableFilter = new Image(manager.get(PropManager.TEXTURE_FILTRATION_UNLOCK, Texture.class));
        Image unlockableTeamwork = new Image(manager.get(PropManager.TEXTURE_TEAMWORK_UNLOCK, Texture.class));

        //Populate inner table with images
        Table table = new Table();
        table.add(GameplayManager.getLevel() >= PropManager.UNLOCK_1_LEVEL ? unlockableMultipliers : new Image(manager.get(PropManager.TEXTURE_LOCKED_UNLOCK, Texture.class))).padTop(10).row();
        table.add(GameplayManager.getLevel() >= PropManager.UNLOCK_2_LEVEL ? unlockableTeamwork : new Image(manager.get(PropManager.TEXTURE_LOCKED_UNLOCK, Texture.class))).padTop(10).row();
        table.add(GameplayManager.getLevel() >= PropManager.UNLOCK_3_LEVEL ? unlockableHealthPack : new Image(manager.get(PropManager.TEXTURE_LOCKED_UNLOCK, Texture.class))).padTop(10).row();
        table.add(GameplayManager.getLevel() >= PropManager.UNLOCK_4_LEVEL ? unlockableUmbrella : new Image(manager.get(PropManager.TEXTURE_LOCKED_UNLOCK, Texture.class))).padTop(10).row();
        table.add(GameplayManager.getLevel() >= PropManager.UNLOCK_5_LEVEL ? unlockableShield :new Image(manager.get(PropManager.TEXTURE_LOCKED_UNLOCK, Texture.class))).padTop(10).row();
        table.add(GameplayManager.getLevel() >= PropManager.UNLOCK_6_LEVEL ? unlockableFilter : new Image(manager.get(PropManager.TEXTURE_LOCKED_UNLOCK, Texture.class))).padTop(10).row();

        table.pack();
        table.setTransform(true);  //clipping enabled

        //Add table to container
        ScrollPane scroll = new ScrollPane(table);
        scrollableTable.add(scroll).expand().fill();
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
