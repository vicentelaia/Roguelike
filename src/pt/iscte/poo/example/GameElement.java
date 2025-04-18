package pt.iscte.poo.example;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public abstract class GameElement implements ImageTile {

	private Point2D position;

	public void setPosition(Point2D p) {
		position = p;
	}

	public Point2D getPosition() {
		return position;
	}

	public static GameElement create(String name, String[] args) {
		switch (name) {
		case "Skeleton":
			Skeleton s = new Skeleton(args);
			Engine.getInstance().addElement("Enemy", s);
			Engine.getInstance().addElement("Obstacle", s);
			return s;
		case "Bat":
			Bat b = new Bat(args);
			Engine.getInstance().addElement("Enemy", b);
			Engine.getInstance().addElement("Obstacle", b);
			return b;
		case "Thug":
			Thug t = new Thug(args);
			Engine.getInstance().addElement("Enemy", t);
			Engine.getInstance().addElement("Obstacle", t);
			return t;
		case "Scorpio":
			Scorpio sc = new Scorpio(args);
			Engine.getInstance().addElement("Enemy", sc);
			Engine.getInstance().addElement("Obstacle", sc);
			return sc;
		case "Thief":
			Thief th = new Thief(args);
			Engine.getInstance().addElement("Enemy", th);
			Engine.getInstance().addElement("Obstacle", th);
			Engine.getInstance().addElement("Collectable", th);
			return th;
		case "Key":
			Key k = new Key(args);
			Engine.getInstance().addElement("Item", k);
			return k;
		case "Sword":
			Sword sw = new Sword(args);
			Engine.getInstance().addElement("Item", sw);
			return sw;
		case "HealingPotion":
			HealingPotion h = new HealingPotion(args);
			Engine.getInstance().addElement("Item", h);
			Engine.getInstance().addElement("Consumable", h);
			return h;
		case "Armor":
			Armor a = new Armor(args);
			Engine.getInstance().addElement("Item", a);
			return a;
		case "Door":
			Door d = new Door(args);
			Engine.getInstance().addElement("Door", d);
			Engine.getInstance().addElement("Obstacles", d);
			return d;
		case "Treasure":
			Treasure tr = new Treasure(args);
			Engine.getInstance().addElement("Item", tr);
			return tr;
		default:
			throw new IllegalArgumentException();
		}
	}
	
	public String print() {
		return getName() + "," + getPosition().getX() + "," + getPosition().getY();
	}
	
}
