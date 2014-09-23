package com.mcprog.moonscape.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.ACubemap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mcprog.moonscape.actors.Barrier;
import com.mcprog.moonscape.actors.Player;
import com.mcprog.moonscape.utility.Constants;
import com.mcprog.moonscape.utility.WorldUtils;

public class GameStage extends Stage {
	
	private World world;
	private Barrier barrier;
	private Player player;
	
	private final float TIME_STEP = 1 / 60f;
	private float accumulator = 0f;
	
	private OrthographicCamera cam;
	private Box2DDebugRenderer renderer;
	private Vector3 touchPoint;//last coor is z = 0
	
	public GameStage() {
		initWorld();
		initCam();
		renderer = new Box2DDebugRenderer();
		initInput();
	}

	private void initInput() {
		Gdx.input.setInputProcessor(this);
		touchPoint = new Vector3();
		
	}

	private void initWorld() {
		world = WorldUtils.createWorld();
		initBarrier();
		initPlayer();
	}

	private void initPlayer() {
		player = new Player(WorldUtils.createPlayer(world, Constants.VIEWPORT_WIDTH / 2, Constants.VIEWPORT_HEIGHT / 2));
		this.addActor(player);
	}

	private void initBarrier() {
		barrier = new Barrier(WorldUtils.createBarrier(world));
		this.addActor(barrier);
	}

	private void initCam () {
		cam = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
		cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0);
		cam.update();
		System.out.println(cam.position);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		accumulator += delta;
		
		while (accumulator >= delta) {
			world.step(TIME_STEP, 6, 2);
			accumulator -= TIME_STEP;
		}
	}
	
	@Override
	public void draw() {
		super.draw();
		renderer.render(world, cam.combined);
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		translateScreenToWorldCoordinates(x, y);
		System.out.println(x + " = " + touchPoint.x + cam.viewportWidth / 2 + ", " + y + " = " + (cam.viewportHeight - touchPoint.y));
		double sinOverCos = Math.abs(y) / Math.abs(x);
		player.getBody().setTransform(player.getBody().getPosition(), (float) (Math.tan(sinOverCos)));
		return true;
	}
	
	/**
	 * Modifies arguments so that match up with world coordinates
	 * @param x
	 * @param y
	 */
	private void translateScreenToWorldCoordinates (int x, int y) {
		cam.unproject(touchPoint.set(x, y, 0));
	}
}
