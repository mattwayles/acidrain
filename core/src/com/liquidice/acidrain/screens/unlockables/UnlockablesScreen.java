package com.liquidice.acidrain.screens.unlockables;

import com.badlogic.gdx.Gdx;
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
import com.liquidice.acidrain.managers.AssetLoader;
import com.liquidice.acidrain.managers.GameplayManager;
import com.liquidice.acidrain.managers.PropManager;
import com.liquidice.acidrain.managers.ScreenManager;
import com.liquidice.acidrain.utilities.SpriteUtil;

/**
 * Render a list of App Unlockables
 */
public class UnlockablesScreen {

    private Stage stage = new Stage();
    private AssetLoader assetLoader;
    private ImageButton closeButton;
    private BitmapFont unlockedItemFont;
    private GlyphLayout unlockedItemLayout = new GlyphLayout();
    private TextureRegion closeButtonRegion;
    private TextureRegion unlockableMultipliers;
    private TextureRegion unlockableHealthPack;
    private TextureRegion unlockableUmbrella;
    private TextureRegion unlockableShield;
    private TextureRegion unlockableFilter;
    private TextureRegion unlockableTeamwork;
    private TextureRegion unlockablePurpleRain;
    private TextureRegionDrawable unlockableMultiplierDrawable;
    private TextureRegionDrawable unlockableTeamworkDrawable;
    private TextureRegionDrawable unlockableHealthPackDrawable;
    private TextureRegionDrawable unlockableUmbrellaDrawable;
    private TextureRegionDrawable unlockablePurpleRainDrawable;
    private TextureRegionDrawable unlockableShieldDrawable;
    private TextureRegionDrawable unlockableFilterDrawable;
    private Image unlockableMultipliersImg;
    private Image unlockableHealthPackImg;
    private Image unlockableUmbrellaImg;
    private Image unlockableShieldImg;
    private Image unlockableFilterImg;
    private Image unlockableTeamworkImg;
    private Image unlockablePurpleRainImg;
    private Table scrollableTable = new Table();
    private Table table = new Table();
    private ScrollPane scroll = new ScrollPane(table);

    /**
     * Initialize the screen with text, close button, and parent handlers
     * @param loader  The AssetLoader containing the assets to use in this screen
     */
    public UnlockablesScreen(AssetLoader loader) {
        //Assets
        this.assetLoader = loader;
        this.assetLoader.loadUnlockablesScreen();
        setFonts();

        
        
        //Close Button
        closeButtonRegion = new TextureRegion();
        closeButtonRegion.setRegion(assetLoader.getManager().get(PropManager.BUTTON_CLOSE, Texture.class));
        TextureRegionDrawable closeButtonDrawable = new TextureRegionDrawable();
        closeButtonDrawable.setRegion(closeButtonRegion);
        ImageButton.ImageButtonStyle closeButtonStyle = new ImageButton.ImageButtonStyle();
        closeButtonStyle.up = closeButtonDrawable;
        closeButton = new ImageButton(closeButtonStyle);

        //Create Scroll Table
        createScrollTable();
    }

