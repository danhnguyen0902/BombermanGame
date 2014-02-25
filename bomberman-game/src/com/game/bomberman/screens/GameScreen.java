package com.game.bomberman.screens;

import com.badlogic.gdx.utils.TimeUtils;
import com.game.bomberman.model.Bob;
import com.game.bomberman.Bomberman;
import com.game.bomberman.view.WorldRenderer;
import com.game.bomberman.model.Button;
import com.game.bomberman.model.World;
import com.game.bomberman.model.Button.Keys;
import com.game.bomberman.controller.WorldController;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;

// -------------------------------------------------------------------------
/**
 * The main game screen
 *
 * @author Ching Hsiang Chen (chingyg)
 * @author Kyle He (kyleh8)
 * @author Danh Nguyen (danh0902)
 * @version Dec 8, 2013
 */
public class GameScreen
    extends AbstractScreen
    implements InputProcessor
{

    private World           world;
    private WorldRenderer   renderer;
    private WorldController controller;

    private int             width, height;


    // -------------------------------------------------------------------------
    /**
     * The state of the game
     *
     * @author Ching Hsiang Chen (chingyg)
     * @author Kyle He (kyleh8)
     * @author Danh Nguyen (danh0902)
     * @version Dec 8, 2013
     */
    public enum State
    {
        /**
         * play mode
         */
        PLAY,
        /**
         * pause mode
         */
        PAUSE
    }

    private State state = State.PLAY;


    // ----------------------------------------------------------
    /**
     * Create a new GameScreen object.
     *
     * @param game
     *            Bomerman game
     */
    public GameScreen(Bomberman game)
    {
        super(game);
    }


    /**
     * Screen implementation
     */
    public void show()
    {
        super.show();

        Gdx.input.setInputProcessor(this);

        if (state == State.PAUSE)
        {
            state = State.PLAY;
            return;
        }

        world = new World();
        renderer = new WorldRenderer(world, true);
        controller = new WorldController(world);
    }


    /**
     * The main loop of the screen
     */
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        Bob bob = world.getBob();
        if (Bob.VICTORY)
        {
            game.setScreen(new VictoryScreen(game));
        }

        if (bob.getState() == Bob.State.DYING)
        {
            if (TimeUtils.millis() - bob.getStart() >= 4500)
            {
                int lives = world.getLives();
                switch (lives)
                {
                    case 1:
                        game.setScreen(new GameOverScreen(game));
                        break;
                    case 2:
                        show();
                        resize(width, height);
                        world.setLives(1);
                        break;
                    case 3:
                        show();
                        resize(width, height);
                        world.setLives(2);
                        break;
                }
            }
        }

        controller.update(delta);

        renderer.render();

        if (state == State.PAUSE)
        {
            game.setScreen(new PauseScreen(game, this, controller));
        }
    }


    /**
     * Resizes the scren
     *
     * @param width
     *            the width of the screen
     * @param height
     *            the height of the screen
     */
    public void resize(int width, int height)
    {
        renderer.setSize(width, height);
        this.width = width;
        this.height = height;
    }


    /**
     * Called when current screen is not active
     */
    public void hide()
    {
        Gdx.input.setInputProcessor(null);
    }


    /**
     * Called when the screen is on pause
     */
    public void pause()
    {
        // do nothing
    }


    public void resume()
    {
        // do nothing
    }


    /**
     * Called when the screen is destroyed
     */
    public void dispose()
    {
        Gdx.input.setInputProcessor(null);
    }


    // ---------------- InputProcessor methods ----------------- //

    /**
     * When the key is pressed
     *
     * @return false
     */
    public boolean keyDown(int keycode)
    {
        if (keycode == com.badlogic.gdx.Input.Keys.BACK)
        {
            state = State.PAUSE;
        }

        return false;
    }


    /**
     * When the key is released
     *
     * @return false
     */
    public boolean keyUp(int keycode)
    {
        return false;
    }


    /**
     * When something is typed in
     *
     * @return false
     */
    public boolean keyTyped(char character)
    {
        return false;
    }


    /**
     * Called when the screen is touched
     *
     * @return true
     */
    public boolean touchDown(int x, int y, int pointer, int button)
    {
        if (!Gdx.app.getType().equals(ApplicationType.Android))
            return false;

        Button left = world.getButton(Keys.LEFT);
        if ((int)left.getX1() <= x && x <= (int)left.getX2()
            && (int)left.getY1() <= y && y <= (int)left.getY2())
        {
            controller.leftPressed();
        }

        Button right = world.getButton(Keys.RIGHT);
        if ((int)right.getX1() <= x && x <= (int)right.getX2()
            && (int)right.getY1() <= y && y <= (int)right.getY2())
        {
            controller.rightPressed();
        }

        Button up = world.getButton(Keys.UP);
        if ((int)up.getX1() <= x && x <= (int)up.getX2()
            && (int)up.getY1() <= y && y <= (int)up.getY2())
        {
            controller.upPressed();
        }

        Button down = world.getButton(Keys.DOWN);
        if ((int)down.getX1() <= x && x <= (int)down.getX2()
            && (int)down.getY1() <= y && y <= (int)down.getY2())
        {
            controller.downPressed();
        }

        Button bomb = world.getButton(Keys.BOMB);
        if ((int)bomb.getX1() <= x && x <= (int)bomb.getX2()
            && (int)bomb.getY1() <= y && y <= (int)bomb.getY2())
        {
            controller.bombPressed();
        }

        Button pause = world.getButton(Keys.PAUSE);
        if ((int)pause.getX1() <= x && x <= (int)pause.getX2()
            && (int)pause.getY1() <= y && y <= (int)pause.getY2())
        {
            controller.pausePressed();
            state = State.PAUSE;
        }

        return true;
    }


    /**
     * Called when the user lifts their fingers up
     *
     * @return true
     */
    public boolean touchUp(int x, int y, int pointer, int button)
    {
        if (!Gdx.app.getType().equals(ApplicationType.Android))
            return false;

        Button left = world.getButton(Keys.LEFT);
        if ((int)left.getX1() <= x && x <= (int)left.getX2()
            && (int)left.getY1() <= y && y <= (int)left.getY2())
        {
            controller.leftReleased();
        }

        Button right = world.getButton(Keys.RIGHT);
        if ((int)right.getX1() <= x && x <= (int)right.getX2()
            && (int)right.getY1() <= y && y <= (int)right.getY2())
        {
            controller.rightReleased();
        }

        Button up = world.getButton(Keys.UP);
        if ((int)up.getX1() <= x && x <= (int)up.getX2()
            && (int)up.getY1() <= y && y <= (int)up.getY2())
        {
            controller.upReleased();
        }

        Button down = world.getButton(Keys.DOWN);
        if ((int)down.getX1() <= x && x <= (int)down.getX2()
            && (int)down.getY1() <= y && y <= (int)down.getY2())
        {
            controller.downReleased();
        }

        Button bomb = world.getButton(Keys.BOMB);
        if ((int)bomb.getX1() <= x && x <= (int)bomb.getX2()
            && (int)bomb.getY1() <= y && y <= (int)bomb.getY2())
        {
            controller.bombReleased();
        }

        /*
         * Button pause = world.getButton(Keys.PAUSE); if ((int)pause.getX1() <=
         * x && x <= (int)pause.getX2() && (int)pause.getY1() <= y && y <=
         * (int)pause.getY2()) { controller.pauseReleased(); state =
         * States.PLAY; }
         */

        return true;
    }


    /**
     * Called when something is dragged on the screen
     *
     * @return false
     */
    public boolean touchDragged(int x, int y, int pointer)
    {
        return false;
    }


    /**
     * Called when something is moved on the screen
     *
     * @param x
     *            x coordinate
     * @param y
     *            y coordinate
     * @return false
     */
    public boolean touchMoved(int x, int y)
    {
        return false;
    }


    /**
     * Called when the screen is scrolled up or down
     *
     * @return false
     */
    public boolean scrolled(int amount)
    {
        return false;
    }


    /**
     * Called when the mouse is moved
     *
     * @return false
     */
    public boolean mouseMoved(int screenX, int screenY)
    {
        return false;
    }

}
