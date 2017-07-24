package main.entity.statics.water.brige;

import java.awt.Graphics;

import main.Handler;
import main.entity.statics.water.Water;

public abstract class Brige extends Water {

	public Brige(Handler hand,int id, float x, float y) {
		super(hand,id, x, y);
		//Bounds
		bounds.width = 0;
		bounds.height = 0;
	
	}

	@Override
	public void tick() {
		waterAnim.tick();
		
		if(frozen){
			iceDecay();
		}
		checkFreeze();
		checkForIceBelow();
		checkFreezeSound();
	}
	
	@Override
	protected abstract void renderWaterBorder(Graphics g);

}
