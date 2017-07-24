package main.state;
import java.awt.Color;
import java.awt.Graphics;

import main.Game;
import main.Handler;
import main.Level.Level;
import main.Level.Level1;
import main.Level.LevelTest;
import main.audio.AudioPlayer;
import main.audio.SoundAssets;
import main.gfx.Cutsean;
import main.util.Timer;

public class GameState extends State {
	
	//Level
	private LevelTest levelTest;
	private Level1 level1;
	
	//Transition
	private boolean transition = false ;
	private boolean firstInTrans = true;
	private float alpha = 0;
	private boolean toBlack = true;
	private long transSpeed;
	
	//Timer
	private Timer timer;
	
	//Musik
	private AudioPlayer currentMusik;
	
	//Cutsean
	public static Cutsean cutsean;
		
	public GameState(Handler hand){
		super(hand);
		
		levelTest = new LevelTest(hand);
		level1 = new Level1(hand);
		Level.setLevel(levelTest);
		hand.setWorld(levelTest.getWorld());
		
		transSpeed = State.DEF_TRANSITION_SPEED;
		
		
		//Musik
		if(!Game.DEBUG){
			currentMusik = SoundAssets.background_musik_level1;
			currentMusik.loop();
		}
		
		cutsean = new Cutsean(hand);
		
	}
	
	@Override
	public void tick() {
		Level.curentLevel.tick();
		cutsean.tick();

		
	}

	@Override
	public void render(Graphics g) {
	
		Level.curentLevel.render(g);
		
		cutsean.render(g);
		
		if(transition){
			transition(g);
		}
	}
	
	
	//Transition
	private void transition(Graphics g){
		if(firstInTrans){
			timer = new Timer(transSpeed);
			timer.start();
			firstInTrans = false;
		}
		
		Color color = new Color(0, 0, 0, alpha); //Black 
		g.setColor(color);
		g.fillRect(0, 0, hand.getBreite(), hand.getHoehe());
		
		if(toBlack){
			if(!timer.isFinished()){
				alpha = (float)timer.getCurrentDuration()/transSpeed;
				
			}else{
				toBlack = false;
				alpha = 1;
				timer.start();
			}
		}else{
			if(!timer.isFinished()){
				alpha = 1- ((float)timer.getCurrentDuration()/transSpeed);
			}else{
				toBlack = true;
				transition = false;
				alpha = 0;
				firstInTrans = true;
			}
		}
	}
	public void startTransition(long transSpeed){
		transition = true;
		this.transSpeed = transSpeed;
	}
	//getters and setters below
	public LevelTest getLevelTest() {
		return levelTest;
	}
		
}
