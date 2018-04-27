package application;
	

import application.EnemyBehavior.AList;
import application.EnemyBehavior.BList;
import application.EnemyBehavior.BasicList;
import application.EnemyBehavior.Boss;
import application.EnemyBehavior.CList;
import application.EnemyBehavior.DList;
import application.EnemyBehavior.Enemy;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.input.KeyCode;

import java.util.Random;


public class Main extends Application {
	
	public Pane root;
	private Player player = new Player();
	private Bullet bullet = new Bullet() ;
	private BasicList list = new BasicList();
	private int EnemyTimer = 0;
	private int displace = 1;
	private int posX = 100;
	private int posY = 20;
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
	
	public Parent createContent() {
	        setRoot(new Pane());
	        getRoot().setPrefSize(600, 600);
	        list.setFirst(null);
	        addGameObject(player, 300, 550);
	        bullet.setAlive(false);
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
		if (type.equals("C")|| type.equals("D")) {
					Enemy pos = list.getFirst();
					do {
						addGameObject(helper, posX, posY);
						posX += 40;
						helper = helper.getNext();
					}while (helper != pos);
		}
		else {
			while (helper != null) {
				addGameObject(helper, posX, posY);
				posX += 40;
				helper = helper.getNext();
				}
		}
		}
	
	public void onUpdate() {
		EnemyTimer ++;
		Enemy helper = list.getFirst();
		if (type.equals("C")|| type.equals("D")) {
			do {
				if (bullet.isAlive()) {
					if (helper.isColliding(bullet)) {
						if (helper.getResistance()>1) {
							//System.out.println("menos res");
							helper.setResistance(helper.getResistance() -1);
						}
						else {
							if (helper instanceof Boss ) {
								//System.out.println("why");
								Enemy remover = list.getFirst();
								do {
									root.getChildren().remove(remover.getView());
									remover = remover.getNext();
								}while (remover != list.getFirst());
							}
							else {
								root.getChildren().remove(helper.getView());
							}
							helper.setAlive(false);
							
							list.setEnemyNumber(list.getEnemyNumber()-1);
							//System.out.println("menos");
							list.CenterList(helper);
							
		                	
						}
						list.DeleteEnemy();
						//if (type.equals("D")) {
						//	list.Sort();
						//}
						bullet.setAlive(false);
		                bullet.getView().setTranslateY(-1);
						bullet.getView().setTranslateX(-1);
						if (list.getEnemyNumber() == 0) {
							break;
						}
						if (helper instanceof Boss && !(helper.isAlive())) {
							addEnemies(list,posX,posY);
						}
						if (list.getEnemyNumber() == 1) {
							helper = list.getFirst();
						}
						if (list.getEnemyNumber() == 0) {
							break;
						}
					}
				}
				helper = helper.getNext();
			}while (helper != list.getFirst());
		}
		else {
			while (helper != null) {
				if (helper.isColliding(bullet)) {
					
					if (helper.getResistance()>1) {
						helper.setResistance(helper.getResistance() -1);
					}
					else {
						if (helper instanceof Boss ) {
							if (type.equals("A") || type.equals("B")) {
								Enemy killer = list.getFirst();
								while (killer != null) {
									killer.setAlive(false);
									root.getChildren().remove(killer.getView());
									killer = killer.getNext();
								}
							}
							list.DeleteEnemy();
							addEnemies(list,posX,posY);
						}
	                	helper.setAlive(false);
					
						list.setEnemyNumber(list.getEnemyNumber()-1);
						list.CenterList(helper);
	                	root.getChildren().remove(helper.getView());
					}
					list.DeleteEnemy();
					bullet.setAlive(false);
	                bullet.getView().setTranslateY(-1);
					bullet.getView().setTranslateX(-1);
					
	            }
				helper = helper.getNext();
			}
		}
		
		bullet.move();
		int newDisplace = list.Move(displace);
		if (newDisplace != displace) {
			posY +=30;
		}
		displace = newDisplace;
		posX -= displace;
		if (!bullet.isAlive()) {
			getRoot().getChildren().remove(bullet.getView());
		}
		if (list.getFirst() == null) {
			posX = 100;
			posY = 20;
			//Random rand = new Random(); 
			//int select = rand.nextInt(6);
			int select = 4 ;
			switch (select) {
			case 0:
				type = "Basic";
				list = new BasicList();
				addEnemies(list,posX,posY);
				break;
			case 1:
				type = "A";
				list = new AList();
				addEnemies(list,posX,posY);
				break;
			case 2:
				type = "B";
				list = new BList();
				addEnemies(list,posX,posY);
				posX = 100;
				posY = 20;
				break;
			case 3:
				type = "C";
				list = new CList();
				addEnemies(list,posX,posY);
				break;
			case 4:
				type = "D";
				list = new DList();
				list.Sort();
				addEnemies(list,posX,posY);
				break;
			case 5:
				System.out.println("5");
				list = new BasicList();
				addEnemies(list,posX,posY);
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
				addEnemies(list,posX,posY);
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
