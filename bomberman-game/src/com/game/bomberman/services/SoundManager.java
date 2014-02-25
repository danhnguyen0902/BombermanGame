package com.game.bomberman.services;

import com.game.bomberman.utils.LRUCache.CacheEntryRemovedListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Disposable;
import com.game.bomberman.services.SoundManager.BombermanSound;
import com.game.bomberman.utils.LRUCache;

// -------------------------------------------------------------------------
/**
 * A service that manages the sound effects.
 *
 * @author danh0902
 * @author chingyg
 * @author kyleh8
 * @version 2013.12.8
 */

public class SoundManager
    implements CacheEntryRemovedListener<BombermanSound, Sound>, Disposable
{
    /**
     * The available sound files.
     */
    public enum BombermanSound
    {

        /**
         * clicked
         */
        CLICK("sound/click.wav");

        private final String fileName;


        private BombermanSound(String fileName)
        {
            this.fileName = fileName;
        }


        // ----------------------------------------------------------
        /**
         * Get the file name
         *
         * @return filename
         */
        public String getFileName()
        {
            return fileName;
        }
    }

    /**
     * The volume to be set on the sound.
     */
    private float                                 volume  = 1f;

    /**
     * Whether the sound is enabled.
     */
    private boolean                               enabled = true;

    /**
     * The sound cache.
     */
    private final LRUCache<BombermanSound, Sound> soundCache;


    /**
     * Creates the sound manager.
     */
    public SoundManager()
    {
        soundCache = new LRUCache<SoundManager.BombermanSound, Sound>(10);
        soundCache.setEntryRemovedListener(this);
    }


    /**
     * Plays the specified sound.
     *
     * @param sound
     *            BombermanSound file
     */
    public void play(BombermanSound sound)
    {
        // check if the sound is enabled
        if (!enabled)
            return;

        // try and get the sound from the cache
        Sound soundToPlay = soundCache.get(sound);
        if (soundToPlay == null)
        {
            FileHandle soundFile = Gdx.files.internal(sound.getFileName());
            soundToPlay = Gdx.audio.newSound(soundFile);
            soundCache.add(sound, soundToPlay);
        }

        // play the sound
        soundToPlay.play(volume);
    }


    /**
     * Sets the sound volume which must be inside the range [0,1].
     *
     * @param volume
     *            Volume value
     */
    public void setVolume(float volume)
    {
        // check and set the new volume
        if (volume < 0 || volume > 1f)
        {
            throw new IllegalArgumentException(
                "The volume must be inside the range: [0,1]");
        }
        this.volume = volume;
    }


    /**
     * Enables or disabled the sound.
     *
     * @param enabled
     *            boolean
     */
    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }


    // EntryRemovedListener implementation

    public void notifyEntryRemoved(BombermanSound key, Sound value)
    {
        value.dispose();
    }


    /**
     * Disposes the sound manager.
     */
    public void dispose()
    {
        for (Sound sound : soundCache.retrieveAll())
        {
            sound.stop();
            sound.dispose();
        }
    }
}
