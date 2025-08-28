import java.util.Scanner;
import java.util.Random;

public class NumberGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int numberToGuess = rand.nextInt(100) + 1;  // 1 to 100
        int userGuess = 0, attempts = 0;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Guess a number between 1 and 100:");

        while (userGuess != numberToGuess) {
            userGuess = sc.nextInt();
            attempts++;

            if (userGuess > numberToGuess) {
                System.out.println("Too high! Try again:");
            } else if (userGuess < numberToGuess) {
                System.out.println("Too low! Try again:");
            } else {
                System.out.println("Correct! You guessed it in " + attempts + " attempts.");
            }
        }
        sc.close();
    }
}
