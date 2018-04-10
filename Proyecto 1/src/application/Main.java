package application;
	

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.KeyCode;


public class Main extends Application {
	
	private Pane root;
	private Player player = new Player();
	private Bullet bullet = new Bullet() ;
	private Enemy enemy = new Enemy(new Rectangle(20, 20, Color.BLUE));

	@Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) {
                player.moveX(-5);
            } else if (e.getCode() == KeyCode.RIGHT) {
                player.moveX(5);
            } else if (e.getCode() == KeyCode.SPACE) {
            	if (!bullet.isAlive()) {
            		bullet = new Bullet() ;
            		addGameObject(bullet, player.getView().getTranslateX()+10, 540);
            	}
               
            }
        });
        stage.show();
    }
	
	 private Parent createContent() {
	        root = new Pane();
	        root.setPrefSize(600, 600);

	        addGameObject(player, 300, 550);
	        AnimationTimer timer = new AnimationTimer() {
	            @Override
	            public void handle(long now) {
	            	onUpdate();
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
	
	private void onUpdate() {
		bullet.move();
		if (!bullet.isAlive()) {
			root.getChildren().remove(bullet.getView());
		}
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
