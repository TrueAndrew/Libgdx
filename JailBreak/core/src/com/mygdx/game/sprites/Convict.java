package com.mygdx.game.sprites;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

public class Convict {

    private static final int SPEED = 350;           // скорость перемещения экрана
    private static final int GRAVITY = -49;         // создание искусственной гравитации
    private Vector3 position;
    private Vector3 velosity;
    private Texture texture;
    private Rectangle bounds;
    private Animation convictAnimation;

    public Convict(int x,int y){
        position = new Vector3(x,y,0);
        velosity = new Vector3(0,0,0);
        texture = new Texture("Convict_Go.psd");
        convictAnimation = new Animation(new TextureRegion(texture),4,0.4f);            // анимация текстуры
        bounds = new Rectangle(x , y , texture.getWidth()/4 , texture.getHeight());
    }

    public TextureRegion getConvict() {
        return convictAnimation.getFrame();
    }

    public Vector3 getPosition() {
        return position;
    }

    public void update(float dt){                               // Основное движение модельки с ограничениями по высоте
        convictAnimation.update(dt);
        if (position.y > 30 || position.y <380)
            velosity.add(0,GRAVITY,0);
        velosity.scl(dt);
        position.add(SPEED * dt, velosity.y, 0);
        velosity.scl(1 / dt);
        if (position.y <30){
            position.y = 30;
        }
        if (position.y >380){
           position.y = 380;
        }
        bounds.setPosition(position.x, position.y);
    }

    public void jump(){  velosity.y = 780; }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose(){
        texture.dispose();
    }
}
