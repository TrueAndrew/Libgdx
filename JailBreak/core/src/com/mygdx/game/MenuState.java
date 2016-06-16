package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class MenuState extends State{                                   // экран меню

    Texture Background;
    Texture Play;
    Texture Info;
    Texture Exit;

    Music music;

    com.mygdx.game.sprites.button play;
    com.mygdx.game.sprites.button info;
    com.mygdx.game.sprites.button exit;


    public MenuState(GameStateManager gsm) {
        super(gsm);

        music = Gdx.audio.newMusic(Gdx.files.internal("PlayMenuState.mp3"));
        music.setLooping(true);
        music.play();
        music.setVolume(0.3f);

        camera.setToOrtho(false, JailBreak.WIDTH, JailBreak.HEIGHT);    // Область обзора и расположение по разрешению экрана
        Background = new Texture("Background.psd");
        Info = new Texture("about.psd");
        Exit = new Texture("exit.png");
        Play = new Texture("play.png");

    }


    @Override
    protected void handleInput() {                                      // В случае нажатия перейти в следующий laiout- игра.
        if (Gdx.input.justTouched()){

            if (play.update(Gdx.input.getX(), Gdx.input.getY()))
            {
              gsm.set(new PlayState(gsm));
            }
            if (info.update(Gdx.input.getX(), Gdx.input.getY()) && Gdx.input.justTouched())
            {
                gsm.set(new HelpState(gsm));
            }
            if (exit.update(Gdx.input.getX(), Gdx.input.getY()))
            {
                Gdx.app.exit ();
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }


    @Override
        public void render(SpriteBatch sb) {                            // Расположение основных эл-том экрана
        sb.setProjectionMatrix(camera.combined);                        // Отрисовка и расположение камеры
        sb.begin();
        sb.draw(Background, 0, 0);
        play = new com.mygdx.game.sprites.button(sb, Play,camera.position.x -380, camera.position.y - 100, Play.getWidth(), Play.getHeight());
        info = new com.mygdx.game.sprites.button(sb, Info,camera.position.x -380, camera.position.y - 150, Info.getWidth(), Info.getHeight());
        exit = new com.mygdx.game.sprites.button(sb, Exit,camera.position.x -380, camera.position.y - 200, Exit.getWidth(), Exit.getHeight());
        sb.end();
    }

    @Override
    public void dispose() {
        Background.dispose();
        Play.dispose();
        Info.dispose();
        Exit.dispose();
        music.dispose();
    }
}
