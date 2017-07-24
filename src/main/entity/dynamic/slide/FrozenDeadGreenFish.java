package main.entity.dynamic.slide;

import java.awt.Graphics;
import main.Handler;
import main.entity.dynamic.slide.SlideEntity;
import main.gfx.Assets;
import main.id.IdManager;
import main.tile.Tile;

public class FrozenDeadGreenFish extends SlideEntity {
	

	public FrozenDeadGreenFish(Handler hand, float x, float y) {
		super(hand,IdManager.FrozenDeadGreenFish, x, y,Tile.TILEBREITE,Tile.TILEHoeHE);
		
		hight = 3;
	}
	public void render(Graphics g) {
		
		g.drawImage(Assets.fish_green_dead_frozen,(int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
		/*
		g.setColor(Color.BLUE);
		g.fillRect((int)(this.getCheckbox(0, 0).x-hand.getCam().getxOffset()),
				(int) (this.getCheckbox(0, 0).y-hand.getCam().getyOffset()),
				this.getCheckbox(0, 0).width, 
				this.getCheckbox(0, 0).height);
		
		g.setColor(Color.red);
		g.fillRect((int)(this.getCollisionBounds(0, 0).x-hand.getCam().getxOffset()),
				(int)(this.getCollisionBounds(0, 0).y-hand.getCam().getyOffset()), 
				this.getCollisionBounds(0, 0).width, 
				this.getCollisionBounds(0, 0).height);
		*/
		
		
	}
}
