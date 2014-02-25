    package com.game.bomberman.controller;

import com.badlogic.gdx.utils.TimeUtils;
import com.game.bomberman.model.Bomb;
import com.game.bomberman.model.Block;
import com.game.bomberman.view.WorldRenderer;
import com.game.bomberman.model.Bob;
import com.game.bomberman.model.Bot;
import com.game.bomberman.model.Button;
import com.game.bomberman.model.World;
import com.game.bomberman.model.Bob.Direction;
import com.game.bomberman.model.Bob.State;
import com.game.bomberman.model.Button.Keys;
import com.badlogic.gdx.math.Vector2;

// -------------------------------------------------------------------------
/**
 * The controller class for the project.
 */
/**
 * @author danh0902
 * @author chingyg
 * @author kyleh8
 * @version 2013.12.8
 */
public class WorldController
{

    private static final float error          = 0.2f;
    private static final int[] xRand          = { 0, 1, 0, -1 };
    private static final int[] yRand          = { -1, 0, 1, 0 };

    private World              world;
    private Bob                bob;
    private Bot[]              bot;
    private int                numBots;
    private Bomb[]             bomb;
    private int                numBombs;
    private int                numBombsPlaced = 0;

    private int                flag           = 0;

    // All of the followings are used as copies
    private Bot                tmpBot;
    private Bob                tmpBob;


    // ----------------------------------------------------------
    /**
     * Create a new WorldController object.
     *
     * @param world
     *            The World class
     */
    public WorldController(World world)
    {
        this.world = world;
        this.bob = world.getBob();
        this.bot = world.getBot();
        this.numBots = world.getNumBots();
        this.bomb = world.getBomb();
        this.numBombs = world.getNumBombs();
    }


    // ** Key presses and touches **************** //

    // ----------------------------------------------------------
    /**
     * Left button pressed
     */
    public void leftPressed()
    {
        Button.keys.get(Button.keys.put(Keys.LEFT, true));
    }


    // ----------------------------------------------------------
    /**
     * Right button pressed
     */
    public void rightPressed()
    {
        Button.keys.get(Button.keys.put(Keys.RIGHT, true));
    }


    // ----------------------------------------------------------
    /**
     * Up button pressed
     */
    public void upPressed()
    {
        Button.keys.get(Button.keys.put(Keys.UP, true));
    }


    // ----------------------------------------------------------
    /**
     * Down button pressed
     */
    public void downPressed()
    {
        Button.keys.get(Button.keys.put(Keys.DOWN, true));
    }


    // ----------------------------------------------------------
    /**
     * Bomb button pressed
     */
    public void bombPressed()
    {
        Button.keys.get(Button.keys.put(Keys.BOMB, true));
    }


    // ----------------------------------------------------------
    /**
     * Pause button pressed
     */
    public void pausePressed()
    {
        Button.keys.get(Button.keys.put(Keys.PAUSE, true));
    }


    // ----------------------------------------------------------
    /**
     * Left button Released
     */
    public void leftReleased()
    {
        Button.keys.get(Button.keys.put(Keys.LEFT, false));
    }


    // ----------------------------------------------------------
    /**
     * Right button released
     */
    public void rightReleased()
    {
        Button.keys.get(Button.keys.put(Keys.RIGHT, false));
    }


    // ----------------------------------------------------------
    /**
     * Up button Released
     */
    public void upReleased()
    {
        Button.keys.get(Button.keys.put(Keys.UP, false));
    }


    // ----------------------------------------------------------
    /**
     * Down button released
     */
    public void downReleased()
    {
        Button.keys.get(Button.keys.put(Keys.DOWN, false));
    }


    // ----------------------------------------------------------
    /**
     * Bomb button released
     */
    public void bombReleased()
    {
        Button.keys.get(Button.keys.put(Keys.BOMB, false));
    }


    // ----------------------------------------------------------
    /**
     * Pause button released
     */
    public void pauseReleased()
    {
        Button.keys.get(Button.keys.put(Keys.PAUSE, false));
    }


