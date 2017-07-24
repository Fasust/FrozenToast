package main.entity.dynamic.push;

import java.awt.Graphics;

import main.Handler;
import main.gfx.Assets;
import main.id.IdManager;
import main.tile.Tile;

public class DeadGreenFish extends PushEntity {
	

	public DeadGreenFish(Handler hand, float x, float y) {
		super(hand,IdManager.DeadGreenFish, x, y,Tile.TILEBREITE,Tile.TILEHoeHE);
		hight = 3;
	}
	public void render(Graphics g) {
		
		g.drawImage(Assets.fish_green_dead,(int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
	}
}