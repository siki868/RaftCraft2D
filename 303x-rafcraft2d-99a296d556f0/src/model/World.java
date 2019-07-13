package model;

import game.Game;
import rend.Material;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class World {

	private int blocksX;
	private int blocksY;
	private Block[][] blocks;

	public World(String filepath, int chunksize) {
		loadWorld(filepath);
	}

	public World(BufferedImage map, int chunksize) {
		loadWorld2(map);
	}

	public void render(Graphics2D g) {
		//int blocksRendered = 0;
		Player player = Playstate.player;
		int startX = player.getCenterX() - GamePanel.width / GamePanel.SCALE / 2;
		int startY = player.getCenterY() - GamePanel.height / GamePanel.SCALE / 2;
		int endX = player.getCenterX() + GamePanel.width / GamePanel.SCALE / 2 + Game.BLOCKSIZE;
		int endY = player.getCenterY() + GamePanel.height / GamePanel.SCALE / 2 + Game.BLOCKSIZE;

		for (int row = startY; row <= endY; row += Game.BLOCKSIZE) {
			for (int col = startX; col <= endX; col += Game.BLOCKSIZE) {
				int blockX = getColTile(col);
				int blockY = getRowTile(row);
				if (blockX >= 0 && blockY >= 0 && blockX < this.blocksX && blockY < this.blocksY) {
					blocks[blockY][blockX].render(g);
					//blocksRendered++;
				}
			}
		}
		g.setColor(Color.red);
		g.fillRect(5, 5, player.getHealth(), 15);
		g.setColor(Color.white);
		g.drawString("" + player.getHealth(), 5, 16);
	}

	public void loadWorld2(BufferedImage img) {
		blocksX = img.getWidth();
		blocksY = img.getHeight();
		blocks = new Block[blocksY][blocksX];
		int id = 0;
		int pxcolor = 0;
		for (int y = 0; y < blocksY; y++) {
			for (int x = 0; x < blocksX; x++) {
				pxcolor = img.getRGB(x, y);
				if (pxcolor == Color.RED.getRGB()) {
					id = 6;
				} else if (pxcolor == Color.GREEN.getRGB()) {
					id = 2;
				} else if (pxcolor == Color.BLUE.getRGB()) {
					id = 4;
				} else if (pxcolor == Color.BLACK.getRGB()) {
					id = 0;
				} else if (pxcolor == Color.MAGENTA.getRGB()) {
					id = 5;
				} else if (pxcolor == Color.YELLOW.getRGB()) {
					id = 1;
				}
				blocks[y][x] = new Block(Material.values()[id], x * Game.BLOCKSIZE, y * Game.BLOCKSIZE, Game.BLOCKSIZE,
						Game.BLOCKSIZE);
			}
		}
	}

	public void loadWorld(String filepath) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(filepath)));
			blocksX = Integer.parseInt(reader.readLine());
			blocksY = Integer.parseInt(reader.readLine());
			blocks = new Block[blocksY][blocksX];

			for (int row = 0; row < blocksY; row++) {
				String line = reader.readLine();
				String tokens[] = line.split(" ");
				for (int col = 0; col < blocksX; col++) {
					int id = Integer.parseInt(tokens[col]);
					blocks[row][col] = new Block(Material.values()[id], col * Game.BLOCKSIZE, row * Game.BLOCKSIZE,
							Game.BLOCKSIZE, Game.BLOCKSIZE);
				}
			}

			reader.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int getRowTile(int y) {
		return y / Game.BLOCKSIZE;
	}

	public int getColTile(int x) {
		return x / Game.BLOCKSIZE;
	}

	public Block getBlock(int x, int y) {
		return blocks[getRowTile(y)][getColTile(x)];
	}

	public Block[][] getBlocks() {
		return blocks;
	}

}
