package model;

import game.Animation;
import game.GameObject;
import rend.ImageLoader;
import rend.Spritesheet;

import java.awt.*;

public class Bullet extends GameObject {

	private float speed = 0;
	private static Spritesheet sprite = new Spritesheet(ImageLoader.load("img/fireball.png"), 0, 64, 64);
	private boolean strana;
	private Player player;
	protected Animation animation;

	public Bullet(float x, float y, int width, int height, boolean strana, Player player) {
		super(x, y, width, height);
		this.player = player;
		this.strana = strana;

		if (strana) this.speed = -2;
		else this.speed = 2;

		if(this.strana){
			this.animation = new Animation(sprite, 0, 8, 30L);
		}else {
			this.animation = new Animation(sprite, 4, 8, 30L);
		}

		this.x = GamePanel.width / 2 / GamePanel.SCALE - width / 2;
		this.y = GamePanel.height / 2 / GamePanel.SCALE - height / 2;
	}

	public void update() {
		calculateMovement();
		move();
	}


	private void calculateMovement() {
		animation.update();
	}

	public void render(Graphics2D g) {
		g.drawImage(animation.getImage(), (int) x, (int) y, width, height, null);
	}

	public void move() {
		x += speed + player.getDx();
		y -= player.getDy();
	}

}
