package my.rpggame;
import java.util.Scanner;

public class RPGRunner {
	public static void main(String[] args) {		
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
					System.out.println("To attack an enemy, type \"Attack.\" To check your character's status in the future, type \"Status.\"");
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
