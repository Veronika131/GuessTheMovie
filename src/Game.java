import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Game class includes methods responsible for handling
 * a single guess or displaying the hidden movie title etc.
 *
 * A simple design would be to have at least one more class called Game that will include
 * methods responsible for handling a single guess or displaying the hidden movie title etc.

 Then have another class that contains the main method and controls the logic of
 reading the user's input and calling the methods in the Game class
 */

public class Game {

    Game(){
    }

    /**
     * read input file and add each movie into array list
     * @param movieFile .txt file of movies
     * @return ArrayList that contain list of movies from .txt input file
     * @throws FileNotFoundException
     */
    public static ArrayList<String> createMovieArrayList (File movieFile) throws FileNotFoundException {
        ArrayList<String> movieArrayList = new ArrayList<>();
        Scanner inputFileScanner = new Scanner(movieFile);

        while(inputFileScanner.hasNextLine()){
            movieArrayList.add(inputFileScanner.nextLine());
        }
        return movieArrayList;
    }

    /**
     * generates random number bound up to 25, added +1 to avoid
     * getting value 0
     * @return integer random value
     */
    public static String generateRandomMovie(File movieFile) throws FileNotFoundException {

        ArrayList<String> movieList = createMovieArrayList(movieFile);
        int lowBound = 1;
        int highBound = movieList.size();
        Random randomValue = new Random();
        int randomInt = randomValue.nextInt(highBound - lowBound + 1);  //to avoid 0 value

        System.out.println("random int = " + randomInt);
        String randomMovie = movieList.get((randomInt - 1 ));

        return randomMovie;
    }

    /**
     * prompts user to guess a letter and checks if the letter is in the movie title,
     * if so, correct letter is revealed, if not indorrect letter is added to list of
     * incorrect letters
     * @param movieTitle String of random movie title
     * @return String of updated movie title
     */
    public static String checkUsersGuess(String movieTitle){
        String guessesSoFar = " ";
        String wrongGuesses = "";
        int wrongGuessCount = 10;
        String updatedTitle = "";
        Scanner usersLetterGuess = new Scanner(System.in);

        while(wrongGuessCount > 0) {

            System.out.println("Guess a letter: ");
            String letterGuess = usersLetterGuess.next();

            if (letterGuess.length() > 1) {
                System.out.println("You can only guess one letter at a time! TRY AGAIN.");
            }
            else {
                if(movieTitle.contains(letterGuess)) {
                    guessesSoFar += letterGuess;
                    updatedTitle = movieTitle.replaceAll("[^ " + guessesSoFar + "]", "-");
                    System.out.println("You guessed right! " + updatedTitle);

                    if (updatedTitle.equals(movieTitle)) {
                        System.out.println("* * * YOU WON! CONGRATS! * * *");
                        break;
                    }
                }
                else if (wrongGuesses.contains(letterGuess)) {
                    System.out.println("You tried this letter already.");
                }
                else {
                    wrongGuesses += " " + letterGuess;
                    wrongGuessCount--;
                    System.out.println("Wrong guess! You can guess " + wrongGuessCount + " more times wrong."
                            + "\nIncorrect guesses so far: " + wrongGuesses);
                }
            }
        }

        //if(wrongGuessCount == 0){
            System.out.println("* * * GAME OVER! * * *");
        //}

        return updatedTitle;
    }

}
