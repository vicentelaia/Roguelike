package pt.iscte.poo.example;

import pt.iscte.poo.gui.ImageMatrixGUI;
import pt.iscte.poo.utils.Point2D;

public class HealthBar {
	
	private GameElement[] imgs;
	
	public HealthBar() {
		imgs = new GameElement[5];
		for (int i = 0;i!=imgs.length;i++) {
			imgs[i] = new Green(new Point2D(i,10));
			ImageMatrixGUI.getInstance().addImage(imgs[i]);
		}
	}
	
	public void update() {
		double redBlocks = (Hero.HERO_HEALTH - Engine.getInstance().getHero().getHealth()) / 2.0;
		double greenBlocks = 5 - redBlocks;
		
		for (int i = 0; i!=(int) greenBlocks;i++)
			imgs[i] = new Green(imgs[i].getPosition());
		
		for (int i = 0; i!=(int) redBlocks;i++)
			imgs[imgs.length-1-i] = new Red(imgs[imgs.length-1-i].getPosition());
		
		if (greenBlocks != (int) greenBlocks) 
			imgs[imgs.length-1 - (int) redBlocks] = new GreenRed(imgs[imgs.length-1 -(int) redBlocks].getPosition());
		
		for (int j = 0; j != imgs.length; j++)
			ImageMatrixGUI.getInstance().addImage(imgs[j]);
	}
	
}
