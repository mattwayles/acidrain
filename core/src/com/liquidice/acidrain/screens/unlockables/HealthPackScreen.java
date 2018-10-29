package com.liquidice.acidrain.screens.unlockables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.liquidice.acidrain.managers.Gameplay;
import com.liquidice.acidrain.managers.assets.Font;
import com.liquidice.acidrain.managers.assets.Textures;

public class HealthPackScreen {
    private static SpriteBatch batch = new SpriteBatch();
    private static GlyphLayout youveUnlockedLayout = new GlyphLayout();
    private static GlyphLayout healthPackLayout = new GlyphLayout();
    private static GlyphLayout powerupLayout = new GlyphLayout();
    private static GlyphLayout holdLayout = new GlyphLayout();
    private static BitmapFont youveUnlockedFont = Font.generatePlayFont(100, Color.GOLD, 6, Color.BLACK);
    private static BitmapFont healthPackFont = Font.generatePlayFont(56, Color.GOLD, 6, Color.BLACK);
    private static BitmapFont powerupFont = Font.generatePlayFont(48, Color.BLACK);
    private static BitmapFont holdFont = Font.generatePlayFont(42, Color.WHITE, 3, Color.BLACK);
    private static String youveUnlockedText = "You've Unlocked...";
    private static String healthPackText = "Health Pack!";
    private static String powerupText = "Increase your City Strength by 25%";
    private static int count;
    private static boolean desc;
    private static int bounceY = Gdx.graphics.getHeight() - 800 ;


    //TODO: Consolidate better to support multiple powerups
    public static void display() {
        batch.begin();
        batch.draw(Textures.unlocked, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //You've Unlocked...

        youveUnlockedLayout.setText(youveUnlockedFont, youveUnlockedText);
        youveUnlockedFont.draw(batch, youveUnlockedText, Gdx.graphics.getWidth() / 2 - youveUnlockedLayout.width / 2, Gdx.graphics.getHeight() - 200);

        //Health Pack!

        healthPackLayout.setText(healthPackFont, healthPackText);
        healthPackFont.draw(batch, healthPackText, Gdx.graphics.getWidth() / 2 - healthPackLayout.width / 2, Gdx.graphics.getHeight() - 900);

        //Increase your city strength by 25%

        powerupLayout.setText(powerupFont, powerupText);
        powerupFont.draw(batch, powerupText, Gdx.graphics.getWidth() / 2 - powerupLayout.width / 2, Gdx.graphics.getHeight() - 1150);

        //Hold touch to continue
        String holdText = "Touch anywhere to play level " + (Gameplay.getLevel() + 1);
        holdLayout.setText(holdFont, holdText);
        holdFont.draw(batch, holdText, Gdx.graphics.getWidth() / 2 - holdLayout.width / 2, Gdx.graphics.getHeight() - 1450);

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
        batch.draw(Textures.healthPack, (Gdx.graphics.getWidth() / 2) - ((Textures.healthPack.getWidth() * 4) / 2), bounceY, Textures.healthPack.getWidth() * 4, Textures.healthPack.getHeight() * 4);

        batch.end();

    }
}
