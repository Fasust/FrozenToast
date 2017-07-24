package main.entity.statics.multistate.piston;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import main.Handler;
import main.audio.SoundAssets;
import main.entity.creature.Player;
import main.entity.statics.StaticEntity;
import main.tile.Tile;
import main.util.Timer;

public abstract class Piston extends StaticEntity {
	protected boolean pushing = false;
	private float pushSpeed = 5.1f;
	
	
	private char pushDirektion;
	
	//animation
	private boolean firstInAnimation = true;
	private Timer timer;
	private Timer scundarTimer;
	private Timer cooldown;
	protected float headXOffset = 20;
	protected float headYOffset = 20;
	private long animSpeed = 500;
	float offsetSpeed = 3;

	public Piston(Handler hand, int id, float x, float y,char direk) {
		super(hand, id, x, y, Tile.TILEBREITE, Tile.TILEHoeHE);
		this.pushDirektion = direk;
		this.hight = 2;
		
		cooldown = new Timer(0);
		
		setCheckboxX(-10000);
		setCheckboxY(-10000);
	}

	@Override
	public void tick() {
		
		if(pushing && cooldown.isFinished()){
			tickPistonAnimation();
			push(pushDirektion);
		}else{
			setCheckboxX(-10000);
			setCheckboxY(-10000);
		}
		
	}

	@Override
	public void render(Graphics g){
		renderPistonFood(g);
		renderPistonStock(g);
		renderPistonTop(g);
		renderPistonHead(g);
		
		/*
		g.setColor(Color.cyan);
		g.fillRect((int)(getCheckbox(0, 0).x-hand.getCam().getxOffset()),
				(int) (getCheckbox(0, 0).y-hand.getCam().getyOffset()),
				getCheckbox(0, 0).width, 
				getCheckbox(0, 0).height);
		*/		
	}
	
	protected abstract void renderPistonFood(Graphics g);
	protected abstract void renderPistonStock(Graphics g);
	protected abstract void renderPistonTop(Graphics g);
	protected abstract void renderPistonHead(Graphics g);
	
	private void push(char direk){
		Rectangle tempColis = getCollisionBounds(0,0);
		
		switch(direk){
		case 'n':
			//Non existent
			setCheckboxX(tempColis.x/Tile.TILEBREITE + tempColis.width /2 -getCheckboxSize()/2);
			setCheckboxY(tempColis.y/Tile.TILEHoeHE - getCheckboxSize());
			
			break;
		case 'e':
			setCheckboxX(tempColis.x /Tile.TILEBREITE+ tempColis.width);
			setCheckboxY(tempColis.y /Tile.TILEHoeHE-Tile.TILEHoeHE/16*2);
			break;
		case's':
			setCheckboxX(tempColis.x /Tile.TILEBREITE-Tile.TILEHoeHE/16*2);
			setCheckboxY(tempColis.y/Tile.TILEHoeHE + tempColis.height);
			break;
		case'w':
			setCheckboxX(tempColis.x /Tile.TILEBREITE- tempColis.width);
			setCheckboxY(tempColis.y /Tile.TILEHoeHE-Tile.TILEHoeHE/16*2);
			break;
		default:
			break;
		}
		
	}
	
	private void tickPistonAnimation(){
		
		if(firstInAnimation){
			animSpeed = (long) ((1/pushSpeed)*1500);
			timer = new Timer(animSpeed);
			scundarTimer = new Timer(animSpeed/12);
			timer.start();
			scundarTimer.start();
			
			
			firstInAnimation = false;
			SoundAssets.piston_push.play();
		}
		if(scundarTimer.isFinished()){
			switch(pushDirektion){
			case 'n':
			
				break;
			case 'e':
				headXOffset+=offsetSpeed;
				break;
			case 's':
				headYOffset +=offsetSpeed;
				break;
			case 'w':
				headXOffset+=offsetSpeed;
				break;
			default:
				break;
			}
			scundarTimer.start();
			
		}
		if(timer.isFinished()){
			headYOffset = 20;
			headXOffset = 20;
			
			cooldown= new Timer(animSpeed);
			cooldown.start();
			
			firstInAnimation = true;
			pushing = false;
		}
	}
	
	
	//Getters and setters

	public boolean isPushing() {
		return pushing;
	}

	public void setPushing(boolean pushing) {
		this.pushing = pushing;
	}

	public float getPushSpeed() {
		return pushSpeed;
	}

	public void setPushSpeed(float pushSpeed) {
		this.pushSpeed = pushSpeed;
	}

	public char getPushDirektion() {
		return pushDirektion;
	}

	
	
}
