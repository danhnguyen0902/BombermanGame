package com.game.bomberman.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.game.bomberman.view.WorldRenderer;

// -------------------------------------------------------------------------
/**
 * class for the bots(enemeies)
 *
 * @author Ching Hsiang Chen(chingyg)
 * @author Kyle He (kyleh8)
 * @author Danh Nguyen(danh0902)
 * @version Dec 8, 2013
 */
public class Bot
{
    // -------------------------------------------------------------------------
    /**
     * state of bot
     */
    public enum State
    {
        /**
         * walking
         */
        WALKING,
        /**
         * dying
         */
        DYING,
        /**
         * dead
         */
        DEAD
    }


    // -------------------------------------------------------------------------
    /**
     * state of direction of the bots
     */
    public enum Direction
    {
        /**
         * left
         */
        LEFT,
        /**
         * right
         */
        RIGHT,
        /**
         * down
         */
        DOWN,
        /**
         * up
         */
        UP
    }

    /**
     * speed
     */
    public static final float SPEED        = 0.5f;                // unit
// per
// second
    /**
     * size
     */
    public static final float SIZE         = 0.5f;                // half
// a unit
    /**
     * times
     */
    public static final int   TIMES        = 300;

    /**
     * position of bot
     */
    Vector2                   position     = new Vector2();
    /**
     * acceleration of bot
     */
    Vector2                   acceleration = new Vector2();
    /**
     * velocity of bot
     */
    Vector2                   velocity     = new Vector2();
    /**
     * bot boundry
     */
    Rectangle                 bounds       = new Rectangle();
    /**
     * bot state
     */
    State                     botState     = State.WALKING;
    /**
     * bot direction
     */
    Direction                 facing       = Direction.LEFT;
    /**
     * state time
     */
    float                     stateTime    = 0;
    /**
     * how long its heading in a direction
     */
    int                       times        = 0;                   // "how long"
// it's
// heading in a direction

    private float             x1;
    private float             y1;
    private float             x2;
    private float             y2;

    private float             xC;
    private float             yC;

    private int               r;
    private int               c;
    private float             min;
    /**
     * if bot is at wall
     */
    public static boolean     isAtWall;
    /**
     * if bot is at bomb
     */
    public static boolean     isAtBomb;

    private int               flag         = 0;

    private int               row;
    private int               col;
    private long              start        = 9000000000000000000L;


    // ----------------------------------------------------------
    /**
     * gets start
     * @return start
     */
    public long getStart()
    {
        return start;
    }


    // ----------------------------------------------------------
    /**
     * sets start
     * @param start desired start
     */
    public void setStart(long start)
    {
        this.start = start;
    }


    // ----------------------------------------------------------
    /**
     * gets row size
     * @return row size
     */
    public int getRow()
    {
        return row;
    }


    // ----------------------------------------------------------
    /**
     * sets row
     * @param row desired row
     */
    public void setRow(int row)
    {
        this.row = row;
    }


    // ----------------------------------------------------------
    /**
     * gets column
     * @return column
     */
    public int getCol()
    {
        return col;
    }


    // ----------------------------------------------------------
    /**
     * sets column
     * @param col
     */
    public void setCol(int col)
    {
        this.col = col;
    }


    // ----------------------------------------------------------
    /**
     * gets min value
     * @return min
     */
    public float getMin()
    {
        return min;
    }


    // ----------------------------------------------------------
    /**
     * sets min value
     * @param min desired min value
     */
    public void setMin(float min)
    {
        this.min = min;
    }


    // ----------------------------------------------------------
    /**
     * gets x1 coordinate
     * @return x1 coordinate
     */
    public float getX1()
    {
        return x1;
    }


    // ----------------------------------------------------------
    /**
     * gets y1 coordinate
     * @return y1 coordinate
     */
    public float getY1()
    {
        return y1;
    }


    // ----------------------------------------------------------
    /**
     * getes x2 coordinate
     * @return x2 coordinate
     */
    public float getX2()
    {
        return x2;
    }


    // ----------------------------------------------------------
    /**
     * gets y2 coordinate
     * @return y2 coordinate
     */
    public float getY2()
    {
        return y2;
    }


    // ----------------------------------------------------------
    /**
     * gets x coordinate
     * @return x coordinate
     */
    public float getxC()
    {
        return xC;
    }


    // ----------------------------------------------------------
    /**
     * gets y coordinate
     * @return y coordinate
     */
    public float getyC()
    {
        return yC;
    }


    // ----------------------------------------------------------
    /**
     * sets x coordinate
     * @param xC desired x coordinate
     */
    public void setxC(float xC)
    {
        this.xC = xC;
    }


    // ----------------------------------------------------------
    /**
     * sets y coordinate
     * @param yC  y coordinate
     */
    public void setyC(float yC)
    {
        this.yC = yC;
    }


    // ----------------------------------------------------------
    /**
     * Create a new Bot object.
     * @param position bot's position
     */
    public Bot(Vector2 position)
    {
        this.position = position;
        this.bounds.height = SIZE;
        this.bounds.width = SIZE;
    }


    // ----------------------------------------------------------
    /**
     * gets direction bot is facing
     * @return direction bot is facing
     */
    public Direction getFacing()
    {
        return facing;
    }


    // ----------------------------------------------------------
    /**
     * set bot's direction
     * @param dir direction bot is facing
     */
    public void setFacing(Direction dir)
    {
        this.facing = dir;
    }


    // ----------------------------------------------------------
    /**
     *gets position of bot
     * @return bot position
     */
    public Vector2 getPosition()
    {
        return position;
    }


