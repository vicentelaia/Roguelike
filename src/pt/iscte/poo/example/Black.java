package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Black extends GameElement{

	public Black(Point2D p) {
		setPosition(p);
	}

	@Override
	public String getName() {
		return "Black";
	}

	@Override
	public int getLayer() {
		return 0;
	}

}
