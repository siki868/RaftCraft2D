package model;

import rend.ImageLoader;
import rend.Spritesheet;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player extends Entity {

	private ArrayList<Bullet> blts = new ArrayList<>();
	private boolean strana = false; // left true, right false

	public Player(float x, float y, int width, int height, float speed) {
		super(new Spritesheet(ImageLoader.load("img/player.png"), 4, 38, 48), x, y, width, height, speed);
	}

	public void render(Graphics2D g) {
		g.drawImage(animation.getImage(), GamePanel.width / 2 / GamePanel.SCALE - width / 2,
				GamePanel.height / 2 / GamePanel.SCALE - height / 2, width, height, null);
	}

	public void keyPressed(KeyEvent e, int k) {
		if (k == KeyEvent.VK_A) {
			left = true;
			right = false;
			strana = true;
		} else if (k == KeyEvent.VK_D) {
			right = true;
			left = false;
			strana = false;
		}
		if (k == KeyEvent.VK_SPACE) {
			if (!falling && !jumping) {
				jumping = true;
			}
		}

		if (k == KeyEvent.VK_K) {
			if (blts.size() <= 8) {
				Bullet blt = new Bullet(x, y + height / 2, 48, 48, strana, this);
				blts.add(blt);
			}
		}
		
		if(k == KeyEvent.VK_UP) {
			dy -=1;
			dyDif = 0;
		}
		
		if(k == KeyEvent.VK_P) health = 0;
	}

	public void keyReleased(KeyEvent e, int k) {
		if (k == KeyEvent.VK_A)
			left = false;
		if (k == KeyEvent.VK_D)
			right = false;
	}

	public ArrayList<Bullet> getBlts() {
		return blts;
	}
}
