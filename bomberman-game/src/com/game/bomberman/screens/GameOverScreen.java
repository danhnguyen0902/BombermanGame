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
 * The screen when the game is over
 *
 * @author Ching Hsiang Chen (chingyg)
 * @author Kyle He (kyleh8)
 * @author Danh Nguyen (danh0902)
 *
 * @version Dec 8, 2013
 */
public class GameOverScreen
    extends AbstractScreen
{
    // ----------------------------------------------------------
    /**
     * Create a new GameOverScreen object.
     *
     * @param game
     */
    public GameOverScreen(Bomberman game)
    {
        super(game);
    }


    @Override
    public void show()
    {
        super.show();

        // retrieve the default table actor
        Table table = super.getTable();
        table.add("Game Over!").spaceBottom(50);
        table.row();

        // register the button "back to main menu"
        TextButton backButton = new TextButton("Back to main menu", getSkin());
        backButton.addListener(new DefaultActorListener() {
            public void touchUp(
                InputEvent event,
                float x,
                float y,
                int pointer,
                int button)
            {
                super.touchUp(event, x, y, pointer, button);
                game.getSoundManager().play(BombermanSound.CLICK);
                game.setScreen(new MenuScreen(game));
            }
        });
        table.add(backButton).size(300, 60).uniform().spaceBottom(10);
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
