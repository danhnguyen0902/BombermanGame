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
public class Bob
{

    // -------------------------------------------------------------------------
    /**
     * state of bob
     */
    public enum State
    {
        /**
         * idle
         */
        IDLE,
        /**
         * walking
         */
        WALKING,
        /**
         * dying
         */
        DYING
    }


    // -------------------------------------------------------------------------
    /**
     * direction bob is facing
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
         * up
         */
        UP,
        /**
         * down
         */
        DOWN
    }

    /**
     * speed of bob
     */
    public static float       SPEED;                              // unit
    /**
     * size
     */
// per
// second
    public static final float SIZE         = 0.5f;                // half
// a unit

    /**
     * position of bob
     */
    Vector2                   position     = new Vector2();
    /**
     * bob's acceleration
     */
    Vector2                   acceleration = new Vector2();
    /**
     * bob's velocity
     */
    Vector2                   velocity     = new Vector2();
    /**
     * boundry
     */
    Rectangle                 bounds       = new Rectangle();
    /**
     * initial state of bob
     */
    State                     state        = State.IDLE;
    /**
     * initial direction of bob
     */
    Direction                 facing       = Direction.DOWN;
    /**
     * initial time state
     */
    float                     stateTime    = 0;

    private float             x1;
    private float             y1;
    private float             x2;
    private float             y2;

    /**
     * row size
     */
    public static int         row;
    /**
     * column size
     */
    public static int         col;

    /**
     *
     */
    public static int         r;
    /**
     *
     */
    public static int         c;
    /**
     * if bob is at wall
     */
    public static boolean     isAtWall;
    /**
     * if bob is at bomb
     */
    public static boolean     isAtBomb;
    /**
     * min
     */
    public static float       min;

    private int               flag         = 0;

    private long              start        = 9000000000000000000L;

    /**
     * if won
     */
    public static boolean     VICTORY;


    // ----------------------------------------------------------
    /**
     * gets start position
     *
     * @return start
     */
    public long getStart()
    {
        return start;
    }


    // ----------------------------------------------------------
    /**
     * sets start position
     *
     * @param start
     */
    public void setStart(long start)
    {
        this.start = start;
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

    private float xC;
    private float yC;


    // ----------------------------------------------------------
    /**
     * get x coordinate
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
     * sets x coordinate
     *
     * @param xC
     *            desired x coordinate
     */
    public void setxC(float xC)
    {
        this.xC = xC;
    }


    // ----------------------------------------------------------
    /**
     * sets y coordinate
     *
     * @param yC
     *            y coordinate
     */
    public void setyC(float yC)
    {
        this.yC = yC;
    }


    // ----------------------------------------------------------
    /**
     * creates bob object
     *
     * @param position
     *            position of bob
     */
    public Bob(Vector2 position)
    {
        this.position = position;
        this.bounds.height = SIZE;
        this.bounds.width = SIZE;
        SPEED = 1.5f;
        VICTORY = false;
    }


    // ----------------------------------------------------------
    /**
     * gets direction bob is facing
     *
     * @return direction
     */
    public Direction getFacing()
    {
        return facing;
    }


    // ----------------------------------------------------------
    /**
     * sets direction of bob
     *
     * @param dir direction
     */
    public void setFacing(Direction dir)
    {
        this.facing = dir;
    }


    // ----------------------------------------------------------
    /**
     * gets position
     * @return position
     */
    public Vector2 getPosition()
    {
        return position;
    }


    // ----------------------------------------------------------
    /**
     * gets acceleration
     * @return acceleration
     */
    public Vector2 getAcceleration()
    {
        return acceleration;
    }


    // ----------------------------------------------------------
    /**
     * gets velocity
     * @return velocity
     */
    public Vector2 getVelocity()
    {
        return velocity;
    }


    // ----------------------------------------------------------
    /**
     * gets boundries
     * @return bounderies
     */
    public Rectangle getBounds()
    {
        return bounds;
    }


    // ----------------------------------------------------------
    /**
     * get state of bob
     * @return state of bob
     */
    public State getState()
    {
        return state;
    }


    // ----------------------------------------------------------
    /**
     * set state of bob
     * @param newState desired state of bob
     */
    public void setState(State newState)
    {
        this.state = newState;
    }


    // ----------------------------------------------------------
    /**
     * gets flag of bob
     * @return flag
     */
    public int getFlag()
    {
        return flag;
    }


    // ----------------------------------------------------------
    /**
     * sets flag of bob
     * @param flag
     */
    public void setFlag(int flag)
    {
        this.flag = flag;
    }


    // ----------------------------------------------------------
    /**
     * gets current state time
     * @return state time
     */
    public float getStateTime()
    {
        return stateTime;
    }


    // ----------------------------------------------------------
    /**
     * updates all coordinates
     * @param delta state time
     */
    public void update(float delta)
    {
        stateTime += delta;
        // position.add(velocity.tmp().mul(delta));
        position.add(velocity.x * delta, velocity.y * delta);

        // position.x += velocity.x * delta;
        // position.y += velocity.y * delta;

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
     * see if created world fits
     * @param world
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
     * @param n  number
     * @param world current world
     * @return number of closet cells
     */
    public int findCell(int n, World world)
    {
        float tmp;
        min = 1000000;
        r = -1;
        c = -1;
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
                        else
                        {
                            if (block[i][c].getState() == Block.State.BOMB
                                && flag == 1)
                            {
                                flag = 0;
                            }
                        }

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
     * checks world boundries and coordinates
     * @param world current world
     * @return true if everything is ok
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


    /**
     * Checks the distance between the current bomb and the bots
     * @param bot bot
     * @return if bomb is at bob
     */
    public boolean distance(Bot bot)
    {
        if (xC == bot.getxC() && yC == bot.getyC())
        {
            return true;
        }
        else if (xC == bot.getxC())
        {
            return (Math.abs(yC - bot.getyC()) <= World.sizeInY / 3 * 2);
        }
        else if (yC == bot.getyC())
        {
            return (Math.abs(xC - bot.getxC()) <= World.sizeInX / 3 * 2);
        }
        else
        {
            return (Math.abs(xC - bot.getxC()) <= World.sizeInX / 3 * 2 && Math
                .abs(yC - bot.getyC()) <= World.sizeInY / 3 * 2);
        }
    }

}
