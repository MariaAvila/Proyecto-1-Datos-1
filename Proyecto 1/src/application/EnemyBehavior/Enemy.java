package application.EnemyBehavior;

import application.GameObject;
import javafx.scene.Node;

public class Enemy extends GameObject {
	
	private int resistance;
	private Enemy next = null;
	private Enemy previous = null;
	

	public Enemy getPrevious() {
		return previous;
	}


	public void setPrevious(Enemy previous) {
		this.previous = previous;
	}


	public int getResistance() {
		return resistance;
	}


	public void setResistance(int resistance) {
		this.resistance = resistance;
	}


	public Enemy getNext() {
		return next;
	}


	public void setNext(Enemy next) {
		this.next = next;
	}


	public Enemy(Node view) {
		super(view);
	}

}
