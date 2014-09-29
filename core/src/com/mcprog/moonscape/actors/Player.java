package com.mcprog.moonscape.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class Player extends GameActor {

	private Vector3 target;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	private int spin;
	
	public Player(Body body) {
		super(body);
		target = new Vector3(40, 24, 0);
		batch = new SpriteBatch();
		texture = new Texture(Gdx.files.internal("player.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		sprite = new Sprite(texture);
		spin = 1;
		getBody().setUserData("player");
	}
	
	public Body getBody () {
		return body;
	}
	
	
	public void setTarget (int x, int y, OrthographicCamera camera) {
		camera.unproject(target.set(x, y, 0));
	}
	
	public void update (OrthographicCamera camera, World world) {
		float x = getBody().getPosition().x;
		float y = getBody().getPosition().y; 
		
		getBody().applyForceToCenter(target.x - x, target.y - y, true);
		getBody().setLinearDamping(.5f);

		if ((int)(x) == (int)(target.x) && (int)(y) == (int)(target.y)) {
			spin = -1;
		} else {
			spin = 1;
		}
		
		

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		sprite.rotate(spin);
		sprite.setCenter(x, y);
		sprite.setScale(.0625f, .0625f);
		sprite.draw(batch);
		batch.end();
		
	}
	
	
	

}
