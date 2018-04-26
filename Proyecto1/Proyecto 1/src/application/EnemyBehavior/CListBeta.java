package application.EnemyBehavior;

import java.util.Random;

public class CListBeta extends BasicList{
	public CListBeta(){
		Enemy enemy;
		Random rand = new Random(); 
		int resistance = rand.nextInt(4);
		enemy = EnemyFactory.GetEnemy("Boss",2 + resistance);
		this.AddEnemy(enemy);
		this.MixList();
	}
	
	public void AddEnemy(Enemy toAdd) {
		Enemy helper = getFirst();
		if (helper == null) {
			this.setFirst(toAdd);
			toAdd.setNext(toAdd);
			toAdd.setPrevious(toAdd);
		}
		else {
			Enemy last = helper.getPrevious();
			last.setNext(toAdd);
			helper.setPrevious(toAdd);
			toAdd.setNext(helper);
			toAdd.setPrevious(last);
		}
	}	
	
	public void DeleteEnemy() {
		Enemy helper = this.getFirst().getNext();
		Enemy pos = this.getFirst();
		if (this.getEnemyNumber() == 1 && !(helper.isAlive())) {
			this.setFirst(null);
		}
		else if (this.getEnemyNumber() == 2 && helper instanceof Boss) {
			this.setFirst(null);
			Enemy newBoss;
			Random rand = new Random(); 
			int resistance = rand.nextInt(4);
			newBoss = EnemyFactory.GetEnemy("Boss",2 + resistance);
			this.AddEnemy(newBoss);
		}
		else if (!(pos.isAlive())) {
			if (helper instanceof Boss) {
				Enemy cutter = helper.getNext();
				helper = helper.getPrevious().getPrevious();
				helper.setNext(cutter);
				cutter.setPrevious(helper);
				Enemy newBoss;
				Random rand = new Random(); 
				int resistance = rand.nextInt(4);
				newBoss = EnemyFactory.GetEnemy("Boss",2 + resistance);
				this.AddEnemy(newBoss);
				this.MixList();
			}
			else {
				Enemy cutter = helper.getNext();
				helper = helper.getPrevious();
				helper.setNext(cutter);
				cutter.setPrevious(helper);
			}
		}
		else {
			while (helper != pos) {
				if (!(helper.isAlive())) {
					if (helper instanceof Boss) {
						Enemy cutter = helper.getNext();
						helper = helper.getPrevious().getPrevious();
						helper.setNext(cutter);
						cutter.setPrevious(helper);
						Enemy newBoss;
						Random rand = new Random(); 
						int resistance = rand.nextInt(4);
						newBoss = EnemyFactory.GetEnemy("Boss",2 + resistance);
						this.AddEnemy(newBoss);
						this.MixList();
					}
					else {
						Enemy cutter = helper.getNext();
						helper = helper.getPrevious();
						helper.setNext(cutter);
						cutter.setPrevious(helper);
					}
				}
				helper = helper.getNext();
			}
		}
	}
	
	public void MixList() {
		Enemy helper = this.getFirst();
		Random rand = new Random(); 
		int a  = this.getEnemyNumber();
		int pos = rand.nextInt(a);
		for (int i = 0; i< pos; i++) {
			helper = helper.getNext();
		}
		this.setFirst(helper);
	}
	
	public int Move(int displace) {
		Enemy helperX = this.getFirst().getNext();
		Enemy pos = this.getFirst();
		while (helperX != pos) {
			if (helperX.getView().getTranslateX()>0) {
				if (helperX.getView().getTranslateX()<580) {
					helperX.getView().setTranslateX(helperX.getView().getTranslateX() - displace);
				}
				else {
					displace *= -1;
					helperX.getView().setTranslateX(579);
					Enemy helperY = this.getFirst().getNext();
					while (helperY != pos) {
						helperY.getView().setTranslateY(helperY.getView().getTranslateY() + 30);
						helperY = helperY.getNext();
						}
					}
			}
			else {
				displace *= -1;
				helperX.getView().setTranslateX(1);
				Enemy helperY = this.getFirst().getNext();
				while (helperY != pos) {
					helperY.getView().setTranslateY(helperY.getView().getTranslateY() + 30);
					helperY = helperY.getNext();
				}
				
			}
			helperX = helperX.getNext();
		}
		return displace;
	}

}
