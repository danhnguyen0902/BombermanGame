package com.game.bomberman.screens;

import com.game.bomberman.controller.WorldController;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.game.bomberman.services.SoundManager.BombermanSound;
import com.game.bomberman.utils.DefaultActorListener;
import com.game.bomberman.Bomberman;

// -------------------------------------------------------------------------
/**
 * The pause screen for the game
 *
 * @author Ching Hsiang Chen (chingyg)
 * @author Kyle He (kyleh8)
 * @author Danh Nguyen (danh0902)
 * @version Dec 8, 2013
 */
public class PauseScreen
    extends AbstractScreen
{
    private GameScreen      gameScreen;
    private WorldController controller;


    // ----------------------------------------------------------
    /**
     * Create a new PauseScreen object.
     *
     * @param game
     *            the Bomberman game
     * @param gameScreen
     *            the GameScreen object
     * @param controller
     *            the Controller object
     */
    public PauseScreen(
        Bomberman game,
        GameScreen gameScreen,
        WorldController controller)
    {
        super(game);
        this.gameScreen = gameScreen;
        this.controller = controller;
    }


    @Override
    public void show()
    {
        super.show();

        // retrieve the default table actor
        Table table = super.getTable();

        // register the button "resume game"
        TextButton resumeButton = new TextButton("Resume", getSkin());
        resumeButton.addListener(new DefaultActorListener() {
            public void touchUp(
                InputEvent event,
                float x,
                float y,
                int pointer,
                int button)
            {
                super.touchUp(event, x, y, pointer, button);
                game.getSoundManager().play(BombermanSound.CLICK);
                controller.pauseReleased();
                // gameScreen.resume(); // doesn't work since resume() is a
                // function inherited from the Game class
                game.setScreen(gameScreen); // show() in GameScreen will be
                                            // called first
            }
        });
        table.add(resumeButton).size(300, 60).uniform().spaceBottom(10);
        table.row();

        // register the button "back to main menu"
        TextButton backButton = new TextButton("Back to main menu", getSkin());
        backButton.addListener(new DefaultActorListener() {
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
                controller.pauseReleased();
                game.setScreen(new MenuScreen(game));
            }
        });
        table.add(backButton).uniform().fill().spaceBottom(10);
        table.row();
    }
}
