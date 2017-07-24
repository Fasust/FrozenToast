package main.entity.creature;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import main.Game;
import main.Handler;
import main.Level.Level;
import main.audio.SoundAssets;
import main.gfx.Animation;
import main.gfx.Assets;
import main.id.IdManager;
import main.playerStates.IceState;
import main.playerStates.NaturelState;
import main.playerStates.PlayerStates;
import main.tile.Tile;
import main.util.Timer;

public class Player extends Creature {
	
	public static final int DEF_PLAYERCHECKBOXSIZE = 40;
	
	//States
	private NaturelState natState;
	private IceState iceState;
	private PlayerStates currentState;
	
	//Mana
	private int mana;
	private int manaRegen = 3;
	private int maxMana = 100;
	
	//HP
	private int hp;
	private int maxHp = 100;
	private boolean invincebel = false;
	private Timer timer;
	private boolean firstInvincebel = true;
	private long invincebelityLength = 200;

	
	//Stamina
	private int stamina;
	private int staminaRegen = 2;
	private int maxStamina = 640;
	private boolean staminaRegBlock = false;
	private boolean exausted = false;
	private boolean firstInExaustion =true;
	
	//Logic
	private boolean blocked,focusBlocked,Invisabel = false;
	private boolean isBeingPorted,drowning = false;
	
	//Spawn
	private float currentSpawnX;
	private float currentSpawnY;
	private Level currentSpawnLevel;
	private boolean spawning = false;
	
	
	
	public Player(Handler hand, float x, float y) {
		super(hand,IdManager.Player,x, y,Creature.DEF_BREITE,Creature.DEF_HoeHE);
		//hitbox
		bounds.x = 2;
		bounds.y = 32;
		bounds.width= 60;
		bounds.height = 32;
		
		//checkbox
		checkbox.height = DEF_PLAYERCHECKBOXSIZE;
		checkbox.width = DEF_PLAYERCHECKBOXSIZE;
		
		//States
		natState = new NaturelState(hand,this);
		iceState = new IceState(hand,this);
		
		currentState = natState;
		
		if(Game.DEBUG)
			maxStamina = 10000;
		
		//hp,mana,stamina
		stamina = maxStamina;
		hp = maxHp;
		mana = maxMana;
	}
	@Override
	public void tick() {

		if(!blocked){
			currentState.tick();
		}else{
			setCheckboxX(-10000);
			setCheckboxY(-10000);
		}
		if(!focusBlocked){
			hand.getCam().centerOnEntity(this);
		}
		
		//STamina && Mana Regen
		
		restoreMana();
		restoreStamina();
		
		if(spawning){
			respawn();
		}
		//Invinc
		if(this.invincebel)
			frameOfinvincebelity();
	}
	@Override
	public void render(Graphics g) {
		
		if(!Invisabel)
			currentState.render(g);
		
		
	}
	
	
	//Mana
	private void restoreMana(){
		if(!exausted){
			if((mana < maxMana)){
				mana += manaRegen;
			}
		}else{
			mana = 0;
		}
	}
	//Stamian
	private void restoreStamina(){
		if((stamina < maxStamina)&& !staminaRegBlock && !exausted){
			stamina += staminaRegen;
		}
		if((stamina <= 0 || exausted)){
			if(firstInExaustion){
				SoundAssets.player_exaustion.play();
				firstInExaustion =false;
				stamina = 0;
			}
			exausted = true;
			stamina += staminaRegen/2;
			if(stamina > maxStamina/2){
				exausted = false;
				firstInExaustion = true;
			}
		}
	}
	public void depledStamina(int x){
		staminaRegBlock = true;
		stamina -= x;
	}
	//HP
	public void hurt(int dmg){
		if(!invincebel){
			if(hp-dmg > 0){
				hp -= dmg;
				
				invincebel = true;
			}else{
				hp = 0;
			}
		}
	}
	private void frameOfinvincebelity(){
		if(this.firstInvincebel){
			timer = new Timer(invincebelityLength);
			timer.start();
			
			firstInvincebel=false;
		}
		
		if(timer.isFinished()){

			firstInvincebel = true;
			invincebel = false;
		}
	}
	public PlayerStates getSelectedCurse(){
		//TemporarySetup
		
		if(currentState == iceState){
			return natState;
		}else{
			return iceState;
		}
	}
	//spawn
	public void setSpawn(){
		this.currentSpawnLevel = Level.curentLevel;
		this.currentSpawnX= this.x /Tile.TILEBREITE ;
		this.currentSpawnY = this.y / Tile.TILEHoeHE;
	}
	public void respawn(){
		Level.getLevel().getEntiPort().portPlayer(currentSpawnX, currentSpawnY, currentSpawnLevel, "spawn");
		
	}
	
