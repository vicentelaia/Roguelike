package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Wall extends GameElement implements Obstacle{

	public Wall(Point2D p) {
		setPosition(p);
	}

	@Override
	public String getName() {
		return "Wall";
	}

	@Override
	public int getLayer() {
		return 0;
	}

}
