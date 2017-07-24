package main.entity.creature.npc;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import main.Handler;
import main.audio.SoundAssets;
import main.entity.Entity;
import main.entity.creature.Creature;
import main.entity.dynamic.push.Toast;
import main.entity.dynamic.slide.FrozenToast;
import main.entity.statics.Hatch;
import main.gfx.Animation;
import main.gfx.Assets;
import main.id.IdManager;
import main.tile.Tile;
import main.util.Timer;

public class ChefBot extends Npc {
	private float yShakeHat = 0;
	private float yShakeMouth = 0;
	private boolean firstInUpdate = true;
	private boolean CDSUpdateBlook = false;
	private Timer timer;
	
	//Logic
	private boolean firstInteraction =true;
	private boolean spreadToast = false;
	private boolean firstInSpreadToast = true;
	private boolean spreadToastFinished = true;
	
	//Toast
	private boolean firstInToastSpwan = true;
	private String toastName = "to";
	public static int toastCounter = 0;
	private int maxToastInGame = -1;
	
	//hatches
	private boolean firstTick = true;
	private List<Hatch> hatchList = new ArrayList<Hatch>();
	private int index = 0;
	private Hatch homeHatch;
	private boolean firstInClose = true;
	private boolean firstInOpen = true;
	
	//cutsean
	private long spitDurcration = 800;
	private Timer timer2;
	private Timer timer3;
	private boolean firstInSpitCutsean = true;
	private boolean spitCutsean = false;
	private float yOffsetToast = 0;
	
	private long HatchOpeningDurcration = 200;
	private boolean firstInHatchOpening = true;
	private boolean hatchOpening = false;
	private Timer HatchTimer;
	
	private long HatchMoveDurcration = 600;
	private Animation hatchMoveAnim;
	private boolean goinDownHatch = true;
	private int animSpeed = 35;
	private Timer timer4;
	private Timer timer5;
	private boolean firstInHatchMove = true;
	private boolean movingBetweenHatches = false;

	public ChefBot(Handler hand, float x, float y) {
		super(hand, IdManager.ChefBot, x*Tile.TILEBREITE, y*Tile.TILEHoeHE, Tile.TILEBREITE, Tile.TILEHoeHE*2);
		timer = new Timer(DEF_ANIM_SPEED*4);
		hatchMoveAnim = new Animation(animSpeed,Assets.chefbot_hatchswap);
		
		//Bounds
		bounds.y = hoehe*2/3-16;
		bounds.height = hoehe * 1/3;
		bounds.x = breite * 1/3;
		bounds.width = breite * 1/3;
		//Checkbox
		checkbox.y = hoehe*2/3-16;
		checkbox.height = hoehe * 1/3;
		checkbox.x = breite * 1/3;
		checkbox.width = breite * 1/3;
		

	}

