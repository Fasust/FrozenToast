package main.tile;
import main.gfx.Assets;

public class StoneWall_window_n extends Tile{

	public StoneWall_window_n(int id) {
		super(Assets.wall_window_n, id);
	}

	public boolean isSolid(){
		return true;
	}
}
