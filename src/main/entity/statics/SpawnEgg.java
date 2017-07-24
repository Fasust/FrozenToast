package main.entity.statics;

import java.awt.Color;
import java.awt.Graphics;

import main.Handler;
import main.audio.SoundAssets;
import main.entity.creature.Creature;
import main.entity.creature.Player;
import main.gfx.Animation;
import main.gfx.Assets;
import main.id.IdManager;
import main.tile.Tile;

public class SpawnEgg extends StaticEntity {
	private Animation anim;
	
	private String popUpText = null;
	public static SpawnEgg currentPlayerSpawn;
	private boolean fristActive = true;
	
	private boolean Invisabel = false;

	public SpawnEgg(Handler hand, float x, float y) {
		super(hand,IdManager.SpawnEgg, x*Tile.TILEBREITE, y*Tile.TILEHoeHE, Tile.TILEBREITE, Tile.TILEHoeHE);
		anim = new Animation(Creature.DEF_ANIM_SPEED,Assets.spawn_egg);
		
		//Bounds
		bounds.x = 12;
		bounds.y = 25;
		bounds.width = 40;
		bounds.height = 35;
		//checkbox
		checkbox.x = 12;
		checkbox.y = 25;
		checkbox.width = 40;
		checkbox.height = 35;
	}

	@Override
	public void tick() {
		anim.tick();
		if(this.getCollisionBounds(0, 0).intersects(hand.getWorld().getEnti().getPl().getCheckbox(0, 0))){
			if(fristActive){
				SoundAssets.set_spawn.play();
				fristActive = false;
			}
			if(!(popUpText == null)){
				hand.getWorld().getEnti().getChat().giveText(popUpText);
				hand.getWorld().getEnti().getChat().setShowText(true);
			}
			hand.getWorld().getEnti().getPl().setSpawn();
			currentPlayerSpawn = this;
			
		}else{
			fristActive = true;
		}
	}

	@Override
	public void render(Graphics g) {
		if(!Invisabel){
			
			if(currentPlayerSpawn == this){
				g.drawImage(anim.getCurrentFrame(), (int)(x-hand.getCam().getxOffset()), 
						(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
			}else{
				g.drawImage(anim.getStillFrame(), (int)(x-hand.getCam().getxOffset()), 
						(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
			}
		}	
	}
//Getters Setters

	public String getPopUpText() {
		return popUpText;
	}

	public void setPopUpText(String popUpText) {
		this.popUpText = popUpText;
	}

	public boolean isInvisabel() {
		return Invisabel;
	}

	public void setInvisabel(boolean invisabel) {
		Invisabel = invisabel;
	}
	
}