	@Override
	public void tick() {
		tickDialoge();
		//prep
		if(firstTick){
			homeHatch = new Hatch(hand,x/Tile.TILEBREITE,(y/Tile.TILEHoeHE)+1);
			hand.getWorld().getEnti().addEnti(homeHatch, "homeHatch"+name);
			homeHatch.switchOpenClose();
			this.giveHatch(homeHatch);
			firstTick = false;
		}
		
		//Logic
		if(spreadToast){
			spreadingToast();
		}
		
		//Cutseans
		if(hatchOpening){
			tickHatchOpeningCutsean(HatchOpeningDurcration);
		}
		if(movingBetweenHatches){
			tickHatchMoveCutsen(HatchMoveDurcration);
		}
		if(spitCutsean){
			tickSpitCutsean(spitDurcration);
		}else{
			updateYOffset();
		}
	
	}
	//Rendering
	@Override
	public void render(Graphics g) {
		
		if(!movingBetweenHatches){
			renderBodyBase(g);
			
			if(spitCutsean){
				renderMouth(g);
				renderShadow(g);
				renderSpitCutsean(g);
			}
			
			renderBodyTop(g);
			
			if(!spitCutsean){
				renderMouth(g);
			}
			
			renderMustache(g);
			renderHat(g);
		}else{
			if(goinDownHatch){
				renderGoingDownHatch(g);
			}else{
				renderGoingUpHatch(g);
			}
		}
		if(!spitCutsean){
			renderShadow(g);
		}

	}
	private void renderShadow(Graphics g){
		g.drawImage(Assets.chef_bot_shadow,
				(int)(x-hand.getCam().getxOffset()), 
				(int) ((y-hand.getCam().getyOffset()-Tile.TILEHoeHE/16*4)+hoehe/2),
				breite,hoehe/2, null);
	}
	private void renderHat(Graphics g){
		g.drawImage(Assets.chef_bot_hat,
				(int)(x-hand.getCam().getxOffset()), 
				(int) ((y-hand.getCam().getyOffset()-Tile.TILEHoeHE/16*4)+yShakeHat),
				breite,hoehe/2, null);
	}
	private void renderMustache(Graphics g){
		g.drawImage(Assets.chef_bot_mustache,
				(int)(x-hand.getCam().getxOffset()), 
				(int) ((y-hand.getCam().getyOffset()-Tile.TILEHoeHE/16)),
				breite,hoehe/2, null);
	}
	private void renderBodyTop(Graphics g){
		g.drawImage(Assets.chef_bot_body_top,
				(int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()-Tile.TILEHoeHE/16),
				breite,hoehe/2 -Tile.TILEHoeHE/16*3, null);
	}
	private void renderBodyBase(Graphics g){
		g.drawImage(Assets.chef_bot_body_base,
				(int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset())+(hoehe/2-Tile.TILEHoeHE/16*4),
				breite,hoehe/2+Tile.TILEHoeHE/16*3, null);
	}
	private void renderMouth(Graphics g){
		g.drawImage(Assets.chef_bot_mouth,
				(int)(x-hand.getCam().getxOffset()), 
				(int) ((y-hand.getCam().getyOffset()-Tile.TILEHoeHE/16)+yShakeMouth),
				breite,hoehe/2, null);
	}
	private void updateYOffset(){
		if(firstInUpdate){
			timer.start();
			firstInUpdate=false;
		}
		if(timer.isFinished()){
			
			if(yShakeHat == 0){
				yShakeHat = 5;
				yShakeMouth = 0;
			}else{
				yShakeHat = 0;
				yShakeMouth = 5;
			}
			
			firstInUpdate=true;
		}
	}
	private void renderSpitCutsean(Graphics g){
		g.drawImage(Assets.toast,
				(int)(x-hand.getCam().getxOffset()) +Tile.TILEBREITE/16*2, 
				(int) ((y-hand.getCam().getyOffset()+10)+yOffsetToast),
				Tile.TILEBREITE/16*12,
				Tile.TILEHoeHE/16*12,
				null);
	}
	private void renderGoingDownHatch(Graphics g){
		g.drawImage(hatchMoveAnim.getCurrentFrame(),
				(int)(x-hand.getCam().getxOffset()), 
				(int) ((y-hand.getCam().getyOffset()-Tile.TILEHoeHE/16*4)),
				breite,hoehe, null);
	}
	private void renderGoingUpHatch(Graphics g){
		g.drawImage(hatchMoveAnim.getReverseCurrentFrame(),
				(int)(x-hand.getCam().getxOffset()), 
				(int) ((y-hand.getCam().getyOffset()-Tile.TILEHoeHE/16*4)),
				breite,hoehe, null);
	}
	//Dialoge
	@Override
	protected void tickDialoge() {
		checkInteraction();
		if(hand.getWorld().getEnti().getChat().getFinishedString() == dialoge.getDialoge(CDS) && this.CDSUpdateBlook == false){
			
			CDSUpdateBlook = true;
			
			if(CDS != 1){
				CDS++;
			}
		}
		if(hand.getWorld().getEnti().getChat().isDisplayFinished()){
			CDSUpdateBlook = false;
		}
		if(spreadToastFinished && interacted){
			
			hand.getWorld().getEnti().getChat().giveText(dialoge.getDialoge(CDS));
			hand.getWorld().getEnti().getChat().setShowText(true);
			
			
			if(CDS == 1 && this.CDSUpdateBlook == false){
				if(firstInteraction){
					firstInteraction = false;
					spreadToast = true;
				}
			}
		}else{
			firstInteraction = true;
		}
		
	}
	@Override
	protected void initDialoge(){
		dialoge.giveDialoge(0, "Hey there Monjoe Federfieh\n"
				+ "I'em Chefbot 201-b\n"
				+ "or \"Cehfbot\" for short.\n"
				+ "I'em equiped to forfill all of your Dieray needs.\n\n\n"
				+ "\n\nScan In Process\n\n\n\n\n"
				+ "the results of my Scan show that you are in need of \"Fish\".\n"
				+ "but I'em afraid that is not on todays menu.\n"
				+ "so I will serve you the next best Thing: \"Toast\"\n\n"
				+ "...don't aktually eat it thogh, my scans show it mide be poiseness for youre specias");
		dialoge.giveDialoge(1, "Monjoe Federfieh is Hungry,\n"
				+ "here we go!");
	}
	//Cutsean 
	private void tickHatchOpeningCutsean(long durc){
		if(firstInHatchOpening){
			HatchTimer = new Timer(durc);
			HatchTimer.start();
			
			firstInHatchOpening = false;
		}
		openNextHatch();
		if(HatchTimer.isFinished()){

			firstInOpen = true;
			firstInHatchOpening = true;
			hatchOpening = false;
			movingBetweenHatches = true;
		}
	
	}
	private void tickHatchMoveCutsen(long durc){
		if(this.firstInHatchMove){
			timer4 = new Timer(durc);
			timer5 = new Timer(durc/2);
			timer4.start();
			timer5.start();
			
			hatchMoveAnim.reset();
			firstInHatchMove = false;
			SoundAssets.chefbot_hatch_down.play();
		}
		hatchMoveAnim.tick();
		if(timer5.isFinished()){
			goinDownHatch = false;
			hatchMoveAnim.reset();
			goNextHatch();
			timer5.setSpeed(durc*2);
			timer5.start();
			SoundAssets.chefbot_hatch_up.play();
			
		}
		if(timer4.isFinished()){
			closeLastHatch();
			
			hatchMoveAnim.reset();
			firstInClose = true;
			goinDownHatch = true;
			firstInHatchMove = true;
			
			movingBetweenHatches = false;
			
	
			if(index != 0){
				spitCutsean =true;
				
			}else{
				spreadToastFinished = true;
				SoundAssets.chefbot_bell.play();
				yShakeHat = -17;
			}
		}
	}
	private void tickSpitCutsean(long durc){
		if(firstInSpitCutsean){
			timer2 =new Timer(durc);
			timer3 = new Timer(durc/50);
			timer2.start();
			timer3.start();
			yShakeMouth = 11;
			
			SoundAssets.tost_enter_slit.play();
			
			firstInSpitCutsean=false;
		}
		if(timer3.isFinished()){
			this.yOffsetToast+=4;
			timer3.start();
		}
		if(timer2.isFinished()){
			
			firstInSpitCutsean = true;
			SoundAssets.chefbot_toast_drop.play();
			yOffsetToast =0;
			
			spitCutsean = false;
			spawnToast();
			
			firstInSpreadToast = true;
		}
		
	}
	
