package application.EnemyBehavior;

public class EnemyFactory {
	public static Enemy GetEnemy(String type, int resistance) {
		if (type.equalsIgnoreCase("Minion")) {
			return new Minion(resistance);
		}
		else if (type.equalsIgnoreCase("General")) {
			return new General(resistance);
		}
		else if (type.equalsIgnoreCase("Boss")) {
			return new Boss(resistance);
		}
		return null;
	}

}
