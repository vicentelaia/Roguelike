package pt.iscte.poo.example;

import pt.iscte.poo.gui.ImageMatrixGUI;
import pt.iscte.poo.utils.Point2D;

public class Treasure extends GameElement implements Item{

	public Treasure(String[] args) {
		setPosition(new Point2D(Integer.parseInt(args[0]),Integer.parseInt(args[1])));
	}

	@Override
	public String getName() {
		return "Treasure";
	}

	@Override
	public int getLayer() {
		return 0;
	}

	@Override
	public void pick() {
		ImageMatrixGUI.getInstance().setMessage("You win!");
		System.exit(0);
	}

	@Override
	public void drop() {
		
	}

}
