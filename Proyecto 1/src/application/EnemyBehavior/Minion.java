package application.EnemyBehavior;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Minion extends Enemy{

	public Minion(int resistance) {
		super(new Rectangle(20, 20, Color.GREEN));
		this.setResistance(resistance);
	}

}
