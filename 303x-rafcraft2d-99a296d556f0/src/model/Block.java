package model;

import game.GameObject;
import rend.Material;

import java.awt.Graphics2D;

public class Block extends GameObject {

	private Material material;

	public Block(Material material, float x, float y, int width, int height) {
		super(x, y, width, height);
		this.material = material;
	}

	public void update() {

	}

	public void render(Graphics2D g) {
		g.drawImage(material.getTexture(), (int) x - Playstate.camera.getCamX(), (int) y - Playstate.camera.getCamY(),
				width, height, null);
	}

	public Material getMaterial() {
		return material;
	}

}
