package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public interface Item {

	void pick();
	
	void drop();
	
	Point2D getPosition();
	
	void setPosition(Point2D p);
	
	String print();
}
