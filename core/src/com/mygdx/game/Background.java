package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Background {
    private Texture assetImage;
    private Rectangle rectangle;


    public Background(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
    public void setWidthAndHeight() {
        rectangle.setWidth(600);
        rectangle.setHeight(500);
    }
    public void setPosXAndPosY(){
        rectangle.setX(0);
        rectangle.setY(0);
    }
    public void setImage(Texture texture){
        this.assetImage = texture;
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
    }
    public Texture getImage(){
        return assetImage;
    }

    public float getX(){
        return rectangle.x;
    }
    public float getY(){
        return rectangle.y;
    }



}
