package main.tile;
import main.gfx.Assets;

public class StoneWallSouth extends Tile{

	public StoneWallSouth(int id) {
		super(Assets.wall_s, id);
	}

	public boolean isSolid(){
		return true;
	}
}
