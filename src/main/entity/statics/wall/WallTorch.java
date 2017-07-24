package main.entity.statics.wall;

import java.awt.Graphics;
import main.Handler;
import main.audio.SoundAssets;
import main.entity.statics.StaticEntity;
import main.gfx.Animation;
import main.gfx.Assets;
import main.id.IdManager;
import main.tile.Tile;

public class WallTorch extends StaticEntity {
	private Animation anim;
	private boolean lit = true;

	public WallTorch(Handler hand, float x, float y) {
		super(hand,IdManager.WallTorch, x*Tile.TILEBREITE, y*Tile.TILEHoeHE, Tile.TILEBREITE, Tile.TILEHoeHE);
		anim = new Animation(140,Assets.wall_torch);
		//hitbox
		bounds.width = 0;
		bounds.height = 0;
	}

	@Override
	public void tick() {
		if(lit)
			anim.tick();
		if(hand.getWorld().getEnti().getPl().getCheckbox(0, 0).intersects(this.getCheckbox(0, 0)) 
				&& hand.getWorld().getEnti().getPl().getIceState().isFreezing()
				&&lit){
			lit = false;
			SoundAssets.freeze.play();
		}
	}

	@Override
	public void render(Graphics g) {
		if(lit){
			g.drawImage(anim.getCurrentFrame(), (int)(x-hand.getCam().getxOffset()), 
					(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
		}else{
			g.drawImage(Assets.torch_dim, (int)(x-hand.getCam().getxOffset()), 
					(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
		}

	}
//getters and setters

}
