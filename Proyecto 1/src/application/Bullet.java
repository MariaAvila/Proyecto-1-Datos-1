package application;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bullet extends GameObject {
	
	private Point2D velocity = new Point2D(0, 5);
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
			if (getView().getTranslateY()> -10) {
				getView().setTranslateY(getView().getTranslateY() - velocity.getY());}
			else {
				return;
			}
		}
	}
		
}


