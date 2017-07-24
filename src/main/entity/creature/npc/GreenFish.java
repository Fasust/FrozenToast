package main.entity.creature.npc;

import java.awt.Graphics;
import main.Handler;
import main.entity.creature.Creature;
import main.gfx.Animation;
import main.gfx.Assets;
import main.id.IdManager;
import main.tile.Tile;

public class GreenFish extends Npc {
	private Animation anim;

	public GreenFish(Handler hand, float x, float y) {
		super(hand,IdManager.GreenFish, x*Tile.TILEBREITE, y*Tile.TILEHoeHE, Tile.TILEBREITE, Tile.TILEHoeHE);
		
		//Animation
		anim = new Animation(DEF_ANIM_SPEED-50, Assets.fish_green);
	}

	@Override
	public void tick() {
		tickDialoge();
		//Animation
		anim.tick();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(anim.getCurrentFrame(),(int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
		
	}
	//Dialoge
	@Override
	protected void tickDialoge() {
		checkInteraction();
		if(interacted){

			hand.getWorld().getEnti().getChat().giveText(dialoge.getDialoge(CDS));
			hand.getWorld().getEnti().getChat().setShowText(true);
			
		}
		
	}
	@Override
	protected void initDialoge(){
		dialoge.giveDialoge(0, "Monjoe");
	}
	//getters and setter below

}
