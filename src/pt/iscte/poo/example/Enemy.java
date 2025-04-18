package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public interface Enemy {
	
	void attack();
	
	int getHealth();
	
	void setHealth(int h);
	
	Point2D nextPos();
	
	Point2D getPosition();
	
	void setPosition(Point2D p);
	
	int getDamage();
	
	void setDamage(int d);
	
	String print();
	
}
