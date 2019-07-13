package model;

import game.Animation;
import game.Game;
import game.GameObject;
import rend.Spritesheet;

import java.awt.Graphics2D;

public class Entity extends GameObject {

	// Constants
	private final float GRAVITY = 0.2F;
	private final float MAX_FALLING_SPEED = 5F;
	private final float JUMP_START = -3.5F;

	protected int health = 100;;

	// Movement
	protected float speed;
	protected float dx;
	protected float dy;
	protected float dyDif = 0;;
	protected boolean left;
	protected boolean right;
	protected boolean falling;
	protected boolean jumping;

	// Collision
	protected boolean topLeft;
	protected boolean topRight;
	protected boolean midLeft;
	protected boolean midRight;
	protected boolean botLeft;
	protected boolean botRight;

	// Animation
	protected int idle;
	protected int state;
	protected int[] frames = { 10, 10, 10, 10, 10, 10 };
	protected final int IDLE_LEFT = 1;
	protected final int IDLE_RIGHT = 0;
	protected final int LEFT = 3;
	protected final int RIGHT = 2;
	protected final int JUMP_LEFT = 5;
	protected final int JUMP_RIGHT = 4;
	protected Animation animation;

	public Entity(Spritesheet sprite, float x, float y, int width, int height, float speed) {
		super(x, y, width, height);
		this.speed = speed;
		this.animation = new Animation(sprite, IDLE_RIGHT, frames[IDLE_RIGHT], 120L);
		this.idle = IDLE_RIGHT;
	}

	public void render(Graphics2D g) {
		g.drawImage(animation.getImage(), (int) x, (int) y, width, height, null);
	}

	public void update() {
		if (dyDif > MAX_FALLING_SPEED * 15) {
			health -= 2;
		}
		calculateMovement();
		calculateCollision();
		calculateAnimations();
		move();
	}

	private void calculateAnimations() {
		animation.update();
		if (left && animation.getState() != LEFT) {
			animation.setImages(LEFT, frames[LEFT]);
			idle = IDLE_LEFT;
		} else if (right && animation.getState() != RIGHT) {
			animation.setImages(RIGHT, frames[RIGHT]);
			idle = IDLE_RIGHT;
		}
		if (!left && !right) {
			animation.setImages(idle, frames[idle]);
		}
		if (jumping && left) {
			animation.setImages(JUMP_LEFT, frames[JUMP_LEFT]);
		}
		if (jumping && right) {
			animation.setImages(JUMP_RIGHT, frames[JUMP_RIGHT]);
		}
		if (falling && left) {
			animation.setImages(JUMP_LEFT, frames[JUMP_LEFT]);
		}
		if (falling && right) {
			animation.setImages(JUMP_RIGHT, frames[JUMP_RIGHT]);
		}
	}

	private void calculateCollision() {
		float tox = x + dx;
		float toy = y + dy;

		// Collision left and right
		calculateCorners(tox, y - 1);
		if (dx < 0) {
			if (topLeft || midLeft || botLeft) {
				dx = 0;
			}
		}
		if (dx > 0) {
			if (topRight || midRight || botRight) {
				dx = 0;
			}
		}
		// Collision top and bottom
		calculateCorners(x, toy);
		if (botLeft || botRight && falling) {
			falling = false;
			if (dyDif > MAX_FALLING_SPEED * 2) {
				health -= 10;
			}
			dyDif = 0;
			dy = 0;
			int playerRow = Playstate.world.getRowTile((int) toy + height);
			y = (playerRow * Game.BLOCKSIZE - height + 3);
		}

		if (!botLeft && !botRight) {
			falling = true;
		}

		if (topLeft || topRight) {
			dy = 0;
			falling = true;
			int playerRow = Playstate.world.getRowTile((int) toy);
			y = ((playerRow + 1) * Game.BLOCKSIZE);
		}
	}

	private void calculateCorners(float x, float y) {
		World world = Playstate.world;
		int leftTile = world.getColTile((int) x + 2);
		int rightTile = world.getColTile((int) x + width - 2);
		int topTile = world.getRowTile((int) y);
		int midTile = world.getRowTile((int) y + height / 2);
		int botTile = world.getRowTile((int) y + height - 3);
		try {
			topLeft = !world.getBlocks()[topTile][leftTile].getMaterial().isWalkable();
			topRight = !world.getBlocks()[topTile][rightTile].getMaterial().isWalkable();
			midLeft = !world.getBlocks()[midTile][leftTile].getMaterial().isWalkable();
			midRight = !world.getBlocks()[midTile][leftTile].getMaterial().isWalkable();
			botLeft = !world.getBlocks()[botTile][leftTile].getMaterial().isWalkable();
			botRight = !world.getBlocks()[botTile][rightTile].getMaterial().isWalkable();
		} catch (Exception ex) {

		}
	}

	private void calculateMovement() {
		if (left)
			dx = -speed;
		if (right)
			dx = speed;
		if (falling && !jumping) {
			dy += GRAVITY;
			dyDif += GRAVITY;
			if (dy > MAX_FALLING_SPEED)
				dy = MAX_FALLING_SPEED;
		}
		if (jumping && !falling) {
			dy = JUMP_START;
			dyDif = 0;
			jumping = false;
			falling = true;
		}
	}

	private void move() {
		x += dx;
		y += dy;
		dx = 0;
	}

	protected float getDy() {
		return dy;
	}

	protected float getDx() {
		return dx;
	}

	protected float getDyDif() {
		return dyDif;
	}

	protected int getHealth() {
		return health;
	}

}
