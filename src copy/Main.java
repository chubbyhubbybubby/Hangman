import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException { //The main method is where the main game occurs
        try {
            File file = new File("Wordbank.txt");
            System.out.println("Welcome to Hangman");
            String word = getRandomword(file);

            char[] charArray = word.toCharArray();
            char[] dash = new char[charArray.length];
            /*This is the array that is used for conceal the word as the player guesses the correct letter, the
            letter will replace the dash at the appropriate index */

            for (int i = 0; i < dash.length; i++) {
                dash[i] = '-';
            }

            int lives = 7;
            String guess = null;
            boolean right = false;
            int win = 0; /*The code will add one to win everytime a letter is correct. When win is equal to the
            length of the word, the player wins.*/

            String lettersguessed = "";//This will keep track of all the letter guessed, and is used to change the second variable

            boolean second = false; //This variable is used to check whether or not a letter has been repeated guess

            while (lives > 0) { // uses a while loop to see if you still have lives
                Scanner guessing = new Scanner(System.in);

                System.out.println("Remaining lives: " + lives);

                System.out.println("Guess a letter");

                System.out.println("Letters guessed: " + lettersguessed);

                System.out.println(dash);

                printhangman(lives);

                guess = guessing.nextLine();
                guess = guess.toUpperCase();

                for (int i = 0; i < lettersguessed.length(); i++){
                    if (lettersguessed.charAt(i)==guess.charAt(0)){
                        second = true;
                    }
                }


                if (guess.length() > 1) {
                    System.out.println("Too long, guess again.");
                }
                else if (second == true){
                    if (word.contains(guess)) {
                        System.out.println("You already guessed this letter, try again. ");
                    }
                    else{
                        System.out.println("Try again");
                        lives--;
                        if (lives==0){
                            printhangman(lives);
                            System.out.println("Game over");
                            System.out.println("The word was "+word);
                            System.exit(0);
                        }
                    }
                    second = false;
                }

                else {
                    guess = guess.toUpperCase();
                    char character = guess.charAt(0);
                    lettersguessed += character+", ";

                    for (int i = 0; i < charArray.length; i++) {
                        if (charArray[i] == character) {
                            dash[i] = character;
                            right = true;
                        }

                    }

                    if (!right) {
                        lives--;

                        if (lives != 0) {
                            System.out.println("False, try again");
                        }
                        else {
                            System.out.println("Game over. ");
                            System.out.println("The word was " + new String(charArray));
                            printhangman(lives);
                            System.exit(0);
                        }
                    }
                    else {
                        System.out.println("You got it right.");
                        right = false;
                    }
                }

                for (int i = 0; i < dash.length; i++) {
                    if (dash[i] != '-') {
                        win++;
                    }
                }

                if (win == dash.length) {
                    System.out.println("You win!");
                    System.out.println("The word was " + word);
                    System.exit(0);
                }
                else {
                    win = 0;
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Error: File not found");
        }
    }

    public static String getRandomword(File file) throws FileNotFoundException {
        int count = 0;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                count++;
            }
        }

        Random rand = new Random();//Generates a random number
        int int_random = rand.nextInt(count) + 1; //The limit of the number is the amount of lines that the file has
        String line = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            for (int i = 1; i <= int_random; i++) {
                line = reader.readLine(); //The string line is equal to whatever line we are in the file
            }
        } catch (IOException e) {
            System.out.println("Error");
        }

        return line;
    }
    public static void printhangman(int lives){ // Method used to print hangman figure based on lives remaining.
        if (lives == 7){
            System.out.println("_______");
            System.out.println("|\n|\n|\n|\n|");
        }
        else if (lives == 6){
            System.out.println("_______");
            System.out.println("|     |\n|\n|\n|\n|");

        }
        else if (lives == 5){
            System.out.println("_______");
            System.out.println("|     |\n|     O\n|\n|\n|");

        }
        else if (lives == 4){
            System.out.println("_______");
            System.out.println("|     |\n|     O\n|     |\n|\n|");

        }
        else if (lives == 3){
            System.out.println("_______");
            System.out.println("|     |\n|     O\n|    /|\n|\n|");

        }
        else if (lives == 2){
            System.out.println("_______");
            System.out.println("|     |\n|     O\n|    /|\\ \n|\n|");

        }
        else if (lives == 1){
            System.out.println("_______");
            System.out.println("|     |\n|     O\n|    /|\\ \n|    /\n|");

        }
        else{
            System.out.println("_______");
            System.out.println("|     |\n|     O\n|    /|\\ \n|    / \\ \n|");

        }
    }
}