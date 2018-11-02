package com.liquidice.acidrain.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.liquidice.acidrain.AcidRain;
import com.liquidice.acidrain.managers.GameplayMgr;
import com.liquidice.acidrain.managers.PropertiesMgr;
import com.liquidice.acidrain.utilities.SpriteUtil;

/**
 * Provide Pause, Play, and Stop buttons during gameplay
 */
public class GameplayButtonOverlay {
    private ImageButton playButton;
    private ImageButton stopButton;
    private ImageButton pauseButton;
    private Stage stage = new Stage();

    /**
     * Create a GameplayButtonOverlay
     * @param manager   The AssetMgr containing the Textures used in this overlay
     */
    public GameplayButtonOverlay(AssetManager manager) {
        //Create GameplayMgr Overlay Button styles
        ImageButton.ImageButtonStyle playButtonStyle = new ImageButton.ImageButtonStyle();
        ImageButton.ImageButtonStyle stopButtonStyle = new ImageButton.ImageButtonStyle();
        ImageButton.ImageButtonStyle pauseButtonStyle = new ImageButton.ImageButtonStyle();

        //Create GameplayMgr Overlay Buttons
        playButton  = new ImageButton(playButtonStyle);
        stopButton  = new ImageButton(stopButtonStyle);
        pauseButton  = new ImageButton(pauseButtonStyle);

        //Set GameplayMgr Overlay Button images
        playButtonStyle.up = new TextureRegionDrawable(new TextureRegion(manager.get("buttons/playButton.png", Texture.class)));
        stopButtonStyle.up = new TextureRegionDrawable(new TextureRegion(manager.get("buttons/stopButton.png", Texture.class)));
        pauseButtonStyle.up = new TextureRegionDrawable(new TextureRegion(manager.get("buttons/pauseButton.png", Texture.class)));
    }

    /**
     * Display the GameplayButtonOverlay on top of a screen
     */
    void display() {

        //Combine the input processor for the parent screen with this button overlay's input processor
        InputMultiplexer multiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(multiplexer);
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(AcidRain.getInputProcessor());

        //Create and display the overlay
        if (!GameplayMgr.isPaused()) {
            //Game is playing: Create pause button
            stage.clear();
            pauseButton.setX(SpriteUtil.middleOf(Gdx.graphics.getWidth() - SpriteUtil.middleOf(pauseButton.getWidth())));
            pauseButton.setY(Gdx.graphics.getHeight() - pauseButton.getHeight() - PropertiesMgr.PAUSE_BUTTON_HEIGHT);
            stage.addActor(pauseButton);
        }
        else {
            //Game is paused: Create play and stop button
            stage.clear();
            playButton.setX(SpriteUtil.middleOf(Gdx.graphics.getWidth()) - playButton.getWidth());
            stopButton.setX(SpriteUtil.middleOf(Gdx.graphics.getWidth()));

            playButton.setY(Gdx.graphics.getHeight() - playButton.getHeight() - PropertiesMgr.PLAY_STOP_BUTTON_HEIGHT);
            stopButton.setY(Gdx.graphics.getHeight() - stopButton.getHeight() - PropertiesMgr.PLAY_STOP_BUTTON_HEIGHT);
            stage.addActor(playButton);
            stage.addActor(stopButton);
        }

        addButtonListeners();
        stage.draw();
    }

    /**
     * Provide actions to the buttons through button listeners
     */
    private void addButtonListeners() {
        //Pause button: Pause the game
        pauseButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameplayMgr.pause();
                return false;
            }});

        //Play button: Resume the game
        playButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameplayMgr.resume();
                return false;
            }});

        //Stop button: Return to main screen
        stopButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameplayMgr.resume();
                AcidRain.setGameState(PropertiesMgr.GAME_START_STATE);
                return false;
            }});
    }
}
