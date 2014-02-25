package com.game.bomberman.screens;

import com.game.bomberman.Bomberman;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * The base class for all game screens.
 *
 * @author Ching Hsiang Chen (chingyg)
 * @author Kyle He (kyleh8)
 * @author Danh Nguyen (danh0902)
 * @version Dec 8, 2013
 */
public abstract class AbstractScreen
    implements Screen
{
    /**
     * The width of the game view
     */
    public static final int   GAME_VIEWPORT_WIDTH  = 400;
    /**
     * The height of the game view
     */
    public static final int   GAME_VIEWPORT_HEIGHT = 240;
    /**
     * The width of the menu view
     */
    public static final int   MENU_VIEWPORT_WIDTH  = 800;
    /**
     * The height of the menu view
     */
    public static final int   MENU_VIEWPORT_HEIGHT = 480;

    /**
     * The Bomberman game
     */
    protected final Bomberman game;
    /**
     * A 2D scenegraph
     */
    protected final Stage     stage;

    private BitmapFont        font;
    private SpriteBatch       batch;
    private Skin              skin;
    private TextureAtlas      atlas;
    private Table             table;


    // ----------------------------------------------------------
    /**
     * Create a new AbstractScreen object.
     *
     * @param game
     *            the Bomberman game
     */
    public AbstractScreen(Bomberman game)
    {
        this.game = game;
        int width =
            (isGameScreen() ? GAME_VIEWPORT_WIDTH : MENU_VIEWPORT_WIDTH);
        int height =
            (isGameScreen() ? GAME_VIEWPORT_HEIGHT : MENU_VIEWPORT_HEIGHT);
        this.stage = new Stage(width, height, true);
    }


    // ----------------------------------------------------------
    /**
     * Returns the name of the class
     *
     * @return the class's name
     */
    protected String getName()
    {
        return getClass().getSimpleName();
    }


    // ----------------------------------------------------------
    /**
     * Determines if it is a game screen
     *
     * @return false
     */
    protected boolean isGameScreen()
    {
        return false;
    }


    // ----------------------------------------------------------
    /**
     * Gets the font
     *
     * @return the font
     */
    public BitmapFont getFont()
    {
        if (font == null)
        {
            font = new BitmapFont();
        }
        return font;
    }


    // ----------------------------------------------------------
    /**
     * Gets the sprite batch that draws the view
     *
     * @return the sprite batch
     */
    public SpriteBatch getBatch()
    {
        if (batch == null)
        {
            batch = new SpriteBatch();
        }
        return batch;
    }


    // ----------------------------------------------------------
    /**
     * Gets the atlas of pictures
     *
     * @return the atlas
     */
    public TextureAtlas getAtlas()
    {
        if (atlas == null)
        {
            atlas =
                new TextureAtlas(
                    Gdx.files.internal("images/textures/textures4.pack"));
        }
        return atlas;
    }


    // ----------------------------------------------------------
    /**
     * Gets the skin for the table layout
     *
     * @return the skin
     */
    protected Skin getSkin()
    {
        if (skin == null)
        {
            FileHandle skinFile = Gdx.files.internal("data/uiskin.json");
            skin = new Skin(skinFile);
        }
        return skin;
    }


    // ----------------------------------------------------------
    /**
     * Gets the table layout
     *
     * @return the table
     */
    protected Table getTable()
    {
        if (table == null)
        {
            table = new Table(getSkin());
            table.setFillParent(true);
            if (Bomberman.DEV_MODE)
            {
                table.debug();
            }
            stage.addActor(table);
        }
        return table;
    }


    /**
     * Screen implementation
     */
    public void show()
    {
        // set the stage as the input processor
        Gdx.input.setInputProcessor(stage);

        // catch back key
        Gdx.input.setCatchBackKey(true);
    }


    /**
     * Resize the screen
     */
    public void resize(int width, int height)
    {
        // do nothing
    }


    /**
     * The main loop of the thread
     */
    public void render(float delta)
    {
        // (1) process the game logic

        // update the actors
        stage.act(delta);

        // (2) draw the result

        // clear the screen with the given RGB color (black)
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // draw the actors
        stage.draw();

        // draw the table debug lines
        Table.drawDebug(stage);
    }


    /**
     * Called when "this" screen is no longer the current screen.
     */
    public void hide()
    {
        // dispose the screen when leaving the screen;
        // note that the dipose() method is not called automatically by the
        // framework, so we must figure out when it's appropriate to call it
        dispose();
    }


    /**
     * The game is on pause
     */
    public void pause()
    {
        // do nothing
    }


    /**
     * The game is resumed again
     */
    public void resume()
    {
        // do nothing
    }


    /**
     * The game is destroyed
     */
    public void dispose()
    {
        if (font != null)
            font.dispose();
        if (batch != null)
            batch.dispose();
        if (skin != null)
            skin.dispose();
        if (atlas != null)
            atlas.dispose();
    }
}
