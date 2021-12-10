package GuessingGame;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner myScanner = new Scanner(System.in);
        int randomNumberUpperLimit = 100;
        int numberOfGuesses = 0;

        Random random = new Random();
        int randomNumber = random.nextInt(randomNumberUpperLimit);

        System.out.println("Hi, what is your name?");
        String userName = myScanner.next();

        boolean startGame;

        do{
            System.out.println("Hi " + userName + ".\nWould you like to start the game?\n   1. YES\n   2. NO ");
            startGame = (myScanner.next().equals("1"));
        }while (!startGame);

        System.out.println("I have chosen a number between 0 and " + randomNumberUpperLimit + ". \nTry to guess it!");
        int userGuess = myScanner.nextInt();

        while (userGuess != randomNumber && numberOfGuesses < 5) {
            if (userGuess < randomNumber){
                System.out.println("The number you guessed is too low.");
            }
            else{
                System.out.println("The number you guessed is too high.");
            }
            userGuess = myScanner.nextInt();
        }

        System.out.println("I had guessed " + randomNumber + ".");
        if (numberOfGuesses < 5){
            System.out.println("Congratulations,  you got it");
        }
        else{
            System.out.println("You lost...");
        }


    }
}

