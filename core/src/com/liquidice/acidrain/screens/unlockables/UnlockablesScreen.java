package com.liquidice.acidrain.screens.unlockables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.liquidice.acidrain.managers.Gameplay;
import com.liquidice.acidrain.managers.Properties;
import com.liquidice.acidrain.managers.assets.Font;
import com.liquidice.acidrain.managers.assets.Textures;
import com.liquidice.acidrain.screens.StartScreen;

public class UnlockablesScreen {

    private static String unlockedItemText = "Unlocked Items";
    private static GlyphLayout unlockedItemLayout = new GlyphLayout();
    private static BitmapFont unlockedItemFont = Font.generatePlayFont(Properties.UNLOCKED_ITEMS_FONT_SIZE, Color.GOLD, 6, Color.BLACK);
    private static ImageButton.ImageButtonStyle closeButtonStyle = new ImageButton.ImageButtonStyle();
    private static ImageButton closeButton;

    static {
        closeButtonStyle.up = Textures.closeButtonStyleImage;
        closeButton  = new ImageButton(closeButtonStyle);
    }
    
    private static Stage stage = new Stage();
    public static void display() {

        //Draw the unlockable list
        stage.getBatch().begin();
        unlockedItemLayout.setText(unlockedItemFont, unlockedItemText);
        unlockedItemFont.draw(stage.getBatch(), unlockedItemText, Gdx.graphics.getWidth() / 2 - unlockedItemLayout.width / 2, Properties.UNLOCKED_ITEMS_Y);
        //Level 5 - Health Pack
        drawUnlockable(Gameplay.getLevel() >= Properties.UNLOCK_1_LEVEL ? Textures.unlockableHealthPack : Textures.unlockableLocked, Properties.UNLOCK_1_Y);

        //Level 10 - Umbrella
        drawUnlockable(Gameplay.getLevel() >= Properties.UNLOCK_2_LEVEL ? Textures.unlockableUmbrella : Textures.unlockableLocked,Properties.UNLOCK_2_Y);

        //Level 15 - ????
        drawUnlockable(Gameplay.getLevel() >= Properties.UNLOCK_3_LEVEL ? Textures.unlockableLocked : Textures.unlockableLocked,Properties.UNLOCK_3_Y);

        //Level 20 - ????
        drawUnlockable(Gameplay.getLevel() >= Properties.UNLOCK_4_LEVEL ? Textures.unlockableLocked : Textures.unlockableLocked,Properties.UNLOCK_4_Y);
        stage.getBatch().end();

        drawCloseButton();
    }

    private static void drawUnlockable(Texture image, int height) {
        stage.getBatch().draw(image, Gdx.graphics.getWidth() / 2 - Textures.unlockableHealthPack.getWidth() / 2, Gdx.graphics.getHeight() - image.getHeight() - height, image.getWidth(), image.getHeight());

    }

    private static void drawCloseButton() {
        Gdx.input.setInputProcessor(stage);
        closeButton.setX(Gdx.graphics.getWidth() - closeButton.getWidth());
        closeButton.setY(Gdx.graphics.getHeight() - closeButton.getHeight());
        stage.addActor(closeButton);

        stage.draw();

        closeButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                StartScreen.setUnlockScreenOpen(false);
                return false;
            }});
    }
}
