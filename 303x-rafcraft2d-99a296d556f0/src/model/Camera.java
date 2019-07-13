package model;

public class Camera {

	private Player player;

	public Camera(Player player) {
		this.player = player;
	}

	public int getCamX() {
		return (int) (player.getX() + player.getWidth() / 2 - GamePanel.width / 2 / GamePanel.SCALE);
	}

	public int getCamY() {
		return (int) (player.getY() + player.getHeight() / 2 - GamePanel.height / 2 / GamePanel.SCALE);
	}

}
