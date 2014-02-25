package com.game.bomberman.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Disposable;

// -------------------------------------------------------------------------
/**
 * A service that manages the background music.
 * <p>
 * Only one music may be playing at a given time.
 *
 * @author danh0902
 * @author chingyg
 * @author kyleh8
 * @version 2013.12.8
 */
public class MusicManager
    implements Disposable
{
    /**
     * The available music files.
     */
    public enum BombermanMusic
    {
        /**
         * Enum type menu
         */
        MENU("music/menu.ogg"),
        /**
         * Enum type level
         */
        LEVEL("music/level.ogg");

        private String fileName;
        private Music  musicResource;


        private BombermanMusic(String fileName)
        {
            this.fileName = fileName;
        }


        // ----------------------------------------------------------
        /**
         * Get the file name
         *
         * @return fileName
         */
        public String getFileName()
        {
            return fileName;
        }


        // ----------------------------------------------------------
        /**
         * Get the music resource
         *
         * @return musicResource
         */
        public Music getMusicResource()
        {
            return musicResource;
        }


        // ----------------------------------------------------------
        /**
         * Set music resource
         *
         * @param musicBeingPlayed
         *            Music instance passed in
         */
        public void setMusicResource(Music musicBeingPlayed)
        {
            this.musicResource = musicBeingPlayed;
        }
    }

    /**
     * Holds the music currently being played, if any.
     */
    private BombermanMusic musicBeingPlayed;

    /**
     * The volume to be set on the music.
     */
    private float          volume  = 1f;

    /**
     * Whether the music is enabled.
     */
    private boolean        enabled = true;


    /**
     * Creates the music manager.
     */
    public MusicManager()
    {
    }


    /**
     * Plays the given music (starts the streaming).
     * <p>
     * If there is already a music being played it is stopped automatically.
     *
     * @param music
     *            Game music
     */
    public void play(BombermanMusic music)
    {
        // check if the music is enabled
        if (!enabled)
            return;

        // check if the given music is already being played
        if (musicBeingPlayed == music)
            return;

        // stop any music being played
        stop();

        // start streaming the new music
        FileHandle musicFile = Gdx.files.internal(music.getFileName());
        Music musicResource = Gdx.audio.newMusic(musicFile);
        musicResource.setVolume(volume);
        musicResource.setLooping(true);
        musicResource.play();

        // set the music being played
        musicBeingPlayed = music;
        musicBeingPlayed.setMusicResource(musicResource);
    }


    /**
     * Stops and disposes the current music being played, if any.
     */
    public void stop()
    {
        if (musicBeingPlayed != null)
        {
            Music musicResource = musicBeingPlayed.getMusicResource();
            musicResource.stop();
            musicResource.dispose();
            musicBeingPlayed = null;
        }
    }


    /**
     * Sets the music volume which must be inside the range [0,1].
     *
     * @param volume
     *            float, volume value
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

        // if there is a music being played, change its volume
        if (musicBeingPlayed != null)
        {
            musicBeingPlayed.getMusicResource().setVolume(volume);
        }
    }


    /**
     * Enables or disabled the music.
     *
     * @param enabled
     *            Check if the music is enabled
     */
    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;

        // if the music is being deactivated, stop any music being played
        if (!enabled)
        {
            stop();
        }
    }


    /**
     * Disposes the music manager.
     */
    public void dispose()
    {
        stop();
    }
}
