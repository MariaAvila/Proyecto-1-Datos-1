package application.EnemyBehavior;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Minion extends Enemy{

	public Minion(int resistance) {
		super(new Rectangle(25, 25, Color.GREEN));
		this.setResistance(resistance);
	}

}
