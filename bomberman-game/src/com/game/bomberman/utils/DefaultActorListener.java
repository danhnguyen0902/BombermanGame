package com.game.bomberman.utils;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

// -------------------------------------------------------------------------
/**
 * The action listener class
 *
 * @author danh0902
 * @author chingyg
 * @author kyleh8
 * @version 2013.12.8
 */
public abstract class DefaultActorListener
    extends ActorGestureListener
{
    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     *
     * @param event
     * @param x
     * @param y
     * @param pointer
     * @param button
     * @return true
     */
    public boolean touchDown(
        Actor event,
        float x,
        float y,
        int pointer,
        int button)
    {
        return true;
    }
}
