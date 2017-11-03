package my.rpggame;
import java.util.Scanner;

public class RPGRunner {
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

	} */
		
		Scanner sc = new Scanner(System.in);
		String input = "";
		System.out.println("Welcome to our first RPG! Type \"Start\" to begin.");
		while (!input.equals("start")) {
			input = sc.nextLine().toLowerCase();
			if (input.equals("start")) {
				while (!input.equals("knight") && !input.equals("mage")) {
					System.out.println("Choose your character. Type \"Knight\" or \"Mage.\"");
					input = sc.nextLine().toLowerCase();
					if (input.equals("knight") || input.equals("mage")) {
						gameLoop(input);
					}
				}
			}
			else System.out.println("Type \"Start\" to begin.");
		}
			
	}
		
	static void gameLoop(String input) {
		Character player = new Character(input);
		Scanner sc = new Scanner(System.in);
		// String input2 = "";
		int gameState = 0;
		while (gameState < 10) {
			switch (gameState) {
				case 0:
					player.checkStatus();
					player.run = true;
					player.isDead.start();
					System.out.println("Ready to go questing? \"Yes\" or \"No\"");
					while (!input.equals("yes")) {
						input = sc.nextLine().toLowerCase();
						if (input.equals("yes")) gameState++;
						else if (input.equals("no")) {
							System.out.println("Bye bye.");
							sc.close();
						}
					}
				case 1:
					Enemy enemy = new Enemy (1+(int)(Math.random()*2));
					System.out.println("To attack the enemy, type \"Attack.\" To check your character's status in the future, type \"Status.\"");
					while (enemy.health > 0) {
						input = sc.nextLine().toLowerCase();
						combatActions(input, player, enemy);
					}
			}
		}
	}
	
	static void combatActions(String str, Character player, Enemy enemy) {
		if (str.equals("attack")) {
			player.attack(enemy);
			enemy.attack(player);
		}
		if (str.equals("status")) player.checkStatus();
		// if (str.equals("item")) player.openItems();
	}
	
}

/* static double[] getCoefficients() {
	Scanner sc = new Scanner(System.in);
	String input = "";
	double[] nums = new double[3];
	int varAsk = 0;
	while (varAsk < 3) {
	switch (varAsk) {
	case 0:
		System.out.println("A?");
			input = sc.nextLine();
			if (catchInput(input)) {
				if (Double.valueOf(input) == 0) {
				System.out.println("You entered zero for A, but the leading coefficient must have a nonzero value. A has been recorded as one.");
				nums[0] = 1;
				}
				else nums[0] = Double.valueOf(input);
				varAsk++;
			}
			else System.out.println("Please enter a numerical value in decimal form.");
		break;
	case 1:
		System.out.println("B?");
			input = sc.nextLine();
			if (catchInput(input)) {
			nums[1] = Double.valueOf(input);
			varAsk++;
			}
			else System.out.println("Please enter a numerical value in decimal form.");
		break;
	case 2:
		System.out.println("C?");
			input = sc.nextLine();
			if (catchInput(input)) {
			nums[2] = Double.valueOf(input);
			varAsk++;
			}
			else System.out.println("Please enter a numerical value in decimal form.");
		break;		
}
}
	return nums;
} */
