package com.game.bomberman.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.game.bomberman.utils.DefaultActorListener;
import com.game.bomberman.services.MusicManager.BombermanMusic;
import com.game.bomberman.services.SoundManager.BombermanSound;
import com.game.bomberman.Bomberman;
import java.util.Locale;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * An options screen for the screen
 *
 * @author Ching Hsiang Chen (chingyg)
 * @author Kyle He (kyleh8)
 * @author Danh Nguyen (danh0902)
 * @version Dec 8, 2013
 */
public class OptionsScreen
    extends AbstractScreen
{
    private Label volumeValue;


    // ----------------------------------------------------------
    /**
     * Create a new OptionsScreen object.
     *
     * @param game
     *            the Bomberman game
     */
    public OptionsScreen(Bomberman game)
    {
        super(game);
    }


    @Override
    public void show()
    {
        super.show();

        // retrieve the default table actor
        Table table = super.getTable();
        table.defaults().spaceBottom(30);
        table.columnDefaults(0).padRight(20);
        table.add("Options").colspan(3);

        // create the labels widgets
        final CheckBox soundEffectsCheckbox = new CheckBox("", getSkin());
        soundEffectsCheckbox.setChecked(game.getPreferencesManager()
            .isSoundEnabled());
        soundEffectsCheckbox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                boolean enabled = soundEffectsCheckbox.isChecked();
                game.getPreferencesManager().setSoundEnabled(enabled);
                game.getSoundManager().setEnabled(enabled);
                game.getSoundManager().play(BombermanSound.CLICK);
            }
        });
        table.row();
        table.add("Sound Effects");
        table.add(soundEffectsCheckbox).colspan(2).left();

        final CheckBox musicCheckbox = new CheckBox("", getSkin());
        musicCheckbox.setChecked(game.getPreferencesManager().isMusicEnabled());
        musicCheckbox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                boolean enabled = musicCheckbox.isChecked();
                game.getPreferencesManager().setMusicEnabled(enabled);
                game.getMusicManager().setEnabled(enabled);
                game.getSoundManager().play(BombermanSound.CLICK);

                // if the music is now enabled, start playing the menu music
                if (enabled)
                    game.getMusicManager().play(BombermanMusic.MENU);
            }
        });
        table.row();
        table.add("Music");
        table.add(musicCheckbox).colspan(2).left();

        // range is [0.0,1.0]; step is 0.1f
        Slider volumeSlider = new Slider(0f, 1f, 0.1f, false, getSkin());
        volumeSlider.setValue(game.getPreferencesManager().getVolume());
        volumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                float value = ((Slider)actor).getValue();
                game.getPreferencesManager().setVolume(value);
                game.getMusicManager().setVolume(value);
                game.getSoundManager().setVolume(value);
                updateVolumeLabel();
            }
        });

        // create the volume label
        volumeValue = new Label("", getSkin());
        updateVolumeLabel();

        // add the volume row
        table.row();
        table.add("Volume");
        table.add(volumeSlider);
        table.add(volumeValue).width(40);

        // register the back button
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
                game.setScreen(new MenuScreen(game));
            }
        });
        table.row();
        table.add(backButton).size(250, 60).colspan(3);
    }


    /**
     * Updates the volume label next to the slider.
     */
    private void updateVolumeLabel()
    {
        float volume = (game.getPreferencesManager().getVolume() * 100);
        volumeValue.setText(String.format(Locale.US, "%1.0f%%", volume));
    }
}
