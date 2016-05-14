package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.sprites.Convict;
import com.mygdx.game.sprites.GameOver;
import com.mygdx.game.sprites.Police;

public class PlayState extends State {

    public static final int POLICE_SPACING = 700;
    public static final int POLICE_COUNT = 2;

    private Convict convict;
    private Texture BackroundGame;
    private Texture ground;
    private Vector2 groundPos_1,groundPos_2;
    private Vector2 BackgroundPos_1,BackgroundPos_2;

    private Music music;

    private Array<Police> polices;

    public PlayState(GameStateManager gsm) {
        super(gsm);

        music = Gdx.audio.newMusic(Gdx.files.internal("JailBreak.mp3"));
        music.setLooping(true);
        music.setVolume(0.3f);								// Громкость музыки
        music.play();

        convict = new Convict(30,30);
        camera.setToOrtho(false, JailBreak.WIDTH, JailBreak.HEIGHT);       // Область обзора и расположение по разрешению экрана
        BackroundGame = new Texture("BackgroundGame.psd");
        ground = new Texture("GameGround.psd");

        groundPos_1 = new Vector2(camera.position.x - camera.viewportWidth / 2 , 0);
        groundPos_2 = new Vector2((camera.position.x - camera.viewportWidth / 2 ) + ground.getWidth(), 0);

        BackgroundPos_1 = new Vector2(camera.position.x - camera.viewportWidth / 2 , 0);
        BackgroundPos_2 = new Vector2((camera.position.x - camera.viewportWidth / 2 ) + ground.getWidth(), 0);

        polices = new Array<Police>();                                     // создание массива обьектов-полицейских

        for (int i = 1; i < POLICE_COUNT; i++){
            polices.add(new Police(POLICE_SPACING + Police.POLICE_WIDTH));
        }
    }

    @Override
    protected void handleInput() {                                          // Выполнение прыжка при нажатии на экран
        if (Gdx.input.isTouched()) {
            convict.jump();
        }
    }

    @Override
    public void update(float dt) {
        updateBackGround();
        handleInput();
        updateGround();
        convict.update(dt);

        camera.position.x = convict.getPosition().x + 350;                   // Позиция обьекта-полицейского

        for (int i = 0; i< polices.size; i++){

            Police police = polices.get(i);

            if (camera.position.x - (camera.viewportWidth / 2 ) > police.getPosPolice().x + police.getPolice().getWidth()){
                police.reposition(police.getPosPolice().x +((Police.POLICE_WIDTH + POLICE_SPACING) * POLICE_COUNT));
            }

            if (police.collides(convict.getBounds()))           // Обнаружение коллизии (столкновения обектов)
                gsm.set(new GameOver(gsm));
        }
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);                // установление положения камеры а так же всех элементов (картинок)
        sb.begin();
        sb.draw(BackroundGame, camera.position.x - (camera.viewportWidth / 2), 0);
        sb.draw(BackroundGame, BackgroundPos_1.x, BackgroundPos_1.y);
        sb.draw(BackroundGame, BackgroundPos_2.x, BackgroundPos_2.y);

        for (Police police:polices){
            sb.draw(convict.getConvict(), convict.getPosition().x, convict.getPosition().y);
            sb.draw(police.getPolice(),police.getPosPolice().x,police.getPosPolice().y);
        }
        sb.draw(ground, groundPos_1.x, groundPos_1.y);
        sb.draw(ground, groundPos_2.x, groundPos_2.y);
        sb.end();
    }

    @Override
    public void dispose() {
        BackroundGame.dispose();
        convict.dispose();
        ground.dispose();
        for (Police police:polices)
            police.dispose();
        music.dispose();
    }

    private void updateGround(){                // создание идущих друг за другом текстуры земли
        if (camera.position.x - (camera.viewportWidth / 2) > groundPos_1.x +ground.getWidth())
            groundPos_1.add(ground.getWidth() * 2, 0);
        if (camera.position.x - (camera.viewportWidth / 2) > groundPos_2.x +ground.getWidth())
            groundPos_2.add(ground.getWidth() * 2, 0);
    }

    private void updateBackGround(){            // создание идущих друг за другом текстуры экрана
        if (camera.position.x - (camera.viewportWidth / 2) > BackgroundPos_1.x +ground.getWidth())
            BackgroundPos_1.add(ground.getWidth() * 2, 0);
        if (camera.position.x - (camera.viewportWidth / 2) > BackgroundPos_2.x +ground.getWidth())
            BackgroundPos_2.add(ground.getWidth() * 2, 0);
    }
}
