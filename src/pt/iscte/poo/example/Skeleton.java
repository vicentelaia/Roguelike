package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Skeleton extends HealthObject implements Enemy,Obstacle{
	
	private static final int SKELETON_HEALTH = 5;
	private static final int SKELETON_DAMAGE = 1;

	public Skeleton(String[] args) {
		setPosition(new Point2D(Integer.parseInt(args[0]),Integer.parseInt(args[1])));
		setHealth(SKELETON_HEALTH);
		setDamage(SKELETON_DAMAGE);
	}

	@Override
	public String getName() {
		return "Skeleton";
	}

	@Override
	public int getLayer() {
		return 0;
	}

	@Override
	public Point2D nextPos() {
		if (Engine.getInstance().getTurns() % 2 != 0) 
			return getPosition().plus(Vector2D.movementVector(getPosition(),Engine.getInstance().getHero().getPosition()));
		else
			return getPosition();
	}

	@Override
	public void attack() {
		Engine.getInstance().getHero().setHealth(Engine.getInstance().getHero().getHealth()-getDamage());
	}

}
