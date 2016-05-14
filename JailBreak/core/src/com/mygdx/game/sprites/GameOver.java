package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameStateManager;
import com.mygdx.game.JailBreak;
import com.mygdx.game.MenuState;
import com.mygdx.game.State;

public class GameOver extends State{                                    // экран окончания игры

    Texture gameover;

    public GameOver(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, JailBreak.WIDTH, JailBreak.HEIGHT);     // Область обзора и расположение по разрешению экрана
        gameover = new Texture("GameOver.psd");
    }

    @Override
    protected void handleInput() {                                      // При нажатии перезапустить игру
        if (Gdx.input.isTouched()){
            gsm.set(new MenuState(gsm));
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
        sb.draw(gameover,0,0);
        sb.end();
    }

    @Override
    public void dispose() {
        gameover.dispose();
    }
}
