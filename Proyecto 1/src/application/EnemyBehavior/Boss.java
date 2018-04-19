package application.EnemyBehavior;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Boss extends Enemy{

	public Boss(int resistance) {
		super(new Rectangle(30, 30, Color.RED));
		this.setResistance(resistance);
	}

}
