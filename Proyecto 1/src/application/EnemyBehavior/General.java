package application.EnemyBehavior;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class General extends Enemy{

	public General(int resistance) {
		super(new Rectangle(25, 25, Color.YELLOW));
		this.setResistance(resistance);
	}

}
