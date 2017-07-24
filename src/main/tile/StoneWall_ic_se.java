package main.tile;
import main.gfx.Assets;

public class StoneWall_ic_se extends Tile{

	public StoneWall_ic_se(int id) {
		super(Assets.wall_ic_se, id);
	}

	public boolean isSolid(){
		return true;
	}
}
