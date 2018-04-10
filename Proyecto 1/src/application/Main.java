package application;
	
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.input.KeyCode;


public class Main extends Application {
	
	private Pane root;
	private Player player = new Player();

	@Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) {
                player.moveX(-10);
            } else if (e.getCode() == KeyCode.RIGHT) {
                player.moveX(10);
            } else if (e.getCode() == KeyCode.SPACE) {
               
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
