package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Door extends GameElement implements Obstacle{
	
	private String nextRoom;
	private Point2D nextPos;
	private String id;

	public Door(String[] args) {
		setPosition(new Point2D(Integer.parseInt(args[0]),Integer.parseInt(args[1])));
		nextRoom = args[2];
		nextPos = new Point2D(Integer.parseInt(args[3]),Integer.parseInt(args[4]));
		id = args.length == 5 ? "-1" : args[5];
	}

	@Override
	public String getName() {
		return id.equals("-1") ? "DoorOpen" : "DoorClosed";
	}

	@Override
	public int getLayer() {
		return 0;
	}
	
	public String getId() {
		return id;
	}
	
	public String getNextRoom() {
		return nextRoom;
	}
	
	public Point2D getNextPos() {
		return nextPos;
	}
	
	public void setId(String s) {
		id = s;
	}
	
	public String print() {
		return id.equals("-1") ? "Door" + "," + getPosition().getX() + "," + getPosition().getY() + "," + nextRoom + "," + nextPos.getX() + "," + nextPos.getY()
		: "Door" + "," + getPosition().getX() + "," + getPosition().getY() + "," + nextRoom + "," + nextPos.getX() + "," + nextPos.getY() + "," + id;
	}

}
