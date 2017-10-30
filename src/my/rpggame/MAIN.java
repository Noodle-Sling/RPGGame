package my.rpggame;
import java.util.Scanner;

public class MAIN {
	static String input = "" ;
	int gameState = 0;
	public static void main(String[] args) {
		
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
