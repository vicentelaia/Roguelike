package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Sword extends GameElement implements Item{

	public Sword(String[] args) {
		setPosition(new Point2D(Integer.parseInt(args[0]),Integer.parseInt(args[1])));	
	}

	@Override
	public String getName() {
		return "Sword";
	}

	@Override
	public int getLayer() {
		return 0;
	}

	@Override
	public void pick() {
		Engine.getInstance().getHero().setDamage(Engine.getInstance().getHero().getDamage() * 2);	
	}

	@Override
	public void drop() {
		Engine.getInstance().getHero().setDamage(Engine.getInstance().getHero().getDamage() / 2);
	}

}
