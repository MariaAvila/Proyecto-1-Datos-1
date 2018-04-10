package application;

public class BulletList {
	private Bullet first = null;
	
	public Bullet getFirst() {
		return first;
	}

	public void AddBullet(Bullet toAdd) {
		Bullet helper = first;
		if (helper.equals(null)) {
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
	
	public void DeleteBullet() {
		Bullet helper = first;
		Bullet prev = null;
		while (helper != null) {
			if (!helper.isAlive()) {
				if (prev.equals(null)) {
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
