package my.rpggame;
import java.util.Scanner;

public class MAIN {
	static String input = "" ;
	int gameState = 0;
	public static void main(String[] args) {
		/* System.out.println("Welcome to OurFirstRPG, please choose your character: Knight or Mage.");
		Scanner sc = new Scanner(System.in);
		input = sc.nextLine().toLowerCase();
		Character player = new Character (input);
		Enemy enemy = new Enemy (1+(int)(Math.random()*2));
		player.run = true;
		player.isDead.start() ;
		while (!input.equals("start") && !input.equals("quit")) { 
			System.out.println("Now that you have chosen a character, you are ready to start your journey. Type \"Start\" to begin");
			input = sc.nextLine().toLowerCase();
			if (input.equals("quit")) {
				System.out.println("Thank you for playing.") ;
				sc.close();
			};
		}
		
		System.out.println("A " + enemy.name + " appears! Attack the " + enemy.name + " by typing \"Attack\", Check your character's status by typing \"Status\".");
		while (!input.equals("quit") && player.run) {
			input = sc.nextLine().toLowerCase();
			combatActions(input, player, enemy);
		}

	}
	
	static void combatActions(String str, Character player, Enemy enemy) {
		if (str.equals("attack")) player.attack(enemy);
		if (str.equals("status")) player.checkStatus(player);
	} */
		
		System.out.println("Welcome to our first RPG! Choose your character: type \"Knight\" or \"Mage.\"");
			gameLoop();
			
	}
		
	static void gameLoop() {
		int gameState = 0;
		Scanner sc = new Scanner(System.in);
		String input;
		switch (gameState) {
		case 0:
			input = sc.nextLine().toLowerCase();
			Character player = new Character (input);
			player.run = true;
			player.isDead.start();
			System.out.println("To begin your adventure, type \"Start.\"");
			if (input.equals("start")) gameState++;
		case 1:
			System.out.println("Kill the enemy!");
			
		}
	}
}