    // ----------------------------------------------------------
    /**
     * gets bot's acceleration
     * @return acceleration of bot
     */
    public Vector2 getAcceleration()
    {
        return acceleration;
    }


    // ----------------------------------------------------------
    /**
     * gets bot's velocity
     * @return bot's velocity
     */
    public Vector2 getVelocity()
    {
        return velocity;
    }


    // ----------------------------------------------------------
    /**
     * gets bot's boundries
     * @return bounderies
     */
    public Rectangle getBounds()
    {
        return bounds;
    }


    // ----------------------------------------------------------
    /**
     * gets state of bot
     * @return state of bot
     */
    public State getState()
    {
        return botState;
    }


    // ----------------------------------------------------------
    /**
     * sets state of bot
     * @param newState desired state of bot
     */
    public void setState(State newState)
    {
        this.botState = newState;
    }


    // ----------------------------------------------------------
    /**
     * get bot flag
     * @return bot flag
     */
    public int getFlag()
    {
        return flag;
    }


    // ----------------------------------------------------------
    /**
     * set bot flag
     * @param flag desired bot flag
     */
    public void setFlag(int flag)
    {
        this.flag = flag;
    }


    // ----------------------------------------------------------
    /**
     * get state time
     * @return current state time
     */
    public float getStateTime()
    {
        return stateTime;
    }


    // ----------------------------------------------------------
    /**
     * get times
     * @return current time
     */
    public int getTimes()
    {
        return times;
    }


    // ----------------------------------------------------------
    /**
     * set time
     * @param times desired time
     */
    public void setTimes(int times)
    {
        this.times = times;
    }


    // ----------------------------------------------------------
    /**
     * updates all statetime and coordinates
     * @param delta statetime
     */
    public void update(float delta)
    {
        stateTime += delta;
        // position.add(velocity.tmp().mul(delta));
        position.add(velocity.x * delta, velocity.y * delta);

        find();
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


    // ----------------------------------------------------------
    /**
     * fits the world
     * @param world current world
     */
    public void fit(World world)
    {
        int p = findCell(1, world);
        if (p != -1)
        {
            position.x = (float)p / 2 + World.X_TOP_LEFT;
            // find();
            return;
        }

        p = findCell(2, world);
        position.y = (float)p / 2 + World.Y_BOTTOM_RIGHT;
        // find();
    }


    /**
     * Finds the closest cell to the current position of the object
     * @param n some num
     * @param world current world
     * @return num of closest cells
     */
    public int findCell(int n, World world)
    {
        float tmp;
        r = -1;
        c = -1;
        min = 1000000;
        int p = -1;
        isAtWall = false;
        isAtBomb = false;
        Block[][] block = world.getBlock();

        switch (n)
        {
            case 1:
                float y = (position.y - World.Y_BOTTOM_RIGHT) * 2;
                r = (int)y;
                y = y - r;

                if (y == 0)
                {
                    int numColumns = world.getNumColumns();
                    for (int j = 0; j < numColumns; ++j)
                    {
                        tmp = Math.abs(block[r][j].getxC() - xC);

                        if (tmp < World.sizeInX)
                        {
                            if (block[r][j].getState() == Block.State.WALL
                                || block[r][j].getState() == Block.State.DESTROY_WALL)
                            {
                                isAtWall = true;
                            }

                            if (block[r][j].getState() == Block.State.BOMB)
                            {
                                if (flag == 0)
                                {
                                    isAtBomb = true;
                                }
                                else
                                {
                                    isAtBomb = false;
                                }
                            }
                        }
                        /*
                         * else { if (block[r][j].getState() == Block.State.BOMB
                         * && flag == 1) { flag = 0; } }
                         */

                        if (tmp < min)
                        {
                            min = tmp;
                            p = j;
                        }
                    }
                }

                row = r;
                col = p;
                return p;
            case 2:
                float x = (position.x - World.X_TOP_LEFT) * 2;
                c = (int)x;
                x = x - c;

                if (x == 0)
                {
                    int numRows = world.getNumRows();
                    for (int i = 0; i < numRows; ++i)
                    {
                        tmp = Math.abs(block[i][c].getyC() - yC);

                        if (tmp < World.sizeInY)
                        {
                            if (block[i][c].getState() == Block.State.WALL
                                || block[i][c].getState() == Block.State.DESTROY_WALL)
                            {
                                isAtWall = true;
                            }

                            if (block[i][c].getState() == Block.State.BOMB)
                            {
                                if (flag == 0)
                                {
                                    isAtBomb = true;
                                }
                                else
                                {
                                    isAtBomb = false;
                                }
                            }
                        }
/*
 * else { if (block[i][c].getState() == Block.State.BOMB && flag == 1) { flag =
 * 0; } }
 */
                        if (tmp < min)
                        {
                            min = tmp;
                            p = i;
                        }
                    }
                }

                row = p;
                col = c;
                return p;
        }

        return p;
    }


    // ----------------------------------------------------------
    /**
     * check to see if world boundry is ok
     * @param world
     * @return if world is ok
     */
    public boolean check(World world)
    {
        boolean check =
            World.X_TOP_LEFT <= position.x
                && position.x <= World.X_BOTTOM_RIGHT
                && World.Y_BOTTOM_RIGHT <= position.y
                && position.y <= World.Y_TOP_LEFT;

        if (!check)
        {
            return false;
        }

        int p = findCell(1, world);
        if (p != -1 && (isAtWall == true || isAtBomb == true))
        {
            return false;
        }

        p = findCell(2, world);
        if (isAtWall == true || isAtBomb == true)
        {
            return false;
        }

        return check;
    }
}
