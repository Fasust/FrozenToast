package main.gfx;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	public static BufferedImage loadImage(String path){
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	public static BufferedImage cropImage(BufferedImage src, int x, int y, int width, int height) {
	      BufferedImage dest = src.getSubimage(x, y,width,height);
	      return dest; 
	   }
}