	public void resetStatusEffects(){
		this.drowning = false;
		this.blocked = false;
		this.Invisabel = false;
		this.isBeingPorted = false;
		this.focusBlocked= false;
	}
	
	//getters and setters
	
	public char getFacing() {
		return facing;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getMaxHp() {
		return maxHp;
	}
	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}
	public boolean isExausted() {
		return exausted;
	}
	public void setExausted(boolean exausted) {
		this.exausted = exausted;
	}
	public boolean isStaminaRegBlock() {
		return staminaRegBlock;
	}
	public void setStaminaRegBlock(boolean staminaRegBlock) {
		this.staminaRegBlock = staminaRegBlock;
	}
	public int getStamina() {
		return stamina;
	}
	public void setStamina(int stamina) {
		this.stamina = stamina;
	}
	public int getStaminaRegen() {
		return staminaRegen;
	}
	public void setStaminaRegen(int staminaRegen) {
		this.staminaRegen = staminaRegen;
	}
	public int getMaxStamina() {
		return maxStamina;
	}
	public void setMaxStamina(int maxStamina) {
		this.maxStamina = maxStamina;
	}
	public int getMaxMana() {
		return maxMana;
	}
	public void setMaxMana(int maxMana) {
		this.maxMana = maxMana;
	}
	public int getMana() {
		return mana;
	}
	public void setMana(int mana) {
		this.mana = mana;
	}
	public void setFacing(char facing) {
		this.facing = facing;
	}
	public boolean isBlocked() {
		return blocked;
	}
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	@Override
	public float getSpeed() {
		return currentState.getSpeed();
	}
	@Override
	public void setSpeed(float speed) {
		currentState.setSpeed(speed);
	}
	public PlayerStates getCurrentState() {
		return currentState;
	}
	public void setCurrentState(PlayerStates currentState) {
		this.currentState = currentState;
	}
	public void setAnimSpeed(int speed){
		currentState.setAnim_speed(speed);
	}
	public NaturelState getNatState() {
		return natState;
	}
	public void setNatState(NaturelState natState) {
		this.natState = natState;
	}
	public IceState getIceState() {
		return iceState;
	}
	public void setIceState(IceState iceState) {
		this.iceState = iceState;
	}
	public boolean isBeingPorted() {
		return isBeingPorted;
	}
	public void setIsBeingPorted(boolean isBeingPorted) {
		this.isBeingPorted = isBeingPorted;
	}
	public boolean isFocusBlocked() {
		return focusBlocked;
	}
	public void setFocusBlocked(boolean focusBlocked) {
		this.focusBlocked = focusBlocked;
	}
	public boolean isInvisabel() {
		return Invisabel;
	}
	public void setInvisabel(boolean invisabel) {
		Invisabel = invisabel;
	}
	public boolean isDrowning() {
		return drowning;
	}
	public void setDrowning(boolean drowning) {
		this.drowning = drowning;
	}
	public boolean isSpawning() {
		return spawning;
	}
	public void setSpawning(boolean spawning) {
		this.spawning = spawning;
	}
	
}
	

