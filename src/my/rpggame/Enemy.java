package my.rpggame;

public class Enemy {
	String name;
	int health;
	int dmg;
	int goldWorth;
	
	Enemy(int random) {
		
		switch (random) {
		case 1: 
			name = "Skeleton" ;
			health=10;
			dmg = 3;
			goldWorth = 10 ;
			break;
		case 2: 
			name = "Wolf";
			health=25;
			dmg=8;
			goldWorth=3;
			break;
		default: 
			break; } 
		}
}