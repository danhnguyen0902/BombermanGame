package com.game.bomberman.model;

import com.game.bomberman.view.WorldRenderer;
import com.game.bomberman.model.Block.State;
import com.game.bomberman.model.Button.Keys;
import com.badlogic.gdx.math.Vector2;

// -------------------------------------------------------------------------
/**
 * The general model
 *
 * @author DANH_NGUYEN
 * @version Dec 8, 2013
 */
public class World
{

    /** The blocks making up the world **/
    private int               numRows        = 20;
    private int               numColumns     = 20;
    /**
     * The board game
     */
    Block[][]                 block          = new Block[numRows][numColumns];
    /** Our player controlled hero **/
    Bob                       bob;
    /** The bots **/
    Bot[]                     bot;
    /**
     * The amount of the bots
     */
    int                       numBots;
    /** The bombs **/
    Bomb[]                    bomb;
    /**
     * The amount of the bombs
     */
    int                       numBombs;
    /** The buttons **/
    Button                    left;
    /**
     * Right button
     */
    Button                    right;
    /**
     * Up button
     */
    Button                    up;
    /**
     * Down button
     */
    Button                    down;
    /**
     * Place bomb button
     */
    Button                    placeBomb;
    /**
     * Pause button
     */
    Button                    pause;

    /**
     * The x coordinate of the top left
     */
    public static final float X_TOP_LEFT     = 2f;
    /**
     * The y coordinate of top left
     */
    public static final float Y_TOP_LEFT     = 11.5f;
    /**
     * The x coordinate of bottom right
     */
    public static final float X_BOTTOM_RIGHT = 11.5f;
    /**
     * The y coordinate of bottom right
     */
    public static final float Y_BOTTOM_RIGHT = 2f;

    /**
     * Size of the block in pixels
     */
    public static float       sizeInX        = 0;
    /**
     * Size of the block in pixels
     */
    public static float       sizeInY        = 0;

    private Vector2           heart;
    private int               lives          = 3;
    private Vector2           clock;
    private Vector2           bobImg;

    /**
     * The speed item
     */
    public static Item        speed;
    /**
     * The item that makes the bomb longer
     */
    public static Item        longerBomb;
    // public static Item addBomb;

    /**
     * The x coordinate of the door
     */
    public static int         doorX;
    /**
     * The y coordinate of the door
     */
    public static int         doorY;


    // ----------------------------------------------------------
    /**
     * Set the lives to the given value
     *
     * @param lives
     *            the given value
     */
    public void setLives(int lives)
    {
        this.lives = lives;
    }


    // ----------------------------------------------------------
    /**
     * Gets the lives
     *
     * @return lives
     */
    public int getLives()
    {
        return lives;
    }


    // ----------------------------------------------------------
    /**
     * Gets the bob's image
     *
     * @return the image
     */
    public Vector2 getBobImg()
    {
        return bobImg;
    }


    // ----------------------------------------------------------
    /**
     * Gets the heart image
     *
     * @return the heart image
     */
    public Vector2 getHeart()
    {
        return heart;
    }


    // ----------------------------------------------------------
    /**
     * Gets the clock image
     *
     * @return the clock image
     */
    public Vector2 getClock()
    {
        return clock;
    }


    // ----------------------------------------------------------
    /**
     * Gets the block
     *
     * @return the block
     */
    public Block[][] getBlock()
    {
        return block;
    }


    // ----------------------------------------------------------
    /**
     * Gets the number of rows
     *
     * @return the number of rows
     */
    public int getNumRows()
    {
        return numRows;
    }


    /**
     * Gets the number of columns
     *
     * @return the number of columns
     */
    public int getNumColumns()
    {
        return numColumns;
    }


    // ----------------------------------------------------------
    /**
     * Gets bob object
     *
     * @return bob
     */
    public Bob getBob()
    {
        return bob;
    }


    // ----------------------------------------------------------
    /**
     * Gets the bot object
     *
     * @return bot object
     */
    public Bot[] getBot()
    {
        return bot;
    }


    // ----------------------------------------------------------
    /**
     * Gets the number of bots
     *
     * @return the number of bots
     */
    public int getNumBots()
    {
        return numBots;
    }


    // ----------------------------------------------------------
    /**
     * Gets the bomb object
     *
     * @return the bomb
     */
    public Bomb[] getBomb()
    {
        return bomb;
    }


    // ----------------------------------------------------------
    /**
     * Gets the number of bombs
     *
     * @return the number of bombs
     */
    public int getNumBombs()
    {
        return numBombs;
    }


