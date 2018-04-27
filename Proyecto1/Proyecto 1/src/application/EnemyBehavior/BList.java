package application.EnemyBehavior;

import java.util.Random;

public class BList extends BasicList{
	
	public BList(){
		Enemy enemy;
		Random rand = new Random(); 
		int resistance = rand.nextInt(4);
		enemy = EnemyFactory.GetEnemy("Boss",2 + resistance);
		this.AddEnemy(enemy);
		this.DeleteFirst();
	}
	
	public void AddEnemy(Enemy toAdd) {
		Enemy helper = getFirst();
		if (helper == null) {
			this.setFirst(toAdd);
		}
		else {
			while (helper.getNext() != null) {
				helper = helper.getNext();
			}
			helper.setNext(toAdd);
			helper.getNext().setPrevious(helper);
		}
	}	
	
	public void DeleteFirst() {
		this.setFirst(this.getFirst().getNext());
		if (this.getFirst() != null) {
			this.getFirst().setPrevious(null);
		}
	}
	
	public void DeleteLast() {
		Enemy helper = getFirst();
		Enemy prev = null;
		while (helper != null) {
			if (!helper.isAlive()) {
				if (prev==null) {
					this.DeleteFirst();
				}
				else {
					while (helper.getNext() != null) {
						prev = helper;
						helper = helper.getNext();
					}
					helper.setPrevious(null);
					prev.setNext(null);
				}
			}
			prev = helper;
			helper = helper.getNext();
		}
	}
	
	public void DeleteEnemy() {
		Enemy helper = this.getFirst();
		Enemy prev = null;
		while (helper != null) {
			if (!helper.isAlive()) {
				if (prev==null) {
					this.DeleteFirst();
				}
				else {
					if (helper.getNext() != null) {
						helper.getNext().setPrevious(prev);
						prev.setNext(helper.getNext());		
					}
					else {
						this.DeleteLast();
					}
				}
			}
			prev = helper;
			helper = helper.getNext();
		}
	}
	
	public void MixList() {
		Enemy boss = this.getFirst();
		while (!(boss instanceof Boss)) {
			boss = boss.getNext();
		}
		int BossRes = boss.getResistance();
		BList toMix = new BList();
		toMix.setFirst(null);
		Random rand = new Random(); 
		int pos = rand.nextInt(this.getEnemyNumber());
		for (int i = 0; i< pos; i++) {
			Enemy enemy = EnemyFactory.GetEnemy("Minion",1);
			toMix.AddEnemy(enemy);
		}
		Enemy newBoss = EnemyFactory.GetEnemy("Boss",BossRes);
		toMix.AddEnemy(newBoss);
		pos = this.getEnemyNumber() - pos - 1;
		for (int i = 0; i< pos; i++) {
			Enemy enemy = EnemyFactory.GetEnemy("Minion",1);
			toMix.AddEnemy(enemy);
		}
		this.setFirst(toMix.getFirst());
	}

}
