package application.EnemyBehavior;

import java.util.Random;

public class AList extends BasicList {
	
	public AList() {
		Enemy enemy;
		Random rand = new Random(); 
		int resistance = rand.nextInt(4);
		enemy = EnemyFactory.GetEnemy("Boss",2 + resistance);
		this.AddEnemy(enemy);
		this.DeleteFirst();
		this.MixList();
	}
}
