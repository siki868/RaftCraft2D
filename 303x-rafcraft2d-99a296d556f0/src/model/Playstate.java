package model;

import game.GameStateManager;
import game.State;
import rend.ImageLoader;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Playstate extends State {

	public static World world;
	public static Player player;
	public static Camera camera;
	public static BufferedImage background = ImageLoader.load("img/backdrop.png");;

	public Playstate(GameStateManager gsm) {
		super(gsm);
		BufferedImage map = ImageLoader.load("worlds/map.png");
		world = new World(map, 10);
		player = new Player(100, 700, 32, 48, 3F);
		camera = new Camera(player);
	}

	@Override
	public void update() {
		player.update();
		for (Bullet blt : player.getBlts()) {
			if (blt.getX() > GamePanel.width / GamePanel.SCALE || blt.getX() < -blt.getWidth()) {
				player.getBlts().remove(blt);
				blt = null;
				break;
			}
			blt.update();
		}

	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(background, 0, 0, GamePanel.width / GamePanel.SCALE, GamePanel.height / GamePanel.SCALE, null);
		// g.clearRect(0, 0, GamePanel.width, GamePanel.height);
		world.render(g);
		player.render(g);
		for (Bullet blt : player.getBlts()) {
			blt.render(g);
		}

	}

	@Override
	public void keyPressed(KeyEvent e, int k) {
		player.keyPressed(e, k);
	}

	@Override
	public void keyReleased(KeyEvent e, int k) {
		player.keyReleased(e, k);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e, int k) {
		// TODO Auto-generated method stub

	}

}
