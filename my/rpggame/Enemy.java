package my.rpggame;

public class Enemy {
	String name;
	int health;
	int minDmg;
	int maxDmg;
	int goldWorth;
	
	Enemy(int random) {
		
		switch (random) {
		case 1: 
			name = "Skeleton";
			health=10;
			minDmg = 6;
			maxDmg = 9;
			goldWorth = 10;
			System.out.println("A skeleton approaches! Health: " + health);
			break;
		case 2: 
			name = "Wolf";
			health=25;
			minDmg = 3;
			maxDmg = 5;
			goldWorth=3;
			System.out.println("A wolf ambushes you! Health: " + health);
			break;
		default: 
			break;
		} 
	}
	
	void attack (Character player) {
		if (health > 0) {
			int realDmg = calcDmg();
			player.health -= realDmg;
			System.out.println("Enemy does " + realDmg + " damage." + ((player.health > 0) ? " Player health: " + player.health : ""));
		}
	}
	
	int calcDmg() {
		return minDmg + (int)((maxDmg - minDmg + 1) * Math.random());
	}
	
}