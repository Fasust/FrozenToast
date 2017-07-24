package main.gfx;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Handler;
import main.audio.SoundAssets;
import main.entity.Entity;
import main.entity.creature.Creature;
import main.entity.creature.Player;
import main.entity.dynamic.push.DeadGreenFish;
import main.entity.dynamic.push.Toast;
import main.entity.dynamic.slide.FrozenDeadGreenFish;
import main.entity.dynamic.slide.FrozenToast;
import main.entity.statics.SpawnEgg;
import main.playerStates.NaturelState;
import main.tile.Tile;
import main.util.Timer;

public class Cutsean {
	private Handler hand;
	private Player pl;

	//Cutsen Starts
	private boolean playerFall = false;
	private boolean playerDropOnFloor = false;
	private boolean playerOptainObject = false;
	private boolean playerDrown = false;
	private boolean renderKeys = false;
	private boolean plHatch = false;
	private boolean entiDrown = false;

	//Timeing
	private int drowningTime = 400;
	private int fallingTime = 100;
	public static final int DEF_FALLINGSPEED = 4;
	public static final int DEF_RECIEVINGTIME = 3100;
	private Timer timer;
	private Timer scundearTimer;
	private Timer timer3;
	private Timer timer4;
	
	//Logic 
	private boolean firstInDrown = true;
	private boolean firstInEntiDrown = true;
	private boolean firstInFall = true;
	private boolean firstInDrop = true;
	private boolean firstInOptain = true;
	private boolean renderFall = false;

	//Items
	private BufferedImage item;
	private Animation animPlayOptain;
	private String itemText;
	private int soundIndex = 0;
	private boolean itemGlow = false;
	
	//Drown
	private int y_DrownImageCrop = 0;
	private int height_DrownImageCrop = 32;
	private float drownY = 0;
	private float drownEntiY = 0;
	private float drownEntiX = 0;
	private int height_EntiDrownImageCrop = 16;
	private int y_EntiDrownImageCrop = 0;
	
	//key
	private float keysX;
	private float keysY;
	
	//Hatch
	private SpawnEgg egg;
	private boolean firstInHatch = true;
	private boolean glowSoundPlayed = false;
	
	//Animations
	private Animation glow;
	private Animation land;
	private Animation keys;
	private Animation drowningPl;
	private Animation hatch;
	
	public Cutsean(Handler hand){
		this.hand = hand;
		
		animPlayOptain = new Animation(Creature.DEF_ANIM_SPEED*3,Assets.ping_optain_item);
		drowningPl = new Animation(Creature.DEF_ANIM_SPEED/2,Assets.ping_optain_item);
		keys = new Animation(500,Assets.key_prompts);
		glow = new Animation(Creature.DEF_ANIM_SPEED/2,Assets.glow_partiel);
		land = new Animation(Creature.DEF_ANIM_SPEED/2,Assets.ping_land);
		hatch = new Animation(270,Assets.egg_hatch);
	}
	
	public void tick(){
		if(playerFall){
			playerFall(fallingTime,DEF_FALLINGSPEED);
		}
		if(playerDropOnFloor){
			playerDropOnFloor(fallingTime,DEF_FALLINGSPEED);
		}
		if(playerOptainObject){
			playerOptainObject(item,DEF_RECIEVINGTIME,itemText);
		}
		if(playerDrown){
			playerDrown(drowningTime);
		}
		if(renderKeys){
			keys.tick();
		}
		if(plHatch){
			playerHatch(1800);
		}
		if(entiDrown){
			entiDrown(drowningTime);
		}
	}
	public void render(Graphics g){
		if(playerOptainObject){
			renderOptainObjekt(g);
		}
		if(renderFall){
			renderFall(g);
		}
		if(playerDrown){
			renderPlayerDrown(g);
		}
		if(entiDrown){
			renderEntiDrown(g);
		}
		if(renderKeys){
			renderKeys(g);
		}
		if(plHatch){
			renderHatch(g);
		}
	}
	
