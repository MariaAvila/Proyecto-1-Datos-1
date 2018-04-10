package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bullet extends GameObject {

	private Bullet next = null;
	
	public Bullet getNext() {
		return next;
	}

	public void setNext(Bullet next) {
		this.next = next;
	}

	public Bullet() {
		super(new Rectangle(1, 10, Color.BLUE));
	}
	
	public void move() {
		if (isAlive()) {
			if (getView().getTranslateY()> 0) {
				getView().setTranslateY(getView().getTranslateY() - 5);}
			else {
				this.setAlive(false);
				return;
			}
		}
	}
		
}


