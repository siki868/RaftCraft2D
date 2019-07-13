package game;

import model.GamePanel;
import rend.ImageLoader;
import rend.Sprite;
import rend.Spritesheet;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JFrame{

	public static Spritesheet sprite = new Spritesheet(ImageLoader.load("img/spritesheet.png"), 3, 80, 80);
	public static final int BLOCKSIZE = 32;
	private static JPanel cards;
	public Game() {
		super("RAFCraft2D");
		setLayout(new BorderLayout());
		setSize(800,600);
		initPanel();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void initPanel() {
		StartPanel startPanel = new StartPanel();
		GamePanel gamePanel = new GamePanel();
		cards = new JPanel(new CardLayout());
		cards.add(startPanel);
		cards.add(gamePanel);
		add(cards, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		new Sprite();
		new Game();
	}
	
	public static JPanel getCards() {
		return cards;
	}

}
