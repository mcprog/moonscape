package com.mcprog.moonscape.actors;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;

public class Player extends GameActor {

	private Vector3 target;
	private float targetAngle = -666;
	
	public Player(Body body) {
		super(body);
		target = new Vector3();
	}
	
	public Body getBody () {
		return body;
	}
	
	public void pointAt (int x, int y, OrthographicCamera camera) {
		camera.unproject(target.set(x, y, 0));
//		if () {
//			
//		}
		double sinOverCos = (target.y - getBody().getPosition().y) / (target.x - getBody().getPosition().x);
		targetAngle = (float) (Math.atan(sinOverCos) + Math.PI / 2f);
		System.out.println("Target angle " + Math.toDegrees(targetAngle));
		System.out.println(target.x - getBody().getPosition().x + ", " + (target.y - getBody().getPosition().y));
		jumpTo(targetAngle);
	}
	
	public void jumpTo (float rotation) {
		if (targetAngle < 0) {
			targetAngle += Math.PI * 2f;
		}
		getBody().setTransform(getBody().getPosition(), rotation);
	}
	
	public void update () {
		float x = getBody().getPosition().x;
		float y = getBody().getPosition().y; 
		if (x < target.x) {
			x += .1f;
		}
		if (x > target.x) {
			x -= .1f;
		}
		if (y < target.y) {
			y += .1f;
		}
		if (y > target.y) {
			y -= .1f;
		}
		
		getBody().setTransform(x, y, getBody().getAngle());
		
	}
	
	
	

}
