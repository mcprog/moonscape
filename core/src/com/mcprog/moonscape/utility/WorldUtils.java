package com.mcprog.moonscape.utility;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
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
        CircleShape shape = new CircleShape();
        shape.setRadius(2);
        Body body = world.createBody(bodyDef);
        body.createFixture(shape, .5f);
        body.resetMassData();
        shape.dispose();
        body.getFixtureList().get(0).setUserData("player");
        return body;
    }
    
    public static Body createMount (World world, int x, int y) {
    	BodyDef bodyDef = new BodyDef();
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        Vector2[] mountCorners = {
        		new Vector2(x, (float) (y + Math.sqrt(3))), 
        		new Vector2(x - 2, (float) (y - Math.sqrt(3))), 
        		new Vector2(x + 2, (float) (y - Math.sqrt(3)))
        		};
        shape.set(mountCorners);
        body.createFixture(shape, 0);
        shape.dispose();
        return body;
    }
}
