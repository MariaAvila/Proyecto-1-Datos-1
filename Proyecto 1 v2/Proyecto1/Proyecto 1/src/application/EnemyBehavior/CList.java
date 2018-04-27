package application.EnemyBehavior;

import java.util.Random;

public class CList extends BasicList{
	public CList(){
		this.getFirst().setAlive(false);
		this.DeleteEnemy();
		Enemy enemy;
		Random rand = new Random(); 
		int resistance = rand.nextInt(4);
		enemy = EnemyFactory.GetEnemy("Boss",2 + resistance);
		this.AddEnemy(enemy);
		this.MixList();
	}
	
	public void AddEnemy(Enemy toAdd) {
		Enemy helper = this.getFirst();
		if (helper == null) {
			this.setFirst(toAdd);
			this.setLast(toAdd);
			toAdd.setNext(toAdd);
		}
		else {
			Enemy last = this.getLast();
			last.setNext(toAdd);
			toAdd.setNext(helper);
			this.setLast(toAdd);
		}
	}	
	
	public void DeleteEnemy() {
		Enemy helper = this.getFirst();
		Enemy pos = this.getFirst();
		do {
			if (!(helper.isAlive())) {
				if (this.getEnemyNumber() == 0) {
					this.setFirst(null);
					this.setLast(null);
					break;
				}
				Enemy aux = this.getFirst();
				if (helper == this.getLast()) {
					while (aux.getNext() != helper) {
						aux = aux.getNext();
					}
					aux.setNext(helper.getNext());
					this.setLast(aux);
				}
				else if(helper == this.getFirst()) {
					this.getLast().setNext(helper.getNext());
					this.setFirst(helper.getNext());
				}
				else {
					while (aux.getNext() != helper) {
						aux = aux.getNext();
					}
					aux.setNext(helper.getNext());
				}
				if (helper instanceof Boss) {
					Random rand = new Random(); 
					int resistance = rand.nextInt(4);
					Enemy enemy = EnemyFactory.GetEnemy("Boss",2 + resistance);
					if (this.getEnemyNumber()==1) {
						this.setFirst(enemy);
						this.setLast(enemy);
						enemy.setNext(enemy);
					}
					else {
						enemy.setNext(this.getFirst().getNext());
						this.getLast().setNext(enemy);
						this.setFirst(enemy);
						this.MixList();
					}
				}
				helper = this.getFirst();
				pos = this.getFirst();
			}
			helper = helper.getNext();
		}while (helper != pos);
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
		helper = this.getFirst();
		Enemy aux = this.getFirst();
		while (aux.getNext()!=helper) {
			aux = aux.getNext();
		}
		this.setLast(aux);
	}
	
	public int Move(int displace,int dg) {
		Enemy helperX = this.getFirst();
		Enemy pos = this.getFirst();
		if (pos == null) {
			return displace;
		}
		do {
			if (helperX.getView().getTranslateX()>0) {
				if (helperX.getView().getTranslateX()<580) {
					helperX.getView().setTranslateX(helperX.getView().getTranslateX() - displace);
				}
				else {
					displace *= -1;
					helperX.getView().setTranslateX(579);
					Enemy helperY = this.getFirst();
					do {
						helperY.getView().setTranslateY(helperY.getView().getTranslateY() + 30);
						helperY = helperY.getNext();
						}while (helperY != pos);
					}
			}
			else {
				displace *= -1;
				helperX.getView().setTranslateX(1);
				Enemy helperY = this.getFirst();
				do {
					helperY.getView().setTranslateY(helperY.getView().getTranslateY() + 30);
					helperY = helperY.getNext();
				}while (helperY != pos);
				
			}
			helperX = helperX.getNext();
		}while (helperX != pos);
		return displace;
	}
	
	public void CenterList(Enemy base, int degree) {
		double location = base.getView().getTranslateX();
		Enemy helper = this.getFirst();
		Enemy prev = null;
		if (helper == null || helper == base || base == this.getLast()) {
			return;
		}
		while (prev != base) {
			prev = helper;
			helper = helper.getNext();
		}
		do {
			helper.getView().setTranslateX(location);
			location += 40;
			helper = helper.getNext();
		}while (helper != this.getFirst());
		
	}

}

