package ca.ubc.cpsc210.spaceinvaders.ui;

import ca.ubc.cpsc210.spaceinvaders.model.SIGame;
import javafx.stage.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*
 * Represents the main window in which the space invaders
 * game is played
 */
@SuppressWarnings("serial")
public class SpaceInvaders extends JFrame {

	private static final int INTERVAL = 20;
	private static final int INTERVAL2 = 2000;
	private SIGame game;
	private GamePanel gp;
	private ScorePanel sp;

	private Image bg;
	private Screen s;
	private boolean loaded;

	// EFFECTS: sets up window in which Space Invaders game will be played
	public SpaceInvaders() {
		super("Space Invaders");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(false);

		loaded = false;

		bg = new ImageIcon("C:\\Users\\royzh\\Google Drive\\Education\\BCS\\CPSC 210\\Lecture\\SpaceInvadersComplete\\src\\ca\\ubc\\cpsc210\\spaceinvaders\\Resources\\bg.jpg").getImage();


		game = new SIGame();
		gp = new GamePanel(game);
		sp = new ScorePanel(game);
		add(gp);
		add(sp, BorderLayout.NORTH);
		addKeyListener(new KeyHandler());
		pack();
		centreOnScreen();
		setVisible(true);
		addTimer();
	}



	// MODIFIES: none
	// EFFECTS:  initializes a timer that updates game each
	//           INTERVAL milliseconds
	private void addTimer() {
		Timer t = new Timer(INTERVAL, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				game.update();
				gp.repaint(); 
				sp.update();
			}
		});
		
		t.start();
	}

	// MODIFIES: this
	// EFFECTS:  location of frame is set so frame is centred on desktop
	private void centreOnScreen() {
		Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
	}
	
	/*
	 * A key handler to respond to key events
	 */
	private class KeyHandler extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			game.keyPressed(e.getKeyCode());
		}
	}

	// Play the game
	public static void main(String[] args) {
		new SpaceInvaders();
	}
}
