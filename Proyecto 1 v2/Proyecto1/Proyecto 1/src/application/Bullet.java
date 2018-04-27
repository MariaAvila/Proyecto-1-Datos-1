package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bullet extends GameObject {

	public Bullet() {
		super(new Rectangle(1, 10, Color.BLUE));
	}
	
	public void move() {
		if (isAlive()) {
			if (getView().getTranslateY()> 0) {
				getView().setTranslateY(getView().getTranslateY() - 10);}
			else {
				this.setAlive(false);
				getView().setTranslateY(-1);
				getView().setTranslateX(-1);
				return;
			}
		}
	}
		
}


