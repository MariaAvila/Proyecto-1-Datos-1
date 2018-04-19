package application;
	

import application.EnemyBehavior.AList;
import application.EnemyBehavior.BList;
import application.EnemyBehavior.BasicList;
import application.EnemyBehavior.Boss;
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
	private int BposX = 100;
	private int BposY = 20;
	private String type = "Basic";

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
	        list.setFirst(null);
	        addGameObject(player, 300, 550);
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
	
	public void addEnemies(BasicList list, int posX, int posY) {
		Enemy helper = list.getFirst();
		while (helper != null) {
			addGameObject(helper, posX, posY);
			posX += 40;
			helper = helper.getNext();
			}
		}
	
	private void onUpdate() {
		EnemyTimer ++;
		Enemy helper = list.getFirst();
		while (helper != null) {
			if (helper.isColliding(bullet)) {
				
				if (helper.getResistance()>1) {
					helper.setResistance(helper.getResistance() -1);
				}
				else {
					if (type.equals("A") || type.equals("B") ) {
						if (helper instanceof Boss) {
							Enemy killer = list.getFirst();
							while (killer != null) {
								killer.setAlive(false);
								root.getChildren().remove(killer.getView());
								killer = killer.getNext();
							}
						}
					}
                	helper.setAlive(false);
				
					list.setEnemyNumber(list.getEnemyNumber()-1);
					list.CenterList(helper);
                	root.getChildren().remove(helper.getView());
				}
				bullet.setAlive(false);
                bullet.getView().setTranslateY(-1);
				bullet.getView().setTranslateX(-1);
				
            }
			helper = helper.getNext();
		}
		list.DeleteEnemy();
		bullet.move();
		int newDisplace = list.Move(displace);
		if (newDisplace != displace) {
			BposY +=30;
		}
		displace = newDisplace;
		BposX -= displace;
		if (!bullet.isAlive()) {
			getRoot().getChildren().remove(bullet.getView());
		}
		if (list.getFirst() == null) {
			//Random rand = new Random(); 
			//int select = rand.nextInt(7);
			int select = 2 ;
			switch (select) {
			case 0:
				type = "Basic";
				list = new BasicList();
				addEnemies(list,100,20);
				break;
			case 1:
				type = "A";
				list = new AList();
				addEnemies(list,100,20);
				break;
			case 2:
				type = "B";
				list = new BList();
				addEnemies(list,100,20);
				BposX = 100;
				BposY = 20;
				break;
			case 3:
				System.out.println("3");
				//list = new BasicList();
				//addEnemies(list,100,20);
				break;
			case 4:
				System.out.println("4");
				//list = new BasicList();
				//addEnemies(list,100,20);
				break;
			case 5:
				System.out.println("5");
				//list = new BasicList();
				//addEnemies(list,100,20);
				break;
			case 6:
				System.out.println("6");
				//list = new BasicList();
				//addEnemies(list,100,20);
				break;
			}
			
			
		}
		if (EnemyTimer%50 == 0) {
			
			if (type.equals("B")) {
				Enemy remover = list.getFirst();
				while (remover != null) {
					root.getChildren().remove(remover.getView());
					remover = remover.getNext();
				}
				list.MixList();
				addEnemies(list,BposX,BposY);
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
