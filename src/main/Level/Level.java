package main.Level;

import java.awt.Graphics;
import main.Handler;
import main.entity.EntityPortManager;
import main.world.World;

public abstract class Level {

	protected World world;
	protected Handler hand;
	protected EntityPortManager entiPort;
	
	public static Level curentLevel = null;
	
	public Level(Handler hand,World world){
		this.hand = hand;
		this.world = world;
		entiPort = new EntityPortManager(hand);
	}
	
	public static void setLevel(Level level){
		curentLevel = level;
	}
	public static Level getLevel(){
		return curentLevel;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	//getters and setters
	public World getWorld(){
		return world;
	}

	public EntityPortManager getEntiPort() {
		return entiPort;
	}
	
}



