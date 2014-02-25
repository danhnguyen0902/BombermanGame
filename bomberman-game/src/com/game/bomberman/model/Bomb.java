package com.game.bomberman.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.game.bomberman.view.WorldRenderer;

// -------------------------------------------------------------------------
/**
 * takes care of bombs
 *
 * @author Ching Hsiang Chen(chingyg)
 * @author Kyle He (kyleh8)
 * @author Danh Nguyen(danh0902)
 * @version Dec 8, 2013
 */
public class Bomb
{

    // -------------------------------------------------------------------------
    /**
     * state of bomb
     */
    public enum State
    {
        /**
         * nothing
         */
        NOTHING,
        /**
         * idle
         */
        IDLE,
        /**
         * explode
         */
        EXPLODE
    }

    /**
     * size of bomb
     */
    public static final float SIZE      = 0.5f;                // half
// a unit

    /**
     * position of bomb
     */
    Vector2                   position  = new Vector2();
    /**
     * bounds of bomb
     */
    Rectangle                 bounds    = new Rectangle();
    /**
     * initial state of bomb
     */
    State                     state     = State.NOTHING;
    /**
     * initial state of time
     */
    float                     stateTime = 0;

    private float             xC        = -10;
    private float             yC        = -10;

    /**
     * row
     */
    public static int         r;
    /**
     * column
     */
    public static int         c;

    private int               row;
    private int               col;

    private float             min;

    private long              start     = 9000000000000000000L;
    private long              start2    = 9000000000000000000L;
    /**
     * length of bomb
     */
    public static int         length;


    // ----------------------------------------------------------
    /**
     * gets start
     *
     * @return start
     */
    public long getStart2()
    {
        return start2;
    }


    // ----------------------------------------------------------
    /**
     * sets start2
     *
     * @param start2
     *            desired start2
     */
    public void setStart2(long start2)
    {
        this.start2 = start2;
    }


    // ----------------------------------------------------------
    /**
     * gets min explosion
     *
     * @return min explosion
     */
    public float getMin()
    {
        return min;
    }


    // ----------------------------------------------------------
    /**
     * sets min explosion
     *
     * @param min
     *            desired min explosion
     */
    public void setMin(float min)
    {
        this.min = min;
    }


    // ----------------------------------------------------------
    /**
     * gets row
     *
     * @return row
     */
    public int getRow()
    {
        return row;
    }


    // ----------------------------------------------------------
    /**
     * sets row
     *
     * @param row
     *            desired row
     */
    public void setRow(int row)
    {
        this.row = row;
    }


    // ----------------------------------------------------------
    /**
     * gets column size
     *
     * @return column
     */
    public int getCol()
    {
        return col;
    }


    // ----------------------------------------------------------
    /**
     * sets column size
     *
     * @param col
     *            desired column
     */
    public void setCol(int col)
    {
        this.col = col;
    }


    // ----------------------------------------------------------
    /**
     * gets start time
     *
     * @return start
     */
    public long getStart()
    {
        return start;
    }


    // ----------------------------------------------------------
    /**
     * sets start time
     *
     * @param times
     *            desired start time
     */
    public void setStart(long times)
    {
        this.start = times;
    }


