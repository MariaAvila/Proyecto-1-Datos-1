package application.EnemyBehavior;

import java.util.Random;

public class EList extends BasicList {
	public EList() {
		this.setFirst(null);
		Enemy enemy;
		for (int i = 0; i<3;i++) {
			enemy = EnemyFactory.GetEnemy("Minion",1);
			this.AddEnemy(enemy);
		}
		Random rand = new Random(); 
		int resistance = rand.nextInt(4);
		enemy = EnemyFactory.GetEnemy("Boss",2 + resistance);
		this.AddEnemy(enemy);
		this.setCenter(enemy);
		for (int i = 0; i<3;i++) {
			enemy = EnemyFactory.GetEnemy("Minion",1);
			this.AddEnemy(enemy);
		}
	}
	
	public void AddEnemy(Enemy toAdd) {
		Enemy helper = this.getFirst();
		if (helper == null) {
			this.setFirst(toAdd);
			this.setLast(toAdd);
			toAdd.setNext(toAdd);
			toAdd.setPrevious(toAdd);
		}
		else {
			Enemy last = this.getLast();
			last.setNext(toAdd);
			toAdd.setNext(helper);
			helper.setPrevious(toAdd);
			toAdd.setPrevious(last);
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
				if (helper instanceof Boss) {
					helper.setAlive(true);
					helper = helper.getNext();
					helper.setAlive(false);
					this.DeleteEnemy();
					break;
				}
				Enemy aux = this.getFirst();
				if (helper == this.getLast()) {
					aux = this.getLast().getPrevious();
					aux.setNext(helper.getNext());
					this.getFirst().setPrevious(aux);
					this.setLast(aux);
				}
				else if(helper == this.getFirst()) {
					this.getLast().setNext(helper.getNext());
					helper.getNext().setPrevious(this.getLast());
					this.setFirst(helper.getNext());
				}
				else {
					aux = helper.getPrevious();
					aux.setNext(helper.getNext());
					helper.getNext().setPrevious(aux);
				}
				
				helper = this.getFirst();
				pos = this.getFirst();
			}
			helper = helper.getNext();
		}while (helper != pos);
	}
	
	public int Move(int displace, int dg) {
		//r = 120 410
		Enemy helperX = this.getCenter();
		Enemy pos = this.getCenter();
		double rad = Math.toRadians(dg);
		int radius = 0;

		if (pos == null) {
			return displace;
		}
		do {
			if (radius == 200){
				radius = -150;
			}
			helperX.getView().setTranslateX(300 + radius*Math.cos(rad));
			helperX.getView().setTranslateY(150 + radius*Math.sin(rad));
			helperX = helperX.getNext();
			radius +=50;
		}while (helperX != pos);
		return displace;
	}

	public void CenterList(Enemy base, int dg) {
		Enemy helperX = this.getCenter();
		Enemy pos = this.getCenter();
		double rad = Math.toRadians(dg);
		int radius = 0;
		
		if (helperX == null || helperX == base || base == this.getLast()) {
			return;
		}
		do {
			if (radius == 200){
				radius = -150;
			}
			helperX.getView().setTranslateX(300 + radius*Math.cos(rad));
			helperX.getView().setTranslateY(150 + radius*Math.sin(rad));
			helperX = helperX.getNext();
			radius +=50;
		}while (helperX != pos);

	}

}
