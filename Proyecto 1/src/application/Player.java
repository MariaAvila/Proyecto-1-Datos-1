package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player extends GameObject {

	public Player() {
		super(new Rectangle(20, 20, Color.BLUE));
	}
	
	void moveX(int value) {
		if (value< 0) {
			if(getView().getTranslateX()== 0) {
				return;
			}
		}
		else {
			if(getView().getTranslateX()+20== 600) {
				return;
			}
		}
		getView().setTranslateX(getView().getTranslateX() + value);
	}

}
