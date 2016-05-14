package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Police {

    public static final int POLICE_WIDTH = 105;             // основные параметры обьекта-полицейского
    public static final int FLUCTUATION = 200;
    public static final int POLICE_GAP = 270;

    private Texture police;
    private Vector2 posPolice;
    private Random random;

    private Rectangle boundsPolice;

    public Texture getPolice() {
        return police;
    }

    public Vector2 getPosPolice() {
        return posPolice;
    }

    public Police(float x){
        police = new Texture("Police.psd");
        random = new Random();

        posPolice = new Vector2(x,random.nextInt(FLUCTUATION)-POLICE_GAP);

        boundsPolice = new Rectangle(posPolice.x,posPolice.y,police.getWidth(),police.getHeight());
    }

    public void reposition(float x){
        posPolice.set(x,random.nextInt(FLUCTUATION)-POLICE_GAP);
        boundsPolice.setPosition(posPolice.x,posPolice.y);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsPolice);
    }

    public void dispose() {
        police.dispose();
    }
}
