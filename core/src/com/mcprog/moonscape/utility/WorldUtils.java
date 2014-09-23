package com.mcprog.moonscape.utility;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class WorldUtils {

	private static float[] corners = {
			0, 0, 
			0, Constants.VIEWPORT_HEIGHT, 
			Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT, 
			Constants.VIEWPORT_WIDTH, 0	
	};
	
	public static World createWorld() {
        return new World(Vector2.Zero, true);
    }

    public static Body createBarrier(World world) {
        BodyDef bodyDef = new BodyDef();
        Body body = world.createBody(bodyDef);
        ChainShape shape = new ChainShape();
        shape.createLoop(corners);
        body.createFixture(shape, 0);
        shape.dispose();
        return body;
    }
    
    public static Body createPlayer(World world, int x, int y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(x, y));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1.5f, 2);
        Body body = world.createBody(bodyDef);
        body.createFixture(shape, .5f);
        body.resetMassData();
        shape.dispose();
        return body;
    }
}
