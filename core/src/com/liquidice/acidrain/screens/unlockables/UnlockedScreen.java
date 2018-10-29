package com.liquidice.acidrain.screens.unlockables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.liquidice.acidrain.managers.assets.Font;

public class UnlockedScreen {

    public static void display() {
       // Gdx.input.setInputProcessor(stage);


        BitmapFont font = Font.generatePlayFont(56, Color.WHITE);

        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = font;
        textFieldStyle.fontColor = Color.WHITE;
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = Color.WHITE;

        Label nameLabel = new Label("Name", labelStyle);
        TextField nameText = new TextField("Foo", textFieldStyle);
        Label addressLabel = new Label("Address:", labelStyle);
        TextField addressText = new TextField("Bar", textFieldStyle);

        Table table = new Table();
        table.setFillParent(true);
        table.setColor(Color.WHITE);
        table.add(nameLabel);              // Row 0, column 0.
        table.add(nameText).width(100);    // Row 0, column 1.
        table.row();                       // Move to next row.
        table.add(addressLabel);           // Row 1, column 0.
        table.add(addressText).width(100); // Row 1, column 1.
        table.center();
        table.debug();
        Stage stage = new Stage();
        stage.addActor(table);
        stage.draw();
    }
}
