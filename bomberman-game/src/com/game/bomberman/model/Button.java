package com.game.bomberman.model;

import com.game.bomberman.view.WorldRenderer;
import java.util.HashMap;
import java.util.Map;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

// -------------------------------------------------------------------------
/**
 * The Button class of the game
 *
 * @author Ching Hsiang Chen (chingyg)
 * @author Kyle He (kyleh8)
 * @author Danh Nguyen (danh0902)
 * @version Dec 8, 2013
 */
public class Button
{

    // -------------------------------------------------------------------------
    /**
     * The directional keys to navigate the character
     *
     * @author DANH_NGUYEN
     * @version Dec 8, 2013
     */
    public enum Keys
    {
        /**
         * Move to the left
         */
        LEFT,
        /**
         * Move to the right
         */
        RIGHT,
        /**
         * Move up
         */
        UP,
        /**
         * Move down
         */
        DOWN,
        /**
         * Place the bomb
         */
        BOMB,
        /**
         * Pause the game
         */
        PAUSE
    }

    /**
     * The map controls the keys pressed or released
     */
    public static Map<Keys, Boolean> keys     = new HashMap<Keys, Boolean>();
    static
    {
        keys.put(Keys.LEFT, false);
        keys.put(Keys.RIGHT, false);
        keys.put(Keys.UP, false);
        keys.put(Keys.DOWN, false);
        keys.put(Keys.BOMB, false);
        keys.put(Keys.PAUSE, false);
    };

    /**
     * The size of the button
     */
    public static final float        SIZE     = 1f;

    /**
     * Position of the button
     */
    Vector2                          position = new Vector2();
    /**
     * The bounds of the button
     */
    Rectangle                        bounds   = new Rectangle();

    // They're all in pixels
    private float                    x1;
    private float                    x2;
    private float                    y1;
    private float                    y2;

    private float                    xC;
    private float                    yC;


    // ----------------------------------------------------------
    /**
     * Create a new Button object.
     *
     * @param pos
     *            the position of the button
     */
    public Button(Vector2 pos)
    {
        this.position = pos;
        this.bounds.width = SIZE;
        this.bounds.height = SIZE;
    }


    // ----------------------------------------------------------
    /**
     * Gets position of the button
     *
     * @return the position
     */
    public Vector2 getPosition()
    {
        return position;
    }


    /**
     * Gets the bounds of the button
     *
     * @return the bounds
     */
    public Rectangle getBounds()
    {
        return bounds;
    }


    /**
     * Gets x1
     *
     * @return x1
     */
    public float getX1()
    {
        return x1;
    }


    /**
     * Gets x2
     *
     * @return x2
     */
    public float getX2()
    {
        return x2;
    }


    /**
     * Gets y1
     *
     * @return y1
     */
    public float getY1()
    {
        return y1;
    }


    /**
     * Gets y2
     *
     * @return y2
     */
    public float getY2()
    {
        return y2;
    }


    /**
     * Gets xC
     *
     * @return xC
     */
    public float getxC()
    {
        return xC;
    }


    /**
     * Gets yC
     *
     * @return yC
     */
    float getyC()
    {
        return yC;
    }


    // ----------------------------------------------------------
    /**
     * Finds the position in pixels
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
