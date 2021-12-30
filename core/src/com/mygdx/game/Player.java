package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Player {
    private Texture assetImage;
    private Rectangle rectangle;
    private int gravityStrength = 3;

    public Player(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
    public void setWidthAndHeight(int width,int height) {
        rectangle.setWidth(width);
        rectangle.setHeight(height);

    }
    public void setPosXAndPosY(int posX,int posY){
        rectangle.setX(posX);
        rectangle.setY(posY);
    }

    public void setGravityStrength(int strength){
        this.gravityStrength = strength;
    }

    public void setImage(Texture texture){
        this.assetImage = texture;
    }
    public Texture getImage(){
        return assetImage;
    }
    public Rectangle getRectangle(){
        return rectangle;
    }

    public float getX(){
        return rectangle.x;
    }
    public float getY(){
        return rectangle.y;
    }

    public void playerGravity(){
        if ( rectangle.getY() > 0){
            rectangle.y -= gravityStrength;
        }
    }
    public void playerFlight(boolean pipesMoving){
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && rectangle.y < 430 && pipesMoving == true  ){
            rectangle.y += 5;
        }
    }

}