    // ----------------------------------------------------------
    /**
     * Handle the bots' locations
     *
     * @param i
     *            bot number
     * @param delta
     *            float
     */
    public void botCalc(int i, float delta)
    {
        tmpBot = new Bot(new Vector2(-1, -1));
        tmpBot.setFlag(bot[i].getFlag());
        tmpBot.getPosition().x = bot[i].getPosition().x;
        tmpBot.getPosition().y = bot[i].getPosition().y;
        tmpBot.getPosition().add(
            bot[i].getVelocity().x * delta,
            bot[i].getVelocity().y * delta);

        float x1 = tmpBot.getPosition().x * WorldRenderer.ppuX;
        float x2 = x1 + Bot.SIZE * WorldRenderer.ppuX;
        float y2 =
            WorldRenderer.height - tmpBot.getPosition().y * WorldRenderer.ppuY;
        float y1 = y2 - Bot.SIZE * WorldRenderer.ppuY;

        tmpBot.setxC((x1 + x2) / 2);
        tmpBot.setyC((y1 + y2) / 2);
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     *
     * @param delta
     */
    public void bobCalc(float delta)
    {
        tmpBob = new Bob(new Vector2(-1, -1));
        tmpBob.setFlag(bob.getFlag());
        tmpBob.getPosition().x = bob.getPosition().x;
        tmpBob.getPosition().y = bob.getPosition().y;
        tmpBob.getPosition().add(
            bob.getVelocity().x * delta,
            bob.getVelocity().y * delta);

        float x1 = tmpBob.getPosition().x * WorldRenderer.ppuX;
        float x2 = x1 + Bot.SIZE * WorldRenderer.ppuX;
        float y2 =
            WorldRenderer.height - tmpBob.getPosition().y * WorldRenderer.ppuY;
        float y1 = y2 - Bot.SIZE * WorldRenderer.ppuY;

        tmpBob.setxC((x1 + x2) / 2);
        tmpBob.setyC((y1 + y2) / 2);
    }


    /** The main update method
     * @param delta **/
    public void update(float delta)
    {
        Block[][] block = world.getBlock();

        // bob
        if (bob.getState() == State.DYING)
        {
            bob.update(delta);
        }
        else
        {
            processBob(delta);
            bob.update(delta);

            int tmp = bob.findCell(1, world);
            if (tmp == -1)
            {
                tmp = bob.findCell(2, world);
            }

            for (int k = 0; k < numBots; ++k)
            {
                if (bot[k].getState() == Bot.State.DYING
                    || bot[k].getState() == Bot.State.DEAD)
                {
                    continue;
                }

                tmp = bot[k].findCell(1, world);
                if (tmp == -1)
                {
                    tmp = bot[k].findCell(2, world);
                }

                if (bob.distance(bot[k]))
                {
                    bob.setState(State.DYING);
                    bob.getVelocity().x = 0;
                    bob.getVelocity().y = 0;
                    bob.update(delta);
                    bob.setStart(TimeUtils.millis());
                }
            }

            // pick up items
            switch (block[Bob.row][Bob.col].getState())
            {
                case SPEED:
                    Bob.SPEED += World.speed.getReward();
                    block[Bob.row][Bob.col].setState(Block.State.EMPTY);
                    break;
                case LONGER_BOMB:
                    Bomb.length += World.longerBomb.getReward();
                    block[Bob.row][Bob.col].setState(Block.State.EMPTY);
                    break;
                // case ADD_BOMB:
                // numBombs += World.addBomb.getReward();
                // block[Bob.row][Bob.col].setState(Block.State.EMPTY);
                // break;
                case DOOR:
                    Bob.VICTORY = true;
                    break;
            }
        }

        // bombs
        processBombs(delta);

        // blocks
        Block.stateTime += delta;

        // bots
        processBots(delta);
    }


    /**
     * Handle the events related to bomb
     *
     * @param delta
     *            float
     */
    private void processBombs(float delta)
    {
        if (Button.keys.get(Keys.BOMB) && numBombsPlaced < numBombs)
        {
            ++numBombsPlaced;
            for (int i = 0; i < numBombs; ++i)
            {
                bomb[i].setState(Bomb.State.IDLE);
                bomb[i].setStart(TimeUtils.millis());
                bomb[i].update(delta);
                bomb[i].setPosition(bob.getPosition());
                bomb[i].fit(world);

                // bob
                if (bomb[i].distance(bob))
                {
                    bob.setFlag(1);
                }

                // bots
                for (int j = 0; j < numBots; ++j)
                {
                    if (bot[j].getState() == Bot.State.DYING
                        || bot[j].getState() == Bot.State.DEAD)
                    {
                        continue;
                    }

                    if (bomb[i].distance(bot[j]))
                    {
                        bot[j].setFlag(1);
                    }
                }
            }
        }
        else
        {
            for (int i = 0; i < numBombs; ++i)
            {
                bomb[i].update(delta);
            }
        }

        for (int i = 0; i < numBombs; ++i)
        {
            // bob
            if (!bomb[i].distance(bob))
            {
                bob.setFlag(0);
            }

            // bots
            for (int j = 0; j < numBots; ++j)
            {
                if (bot[j].getState() == Bot.State.DYING
                    || bot[j].getState() == Bot.State.DEAD)
                {
                    continue;
                }

                if (!bomb[i].distance(bot[j]))
                {
                    bot[j].setFlag(0);
                }
            }

            // explode
            if (TimeUtils.millis() - bomb[i].getStart() >= 3000)
            {
                bomb[i].setState(Bomb.State.EXPLODE);
                bomb[i].setStart(9000000000000000000L);
                bomb[i].setStart2(TimeUtils.millis());

                bomb[i].setExplode(bomb[i].getRow(), bomb[i].getCol(), world);
            }
            else
            {
                if (bomb[i].getState() == Bomb.State.EXPLODE)
                {
                    if (TimeUtils.millis() - bomb[i].getStart2() >= 2520)
                    {
                        bomb[i].setStart2(9000000000000000000L);

                        bomb[i].setState(Bomb.State.NOTHING);
                        bomb[i].setUnExplode(
                            bomb[i].getRow(),
                            bomb[i].getCol(),
                            world);

                        numBombsPlaced = 0;
                        Block.countExplodeBlocks = 0;
                    }
                    else
                    {
                        int tmp = bob.findCell(1, world);
                        if (tmp == -1)
                        {
                            tmp = bob.findCell(2, world);
                        }

                        for (int j = 0; j < Block.countExplodeBlocks; ++j)
                        {
                            if (Bob.row == Block.explodedBlockX[j]
                                && Bob.col == Block.explodedBlockY[j])
                            {
                                bob.setState(State.DYING);
                                bob.getVelocity().x = 0;
                                bob.getVelocity().y = 0;
                                bob.update(delta);
                                bob.setStart(TimeUtils.millis());
                            }

                            for (int k = 0; k < numBots; ++k)
                            {
                                if (bot[k].getState() == Bot.State.DYING
                                    || bot[k].getState() == Bot.State.DEAD)
                                {
                                    continue;
                                }

                                tmp = bot[k].findCell(1, world);
                                if (tmp == -1)
                                {
                                    tmp = bot[k].findCell(2, world);
                                }

                                if (bot[k].getRow() == Block.explodedBlockX[j]
                                    && bot[k].getCol() == Block.explodedBlockY[j])
                                {
                                    bot[k].setState(Bot.State.DYING);
                                    bot[k].getVelocity().x = 0;
                                    bot[k].getVelocity().y = 0;
                                    bot[k].update(delta);
                                    bot[k].setStart(TimeUtils.millis());
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    private void processBots(float delta)
    {
        for (int i = 0; i < numBots; ++i)
        {
            if (bot[i].getState() == Bot.State.DYING)
            {
                bot[i].update(delta);
                if (TimeUtils.millis() - bot[i].getStart() >= 3000)
                {
                    bot[i].setState(Bot.State.DEAD);
                }
                continue;
            }

            if (bot[i].getState() == Bot.State.DEAD)
            {
                continue;
            }

            botCalc(i, delta);
            int tmp = bot[i].getTimes();
            boolean check = tmpBot.check(world);

            if (tmp == 0 || tmp == Bot.TIMES || !check)
            {
                if (tmp == 0)
                {
                    bot[i].setTimes(1);
                }

                if (tmp == Bot.TIMES)
                {
                    bot[i].setTimes(0);
                }

                if (!check)
                {
                    bot[i].fit(world);
                }

                int rand = 0;
                while (true)
                {
                    int count = 0;
                    for (int j = 0; j < 4; ++j)
                    {
                        bot[i].getVelocity().x = xRand[j] * Bot.SPEED;
                        bot[i].getVelocity().y = yRand[j] * Bot.SPEED;

                        botCalc(i, delta);

                        if (!tmpBot.check(world))
                        {
                            ++count;
                        }
                    }

                    if (count == 4)
                    {
                        bot[i].fit(world);
                        break;
                    }

                    rand = (int)(Math.random() * 4);
                    bot[i].getVelocity().x = xRand[rand] * Bot.SPEED;
                    bot[i].getVelocity().y = yRand[rand] * Bot.SPEED;

                    botCalc(i, delta);

                    if (tmpBot.check(world)) // if
// the
// bot will move in the right way
                    {
                        // Stay on the same way
                        if ((bot[i].getFacing() == Bot.Direction.UP || bot[i]
                            .getFacing() == Bot.Direction.DOWN)
                            && (rand == 0 || rand == 2))
                        {
                            break;
                        }

                        if ((bot[i].getFacing() == Bot.Direction.LEFT || bot[i]
                            .getFacing() == Bot.Direction.RIGHT)
                            && (rand == 1 || rand == 3))
                        {
                            break;
                        }

                        // Change direction
                        int p = bot[i].findCell(1, world);
                        if (p != -1)
                        {
                            float dist = bot[i].getMin() / WorldRenderer.ppuX;
                            if (0 < dist && dist <= error)
                            {
                                bot[i].fit(world);
                                break;
                            }
                        }

                        p = bot[i].findCell(2, world);
                        float dist = bot[i].getMin() / WorldRenderer.ppuY;
                        if (0 < dist && dist <= error)
                        {
                            bot[i].fit(world);
                            break;
                        }
                    }
                    else
                    {
                        bot[i].fit(world);
                    }
                }

                switch (rand)
                {
                    case 0:
                        bot[i].setFacing(Bot.Direction.DOWN);
                        break;
                    case 1:
                        bot[i].setFacing(Bot.Direction.RIGHT);
                        break;
                    case 2:
                        bot[i].setFacing(Bot.Direction.UP);
                        break;
                    case 3:
                        bot[i].setFacing(Bot.Direction.LEFT);
                        break;
                }
            }
            else
            // if the bot is still on the right track
            {
                bot[i].setTimes(tmp + 1);
            }

            bot[i].update(delta);
        }

    }


    /**
     * Change Bob's state and parameters based on input controls
     *
     * @param delta
     *            float
     */
    private void processBob(float delta)
    {
        int count = 0;
        int dir = 0;

        switch (bob.getFacing())
        {
            case LEFT:
                dir = 3;
                break;
            case RIGHT:
                dir = 1;
                break;
            case UP:
                dir = 2;
                break;
            case DOWN:
                dir = 0;
                break;
        }

        if (Button.keys.get(Keys.LEFT))
        {
            bob.setFacing(Direction.LEFT);
            bob.setState(State.WALKING);
            bob.getVelocity().x = -Bob.SPEED;
            bob.getVelocity().y = 0f;

            ++count;
        }

        if (Button.keys.get(Keys.RIGHT))
        {
            bob.setFacing(Direction.RIGHT);
            bob.setState(State.WALKING);
            bob.getVelocity().x = Bob.SPEED;
            bob.getVelocity().y = 0f;

            ++count;
        }

        if (Button.keys.get(Keys.UP))
        {
            bob.setFacing(Direction.UP);
            bob.setState(State.WALKING);
            bob.getVelocity().x = 0f;
            bob.getVelocity().y = Bob.SPEED;

            ++count;
        }

        if (Button.keys.get(Keys.DOWN))
        {
            bob.setFacing(Direction.DOWN);
            bob.setState(State.WALKING);
            bob.getVelocity().x = 0f;
            bob.getVelocity().y = -Bob.SPEED;

            ++count;
        }

        // need to check the bounds
        bobCalc(delta);

        if (tmpBob.check(world))
        {
            // Stay on the same direction
            if ((bob.getFacing() == Bob.Direction.UP || bob.getFacing() == Bob.Direction.DOWN)
                && (dir == 0 || dir == 2))
            {
                if (flag == 1)
                {
                    bob.getVelocity().x = 0;
                    bob.getVelocity().y = 0;
                }
            }
            else if ((bob.getFacing() == Bob.Direction.LEFT || bob.getFacing() == Bob.Direction.RIGHT)
                && (dir == 1 || dir == 3))
            {
                if (flag == 1)
                {
                    bob.getVelocity().x = 0;
                    bob.getVelocity().y = 0;
                }
            }
            else
            // Change direction
            {
                if (flag == 1)
                {
                    flag = 0;
                }
                else
                {
                    int p = bob.findCell(1, world);
                    if (p != -1)
                    {
                        float dist = Bob.min / WorldRenderer.ppuX;
                        if (dist <= error)
                        {
                            if (dist > 0)
                            {
                                bob.fit(world);
                            }
                        }
                        else
                        {
                            bob.getVelocity().x = 0;
                            bob.getVelocity().y = 0;
                            flag = 1;
                        }
                    }
                    else
                    {
                        p = bob.findCell(2, world);
                        float dist = Bob.min / WorldRenderer.ppuY;
                        if (dist <= error)
                        {
                            if (dist > 0)
                            {
                                bob.fit(world);
                            }
                        }
                        else
                        {
                            bob.getVelocity().x = 0;
                            bob.getVelocity().y = 0;
                            flag = 1;
                        }
                    }

                }
            }
        }
        else
        {
            bob.fit(world);
        }

        // need to check if multiple or none direction is pressed, then Bob is
// idle
        if (count == 0 || count >= 2)
        {
            bob.setState(State.IDLE);
            // acceleration is 0 on the x and y
            bob.getAcceleration().x = 0;
            bob.getAcceleration().y = 0;
            // speed is 0
            bob.getVelocity().x = 0;
            bob.getVelocity().y = 0;
        }
    }
}
