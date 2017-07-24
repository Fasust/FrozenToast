package main;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;


import main.audio.SoundAssets;
import main.display.Display;
import main.gfx.Assets;
import main.gfx.Camera;
import main.input.KeyManager;
import main.state.GameState;
import main.state.MenuState;
import main.state.State;

public class Game implements Runnable {
	
	//Window Propeties
	private Display display;
	private int hoehe,breite;
	private String title;
	
	//states
	private GameState gameState;
	private MenuState menuState;
	
	private Thread thread;
	private BufferStrategy bs;
	private Graphics g;
	private boolean run = false;
	private KeyManager keyManager;
	private Camera cam;
	private Handler hand;
	
	//Debug
	public static boolean DEBUG = false;
	
	public Game(String title, int breite, int hoehe){ //Game Constructor(takes in Window Properties and Initioalieses Key Maneger)
		this.hoehe = hoehe;
		this.breite = breite;
		this.title = title;
		keyManager = new KeyManager();
	}
	private void preboot(){
		run = true;
		display = new Display(title,breite,hoehe); 		//Initaliese new Display
		display.getFrame().addKeyListener(keyManager); //assienges Key manaager to display
		Assets.preboot();							 //load all assstes on Sprite sheet into storage (once(!))
		SoundAssets.preBoot();
		hand = new Handler(this);  					// Initaliese Handler and give it the Game object
		cam = new Camera(hand,0,0);					//new Camera Initaliesed with Handler
		gameState = new GameState(hand);			//new Game state (player wird inizialiersiert)
		menuState = new MenuState(hand);
		State.setState(gameState);                //static Curent state = gameState
	}
	private void tick(){
		keyManager.tick();
		if(State.getState() != null){
			State.getState().tick();
		}
	}
	
	private void render(){
		bs = display.getCanvas().getBufferStrategy();   //buffer Bla bla
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, breite, hoehe);
		if(State.getState() != null){
			State.getState().render(g);  //render current state
		}
		bs.show();
		g.dispose();
	}
	public void run(){
		preboot();
		
		int fps = 60;
		double timePerTick = 1000000000/fps; //one Billion nanosekond in one sekond
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		
		while(run){
			now = System.nanoTime();
			delta = delta + (now - lastTime)/ timePerTick;
			lastTime = now;
			
			if(delta >= 1){
				tick();
				render();
				delta--;
			}
		}
		stop();
	}
	public synchronized void start(){
		thread = new Thread(this); //Run this Class on new Thread
		thread.start(); 		//executes run methode
	}
	public synchronized void stop(){
		run = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
	
	//getters and setters below
	
	
	public Graphics getG() {
		return g;
	}
	public KeyManager getKeyManager(){
		return keyManager;
	}
	public Camera getCam(){
		return cam;
	}
	
	public int getHoehe() {
		return hoehe;
	}
	public int getBreite() {
		return breite;
	}
	public GameState getGameState() {
		return gameState;
	}
	public MenuState getMenuState() {
		return menuState;
	}
	
}
