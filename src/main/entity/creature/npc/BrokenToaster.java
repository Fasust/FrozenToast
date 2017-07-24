package main.entity.creature.npc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Handler;
import main.audio.SoundAssets;
import main.entity.Entity;
import main.entity.creature.Creature;
import main.entity.dynamic.push.Toast;
import main.entity.dynamic.slide.FrozenToast;
import main.entity.statics.multistate.piston.Piston;
import main.gfx.Animation;
import main.gfx.Assets;
import main.id.IdManager;
import main.tile.Tile;
import main.util.Timer;

public class BrokenToaster extends Npc{
	private Animation anim;
	private Timer timer;
	
	private boolean CDSUpdateBlook = false;
	private String emot;
	private float yOffset = 1;
	private boolean firstInUpdate = true;
	
	//Cutsean
	private boolean cutsean = false;
	private float yOffsetToast = 0;
	private Timer timer2;
	private Timer timer3;
	private boolean firstInCutsean = true;
	
	//Cutsean2
	private Animation glow;
	private int glowSize = -15;
	private boolean cutsean2 = false;
	private Timer timer4;
	private Timer timer5;
	private boolean firstInCutsean2 = true;
	private float yShake = 0;
	private float xShake = 0;
	
	//Toast
	private String toastName = "tmp";
	private boolean firstInToastSpwan = true;
	
	public BrokenToaster(Handler hand, float x, float y) {
		super(hand, IdManager.BrokenToaster, x*Tile.TILEBREITE, y*Tile.TILEHoeHE, Tile.TILEBREITE, Tile.TILEHoeHE);
		anim = new Animation(DEF_ANIM_SPEED*3,Assets.broken_toster);
		glow = new Animation(DEF_ANIM_SPEED,Assets.frezze_partiel);
		
		timer = new Timer(DEF_ANIM_SPEED*6);
		emot = "def";
		
		//Bounds
		bounds.y = 16;
		bounds.height = hoehe*2/3;
		//Checkbox
		checkbox.y = hoehe;
	}

	@Override
	public void tick() {
		anim.tick();
		updateYOffset();
		toastFreeze();
		
		if(!(cutsean||cutsean2))
			tickDialoge();
		
		
		//Cutsean
		if(cutsean){
			tickCutsean();
		}
		if(cutsean2){
			tickCutsean2();
		}
		
	}

	@Override
	public void render(Graphics g) {
		if(cutsean2){
			renderCutsean2(g);
		}
		renderTop(g);
		if(cutsean){
			renderCutsean(g);
		} 
		renderBase(g);
		renderEmote(g);
		
		
	}
	private void renderBase(Graphics g){
		g.drawImage(anim.getCurrentFrame(),
				(int)(x-hand.getCam().getxOffset()+xShake), 
				(int) (y-hand.getCam().getyOffset()+yShake),
				breite*2,hoehe, null);
	}
	private void renderTop(Graphics g){
		g.drawImage(Assets.broken_toster_top,
				(int)(x-hand.getCam().getxOffset()+xShake), 
				(int) ((y-hand.getCam().getyOffset())+yShake),
				breite,hoehe, null);
	}
	private void renderEmote(Graphics g){
		g.drawImage(getEmot(emot),
				(int)(x-hand.getCam().getxOffset()), 
				(int) (y-hand.getCam().getyOffset()-(yOffset*Tile.TILEHoeHE/16)/2),
				breite,hoehe, null);
	}
	private BufferedImage getEmot(String id){
		if(id == "def"){
			return Assets.broken_toster_def;
		}else if(id == "sad"){
			return Assets.broken_toster_sad;
		}else if(id == "pow"){
			return Assets.broken_toster_pow;
		}else{
			return Assets.broken_toster_def;
		}
	}	
	private void renderCutsean(Graphics g){
		g.drawImage(Assets.toast,
				(int)(x-hand.getCam().getxOffset()) +Tile.TILEBREITE/16*2, 
				(int) (y-hand.getCam().getyOffset()-Tile.TILEHoeHE+yOffsetToast),
				Tile.TILEBREITE/16*12,
				Tile.TILEHoeHE/16*12,
				null);
	}	
	private void renderCutsean2(Graphics g){
		g.drawImage(glow.getCurrentFrame(),
				(int)(x-hand.getCam().getxOffset()-glowSize/2), 
				(int) (y-hand.getCam().getyOffset()-glowSize/2),
				Tile.TILEBREITE+glowSize,
				Tile.TILEHoeHE+glowSize,
				null);
	}
	private void updateYOffset(){
		if(firstInUpdate){
			timer.start();
			firstInUpdate=false;
		}
		if(timer.isFinished()){
			
			if(yOffset == 1){
				yOffset = 0;
			}else{
				yOffset = 1;
			}
			
			firstInUpdate=true;
		}
	}
	//Dialoge
	@Override
	protected void tickDialoge() {
		checkInteraction();
		if(hand.getWorld().getEnti().getChat().getFinishedString() == dialoge.getDialoge(CDS)&& this.CDSUpdateBlook == false){
			
			CDSUpdateBlook = true;
			
			if(CDS < 3){
				CDS++;
				emot = "def";
			}else{
				emot = "sad";
			}
			
		}
		if(hand.getWorld().getEnti().getChat().isDisplayFinished()){
			CDSUpdateBlook = false;
		}
		if(interacted){

			hand.getWorld().getEnti().getChat().giveText(dialoge.getDialoge(CDS));
			hand.getWorld().getEnti().getChat().setShowText(true);
			CDSUpdateBlook = false;
		}
		
		
	}
	@Override
	protected void initDialoge(){
		dialoge.giveDialoge(0, "Oh!\n"
				+ "Hello my dear Friend.\n"
				+ "I did not expect to see another soule Around here."
				+ "Would you be so kind and carry me over to that Bathtub over there?\n"
				+ "Ever since I've been cursed I lost the ability to move on my own.\n"
				+ "The last chap I asked brought me up to here but he did not go throug with his Promise.\n\n\n"
				+ "would you be so kinde and Finish This?");
		dialoge.giveDialoge(1, "Common boy, please help me out.\n"
				+ "Lets not drag this out any longer."
				+ "I can not wait any more."
				+ "I've waited too long already!");
		dialoge.giveDialoge(2, "I have no Use !");
		dialoge.giveDialoge(3, "Kill me, please");
	}

