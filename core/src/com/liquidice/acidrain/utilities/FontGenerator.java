package com.liquidice.acidrain.utilities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

public class FontGenerator {
    private static final String MAIN_FONT = "font/Play.ttf";

    public static FreetypeFontLoader.FreeTypeFontLoaderParameter generatePlayFont(int size, Color color) {
        FreetypeFontLoader.FreeTypeFontLoaderParameter font = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        font.fontFileName = MAIN_FONT;
        font.fontParameters.size = size;
        font.fontParameters.color = color;
        return font;
    }

    public static FreetypeFontLoader.FreeTypeFontLoaderParameter generatePlayFont(int size, Color color, int border, Color borderColor) {
        FreetypeFontLoader.FreeTypeFontLoaderParameter font = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        font.fontFileName = MAIN_FONT;
        font.fontParameters.size = size;
        font.fontParameters.color = color;
        font.fontParameters.borderWidth = border;
        font.fontParameters.borderColor = borderColor;
        return font;
    }
}
