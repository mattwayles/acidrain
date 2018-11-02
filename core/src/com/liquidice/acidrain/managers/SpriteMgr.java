package com.liquidice.acidrain.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.liquidice.acidrain.sprites.Background;
import com.liquidice.acidrain.sprites.Bucket;
import com.liquidice.acidrain.sprites.City;
import com.liquidice.acidrain.sprites.Clouds;
import com.liquidice.acidrain.sprites.Umbrella;

public class SpriteMgr {
    public static void init(AssetManager manager) {
        Background.setManager(manager);
        Clouds.init(manager.get("clouds.png", Texture.class));
        City.setImage(manager.get("city/city10.png", Texture.class));
        Bucket.init(manager.get("bucket/bucket0.png", Texture.class));
        Umbrella.init(manager.get("unlockables/umbrella/umbrellaLeft.png", Texture.class),
                manager.get("unlockables/umbrella/umbrellaRight.png", Texture.class));

    }
}
