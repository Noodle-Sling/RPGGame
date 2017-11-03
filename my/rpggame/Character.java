package my.rpggame;

public class Character {
	int maxHealth;
	int health;
	volatile boolean run;
	/* String healthStatement;
	switch (health) {
	
	}
	 */
	int minDmg;
	int maxDmg;
	int gold = 0;
	int enemiesKilled = 0;

	Character(String input) {
		
		switch (input) {
		case "knight": 
			maxHealth = 90;
			health = 90;
			minDmg = 6;
			maxDmg = 8;
			System.out.println("Congratulations, you have chosen knight!");
			break;
		case "mage": 
			maxHealth = 60;
			health = 60;
			minDmg = 5;
			maxDmg = 11;
			System.out.println("Congratulations, you have chosen mage!");
			break;
		default: 
			System.out.println("You didn't select a character.");
			break;
		} 
	}
	
	int calcDmg() {
		return minDmg + (int)((maxDmg - minDmg + 1) * Math.random());
	}
	
	void attack(Enemy enemy) {
		int realDmg = calcDmg();
		enemy.health -= realDmg;
		System.out.print(realDmg + " Damage! ");
		if (enemy.health > 0) {
			System.out.println("Enemy health: " + enemy.health + ".");
		}
		else {
			killEnemy(enemy);
			return;
		}
	}
	
	void killEnemy(Enemy enemy) {
		System.out.println("You killed the " + enemy.name + "! " + enemy.goldWorth + " gold earned.");
		enemiesKilled++;
		gold += enemy.goldWorth;
	}
	
	void checkStatus() {
		System.out.println("Health: " + health + "/" + maxHealth);
		System.out.println("Damage: " + minDmg + "-" + maxDmg);
		System.out.println("Gold: " + gold);
		System.out.println("Enemies killed: " + enemiesKilled);
	}
	
	Thread isDead = new Thread(
			new Runnable() {
				public void 	run() {
					while(run) {
						if (health <= 0) { 
							health = 0;
							System.out.println("You have died! Please Log out to play again.");
							run = false; }
					}
				}
			});
	

		
}
