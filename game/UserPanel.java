package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import game.BackGround;
import game.Dino;
import game.Cactus;

class UserPanel extends JPanel implements KeyListener, Runnable {

	private int xPos = 750, yPos = 50;
	public static int width, height;
	private Thread thread;
	int sleepTimer = 40;
	private boolean running = false;
	private int score;
	
	BackGround backGround;
	Dino dino;
	Cactus cactus;

	public UserPanel() {
		score = 0;
		width = UserInterface.width;
		height = UserInterface.height;

		backGround = new BackGround(height);
		dino = new Dino();
		cactus = new Cactus((int) (width));

		

		setSize(width, height);
		setVisible(true);
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawString("Score: ", xPos-50, yPos);
		g.drawString(Integer.toString(score), xPos, yPos);
		backGround.create(g);
		dino.create(g);
		cactus.create(g);
	}

	public void run() {
		running = true;

		while (running) {
			updateGame();
			repaint();
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateGame() {
		score += 1;
		backGround.update();
		cactus.update();

		if (cactus.hasCollided()) {
			dino.dead();
			repaint();
			running = false;
			System.out.println("collide");
		}
		// game complete condition
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_SPACE) {
			dino.Jump();
			repaint();
		} else {
			System.out.println("nothing");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			if (thread == null || !running) {	//start game
				thread = new Thread(this);
				thread.start();
				dino.startRunning();
			}
		}
	}
}