package main.gfx;
import java.awt.image.BufferedImage;

public class Assets {
	public static final int BREITE = 16, HoeHE = 16;
	public static BufferedImage 
	wood_floor,blue_tile,metal_floor,
	wall_window_n,
	water_bed, water_n,water_e,water_s,water_w,
	water_oc_ne,water_oc_se,water_oc_sw,water_oc_nw,
	water_ic_ne,water_ic_se,water_ic_sw,water_ic_nw,
	water_bed_border_ic_ne,water_bed_border_ic_se,water_bed_border_ic_sw,water_bed_border_ic_nw,
	water_bed_border_ic_n,
	water_br_n,water_br_e,water_br_s,water_br_w,
	ice_floor,ice_floor_noBorder,
	flor,wall_top,flor_marked,wall_marked_with_e,
	stone_door_s,stone_door_s_closed,stone_door_s_iron,
	stone_stairs_down,stone_stairs_up,
	stone_flor_hole_wall_n,stone_flor_hole,
	wall_end_w,wall_end_e,
	wall_s, wall_n,wall_w,wall_e,
	wall_oc_ne,wall_oc_se,wall_oc_sw,wall_oc_nw,
	wall_ic_ne,wall_ic_se,wall_ic_sw,wall_ic_nw,
	wall_s_hole,wall_e_hole,wall_w_hole,
	button, button_pressed,button_red, button_pressed_red,
	wall_button,wall_button_pressed,
	chest,chest_open,sing,sing_post,
	fish_green_dead,fish_green_dead_frozen,
	toast,toast_frozen,
	chat_box_n,chat_box_e,chat_box_s,chat_box_w,chat_box_ne,chat_box_se,chat_box_sw,chat_box_nw,chat_box_black,e_animation,
	note,slime,
	ping_ice_up,ping_ice_right,ping_ice_down,ping_ice_left,
	gui_main,gui_blue_bar,gui_green_bar,gui_red_bar,gui_fade,gui_ball_fade,gui_gold_block,gui_mini,
	torch_dim,
	ping_up_interact,pint_left_interact,ping_down_interact,ping_right_interact,
	ping_overlay_hold_front,ping_overlay_hold_leftside,ping_overlay_hold_rightside,
	ping_shadow,
	broken_toster_def,broken_toster_pow,broken_toster_sad,broken_toster_top,
	piston_stock_e,piston_head_e,piston_wall_top_e,piston_wall_food_e,
	piston_stock_w,piston_head_w,piston_wall_top_w,piston_wall_food_w,
	piston_stock_s,piston_head_s,piston_wall_top_s,piston_wall_food_s,
	chef_bot_body_top,chef_bot_body_base,chef_bot_mouth,chef_bot_hat,chef_bot_mustache,chef_bot_shadow,
	bathtub,bed;
	
	public static BufferedImage[] fish_green;
	public static BufferedImage[] broken_toster;
	public static BufferedImage[] ping_down;
	public static BufferedImage[] ping_up;
	public static BufferedImage[] ping_right;
	public static BufferedImage[] ping_left;
	public static BufferedImage[] ping_optain_item;
	public static BufferedImage[] ping_land;
	
