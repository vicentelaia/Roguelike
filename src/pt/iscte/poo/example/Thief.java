package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Thief extends HealthObject implements Enemy,Obstacle,Collectable{
	
	private static final int THIEF_HEALTH = 5;
	private static final int THIEF_DAMAGE = 1;
	
	private boolean hasItem;
	private Item[] slots;

	public Thief(String[] args) {
		setPosition(new Point2D(Integer.parseInt(args[0]),Integer.parseInt(args[1])));
		setHealth(THIEF_HEALTH);
		setDamage(THIEF_DAMAGE);
		hasItem = false;
		slots = new Item[Hero.HERO_SLOTS];
	}

	@Override
	public String getName() {
		return "Thief";
	}

	@Override
	public int getLayer() {
		return 0;
	}

	@Override
	public void attack() {
		if (!Engine.getInstance().getHero().getInventory().isEmpty()) {
			int r = (int)(Math.random() * Hero.HERO_SLOTS);
			if (Engine.getInstance().getHero().getInventory().getItem(r) != null) {
				slots[0] = Engine.getInstance().getHero().getInventory().getItem(r);
				Engine.getInstance().getHero().getInventory().getItem(r).drop();
				Engine.getInstance().getHero().getInventory().removeItem(r);
				hasItem = true;
			}
		}
	}

	@Override
	public Point2D nextPos() {
		return hasItem ? getPosition().plus(Vector2D.movementVector(Engine.getInstance().getHero().getPosition(),getPosition())) : getPosition().plus(Vector2D.movementVector(getPosition(),Engine.getInstance().getHero().getPosition()));
	}

	@Override
	public void dropItems() {
		for (int i = 0; i != slots.length; i++) {
			if (slots[i] != null) {
				Engine.getInstance().addElement("Item",(GameElement)(slots[i]));
				slots[i].setPosition(getPosition());
			}
		}
	}

}
