package com.liquidice.acidrain.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class PreferenceManager {
    private static Preferences prefs = Gdx.app.getPreferences("MyPrefs");

    public static Boolean getBoolean(String pref) { return prefs.getBoolean(pref); }
    static float getFloat(String pref, float def) { return prefs.getFloat(pref, def); }
    static int getInt(String pref, int def) { return prefs.getInteger(pref, def); }
    public static void putBoolean(String pref, boolean bool) {
        prefs.putBoolean(pref, bool);
        prefs.flush();
    }

    public static void putInt(String pref, int num) {
        prefs.putInteger(pref, num);
        prefs.flush();
    }

    static void putFloat(String pref, float num) {
        prefs.putFloat(pref, num);
        prefs.flush();
    }

    public static void clear() { prefs.clear(); prefs.flush(); }

}
