/*
Paddle.java
Usman Farooqi & Ghanem Ghanem
Paddle/player class for the Arkanoid game
 */
package com.mygdx.game;
// import modules
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// paddle class will control everything for player
public class Paddle {
    //making public and private variables
    private static int x, y; // x and y
    public static int height; // height of player
    public static String powerup; // powerup
    public static boolean mag = false; // if magnet is true or not
    private Texture img; // img
    static Sprite player; // sprite
    Ball ball; // allows paddle class to access the ball from ball class

    // paddle method takes in an x and y
    public Paddle (int x, int y){
        this.x = x;
        this.y = y;
        this.height = 12;
        Paddle.powerup = ""; // def power is set to nothing
        img = new Texture("player1.png"); // player img
        player = new Sprite(img);
    }

    //rendering the player
    public void render(SpriteBatch batch){
        player.draw(batch);

    }
    // updating the player (x, y, type of powerup)
    public void update(SpriteBatch batch, int x, int y, String powerup){

        if(powerup.equals("")){ // if power up is nothing then reset all
            player.setSize(64,12);
            ball.speedy = 3;
            ball.speedx = 3;
            mag = false;
            player.setPosition(x,y);
            render(batch);
        }

        else if(powerup.equals("expand")){ // if power up is expand then expand the player
            player.setSize(100,12);
            player.setPosition(x,y);
            render(batch);
        }

        else if(powerup.equals("magnet")){ // if power up is magnet then allow ball to stay on paddle
            mag = true;
            player.setPosition(x,y);
            render(batch);
        }

        else if(powerup.equals("speed")){ // if power up is speed then increase the speed of ball
            ball.speedx = 5;
            ball.speedy = 5;
            player.setPosition(x,y);
            render(batch);
        }

        else if(powerup.equals("slow")){ // if power up is slow then slow down the ball
            ball.speedx = 2;
            ball.speedy = 2;
            player.setPosition(x,y);
            render(batch);
        }

        else if(powerup.equals("laser")){ // if laser is the powerup then get bullets working
            player.setPosition(x,y);
            render(batch);
        }
        else if(powerup.equals("player")){ // if player is power up add life
            player.setPosition(x,y);
            render(batch);
        }


    }

    // gets the x of player
    public static int getX(){
        return x;
    }

    // gets the y of player
    public static int getY(){
        return y;
    }

    // sets the x of player
    public void setX(int x){
        Paddle.x = x;
    }

    // sets the y of player
    public static int getWidth(){
        return (int) player.getWidth();
    }

}