package org.example;

import java.util.Scanner;
import java.util.Random;

public class GuessNumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome to the Guess the Number Game!");
        boolean playAgain = true;

        while (playAgain) {
            System.out.println("Select Difficulty Level:");
            System.out.println("1. Easy (1 to 50, unlimited attempts)");
            System.out.println("2. Medium (1 to 100, max 10 attempts)");
            System.out.println("3. Hard (1 to 500, max 7 attempts)");
            System.out.print("Enter your choice (1/2/3): ");
            int difficulty = scanner.nextInt();

            int maxRange = 0;
            int maxAttempts = 0;

            switch (difficulty) {
                case 1:
                    maxRange = 50;
                    maxAttempts = Integer.MAX_VALUE; // unlimited attempts for Easy
                    break;
                case 2:
                    maxRange = 100;
                    maxAttempts = 10; // 10 attempts for Medium
                    break;
                case 3:
                    maxRange = 500;
                    maxAttempts = 7; // 7 attempts for Hard
                    break;
                default:
                    System.out.println("Invalid choice. Defaulting to Easy.");
                    maxRange = 50;
                    maxAttempts = Integer.MAX_VALUE;
            }

            // Generate a random number based on difficulty
            int randomNumber = random.nextInt(maxRange) + 1;
            int attempts = 0;
            boolean guessedCorrectly = false;
            int lowerBound = 1, upperBound = maxRange;

            System.out.println("I have selected a number between 1 and " + maxRange + ". Can you guess it?");

            while (attempts < maxAttempts && !guessedCorrectly) {
                System.out.print("Enter your guess (between " + lowerBound + " and " + upperBound + "): ");
                int guess = scanner.nextInt();
                attempts++;

                if (guess == randomNumber) {
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                    guessedCorrectly = true;
                } else if (guess > randomNumber) {
                    System.out.println("Too high!");
                    upperBound = Math.min(guess - 1, upperBound); // Adjust hint range
                } else {
                    System.out.println("Too low!");
                    lowerBound = Math.max(guess + 1, lowerBound); // Adjust hint range
                }

                // Check if the player has reached the max attempts
                if (!guessedCorrectly && attempts >= maxAttempts) {
                    System.out.println("Sorry, you've reached the maximum number of attempts. The correct number was " + randomNumber);
                }
            }

            // Ask the player if they want to play again
            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");
        }

        System.out.println("Thanks for playing! Goodbye!");
        scanner.close();
    }
}
