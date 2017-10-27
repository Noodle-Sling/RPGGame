package my.rpggame;

public class Character {
	int maxHealth;
	static int health;
	volatile boolean run;
	/* String healthStatement;
	switch (health) {
	
	}
	 */
	int dmgDice;
	int gold = 0;
	int enemiesKilled = 0;

	Character(String input) {
		
		switch (input) {
		case "knight": 
			maxHealth=100;
			health=100;
			dmgDice=2;
			System.out.println("Congratulations, you have chosen knight!");
			break;
		case "mage": 
			maxHealth=50;
			health=50;
			dmgDice=3;
			System.out.println("Congratulations, you have chosen mage!");
			break;
		default: 
			System.out.println("You didn't select a character.");
			break; } 
		}
	
	void attack(Enemy enemy) {
		for (int i = 0; i < dmgDice; i++) {
			int realDmg = 1 + (int)(5 *Math.random());
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
		if (enemy.health > 0) {
			health -= enemy.dmg;
			System.out.println("Enemy does " + enemy.dmg + " damage. Player health: " + Character.health + "." );
		}
	}
	
	void killEnemy(Enemy enemy) {
		System.out.println("You killed the " + enemy.name + "! " + enemy.goldWorth + " gold earned.");
		enemiesKilled++;
		gold += enemy.goldWorth;
	}
	
	void checkStatus(Character player) {
		System.out.println("Health: " + player.health + "/" + player.maxHealth);
		System.out.println("Number of damage die rolls: " + player.dmgDice);
		System.out.println("Gold: " + player.gold);
		System.out.println("Enemies killed: " + player.enemiesKilled);
	}
	
	Thread isDead = new Thread(
			new Runnable() {
				public void 	run() {
					while(run) {
						if (health <= 0) { 
							System.out.println("You have died! Please Log out to play again.") ;
							run = false ; }
					}
				}
			});
	

		
}
