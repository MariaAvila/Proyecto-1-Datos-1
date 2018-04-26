package application.EnemyBehavior;

import java.util.Random;

public class DList extends CList{
	public DList() {
		this.setFirst(null);
		this.setLast(null);
		Enemy enemy;
		for (int i = 0; i < 6 ;i++ ) {
			Random rand = new Random(); 
			int resistance = rand.nextInt(4);
			enemy = EnemyFactory.GetEnemy("General",1+resistance);
			this.AddEnemy(enemy);
		}
		Enemy Boss;
		Random rand = new Random(); 
		int resistance = rand.nextInt(4);
		Boss = EnemyFactory.GetEnemy("Boss",2 + resistance);
		this.AddEnemy(Boss);
		this.MixList();
		//this.Sort();
	}
	
	public void Sort() {
		BasicList result = new DList();
		Enemy toAdd = null;
		int len = this.getEnemyNumber();
		Enemy high = EnemyFactory.GetEnemy("Minion",1);
		result.setFirst(null);
		result.setLast(null);
		Enemy pos = this.getFirst();
		for (int i = 0; i< len; i++) {
			for (int j = 0; j< len; j++) {
				if (pos.isAlive()) {
					if (pos.getResistance() >high.getResistance()) {
						high = pos;
					}
				}
				pos = pos.getNext();
			}
			high.setAlive(false);
			if (high instanceof Boss) {
				toAdd = EnemyFactory.GetEnemy("Boss",high.getResistance());
			}
			else {
				toAdd = EnemyFactory.GetEnemy("General",high.getResistance());
			}
			result.AddEnemy(toAdd);
			high = EnemyFactory.GetEnemy("Minion",1);
			pos = this.getFirst();
		}
		
		Enemy helper = result.getFirst();
		pos = result.getFirst();
		do {
			helper.setAlive(true);
			helper = helper.getNext();
		}while (helper != pos);
		this.setFirst(result.getFirst());
		this.setLast(result.getLast());
	}
}
