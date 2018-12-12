package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import game.BackGround;
import game.imageHandler;

public class Dino {
	private static int botY, topY, xPos;
	private static int jumpY, maxY;

	private static boolean maxYReached;
	private static int jumpFactor = 20;

	public static final int alive = 1, jump = 3, dead = 4;

	private static int state;

	static BufferedImage aliveDino;
	BufferedImage deadDino;

	public Dino() {
		aliveDino = new imageHandler().getImage("../images/Dino-stand.png");
		deadDino = new imageHandler().getImage("../images/Dino-big-eyes.png");

		botY = BackGround.groundY;
		topY = BackGround.groundY - aliveDino.getHeight();
		xPos = 100;
		maxY = topY - 120;

		state = 1;
	}

	public void create(Graphics g) {

		switch (state) {
		case alive:
			g.drawImage(aliveDino, xPos, botY - aliveDino.getHeight(), null);
			break;
		case dead:
			g.drawImage(deadDino, xPos, botY - deadDino.getHeight(), null);
			break;
		case jump:
			if (jumpY > maxY && !maxYReached) {
				g.drawImage(aliveDino, xPos, jumpY -= jumpFactor, null);
				break;
			}
			if (jumpY >= maxY && !maxYReached) {
				maxYReached = true;
				g.drawImage(aliveDino, xPos, jumpY += jumpFactor, null);
				break;
			}
			if (jumpY > maxY && maxYReached) {
				if (topY == jumpY && maxYReached) {
					state = alive;
					maxYReached = false;
					break;
				}
				g.drawImage(aliveDino, xPos, jumpY += jumpFactor, null);
				break;
			}
		}

	}

	public void update() {

	}

	public void Jump() {
		jumpY = topY;
		maxYReached = false;
		state = jump;
	}

	public void dead() {
		state = dead;
	}

	public static Rectangle getDino() {
		Rectangle dino = new Rectangle();
		dino.x = xPos;

		if (state == jump && !maxYReached)
			dino.y = jumpY - jumpFactor;
		else if (state == jump && maxYReached)
			dino.y = jumpY + jumpFactor;
		else if (state != jump)
			dino.y = jumpY;

		dino.width = aliveDino.getWidth();
		dino.height = aliveDino.getHeight();

		return dino;
	}

	public void startRunning() {
		jumpY = topY;
	}

	public void jump() {
		jumpY = topY;
		maxYReached = false;
		state = jump;
	}

}