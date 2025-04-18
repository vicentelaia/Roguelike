package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Scorpio extends HealthObject implements Enemy,Obstacle{
	
	private static final int SCORPIO_HEALTH = 2;
	private static final int SCORPIO_DAMAGE = 1;

	public Scorpio(String[] args) {
		setPosition(new Point2D(Integer.parseInt(args[0]),Integer.parseInt(args[1])));
		setHealth(SCORPIO_HEALTH );
		setDamage(SCORPIO_DAMAGE);
	}

	@Override
	public String getName() {
		return "Scorpio";
	}

	@Override
	public int getLayer() {
		return 0;
	}

	@Override
	public void attack() {
		Engine.getInstance().getHero().setPoison(SCORPIO_DAMAGE);
	}

	@Override
	public Point2D nextPos() {
		return getPosition().plus(Vector2D.movementVector(getPosition(),Engine.getInstance().getHero().getPosition()));
	}

}
