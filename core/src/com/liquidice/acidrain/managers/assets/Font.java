package com.liquidice.acidrain.managers.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Font {
    private static FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Play.ttf"));

    public static BitmapFont generatePlayFont(int size, Color color) {
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.color = color;
        return generator.generateFont(parameter);
    }

    public static BitmapFont generatePlayFont(int size, Color color, int border, Color borderColor) {
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.color = color;
        parameter.borderWidth = border;
        parameter.borderColor = borderColor;
        return generator.generateFont(parameter);
    }
}
