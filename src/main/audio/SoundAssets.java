package main.audio;

import main.Game;

public class SoundAssets {
	
	//SFX
	public static AudioPlayer
	button_press,interact,
	ice_push,ice_hit,
	stoneGate_traferse,
	door_open,
	piston_push,
	switch_level,
	pickup_blip_low,pickup_blip_mid,pickup_blip_high,
	curse_switch,freeze,freeze_ray,player_exaustion,curse_optained,
	entity_landing_from_fall,
	game_over,set_spawn,fall_in_water,
	dunce_high,dunce_mid,dunce_low,
	mishab,antisipation,tost_enter_slit,
	invince_blip_high,invince_blip_low,ping_dmg_taken,
	chefbot_hatch_down,chefbot_hatch_up,chefbot_toast_drop,chefbot_bell,
	dash,glimmer;
	
	//Musik
	public static AudioPlayer
	background_musik_level1;
	
	public static void preBoot(){

		//SFX
		pickup_blip_low = new AudioPlayer("/sound/pickup_blip_low.wav");
		pickup_blip_mid = new AudioPlayer("/sound/pickup_blip_mid.wav");
		pickup_blip_high = new AudioPlayer("/sound/pickup_blip_high.wav");
		
		
		button_press = new AudioPlayer("/sound/button_press.wav");
		interact = new AudioPlayer("/sound/interact.wav");
		
		ice_push = new AudioPlayer("/sound/ice_push.wav");
		ice_hit = new AudioPlayer("/sound/ice_hit.wav");
		
		stoneGate_traferse = new AudioPlayer("/sound/stoneGate_traferse.wav");
		door_open = new AudioPlayer("/sound/door_open.wav");
		
		piston_push = new AudioPlayer("/sound/piston_push.wav");
		
		switch_level = new AudioPlayer("/sound/switch_level.wav");
		game_over = new AudioPlayer("/sound/game_over.wav");
		set_spawn = new AudioPlayer("/sound/set_spawn.wav");
		
		curse_switch = new AudioPlayer("/sound/curse_switch.wav");
		curse_optained = new AudioPlayer("/sound/curse_optained.wav");
		player_exaustion = new AudioPlayer("/sound/player_exaustion.wav");
		freeze = new AudioPlayer("/sound/freeze.wav");
		freeze_ray = new AudioPlayer("/sound/freeze_ray.wav");
		
		entity_landing_from_fall = new AudioPlayer("/sound/entity_landing_from_fall.wav");
		fall_in_water = new AudioPlayer("/sound/fall_in_water.wav");
		
		dunce_high = new AudioPlayer("/sound/dunce_high.wav");
		dunce_mid = new AudioPlayer("/sound/dunce_mid.wav");
		dunce_low= new AudioPlayer("/sound/dunce_low.wav");
		
		mishab =  new AudioPlayer("/sound/mishab.wav");
		antisipation = new AudioPlayer("/sound/antisipation.wav");
		tost_enter_slit = new AudioPlayer("/sound/tost_enter_slit.wav");
		
		invince_blip_high =  new AudioPlayer("/sound/invince_blip_high.wav");
		invince_blip_low =  new AudioPlayer("/sound/invince_blip_low.wav");
		ping_dmg_taken =  new AudioPlayer("/sound/ping_dmg_taken.wav");
		
		chefbot_hatch_down=  new AudioPlayer("/sound/chefbot_hatch_down.wav");
		chefbot_hatch_up=  new AudioPlayer("/sound/chefbot_hatch_up.wav");
		chefbot_toast_drop=  new AudioPlayer("/sound/chefbot_toast_drop.wav");
		chefbot_bell=  new AudioPlayer("/sound/chefbot_bell.wav");
		
		dash=  new AudioPlayer("/sound/dash.wav");
		glimmer=  new AudioPlayer("/sound/glimmer.wav");
		
		//Musik
		if(!Game.DEBUG){
			background_musik_level1 = new AudioPlayer("/sound/music/background_musik_level1.mp3");
			background_musik_level1.changeVolum(-10);
		}
		
		//Volume
		interact.changeVolum(-10);
		ice_push.changeVolum(-10);
		piston_push.changeVolum(-15);
		stoneGate_traferse.changeVolum(-5);
		curse_switch.changeVolum(-7);
		switch_level.changeVolum(-20);
		freeze.changeVolum(-15);
		freeze_ray.changeVolum(-25);
		entity_landing_from_fall.changeVolum(-5);
		set_spawn.changeVolum(-10);
		tost_enter_slit.changeVolum(-5);
		
		chefbot_hatch_down.changeVolum(-15);
		chefbot_hatch_up.changeVolum(-15);
		chefbot_toast_drop.changeVolum(-10);
		chefbot_bell.changeVolum(-15);
		
		dunce_low.changeVolum(-15);
		dunce_mid.changeVolum(-15);
		dunce_high.changeVolum(-15);
		
		
		
	}
}
