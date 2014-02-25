package com.game.bomberman.model;

import com.game.bomberman.view.WorldRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author Ching Hsiang Chen(chingyg)
 * @author Kyle He (kyleh8)
 * @author Danh Nguyen(danh0902)
 * @version Dec 8, 2013
 */
public class Block
{

    // -------------------------------------------------------------------------
    /**
     * Enum State of the cells inside the blocks
     */
    public enum State
    {
        /**
         * empty cell
         */
        EMPTY,
        /**
         * cell with wall
         */
        WALL,
        /**
         * Destroyed wall
         */
        DESTROY_WALL,
        /**
         * process of destorying wall
         */
        DESTROYING_WALL,
        /**
         * cell with bomb
         */
        BOMB,
        /**
         * cell with exploding bomb
         */
        EXPLODE,
        /**
         * cell with speed powerup
         */
        SPEED,
        /**
         * cell with longerBomb powerup
         */
        LONGER_BOMB,
        /**
         * cell with door
         */
        DOOR
    }

    /**
     * size
     */
    public static final float SIZE               = 0.5f;
    /**
     * state of time
     */
    public static float       stateTime          = 0;

    /**
     * number of exploding blocks
     */
    public static int         countExplodeBlocks = 0;
    /**
     * exploding block at X
     */
    public static int[]       explodedBlockX     = new int[30];
    /**
     * exploding block at Y
     */
    public static int[]       explodedBlockY     = new int[30];

    /**
     * position with x and y coordinates
     */
    Vector2                   position           = new Vector2();

    /**
     * boundries
     */
    Rectangle                 bounds             = new Rectangle();

    private float             x1;
    private float             y1;
    private float             x2;
    private float             y2;

    private float             xC;
    private float             yC;

    /**
     * initial condition of state
     */
    State                     state              = State.EMPTY;


    // ----------------------------------------------------------
    /**
     * Create a new Block object.
     *
     * @param pos
     *            position
     */
    public Block(Vector2 pos)
    {
        this.position = pos;
        this.bounds.width = SIZE;
        this.bounds.height = SIZE;
    }


    // ----------------------------------------------------------
    /**
     * gets position
     *
     * @return position
     */
    public Vector2 getPosition()
    {
        return position;
    }


    // ----------------------------------------------------------
    /**
     * gets boundry of cell
     *
     * @return boundry
     */
    public Rectangle getBounds()
    {
        return bounds;
    }


    // ----------------------------------------------------------
    /**
     * gets x coordinate
     *
     * @return x coordinate
     */
    public float getxC()
    {
        return xC;
    }


    // ----------------------------------------------------------
    /**
     * gets y coordinate
     *
     * @return y coordinate
     */
    public float getyC()
    {
        return yC;
    }


    // ----------------------------------------------------------
    /**
     * gets x1 coordinate
     *
     * @return x1 coordinate
     */
    public float getX1()
    {
        return x1;
    }


    // ----------------------------------------------------------
    /**
     * gets y1 coordinate
     *
     * @return y1 coordinate
     */
    public float getY1()
    {
        return y1;
    }


    // ----------------------------------------------------------
    /**
     * gets x2 coordinate
     *
     * @return x2 coordinate
     */
    public float getX2()
    {
        return x2;
    }


    // ----------------------------------------------------------
    /**
     * gets y2 coordinate
     *
     * @return y2 coordinate
     */
    public float getY2()
    {
        return y2;
    }


    // ----------------------------------------------------------
    /**
     * gets state of cell
     *
     * @return state of cell
     */
    public State getState()
    {
        return state;
    }


    // ----------------------------------------------------------
    /**
     * sets state of cell
     *
     * @param state
     *            desired state of cell
     */
    public void setState(State state)
    {
        this.state = state;
    }


    // ----------------------------------------------------------
    /**
     * finds coordinates
     */
    public void find()
    {
        x1 = position.x * WorldRenderer.ppuX;
        x2 = x1 + SIZE * WorldRenderer.ppuX;
        y2 = WorldRenderer.height - position.y * WorldRenderer.ppuY;
        y1 = y2 - SIZE * WorldRenderer.ppuY;

        xC = (x1 + x2) / 2;
        yC = (y1 + y2) / 2;
    }

}
