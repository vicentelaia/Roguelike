package pt.iscte.poo.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pt.iscte.poo.gui.ImageMatrixGUI;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.observer.Observed;
import pt.iscte.poo.observer.Observer;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Engine implements Observer {

	public static final int GRID_HEIGHT = 11;
	public static final int GRID_WIDTH = 10;
	public static final int SCORE_PER_KILL = 10;

	private static Engine INSTANCE = null;
	private ImageMatrixGUI gui = ImageMatrixGUI.getInstance();

	private Hero hero;
	private int turns;

	private List<Enemy> enemies = new ArrayList<>();
	private List<Item> items = new ArrayList<>();
	private List<Obstacle> obstacles = new ArrayList<>();
	private List<Door> doors = new ArrayList<>();
	private List<Consumable> consumables = new ArrayList<>();
	private List<Collectable> collectables = new ArrayList<>();

	private boolean askUser = true;
	private String room;
	private Player player;

	public static Engine getInstance() {
		if (INSTANCE == null)
			INSTANCE = new Engine();
		return INSTANCE;
	}

	private Engine() {
		gui.registerObserver(this);
		gui.setSize(GRID_WIDTH, GRID_HEIGHT);
		gui.go();
	}

	//Getters
	public int getTurns() {
		return turns;
	}

	public Hero getHero() {
		return hero;
	}
	
	public void addElement(String list, GameElement g) {
		switch(list) {
		case "Enemy": enemies.add((Enemy) g);
		break;
		case "Item": items.add((Item) g);
		break;
		case "Door": doors.add((Door) g);
		break;
		case "Obstacle": obstacles.add((Obstacle) g);
		break;
		case "Consumable": consumables.add((Consumable) g);
		break;
		case "Collectable": collectables.add((Collectable) g);
		break;
		}
	}
	
	public boolean hasObstacle(Point2D p) {
		return Utils.check(obstacles,o -> o.getName().equals("Wall") && o.getPosition().equals(p));
	}
	
	public void printLists(PrintWriter w) {
		Engine.getInstance().enemies.forEach(e -> w.println(e.print()));
		Engine.getInstance().items.forEach(i -> w.println(i.print()));
		Engine.getInstance().doors.forEach(d -> w.println(d.print()));
	}
	
	//
	
	public void start(String room) {
		this.room = room;
		MapReader.read(room);
		addHero();
		openDoor();
		gui.update();
		if (askUser && turns == 0) player = new Player(gui.askUser("Insert username"));
		gui.setStatusMessage("Moves: " + turns + " Score: " + player.getScore());
	}

	private void addHero() {
		hero = hero == null ? new Hero(new Point2D(1, 1)) : hero;
		gui.addImage(hero);
	}
	
	private void reset() {
		enemies.clear();
		obstacles.clear();
		items.clear();
		doors.clear();
		collectables.clear();
	}

	private void heroMove(Point2D p) {
		if (!Utils.check(obstacles, o -> p.equals(o.getPosition())) && gui.isWithinBounds(p) && p.getY() < 10)
			hero.setPosition(p);
	}

	private void heroAttack(Point2D p) {
		enemies.forEach(e -> {
			if (p.equals(e.getPosition()))
				hero.attack(e);
		});
	}

	private void heroItems(Point2D p) {
		Item it = Utils.find(items, i -> hero.getPosition().equals(i.getPosition()));
		if (it != null && !hero.getInventory().isFull()) {
			it.pick();
			items.remove(it);
			hero.getInventory().addItem(it);
		}
	}

	private void heroDoors(Point2D p) {
		Door door = Utils.find(doors, d -> p.equals(d.getPosition()));
		if (door != null) {
			if (hero.hasKey(door.getId()) || door.getId().equals("-1")) {
				nextRoom(door);
			}
		}
	}

	private void useKey(Door door) {
		if (!door.getId().equals("-1")) {
			hero.dropKey(door.getId());
			door.setId("-1");
		}
	}

	private void nextRoom(Door door) {
		obstacles.remove(door);
		useKey(door);
		MapReader.write(room);
		reset();
		hero.setPosition(door.getNextPos());
		start(door.getNextRoom());
	}

	private void openDoor() {
		Door door = Utils.find(doors, d -> hero.getPosition().equals(d.getPosition()));
		if (door != null)
			door.setId("-1");
	}

	private void useConsumable(int i) {
		Consumable c = (Consumable) hero.getInventory().getItem(i);
		c.use();
		consumables.remove(c);
		hero.getInventory().removeItem(i);
	}
	
	private void dropItem(int i) {
		hero.getInventory().getItem(i).drop();
		items.add(hero.getInventory().getItem(i));
		hero.getInventory().dropItem(i);
	}

	private void remove() {
		List<Enemy> toRemove = (List<Enemy>) Utils.select(enemies, e -> e.getHealth() <= 0);
		toRemove.forEach(e -> {
			enemies.remove(e);
			obstacles.remove((Obstacle) e);
			gui.removeImage((ImageTile) e);
			player.setScore(player.getScore() + SCORE_PER_KILL);
		});
	}

	private void move() {
		enemies.forEach(e -> {
			Point2D temp = e.nextPos();
			if (!Utils.check(obstacles, o -> temp.equals(o.getPosition())) && !temp.equals(hero.getPosition()))
				e.setPosition(temp);
			else if (temp.equals(hero.getPosition()) && hero.armor() == 1)
				e.attack();
		});
	}

	private void gameOver() {
		if (hero.getHealth() <= 0) {
			askUser = false;
			MapReader.delete(false);
			gui.setMessage("You Lost!");
			reset();
			gui.clearImages();
			hero = null;
			writeStats();
			turns = 0;
			player.setScore(0);
			start("room0");
		}
	}

	private void collectable() {
		List<Collectable> c = (List<Collectable>) Utils.select(collectables, cl -> cl.getHealth() <= 0);
		c.forEach(co -> {
			co.dropItems();
			collectables.remove(co);
		});
	}

	private void writeStats() {
		ArrayList<Player> lines = new ArrayList<>();
		try {
			File f = new File("stats.txt");
			if (f.exists()) {
				Scanner sc = new Scanner(f);
				while (sc.hasNextLine()) {
					String line = sc.nextLine();
					lines.add(new Player(line.split(",")));
				}
				sc.close();
				lines.sort((p1, p2) -> p2.getScore() - p1.getScore());
			}
			PrintWriter w = new PrintWriter(f);
			lines.add(player);
			lines.sort((p1, p2) -> p2.getScore() - p1.getScore());
			if (lines.size() > 5)
				lines.remove(lines.size() - 1);
			lines.forEach(pl -> w.println(pl.toString()));
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void heroMovement(int k) {
		Point2D heroNextPos = hero.getPosition().plus(Direction.directionFor(k).asVector());
		heroMove(heroNextPos);
		heroAttack(heroNextPos);
		heroItems(heroNextPos);
		heroDoors(heroNextPos);
	}
	
	private void heroDropItem(int i) {
		if (hero.getInventory().getItem(i) != null) {
			if (consumables.contains(hero.getInventory().getItem(i))) 
				useConsumable(i);
			else 
				dropItem(i);
		}
	}
	
	private void turn() {
		turns++;
		move();
		remove();
		collectable();
		gameOver();
		hero.update();
		gui.setStatusMessage("Moves: " + turns + " Score: " + player.getScore());
		gui.update();
	}

	@Override
	public void update(Observed source) {
		MapReader.delete(true);
		int key = ((ImageMatrixGUI) source).keyPressed();
		switch (key) {
		case KeyEvent.VK_UP: case KeyEvent.VK_DOWN: case KeyEvent.VK_LEFT: case KeyEvent.VK_RIGHT:
			heroMovement(key);
			break;
		case KeyEvent.VK_1: case KeyEvent.VK_2: case KeyEvent.VK_3:
			heroDropItem(key - 49);
			break;
		default:
			System.out.println("Illegal Key");
			return;
		}
		turn();
	}
}
