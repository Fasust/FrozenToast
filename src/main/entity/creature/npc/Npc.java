package main.entity.creature.npc;

import java.awt.Graphics;

import main.Handler;
import main.dialoge.Dialoge;
import main.entity.creature.Creature;

public abstract class Npc extends Creature {
	
	protected Dialoge dialoge;
	protected int CDS = 0;
	
	public Npc(Handler hand, int id, float x, float y, int breite, int hoehe) {
		super(hand, id, x, y, breite, hoehe);
		dialoge = new Dialoge();
		initDialoge();
	}
	protected abstract void tickDialoge();
	protected abstract void initDialoge();
	
}
