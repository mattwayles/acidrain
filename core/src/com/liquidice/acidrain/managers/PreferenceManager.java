package com.liquidice.acidrain.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Manage Shared Preferences stored user data
 */
public class PreferenceManager {
    private static Preferences prefs = Gdx.app.getPreferences("MyPrefs");

    /**
     * Retrieve a Boolean preference
     * @param pref The preference name
     * @return The preference stored value
     */
    public static Boolean getBoolean(String pref) { return prefs.getBoolean(pref); }

    /**
     * Retrieve a Float preference
     * @param pref The preference name
     * @param def The preference default value
     * @return The preference stored value
     */
    static float getFloat(String pref, float def) { return prefs.getFloat(pref, def); }

    /**
     * Retrieve an Integer preference
     * @param pref The preference name
     * @param def The preference default value
     * @return The preference stored value
     */
    static int getInt(String pref, int def) { return prefs.getInteger(pref, def); }

    /**
     * Save a Boolean preference
     * @param pref The preference name
     * @param bool The preference value
     */
    public static void putBoolean(String pref, boolean bool) {
        prefs.putBoolean(pref, bool);
        prefs.flush();
    }

    /**
     * Save a Float preference
     * @param pref The preference name
     * @param num The preference value
     */
    static void putFloat(String pref, float num) {
        prefs.putFloat(pref, num);
        prefs.flush();
    }

    /**
     * Save an Integer preference
     * @param pref The preference name
     * @param num The preference value
     */
    public static void putInt(String pref, int num) {
        prefs.putInteger(pref, num);
        prefs.flush();
    }

    /**
     * Clear all preferences
     */
    public static void clear() { prefs.clear(); prefs.flush(); }

}
