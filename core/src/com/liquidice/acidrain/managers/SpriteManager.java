package com.liquidice.acidrain.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.liquidice.acidrain.sprites.Background;
import com.liquidice.acidrain.sprites.Bucket;
import com.liquidice.acidrain.sprites.City;
import com.liquidice.acidrain.sprites.Clouds;
import com.liquidice.acidrain.sprites.Shield;
import com.liquidice.acidrain.sprites.Umbrella;

/**
 * Initialize Static Sprite Images on application startup
 */
public class SpriteManager {
    public static void init(AssetManager manager) {
        Background.setManager(manager);
        Clouds.init(manager.get(PropManager.TEXTURE_CLOUDS, Texture.class));
        City.setImage(manager.get(PropManager.TEXTURE_CITY_10, Texture.class));
        Bucket.init(manager.get(PropManager.TEXTURE_BUCKET_0, Texture.class));
        Umbrella.init(manager.get(PropManager.TEXTURE_UMBRELLA_LEFT, Texture.class),
                manager.get(PropManager.TEXTURE_UMBRELLA_RIGHT, Texture.class));
        Shield.init(manager.get(PropManager.TEXTURE_SHIELD, Texture.class));

    }
}
