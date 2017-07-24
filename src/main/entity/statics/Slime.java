package main.entity.statics;

import java.awt.Graphics;
import main.Handler;
import main.entity.Entity;
import main.entity.creature.Creature;
import main.entity.dynamic.slide.SlideEntity;
import main.gfx.Assets;
import main.id.IdManager;
import main.tile.Tile;


public class Slime extends StaticEntity {
	private boolean temp = false;

	public Slime(Handler hand, float x, float y) {
		super(hand,IdManager.Slime, x*Tile.TILEBREITE, y*Tile.TILEHoeHE, Tile.TILEBREITE, Tile.TILEHoeHE);
		
		hight = 2;
		
		//hitbox
		bounds.x = 0;
		bounds.y = 0;
		bounds.width=0;
		bounds.height = 0;
		//Checkbox
		checkbox.x = 0;
		checkbox.y = 0;
		checkbox.width= Tile.TILEBREITE;
		checkbox.height = Tile.TILEHoeHE;
		
	}

	@Override
	public void tick() {
		if(isThisCheckboxCollidingWithEntityBounds()){
			Entity e = getEntityCollidingWithCheckbox();
			if(e == hand.getWorld().getEnti().getPl()){
				hand.getWorld().getEnti().getPl().setSpeed(1);
				hand.getWorld().getEnti().getPl().setAnimSpeed(1000);
				temp = true;
			}	
			if(e instanceof SlideEntity){
				SlideEntity es =(SlideEntity) e;
				es.setSlideSpeed(1);
				e = es;
			}	
		}
		if(temp && !isThisCheckboxCollidingWithEntityBounds()){
			hand.getWorld().getEnti().getPl().setSpeed(Creature.DEF_SPEED);
			hand.getWorld().getEnti().getPl().setAnimSpeed(Creature.DEF_ANIM_SPEED);
			temp = false;
		}
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(Assets.slime, (int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
	
			
			
	}
	//getters and setter below
	

}
