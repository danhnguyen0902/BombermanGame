package com.game.bomberman.view;

import com.badlogic.gdx.math.Vector2;
import com.game.bomberman.model.Bomb;
import com.game.bomberman.model.Block;
import com.game.bomberman.model.Bob;
import com.game.bomberman.model.Bot;
import com.game.bomberman.model.Button;
import com.game.bomberman.model.World;
import com.game.bomberman.model.Bob.State;
import com.game.bomberman.model.Button.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

// -------------------------------------------------------------------------
/**
 * Render the world and characters, objects
 *
 * @author danh0902
 * @author chingyg
 * @author kyleh8
 * @version 2013.12.8
 */
public class WorldRenderer
{

    private static final float CAMERA_WIDTH                   = 12f;
    private static final float CAMERA_HEIGHT                  = 12f;
    private static final float BOB_RUNNING_FRAME_DURATION     = 0.11f;
    private static final float BOB_DYING_FRAME_DURATION       = 1f;
    private static final float BOT_RUNNING_FRAME_DURATION     = 0.2f;
    private static final float WALL_DESTROYING_FRAME_DURATION = 1.2f;
    private static final float BOMB_IDLE_FRAME_DURATION       = 0.2f;
    private static final float BOMB_EXPLODE_FRAME_DURATION    = 0.14f;
    private static final float BOT_DYING_FRAME_DURATION       = 0.5f;

    private World              world;
    private OrthographicCamera cam;

    /** for debug rendering **/
    ShapeRenderer              debugRenderer                  =
                                                                  new ShapeRenderer();

    /** Textures **/
    private TextureRegion      bobIdleLeft;
    private TextureRegion      bobIdleRight;
    private TextureRegion      bobIdleUp;
    private TextureRegion      bobIdleDown;
    private TextureRegion      blockTexture;
    private TextureRegion      bobFrame;

    private TextureRegion      botIdleLeft;
    private TextureRegion      botIdleRight;
    private TextureRegion      botFrame;

    private TextureRegion      leftReleased;
    private TextureRegion      leftPressed;
    private TextureRegion      rightReleased;
    private TextureRegion      rightPressed;
    private TextureRegion      upReleased;
    private TextureRegion      upPressed;
    private TextureRegion      downReleased;
    private TextureRegion      downPressed;
    private TextureRegion      bombReleased;
    private TextureRegion      bombPressed;
    private TextureRegion      pauseReleased;
    private TextureRegion      pausePressed;

    private TextureRegion      wall;
    private TextureRegion      destroyWall;
    private TextureRegion      wallFrame;

    private TextureRegion      bombFrame;

    private TextureRegion      speed;
    private TextureRegion      longerBomb;
    // private TextureRegion addBomb;
    private TextureRegion      door;

    private TextureRegion      heartFrame;
    private TextureRegion      clockFrame;
    private TextureRegion      bobImgFrame;

    /** Animations **/
    private Animation          bobDyingAnimation;
    private Animation          bobWalkLeftAnimation;
    private Animation          bobWalkRightAnimation;
    private Animation          bobWalkUpAnimation;
    private Animation          bobWalkDownAnimation;

    private Animation          botDyingAnimation;
    private Animation          botWalkLeftDownAnimation;
    private Animation          botWalkRightUpAnimation;

    private Animation          destroyWallAnimation;

    private Animation          bombIdleAnimation;
    private Animation          bombExplodeAnimation;

    private SpriteBatch        spriteBatch;
    // private boolean debug = false;
    private int                width;
    /**
     *
     */
    public static int          height;
    /**
     *
     */
    public static float        ppuX;                                                  // pixels
// per unit on the X axis
    /**
     *
     */
    public static float        ppuY;                                                  // pixels
// per unit on the Y axis


    // ----------------------------------------------------------
    /**
     * Set the Size of the screen
     *
     * @param w
     *            int width
     * @param h
     *            int height
     */
    public void setSize(int w, int h)
    {
        this.width = w;
        this.height = h;
        ppuX = (float)width / CAMERA_WIDTH;
        ppuY = (float)height / CAMERA_HEIGHT;

        // Find position in pixels of blocks, buttons, bots, the character, etc.
        // (NOT GOOD, BUT..)
        world.findPositions();
    }


