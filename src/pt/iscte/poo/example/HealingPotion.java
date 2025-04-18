package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class HealingPotion extends GameElement implements Item,Consumable{

	public HealingPotion(String[] args) {
		setPosition(new Point2D(Integer.parseInt(args[0]),Integer.parseInt(args[1])));
	}

	@Override
	public String getName() {
		return "HealingPotion";
	}

	@Override
	public int getLayer() {
		return 0;
	}

	@Override
	public void pick() {
		return;
	}

	@Override
	public void drop() {
		return;
	}

	@Override
	public void use() {
		Engine.getInstance().getHero().setHealth(Engine.getInstance().getHero().getHealth() + 5 > Hero.HERO_HEALTH ? Hero.HERO_HEALTH 
				: Engine.getInstance().getHero().getHealth() + 5);
		Engine.getInstance().getHero().setPoison(0);
	}

}
