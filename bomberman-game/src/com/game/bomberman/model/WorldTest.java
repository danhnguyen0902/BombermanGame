package com.game.bomberman.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.game.bomberman.model.Button.Keys;
import com.game.bomberman.model.Bob.Direction;
import com.game.bomberman.model.Block.State;
import junit.framework.TestCase;

// -------------------------------------------------------------------------
/**
 * Test class for world. Due to the nature of the game, this test class only
 * tests the initial conditions of the following classes: World, Bob, Block. Not
 * possible to test all possible case without actually running the game. Not
 * possible to test using GDX framework once the game is running.
 *
 * @author Ching Hsiang Chen (chingyg)
 * @author Kyle He (kyleh8)
 * @author Danh Nguyen (danh0902)
 * @version Dec 8, 2013
 */
public class WorldTest
    extends TestCase
{

    private Bob       dummyBob;
    private World     testWorld;
    private int       numBots;
    private int       rows;
    private int       columns;
    private Block[][] testBlock;
    private Vector2   acceleration;
    private Vector2   position;
    private Vector2   velocity;

    private Bot[]     bots;

    private Rectangle bounds;

    private float     stateTime;


    public void setUp()
    {
        testWorld = new World();

    }


    // ----------------------------------------------------------
    /**
     * Test initial conditions of world
     */
    public void testWorldAttributes()
    {

        // test number of bots
        numBots = testWorld.getNumBots();
        assertEquals(5, numBots);

        // rows and columns
        rows = testWorld.getNumRows();
        columns = testWorld.getNumColumns();

        assertEquals(20, rows);
        assertEquals(20, columns);

        // Test for walls
        testBlock = testWorld.getBlock();

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {

                if (i % 2 == 1 && j % 2 == 1)
                {

                    assertEquals(State.WALL, testBlock[i][j].getState());
                }

            }
        }

        // Test num of bombs
        assertEquals(1, testWorld.getNumBombs());

    }


    // ----------------------------------------------------------
    /**
     * Tests getButton method
     */
    public void testGetButton()
    {
        Button left = new Button(new Vector2(0f, 0.75f));
        Button right = new Button(new Vector2(2f, 0.75f));
        Button up = new Button(new Vector2(1f, 1.5f));
        Button down = new Button(new Vector2(1f, 0f));
        Button placeBomb = new Button(new Vector2(11, 0.5f));
        Button pause = new Button(new Vector2(6.5f, 0.5f));

        assertEquals(placeBomb.getPosition(), testWorld.getButton(Keys.BOMB)
            .getPosition());

        assertEquals(down.getPosition(), testWorld.getButton(Keys.DOWN)
            .getPosition());
        assertEquals(left.getPosition(), testWorld.getButton(Keys.LEFT)
            .getPosition());
        assertEquals(pause.getPosition(), testWorld.getButton(Keys.PAUSE)
            .getPosition());
        assertEquals(right.getPosition(), testWorld.getButton(Keys.RIGHT)
            .getPosition());
        assertEquals(up.getPosition(), testWorld.getButton(Keys.UP)
            .getPosition());
    }


    // ----------------------------------------------------------
    /**
     * Tests createWorld method
     */
    public void testCreatWorld()
    {

        // testing for bot positions
        numBots = 5;
        bots = new Bot[numBots];
        bots[0] = new Bot(new Vector2(5, 8));
        bots[1] = new Bot(new Vector2(6, 9));
        bots[2] = new Bot(new Vector2(7, 10));
        bots[3] = new Bot(new Vector2(5, 11));
        bots[4] = new Bot(new Vector2(4, 7));

        Bot[] testBotFromWorld = testWorld.getBot();
        assertEquals(bots[0].getPosition(), testBotFromWorld[0].getPosition());
        assertEquals(bots[1].getPosition(), testBotFromWorld[1].getPosition());
        assertEquals(bots[2].getPosition(), testBotFromWorld[2].getPosition());
        assertEquals(bots[3].getPosition(), testBotFromWorld[3].getPosition());
        assertEquals(bots[4].getPosition(), testBotFromWorld[4].getPosition());

        // test for Block positions
        testBlock = testWorld.getBlock();
        Vector2 tempPos;
        float x;
        float y = World.Y_BOTTOM_RIGHT;
        rows = testWorld.getNumRows();
        columns = testWorld.getNumColumns();

        for (int i = 0; i < rows; ++i)
        {
            x = World.X_TOP_LEFT;
            for (int j = 0; j < columns; ++j)
            {
                tempPos = new Vector2(x, y);
                assertEquals(tempPos, testBlock[i][j].getPosition());

                x += 0.5f;
            }
            y += 0.5f;
        }

        // Test for walls

        int numOfDestroyedWalls = 0;
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                if (testBlock[i][j].getState() == State.DESTROY_WALL)
                {
                    numOfDestroyedWalls++;
                }
            }
        }

        assertTrue(numOfDestroyedWalls > 30);
    }


    // ========================================================================
    // ========================================================================
    // NOW TESTING FOR BOB
    /**
     * Test initial conditions of Bob
     */
    public void testBob()
    {
        position = new Vector2(2f, 11.5f);
        acceleration = new Vector2();
        velocity = new Vector2();
        bounds = new Rectangle();
        stateTime = 0;

        // --------------------------------------------------------------------
        // ***GETTERS***

        // Test initial conditions
        assertEquals(0.0, testWorld.getBob().getX1(), .001);
        assertEquals(0.0, testWorld.getBob().getX2(), .001);
        assertEquals(0.0, testWorld.getBob().getY1(), .001);
        assertEquals(0.0, testWorld.getBob().getY2(), .001);
        assertEquals(0.0, testWorld.getBob().getyC(), .001);
        assertEquals(0.0, testWorld.getBob().getyC(), .001);

        // Test for enum state
        assertEquals(Direction.DOWN, testWorld.getBob().getFacing());
        assertEquals(Bob.State.IDLE, testWorld.getBob().getState());

        // Test for position, acceleration, velocity
        assertEquals(position, testWorld.getBob().getPosition());
        assertEquals(acceleration, testWorld.getBob().getAcceleration());
        assertEquals(velocity, testWorld.getBob().getVelocity());

        // height and width
        bounds.height = 0.5f;
        bounds.width = 0.5f;

        assertEquals(bounds.height, testWorld.getBob().bounds.height);
        assertEquals(bounds.width, testWorld.getBob().bounds.width);

        // stateTime
        assertEquals(stateTime, testWorld.getBob().getStateTime());
        // --------------------------------------------------------------------

        testWorld.getBob().getFlag();
    }


    // ------------------------------------------------------------------------
    // SETTERS
    // ----------------------------------------------------------
    /**
     * Test Bob constructor
     */
    public void testBobConstructor()
    {
        position = new Vector2(4f, 71.5f);
        dummyBob = new Bob(position);

        Vector2 tempPos = position;

        assertEquals(tempPos, dummyBob.getPosition());
    }


    // ----------------------------------------------------------
    /**
     * Test Bob's setxC method
     */
    public void testBobSetxC()
    {
        testWorld.getBob().setxC(5);
        assertEquals(5, testWorld.getBob().getxC(), .001);
    }


    // ----------------------------------------------------------
    /**
     * Tests Bob's setyC method
     */
    public void testBobSetyC()
    {
        testWorld.getBob().setyC(5);
        assertEquals(5, testWorld.getBob().getyC(), .001);
    }


    // ----------------------------------------------------------
    /**
     * Tests Bob's setFacing method
     */
    public void testSetFacing()
    {
        testWorld.getBob().setFacing(Direction.RIGHT);
        assertEquals(Direction.RIGHT, testWorld.getBob().getFacing());
    }


    // ----------------------------------------------------------
    /**
     * Test Bob's setState Method
     */
    public void testSetState()
    {
        testWorld.getBob().setState(Bob.State.DYING);
        assertEquals(Bob.State.DYING, testWorld.getBob().getState());
    }


    // ----------------------------------------------------------
    /**
     * Test Bob's setFlag Method
     */
    public void testSetFlag()
    {
        testWorld.getBob().setFlag(1);
        assertEquals(1, testWorld.getBob().getFlag());
    }


    // ----------------------------------------------------------
    /**
     * Test Bob's update method
     */
    public void testUpdate()
    {
        position = new Vector2(2f, 11.5f);

        testWorld.getBob().update(3);
        assertEquals(3, testWorld.getBob().getStateTime(), .001);
        assertEquals(position, testWorld.getBob().getPosition());
    }


    // ----------------------------------------------------------
    /**
     * Test Bob's find method
     */
    public void testFind()
    {

        testWorld.getBob().find();
        assertEquals(0.0, testWorld.getBob().getX1(), .001);
        assertEquals(0.0, testWorld.getBob().getX2(), .001);
        assertEquals(0.0, testWorld.getBob().getxC(), .001);
        assertEquals(0.0, testWorld.getBob().getY1(), .001);
        assertEquals(0.0, testWorld.getBob().getY2(), .001);
        assertEquals(0.0, testWorld.getBob().getyC(), .001);
    }


    // ----------------------------------------------------------
    /**
     * Test Bob's fit method
     */
    public void testFit()
    {
        position = new Vector2(2f, 11.5f);
        dummyBob = new Bob(position);

        dummyBob.fit(testWorld);
        assertEquals(position, testWorld.getBob().getPosition());
    }


    // ----------------------------------------------------------
    /**
     * Tests Bob's findCell method.
     */
    public void testFindCell()
    {
        position = new Vector2(2f, 11.5f);
        dummyBob = new Bob(position);

        assertEquals(0, dummyBob.findCell(1, testWorld));
        assertEquals(0, dummyBob.findCell(2, testWorld));

    }


    // ----------------------------------------------------------
    /**
     * Test Bob's check method
     */
    public void testCheck()
    {
        position = new Vector2(2f, 11.5f);
        dummyBob = new Bob(position);

        assertTrue(dummyBob.check(testWorld));
    }


    // ----------------------------------------------------------
    /**
     * test Bob's distance method
     */
    public void testDistance()
    {
        Bot[] bots1 = testWorld.getBot();

        assertTrue(testWorld.getBob().distance(bots1[0]));
        assertTrue(testWorld.getBob().distance(bots1[1]));
        assertTrue(testWorld.getBob().distance(bots1[2]));
        assertTrue(testWorld.getBob().distance(bots1[3]));
        assertTrue(testWorld.getBob().distance(bots1[4]));

    }

}