package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.InputAdapter;

public abstract class State extends InputAdapter{

    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected GameStateManager gsm;                 // Управление различными состояниями игры (между laiout-ами)


    public State(GameStateManager gsm){             // Конструктор

        this.gsm = gsm;
        Gdx.input.setInputProcessor(this);
        camera = new OrthographicCamera();
        mouse = new Vector3();
        Gdx.input.setCatchBackKey(true);
    }

    protected abstract void handleInput();          // Проверяет были ли нажаты клавиши или touchScreen
    public abstract void update(float dt);          // Обновляет картинки (экрана и др.) через определенные промежутки времени
    public abstract void render(SpriteBatch sb);    // Отрисовывает экран
    public abstract void dispose();                 // Делает очистку компонентов (уничтожает экземпляр класса, когда они не нужны)
}
