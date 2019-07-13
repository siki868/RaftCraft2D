package rend;

import game.Game;

import java.awt.image.BufferedImage;

public enum Material {
	
	// MATERIAL LIST
	GRASS(1, false),
	DIRT(0, false),
	STONE(2, false),
	EDGE(5, false),
	AIR(5, true),
	WOOD(3, true),
	LEAF(4, true);
	

	private int id;
	private boolean walkable;
	private BufferedImage image;
	
	private Material(int id, boolean walkable) {
		this.id = id;
		this.walkable = walkable;
		this.image = Game.sprite.getTexture(id);
	}
	
	public int getID() {
		return id;
	}
	
	public boolean isWalkable() {
		return walkable;
	}

	public BufferedImage getTexture() {
		return image;
	}
}
