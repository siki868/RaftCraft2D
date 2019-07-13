package rend;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	public static int blocksize = 80;
	BufferedImage raster;

	public Sprite() {
		this.raster = new BufferedImage(240, 160, BufferedImage.TYPE_INT_ARGB);
		File outputfile = new File("img/spritesheet.png");
		int rgb[] = { 100, 100, 255 };
		int col = new Color(rgb[0], rgb[1], rgb[2], 0).getRGB();
		for (int y = 0; y < this.raster.getHeight(); y++) {
			for (int x = 0; x < this.raster.getWidth(); x++) {
				this.raster.setRGB(x, y, col);
			}
		}
		drawDirtBlock(0, 0);
		drawGrassBlock(0, 1);
		drawStoneBlock(0, 2);
		drawWoodBlock(1, 0);
		drawLeafBlock(1, 1);
		try {
			ImageIO.write(raster, "PNG", outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void drawLeafBlock(int x, int y) {
		int startX = x * blocksize;
		int startY = y * blocksize;
		int[] rgb1 = { 34, 139, 34 };
		int[] rgb2 = { 127, 255, 0 };
		int col = 0;
		for (int i = startY; i < startY + blocksize; i++) {
			for (int j = startX; j < startX + blocksize; j++) {
				int r = (int) (Math.random() * ((3) + 1));
				switch (r) {
				case 1: {
					col = new Color(rgb1[0], rgb1[1], rgb1[2], 255).getRGB();
					raster.setRGB(i, j, col);
					break;
				}
				case 2: {
					col = new Color(rgb2[0], rgb2[1], rgb2[2], 255).getRGB();
					raster.setRGB(i, j, col);
					break;
				}
				case 3: {
					col = new Color(rgb1[0], rgb1[1], rgb1[2], 0).getRGB();
					raster.setRGB(i, j, col);
				}
				}

			}
		}
	}

	public void drawWoodBlock(int y, int x) {
		int startX = x * blocksize;
		int startY = y * blocksize;
		int[] rgb = { 160, 82, 45 };
		int[] rgb1 = { 205, 133, 63 };
		int col = (rgb[0] << 16) | (rgb[1] << 8) | rgb[2];
		for (int i = startY; i < startY + blocksize; i++) {
			for (int j = startX + 16; j < startX + blocksize - 16; j++) {
				col = new Color(rgb[0], rgb[1], rgb[2], 255).getRGB();
				raster.setRGB(j, i, col);
			}
		}
		for (int i = startY; i < startY + blocksize; i++) {
			for (int j = startX + 10; j < startX + 15; j++) {
				col = new Color(rgb1[0], rgb1[1], rgb1[2], 255).getRGB();
				raster.setRGB(j, i, col);
			}
		}
		for (int i = startY; i < startY + blocksize; i++) {
			for (int j = startX + blocksize - 16; j < startX + blocksize - 10; j++) {
				col = new Color(rgb1[0], rgb1[1], rgb1[2], 255).getRGB();
				raster.setRGB(j, i, col);
			}
		}
	}

	public void drawStoneBlock(int x, int y) {
		int startX = x * blocksize;
		int startY = y * blocksize;
		int[] rgb1 = { 112, 128, 144 };
		int[] rgb2 = { 119, 136, 153 };
		int[] rgb3 = { 105, 105, 105 };
		int col = new Color(rgb1[0], rgb1[1], rgb1[2], 255).getRGB();
		for (int i = startY; i < startY + blocksize; i++) {
			for (int j = startX; j < startX + blocksize; j++) {
				int r = (int) (Math.random() * ((3) + 1));
				switch (r) {
				case 1: {
					col = new Color(rgb1[0], rgb1[1], rgb1[2], 255).getRGB();
					break;
				}
				case 2: {
					col = new Color(rgb2[0], rgb2[1], rgb2[2], 255).getRGB();
					break;
				}
				case 3: {
					col = new Color(rgb3[0], rgb3[1], rgb3[2], 255).getRGB();
					break;
				}
				}
				raster.setRGB(i, j, col);
			}
		}
	}

	public void drawGrassBlock(int x, int y) {
		int startX = x * blocksize;
		int startY = y * blocksize;
		int[] rgb = new int[3];
		int[] rgb1 = { 139, 69, 19 };
		int[] rgb2 = { 160, 82, 45 };
		int[] rgb3 = { 128, 0, 0 };

		int[] rgb4 = { 124, 252, 0 };
		int[] rgb5 = { 127, 255, 0 };
		int[] rgb6 = { 0, 255, 0 };

		int col = (rgb[0] << 16) | (rgb[1] << 8) | rgb[2];
		for (int i = startY; i < startY + blocksize; i++) {
			for (int j = startX + 5; j < startX + blocksize; j++) {
				int r = (int) (Math.random() * ((3) + 1));
				switch (r) {
				case 1: {
					col = new Color(rgb1[0], rgb1[1], rgb1[2], 255).getRGB();
					break;
				}
				case 2: {
					col = new Color(rgb2[0], rgb2[1], rgb2[2], 255).getRGB();
					break;
				}
				case 3: {
					col = new Color(rgb3[0], rgb3[1], rgb3[2], 255).getRGB();
					break;
				}
				}
				raster.setRGB(i, j, col);
			}
		}
		for (int i = startY; i < startY + blocksize; i++) {
			for (int j = startX; j < startX + 10; j++) {
				int r = (int) (Math.random() * ((3) + 1));
				switch (r) {
				case 1: {
					col = new Color(rgb4[0], rgb4[1], rgb4[2], 255).getRGB();
					break;
				}
				case 2: {
					col = new Color(rgb5[0], rgb5[1], rgb5[2], 255).getRGB();
					break;
				}
				case 3: {
					col = new Color(rgb6[0], rgb6[1], rgb6[2], 255).getRGB();
					break;
				}
				}
				raster.setRGB(i, j, col);
			}
		}
	}

	public void drawDirtBlock(int x, int y) {
		int startX = x * blocksize;
		int startY = y * blocksize;
		int[] rgb1 = { 139, 69, 19 };
		int[] rgb2 = { 160, 82, 45 };
		int[] rgb3 = { 128, 0, 0 };
		int col = (rgb1[0] << 16) | (rgb1[1] << 8) | rgb1[2];
		for (int i = startY; i < startY + blocksize; i++) {
			for (int j = startX; j < startX + blocksize; j++) {
				int r = (int) (Math.random() * ((3) + 1));
				switch (r) {
				case 1: {
					col = new Color(rgb1[0], rgb1[1], rgb1[2], 255).getRGB();
					break;
				}
				case 2: {
					col = new Color(rgb2[0], rgb2[1], rgb2[2], 255).getRGB();
					break;
				}
				case 3: {
					col = new Color(rgb3[0], rgb3[1], rgb3[2], 255).getRGB();
					break;
				}
				}
				raster.setRGB(i, j, col);
			}
		}
	}

}
