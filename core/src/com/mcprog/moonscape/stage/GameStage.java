package com.mcprog.moonscape.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mcprog.moonscape.actors.Barrier;
import com.mcprog.moonscape.actors.Mountain;
import com.mcprog.moonscape.actors.Player;
import com.mcprog.moonscape.utility.Constants;
import com.mcprog.moonscape.utility.ContactHandler;
import com.mcprog.moonscape.utility.WorldUtils;

public class GameStage extends Stage {
	
	private World world;
	private Barrier barrier;
	private Player player;
	private Mountain mount1;
	
	private final float TIME_STEP = 1 / 60f;
	private float accumulator = 0f;
	
	private OrthographicCamera cam;
	private Box2DDebugRenderer renderer;
	
	public GameStage() {
		initWorld();
		initCam();
		renderer = new Box2DDebugRenderer();
		initInput();
	}

	private void initInput() {
		Gdx.input.setInputProcessor(this);
		
	}

	private void initWorld() {
		world = WorldUtils.createWorld();
		initBarrier();
		initObstacles();
		initPlayer();
		world.setContactListener(new ContactHandler());
	}

	private void initObstacles() {
		mount1 = new Mountain(WorldUtils.createMount(world, 15, 20));
		this.addActor(mount1);
	}

	private void initPlayer() {
		player = new Player(WorldUtils.createPlayer(world, Constants.VIEWPORT_WIDTH / 2, Constants.VIEWPORT_HEIGHT / 2));
//		player.getBody().setTransform(2, 2, 0);
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
			player.update(cam, world);
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
		player.setTarget(x, y, cam);
		return true;
	}
	
	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		player.setTarget(x, y, cam);
		return true;
	}

}
