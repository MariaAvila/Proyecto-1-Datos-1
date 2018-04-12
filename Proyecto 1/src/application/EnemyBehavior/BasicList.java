package application.EnemyBehavior;


public class BasicList {
	
private Enemy first = null;
	
	public Enemy getFirst() {
		return first;
	}

	public void AddEnemy(Enemy toAdd) {
		Enemy helper = first;
		if (helper==null) {
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
		Enemy helper = first;
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
	
	public int Move(int displace) {
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
	
	public BasicList() {
		for (int i = 0; i < 7 ;i++ ) {
			Enemy enemy = EnemyFactory.GetEnemy("Minion",1);
			this.AddEnemy(enemy);
		}
	}

}

