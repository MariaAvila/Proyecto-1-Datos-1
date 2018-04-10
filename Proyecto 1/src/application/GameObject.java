package application;

import javafx.scene.Node;

public class GameObject {
	private Node view;

    private boolean alive = true;
    
    public GameObject(Node view) {
        this.view = view;
    }

    public Node getView() {
        return view;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isDead() {
        return !alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isColliding(GameObject other) {
        return getView().getBoundsInParent().intersects(other.getView().getBoundsInParent());
    }
}
