package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {
    private Array<TextureRegion> frames;    // массив текстур, где хранятся кадры анимации
    private float maxFrameTime;             // длительность отображения одного кадра
    private float currentFrameTime;         // время отображения текущего кадра
    private int frameCount;                 // количество кадров анимации
    private int frame;                      // отдельный кадр


    // конструктор с регионом текстур,количество кадров и длительность цикла анимации
    public Animation(TextureRegion region,int frameCount, float cycleTime){
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount;          // ширина отдельного кадра
        for (int i = 0; i<frameCount; i++){                             // прогон по всем кадрам и отрисовка каждого
            frames.add(new TextureRegion(region , i*frameWidth , 0 , frameWidth , region.getRegionHeight()));
        }
        this.frameCount = frameCount;                                   // кол-во кадров
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    public void update(float dt){                                       // обновление кадров, то есть их смена
        currentFrameTime += dt;
        if (currentFrameTime > maxFrameTime){
            frame++;
            currentFrameTime = 0;
        }
        if (frame >= frameCount){
            frame = 0;
        }
    }

    public TextureRegion getFrame(){
        return frames.get(frame);
    }
}