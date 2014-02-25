package com.game.bomberman.utils;

import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

// -------------------------------------------------------------------------
/**
 * Set up the texture
 *
 * @author danh0902
 * @author chingyg
 * @author kyleh8
 * @version 2013.12.8
 */
public class TextureSetup
{

    // ----------------------------------------------------------
    /**
     * Main method for the texture class
     *
     * @param args
     *            main method parameter
     */
    public static void main(String[] args)
    {
        TexturePacker2.process("/IMG/WALLS/", "/IMG/WALLS/", "textures5.pack");
    }
}
