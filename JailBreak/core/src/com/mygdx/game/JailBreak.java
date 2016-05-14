package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class JailBreak extends ApplicationAdapter {
	public static final int WIDTH = 800;					// длина экрана
	public static final int HEIGHT = 480;					// ширина экрана

	public static final String TITLE = "Jail Break";      	//название игры

	private GameStateManager gsm;
	private SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());			// Обновление экрана
		gsm.render(batch);									// Отрисовывает верхний экран в стеке
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
