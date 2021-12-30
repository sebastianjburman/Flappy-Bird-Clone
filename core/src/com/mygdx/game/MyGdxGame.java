package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	private OrthographicCamera camera;
	Background background = new Background(new Rectangle());
	Player player = new Player(new Rectangle());
	Texture topPipe;
	Texture bottomPipe;
	boolean pipesMoving = true;
	ArrayList<Rectangle[]> allPipes = new ArrayList<Rectangle[]>();
	private long lastPipeTime;
	private int score;
	private String scoreName;
	BitmapFont yourBitmapFontName;


	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false,500,500);
		batch = new SpriteBatch();

		background.setImage(new Texture(Gdx.files.internal("background.png")));
		background.setWidthAndHeight();
		background.setPosXAndPosY();

		player.setImage(new Texture(Gdx.files.internal("character.png")));
		player.setWidthAndHeight(80,80);
		player.setPosXAndPosY(250,300);

		topPipe = new Texture(Gdx.files.internal("topPipe.png"));
		bottomPipe = new Texture(Gdx.files.internal("bottomPipe.png"));

		score = 0;
		scoreName = "Score: 0";
		yourBitmapFontName = new BitmapFont();




	}
	private void spawnPipes() {
		Rectangle[] pipePair = new Rectangle[2];
		Rectangle topPipe = new Rectangle();

		int randomY = MathUtils.random(0,60);

		topPipe.width = 90;
		topPipe.height = 270;
		topPipe.x = 550;
		topPipe.y = 310 - randomY;

		Rectangle bottomPipe = new Rectangle();
		bottomPipe.width = 90;
		bottomPipe.height = 270;
		bottomPipe.x = 550;
		bottomPipe.y = -100 - randomY;

		pipePair[0] = topPipe;
		pipePair[1] = bottomPipe;

		allPipes.add(pipePair);
		lastPipeTime = TimeUtils.nanoTime();
	}

	private void checkIfPlayerHitsPipe(Rectangle topPipe, Rectangle bottomPipe){
		if (player.getRectangle().overlaps(topPipe)||player.getRectangle().overlaps(bottomPipe)){
			player.setGravityStrength(5);
			pipesMoving = false;
		}
	}

	private void newGame() {
		if (Gdx.input.isKeyPressed(Input.Keys.R) && pipesMoving == false  ){
			player.setPosXAndPosY(250,300);
			allPipes.clear();
			score = 0;
			scoreName = "Score: " + score;
			player.setGravityStrength(3);
			pipesMoving = true;
		}
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();

		batch.draw(background.getImage(),background.getX(),background.getY());


		for (int i = 0; i <allPipes.size(); i++) {
			Rectangle pipe1 = allPipes.get(i)[0];
			Rectangle pipe2 = allPipes.get(i)[1];
			batch.draw(topPipe, pipe1.x,pipe1.y);
			batch.draw(bottomPipe, pipe2.x,pipe2.y);
			if (pipesMoving == true){
				pipe1.x -= 5;
				pipe2.x-= 5;
			}
			checkIfPlayerHitsPipe(pipe1,pipe2);

		}
		yourBitmapFontName.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		yourBitmapFontName.draw(batch, scoreName, 500, 480);

		batch.draw(player.getImage(), player.getX(),player.getY());

		batch.end();

		//Check if can spawn new pipe
		if (pipesMoving == true){
			//Spawns every second
			if(TimeUtils.nanoTime() - lastPipeTime > 1000000000) spawnPipes();
			score++;
			scoreName = "Score: " + score;
		}


		player.playerGravity();
		player.playerFlight(pipesMoving);

		//Delete pipes that are off screen
		for (int i = 0; i < allPipes.size();i++){
			Rectangle pipe1 = allPipes.get(i)[0];
			Rectangle pipe2 = allPipes.get(i)[1];
			if (pipe1.x < -10 && pipe2.x < -90){
				allPipes.remove(i);
			}
		}
		newGame();


	}
	
	@Override
	public void dispose () {
		background.getImage().dispose();
		player.getImage().dispose();
		topPipe.dispose();
		bottomPipe.dispose();
		batch.dispose();

	}
}