	//Logic
	private void spreadingToast(){
		if(firstInSpreadToast){
			firstInSpreadToast = false;
			spreadToastFinished = false;
			
			if(index < hatchList.size()-1){
				index++;
				hatchOpening = true;
			}else{
				
				index = 0;
				hatchOpening = true;
				spreadToast = false;
				firstInSpreadToast = true;
				
			}
		}
	}
	
	
	//Hatches
	public void giveHatch(Hatch h){
		hatchList.add(h);
		maxToastInGame++;
		
	}
	private void openNextHatch(){
		if(firstInOpen){
			hatchList.get(index).switchOpenClose();
			firstInOpen = false;
		}
		
	}
	private void closeLastHatch(){
		if(firstInClose){
			if(index -1 < 0){
				hatchList.get(hatchList.size()-1).switchOpenClose();
			}else{
				hatchList.get(index-1).switchOpenClose();;
			}
			firstInClose = false;
		}
	}
	
	private void goNextHatch(){
		x = hatchList.get(index).getX();
		y = hatchList.get(index).getY() - hoehe/2;
		
	}
	
	//SpawnToast
	private void spawnToast(){
		if(firstInToastSpwan){
			this.toastCounter = 0;
			deleteToasts();
			firstInToastSpwan=false;
		}
		Toast ft = new Toast(hand,x/Tile.TILEBREITE, (y/Tile.TILEHoeHE)+2);
		hand.getWorld().getEnti().addEnti(ft,toastName + toastCounter);
		
		toastCounter++;
		if(toastCounter >= this.maxToastInGame){

			firstInToastSpwan=true;
		}
		
	}
	private void deleteToasts(){
		for(Entity e : hand.getWorld().getEnti().getEnti()){
			if((e instanceof Toast)||(e instanceof FrozenToast)){
				e.setActiv(false);
			}
		}
	}


	//Getters and setters
	public int getMaxToastInGame() {
		return maxToastInGame;
	}

	public void setMaxToastInGame(int maxToastInGame) {
		this.maxToastInGame = maxToastInGame;
	}
	
}
