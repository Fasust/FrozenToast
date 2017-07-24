package main.dialoge;

public class Dialoge {

	private final int DEF_STATE_COUNT = 25;
	private boolean[] states;
	private String[] talk;
	
	public Dialoge(){
		states = new boolean[DEF_STATE_COUNT];
		talk = new String[DEF_STATE_COUNT];
	}
	public void changeState(int i,boolean wert){
		states[i]= wert;
	}
	public boolean getState(int i){
		return states[i];
	}
	public void giveDialoge(int i,String speach){
		talk[i]= speach;
	}
	public String getDialoge(int i){
		return talk[i];
	}
}
