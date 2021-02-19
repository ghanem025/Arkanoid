/*
MyGdxGame.java
Usman Farooqi & Ghanem Ghanem
Main class for the Arkanoid game
Simple game Arkanoid - user controls a paddle to destroy bricks and gain points
 */

//import modules
package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.Random;

//main method controls everything
public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch; // spritebatch
	Paddle player; // player accessed from paddle
	Ball ball; // ball accessed from ball class
	Powerups power; // powerups accesed from powerup class
	Texture texture, menu, over; // textures
	public int x = 291; // initial x of paddle
	int score =0 ; // score
	public static int deadx, deady; // this is used to locate where the brick is hit (for powerup)
	public static String [] powerups = new String[]{"magnet", "slow", "speed", "expand", "player","laser"}; // list of all the powerups
	public static String powerup = ""; // powerup

	// Object arrays
	ArrayList<Bullets> bullets;
	public static ArrayList<ArrayList<Bricks>> bricklist = new ArrayList<ArrayList<Bricks>>();
	ArrayList<Powerups>POWERUPS = new ArrayList<Powerups>();

	// random numbers
	Random rand = new Random();
	public boolean hit = false;
	public boolean running = true;

	// fonts
	BitmapFont font;
	boolean game = false;
	private int dx = 1;
	// music
	public static Music music, level1;
	public Sound powersound;

	@Override
	public void create() { // creates
		font = new BitmapFont();
		menu = new Texture(Gdx.files.internal("menu.png"));
		texture = new Texture(Gdx.files.internal("Arkanoid1.png"));
		batch = new SpriteBatch();
		player = new Paddle(291,0);
		power = new Powerups();
		ball = new Ball(291, 10,ball.getDx(),ball.getDy());
		bullets = new ArrayList<Bullets>();
		music = Gdx.audio.newMusic(Gdx.files.internal("Assets/Music/ArkanoidMenu.mp3"));
		level1 = Gdx.audio.newMusic(Gdx.files.internal("Assets/Music/level1.mp3"));
		powersound = Gdx.audio.newSound(Gdx.files.internal("Assets/Soundeffects/powerup.wav"));
		over = new Texture (Gdx.files.internal(("game-over.png" )));

		// creates a 2d Arraylist that stores the locations and the sprites of all the bricks
		for(int i = 0; i < 7; i ++){
			ArrayList<Bricks> bricks = new ArrayList<Bricks>();
			for(int j = 0; j < 14; j ++){
				if(i == 0) bricks.add(new Bricks("grey", j,i));
				if(i == 1) bricks.add(new Bricks("red",j,i));
				if(i == 2) bricks.add(new Bricks("yellow",j,i));
				if(i == 3) bricks.add(new Bricks("blue",j,i));
				if(i == 4) bricks.add(new Bricks("pink",j,i));
				if(i == 5) bricks.add(new Bricks("orange",j,i));
				if(i == 6) bricks.add(new Bricks("green",j,i));

			}
			bricklist.add(bricks);
		}
	}

	@Override
	public void render() { // render
		if(running) {
			batch.begin(); // begin drawing
			if (score == 9800) {// if the score is equal to 9800 then game over
				running = false;//added
			}
			if (score == 0) {
				batch.draw(menu, 0, 0, 672, 768);//added // if not then draw menu screen
				font.draw(batch, "By: Usman and Ghanem", 585, 15, 10, 5, false);
				music.play();
			}
			batch.end();//added
			if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {// if enter is pressed then play
				game = true;

			}//added
			if (game == true) { // if game equal play
				// play game music and stop main music
				music.stop();
				level1.play();
				Gdx.gl.glClearColor(0, 0, 0, 1);
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

				if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.getX() < 582) {
					x += 5;
				} else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.getX() > 25) {
					x -= 5;
				}

				//bullets
				if (powerup.equals("laser") && Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) { // if powerup is laser
					bullets.add(new Bullets(player.getX() + 2)); // add bullet to left
					bullets.add(new Bullets(player.getX() + player.getWidth() - 2)); // add bullet to right

				}
				player.setX(x); // set the paddle to centre

				batch.begin(); // start drawing

				batch.draw(menu, 0, 0, 672, 768);
				if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
					game = true;
				}
				if (game = true) {
					batch.draw(texture, 0, 0, 672, 768);
					font.draw(batch, "score = " + score, 300, 740);
				}
				// randomally drops a powerup upon collision
				int drop = rand.nextInt(1000);
				if (drop < 380 && hit && POWERUPS.size() == 0) { // 38% chance of power up dropping
					POWERUPS.add(new Powerups()); // makes a power up
				} else {
					hit = false; // if collision is false
				}
				// updates the power ups (removing, adding etc.)
				for (int m = 0; m < POWERUPS.size(); m++) {
					POWERUPS.get(m).update(batch);
					if (POWERUPS.get(m).collide(player)) {
						powerup = powerups[power.getType()];
						POWERUPS.remove(m);
						powersound.play();
						hit = false;
					} else if (POWERUPS.get(m).getRect().y + POWERUPS.get(m).getRect().height < 0) {
						POWERUPS.remove(m);

						hit = false;
					} else {
						powersound.stop(); // powerup sound
					}
				}
				if (powerup.equals("player")) { // if the powerup == player then increase lives by only 1
					ball.lives += 1; // increase live by 1
					powerup = ""; // set power up to nothing
				}
				// draw/update the player
				player.update(batch, x, player.getY(), powerup);

				//checks collisions and removes bricks in the 2d bricks Arraylist
				for (int i = 0; i < bricklist.size(); i++) {
					for (int j = 0; j < bricklist.get(i).size(); j++) {
						if (bricklist.get(i).get(j).collide(ball)) {
							ball.dy = -2;
							dx = -dx;
							ball.dx = dx;
							score += 100;
							hit = true;
							deadx = bricklist.get(i).get(j).rect.x;
							deady = bricklist.get(i).get(j).rect.y;
							bricklist.get(i).get(j).setGone(true);
						}
						for (int n = 0; n < bullets.size(); n++) {
							if (bricklist.get(i).get(j).bulletcollide(bullets.get(n))) {
								bullets.remove(n);
								score += 100;
								hit = true;
								deadx = bricklist.get(i).get(j).rect.x;
								deady = bricklist.get(i).get(j).rect.y;
								bricklist.get(i).get(j).setGone(true);
							}
						}

						bricklist.get(i).get(j).update(batch);
					}
				}
				//update
				for (int i = 0; i < bullets.size(); i++) {
					bullets.get(i).render(batch);
					bullets.get(i).update(batch);
					if (bullets.get(i).y > Gdx.graphics.getHeight()) {
						bullets.remove(i);
					}
				}
				batch.end();
				ball.move(); // this will call the move method in the ball class
			}
		}
		else if(!running){
			batch.begin();
			batch.draw(over, 0, 0, 678, 768);//added // draw the game over texture
			score = 0;//added

			batch.end();

		}
	}

	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
		music.dispose();
	}
}