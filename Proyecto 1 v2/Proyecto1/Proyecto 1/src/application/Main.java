package application;
	

import application.EnemyBehavior.AList;
import application.EnemyBehavior.BList;
import application.EnemyBehavior.BasicList;
import application.EnemyBehavior.Boss;
import application.EnemyBehavior.CList;
import application.EnemyBehavior.DList;
import application.EnemyBehavior.EList;
import application.EnemyBehavior.Enemy;
import application.EnemyBehavior.General;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.input.KeyCode;

import java.io.IOException;
import java.util.Random;


public class Main extends Application {
	
	public Pane root;
	private static  Server server = new Server();
	private Player player = new Player();
	private Bullet bullet = new Bullet() ;
	private BasicList list = new BasicList();
	private int EnemyTimer = 0;
	private int displace = 1;
	private int degree = 0;
	private int posX = 100;
	private int posY = 20;

	private IntegerProperty linesLeft = new SimpleIntegerProperty();
	private Text lineasRestantes =new Text();
	private IntegerProperty nivel = new SimpleIntegerProperty();
	private Text Level =new Text();
	
	private int select = 1;
	private StringProperty type = new SimpleStringProperty();
	private StringProperty nextType = new SimpleStringProperty();
	private Text enemyType =new Text();
	private Text nextEnemyType =new Text();
	
	private IntegerProperty puntaje = new SimpleIntegerProperty();
	private Text Score =new Text();
	
	private Node Separator = new Rectangle(1, 600, Color.BLACK);
	

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
	        getRoot().setPrefSize(800, 600);
	        list.setFirst(null);
	        addGameObject(player, 300, 550);
	        bullet.setAlive(false);
	        
	        Score.setTranslateX(630);
	        Score.setTranslateY(50);
	        Score.setFont(Font.font(20));
	        Score.textProperty().bind(puntaje.asString("Puntaje: %d"));
	        root.getChildren().add(Score);
	        
	        type.set("None");
	        enemyType.setTranslateX(620);
	        enemyType.setTranslateY(90);
	        enemyType.setFont(Font.font(20));
	        enemyType.textProperty().bind(type.concat(" = Linea Actual"));
	        root.getChildren().add(enemyType);
	        
	        nextEnemyType.setTranslateX(620);
	        nextEnemyType.setTranslateY(130);
	        nextEnemyType.setFont(Font.font(18));
	        nextEnemyType.textProperty().bind(nextType.concat(" = Linea Siguiente"));
	        root.getChildren().add(nextEnemyType);
	        
	        nivel.set(1);
	        Level.setTranslateX(630);
	        Level.setTranslateY(170);
	        Level.setFont(Font.font(20));
	        Level.textProperty().bind(nivel.asString("Nivel: %d"));
	        root.getChildren().add(Level);
	        
	        linesLeft.set(11);
	        lineasRestantes.setTranslateX(620);
	        lineasRestantes.setTranslateY(210);
	        lineasRestantes.setFont(Font.font(20));
	        lineasRestantes.textProperty().bind(linesLeft.asString("Lineas restantes\npara siguiente\nnivel: %d"));
	        root.getChildren().add(lineasRestantes);
	        
