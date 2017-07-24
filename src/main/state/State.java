package main.state;
import java.awt.Graphics;
import main.Handler;

public abstract class State {

	protected Handler hand;
	private static State curentState = null;
	
	public static final long DEF_TRANSITION_SPEED = 200;
	
	public State(Handler hand){
		this.hand = hand;
	}
	
	public static void setState(State state){
		curentState = state;
	}
	public static State getState(){
		return curentState;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
}
