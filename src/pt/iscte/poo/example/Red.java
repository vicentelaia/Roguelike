package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Red extends GameElement{

	public Red(Point2D p) {
		setPosition(p);
	}

	@Override
	public String getName() {
		return "Red";
	}

	@Override
	public int getLayer() {
		return 0;
	}

}