    public void loadAssets() {
        table.clear();
        this.assetLoader.loadUnlockablesScreen();
        closeButtonRegion.setRegion(assetLoader.getManager().get(PropManager.BUTTON_CLOSE, Texture.class));

        //TODO: Table does not render correctly on loadAsset(), Investigate and fix
        //TODO: Clean up this image stuff, or refactor into private method

        //Texture Regions
        unlockableMultipliers.setRegion(GameplayManager.getLevel() >= PropManager.UNLOCK_1_LEVEL
                ? assetLoader.getManager().get(PropManager.TEXTURE_MULTIPLIER_UNLOCK, Texture.class)
                : assetLoader.getManager().get(PropManager.TEXTURE_LOCKED_UNLOCK, Texture.class));
        unlockableTeamwork.setRegion(GameplayManager.getLevel() >= PropManager.UNLOCK_2_LEVEL
                ? assetLoader.getManager().get(PropManager.TEXTURE_TEAMWORK_UNLOCK, Texture.class)
                : assetLoader.getManager().get(PropManager.TEXTURE_LOCKED_UNLOCK, Texture.class));
        unlockableHealthPack.setRegion(GameplayManager.getLevel() >= PropManager.UNLOCK_3_LEVEL
                ? assetLoader.getManager().get(PropManager.TEXTURE_HEALTHPACK_UNLOCK, Texture.class)
                : assetLoader.getManager().get(PropManager.TEXTURE_LOCKED_UNLOCK, Texture.class));
        unlockableUmbrella.setRegion(GameplayManager.getLevel() >= PropManager.UNLOCK_4_LEVEL
                ? assetLoader.getManager().get(PropManager.TEXTURE_UMBRELLA_UNLOCK, Texture.class)
                : assetLoader.getManager().get(PropManager.TEXTURE_LOCKED_UNLOCK, Texture.class));
        unlockablePurpleRain.setRegion(GameplayManager.getLevel() >= PropManager.UNLOCK_5_LEVEL
                ? assetLoader.getManager().get(PropManager.TEXTURE_PURPLE_RAIN_UNLOCK, Texture.class)
                : assetLoader.getManager().get(PropManager.TEXTURE_LOCKED_UNLOCK, Texture.class));
        unlockableShield.setRegion(GameplayManager.getLevel() >= PropManager.UNLOCK_6_LEVEL
                ? assetLoader.getManager().get(PropManager.TEXTURE_SHIELD_UNLOCK, Texture.class)
                : assetLoader.getManager().get(PropManager.TEXTURE_LOCKED_UNLOCK, Texture.class));
        unlockableFilter.setRegion(GameplayManager.getLevel() >= PropManager.UNLOCK_7_LEVEL
                ? assetLoader.getManager().get(PropManager.TEXTURE_FILTRATION_UNLOCK, Texture.class)
                : assetLoader.getManager().get(PropManager.TEXTURE_LOCKED_UNLOCK, Texture.class));

        //Drawables
        unlockableMultiplierDrawable.setRegion(unlockableMultipliers);
        unlockableTeamworkDrawable.setRegion(unlockableTeamwork);
        unlockableHealthPackDrawable.setRegion(unlockableHealthPack);
        unlockableUmbrellaDrawable.setRegion(unlockableUmbrella);
        unlockablePurpleRainDrawable.setRegion(unlockablePurpleRain);
        unlockableShieldDrawable.setRegion(unlockableShield);
        unlockableFilterDrawable.setRegion(unlockableFilter);

        //Image
        unlockableMultipliersImg.setDrawable(unlockableMultiplierDrawable);
        unlockableTeamworkImg.setDrawable(unlockableTeamworkDrawable);
        unlockableHealthPackImg.setDrawable(unlockableHealthPackDrawable);
        unlockableUmbrellaImg.setDrawable(unlockableUmbrellaDrawable);
        unlockablePurpleRainImg.setDrawable(unlockablePurpleRainDrawable);
        unlockableShieldImg.setDrawable(unlockableShieldDrawable);
        unlockableFilterImg.setDrawable(unlockableFilterDrawable);

        //Populate inner table with images

        table.add(unlockableMultipliersImg).padTop(10).row();
        table.add(unlockableTeamworkImg).padTop(10).row();
        table.add(unlockableHealthPackImg).padTop(10).row();
        table.add(unlockableUmbrellaImg).padTop(10).row();
        table.add(unlockablePurpleRainImg).padTop(10).row();
        table.add(unlockableShieldImg).padTop(10).row();
        table.add(unlockableFilterImg).padTop(10).row();

        table.pack();
        table.setTransform(true);  //clipping enabled

        //Add table to container
        scrollableTable.add(scroll).expand().fill();

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
        scrollableTable.setFillParent(true);
        scrollableTable.padTop(PropManager.UNLOCKED_ITEMS_SCROLL_Y);
        scrollableTable.padBottom(PropManager.CITY_HEIGHT + PropManager.UNLOCKED_ITEMS_SCROLL_OFFSET);

        //Add container to stage
        stage.addActor(scrollableTable);
        
        //TODO: Clean up this table stuff, or refactor into private method:
        
        //Texture Regions
        unlockableMultipliers = new TextureRegion(GameplayManager.getLevel() >= PropManager.UNLOCK_1_LEVEL 
                ? assetLoader.getManager().get(PropManager.TEXTURE_MULTIPLIER_UNLOCK, Texture.class) 
                : assetLoader.getManager().get(PropManager.TEXTURE_LOCKED_UNLOCK, Texture.class));
        unlockableTeamwork = new TextureRegion(GameplayManager.getLevel() >= PropManager.UNLOCK_2_LEVEL
                ? assetLoader.getManager().get(PropManager.TEXTURE_TEAMWORK_UNLOCK, Texture.class)
                : assetLoader.getManager().get(PropManager.TEXTURE_LOCKED_UNLOCK, Texture.class));
        unlockableHealthPack = new TextureRegion(GameplayManager.getLevel() >= PropManager.UNLOCK_3_LEVEL
                ? assetLoader.getManager().get(PropManager.TEXTURE_HEALTHPACK_UNLOCK, Texture.class)
                : assetLoader.getManager().get(PropManager.TEXTURE_LOCKED_UNLOCK, Texture.class));
        unlockableUmbrella = new TextureRegion(GameplayManager.getLevel() >= PropManager.UNLOCK_4_LEVEL
                ? assetLoader.getManager().get(PropManager.TEXTURE_UMBRELLA_UNLOCK, Texture.class)
                : assetLoader.getManager().get(PropManager.TEXTURE_LOCKED_UNLOCK, Texture.class));
        unlockablePurpleRain = new TextureRegion(GameplayManager.getLevel() >= PropManager.UNLOCK_5_LEVEL
                ? assetLoader.getManager().get(PropManager.TEXTURE_PURPLE_RAIN_UNLOCK, Texture.class)
                : assetLoader.getManager().get(PropManager.TEXTURE_LOCKED_UNLOCK, Texture.class));
        unlockableShield = new TextureRegion(GameplayManager.getLevel() >= PropManager.UNLOCK_6_LEVEL
                ? assetLoader.getManager().get(PropManager.TEXTURE_SHIELD_UNLOCK, Texture.class)
                : assetLoader.getManager().get(PropManager.TEXTURE_LOCKED_UNLOCK, Texture.class));
        unlockableFilter = new TextureRegion(GameplayManager.getLevel() >= PropManager.UNLOCK_7_LEVEL
                ? assetLoader.getManager().get(PropManager.TEXTURE_FILTRATION_UNLOCK, Texture.class)
                : assetLoader.getManager().get(PropManager.TEXTURE_LOCKED_UNLOCK, Texture.class));
        
        //Drawables
        unlockableMultiplierDrawable = new TextureRegionDrawable(unlockableMultipliers);
        unlockableTeamworkDrawable = new TextureRegionDrawable(unlockableTeamwork);
        unlockableHealthPackDrawable = new TextureRegionDrawable(unlockableHealthPack);
        unlockableUmbrellaDrawable = new TextureRegionDrawable(unlockableUmbrella);
        unlockablePurpleRainDrawable = new TextureRegionDrawable(unlockablePurpleRain);
        unlockableShieldDrawable = new TextureRegionDrawable(unlockableShield);
        unlockableFilterDrawable = new TextureRegionDrawable(unlockableFilter);
        
        //Image
        unlockableMultipliersImg = new Image(unlockableMultiplierDrawable);
        unlockableTeamworkImg = new Image(unlockableTeamworkDrawable);
        unlockableHealthPackImg = new Image(unlockableHealthPackDrawable);
        unlockableUmbrellaImg = new Image(unlockableUmbrellaDrawable);
        unlockablePurpleRainImg = new Image(unlockablePurpleRainDrawable);
        unlockableShieldImg = new Image(unlockableShieldDrawable);
        unlockableFilterImg = new Image(unlockableFilterDrawable);

        //Populate inner table with images

        table.add(unlockableMultipliersImg).padTop(10).row();
        table.add(unlockableTeamworkImg).padTop(10).row();
        table.add(unlockableHealthPackImg).padTop(10).row();
        table.add(unlockableUmbrellaImg).padTop(10).row();
        table.add(unlockablePurpleRainImg).padTop(10).row();
        table.add(unlockableShieldImg).padTop(10).row();
        table.add(unlockableFilterImg).padTop(10).row();
        
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

        closeButton.clearListeners();
        closeButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                assetLoader.unloadUnlockablesScreen();
                ScreenManager.getStartScreen().setUnlockScreenOpen(false);
                return false;
            }});
    }

    /**
     * Set screen fonts
     */
    private void setFonts() {
        //Fonts
        unlockedItemFont = new BitmapFont(Gdx.files.internal(PropManager.FONT_PLAY_100),
                Gdx.files.internal(PropManager.FONT_PLAY_100_PNG), false);
        unlockedItemFont.setColor(Color.GOLD);
        unlockedItemLayout.setText(unlockedItemFont, PropManager.UNLOCKED_ITEMS_TEXT);
    }

}
