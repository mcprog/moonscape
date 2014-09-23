package com.mcprog.moonscape.stage;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g3d.shaders.DefaultShader.Setters.ACubemap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mcprog.moonscape.physics.Barrier;
import com.mcprog.moonscape.utility.Constants;

public class GameStage extends Stage {
	
	private World world;
	private Body barrier;
	
	private final float TIME_STEP = 1 / 60f;
	private float accumulator = 0f;
	
	private OrthographicCamera cam;
	private Box2DDebugRenderer renderer;
	
	public GameStage() {
		world = new World(Vector2.Zero, true);
		barrier = new Barrier(world).getBody();
		renderer = new Box2DDebugRenderer();
		initCam ();
	}

	private void initCam () {
		cam = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
		cam.position.set(cam.viewportWidth / 2, cam.viewportHeight / 2, 0);
		cam.update();
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
}
