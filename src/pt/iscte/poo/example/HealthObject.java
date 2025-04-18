package pt.iscte.poo.example;

public abstract class HealthObject extends GameElement{

	private int health;
	private int damage;
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int h) {
		health = h;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void setDamage(int d) {
		damage = d;
	}
}
