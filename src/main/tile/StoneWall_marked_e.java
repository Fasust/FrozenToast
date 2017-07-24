package main.tile;
import main.gfx.Assets;

public class StoneWall_marked_e extends Tile{

	public StoneWall_marked_e(int id) {
		super(Assets.wall_marked_with_e, id);
	}

	public boolean isSolid(){
		return true;
	}
}
