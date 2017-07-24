package main.entity.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import main.Handler;
import main.audio.SoundAssets;
import main.entity.Entity;
import main.gfx.Assets;
import main.id.IdManager;
import main.tile.Tile;

public class ChatBox extends Entity {
	private boolean showText;
	private boolean firstTick =true;
	private boolean displayFinished = false;
	
	private Font curentFont,font0,font1,font2,font3;
	private Color color;
	
	private String currentTxt ="";
	private String nextTxt ="";
	private String rawTxt = null;
	private String rawTxtBackup = null;
	private int maxCarecterPerLine = 32;
	
	private int anim_counter = 40;

	public ChatBox(Handler hand){
		super(hand,IdManager.Chatbox,0,408,Tile.TILEBREITE*8,Tile.TILEHoeHE*3);
		
		hight = 10;
		
		//Hitbox
		bounds.x=0;
		bounds.y=0;
		bounds.height = 0;
		bounds.height = 0;
		//checkbox
		checkbox.width=0;
		checkbox.height=0;
		
		//Text Font + Color
		
	     GraphicsEnvironment ge = 
	         GraphicsEnvironment.getLocalGraphicsEnvironment();
	     try {
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/font/joystix monospace.ttf")));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		color = Color.WHITE;
		font0 = new Font("joystix monospace", Font.PLAIN, 20);
		font1 = new Font("lunchds",Font.PLAIN, 20);
		font2 = new Font("lunchds", Font.ITALIC, 20);
		font3 = new Font("Monospaced", Font.PLAIN, 30);
		
		curentFont = font0;
	}
	@Override
	public void tick() {}
	@Override
	public void render(Graphics g) {
		if(showText){
			displayText(g);
		}
	}
	//Chatbox Text
	public void giveText(String txt){
		if(this.rawTxt == null){
			this.rawTxt = txt;
			this.rawTxtBackup = txt;
			firstTick = true;
		}
	}
	private void splitText(Graphics g, String txt){
		currentTxt = "";
		nextTxt = "";
		
		String txtP1;
		String txtP2;
		int charCount = 0;
		
		int y = hand.getHoehe()-this.hoehe+7;
		
		for(int i = 0; i< txt .length(); i++){
			
			if(txt.charAt(i)=='\n'){
				charCount = -1;
			}
			
			if(charCount == maxCarecterPerLine){
				for(int j = i; j>0 ;j--){
					if(txt.charAt(j)==' '){
						txtP1 = txt.substring(0,j);
						txtP2 = txt.substring(j+1, txt.length());
						txt = txtP1 + "\n" + txtP2;
						charCount = 0;
						i = j;
						break;
					}
				}
				
			}
			charCount ++;
			
		}
		
		for (String line : txt.split("\n")){
			if(y<hand.getHoehe()-30){
				y+=g.getFontMetrics().getHeight();
				currentTxt+=line+"\n";
			}else{
				nextTxt+= line+"\n";
			}
		}
	}
	public void displayText(Graphics g){
		hand.getWorld().getEnti().getPl().setBlocked(true);
		prepBox(hand.getGame().getG());
		calcAnimation(g);
		
		boolean tempE = hand.getKeyManager().wasEPressed();
		
		if(firstTick){
			splitText(g,rawTxt);
			firstTick = false;
			tempE = false;
			displayFinished = false;
			
			SoundAssets.interact.play();
		}
		
		if (tempE && nextTxt == ""){
			showText =false;
			rawTxt = null;
			hand.getKeyManager().e = false;
			hand.getWorld().getEnti().getPl().setBlocked(false);
			SoundAssets.interact.play();
			displayFinished = true;
		}else if(tempE){
			splitText(g,nextTxt);
			SoundAssets.interact.play();
		}else{
			int y= hand.getHoehe()-this.getHoehe()+7;
			for (String line : currentTxt.split("\n")){
				g.drawString(line, 18, y += g.getFontMetrics().getHeight());	
			}
		}
	}
	private void calcAnimation(Graphics g){
		if(anim_counter>40 && anim_counter < 80){
			g.drawImage(Assets.e_animation,440,hand.getHoehe()-Tile.TILEHoeHE,Tile.TILEBREITE,Tile.TILEHoeHE, null);
			anim_counter++;
		}else if (anim_counter>80){
			anim_counter = 0;	
		}else{
			anim_counter++;
		}
	}
	private void prepBox(Graphics g){
		g.drawImage(Assets.chat_box_nw,0,hand.getHoehe()-this.hoehe,Tile.TILEBREITE,Tile.TILEHoeHE, null);
		g.drawImage(Assets.chat_box_ne,breite,hand.getHoehe()-this.hoehe,Tile.TILEBREITE,Tile.TILEHoeHE, null);
		g.drawImage(Assets.chat_box_sw,0,hand.getHoehe()-Tile.TILEHoeHE,Tile.TILEBREITE,Tile.TILEHoeHE, null);
		g.drawImage(Assets.chat_box_se,breite,hand.getHoehe()-Tile.TILEHoeHE,Tile.TILEBREITE,Tile.TILEHoeHE, null);
		g.drawImage(Assets.chat_box_n,Tile.TILEBREITE,hand.getHoehe()-this.hoehe,breite-Tile.TILEBREITE,Tile.TILEHoeHE, null);
		g.drawImage(Assets.chat_box_e,breite,hand.getHoehe()-(this.hoehe-Tile.TILEHoeHE),Tile.TILEBREITE,hoehe-(Tile.TILEHoeHE*2), null);
		g.drawImage(Assets.chat_box_s,Tile.TILEBREITE,hand.getHoehe()-Tile.TILEHoeHE,breite-Tile.TILEBREITE,Tile.TILEHoeHE, null);
		g.drawImage(Assets.chat_box_w,0,hand.getHoehe()-(this.hoehe-Tile.TILEHoeHE),Tile.TILEBREITE,hoehe-Tile.TILEHoeHE*2, null);
		g.drawImage(Assets.chat_box_black,Tile.TILEBREITE,hand.getHoehe()-(this.hoehe-Tile.TILEHoeHE),breite-Tile.TILEBREITE,hoehe-(Tile.TILEHoeHE*2), null);
		g.setColor(color);
		g.setFont(curentFont);
		
	}
	
	//Font
	public void switchSpeeker(int id){
		switch(id){
		case 0:					//def
			curentFont = font0;
			color = Color.WHITE;
			break;
		case 1:		
			curentFont = font1;//test
			color = Color.WHITE;
			break;
		case 2:		
			curentFont = font1;//test
			color = Color.WHITE;
			break;
		case 3:		
			curentFont = font1;//test
			color = Color.WHITE;
			break;
		}
	}
	//getters and setters below
	public boolean isShowText() {
		return showText;
	}
	public void setShowText(boolean showText) {
		this.showText = showText;
	}
	public boolean isDisplayFinished() {
		return displayFinished;
	}
	public String getFinishedString() {
			return rawTxtBackup;
	}
	public void setDisplayFinished(boolean displayFinished) {
		this.displayFinished = displayFinished;
	}
	
}
