package main.entity.statics.multistate;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Handler;
import main.audio.SoundAssets;
import main.entity.statics.StaticEntity;
import main.gfx.Assets;
import main.id.IdManager;
import main.tile.Tile;


public class WallButton extends StaticEntity {
	private boolean buttonPressed = false;
	private BufferedImage buttonState = Assets.wall_button;

	public WallButton(Handler hand, float x, float y) {
		super(hand,IdManager.WallButton, x*Tile.TILEBREITE, y*Tile.TILEHoeHE, Tile.TILEBREITE, Tile.TILEHoeHE);
		//hitbox
		bounds.x = 0;
		bounds.y = 0;
		bounds.width=0;
		bounds.height = 0;
		
	}

	@Override
	public void tick() {
		if((hand.getWorld().getEnti().getPl().getCheckbox(0, 0).intersects(this.getCheckbox(0, 0)))&& !buttonPressed){
			buttonPressed = true;
			buttonState = Assets.wall_button_pressed;
			SoundAssets.button_press.play();	
		}else if((hand.getWorld().getEnti().getPl().getCheckbox(0, 0).intersects(this.getCheckbox(0, 0)))){
			buttonState = Assets.wall_button_pressed;
		}else{
			buttonPressed = false;
			buttonState = Assets.wall_button;;
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(buttonState, (int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()),breite,hoehe, null);
			
	}
	//getters and setter below

	public boolean isButtonPressed() {
		return buttonPressed;
	}	

}