	        Separator.setTranslateX(600);
	        Separator.setTranslateY(0);
	        root.getChildren().add(Separator);
	        
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
		if (type.get().equals("C")|| type.get().equals("D")|| type.get().equals("E")) {
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
		if (type.get().equals("C")|| type.get().equals("D")|| type.get().equals("E")) {
			do {
				if (bullet.isAlive()) {
					if (helper.isColliding(bullet)) {
						if (helper.getResistance()>1) {
							//System.out.println("menos res");
							helper.setResistance(helper.getResistance() -1);
						}
						else {
							if (helper instanceof Boss && !(type.get().equals("E"))) {
								//System.out.println("why");
								puntaje.set(puntaje.get() + 50);;
								Enemy remover = list.getFirst();
								do {
									root.getChildren().remove(remover.getView());
									remover = remover.getNext();
								}while (remover != list.getFirst());
							}
							else{
								if (helper instanceof General) {
									puntaje.set(puntaje.get() + 30);
								}
								else {
									puntaje.set(puntaje.get() + 10);
								}
								root.getChildren().remove(helper.getView());
							}
							helper.setAlive(false);
							if (type.get().equals("E")&& helper instanceof Boss && !(helper.isAlive())) {
								if (list.getEnemyNumber() != 1) {
									getRoot().getChildren().add(helper.getView());
									Random rand = new Random(); 
									int resistance = rand.nextInt(4);
									helper.setResistance(2 + resistance);
								}
							}
							
							
							list.setEnemyNumber(list.getEnemyNumber()-1);
							//System.out.println("menos");
							list.CenterList(helper,degree);
							
		                	
						}
						list.DeleteEnemy();
						bullet.setAlive(false);
		                bullet.getView().setTranslateY(-1);
						bullet.getView().setTranslateX(-1);
						if (list.getEnemyNumber() == 0) {
							break;
						}
						if (helper instanceof Boss && !(helper.isAlive())&& !(type.get().equals("E"))) {
							addEnemies(list,posX,posY);
						}
/*						if (helper instanceof Boss && type.equals("E")&&(!(list.getFirst().isAlive())||!(list.getLast().isAlive()))) {
							addEnemies(list,posX,posY);
						}*/
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
							puntaje.set(puntaje.get() + 50);;
							if (type.get().equals("A") || type.get().equals("B")) {
								Enemy killer = list.getFirst();
								while (killer != null) {
									killer.setAlive(false);
									root.getChildren().remove(killer.getView());
									killer = killer.getNext();
								}
								list.setEnemyNumber(0);
							}
							list.DeleteEnemy();
							addEnemies(list,posX,posY);
						}
						else {
							puntaje.set(puntaje.get() + 10);
							root.getChildren().remove(helper.getView());
						}
	                	helper.setAlive(false);
	                	
						list.setEnemyNumber(list.getEnemyNumber()-1);
						list.CenterList(helper,degree);
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
		int newDisplace = list.Move(displace,degree);
		if (newDisplace != displace) {
			posY +=30;
		}
		displace = newDisplace;
		posX -= displace;
		if (!bullet.isAlive()) {
			getRoot().getChildren().remove(bullet.getView());
		}
		degree += 1;
		if (list.getFirst() == null) {
			linesLeft.set(linesLeft.get()-1);
			if (linesLeft.get() == 0) {
				nivel.set(nivel.get() + 1);
				if((nivel.get()%2 -1 )== 0) {
					displace *=2;
				}
				linesLeft.set(10);
			}
			posX = 100;
			posY = 20;
			Random rand = new Random(); 
			int nextSelect = rand.nextInt(5);
			switch (select) {
			case 0:
				type.set("Basic");
				list = new BasicList();
				addEnemies(list,posX,posY);
				break;
			case 1:
				type.set("A");
				list = new AList();
				addEnemies(list,posX,posY);
				break;
			case 2:
				type.set("B");
				list = new BList();
				addEnemies(list,posX,posY);
				break;
			case 3:
				type.set("C");
				list = new CList();
				addEnemies(list,posX,posY);
				break;
			case 4:
				type.set("D");
				list = new DList();
				list.Sort();
				addEnemies(list,posX,posY);
				break;
			case 5:
				type.set("E");
				list = new EList();
				addEnemies(list,posX,posY);
				break;
			}
			select = nextSelect;
			switch (nextSelect) {
			case 0:
				nextType.set("Basic");
				break;
			case 1:
				nextType.set("A");
				break;
			case 2:
				nextType.set("B");
				break;
			case 3:
				nextType.set("C");
				break;
			case 4:
				nextType.set("D");
				break;
			case 5:
				nextType.set("E");
				break;
			}
			
			
		}
		if (EnemyTimer%50 == 0) {
			
			if (type.get().equals("B")) {
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
	
	public static void main(String[] args) throws IOException {
		//new Thread((Runnable) server).start();
		launch(args);
	    
	        
	}

	public Pane getRoot() {
		return root;
	}

	public void setRoot(Pane root) {
		this.root = root;
	}
}
