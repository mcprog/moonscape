package com.mcprog.moonscape;

import com.badlogic.gdx.Game;
import com.mcprog.moonscape.screen.GameScreen;

public class Moonscape extends Game {

	@Override
	public void create () {
		setScreen(new GameScreen());
	}

}
