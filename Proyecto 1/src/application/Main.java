package application;
	

import application.EnemyBehavior.BasicList;
import application.EnemyBehavior.Enemy;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.input.KeyCode;

import java.util.Random;


public class Main extends Application {
	
	private Pane root;
	private Player player = new Player();
	private Bullet bullet = new Bullet() ;
	private BasicList list = new BasicList();
	private int EnemyTimer = 0;
	private int displace = 1;

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
	        setRoot(new Pane());
	        getRoot().setPrefSize(600, 600);

	        addGameObject(player, 300, 550);
	        list = new BasicList();
	        addEnemies(list);
	        AnimationTimer timer = new AnimationTimer() {
	            @Override
	            public void handle(long now) {
	            	onUpdate();
	            }
	        };
	        timer.start();

	        return getRoot();
	    }
	
	public void addGameObject(GameObject object, double x, double y) {
        object.getView().setTranslateX(x);
        object.getView().setTranslateY(y);
        getRoot().getChildren().add(object.getView());
	}
	
	public void addEnemies(BasicList list) {
		Enemy helper = list.getFirst();
		int pos = 100;
		while (helper != null) {
			addGameObject(helper, pos, 20);
			pos += 40;
			helper = helper.getNext();
			}
		}
	
	private void onUpdate() {
		EnemyTimer ++;
		Enemy helper = list.getFirst();
		while (helper != null) {
			if (helper.isColliding(bullet)) {
                bullet.setAlive(false);
                Enemy toKill = helper;
                while (toKill.getNext() != null) {
                	toKill = toKill.getNext();
                }
                toKill.setAlive(false);
                
                bullet.getView().setTranslateY(-1);
				bullet.getView().setTranslateX(-1);

                root.getChildren().removeAll (toKill.getView());
            }
			helper = helper.getNext();
		}
		list.DeleteLast();
		bullet.move();
		displace = list.Move(displace);
		if (!bullet.isAlive()) {
			getRoot().getChildren().remove(bullet.getView());
		}
		if (EnemyTimer%10 == 0) {
			
			if (list.getFirst() == null) {
				Random rand = new Random(); 
				int select = rand.nextInt(7);
				switch (select) {
				case 0:
					System.out.println("0");
					list = new BasicList();
					addEnemies(list);
					break;
				case 1:
					System.out.println("1");
					list = new BasicList();
					addEnemies(list);
					break;
				case 2:
					System.out.println("2");
					list = new BasicList();
					addEnemies(list);
					break;
				case 3:
					System.out.println("3");
					list = new BasicList();
					addEnemies(list);
					break;
				case 4:
					System.out.println("4");
					list = new BasicList();
					addEnemies(list);
					break;
				case 5:
					System.out.println("5");
					list = new BasicList();
					addEnemies(list);
					break;
				case 6:
					System.out.println("6");
					list = new BasicList();
					addEnemies(list);
					break;
				}
				
				
			}
			}
    }
	
	public static void main(String[] args) {
		launch(args);
	}

	public Pane getRoot() {
		return root;
	}

	public void setRoot(Pane root) {
		this.root = root;
	}
}
