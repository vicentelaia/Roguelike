package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class GreenRed extends GameElement{

	public GreenRed(Point2D p) {
		setPosition(p);
	}

	@Override
	public String getName() {
		return "GreenRed";
	}

	@Override
	public int getLayer() {
		return 0;
	}

}
