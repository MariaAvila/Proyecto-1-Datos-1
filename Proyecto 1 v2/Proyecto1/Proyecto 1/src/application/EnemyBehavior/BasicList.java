package application.EnemyBehavior;

import java.util.Random;


public class BasicList {
	
	private Enemy first = null;
	private Enemy last = null;
	private Enemy center = null;
	public Enemy getCenter() {
		return center;
	}

	public void setCenter(Enemy center) {
		this.center = center;
	}

	public Enemy getLast() {
		return last;
	}

	public void setLast(Enemy last) {
		this.last = last;
	}

	private int EnemyNumber = 7;
	
	public Enemy getFirst() {
		return first;
	}

	public void AddEnemy(Enemy toAdd) {
		Enemy helper = first;
		if (helper == null) {
			this.first = toAdd;
		}
		else {
			while (helper.getNext() != null) {
				helper = helper.getNext();
			}
			helper.setNext(toAdd);
		}
	}	
	
	public void DeleteFirst() {
		this.first = this.first.getNext();
	}
	
	public void DeleteLast() {
		Enemy helper = first;
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
					prev.setNext(null);
					
					
				}
			}
			prev = helper;
			helper = helper.getNext();
		}
	}
	
	public void DeleteEnemy() {
		if (this.getEnemyNumber()==0) {
			this.setFirst(null);
		}
		else {
			Enemy helper = this.first;
			Enemy prev = null;
			while (helper != null) {
				if (!helper.isAlive()) {
					if (prev==null) {
						this.DeleteFirst();
					}
					else {
						prev.setNext(helper.getNext());				
					}
				}
				prev = helper;
				helper = helper.getNext();
			}
		}
	}
	
	public int Move(int displace, int dg) {
		Enemy helperX = this.first;
		while (helperX != null) {
			if (helperX.getView().getTranslateX()>0) {
				if (helperX.getView().getTranslateX()<580) {
					helperX.getView().setTranslateX(helperX.getView().getTranslateX() - displace);
				}
				else {
					displace *= -1;
					helperX.getView().setTranslateX(579);
					Enemy helperY = this.first;
					while (helperY != null) {
						helperY.getView().setTranslateY(helperY.getView().getTranslateY() + 30);
						helperY = helperY.getNext();
						}
					}
			}
			else {
				displace *= -1;
				helperX.getView().setTranslateX(1);
				Enemy helperY = this.first;
				while (helperY != null) {
					helperY.getView().setTranslateY(helperY.getView().getTranslateY() + 30);
					helperY = helperY.getNext();
				}
				
			}
			helperX = helperX.getNext();
		}
		return displace;
	}
	
	public void CenterList(Enemy base,int dg) {
		double location = base.getView().getTranslateX();
		Enemy helper = this.getFirst();
		Enemy prev = null;
		if (helper == null) {
			return;
		}
		if (helper == base || base == this.getFirst()) {
			return;
		}
		while (prev != base) {
			prev = helper;
			helper = helper.getNext();
		}
		while (helper != null) {
			helper.getView().setTranslateX(location);
			location += 40;
			helper = helper.getNext();
		}
		
	}
	
	public void MixList() {
		BasicList result = new BasicList();
		result.setFirst(null);
		Random rand = new Random(); 
		Enemy helper = this.first;
		Enemy next = null;
		int pos = rand.nextInt(EnemyNumber);
		for (int i = 0; i< pos; i++) {
			helper = helper.getNext();
		}
		while (helper != null) {
			next = helper.getNext();
			helper.setNext(null);
			result.AddEnemy(helper);
			helper.setNext(next);
			helper = helper.getNext();
		}
		for (int i = 0; i< pos; i++) {
			Enemy enemy = EnemyFactory.GetEnemy("Minion",1);
			result.AddEnemy(enemy);
		}
		this.setFirst(result.getFirst());
		
	}
	public void Sort() {
		return;
	}
	
	public void setFirst(Enemy first) {
		this.first = first;
	}

	public int getEnemyNumber() {
		return EnemyNumber;
	}

	public void setEnemyNumber(int enemyNumber) {
		EnemyNumber = enemyNumber;
	}

	public BasicList() {
		Enemy enemy;
		for (int i = 0; i < 7 ;i++ ) {
			enemy = EnemyFactory.GetEnemy("Minion",1);
			this.AddEnemy(enemy);
		}
	}

}

