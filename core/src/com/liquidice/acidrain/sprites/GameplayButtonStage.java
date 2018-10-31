package com.liquidice.acidrain.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.liquidice.acidrain.AcidRain;
import com.liquidice.acidrain.managers.Gameplay;
import com.liquidice.acidrain.managers.assets.Textures;

public class GameplayButtonStage {
    private static ImageButton.ImageButtonStyle pauseButtonStyle = new ImageButton.ImageButtonStyle();
    private static ImageButton.ImageButtonStyle playButtonStyle = new ImageButton.ImageButtonStyle();
    private static ImageButton.ImageButtonStyle stopButtonStyle = new ImageButton.ImageButtonStyle();
    private static ImageButton pauseButton;
    private static ImageButton playButton;
    private static ImageButton stopButton;
    private static Stage stage = new Stage();

    static {
        pauseButtonStyle.up = Textures.pauseButtonStyleImage;
        playButtonStyle.up = Textures.playButtonStyleImage;
        stopButtonStyle.up = Textures.stopButtonStyleImage;
        pauseButton  = new ImageButton(pauseButtonStyle);
        playButton  = new ImageButton(playButtonStyle);
        stopButton  = new ImageButton(stopButtonStyle);
    }

    public static void display(InputProcessor mainInputProcessor) {
        InputMultiplexer multiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(multiplexer);
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(mainInputProcessor);
        if (!Gameplay.isPaused()) {
            stage.clear();
            pauseButton.setX(Gdx.graphics.getWidth() / 2 - pauseButton.getWidth() / 2);
            pauseButton.setY(Gdx.graphics.getHeight() - pauseButton.getHeight() - 40);
            stage.addActor(pauseButton);
        }
        else {
            stage.clear();
            stopButton.setX(Gdx.graphics.getWidth() / 2 - stopButton.getWidth() / 2);
            playButton.setX(Gdx.graphics.getWidth() / 2 - playButton.getWidth() / 2);

            stopButton.setY(Gdx.graphics.getHeight() - playButton.getHeight() - 20);
            playButton.setY(Gdx.graphics.getHeight() - playButton.getHeight() - stopButton.getHeight() - 60);
            stage.addActor(stopButton);
            stage.addActor(playButton);
        }
        stage.draw();

        InputListener listener = new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("TOUCH", "DOWN");
                return true;
            }
            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                Gdx.app.log("DRAGGED: ", String.valueOf(x));
                if (x > Bucket.getImage().getWidth() && x < Gdx.graphics.getWidth() - Bucket.getImage().getWidth()) {
                    Bucket.setX(x - Bucket.getImage().getWidth() / 2);
                }
            }};

        stage.addListener(listener);
        pauseButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gameplay.pause();
                return false;
            }});
        playButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gameplay.resume();
                return false;
            }});
        stopButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gameplay.resume();
                AcidRain.setGameState(0);
                return false;
            }});
    }
}
