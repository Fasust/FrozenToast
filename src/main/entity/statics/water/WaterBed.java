package main.entity.statics.water;

import java.awt.Graphics;

import main.Handler;
import main.gfx.Assets;
import main.id.IdManager;

public class WaterBed extends Water{

	public WaterBed(Handler hand, float x, float y) {
		super(hand,IdManager.WaterBed, x, y);

	}
	@Override
	protected void renderWaterBorder(Graphics g) {
		
	}
}
