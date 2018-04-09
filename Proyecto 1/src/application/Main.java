package application;
	
import java.util.HashMap;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.input.KeyCode;


public class Main extends Application {
	
	private Pane root;
	private GameObject player;

	
	private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
	@Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.show();
    }
	
	 private Parent createContent() {
	        root = new Pane();
	        root.setPrefSize(600, 600);

	        player = new Player();
	        addGameObject(player, 300, 300);
	        AnimationTimer timer = new AnimationTimer() {
	            @Override
	            public void handle(long now) {
	            	player.update();
	            }
	        };
	        timer.start();

	        return root;
	    }
	
	private void addGameObject(GameObject object, double x, double y) {
        object.getView().setTranslateX(x);
        object.getView().setTranslateY(y);
        root.getChildren().add(object.getView());
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
