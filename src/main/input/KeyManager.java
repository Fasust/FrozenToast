package main.input;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

	private boolean[] keysPressed;
	public boolean up, down, left, right,shift,space,e,strg;
	

	public KeyManager(){
		keysPressed = new boolean[256];
	}
	public void tick(){        //uses KeyyPressen and Key Released to update booles of key variebels
		up = keysPressed[KeyEvent.VK_W];
		down = keysPressed[KeyEvent.VK_S];
		left = keysPressed[KeyEvent.VK_A];
		right = keysPressed[KeyEvent.VK_D];
		shift = keysPressed[KeyEvent.VK_SHIFT];
		space = keysPressed[KeyEvent.VK_SPACE];
		strg = keysPressed[KeyEvent.VK_CONTROL];
		
		e = keysPressed[KeyEvent.VK_E];
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_E){
			if (!keysPressed[KeyEvent.VK_E]){
				keysPressed[KeyEvent.VK_E] = true;
			}
		}else if(e.getKeyCode()==KeyEvent.VK_SPACE){
			if (!keysPressed[KeyEvent.VK_SPACE]){
				keysPressed[KeyEvent.VK_SPACE] = true;
			}
		}else{
			keysPressed[e.getKeyCode()] = true;
			keysPressed[KeyEvent.VK_E] = false;
			keysPressed[KeyEvent.VK_SPACE] = false;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		keysPressed[e.getKeyCode()] = false;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	//was Key Pressed
	
	public boolean wasEPressed(){
		if (e == true){
			e = false;
			keysPressed[KeyEvent.VK_E] = false;
			return true;
		}else
			return false;
	}
	
	public boolean wasSpacePressed(){
		if (space == true){
			space = false;
			keysPressed[KeyEvent.VK_SPACE] = false;
			return true;
		}else
			return false;
	}
}
