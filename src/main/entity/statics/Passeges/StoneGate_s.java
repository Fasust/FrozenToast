package main.entity.statics.Passeges;

import java.awt.Graphics;

import main.Handler;
import main.audio.SoundAssets;
import main.entity.creature.Creature;
import main.entity.statics.StaticEntity;
import main.gfx.Animation;
import main.gfx.Assets;
import main.id.IdManager;
import main.tile.Tile;

public class StoneGate_s extends StaticEntity{
	private Animation anim;
	private boolean firstTouche = true;
	
	public StoneGate_s(Handler hand, float x, float y) {
		super(hand,IdManager.StoneGate_s, x*Tile.TILEBREITE, y*Tile.TILEHoeHE,Tile.TILEBREITE,Tile.TILEHoeHE);
		this.CreaturePass = true;
		anim = new Animation(Creature.DEF_ANIM_SPEED, Assets.stone_gate);
	}

	@Override
	public void tick() {
		anim.tick();
		if(isThisCheckboxCollidingWithEntityBounds()&&firstTouche){
			firstTouche = false;
			SoundAssets.stoneGate_traferse.play();
		}else if(isThisCheckboxCollidingWithEntityBounds()){
			firstTouche = false;
		}else{
			firstTouche = true;
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(anim.getCurrentFrame(), (int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
		
	}
}
