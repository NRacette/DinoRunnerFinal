package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import game.imageHandler;

public class Cactus {
	private class Obstacle {
		BufferedImage image;
		private int posx;
		private int posy;

		Rectangle getCactus() {
			Rectangle cactus = new Rectangle();
			cactus.x = posx;
			cactus.y = posy;
			cactus.width = image.getWidth();
			cactus.height = image.getHeight();

			return cactus;
		}
	}

	private int firstX;
	private int space;
	private int speed;

	private ArrayList<BufferedImage> imageList;
	private ArrayList<Obstacle> cactusList;

	private Obstacle blockedAt;

	public Cactus(int firstPos) {
		cactusList = new ArrayList<Obstacle>();
		imageList = new ArrayList<BufferedImage>();

		firstX = firstPos;
		space = 220;
		speed = 14;

		imageList.add(new imageHandler().getImage("../images/Cactus-1.png"));
		imageList.add(new imageHandler().getImage("../images/Cactus-5.png"));
		imageList.add(new imageHandler().getImage("../images/Cactus-1.png"));
		imageList.add(new imageHandler().getImage("../images/Cactus-5.png"));
		imageList.add(new imageHandler().getImage("../images/Cactus-1.png"));
		int x = firstX;

		for (BufferedImage bi : imageList) {

			Obstacle cactus = new Obstacle();

			cactus.image = bi;
			cactus.posx = x;
			cactus.posy = BackGround.groundY - bi.getHeight() + 5;
			x += space;

			cactusList.add(cactus);
		}
	}

	public void update() {
		Iterator<Obstacle> iterator = cactusList.iterator();

		Obstacle firstCactus = iterator.next();
		firstCactus.posx -= speed;

		while (iterator.hasNext()) {
			Obstacle cact = iterator.next();
			cact.posx -= speed;
		}

		Obstacle lastCactus = cactusList.get(cactusList.size() - 1);

		if (firstCactus.posx < -firstCactus.image.getWidth()) {
			cactusList.remove(firstCactus);
			firstCactus.posx = cactusList.get(cactusList.size() - 1).posx + space;
			cactusList.add(firstCactus);
		}
	}

	public void create(Graphics g) {
		for (Obstacle cact : cactusList) {
			g.drawImage(cact.image, cact.posx, cact.posy, null);
		}
	}

	public boolean hasCollided() {
		for (Obstacle cactus : cactusList) {
			if (Dino.getDino().intersects(cactus.getCactus())) {
				blockedAt = cactus;
				return true;
			}
		}
		return false;
	}



}