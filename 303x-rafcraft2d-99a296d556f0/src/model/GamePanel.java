package model;

import game.GameStateManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.VolatileImage;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements ComponentListener, ActionListener, KeyListener, MouseListener {

	public static int width;
	public static int height;
	public static final int SCALE = 2;

	private Timer timer;
	private GameStateManager gsm;
	private VolatileImage image;

	public GamePanel() {
		super();
		addMouseListener(this);
		addKeyListener(this);
		addComponentListener(this);
		setFocusable(true);
		requestFocus();
		width = getPreferredSize().width;
		height = getPreferredSize().height;
		gsm = new GameStateManager(GameStateManager.PLAYSTATE);
	}

	public void update() {
		gsm.update();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Player player = Playstate.player;
		Graphics2D g2d = image.createGraphics();
		g2d.setBackground(new Color(25, 25, 112));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		if (player.getHealth() != 0) {
			gsm.render(g2d);
			g.drawImage(image, 0, 0, width, height, null);
		} else {
			timer.stop();
			gsm.render(g2d);
			g.drawImage(image, 0, 0, width, height, null);
			g.setColor(new Color(0, 0, 0, 125));
			g.fillRect(0, 0, width, height);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
			g.setColor(Color.RED);
			g.drawString("GAME OVER", GamePanel.width / 2 - (int)(100 * 3.4), GamePanel.height / 2);
		}
	}

	@Override
	public void addNotify() {
		super.addNotify();
		image = createVolatileImage(width / SCALE, height / SCALE);
		timer = new Timer(1000 / 60, this);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		gsm.mousePressed(arg0);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		gsm.mouseReleased(arg0);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		gsm.keyPressed(arg0, arg0.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		gsm.keyReleased(arg0, arg0.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		gsm.keyTyped(arg0, arg0.getKeyCode());
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		if (arg0.getSource().equals(this)) {
			width = getWidth();
			height = getHeight();
			image = createVolatileImage(width / SCALE, height / SCALE);
		}
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		this.requestFocusInWindow();
	}

}
