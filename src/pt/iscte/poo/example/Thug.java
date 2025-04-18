package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Thug extends HealthObject implements Enemy,Obstacle{
	
	private static final int THUG_HEALTH = 10;
	private static final int THUG_DAMAGE = 3;

	public Thug(String[] args) {
		setPosition(new Point2D(Integer.parseInt(args[0]),Integer.parseInt(args[1])));
		setHealth(THUG_HEALTH);
		setDamage(THUG_DAMAGE);
	}

	@Override
	public String getName() {
		return "Thug";
	}

	@Override
	public int getLayer() {
		return 0;
	}

	@Override
	public Point2D nextPos() {
		return getPosition().plus(Vector2D.movementVector(getPosition(),Engine.getInstance().getHero().getPosition()));
	}

	@Override
	public void attack() {
		if (Engine.getInstance().getTurns() % 3 == 0)
			Engine.getInstance().getHero().setHealth(Engine.getInstance().getHero().getHealth()-getDamage());
	}

}
