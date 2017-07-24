package main.tile;
import main.gfx.Assets;

public class StoneWall_ic_sw extends Tile{

	public StoneWall_ic_sw(int id) {
		super(Assets.wall_ic_sw, id);
	}

	public boolean isSolid(){
		return true;
	}
}
