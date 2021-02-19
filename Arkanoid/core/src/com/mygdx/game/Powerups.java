/*
Powerups.java
Usman Farooqi & Ghanem Ghanem
powerup class for the Arkanoid game
 */
package com.mygdx.game;

// importing modules
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.awt.*;
import java.util.Random;

//powerups class controls all the methods and functions needed for powerups to work
public class Powerups {
    static Sprite power;
    private Texture sprite;
    public Rectangle rect;
    private static int x,y,type,speed;
    Random rand = new Random();
    public Bricks bricks;
    public int vx,vy;

    //power up method
    public Powerups(){
        // makes a new texture
        Texture [] img = {new Texture("powerups/greenpowerup.png"), new Texture("powerups/orangepowerup.png"), new Texture("powerups/pinkpowerup.png"), new Texture("powerups/bluepowerup.png"), new Texture("powerups/greypowerup.png"), new Texture("powerups/redpowerup.png")};
        type = rand.nextInt(img.length); // random number that also gets the index (powerup)
        sprite = img[type]; // an image texture based on the index
        power  = new Sprite(sprite); // makes a new sprite
        // fall speed
        speed = 3; // speed of the falling powerups
        x = MyGdxGame.deadx; // initial x pos of powerup
        y = MyGdxGame.deady; // initial y pos of powerup
        power.setX(x);
        power.setY(y);
        // make a rectangle for power up
        rect = new Rectangle((int)power.getX(), (int)power.getY(),(int) power.getWidth(),(int) power.getHeight());
    }
    // render
    public void render(SpriteBatch batch){
        power.draw(batch);
    }

    //update
    public void update(SpriteBatch batch) {
        power.setY(power.getY() - speed);
        rect = new Rectangle((int)power.getX(), (int)power.getY(),(int) power.getWidth(),(int) power.getHeight());
        this.render(batch);
    }
     // check for colliision with the player
    public boolean collide(Paddle player){
        return Paddle.player.getBoundingRectangle().overlaps(power.getBoundingRectangle());
    }
    // gets the rects
    public Rectangle getRect(){
        return rect;
    }

    // gets the type of power up
    public int getType (){
        return type;
    }

}
