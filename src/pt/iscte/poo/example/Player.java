package pt.iscte.poo.example;

public class Player {
	
	private String name;
	private int score;

	public Player(String askUser) {
		name = askUser;
		score = 0;
	}
	
	public Player(String[] args) {
		name = args[0];
		score = Integer.parseInt(args[1]);
	}

	public String getName() {
		return name;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public String toString() {
		return name + "," + score;	
	}

}
