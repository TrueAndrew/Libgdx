package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;


public class GameStateManager {

    private Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>();
    }          // Создание стека

    public void push(State state){                                      // Помещает элемент в вершину стека
        states.push(state);
    }

    public void pop(){                                                  // Извлекает первый элемент стека,удаляя его
        states.pop().dispose();
    }

    public void set(State state){                                       // Удаляет из стека предыдущий и помещать новый экран
        states.pop().dispose();
        states.push(state);
    }

    public void update(float dt){                                       // Возращает верхний элемент стека, не удаляя его
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }    // Отрисовывает экран
}
