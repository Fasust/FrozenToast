package main.entity.statics;

import main.Handler;
import main.entity.Entity;

public abstract class StaticEntity extends Entity {
	
	public StaticEntity(Handler hand,int id, float x, float y, int breite, int hoehe) {
		super(hand,id, x, y, breite, hoehe);
	}
	
	
}
