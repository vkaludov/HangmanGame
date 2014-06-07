import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class HangmanMain {
	public static void main(String[] args) {		

		StartTheGame();
	}

	public static void StartTheGame() {		
		
		/*
		 * Clears the console
		 */
		final String ANSI_CLS = "\u001b[2J";
        final String ANSI_HOME = "\u001b[H";
        System.out.print(ANSI_CLS + ANSI_HOME);
        System.out.flush();
        
		System.out.println("********Welcome to Hangman********");
		System.out.println("****May the FORCE be with you!****");
		System.out.println();
		System.out.println("You can choose one of the following categories: ");
		System.out.println();
		System.out.println("1. Countries");
		System.out.println("2. Famous People");
		System.out.println("3. Companies");
		System.out.println("4. Foods");
		System.out.println("5. Animals");
		System.out.println();
		System.out.print("Choose a category (1-5): ");

		Scanner input = new Scanner(System.in);
		int category = input.nextInt();

		// Calling getRandomWord(); method		
		String[] randomWord = getRandomWord(category);
		
		/* 
		 * Calling the Choose Difficulty method which will
		 * start the Game, by directing us to the HangmanBody class. 
		 */
		
		chooseDifficulty(randomWord);
	}
	
	public static String[] getRandomWord(int category) {
		String[] random = new String[2];

		// Calling getWords(); method
		Map<String, String> words = getWords(category);

		// Getting a random entry from the Map.
		Object[] crunchifyKeys = words.keySet().toArray();
		Object key = crunchifyKeys[new Random().nextInt(crunchifyKeys.length)];
		random[0] = (String) key;
		random[1] = (String) words.get(key);

		// Returning random word and hint in String array
		return random;

	}

	public static Map<String, String> getWords(int category) {
		Map<String, String> dictionary = new HashMap<String, String>();
		String link = "";
		switch (category) {
		case 1:
			link = "Countries.txt";
			break;
		case 2:
			link = "FamousPeople.txt";
			break;
		case 3:
			link = "Companies.txt";
			break;
		case 4:
			link = "Foods.txt";
			break;
		case 5:
			link = "Animals.txt";
			break;
		default:
			break;
		}

		File file = new File(link);

		try {
			Scanner sc = new Scanner(file);

			while (sc.hasNextLine()) {
				String word = sc.nextLine();
				String hint = sc.nextLine();
				dictionary.put(word, hint);
				word = sc.nextLine();// reading the empty line
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Returning map with all words from the category file
		return dictionary;
	}

	public static void chooseDifficulty(String[] randomWord) {

		System.out.println();
		System.out.print("Choose a difficulty(1-3): ");

		Scanner input = new Scanner(System.in);
		int difficulty = input.nextInt();
		System.out.println();

		switch (difficulty) {
		case 1:
			
			HangmanBody.LoadTheGameScreen(difficulty, randomWord);
			break;
		case 2:
			System.out
					.println("You chose difficulty 2: You have 7 tries to guess the word. "
							+ "You will also have a hint for the word that you have to guess.");
			System.out.println();
			HangmanBody.LoadTheGameScreen(difficulty, randomWord);
			break;
		case 3:
			System.out
					.println("You chose difficulty 3: You have 5 tries to guess the word, "
							+ "otherwise you lose.");			
			System.out.println();
			HangmanBody.LoadTheGameScreen(difficulty, randomWord);
			break;
		default:
			System.out
					.println("Invalid choice! Please input a number from 1-3 !");
			break;
		}
	}
}