    // ----------------------------------------------------------
    /**
     * set times
     *
     * @param times
     *            desired time
     */
    public void setTimes(long times)
    {
        this.start = times;
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
     * Create a new Bomb object.
     *
     * @param position
     *            position
     */
    public Bomb(Vector2 position)
    {
        this.position = position;
        this.bounds.height = SIZE;
        this.bounds.width = SIZE;
        length = 2;
    }


    // ----------------------------------------------------------
    /**
     * sets x coordinate
     *
     * @param xC
     *            x coordinate
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
     * gets bounderies
     *
     * @return bounderies
     */
    public Rectangle getBounds()
    {
        return bounds;
    }


    // ----------------------------------------------------------
    /**
     * gets state of bomb
     *
     * @return state of bomb
     */
    public State getState()
    {
        return state;
    }


    // ----------------------------------------------------------
    /**
     * sets state of bomb
     *
     * @param newState
     *            desired state of bomb
     */
    public void setState(State newState)
    {
        this.state = newState;
    }


    // ----------------------------------------------------------
    /**
     * gets state time
     *
     * @return statetime
     */
    public float getStateTime()
    {
        return stateTime;
    }


    // ----------------------------------------------------------
    /**
     * updates all coordinates and time
     *
     * @param delta
     *            statetime
     */
    public void update(float delta)
    {
        stateTime += delta;
    }


    // ----------------------------------------------------------
    /**
     * sets position of bomb
     *
     * @param bobPosition
     *            desired bomb position
     */
    public void setPosition(Vector2 bobPosition)
    {
        this.position.x = bobPosition.x;
        this.position.y = bobPosition.y;

        find();
    }


    // ----------------------------------------------------------
    /**
     * finds coordinates
     */
    public void find()
    {
        float x1 = position.x * WorldRenderer.ppuX;
        float x2 = x1 + SIZE * WorldRenderer.ppuX;
        float y2 = WorldRenderer.height - position.y * WorldRenderer.ppuY;
        float y1 = y2 - SIZE * WorldRenderer.ppuY;

        xC = (x1 + x2) / 2;
        yC = (y1 + y2) / 2;
    }


    // ----------------------------------------------------------
    /**
     * check bounderies of the world
     *
     * @param x
     *            some num
     * @param world
     *            current world
     * @return true or false if bounderies are ok
     */
    public boolean checkBounds(int x, World world)
    {
        return (0 <= x && x < world.getNumRows());
    }


    // ----------------------------------------------------------
    /**
     * sets bomb to a block
     *
     * @param i
     *            row
     * @param j
     *            column
     * @param world
     *            current world
     */
    public void setBomb(int i, int j, World world)
    {
        Block[][] block = world.getBlock();

        block[i][j].setState(Block.State.BOMB);
    }


    // ----------------------------------------------------------
    /**
     * sets bomb to explode
     *
     * @param i
     *            row
     * @param j
     *            column
     * @param world
     *            current world
     */
    public void setExplode(int i, int j, World world)
    {
        Block[][] block = world.getBlock();

        block[i][j].setState(Block.State.EXPLODE);
        Block.explodedBlockX[Block.countExplodeBlocks] = i;
        Block.explodedBlockY[Block.countExplodeBlocks] = j;
        ++Block.countExplodeBlocks;

        int stop1 = 0;
        int stop2 = 0;
        int stop3 = 0;
        int stop4 = 0;

        for (int k = 1; k <= length; ++k)
        {
            if (checkBounds(i + k, world))
            {
                if ((block[i + k][j].getState() == Block.State.WALL || block[i
                    + k][j].getState() == Block.State.DESTROY_WALL)
                    && stop1 == 0)
                {
                    stop1 = 1;
                    if (block[i + k][j].getState() == Block.State.DESTROY_WALL)
                    {
                        block[i + k][j].setState(Block.State.DESTROYING_WALL);
                    }
                }

                if (stop1 == 0)
                {
                    block[i + k][j].setState(Block.State.EXPLODE);
                    Block.explodedBlockX[Block.countExplodeBlocks] = i + k;
                    Block.explodedBlockY[Block.countExplodeBlocks] = j;
                    ++Block.countExplodeBlocks;
                }
            }

            if (checkBounds(i - k, world))
            {
                if ((block[i - k][j].getState() == Block.State.WALL || block[i
                    - k][j].getState() == Block.State.DESTROY_WALL)
                    && stop2 == 0)
                {
                    stop2 = 1;
                    if (block[i - k][j].getState() == Block.State.DESTROY_WALL)
                    {
                        block[i - k][j].setState(Block.State.DESTROYING_WALL);
                    }
                }

                if (stop2 == 0)
                {
                    block[i - k][j].setState(Block.State.EXPLODE);
                    Block.explodedBlockX[Block.countExplodeBlocks] = i - k;
                    Block.explodedBlockY[Block.countExplodeBlocks] = j;
                    ++Block.countExplodeBlocks;
                }
            }

            if (checkBounds(j + k, world))
            {
                if ((block[i][j + k].getState() == Block.State.WALL || block[i][j
                    + k].getState() == Block.State.DESTROY_WALL)
                    && stop3 == 0)
                {
                    stop3 = 1;
                    if (block[i][j + k].getState() == Block.State.DESTROY_WALL)
                    {
                        block[i][j + k].setState(Block.State.DESTROYING_WALL);
                    }
                }

                if (stop3 == 0)
                {
                    block[i][j + k].setState(Block.State.EXPLODE);
                    Block.explodedBlockX[Block.countExplodeBlocks] = i;
                    Block.explodedBlockY[Block.countExplodeBlocks] = j + k;
                    ++Block.countExplodeBlocks;
                }
            }

            if (checkBounds(j - k, world))
            {
                if ((block[i][j - k].getState() == Block.State.WALL || block[i][j
                    - k].getState() == Block.State.DESTROY_WALL)
                    && stop4 == 0)
                {
                    stop4 = 1;
                    if (block[i][j - k].getState() == Block.State.DESTROY_WALL)
                    {
                        block[i][j - k].setState(Block.State.DESTROYING_WALL);
                    }
                }

                if (stop4 == 0)
                {
                    block[i][j - k].setState(Block.State.EXPLODE);
                    Block.explodedBlockX[Block.countExplodeBlocks] = i;
                    Block.explodedBlockY[Block.countExplodeBlocks] = j - k;
                    ++Block.countExplodeBlocks;
                }
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * assign items to block
     *
     * @param i
     *            row
     * @param j
     *            column
     * @param world
     *            current world
     */
    public void assignItem(int i, int j, World world)
    {
        Block[][] block = world.getBlock();

        if (i == World.doorX && j == World.doorY)
        {
            block[i][j].setState(Block.State.DOOR);
            return;
        }

        int rand = (int)(Math.random() * 4);
        if (rand == 2)
        {
            rand = (int)(Math.random() * 2);
            switch (rand)
            {
                case 0:
                    block[i][j].setState(Block.State.SPEED);
                    break;
                case 1:
                    block[i][j].setState(Block.State.LONGER_BOMB);
                    break;
            // case 2:
            // block[i][j].setState(Block.State.ADD_BOMB);
            // break;
            }
        }
        else
        {
            block[i][j].setState(Block.State.EMPTY);
        }
    }


    // ----------------------------------------------------------
    /**
     * set bomb to not explode
     *
     * @param i
     *            row
     * @param j
     *            column
     * @param world
     *            current world
     */
    public void setUnExplode(int i, int j, World world)
    {
        Block[][] block = world.getBlock();
        block[i][j].setState(Block.State.EMPTY);

        for (int k = 1; k <= length; ++k)
        {
            if (checkBounds(i + k, world)
                && (block[i + k][j].getState() == Block.State.EXPLODE || block[i
                    + k][j].getState() == Block.State.DESTROYING_WALL))
            {
                if (block[i + k][j].getState() == Block.State.DESTROYING_WALL)
                {
                    assignItem(i + k, j, world);
                }
                else
                {
                    block[i + k][j].setState(Block.State.EMPTY);
                }
            }

            if (checkBounds(i - k, world)
                && (block[i - k][j].getState() == Block.State.EXPLODE || block[i
                    - k][j].getState() == Block.State.DESTROYING_WALL))
            {
                if (block[i - k][j].getState() == Block.State.DESTROYING_WALL)
                {
                    assignItem(i - k, j, world);
                }
                else
                {
                    block[i - k][j].setState(Block.State.EMPTY);
                }
            }

            if (checkBounds(j + k, world)
                && (block[i][j + k].getState() == Block.State.EXPLODE || block[i][j
                    + k].getState() == Block.State.DESTROYING_WALL))
            {
                if (block[i][j + k].getState() == Block.State.DESTROYING_WALL)
                {
                    assignItem(i, j + k, world);
                }
                else
                {
                    block[i][j + k].setState(Block.State.EMPTY);
                }
            }

            if (checkBounds(j - k, world)
                && (block[i][j - k].getState() == Block.State.EXPLODE || block[i][j
                    - k].getState() == Block.State.DESTROYING_WALL))
            {
                if (block[i][j - k].getState() == Block.State.DESTROYING_WALL)
                {
                    assignItem(i, j - k, world);
                }
                else
                {
                    block[i][j - k].setState(Block.State.EMPTY);
                }
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * if bomb fits, bomb will be set at specified block
     *
     * @param world
     *            current world
     */
    public void fit(World world)
    {
        int p = findCell(1, world);
        if (p != -1)
        {
            position.x = (float)p / 2 + World.X_TOP_LEFT;

            row = r;
            col = p;
            setBomb(row, col, world);

            // find();

            return;
        }

        p = findCell(2, world);
        position.y = (float)p / 2 + World.Y_BOTTOM_RIGHT;

        row = p;
        col = c;
        setBomb(row, col, world);

        // find();
    }


    /**
     * Finds the closest cell to the current position of the object
     *
     * @param n
     *            some num
     * @param world
     *            current world
     * @return num of closest cells to object
     */
    public int findCell(int n, World world)
    {
        float tmp;
        min = 1000000;
        r = -1;
        c = -1;
        int p = -1;
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

                        if (tmp < min)
                        {
                            min = tmp;
                            p = j;
                        }
                    }
                }

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

                        if (tmp < min)
                        {
                            min = tmp;
                            p = i;
                        }
                    }
                }

                return p;
        }

        return p;
    }


    /**
     * Checks the distance between the current bomb and the character
     *
     * @param bob
     *            BOB
     * @return if bob is within bomb cell explosion
     */
    public boolean distance(Bob bob)
    {
        if (xC == bob.getxC() && yC == bob.getyC())
        {
            return true;
        }
        else if (xC == bob.getxC())
        {
            return (Math.abs(yC - bob.getyC()) < World.sizeInY);
        }
        else if (yC == bob.getyC())
        {
            return (Math.abs(xC - bob.getxC()) < World.sizeInX);
        }
        else
        {
            return (Math.abs(xC - bob.getxC()) < World.sizeInX && Math.abs(yC
                - bob.getyC()) < World.sizeInY);
        }
    }


    /**
     * Checks the distance between the current bomb and the bots
     *
     * @param bot
     *            BOT
     * @return if bot is in bomb's explosion
     */
    public boolean distance(Bot bot)
    {
        if (xC == bot.getxC() && yC == bot.getyC())
        {
            return true;
        }
        else if (xC == bot.getxC())
        {
            return (Math.abs(yC - bot.getyC()) < World.sizeInY);
        }
        else if (yC == bot.getyC())
        {
            return (Math.abs(xC - bot.getxC()) < World.sizeInX);
        }
        else
        {
            return (Math.abs(xC - bot.getxC()) < World.sizeInX && Math.abs(yC
                - bot.getyC()) < World.sizeInY);
        }
    }

}
