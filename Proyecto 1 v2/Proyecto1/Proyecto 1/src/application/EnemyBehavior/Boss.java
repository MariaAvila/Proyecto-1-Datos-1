package application.EnemyBehavior;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Boss extends Enemy{

	public Boss(int resistance) {
		super(new Rectangle(25, 25, Color.RED));
		this.setResistance(resistance);
	}

}
