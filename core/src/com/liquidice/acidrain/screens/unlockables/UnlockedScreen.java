package com.liquidice.acidrain.screens.unlockables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.liquidice.acidrain.managers.Gameplay;
import com.liquidice.acidrain.managers.assets.Font;
import com.liquidice.acidrain.managers.assets.Textures;

public class UnlockedScreen {
    private static SpriteBatch batch = new SpriteBatch();
    private static GlyphLayout youveUnlockedLayout = new GlyphLayout();
    private static GlyphLayout layout = new GlyphLayout();
    private static GlyphLayout powerupLayout = new GlyphLayout();
    private static GlyphLayout holdLayout = new GlyphLayout();
    private static String youveUnlockedText = "You've Unlocked...";
    private static int count;
    private static boolean desc;
    private static int bounceY = Gdx.graphics.getHeight() - 800 ;

    public static void display(Texture image, String title, String power) {
        batch.begin();
        batch.draw(Textures.unlocked, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //You've Unlocked...

        youveUnlockedLayout.setText(Font.youveUnlockedFont, youveUnlockedText);
        Font.youveUnlockedFont.draw(batch, youveUnlockedText, Gdx.graphics.getWidth() / 2 - youveUnlockedLayout.width / 2, Gdx.graphics.getHeight() - 200);

        layout.setText(Font.powerupTypeFont, title);
        powerupLayout.setText(Font.powerupFont, power);
        Font.powerupTypeFont.draw(batch, title, Gdx.graphics.getWidth() / 2 - layout.width / 2, Gdx.graphics.getHeight() - 900);
        Font.powerupFont.draw(batch, power, Gdx.graphics.getWidth() / 2 - powerupLayout.width / 2, Gdx.graphics.getHeight() - 1150);

        //Hold touch to continue
        String holdText = "Touch anywhere to play level " + (Gameplay.getLevel() + 1);
        holdLayout.setText(Font.holdFont, holdText);
        Font.holdFont.draw(batch, holdText, Gdx.graphics.getWidth() / 2 - holdLayout.width / 2, Gdx.graphics.getHeight() - 1450);

        if (!desc) {
            if (count < 10){
                count++;
                bounceY += 6;
            } else {
                desc = true;
            }
        }
        else {
            if (count > 0) {
                count --;
                bounceY -= 6;
            } else {
                desc = false;
            }
        }
        //Health Pack Bouncing Image
        batch.draw(image, (Gdx.graphics.getWidth() / 2) - ((image.getWidth() * 4) / 2), bounceY, image.getWidth() * 4, image.getHeight() * 4);

        batch.end();

    }
}