	//Render Funktios
	private void renderOptainObjekt( Graphics g){
		g.drawImage(animPlayOptain.getCurrentFrame(), 
				((int)hand.getWorld().getEnti().getPl().getX() -(int)hand.getCam().getxOffset()),
				(((int)hand.getWorld().getEnti().getPl().getY()-(int)hand.getCam().getyOffset())-Tile.TILEHoeHE), 
				Tile.TILEBREITE, 
				Tile.TILEHoeHE*2,
				null);
		
		if(itemGlow){
			g.drawImage(glow.getCurrentFrame(), 
					((int)hand.getWorld().getEnti().getPl().getX()-6 -(int)hand.getCam().getxOffset()),
					(((int)hand.getWorld().getEnti().getPl().getY()-6-(int)hand.getCam().getyOffset())-Tile.TILEHoeHE), 
					Tile.TILEBREITE+12, 
					Tile.TILEHoeHE+12,
					null);
			g.drawImage(Assets.gui_fade, 
					((int)hand.getWorld().getEnti().getPl().getX()-6 -(int)hand.getCam().getxOffset()),
					(((int)hand.getWorld().getEnti().getPl().getY()-6-(int)hand.getCam().getyOffset())-Tile.TILEHoeHE), 
					Tile.TILEBREITE+12, 
					Tile.TILEHoeHE+12,
					null);	
		}
		g.drawImage(item, 
				((int)hand.getWorld().getEnti().getPl().getX()+6 -(int)hand.getCam().getxOffset()),
				(((int)hand.getWorld().getEnti().getPl().getY()+6 -(int)hand.getCam().getyOffset())-Tile.TILEHoeHE), 
				Tile.TILEBREITE-12, 
				Tile.TILEHoeHE-12,
				null);	
		
	}
	private void renderFall (Graphics g){
		g.drawImage(land.getCurrentFrame(), 
				((int)hand.getWorld().getEnti().getPl().getX() -(int)hand.getCam().getxOffset()),
				(((int)hand.getWorld().getEnti().getPl().getY()-(int)hand.getCam().getyOffset())-Tile.TILEHoeHE), 
				Tile.TILEBREITE, 
				Tile.TILEHoeHE*2,
				null);
	}
	private void renderPlayerDrown (Graphics g){
		g.drawImage(ImageLoader.cropImage(drowningPl.getCurrentFrame(), 0, y_DrownImageCrop, 16, height_DrownImageCrop), 
				(int)hand.getWorld().getEnti().getPl().getX(),
				(int)drownY-(int)hand.getCam().getyOffset()-Tile.TILEHoeHE, 
				Tile.TILEBREITE, 
				height_DrownImageCrop * (Tile.TILEHoeHE/16),
				null);
	}
	private void renderEntiDrown(Graphics g){
		g.drawImage(ImageLoader.cropImage(item, 0, y_EntiDrownImageCrop, 16, height_EntiDrownImageCrop), 
				(int)drownEntiX-(int)hand.getCam().getxOffset(),
				(int)drownEntiY-(int)hand.getCam().getyOffset(), 
				Tile.TILEBREITE, 
				height_EntiDrownImageCrop * (Tile.TILEHoeHE/16),
				null);
	}
	private void renderKeys(Graphics g){
		g.drawImage(keys.getCurrentFrame(),
				(int)keysX*Tile.TILEBREITE,
				(int)keysY*Tile.TILEHoeHE,
				Tile.TILEBREITE*4,
				Tile.TILEHoeHE*3,
				null);
	}
	private void renderHatch(Graphics g){
		if(itemGlow){
			g.drawImage(glow.getCurrentFrame(), 
					((int)egg.getX()-6 -(int)hand.getCam().getxOffset()),
					(((int)egg.getY()-6-(int)hand.getCam().getyOffset())-Tile.TILEHoeHE), 
					Tile.TILEBREITE+12, 
					Tile.TILEHoeHE+12,
					null);
		}
		g.drawImage(hatch.getCurrentFrame(), 
				((int)egg.getX() -(int)hand.getCam().getxOffset()),
				(((int)egg.getY()-(int)hand.getCam().getyOffset())-Tile.TILEHoeHE), 
				Tile.TILEBREITE, 
				Tile.TILEHoeHE*2,
				null);
	}
	
