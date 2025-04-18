package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Key extends GameElement implements Item{
	
	private String id;

	public Key(String[] args) {
		setPosition(new Point2D(Integer.parseInt(args[0]),Integer.parseInt(args[1])));
		id = args[2];
	}

	@Override
	public String getName() {
		return "Key";
	}

	@Override
	public int getLayer() {
		return 0;
	}

	@Override
	public void pick() {
		Engine.getInstance().getHero().setKey(id);
	}

	@Override
	public void drop() {
		Engine.getInstance().getHero().removeKey(id);
	}
	
	public String print() {
		return super.print() + "," + id;
	}

}
