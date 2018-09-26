import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class contains the main method and controls the logic
 * of reading the user's input and calling the methods in the Game class.
 */

public class Main {

    public static void main (String[] args) throws FileNotFoundException {

        File movieFile = new File("movies.txt");

        String randomMovieTitle = Game.generateRandomMovie(movieFile);
        String maskedMovieTitle = randomMovieTitle.replaceAll("[^." + " " + "]","-");

        //System.out.println("Random movie title: " + randomMovieTitle);
        System.out.println("Guess this movie title: " + maskedMovieTitle);

        //prompts user for input and checks the guessed letter against title
        Game.checkUsersGuess(randomMovieTitle);

        //System.out.println(updatedTitle);

    }

}




