package rend;

import java.awt.image.BufferedImage;

public class Spritesheet {

	private int width;
	private int height;
	private int cols;
	private BufferedImage sprite;
	
	public Spritesheet(BufferedImage sprite, int cols, int width, int height) {
		this.sprite = sprite;
		this.cols = cols;
		this.width = width;
		this.height = height;
	}
	
	public BufferedImage getTexture(int id) {
		int col = (id%cols);
		int row = (id/cols);
		return sprite.getSubimage(col*width, row*height, width, height);
	}
	
	public BufferedImage getTexture(int col, int row) {
		return sprite.getSubimage(col*width, row*height, width, height);
	}

	
}
