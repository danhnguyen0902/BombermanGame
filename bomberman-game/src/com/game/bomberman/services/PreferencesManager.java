package com.game.bomberman.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

// -------------------------------------------------------------------------
/**
 * Handle the game preference
 *
 * @author danh0902
 * @author chingyg
 * @author kyleh8
 * @version 2013.12.8
 */
public class PreferencesManager
{
    // constants
    private static final String PREF_VOLUME        = "volume";
    private static final String PREF_MUSIC_ENABLED = "music.enabled";
    private static final String PREF_SOUND_ENABLED = "sound.enabled";
    private static final String PREFS_NAME         = "bomberman";


    // ----------------------------------------------------------
    /**
     * Create a new PreferencesManager object.
     */
    public PreferencesManager()
    {
        // Do nothing
    }


    // ----------------------------------------------------------
    /**
     * Get the user preference
     *
     * @return preference set
     */
    protected Preferences getPrefs()
    {
        return Gdx.app.getPreferences(PREFS_NAME);
    }


    // ----------------------------------------------------------
    /**
     * Check if the sound is enabled
     *
     * @return boolean
     */
    public boolean isSoundEnabled()
    {
        return getPrefs().getBoolean(PREF_SOUND_ENABLED, true);
    }


    // ----------------------------------------------------------
    /**
     * Set the sound to enabled
     *
     * @param soundEffectsEnabled
     *            boolean
     */
    public void setSoundEnabled(boolean soundEffectsEnabled)
    {
        getPrefs().putBoolean(PREF_SOUND_ENABLED, soundEffectsEnabled);
        getPrefs().flush();
    }


    // ----------------------------------------------------------
    /**
     * Check if the music is enabled
     *
     * @return boolean
     */
    public boolean isMusicEnabled()
    {
        return getPrefs().getBoolean(PREF_MUSIC_ENABLED, true);
    }


    // ----------------------------------------------------------
    /**
     * Enabled the music
     *
     * @param musicEnabled
     *            boolean
     */
    public void setMusicEnabled(boolean musicEnabled)
    {
        getPrefs().putBoolean(PREF_MUSIC_ENABLED, musicEnabled);
        getPrefs().flush();
    }


    // ----------------------------------------------------------
    /**
     * Get the volume value
     *
     * @return float value of volume
     */
    public float getVolume()
    {
        return getPrefs().getFloat(PREF_VOLUME, 0.5f);
    }


    // ----------------------------------------------------------
    /**
     * Set the volume value
     *
     * @param volume
     *            float volume value
     */
    public void setVolume(float volume)
    {
        getPrefs().putFloat(PREF_VOLUME, volume);
        getPrefs().flush();
    }
}
