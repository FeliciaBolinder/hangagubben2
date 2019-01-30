import java.util.Arrays;
import java.util.Scanner;

public class hangagubben2

{
    public static void main(String[] args) {
        String[] words = {"potatis", "glass", "gulleplutt"};
        //Pick random world from array
        int randomWordNumber = (int) (Math.random() * words.length);

        //Array for words that are already guessed
        char[] enteredLetters = new char[words[randomWordNumber].length()];
        int triesCount = 0;


        boolean wordIsGuessed = false;

        do {

            switch (enterLetter(words[randomWordNumber], enteredLetters)) {
                case 0:
                    triesCount++;

                    break;
                case 1:
                    triesCount++;
                    break;
                case 2:
                    break;
                case 3:
                    wordIsGuessed = true;
                    break;
            }
            //Continue as long as the word is not guessed (or you have guessed wrong to many times)
        } while (!wordIsGuessed && triesCount- findEmptyPosition(enteredLetters)<5);
        if (wordIsGuessed) {
            System.out.println("\nThe word is " + words[randomWordNumber] +
                    " You missed " + (triesCount - findEmptyPosition(enteredLetters)) +
                    " time(s)");
            System.exit(5);
        }
        else {
            System.out.print("You guessed wrong to many times. You dead m8\n");
            System.out.println("         ____________");
            System.out.println("        |      |___  |");
            System.out.println("        |      (._.) |");
            System.out.println("        |       <|>  |");
            System.out.println("        |      _/\\_ |");
            System.out.println("        |            |");
            System.out.println("         ____________");
            System.out.print("\n");

            System.exit(5);
        }
    }

    // Hint user to enter a guess letter
    //returns 0 = letter entered is not in the word (counts as guess)
    //returns 1 = letter were entered 1st time (counts as guess)
    //returns 2 = already guessed letter was REentered (doesn't count as a guess)
    //returns 3 = all letters were guessed
    public static int enterLetter(String word, char[] enteredLetters)    {

        System.out.print("(Your guess) Enter a letter ");

        //Check if all letters in the word are guesses
        if (! printWord(word, enteredLetters))
            return 3;
        System.out.print(" : ");
        Scanner input = new Scanner(System.in);
        int emptyPosition = findEmptyPosition(enteredLetters);
        char userInput = input.nextLine().charAt(0);

        //Check if letter is already guessed
        if (inEnteredLetters(userInput, enteredLetters)) {
            System.out.println(userInput + " has already been guessed");
            return 2;
        }

        //Check if letter is in the word
        else if (word.contains(String.valueOf(userInput))) {
            enteredLetters[emptyPosition] = userInput;
            return 1;
        }

        //Otherwise... (letter not in the word)
        else {

            System.out.println(userInput + " is not in the word");

            return 0;
        }
    }

    //Print a line for each letter in the word.
    public static boolean printWord(String word, char[] enteredLetters) {

        boolean asteriskPrinted = false;
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);

            // If a letter in the word is guesses - replace line with the letter
            if (inEnteredLetters(letter, enteredLetters))
                System.out.print(letter);
            else {
                System.out.print('-');
                asteriskPrinted = true;
            }
        }
        return asteriskPrinted;
    }


    public static boolean inEnteredLetters(char letter, char[] enteredLetters) {
        return new String(enteredLetters).contains(String.valueOf(letter));
    }


    public static int findEmptyPosition(char[] enteredLetters) {
        int i = 0;
        while (enteredLetters[i] != '\u0000') i++;
        return i;
    }
}

