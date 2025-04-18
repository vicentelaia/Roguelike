package pt.iscte.poo.example;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Bat extends HealthObject implements Enemy,Obstacle{
	
	private static final int BAT_HEALTH = 3;
	private static final int BAT_DAMAGE = 1;

	public Bat(String[] args) {
		setPosition(new Point2D(Integer.parseInt(args[0]),Integer.parseInt(args[1])));
		setHealth(BAT_HEALTH);
		setDamage(BAT_DAMAGE);
	}

	@Override
	public String getName() {
		return "Bat";
	}

	@Override
	public int getLayer() {
		return 0;
	}

	@Override
	public Point2D nextPos() {
		double rand = Math.random();
		if (rand > 0.49)
			return getPosition().plus(Vector2D.movementVector(getPosition(),Engine.getInstance().getHero().getPosition()));
		else
			return getPosition().plus(Direction.random().asVector());
	}

	@Override
	public void attack() {
		if (Engine.getInstance().getTurns() % 2 == 0) {
			Engine.getInstance().getHero().setHealth(Engine.getInstance().getHero().getHealth()-getDamage());
			setHealth(getHealth() + 1 > BAT_HEALTH ? BAT_HEALTH : getHealth() + 1);
		}
	}

}
