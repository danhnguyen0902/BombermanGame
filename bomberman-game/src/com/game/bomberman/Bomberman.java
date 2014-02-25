package com.game.bomberman;

import com.game.bomberman.services.SoundManager;
import com.game.bomberman.services.PreferencesManager;
import com.game.bomberman.screens.SplashScreen;
import com.game.bomberman.services.MusicManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;

/**
 * The game's main class, called as application events are fired.
 */
public class Bomberman
    extends Game
{
    // whether we are in development mode
    /**
     * the mode of the game
     */
    public static final boolean DEV_MODE = false;

    // a libgdx helper class that logs the current FPS each second
    private FPSLogger           fpsLogger;

    // services
    private PreferencesManager  preferencesManager;
    private MusicManager        musicManager;
    private SoundManager        soundManager;


    // ----------------------------------------------------------
    /**
     * Create a new Bomberman object.
     */
    public Bomberman()
    {
    }


    // Services' getters

    // ----------------------------------------------------------
    /**
     * Gets the PreferencesManager
     *
     * @return the object
     */
    public PreferencesManager getPreferencesManager()
    {
        return preferencesManager;
    }


    // ----------------------------------------------------------
    /**
     * Gets the MusicManager
     *
     * @return the object
     */
    public MusicManager getMusicManager()
    {
        return musicManager;
    }


    // ----------------------------------------------------------
    /**
     * Gets the SoundManager
     *
     * @return the object
     */
    public SoundManager getSoundManager()
    {
        return soundManager;
    }


    // Game-related methods

    public void create()
    {
        // create the preferences manager
        preferencesManager = new PreferencesManager();

        // create the music manager
        musicManager = new MusicManager();
        musicManager.setVolume(preferencesManager.getVolume());
        musicManager.setEnabled(preferencesManager.isMusicEnabled());

        // create the sound manager
        soundManager = new SoundManager();
        soundManager.setVolume(preferencesManager.getVolume());
        soundManager.setEnabled(preferencesManager.isSoundEnabled());

        // create the helper objects
        fpsLogger = new FPSLogger();
    }


    @Override
    public void resize(int width, int height)
    {
        super.resize(width, height);

        // show the splash screen when the game is resized for the first time;
        // this approach avoids calling the screen's resize method repeatedly
        if (getScreen() == null)
        {
            if (DEV_MODE)
            {
                // setScreen(new LevelScreen(this, 0));
            }
            else
            {
                setScreen(new SplashScreen(this));
            }
        }
    }


    @Override
    public void render()
    {
        super.render();

        // output the current FPS
        if (DEV_MODE)
            fpsLogger.log();
    }


    @Override
    public void pause()
    {
        super.pause();

        // persist the profile, because we don't know if the player will come
        // back to the game
        // profileManager.persist();
    }


    @Override
    public void resume()
    {
        super.resume();
    }


    @Override
    public void setScreen(Screen screen)
    {
        super.setScreen(screen);
    }


    @Override
    public void dispose()
    {
        super.dispose();

        // dipose some services
        musicManager.dispose();
        soundManager.dispose();
    }
}
