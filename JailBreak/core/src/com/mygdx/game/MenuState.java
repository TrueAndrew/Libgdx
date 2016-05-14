package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuState extends State{                                   // экран меню

    Texture Background;
    Texture Option;
    Texture information;
    Texture Exit;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, JailBreak.WIDTH, JailBreak.HEIGHT);    // Область обзора и расположение по разрешению экрана
        Background = new Texture("Background.psd");
        Option = new Texture("Option.psd");
        information = new Texture("Information.psd");
        Exit = new Texture("Exit.psd");
    }

    @Override
    protected void handleInput() {                                      // В случае нажатия перейти в следующий laiout- игра.
        if (Gdx.input.isTouched()){
            gsm.set(new PlayState(gsm));
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
        sb.draw(Option,camera.position.x -380, camera.position.y + 160);
        sb.draw(information,camera.position.x - 380, camera.position.y + 95);
        sb.draw(Exit,camera.position.x - 380,camera.position.y + 28);
        sb.end();
    }

    @Override
    public void dispose() {
        Background.dispose();
        Option.dispose();
        information.dispose();
        Exit.dispose();
    }
}
