package com.game.bomberman;

import com.game.bomberman.Bomberman;
import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

// -------------------------------------------------------------------------
/**
 *  The main android game
 *
 *  @author DANH_NGUYEN
 *  @version Dec 8, 2013
 */
public class BombermanGame
    extends AndroidApplication
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        AndroidApplicationConfiguration cfg =
            new AndroidApplicationConfiguration();
        cfg.useGL20 = true; // we didn't actually use it, but we just put it
                            // there

        initialize(new Bomberman(), cfg);
    }
}