    // ----------------------------------------------------------
    /**
     * Sets the number of bots to the given value
     *
     * @param numBombs
     *            the given value
     */
    public void setNumBombs(int numBombs)
    {
        this.numBombs = numBombs;
    }


    // ----------------------------------------------------------
    /**
     * Returns the kind of button needed
     *
     * @param button
     *            the kind of button
     * @return the button
     */
    public Button getButton(Keys button)
    {
        switch (button)
        {
            case LEFT:
                return left;
            case RIGHT:
                return right;
            case UP:
                return up;
            case DOWN:
                return down;
            case BOMB:
                return placeBomb;
            case PAUSE:
                return pause;
        }

        return null;
    }


    // ----------------------------------------------------------
    /**
     * Create a new World object.
     */
    public World()
    {
        createWorld();
    }


    private void createWorld()
    {
        // bob
        bob = new Bob(new Vector2(X_TOP_LEFT, Y_TOP_LEFT));

        // bots
        numBots = 5;
        bot = new Bot[numBots];
        bot[0] = new Bot(new Vector2(5, 8));
        bot[1] = new Bot(new Vector2(6, 9));
        bot[2] = new Bot(new Vector2(7, 10));
        bot[3] = new Bot(new Vector2(5, 11));
        bot[4] = new Bot(new Vector2(4, 7));

        // blocks
        float x;
        float y = Y_BOTTOM_RIGHT;

        for (int i = 0; i < numRows; ++i)
        {
            x = X_TOP_LEFT;
            for (int j = 0; j < numColumns; ++j)
            {
                block[i][j] = new Block(new Vector2(x, y));
                x += 0.5f;
            }
            y += 0.5f;
        }

        setWalls();

        // buttons
        left = new Button(new Vector2(0f, 0.75f));
        right = new Button(new Vector2(2f, 0.75f));
        up = new Button(new Vector2(1f, 1.5f));
        down = new Button(new Vector2(1f, 0f));
        placeBomb = new Button(new Vector2(11, 0.5f));
        pause = new Button(new Vector2(6.5f, 0.5f));

        // bombs
        numBombs = 1;
        bomb = new Bomb[numBombs];
        bomb[0] = new Bomb(new Vector2(-1, -1));

        // items:
        speed = new Item(0.2f);
        longerBomb = new Item(1);
        // addBomb = new Item(1);

        // graphics
        heart = new Vector2(0.5f, Y_TOP_LEFT);
        clock = new Vector2(0, Y_TOP_LEFT - 1);
        bobImg = new Vector2(0, Y_TOP_LEFT);
    }


    // ----------------------------------------------------------
    /**
     * Find positions of all objects
     */
    public void findPositions()
    {
        // buttons
        left.find();
        right.find();
        up.find();
        down.find();
        placeBomb.find();
        pause.find();

        // blocks
        for (int i = 0; i < numRows; ++i)
        {
            for (int j = 0; j < numColumns; ++j)
            {
                block[i][j].find();
            }
        }

        // bots
        for (int i = 0; i < numBots; ++i)
        {
            bot[i].find();
        }

        // bob
        bob.find();

        // initialize:
        sizeInX = Block.SIZE * WorldRenderer.ppuX;
        sizeInY = Block.SIZE * WorldRenderer.ppuY;
    }


    // ----------------------------------------------------------
    /**
     * Set the walls to the block
     */
    public void setWalls()
    {
        int[] tmpX = new int[500];
        int[] tmpY = new int[500];
        int count = 0;

        for (int i = 0; i < numRows; ++i)
        {
            for (int j = 0; j < numColumns; ++j)
            {
                if (i % 2 == 1 && j % 2 == 1)
                {
                    block[i][j].setState(State.WALL);
                }
                else
                {
                    if ((i == 18 && (j == 0 || j == 1)) || (i == 19 && j == 0))
                    {
                        continue;
                    }

                    block[i][j].setState(State.EMPTY);
                    tmpX[count] = i;
                    tmpY[count] = j;
                    ++count;
                }
            }
        }

        int num = 50;
        int rand = 0;
        int flag = 0;
        for (int i = 1; i <= num; ++i)
        {
            rand = (int)(Math.random() * count);

            block[tmpX[rand]][tmpY[rand]].setState(State.DESTROY_WALL);

            if (flag == 0)
            {
                doorX = tmpX[rand];
                doorY = tmpY[rand];
                flag = 1;
            }

            ++count;
        }
    }
}
