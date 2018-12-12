package game;
import game.imageHandler;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.*;

public class BackGround {
	private static int sunX = 50, sunY = 50;
  
  private class GroundImage {
    BufferedImage image;
    int x;
  }
  
  public static int groundY;
  private BufferedImage ground, sun;
  private ArrayList<GroundImage> groundImages;
  
  public BackGround(int panelHeight) {
    groundY = (int)(panelHeight - 0.25 * panelHeight);
    
    try{
      ground = new imageHandler().getImage("../images/Ground.png");
      sun = new imageHandler().getImage("../images/Sun.png");
    } catch(Exception e) {e.printStackTrace();}
    
    groundImages = new ArrayList<GroundImage>();
    
    for(int i=0; i<3; i++) {
      GroundImage obj = new GroundImage();
      obj.image = ground;
      obj.x = 0;
      groundImages.add(obj);
    }
  }
  
  public void update() {
    Iterator<GroundImage> iterator = groundImages.iterator();
    GroundImage first = iterator.next();
    
    first.x -= 12;
    
    int previousX = first.x;
    while(iterator.hasNext()) {
      GroundImage next = iterator.next();
      next.x = previousX + ground.getWidth();
      previousX = next.x;
    }
    
    if(first.x < -ground.getWidth()) {
      groundImages.remove(first);
      first.x = previousX + ground.getWidth();
      groundImages.add(first);
    }
    
  }
  
  public void create(Graphics g) {
	  g.drawImage(sun, sunX, sunY, null);
		for(GroundImage img : groundImages) {
			g.drawImage(img.image, (int) img.x, groundY, null);
		}
	}
}