    // ----------------------------------------------------------
    /**
     * The WorldRenderer constructor
     *
     * @param world
     *            World class
     * @param debug
     *            boolean
     */
    public WorldRenderer(World world, boolean debug)
    {
        this.world = world;
        this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        this.cam.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);
        this.cam.update();
        // this.debug = debug;
        spriteBatch = new SpriteBatch();

        loadBlockTextures();
        loadBobTextures();
        loadBotTextures();
        loadButtonTextures();
        loadBombTextures();

        loadGraphicsTextures();
    }


    private void loadBobTextures()
    {
        TextureAtlas atlas =
            new TextureAtlas(
                Gdx.files.internal("images/textures/textures.pack"));

        bobIdleLeft = atlas.findRegion("left-0");
        bobIdleRight = atlas.findRegion("right-0");
        bobIdleUp = atlas.findRegion("up-0");
        bobIdleDown = atlas.findRegion("down-0");

        // left
        TextureRegion[] walkLeftFrames = new TextureRegion[3];
        for (int i = 0; i < 3; ++i)
        {
            walkLeftFrames[i] = atlas.findRegion("left-" + (i + 1) % 3);
        }
        bobWalkLeftAnimation =
            new Animation(BOB_RUNNING_FRAME_DURATION, walkLeftFrames);

        // right
        TextureRegion[] walkRightFrames = new TextureRegion[3];
        for (int i = 0; i < 3; ++i)
        {
            walkRightFrames[i] = atlas.findRegion("right-" + (i + 1) % 3);
        }
        bobWalkRightAnimation =
            new Animation(BOB_RUNNING_FRAME_DURATION, walkRightFrames);

        // up
        TextureRegion[] walkUpFrames = new TextureRegion[3];
        for (int i = 0; i < 3; ++i)
        {
            walkUpFrames[i] = atlas.findRegion("up-" + (i + 1) % 3);
        }
        bobWalkUpAnimation =
            new Animation(BOB_RUNNING_FRAME_DURATION, walkUpFrames);

        // down
        TextureRegion[] walkDownFrames = new TextureRegion[3];
        for (int i = 0; i < 3; ++i)
        {
            walkDownFrames[i] = atlas.findRegion("down-" + (i + 1) % 3);
        }
        bobWalkDownAnimation =
            new Animation(BOB_RUNNING_FRAME_DURATION, walkDownFrames);

        // dying
        TextureRegion[] dyingFrames = new TextureRegion[5];
        for (int i = 0; i < 5; ++i)
        {
            dyingFrames[i] = atlas.findRegion("die-" + i);
        }
        bobDyingAnimation =
            new Animation(BOB_DYING_FRAME_DURATION, dyingFrames);
    }


    private void loadBotTextures()
    {
        TextureAtlas atlas =
            new TextureAtlas(
                Gdx.files.internal("images/textures/textures2.pack"));

        botIdleLeft = atlas.findRegion("bot-0");
        botIdleRight = atlas.findRegion("bot-3");

        // left
        TextureRegion[] walkLeftDownFrames = new TextureRegion[6];
        for (int i = 0; i < 6; ++i)
        {
            walkLeftDownFrames[i] = atlas.findRegion("bot-" + (i % 3));
        }
        botWalkLeftDownAnimation =
            new Animation(BOT_RUNNING_FRAME_DURATION, walkLeftDownFrames);

        // right
        TextureRegion[] walkRightUpFrames = new TextureRegion[6];
        for (int i = 0; i < 6; ++i)
        {
            walkRightUpFrames[i] = atlas.findRegion("bot-" + ((i % 3) + 3));
        }
        botWalkRightUpAnimation =
            new Animation(BOT_RUNNING_FRAME_DURATION, walkRightUpFrames);

        // dying
        TextureRegion[] dyingFrames = new TextureRegion[7];
        for (int i = 0; i < 7; ++i)
        {
            dyingFrames[i] = atlas.findRegion("die-" + i);
        }
        botDyingAnimation =
            new Animation(BOT_DYING_FRAME_DURATION, dyingFrames);
    }


    // ----------------------------------------------------------
    /**
     * Load the button and textures
     */
    public void loadButtonTextures()
    {
        TextureAtlas atlas =
            new TextureAtlas(
                Gdx.files.internal("images/textures/textures3.pack"));

        leftReleased = atlas.findRegion("b-left-1");
        leftPressed = atlas.findRegion("b-left-2");

        rightReleased = new TextureRegion(leftReleased);
        rightReleased.flip(true, false);
        rightPressed = new TextureRegion(leftPressed);
        rightPressed.flip(true, false);

        upReleased = atlas.findRegion("b-up-1");
        upPressed = atlas.findRegion("b-up-2");

        downReleased = new TextureRegion(upReleased);
        downReleased.flip(false, true);
        downPressed = new TextureRegion(upPressed);
        downPressed.flip(false, true);

        bombReleased = atlas.findRegion("bomb-1");
        bombPressed = atlas.findRegion("bomb-2");

        pauseReleased = atlas.findRegion("pause-1");
        pausePressed = atlas.findRegion("pause-2");
    }


    // ----------------------------------------------------------
    /**
     * Load block textures
     */
    public void loadBlockTextures()
    {
        TextureAtlas atlas =
            new TextureAtlas(
                Gdx.files.internal("images/textures/textures5.pack"));

        blockTexture = atlas.findRegion("block");
        wall = atlas.findRegion("wall");
        destroyWall = atlas.findRegion("destroy_wall");

        TextureRegion[] destroyWallFrames = new TextureRegion[1];
        destroyWallFrames[0] = atlas.findRegion("destroyed_wall");
        // destroyWallFrames[1] = atlas.findRegion("block");

        destroyWallAnimation =
            new Animation(WALL_DESTROYING_FRAME_DURATION, destroyWallFrames);

        speed = atlas.findRegion("speed");
        longerBomb = atlas.findRegion("longer-bomb");
        // addBomb = atlas.findRegion("add-bomb");
        door = atlas.findRegion("door");
    }


    // ----------------------------------------------------------
    /**
     * Load bomb Textures
     */
    public void loadBombTextures()
    {
        TextureAtlas atlas =
            new TextureAtlas(
                Gdx.files.internal("images/textures/textures6.pack"));

        TextureRegion[] bombIdleFrames = new TextureRegion[3];
        bombIdleFrames[0] = atlas.findRegion("bomb-1");
        bombIdleFrames[1] = atlas.findRegion("bomb-2");
        bombIdleFrames[2] = atlas.findRegion("bomb-3");
        bombIdleAnimation =
            new Animation(BOMB_IDLE_FRAME_DURATION, bombIdleFrames);

        TextureRegion[] bombExplodeFrames = new TextureRegion[18];
        for (int i = 1; i <= 18; ++i)
        {
            bombExplodeFrames[i - 1] = atlas.findRegion("explode-" + i);
        }
        bombExplodeAnimation =
            new Animation(BOMB_EXPLODE_FRAME_DURATION, bombExplodeFrames);
    }


    // ----------------------------------------------------------
    /**
     * Load Graphics Textures
     */
    public void loadGraphicsTextures()
    {
        TextureAtlas atlas =
            new TextureAtlas(
                Gdx.files.internal("images/textures/textures7.pack"));

        heartFrame = atlas.findRegion("heart");
        clockFrame = atlas.findRegion("clock");
        bobImgFrame = atlas.findRegion("bob");
    }


    // ----------------------------------------------------------
    /**
     * Render the map
     */
    public void render()
    {
        spriteBatch.begin();
        drawBlocks();
        drawBob();
        drawBots();
        drawButtons();
        drawBombs();

        drawGraphics();
        spriteBatch.end();

        /*
         * if (debug) drawDebug();
         */
    }


    private void drawBlocks()
    {
        Block[][] block = world.getBlock();
        int row = world.getNumRows();
        int column = world.getNumColumns();

        for (int i = 0; i < row; i++)
            for (int j = 0; j < column; ++j)
            {
                switch (block[i][j].getState())
                {
                    case EMPTY:
                        spriteBatch.draw(
                            blockTexture,
                            block[i][j].getPosition().x * ppuX,
                            block[i][j].getPosition().y * ppuY,
                            Block.SIZE * ppuX,
                            Block.SIZE * ppuY);
                        break;
                    case WALL:
                        spriteBatch.draw(
                            wall,
                            block[i][j].getPosition().x * ppuX,
                            block[i][j].getPosition().y * ppuY,
                            Block.SIZE * ppuX,
                            Block.SIZE * ppuY);
                        break;
                    case DESTROY_WALL:
                        spriteBatch.draw(
                            destroyWall,
                            block[i][j].getPosition().x * ppuX,
                            block[i][j].getPosition().y * ppuY,
                            Block.SIZE * ppuX,
                            Block.SIZE * ppuY);
                        break;
                    case DESTROYING_WALL:
                        wallFrame =
                            destroyWallAnimation.getKeyFrame(
                                Block.stateTime,
                                true);

                        spriteBatch.draw(
                            wallFrame,
                            block[i][j].getPosition().x * ppuX,
                            block[i][j].getPosition().y * ppuY,
                            Block.SIZE * ppuX,
                            Block.SIZE * ppuY);
                        break;

                    case SPEED:
                        spriteBatch.draw(
                            speed,
                            block[i][j].getPosition().x * ppuX,
                            block[i][j].getPosition().y * ppuY,
                            Block.SIZE * ppuX,
                            Block.SIZE * ppuY);
                        break;
                    case LONGER_BOMB:
                        spriteBatch.draw(
                            longerBomb,
                            block[i][j].getPosition().x * ppuX,
                            block[i][j].getPosition().y * ppuY,
                            Block.SIZE * ppuX,
                            Block.SIZE * ppuY);
                        break;
                    /*
                     * case ADD_BOMB: spriteBatch.draw( addBomb,
                     * block[i][j].getPosition().x * ppuX,
                     * block[i][j].getPosition().y * ppuY, Block.SIZE * ppuX,
                     * Block.SIZE * ppuY); break;
                     */
                    case DOOR:
                        spriteBatch.draw(
                            door,
                            block[i][j].getPosition().x * ppuX,
                            block[i][j].getPosition().y * ppuY,
                            Block.SIZE * ppuX,
                            Block.SIZE * ppuY);
                        break;
                }
            }
    }


    private void drawBob()
    {
        Bob bob = world.getBob();

        if (bob.getState().equals(State.DYING))
        {
            bobFrame = bobDyingAnimation.getKeyFrame(bob.getStateTime(), true);
        }
        else
        {
            switch (bob.getFacing())
            {
                case LEFT:
                    bobFrame = bobIdleLeft;
                    break;
                case RIGHT:
                    bobFrame = bobIdleRight;
                    break;
                case UP:
                    bobFrame = bobIdleUp;
                    break;
                case DOWN:
                    bobFrame = bobIdleDown;
                    break;
            }

            if (bob.getState().equals(State.WALKING))
            {
                switch (bob.getFacing())
                {
                    case LEFT:
                        bobFrame =
                            bobWalkLeftAnimation.getKeyFrame(
                                bob.getStateTime(),
                                true);
                        break;
                    case RIGHT:
                        bobFrame =
                            bobWalkRightAnimation.getKeyFrame(
                                bob.getStateTime(),
                                true);
                        break;
                    case UP:
                        bobFrame =
                            bobWalkUpAnimation.getKeyFrame(
                                bob.getStateTime(),
                                true);
                        break;
                    case DOWN:
                        bobFrame =
                            bobWalkDownAnimation.getKeyFrame(
                                bob.getStateTime(),
                                true);
                        break;
                }
            }
        }

        spriteBatch.draw(
            bobFrame,
            bob.getPosition().x * ppuX,
            bob.getPosition().y * ppuY,
            Bob.SIZE * ppuX,
            Bob.SIZE * ppuY);
    }


    private void drawBots()
    {
        Bot[] bot = world.getBot();
        int numBots = world.getNumBots();

        for (int i = 0; i < numBots; ++i)
        {
            if (bot[i].getState() == Bot.State.DEAD)
            {
                continue;
            }

            if (bot[i].getState() == Bot.State.DYING)
            {
                botFrame =
                    botDyingAnimation.getKeyFrame(bot[i].getStateTime(), true);
            }
            else
            {
                switch (bot[i].getFacing())
                {
                    case DOWN:
                    case LEFT:
                        botFrame = botIdleLeft;
                        break;
                    case UP:
                    case RIGHT:
                        botFrame = botIdleRight;
                        break;
                }

                if (bot[i].getState().equals(Bot.State.WALKING))
                {
                    switch (bot[i].getFacing())
                    {
                        case DOWN:
                        case LEFT:
                            botFrame =
                                botWalkLeftDownAnimation.getKeyFrame(
                                    bot[i].getStateTime(),
                                    true);
                            break;
                        case UP:
                        case RIGHT:
                            botFrame =
                                botWalkRightUpAnimation.getKeyFrame(
                                    bot[i].getStateTime(),
                                    true);
                            break;
                    }
                }
            }

            spriteBatch.draw(
                botFrame,
                bot[i].getPosition().x * ppuX,
                bot[i].getPosition().y * ppuY,
                Bot.SIZE * ppuX,
                Bot.SIZE * ppuY);
        }
    }


    private void drawButtons()
    {
        Button left = world.getButton(Keys.LEFT);
        if (Button.keys.get(Keys.LEFT))
        {
            spriteBatch.draw(
                leftPressed,
                left.getPosition().x * ppuX,
                left.getPosition().y * ppuY,
                Button.SIZE * ppuX,
                Button.SIZE * ppuY);
        }
        else
        {
            spriteBatch.draw(
                leftReleased,
                left.getPosition().x * ppuX,
                left.getPosition().y * ppuY,
                Button.SIZE * ppuX,
                Button.SIZE * ppuY);
        }

        Button right = world.getButton(Keys.RIGHT);
        if (Button.keys.get(Keys.RIGHT))
        {
            spriteBatch.draw(
                rightPressed,
                right.getPosition().x * ppuX,
                right.getPosition().y * ppuY,
                Button.SIZE * ppuX,
                Button.SIZE * ppuY);
        }
        else
        {
            spriteBatch.draw(
                rightReleased,
                right.getPosition().x * ppuX,
                right.getPosition().y * ppuY,
                Button.SIZE * ppuX,
                Button.SIZE * ppuY);
        }

        Button up = world.getButton(Keys.UP);
        if (Button.keys.get(Keys.UP))
        {
            spriteBatch.draw(
                upPressed,
                up.getPosition().x * ppuX,
                up.getPosition().y * ppuY,
                Button.SIZE * ppuX,
                Button.SIZE * ppuY);
        }
        else
        {
            spriteBatch.draw(
                upReleased,
                up.getPosition().x * ppuX,
                up.getPosition().y * ppuY,
                Button.SIZE * ppuX,
                Button.SIZE * ppuY);
        }

        Button down = world.getButton(Keys.DOWN);
        if (Button.keys.get(Keys.DOWN))
        {
            spriteBatch.draw(
                downPressed,
                down.getPosition().x * ppuX,
                down.getPosition().y * ppuY,
                Button.SIZE * ppuX,
                Button.SIZE * ppuY);
        }
        else
        {
            spriteBatch.draw(
                downReleased,
                down.getPosition().x * ppuX,
                down.getPosition().y * ppuY,
                Button.SIZE * ppuX,
                Button.SIZE * ppuY);
        }

        Button bomb = world.getButton(Keys.BOMB);
        if (Button.keys.get(Keys.BOMB))
        {
            spriteBatch.draw(
                bombPressed,
                bomb.getPosition().x * ppuX,
                bomb.getPosition().y * ppuY,
                Button.SIZE * ppuX,
                Button.SIZE * ppuY);
        }
        else
        {
            spriteBatch.draw(
                bombReleased,
                bomb.getPosition().x * ppuX,
                bomb.getPosition().y * ppuY,
                Button.SIZE * ppuX,
                Button.SIZE * ppuY);
        }

        Button pause = world.getButton(Keys.PAUSE);
        if (Button.keys.get(Keys.PAUSE))
        {
            spriteBatch.draw(
                pausePressed,
                pause.getPosition().x * ppuX,
                pause.getPosition().y * ppuY,
                Button.SIZE * ppuX,
                Button.SIZE * ppuY);
        }
        else
        {
            spriteBatch.draw(
                pauseReleased,
                pause.getPosition().x * ppuX,
                pause.getPosition().y * ppuY,
                Button.SIZE * ppuX,
                Button.SIZE * ppuY);
        }
    }


    // ----------------------------------------------------------
    /**
     * Check the bounds
     *
     * @param x
     *            int
     * @param world
     *            World class
     * @return boolean
     */
    public boolean checkBounds(int x, World world)
    {
        return (0 <= x && x < world.getNumRows());
    }


    private void drawBombs()
    {
        Bomb[] bomb = world.getBomb();
        int numBombs = world.getNumBombs();

        for (int i = 0; i < numBombs; ++i)
        {
            if (bomb[i].getState() == Bomb.State.NOTHING)
            {
                continue;
            }

            switch (bomb[i].getState())
            {
                case IDLE:
                    bombFrame =
                        bombIdleAnimation.getKeyFrame(
                            bomb[i].getStateTime(),
                            true);

                    spriteBatch.draw(
                        bombFrame,
                        bomb[i].getPosition().x * ppuX,
                        bomb[i].getPosition().y * ppuY,
                        Bomb.SIZE * ppuX,
                        Bomb.SIZE * ppuY);
                    break;

                case EXPLODE:
                    bombFrame =
                        bombExplodeAnimation.getKeyFrame(
                            bomb[i].getStateTime(),
                            true);

                    Block[][] block = world.getBlock();
                    int u = bomb[i].getRow();
                    int v = bomb[i].getCol();

                    spriteBatch.draw(bombFrame, block[u][v].getPosition().x
                        * ppuX, block[u][v].getPosition().y * ppuY, Bomb.SIZE
                        * ppuX, Bomb.SIZE * ppuY);

                    for (int k = 1; k <= Bomb.length; ++k)
                    {
                        if (checkBounds(u + k, world)
                            && block[u + k][v].getState() == Block.State.EXPLODE)
                        {
                            spriteBatch.draw(
                                bombFrame,
                                block[u + k][v].getPosition().x * ppuX,
                                block[u + k][v].getPosition().y * ppuY,
                                Bomb.SIZE * ppuX,
                                Bomb.SIZE * ppuY);
                        }

                        if (checkBounds(u - k, world)
                            && block[u - k][v].getState() == Block.State.EXPLODE)
                        {
                            spriteBatch.draw(
                                bombFrame,
                                block[u - k][v].getPosition().x * ppuX,
                                block[u - k][v].getPosition().y * ppuY,
                                Bomb.SIZE * ppuX,
                                Bomb.SIZE * ppuY);
                        }

                        if (checkBounds(v + k, world)
                            && block[u][v + k].getState() == Block.State.EXPLODE)
                        {
                            spriteBatch.draw(
                                bombFrame,
                                block[u][v + k].getPosition().x * ppuX,
                                block[u][v + k].getPosition().y * ppuY,
                                Bomb.SIZE * ppuX,
                                Bomb.SIZE * ppuY);
                        }

                        if (checkBounds(v - k, world)
                            && block[u][v - k].getState() == Block.State.EXPLODE)
                        {
                            spriteBatch.draw(
                                bombFrame,
                                block[u][v - k].getPosition().x * ppuX,
                                block[u][v - k].getPosition().y * ppuY,
                                Bomb.SIZE * ppuX,
                                Bomb.SIZE * ppuY);
                        }
                    }

                    break;
            }
        }
    }


    private void drawGraphics()
    {
        int lives = world.getLives();
        Vector2 heart = world.getHeart();
        Vector2 clock = world.getClock();
        Vector2 bobImg = world.getBobImg();

        float u = heart.x;
        float v = heart.y;
        for (int i = 0; i < lives; ++i)
        {
            spriteBatch.draw(
                heartFrame,
                u * ppuX,
                v * ppuY,
                0.5f * ppuX,
                0.5f * ppuY);
            u += 0.5;
        }

        /*
         * spriteBatch.draw( clockFrame, clock.x * ppuX, clock.y * ppuY, 0.5f *
         * ppuX, 0.5f * ppuY);
         */

        spriteBatch.draw(
            bobImgFrame,
            bobImg.x * ppuX,
            bobImg.y * ppuY,
            0.5f * ppuX,
            0.5f * ppuY);
    }
}
