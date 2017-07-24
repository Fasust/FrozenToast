package main.entity.statics.multistate;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Handler;
import main.audio.SoundAssets;
import main.entity.statics.StaticEntity;
import main.gfx.Assets;
import main.id.IdManager;
import main.tile.Tile;


public class Button_red extends StaticEntity {
	private boolean buttonPressed = false;
	private BufferedImage buttonState = Assets.button_red;

	public Button_red(Handler hand, float x, float y) {
		super(hand,IdManager.Button_red, x*Tile.TILEBREITE, y*Tile.TILEHoeHE, Tile.TILEBREITE, Tile.TILEHoeHE);
	
		hight = 2;
		
		//hitbox
		bounds.x = 0;
		bounds.y = 0;
		bounds.width=0;
		bounds.height = 0;
		//Checkbox
		checkbox.x = 13;
		checkbox.y = 13;
		checkbox.width= 39;
		checkbox.height = 39;
		
	}

	@Override
	public void tick() {
		if(isThisCheckboxCollidingWithEntityBounds() && ! buttonPressed){
			SoundAssets.button_press.play();
			buttonPressed = true;
			buttonState = Assets.button_pressed_red;
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