	//Logic Funktions
	private void playerFall(int fallingTime,int fallinSpeed){
		if(firstInFall){
			timer = new Timer(fallingTime);
			timer.start();
			pl = hand.getWorld().getEnti().getPl();
			pl.setBlocked(true);
			
			firstInFall = false;
		}
		if(!timer.isFinished()){
			pl.setyMove(fallinSpeed);
			pl.moveY();
		}else{
			pl.setBlocked(false);
			firstInFall = true;
			playerFall = false;
		}
	}
	private void playerDropOnFloor(int fallingTime,int fallinSpeed){
		
		if(firstInDrop){
			timer = new Timer(fallingTime);
			timer.start();
			pl = hand.getWorld().getEnti().getPl();
			pl.setBlocked(true);
			
			if(pl.getCurrentState() instanceof NaturelState){
				pl.setInvisabel(true);
				renderFall = true;
			}
			
			
			firstInDrop = false;
		}
		land.tick();
		if(!timer.isFinished()){
			pl.setyMove(fallinSpeed);
			pl.moveY();
		}else{
			pl.setBlocked(true);
			if(!hand.getCam().getScreenShakeFinsihed()){
				hand.getWorld().getEnti().getPl().setFocusBlocked(true);
				hand.getCam().screenShake(200, 20, 0.7f, 0.7f);
			
			}else{
				land.reset();
				renderFall = false;
				firstInDrop = true;
				playerDropOnFloor = false;
				hand.getCam().setScreenShakeFinsihed(false);
				
				pl.setBlocked(false);
				pl.setInvisabel(false);
				hand.getWorld().getEnti().getPl().setFocusBlocked(false);
			}
		}
	}
	private void playerOptainObject(BufferedImage item, int time,String text){
		if(firstInOptain){
			timer = new Timer(time);
			scundearTimer = new Timer(time/3);
			timer.start();
			scundearTimer.start();
			
			hand.getWorld().getEnti().getPl().setBlocked(true);
			hand.getWorld().getEnti().getPl().setInvisabel(true);
			
			firstInOptain=false;
			
			SoundAssets.pickup_blip_low.play();
		}
		glow.tick();
		animPlayOptain.tick();
		
		if(scundearTimer.isFinished()){
			if(soundIndex == 0){
				SoundAssets.pickup_blip_mid.play();
			}else if(soundIndex == 1){
				SoundAssets.pickup_blip_high.play();
				itemGlow = true;
			}
			soundIndex++;
			scundearTimer.start();
		}
		
		if(timer.isFinished()){
			hand.getWorld().getEnti().getPl().setBlocked(false);
			hand.getWorld().getEnti().getPl().setInvisabel(false);
			
			hand.getWorld().getEnti().getChat().giveText(text);
			hand.getWorld().getEnti().getChat().setShowText(true);
			
			firstInOptain=true;
			playerOptainObject = false;
			animPlayOptain.reset();
			
			soundIndex = 0;
			itemGlow = false;
		}

	}
	private void playerDrown(int time){
		if(firstInDrown){
			timer = new Timer(time);
			scundearTimer = new Timer(time/5);
			timer.start();
			scundearTimer.start();
			
			hand.getWorld().getEnti().getPl().setBlocked(true);
			hand.getWorld().getEnti().getPl().setInvisabel(true);
			drownY = hand.getWorld().getEnti().getPl().getY();
			
			firstInDrown=false;
			SoundAssets.fall_in_water.play();
		}
		drowningPl.tick();
		if(scundearTimer.isFinished()){
			drownY += 20;

			if(height_DrownImageCrop > 1 ){
				height_DrownImageCrop -=4;
			}
		
			scundearTimer.start();
		}
		
		if(timer.isFinished()){

			firstInDrown=true;
			playerDrown= false;
			height_DrownImageCrop = 32;
			drowningPl.reset();
			
			hand.getWorld().getEnti().getPl().setSpawning(true);
		}

	}
	private void entiDrown(int time){
		if(firstInEntiDrown){
			timer3 = new Timer(time);
			timer4 = new Timer(time/5);
			timer3.start();
			timer4.start();
	
			firstInEntiDrown=false;
			SoundAssets.fall_in_water.play();
		}
		
		if(timer4.isFinished()){
			drownEntiY += 10;
			if(height_EntiDrownImageCrop > 1 ){
				height_EntiDrownImageCrop -=2;
			}
			timer4.start();
		}
		if(timer3.isFinished()){
			firstInEntiDrown=true;
			height_EntiDrownImageCrop = 16;
			entiDrown = false;
		}

	}
	private void playerHatch(int time){
		if(firstInHatch){
			timer = new Timer(time);
			scundearTimer = new Timer(time-700);
			timer.start();
			scundearTimer.start();
			
			egg.setInvisabel(true);
			hand.getWorld().getEnti().getPl().setBlocked(true);
			hand.getWorld().getEnti().getPl().setInvisabel(true);
			
			firstInHatch=false;
			
			SoundAssets.antisipation.play();
		}
		glow.tick();
		
		
		if(scundearTimer.isFinished()){
		
			if(!itemGlow){
				scundearTimer.setSpeed(110);
				scundearTimer.start();
				
				itemGlow=true;
			}else{
				if(!glowSoundPlayed){
					SoundAssets.dunce_high.play();
					glowSoundPlayed = true;
				}
			}
			
		}else if(!itemGlow){
			hatch.tick();
		}
		
		if(timer.isFinished()){
			egg.setInvisabel(false);
			hand.getWorld().getEnti().getPl().setBlocked(false);
			hand.getWorld().getEnti().getPl().setInvisabel(false);
			
			firstInHatch=true;
			plHatch = false;
			hatch.reset();
			
			itemGlow = false;
			glowSoundPlayed = false;

		}
		

	}
	
	
	//CutseanStarts
	public void activatePlayerFall(int fallingTime) {
		this.fallingTime = fallingTime;
		playerFall = true;
	}
	public void activatePlayerDropOnFloor(int fallingTime) {
		this.fallingTime = fallingTime;
		playerDropOnFloor = true;
	}
	public void activatePlayerOptainObject(BufferedImage item,String text) {
		this.item = item;
		this.itemText = text;
		playerOptainObject = true;
	}
	public void activatePlayerDrown() {
		playerDrown = true;
	}
	public void activateEntiDrown(Entity e) {
		entiDrown = true;
		this.drownEntiY = e.getY();
		this.drownEntiX = e.getX();
		
		if(e instanceof Toast){
			item = Assets.toast;
		}else if(e instanceof FrozenToast){
			item = Assets.toast_frozen;
		}else if(e instanceof DeadGreenFish){
			item = Assets.fish_green_dead;
		}else if(e instanceof FrozenDeadGreenFish){
			item = Assets.fish_green_dead_frozen;
		}
	}
	public void showKeyPrompts(float x,float y,boolean show) {
		this.renderKeys = show;
		this.keysX = x;
		this.keysY = y;
	}
	public void showKeyPrompts(boolean show) {
		this.renderKeys = show;
	}
	public void startPlayerHatch(SpawnEgg egg){
		plHatch = true;
		this.egg = egg;		
	}
}

