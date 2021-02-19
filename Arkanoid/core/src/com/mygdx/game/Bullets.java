/*
Bullets.java
Usman Farooqi & Ghanem Ghanem
Bullets class for the Arkanoid game
 */

package com.mygdx.game;
//importing modules
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.awt.*;

// class bullets controls all the bullets methods and fucntions
public class Bullets {
    // making final int speed and default y so they cant be changes
    public static final int speed = 10;
    public static final int defaultY = 12;
    private static Texture img; // texture
    float x,y; // x and y
    static Sprite bullet; // bullet as a sprite
    public Rectangle bulletrect; // bullet rect

    //public bullet method takes in x
    public Bullets (float x){
        this.x = x;
        this.y = defaultY;
        // makes rectangle
        bulletrect = new Rectangle((int)x, (int) y,3,9);
        img = new Texture("bullet.png"); // new texture
        bullet = new Sprite(img); // make sprite

    }
    //render
    public void render(SpriteBatch batch){
        bulletrect = new Rectangle((int)x, (int) y,3,9);
        bullet.draw(batch);
    }
    //update
    public void update(SpriteBatch batch){
        y += speed; // increase the y of bullet
        bullet.setPosition(x,y);
        this.render(batch);
    }
    // get the rectangle
    public Rectangle getRect(){
        return bulletrect;
    }
}
