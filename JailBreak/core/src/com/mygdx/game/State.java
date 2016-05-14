package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.GameStateManager;

public abstract class State{

    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected GameStateManager gsm;                 // Управление различными состояниями игры (между laiout-ами)

    public State(GameStateManager gsm){             // Конструктор
        this.gsm = gsm;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void handleInput();          // Проверяет были ли нажаты клавиши или touchScreen
    public abstract void update(float dt);          // Обновляет картинки (экрана и др.) через определенные промежутки времени
    public abstract void render(SpriteBatch sb);    // Отрисовывает экран
    public abstract void dispose();                 // Делает очистку компонентов (уничтожает экземпляр класса, когда они не нужны)
}
