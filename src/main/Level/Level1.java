package main.Level;

import java.awt.Graphics;

import main.Handler;
import main.world.World;

public class Level1 extends Level {

	public Level1(Handler hand) {
		super(hand,new World(hand,"res/worlds/world1"));
	}

	@Override
	public void tick() {
		world.tick();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
	}

}
