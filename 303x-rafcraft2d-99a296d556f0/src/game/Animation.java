package game;

import rend.Spritesheet;

import java.awt.image.BufferedImage;

public class Animation {

	private int state;
	private int frame;
	private int frames;
	private long delay;
	private long startTime;
	private Spritesheet sprite;

	public Animation(Spritesheet sprite, int state, int frames, long delay) {
		this.sprite = sprite;
		this.state = state;
		this.frames = frames;
		this.delay = delay;
		frame = 0;
		startTime = System.currentTimeMillis();
	}

	public void update() {
		if(System.currentTimeMillis() - startTime > delay) {
			frame++;
			if(frame == frames) frame = 0;
			startTime = System.currentTimeMillis();
		}

	}
	
	public BufferedImage getImage() {
		return sprite.getTexture(frame, state);
	}
	
	public void setImages(int state, int frames) {
		this.state = state;
		this.frames = frames;
		frame = 0;
		startTime = System.currentTimeMillis();
	}
	
	public int getState() {
		return state;
	}
}
