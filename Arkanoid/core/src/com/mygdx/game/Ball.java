/*
Ball.java
Usman Farooqi & Ghanem Ghanem
Ball class for the Arkanoid game
 */
package com.mygdx.game;
// importing modules
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.awt.*;

// ball class will control all the ball movements
public class Ball {
    //making public and private variables
    private static int x, y; // x and y
    public static int dx = 1, dy = 2; // direction for the ball
    public boolean play = false; // checks if the ball is touching paddle before or not
    private Texture img, over; // ball img texture
    static Sprite ball; // ball sprite
    private SpriteBatch batch; // spritebatch
    Paddle player; // this allows the class to use the player sprite from paddle class
    public static Rectangle rect; // makes a rectangle for the ball
    public static int speedx= 3; // speed of x direction
    public static int speedy= 3; // speed of y direction
    public static int lives = 3 ;// amount of lives player has
    BitmapFont font;// fonts

    //music / sounds
    private Sound dead;
    private Music gameover;

    // ball method takes in x y and the direction of the ball
    public Ball (int x, int y,int dx, int dy){
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        //making textures
        over = new Texture (Gdx.files.internal(("game-over.png" )));
        img = new Texture(Gdx.files.internal("ball.png"));
        ball = new Sprite(img); // making ball sprite
        batch = new SpriteBatch();
        //making ball rect
        font = new BitmapFont();
        // adding music
        dead = Gdx.audio.newSound(Gdx.files.internal("Assets/Soundeffects/dead.wav"));
        gameover = Gdx.audio.newMusic(Gdx.files.internal("Assets/Music/Gameover.mp3"));
    }
    //renders the ball
    public void render(int x, int y){
        batch.begin(); // begin drawing
        ball.setPosition(x,y); // set pos
        ball.draw(batch); // draw
        font.draw(batch, "Lives = " + lives, 100, 740);//display the amount of lives

        if (lives == 0){// if lives is 0 game over
            batch.draw(over,0,0,678,786);//added // draw gameover screen
            MyGdxGame.level1.stop(); // stop playing level 1 music
            gameover.play(); // play gameover music
        }
        batch.end(); // stop drawing
    }


    public void move() {
        // this method will move the ball and also render it on to the screen
        if(!play){ // if ball is touching paddle
            render(player.getX() + player.getWidth()/2 -2,player.getY() + player.height); // render it at the center of paddle
            x = player.getX() + player.getWidth()/2 - 2;
            y = player.getY() + player.height;
            dx = -1; dy = 2; // set the dx and dy
            if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
                play = true; // ball is off paddle
            }
        }
        if(play){
            dead.stop(); // stop the dead sound
            if(play && ball.getBoundingRectangle().overlaps(Paddle.player.getBoundingRectangle())){ // if ball collides with paddle
                dy = -dy; // change dy
            }

            if(Paddle.mag && ball.getBoundingRectangle().overlaps(Paddle.player.getBoundingRectangle())){
                ball.setPosition(player.getX() + player.getWidth()/2 - 2,player.getY() + Paddle.player.getHeight());
            }
            if(getX() < 25){  // if it hits a wall
                dx = -dx; // change x
            }
            if(getX() > 642){ // if it hits right wall
                dx = -dx; // change dx
            }
            if(getY() < 0){ // if drops
                play = false; // set ball to be on paddle
                MyGdxGame.powerup = ""; // set power up to empty
                dead.play(); // play sound
                lives -= 1; // take a live
           }

            if(getY() > 735){ // if it hits top wall
                dy = -dy;
            }
            x += dx*speedx; // add the direction plus speed to x
            y += dy*speedy; // add the direction plus speed to y
            render(x, y); // render at x, y
        }
    }

    public static int getX(){ // get the x
        return x;
    }

    public static int getY(){ // get the y
        return y;
    }

    public static int getDx(){ // get the x direction
        return dx;

    }

    public static int getDy(){ // get the y direction
        return dy;
    }

}
