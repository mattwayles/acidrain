package com.liquidice.acidrain.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.liquidice.acidrain.AcidRain;
import com.liquidice.acidrain.managers.AssetLoader;
import com.liquidice.acidrain.managers.GameplayManager;
import com.liquidice.acidrain.managers.PowerupManager;
import com.liquidice.acidrain.managers.PreferenceManager;
import com.liquidice.acidrain.managers.PropManager;
import com.liquidice.acidrain.managers.ScoreManager;
import com.liquidice.acidrain.managers.ScreenManager;
import com.liquidice.acidrain.utilities.SpriteUtil;

/**
 * Provide Pause, Play, and Stop buttons during gameplay
 */
class GameplayButtonOverlay {
    private AssetLoader assetLoader;
    private TextureRegion pauseButtonRegion;
    private TextureRegion playButtonRegion;
    private TextureRegion stopButtonRegion;
    private ImageButton playButton;
    private ImageButton stopButton;
    private ImageButton pauseButton;
    private BitmapFont pausedFont;
    private GlyphLayout pausedLayout = new GlyphLayout();
    private Stage stage = new Stage();

    /**
     * Create a GameplayButtonOverlay
     * @param loader   The AssetLoader containing the Textures used in this overlay
     */
    GameplayButtonOverlay(AssetLoader loader) {
        this.assetLoader = loader;

        //Create Gameplay Overlay Button styles
        ImageButton.ImageButtonStyle pauseButtonStyle = new ImageButton.ImageButtonStyle();
        ImageButton.ImageButtonStyle playButtonStyle = new ImageButton.ImageButtonStyle();
        ImageButton.ImageButtonStyle stopButtonStyle = new ImageButton.ImageButtonStyle();

        //Set Gameplay Overlay Button Textures
        pauseButtonRegion = new TextureRegion(loader.getManager().get(PropManager.BUTTON_PAUSE, Texture.class));
        playButtonRegion = new TextureRegion(loader.getManager().get(PropManager.BUTTON_PLAY, Texture.class));
        stopButtonRegion = new TextureRegion(loader.getManager().get(PropManager.BUTTON_STOP, Texture.class));

        //Set Gameplay Overlay Button Images
        pauseButtonStyle.up = new TextureRegionDrawable(pauseButtonRegion);
        playButtonStyle.up = new TextureRegionDrawable(playButtonRegion);
        stopButtonStyle.up = new TextureRegionDrawable(stopButtonRegion);

        //Create Gameplay Overlay Buttons
        playButton  = new ImageButton(playButtonStyle);
        stopButton  = new ImageButton(stopButtonStyle);
        pauseButton  = new ImageButton(pauseButtonStyle);

        pausedFont = new BitmapFont(Gdx.files.internal(PropManager.FONT_PLAY_56),
                Gdx.files.internal(PropManager.FONT_PLAY_56_PNG), false);
        pausedLayout.setText(pausedFont, PropManager.PAUSED);
    }

    void loadAssets() {
        //Set Gamplay Overlay Regions
        pauseButtonRegion.setTexture(assetLoader.getManager().get(PropManager.BUTTON_PAUSE, Texture.class));
        playButtonRegion.setTexture(assetLoader.getManager().get(PropManager.BUTTON_PLAY, Texture.class));
        stopButtonRegion.setTexture(assetLoader.getManager().get(PropManager.BUTTON_STOP, Texture.class));
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
        if (!GameplayManager.isPaused()) {
            //Game is playing: Create pause button
            stage.clear();
            pauseButton.setX(SpriteUtil.middleOf(Gdx.graphics.getWidth()) - SpriteUtil.middleOf(pauseButton.getWidth()));
            pauseButton.setY(Gdx.graphics.getHeight() - pauseButton.getHeight() - PropManager.PAUSE_BUTTON_HEIGHT);
            stage.addActor(pauseButton);
        }
        else {
            //Game is paused: Create play and stop button
            stage.clear();
            playButton.setX(SpriteUtil.middleOf(Gdx.graphics.getWidth()) - playButton.getWidth());
            stopButton.setX(SpriteUtil.middleOf(Gdx.graphics.getWidth()));

            playButton.setY(Gdx.graphics.getHeight() - playButton.getHeight() - PropManager.PLAY_STOP_BUTTON_HEIGHT);
            stopButton.setY(Gdx.graphics.getHeight() - stopButton.getHeight() - PropManager.PLAY_STOP_BUTTON_HEIGHT);
            stage.addActor(playButton);
            stage.addActor(stopButton);

            //Draw "Game Paused" font
            stage.getBatch().begin();
            pausedFont.draw(
                    stage.getBatch(),
                    PropManager.PAUSED,
                    SpriteUtil.middleOf(Gdx.graphics.getWidth()) - SpriteUtil.middleOf(pausedLayout.width),
                    SpriteUtil.middleOf(Gdx.graphics.getHeight()));
            stage.getBatch().end();
        }

        addButtonListeners();
        stage.draw();
    }

    /**
     * Provide actions to the buttons through button listeners
     */
    private void addButtonListeners() {
        //Pause button: Pause the game
        pauseButton.clearListeners();
        pauseButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameplayManager.pause();
                return false;
            }});

        //Play button: Resume the game
        playButton.clearListeners();
        playButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameplayManager.resume();
                return false;
            }});

        //Stop button: Return to main screen
        stopButton.clearListeners();
        stopButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (ScoreManager.getCaughtPercentage() > GameplayManager.getLevelBest()) {
                    PreferenceManager.putInt(PropManager.PREF_LEVEL_BEST, ScoreManager.getCaughtPercentage());
                }
                ScreenManager.getGameplayScreen().clearAll();
                PowerupManager.deactivateAllPowerups();
                assetLoader.unloadGameplayScreenAssets();
                ScreenManager.getStartScreen().loadAssets();
                GameplayManager.resume();
                GameplayManager.setGameState(PropManager.GAME_START_STATE);
                return false;
            }});
    }
}