	//Cutsean
	private void tickCutsean(){
		if(firstInCutsean){
			firstInToastSpwan=true;
			
			timer2 =new Timer(1000);
			timer3 = new Timer(1000/50);
			timer2.start();
			timer3.start();
			
			emot="pow";
			
			firstInCutsean=false;
		}
		if(timer3.isFinished()){
			this.yOffsetToast+=2;
			timer3.start();
		}
		if(timer2.isFinished()){
			
			firstInCutsean = true;
			cutsean = false;
			cutsean2 = true;
			
			yOffsetToast =0;
			
			SoundAssets.tost_enter_slit.play();
		}
		
	}
	private void tickCutsean2(){
		if(firstInCutsean2){
			timer4 =new Timer(2100);
			timer5 = new Timer(2100/40);
			timer4.start();
			timer5.start();
			
			SoundAssets.freeze_ray.play();
			
			firstInCutsean2=false;
		}
		glow.tick();
		if(timer5.isFinished() && !timer4.isFinished()){
			if(yShake<=0){
				yShake+=1;
			}else{
				yShake-=2;
			}
			if(xShake <=0){
				xShake+=1;
			}else{
				xShake-=2;
			}
			timer5.start();
			
			glowSize += 1;
		}
		if(timer4.isFinished()){
			xShake = 0;
			yShake =0;
			glowSize = -Tile.TILEBREITE;
			
			timer5.setSpeed(1600);
			
			spawnFrozenToast();
			
			
			if(timer5.isFinished()){
				firstInCutsean2 = true;
				cutsean2 = false;
				glowSize = -15;
				
				SoundAssets.mishab.play();
				emot="sad";
			}
			
		}
		
	}

	//Spawn Toast
	private void toastFreeze(){
		Entity tmp = getEntityCollidingWithCheckbox();
		if(tmp != null && tmp instanceof Toast){
		
			 toastName = tmp.getName();
			 tmp.setActiv(false);
			 cutsean = true;
		}
	}
	private void spawnFrozenToast(){
		if(firstInToastSpwan){
			SoundAssets.freeze.play();
			
			FrozenToast ft = new FrozenToast(hand,x/Tile.TILEBREITE, (y/Tile.TILEHoeHE)-1);
			ft.setyMove(-4);
			hand.getWorld().getEnti().addEnti(ft,toastName);
			
			firstInToastSpwan=false;
		}
	}

}