	public static BufferedImage[] wall_torch;
	public static BufferedImage[] stone_gate;
	public static BufferedImage[] frezze_partiel;
	public static BufferedImage[] glow_partiel;
	public static BufferedImage[] spawn_egg;
	public static BufferedImage[] water_anim;
	public static BufferedImage[] key_prompts;
	public static BufferedImage[] egg_hatch;
	public static BufferedImage[] stone_hatch;
	public static BufferedImage[] chefbot_hatchswap;
	
	
	public static void preboot(){
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/texture/Tileset.png"));
		
		ping_down = new BufferedImage[9];
		ping_down[0]= sheet.crop(11*BREITE,13*HoeHE, BREITE, HoeHE);
		ping_down[1]= sheet.crop(10*BREITE,13*HoeHE, BREITE, HoeHE);
		ping_down[2]= sheet.crop(9*BREITE,13*HoeHE, BREITE, HoeHE);
		ping_down[3]= sheet.crop(10*BREITE,13*HoeHE, BREITE, HoeHE);
		ping_down[4]= sheet.crop(11*BREITE,13*HoeHE, BREITE, HoeHE);
		ping_down[5]= sheet.crop(13*BREITE,13*HoeHE, BREITE, HoeHE);
		ping_down[6]= sheet.crop(12*BREITE,13*HoeHE, BREITE, HoeHE);
		ping_down[7]= sheet.crop(13*BREITE,13*HoeHE, BREITE, HoeHE);
		ping_down[8]= sheet.crop(11*BREITE,13*HoeHE, BREITE, HoeHE);
		
		ping_up = new BufferedImage[9];
		ping_up[0]= sheet.crop(11*BREITE,14*HoeHE, BREITE, HoeHE);
		ping_up[1]= sheet.crop(10*BREITE,14*HoeHE, BREITE, HoeHE);
		ping_up[2]= sheet.crop(9*BREITE,14*HoeHE, BREITE, HoeHE);
		ping_up[3]= sheet.crop(10*BREITE,14*HoeHE, BREITE, HoeHE);
		ping_up[4]= sheet.crop(11*BREITE,14*HoeHE, BREITE, HoeHE);
		ping_up[5]= sheet.crop(13*BREITE,14*HoeHE, BREITE, HoeHE);
		ping_up[6]= sheet.crop(12*BREITE,14*HoeHE, BREITE, HoeHE);
		ping_up[7]= sheet.crop(13*BREITE,14*HoeHE, BREITE, HoeHE);
		ping_up[8]= sheet.crop(11*BREITE,14*HoeHE, BREITE, HoeHE);
		
		ping_right = new BufferedImage[9];
		ping_right[0]= sheet.crop(11*BREITE,11*HoeHE, BREITE, HoeHE);
		ping_right[1]= sheet.crop(11*BREITE,11*HoeHE, BREITE, HoeHE);
		ping_right[2]= sheet.crop(12*BREITE,11*HoeHE, BREITE, HoeHE);
		ping_right[3]= sheet.crop(13*BREITE,11*HoeHE, BREITE, HoeHE);
		ping_right[4]= sheet.crop(12*BREITE,11*HoeHE, BREITE, HoeHE);
		ping_right[5]= sheet.crop(11*BREITE,11*HoeHE, BREITE, HoeHE);
		ping_right[6]= sheet.crop(10*BREITE,11*HoeHE, BREITE, HoeHE);
		ping_right[7]= sheet.crop(9*BREITE,11*HoeHE, BREITE, HoeHE);
		ping_right[8]= sheet.crop(10*BREITE,11*HoeHE, BREITE, HoeHE);
		
		ping_left = new BufferedImage[9];
		ping_left[0]= sheet.crop(11*BREITE,12*HoeHE, BREITE, HoeHE);
		ping_left[1]= sheet.crop(11*BREITE,12*HoeHE, BREITE, HoeHE);
		ping_left[2]= sheet.crop(12*BREITE,12*HoeHE, BREITE, HoeHE);
		ping_left[3]= sheet.crop(13*BREITE,12*HoeHE, BREITE, HoeHE);
		ping_left[4]= sheet.crop(12*BREITE,12*HoeHE, BREITE, HoeHE);
		ping_left[5]= sheet.crop(11*BREITE,12*HoeHE, BREITE, HoeHE);
		ping_left[6]= sheet.crop(10*BREITE,12*HoeHE, BREITE, HoeHE);
		ping_left[7]= sheet.crop(9*BREITE,12*HoeHE, BREITE, HoeHE);
		ping_left[8]= sheet.crop(10*BREITE,12*HoeHE, BREITE, HoeHE);
		
		ping_optain_item = new BufferedImage[6];
		ping_optain_item[0]= sheet.crop(5*BREITE,13*HoeHE, BREITE, HoeHE*2);
		ping_optain_item[1]= sheet.crop(5*BREITE,13*HoeHE, BREITE, HoeHE*2);
		ping_optain_item[2]= sheet.crop(6*BREITE,13*HoeHE, BREITE, HoeHE*2);
		ping_optain_item[3]= sheet.crop(7*BREITE,13*HoeHE, BREITE, HoeHE*2);
		ping_optain_item[4]= sheet.crop(8*BREITE,13*HoeHE, BREITE, HoeHE*2);
		ping_optain_item[5]= sheet.crop(8*BREITE,13*HoeHE, BREITE, HoeHE*2);
		
		ping_land = new BufferedImage[6];
		ping_land[0]= ping_optain_item[5];
		ping_land[1]= ping_optain_item[5];
		ping_land[2]= ping_optain_item[4];
		ping_land[3]= ping_optain_item[3];
		ping_land[4]= ping_optain_item[2];
		ping_land[5]= ping_optain_item[1];
		
		water_anim = new BufferedImage[9];
		water_anim[0]= sheet.crop(18*BREITE,0*HoeHE, BREITE, HoeHE);
		water_anim[1]= sheet.crop(18*BREITE,0*HoeHE, BREITE, HoeHE);
		water_anim[2]= sheet.crop(19*BREITE,0*HoeHE, BREITE, HoeHE);
		water_anim[3]= sheet.crop(18*BREITE,1*HoeHE, BREITE, HoeHE);
		water_anim[4]= sheet.crop(19*BREITE,1*HoeHE, BREITE, HoeHE);
		water_anim[5]= sheet.crop(18*BREITE,2*HoeHE, BREITE, HoeHE);
		water_anim[6]= sheet.crop(19*BREITE,2*HoeHE, BREITE, HoeHE);
		water_anim[7]= sheet.crop(18*BREITE,3*HoeHE, BREITE, HoeHE);
		water_anim[8]= sheet.crop(19*BREITE,3*HoeHE, BREITE, HoeHE);	
		
		ping_up_interact = sheet.crop(14*BREITE,14*HoeHE, BREITE, HoeHE);
		pint_left_interact = sheet.crop(14*BREITE,12*HoeHE, BREITE, HoeHE);
		ping_down_interact = sheet.crop(14*BREITE,13*HoeHE, BREITE, HoeHE);
		ping_right_interact = sheet.crop(14*BREITE,11*HoeHE, BREITE, HoeHE);
		
		ping_overlay_hold_front = sheet.crop(14*BREITE,9*HoeHE, BREITE, HoeHE*2);
		ping_overlay_hold_leftside = sheet.crop(13*BREITE,9*HoeHE, BREITE, HoeHE*2);
		ping_overlay_hold_rightside = sheet.crop(12*BREITE,9*HoeHE, BREITE, HoeHE*2);
		
		ping_shadow = sheet.crop(11*BREITE,9*HoeHE, BREITE, HoeHE*2);
		
		fish_green = new BufferedImage[3];
		fish_green[0]= sheet.crop(10*BREITE,0, BREITE, HoeHE);
		fish_green[1]= sheet.crop(10*BREITE,0, BREITE, HoeHE);
		fish_green[2]= sheet.crop(11*BREITE,0, BREITE, HoeHE);
		
		broken_toster = new BufferedImage[3];
		broken_toster[0]= sheet.crop(10*BREITE,8*HoeHE, BREITE*2, HoeHE);
		broken_toster[1]= sheet.crop(10*BREITE,8*HoeHE, BREITE*2, HoeHE);
		broken_toster[2]= sheet.crop(12*BREITE,8*HoeHE, BREITE*2, HoeHE);
		
		broken_toster_def = sheet.crop(9*BREITE,9*HoeHE, BREITE, HoeHE);
		broken_toster_pow = sheet.crop(10*BREITE,10*HoeHE, BREITE, HoeHE);
		broken_toster_sad = sheet.crop(10*BREITE,9*HoeHE, BREITE, HoeHE);
		
		broken_toster_top= sheet.crop(12*BREITE,7*HoeHE, BREITE, HoeHE);
		
		wood_floor = sheet.crop(1*BREITE,4*HoeHE, BREITE, HoeHE);
		
		wall_torch = new BufferedImage[7];
		wall_torch[0] = sheet.crop(12*BREITE, 0*HoeHE, BREITE, HoeHE);
		wall_torch[1] = sheet.crop(12*BREITE, 0*HoeHE, BREITE, HoeHE);
		wall_torch[2] = sheet.crop(13*BREITE, 0*HoeHE, BREITE, HoeHE);
		wall_torch[3] = sheet.crop(14*BREITE, 0*HoeHE, BREITE, HoeHE);
		wall_torch[4] = sheet.crop(12*BREITE, HoeHE, BREITE, HoeHE);
		wall_torch[5] = sheet.crop(13*BREITE, HoeHE, BREITE, HoeHE);
		wall_torch[6] = sheet.crop(14*BREITE, HoeHE, BREITE, HoeHE);
		
		stone_gate = new BufferedImage[7];
		stone_gate[0] = sheet.crop(9*BREITE,6*HoeHE, BREITE, HoeHE);
		stone_gate[1] = sheet.crop(9*BREITE,6*HoeHE, BREITE, HoeHE);
		stone_gate[2] = sheet.crop(10*BREITE,6*HoeHE, BREITE, HoeHE);
		stone_gate[3] = sheet.crop(11*BREITE,6*HoeHE, BREITE, HoeHE);
		stone_gate[4] = sheet.crop(9*BREITE,7*HoeHE, BREITE, HoeHE);
		stone_gate[5] = sheet.crop(10*BREITE,7*HoeHE, BREITE, HoeHE);
		stone_gate[6] = sheet.crop(11*BREITE,7*HoeHE, BREITE, HoeHE);
		
		spawn_egg = new BufferedImage[5];
		spawn_egg[0] = sheet.crop(4*BREITE,3*HoeHE, BREITE, HoeHE);
		spawn_egg[1] = sheet.crop(1*BREITE,3*HoeHE, BREITE, HoeHE);
		spawn_egg[2] = sheet.crop(2*BREITE,3*HoeHE, BREITE, HoeHE);
		spawn_egg[3] = sheet.crop(3*BREITE,3*HoeHE, BREITE, HoeHE);
		spawn_egg[4] = sheet.crop(2*BREITE,3*HoeHE, BREITE, HoeHE);
		
		egg_hatch = new BufferedImage[6];
		egg_hatch[0]= sheet.crop(15*BREITE,13*HoeHE, BREITE, 2*HoeHE);
		egg_hatch[1]= sheet.crop(15*BREITE,13*HoeHE, BREITE, 2*HoeHE);
		egg_hatch[2]= sheet.crop(16*BREITE,13*HoeHE, BREITE, 2*HoeHE);
		egg_hatch[3]= sheet.crop(17*BREITE,13*HoeHE, BREITE, 2*HoeHE);
		egg_hatch[4]= sheet.crop(18*BREITE,13*HoeHE, BREITE, 2*HoeHE);
		egg_hatch[5]= sheet.crop(18*BREITE,13*HoeHE, BREITE, 2*HoeHE);
		
		frezze_partiel = new BufferedImage[5];
		frezze_partiel[0] = sheet.crop(10*BREITE,3*HoeHE, BREITE, HoeHE);
		frezze_partiel[1] = sheet.crop(10*BREITE,3*HoeHE, BREITE, HoeHE);
		frezze_partiel[2] = sheet.crop(11*BREITE,3*HoeHE, BREITE, HoeHE);
		frezze_partiel[3] = sheet.crop(10*BREITE,4*HoeHE, BREITE, HoeHE);
		frezze_partiel[4] = sheet.crop(11*BREITE,4*HoeHE, BREITE, HoeHE);
		
		glow_partiel = new BufferedImage[5];
		glow_partiel[0] = sheet.crop(12*BREITE,3*HoeHE, BREITE, HoeHE);
		glow_partiel[1] = sheet.crop(12*BREITE,3*HoeHE, BREITE, HoeHE);
		glow_partiel[2] = sheet.crop(13*BREITE,3*HoeHE, BREITE, HoeHE);
		glow_partiel[3] = sheet.crop(12*BREITE,4*HoeHE, BREITE, HoeHE);
		glow_partiel[4] = sheet.crop(13*BREITE,4*HoeHE, BREITE, HoeHE);
		
		ping_ice_up = sheet.crop(11*BREITE,HoeHE, BREITE, HoeHE);
		ping_ice_right = sheet.crop(10*BREITE,2*HoeHE, BREITE, HoeHE);
		ping_ice_down = sheet.crop(10*BREITE,HoeHE, BREITE, HoeHE);
		ping_ice_left = sheet.crop(11*BREITE,2*HoeHE, BREITE, HoeHE);
		
		key_prompts = new BufferedImage[3];
		key_prompts[0] = sheet.crop(14*BREITE,2*HoeHE, 4*BREITE, 3*HoeHE);
		key_prompts[1] = sheet.crop(14*BREITE,2*HoeHE, 4*BREITE, 3*HoeHE);
		key_prompts[2] = sheet.crop(14*BREITE,5*HoeHE, 4*BREITE, 3*HoeHE);
		
		stone_hatch = new BufferedImage[5];
		stone_hatch[0] = sheet.crop(11*BREITE,5*HoeHE, BREITE, HoeHE);
		stone_hatch[1] = sheet.crop(11*BREITE,5*HoeHE, BREITE, HoeHE);
		stone_hatch[2] = sheet.crop(12*BREITE,5*HoeHE, BREITE, HoeHE);
		stone_hatch[3] = sheet.crop(13*BREITE,5*HoeHE, BREITE, HoeHE);
		stone_hatch[4] = sheet.crop(12*BREITE,6*HoeHE, BREITE, HoeHE);
		
		chefbot_hatchswap = new BufferedImage[9];
		chefbot_hatchswap[0]  = sheet.crop(15*BREITE,8*HoeHE, BREITE, 2*HoeHE);
		chefbot_hatchswap[1]  = sheet.crop(15*BREITE,8*HoeHE, BREITE, 2*HoeHE);
		chefbot_hatchswap[2]  = sheet.crop(16*BREITE,8*HoeHE, BREITE, 2*HoeHE);
		chefbot_hatchswap[3]  = sheet.crop(17*BREITE,8*HoeHE, BREITE, 2*HoeHE);
		chefbot_hatchswap[4]  = sheet.crop(15*BREITE,10*HoeHE, BREITE, 2*HoeHE);
		chefbot_hatchswap[5]  = sheet.crop(16*BREITE,10*HoeHE, BREITE, 2*HoeHE);
		chefbot_hatchswap[6]  = sheet.crop(17*BREITE,10*HoeHE, BREITE, 2*HoeHE);
		chefbot_hatchswap[7]  = sheet.crop(18*BREITE,10*HoeHE, BREITE, 2*HoeHE);
		
		flor = sheet.crop(0, 0, BREITE, HoeHE);
		blue_tile =sheet.crop(BREITE,0, BREITE, HoeHE);
		metal_floor =sheet.crop(BREITE*2,0, BREITE, HoeHE);
		flor_marked=sheet.crop(3*BREITE,6*HoeHE, BREITE, HoeHE);
		wall_top = sheet.crop(2*BREITE,HoeHE, BREITE, HoeHE);
		sing = sheet.crop(9*BREITE,5*HoeHE, BREITE, HoeHE);
		sing_post = sheet.crop(8*BREITE,5*HoeHE, BREITE, HoeHE);
		note = sheet.crop(8*BREITE,7*HoeHE, BREITE, HoeHE);
		
		stone_door_s=sheet.crop(4*BREITE,6*HoeHE, BREITE, HoeHE);
		stone_door_s_closed=sheet.crop(5*BREITE,6*HoeHE, BREITE, HoeHE);
		stone_door_s_iron =sheet.crop(10*BREITE,5*HoeHE, BREITE, HoeHE);
		
		stone_stairs_down =sheet.crop(4*BREITE,5*HoeHE, BREITE, HoeHE);
		stone_stairs_up =sheet.crop(5*BREITE,5*HoeHE, BREITE, HoeHE);
		stone_flor_hole_wall_n =sheet.crop(6*BREITE,6*HoeHE, BREITE, HoeHE);
		stone_flor_hole =sheet.crop(BREITE,8*HoeHE, BREITE, HoeHE);
		
		wall_s = sheet.crop(2*BREITE,2*HoeHE, BREITE, HoeHE);
		wall_n = sheet.crop(2*BREITE,0, BREITE, HoeHE);
		wall_e = sheet.crop(3*BREITE,HoeHE, BREITE, HoeHE);
		wall_w = sheet.crop(BREITE,HoeHE, BREITE, HoeHE);
		
		wall_oc_ne = sheet.crop(3*BREITE,0, BREITE, HoeHE);
		wall_oc_se = sheet.crop(3*BREITE,2*HoeHE, BREITE, HoeHE);
		wall_oc_sw = sheet.crop(BREITE,2*HoeHE, BREITE, HoeHE);
		wall_oc_nw = sheet.crop(BREITE,0, BREITE, HoeHE);
		
		wall_ic_ne = sheet.crop(4*BREITE,HoeHE, BREITE, HoeHE);
		wall_ic_se = sheet.crop(4*BREITE,0, BREITE, HoeHE);
		wall_ic_sw = sheet.crop(5*BREITE,0, BREITE, HoeHE);
		wall_ic_nw = sheet.crop(5*BREITE,HoeHE, BREITE, HoeHE);
		
		wall_s_hole = sheet.crop(0*BREITE,6*HoeHE, BREITE, HoeHE);
		wall_e_hole = sheet.crop(1*BREITE,6*HoeHE, BREITE, HoeHE);
		wall_w_hole = sheet.crop(2*BREITE,6*HoeHE, BREITE, HoeHE);
		
		wall_end_w = sheet.crop(BREITE*4,2*HoeHE, BREITE, HoeHE);
		wall_end_e = sheet.crop(BREITE*5,2*HoeHE, BREITE, HoeHE);
		
		wall_window_n = sheet.crop(BREITE*13,6*HoeHE, BREITE, HoeHE);
		
		wall_marked_with_e = sheet.crop(BREITE,5*HoeHE, BREITE, HoeHE);
		
		water_bed = sheet.crop(0,2*HoeHE, BREITE, HoeHE);
		
		water_s = sheet.crop(6*BREITE,3*HoeHE, BREITE, HoeHE);
		water_n = sheet.crop(7*BREITE,2*BREITE, BREITE, HoeHE);
		water_e = sheet.crop(7*BREITE,3*HoeHE, BREITE, HoeHE);
		water_w = sheet.crop(6*BREITE,2*HoeHE, BREITE, HoeHE);
		
		water_oc_ne = sheet.crop(7*BREITE,0, BREITE, HoeHE);
		water_oc_se = sheet.crop(7*BREITE,HoeHE, BREITE, HoeHE);
		water_oc_sw = sheet.crop(6*BREITE,HoeHE, BREITE, HoeHE);
		water_oc_nw = sheet.crop(6*BREITE,0, BREITE, HoeHE);
		
		water_ic_ne = sheet.crop(8*BREITE,HoeHE, BREITE, HoeHE);
		water_ic_se = sheet.crop(8*BREITE,0, BREITE, HoeHE);
		water_ic_sw = sheet.crop(9*BREITE,0, BREITE, HoeHE);
		water_ic_nw = sheet.crop(9*BREITE,HoeHE, BREITE, HoeHE);
		
		water_br_n = sheet.crop(9*BREITE,3*HoeHE, BREITE, HoeHE);
		water_br_e = sheet.crop(9*BREITE,2*BREITE, BREITE, HoeHE);
		water_br_s = sheet.crop(8*BREITE,3*HoeHE, BREITE, HoeHE);
		water_br_w = sheet.crop(8*BREITE,2*HoeHE, BREITE, HoeHE);
		
		water_bed_border_ic_ne = sheet.crop(15*BREITE,HoeHE, BREITE, HoeHE);
		water_bed_border_ic_se = sheet.crop(15*BREITE,0, BREITE, HoeHE);
		water_bed_border_ic_sw = sheet.crop(16*BREITE,0, BREITE, HoeHE);
		water_bed_border_ic_nw = sheet.crop(16*BREITE,HoeHE, BREITE, HoeHE);
		
		water_bed_border_ic_n = sheet.crop(17*BREITE,0, BREITE, HoeHE);

		
		ice_floor = sheet.crop(0*BREITE,1*HoeHE, BREITE, HoeHE);
		ice_floor_noBorder = sheet.crop(0*BREITE,3*HoeHE, BREITE, HoeHE);
		
		button = sheet.crop(6*BREITE,4*HoeHE, BREITE, HoeHE);
		button_pressed = sheet.crop(7*BREITE,4*BREITE, BREITE, HoeHE);
		
		wall_button_pressed= sheet.crop(8*BREITE,6*HoeHE, BREITE, HoeHE);
		wall_button=sheet.crop(7*BREITE,6*HoeHE, BREITE, HoeHE);
		
		button_red = sheet.crop(6*BREITE,5*HoeHE, BREITE, HoeHE);
		button_pressed_red = sheet.crop(7*BREITE,5*BREITE, BREITE, HoeHE);
		
		chest= sheet.crop(8*BREITE,4*HoeHE, BREITE, HoeHE);
		chest_open= sheet.crop(9*BREITE,4*HoeHE, BREITE, HoeHE);
		
		fish_green_dead = sheet.crop(0*BREITE,5*HoeHE, BREITE, HoeHE);
		fish_green_dead_frozen = sheet.crop(2*BREITE,5*HoeHE, BREITE, HoeHE);
		
		toast = sheet.crop(8*BREITE,8*HoeHE, BREITE, HoeHE);
		toast_frozen= sheet.crop(9*BREITE,8*HoeHE, BREITE, HoeHE);
		
		slime = sheet.crop(3*BREITE,5*HoeHE, BREITE, HoeHE);
		
		torch_dim = sheet.crop(12*BREITE,2*HoeHE, BREITE, HoeHE);
		
		piston_stock_e = sheet.crop(19*BREITE,4*HoeHE, BREITE, HoeHE);
		piston_head_e = sheet.crop(19*BREITE,5*HoeHE, BREITE, HoeHE);
		piston_wall_top_e = sheet.crop(18*BREITE,5*HoeHE, BREITE, HoeHE);
		piston_wall_food_e = sheet.crop(18*BREITE,4*HoeHE, BREITE, HoeHE);
		
		piston_stock_w = sheet.crop(19*BREITE,6*HoeHE, BREITE, HoeHE);
		piston_head_w = sheet.crop(19*BREITE,7*HoeHE, BREITE, HoeHE);
		piston_wall_top_w = sheet.crop(18*BREITE,7*HoeHE, BREITE, HoeHE);
		piston_wall_food_w = sheet.crop(18*BREITE,6*HoeHE, BREITE, HoeHE);
		
		piston_stock_s = sheet.crop(19*BREITE,8*HoeHE, BREITE, HoeHE);
		piston_head_s = sheet.crop(19*BREITE,9*HoeHE, BREITE, HoeHE);
		piston_wall_top_s = sheet.crop(18*BREITE,9*HoeHE, BREITE, HoeHE);
		piston_wall_food_s = sheet.crop(18*BREITE,8*HoeHE, BREITE, HoeHE);
		
		chef_bot_mouth = sheet.crop(3*BREITE,4*HoeHE, BREITE, HoeHE);
		chef_bot_body_top = sheet.crop(5*BREITE,3*HoeHE, BREITE, HoeHE-3);
		chef_bot_body_base = sheet.crop(5*BREITE,4*HoeHE-3, BREITE, HoeHE+3);
		chef_bot_hat = sheet.crop(4*BREITE,4*HoeHE, BREITE, HoeHE);
		chef_bot_mustache = sheet.crop(2*BREITE,4*HoeHE, BREITE, HoeHE);
		chef_bot_shadow = sheet.crop(14*BREITE,8*HoeHE, BREITE, HoeHE);
		
		bathtub = sheet.crop(3*BREITE,7*HoeHE, BREITE*3, HoeHE*2);
		bed = sheet.crop(6*BREITE,7*HoeHE, BREITE, HoeHE*2);
		
		//GUI
		chat_box_n = sheet.crop(BREITE,7*HoeHE,BREITE, HoeHE);
		chat_box_e = sheet.crop(2*BREITE,8*HoeHE,BREITE, HoeHE);
		chat_box_s = sheet.crop(BREITE,9*HoeHE,BREITE, HoeHE);
		chat_box_w = sheet.crop(0,8*HoeHE,BREITE, HoeHE);
		chat_box_ne = sheet.crop(2*BREITE,7*HoeHE,BREITE, HoeHE);
		chat_box_se = sheet.crop(2*BREITE,9*HoeHE,BREITE, HoeHE);
		chat_box_sw = sheet.crop(0,9*HoeHE,BREITE, HoeHE);
		chat_box_nw = sheet.crop(0,7*HoeHE,BREITE, HoeHE);
		chat_box_black = sheet.crop(BREITE,8*HoeHE,BREITE, HoeHE);
		
		gui_main =sheet.crop(0*BREITE,10*HoeHE, 9*BREITE,2* HoeHE);
		
		gui_red_bar =sheet.crop(9*BREITE,10*HoeHE,1,4);
		gui_green_bar =sheet.crop(9*BREITE+1,10*HoeHE,1,4);
		gui_blue_bar =sheet.crop(9*BREITE,10*HoeHE+4,2,1);
		
		gui_fade =sheet.crop(0*BREITE,12*HoeHE, 2*BREITE,2*HoeHE);
		gui_ball_fade = sheet.crop(9*BREITE,10*HoeHE+12,4,4);
		gui_gold_block =sheet.crop(2*BREITE,12*HoeHE, 3*BREITE,2*HoeHE);
		
		gui_mini = sheet.crop(0*BREITE,4*HoeHE,BREITE,HoeHE);
	}
}
