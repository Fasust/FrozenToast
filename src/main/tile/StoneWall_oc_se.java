package main.tile;
import main.gfx.Assets;

public class StoneWall_oc_se extends Tile{

	public StoneWall_oc_se(int id) {
		super(Assets.wall_oc_se, id);
	}

	public boolean isSolid(){
		return true;
	}
}
