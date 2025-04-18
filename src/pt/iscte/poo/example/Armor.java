package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Armor extends GameElement implements Item{

	public Armor(String[] args) {
		setPosition(new Point2D(Integer.parseInt(args[0]),Integer.parseInt(args[1])));
	}

	@Override
	public String getName() {
		return "Armor";
	}

	@Override
	public int getLayer() {
		return 0;
	}

	@Override
	public void pick() {
		Engine.getInstance().getHero().setArmor();
	}

	@Override
	public void drop() {
		Engine.getInstance().getHero().setNoArmor();
	}

}
