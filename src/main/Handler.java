package main;
import main.gfx.Camera;
import main.input.KeyManager;
import main.world.World;

/* Saves Game and World Objects
 * Other classes use Handler to acceses World and Game Functions
 */

public class Handler {
	private Game game;
	private World world;
	
	public Handler(Game game){
		this.game = game;
		this.world= null;
	}
	
	//Game getters and setters
	public Game getGame() {
		return game;
	}
	
	public Camera getCam(){
		return game.getCam();
	}
	
	public KeyManager getKeyManager(){
		return (KeyManager) game.getKeyManager();
	}
	public int getBreite(){
		return game.getBreite();
	}
	public int getHoehe(){
		return game.getHoehe();
	}
	
	public void setGame(Game game) {
		this.game = game;
	}

	//world Getters and setters
	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}	
}
