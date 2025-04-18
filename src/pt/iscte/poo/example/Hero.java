package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;


public class Hero extends HealthObject {

	public static final int HERO_HEALTH = 10;
	public static final int HERO_DAMAGE = 1;
	public static final int HERO_SLOTS = 3;
	private Inventory inventory;
	private HealthBar healthBar;
	private String[] key_ids;
	private boolean hasArmor;
	private int poisoned;

	public Hero(Point2D p) {
		setPosition(p);
		setHealth(HERO_HEALTH);
		setDamage(HERO_DAMAGE);
		healthBar = new HealthBar();
		inventory = new Inventory();
		key_ids = new String[HERO_SLOTS];
		for (int i = 0; i != HERO_SLOTS; i++)
			key_ids[i] = "-1";
		hasArmor = false;
		poisoned = 0;
	}

	@Override
	public String getName() {
		return "Hero";
	}

	@Override
	public int getLayer() {
		return 0;
	}
	
	public Inventory getInventory() {
		return inventory;
	}

	public void update() {
		poison();
		healthBar.update();
		inventory.update();
	}
	
	public void attack(Enemy e) {
		e.setHealth(e.getHealth()-getDamage());
	}
	
	public void setKey(String id) {
		for (int i = 0; i != key_ids.length; i++)
			if (key_ids[i].equals("-1")) {
				key_ids[i] = id;
				return;
			}
				
	}
	
	public void removeKey(String id) {
		for (int i = 0; i != key_ids.length; i++)
			if (key_ids[i].equals(id))
				key_ids[i] = "-1";
		
	}
	
	public boolean hasKey(String id) {
		for (int i = 0; i != key_ids.length; i++)
			if (key_ids[i].equals(id))
				return true;
		return false;
	}
	
	public void setArmor() {
		hasArmor = true;
	}
	
	public void setNoArmor() {
		hasArmor = false;
	}
	
	private int random() {
		double r = Math.random();
		return r > 0.49 ? 0 : 1;
	}
	
	public int armor() {
		return hasArmor ? random() : 1;
	}
	
	public void setPoison(int dmg) {
		poisoned = dmg;
	}
	
	public void dropKey(String id) {
		inventory.getKey(id).drop();
		inventory.removeItem(inventory.getKey(id));
	}
	
	private void poison() {
		setHealth(getHealth() - poisoned);
	}
	
}
