package com.game.bomberman.screens;

import com.badlogic.gdx.Gdx;
import com.game.bomberman.Bomberman;
import com.game.bomberman.services.SoundManager.BombermanSound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.game.bomberman.utils.DefaultActorListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

// -------------------------------------------------------------------------
/**
 * The menu screen of the game
 *
 * @author Ching Hsiang Chen (chingyg)
 * @author Kyle He (kyleh8)
 * @author Danh Nguyen (danh0902)
 * @version Dec 8, 2013
 */
public class MenuScreen
    extends AbstractScreen
{
    // ----------------------------------------------------------
    /**
     * Create a new MenuScreen object.
     *
     * @param game
     *            Bomberman game
     */
    public MenuScreen(Bomberman game)
    {
        super(game);
    }


    @Override
    public void show()
    {
        super.show();

        // retrieve the default table actor
        Table table = super.getTable();
        table.add("Welcome to Bomberman!").spaceBottom(50);
        table.row();

        // register the button "start game"
        TextButton startGameButton = new TextButton("New game", getSkin());
        startGameButton.addListener(new DefaultActorListener() {
            public void touchUp(
                InputEvent event,
                float x,
                float y,
                int pointer,
                int button)
            {
                super.touchUp(event, x, y, pointer, button);
                game.getSoundManager().play(BombermanSound.CLICK);
                game.setScreen(new GameScreen(game));
            }
        });
        table.add(startGameButton).size(300, 60).uniform().spaceBottom(10);
        table.row();

        // register the button "options"
        TextButton optionsButton = new TextButton("Options", getSkin());
        optionsButton.addListener(new DefaultActorListener() {
            @Override
            public void touchUp(
                InputEvent event,
                float x,
                float y,
                int pointer,
                int button)
            {
                super.touchUp(event, x, y, pointer, button);
                game.getSoundManager().play(BombermanSound.CLICK);
                game.setScreen(new OptionsScreen(game));
            }
        });
        table.add(optionsButton).uniform().fill().spaceBottom(10);
        table.row();

        // register the button "quit"
        TextButton quitButton = new TextButton("Quit", getSkin());
        quitButton.addListener(new DefaultActorListener() {
            @Override
            public void touchUp(
                InputEvent event,
                float x,
                float y,
                int pointer,
                int button)
            {
                super.touchUp(event, x, y, pointer, button);
                game.getSoundManager().play(BombermanSound.CLICK);
                // game.dispose();
                Gdx.app.exit();
            }
        });
        table.add(quitButton).uniform().fill();
    }
}
