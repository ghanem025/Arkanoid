/*
Brick.java
Usman Farooqi & Ghanem Ghanem
Brick class for the Arkanoid game
 */

package com.mygdx.game;
//importing modules
import java.awt.*;
import java.util.Random;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//this class controls all the methods and functions needed for bricks
public class Bricks{
    // making public and private variables
    private Sprite brick; // bricks as a sprite
    private Texture blueBrick, greenBrick, greyBrick, redBrick, pinkBrick, orangeBrick, yellowBrick; // textures
    public Rectangle rect; // rects for bricks
    public boolean gone = false; // gone is a boolean that determines if the specific brick is removed or not

    //brick method takes in the type of brick and its x and y location
    public Bricks(String type, int x, int y){
        //loading all textures to the object array list
        blueBrick = new Texture("Bricks/bluebrick.png");
        greenBrick = new Texture("Bricks/greenbrick.png");
        greyBrick = new Texture("Bricks/greybrick.png");
        redBrick = new Texture("Bricks/redbrick.png");
        pinkBrick = new Texture("Bricks/pinkbrick.png");
        orangeBrick = new Texture("Bricks/orangebrick.png");
        yellowBrick = new Texture("Bricks/yellowbrick.png");

        //checks which type of brick is passed through and makes the corrisponding sprite
        if(type.equals("blue")){
            brick = new Sprite(blueBrick);
        }
        else if (type.equals("green")){
            brick = new Sprite(greenBrick);
        }
        else if (type.equals("grey")){
            brick = new Sprite(greyBrick);
        }
        else if (type.equals("red")){
            brick = new Sprite(redBrick);
        }
        else if (type.equals("pink")){
            brick = new Sprite(pinkBrick);
        }
        else if (type.equals("orange")){
            brick = new Sprite(orangeBrick);
        }
        else if (type.equals("yellow")){
            brick = new Sprite(yellowBrick);
        }
        // sets the sprite bricks x and y
        brick.setPosition((40 + x * 42)  , 705 - y * 20);
        // creates a rect used for collision etc.
        rect = new Rectangle((int) brick.getX(), (int) brick.getY(), (int) brick.getWidth(), (int) brick.getHeight());
    }


    // renders the bricks
    private void render(SpriteBatch batch){
        rect = new Rectangle((int) brick.getX(), (int) brick.getY(), (int) brick.getWidth(), (int) brick.getHeight());
        brick.draw(batch);
    }

    // updates the sprites to make the multiple bricks
    public void update(SpriteBatch batch){
        // if the specific brick is gone
        if(this.getGone()){
            brick.setAlpha(0); // make it invisible

        }
        this.render(batch); // update
    }

    //used for getting the rectanlge
    public Rectangle getRect(){
        return rect;
    }
    // returns boolean upon checking for collision with the ball
    public boolean collide (Ball ball){
        return Ball.ball.getBoundingRectangle().overlaps(brick.getBoundingRectangle()) && !this.getGone();

    }
    // returns boolean upon checking for collision with the bullet
    public boolean bulletcollide(Bullets bullet){
        return bullet.getRect().intersects(this.getRect()) && !this.getGone();

    }
    // get method for the gone boolean
    public boolean getGone(){
        return gone;
    }
    // set method for the gone boolean (used in the main file)
    public void setGone(boolean gone){
        this.gone = gone;

    }
}
