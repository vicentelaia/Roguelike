package pt.iscte.poo.example;

import pt.iscte.poo.gui.ImageMatrixGUI;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class Inventory {
	
	private Item[] items;
	private GameElement[] imgs;
	private int curr;
	
	public Inventory() {
		items = new Item[Hero.HERO_SLOTS];
		imgs = new GameElement[5];
		for (int i = 0;i!=imgs.length;i++) {
			imgs[i] = new Black(new Point2D(5+i,10));
			ImageMatrixGUI.getInstance().addImage(imgs[i]);
		}
		curr = 0;
	}
	
	public Item getItem(int i) {
		return items[i];
	}
	
	public boolean isEmpty() {
		for (int i = 0; i!=items.length;i++)
			if (items[i]!=null)
				return false;
		return true;
	}
	
	private int calculateCurr() {
		for (int i = 0;i!=items.length;i++) {
			if (items[i]==null)
				return i;
		}
		return -1;
	}
	
	//funcao que tem 1/3 prob caso size = 3 ,, 1/2 prob caso size = 2 ,, 1/1 caso size = 1
	
	public boolean isFull() {
		return calculateCurr() == -1;
	}
	
	public int size() {
		int n = 0;
		for (int i = 0; i!= items.length; i++)
			if (items[i]!=null)
				n++;
		return n;
	}
	
	public void addItem(Item i) {
		items[curr] = i;
		i.setPosition(imgs[curr].getPosition());
		curr = calculateCurr();
	}
	
	public void dropItem(int i) {
		items[i].setPosition(Engine.getInstance().getHero().getPosition());
		items[i] = null;
		curr = calculateCurr();
	}
	
	public void removeItem(int i) {
		items[i] = null;
		curr = calculateCurr();
	}
	
	public void removeItem(Item it) {
		int i;
		for (i = 0; i!=items.length;i++)
			if (items[i] != null)
				if (items[i].equals(it))
					break;
		items[i] = null;
		curr = calculateCurr();
	}
	
	public Key getKey(String id) {
		for (int i = 0; i!=items.length;i++) {
			if (items[i] == null)
				continue;
			String[] args = items[i].print().split(",");
			if (args.length == 4)
				if (args[0].equals("Key") && args[3].equals(id))
					return (Key) items[i];
		}
		return null;
	}
	
	public void update() {
		for (int i = 0; i!=imgs.length;i++)
			ImageMatrixGUI.getInstance().addImage(new Black(new Point2D(5+i,10)));
		
		for (int i = 0; i!=items.length;i++)
			if (items[i] != null)
				ImageMatrixGUI.getInstance().addImage((ImageTile) items[i]);
	}
}
