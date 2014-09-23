package com.mcprog.moonscape.physics;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mcprog.moonscape.utility.Constants;


public class Barrier {

	private BodyDef definition;
	private Body body;
	private ChainShape shape;
	private final float[] corners = {
			0, 0, 
			0, Constants.VIEWPORT_HEIGHT, 
			Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT, 
			Constants.VIEWPORT_WIDTH, 0	
	};
	
	public Barrier(World world) {
		definition = new BodyDef();
		body = world.createBody(definition);
		shape = new ChainShape();
		shape.createLoop(corners);
		body.createFixture(shape, 0);
		shape.dispose();
	}
	
	public Body getBody () {
		return body;
	}
}
