package com.mygdx.game.sprites;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameStateManager;
import com.mygdx.game.JailBreak;
import com.mygdx.game.MenuState;
import com.mygdx.game.PlayState;
import com.mygdx.game.State;

public class GameOver extends State{                                    // экран окончания игры

    Texture gameover;
    Texture Play;
    Texture Menu;

    Music music;

    com.mygdx.game.sprites.button play;
    com.mygdx.game.sprites.button menu;


    public GameOver(GameStateManager gsm) {
        super(gsm);

        music = Gdx.audio.newMusic(Gdx.files.internal("musicGameOver.mp3"));
        music.play();
        music.setLooping(true);
        music.setVolume(0.3f);								// Громкость музыки

        camera.setToOrtho(false, JailBreak.WIDTH, JailBreak.HEIGHT);     // Область обзора и расположение по разрешению экрана
        gameover = new Texture("GameOver.psd");
        Play = new Texture("retry.psd");
        Menu = new Texture("back_menu.psd");

    }

    @Override
    protected void handleInput() {                                      // При нажатии перезапустить игру
        if (Gdx.input.justTouched()){
            if (play.update(Gdx.input.getX(), Gdx.input.getY())) {
                gsm.set(new PlayState(gsm));
                music.play();
            }
            if (menu.update(Gdx.input.getX(), Gdx.input.getY())) {
                gsm.set(new MenuState(gsm));
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {                                // Отрисовка экрана конца игры
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(gameover, 0, 0);
        play = new button(sb, Play, camera.position.x - 380, camera.position.y - 150, Play.getWidth(), Play.getHeight());
        menu = new button(sb, Menu, camera.position.x - 380, camera.position.y - 200, Menu.getWidth(), Menu.getHeight());
        sb.end();
    }

    @Override
    public void dispose() {
        gameover.dispose();
        Play.dispose();
        Menu.dispose();
        music.dispose();
    }
}
