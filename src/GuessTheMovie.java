import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.util.ArrayList;

public class GuessTheMovie {

    public static void main (String[] args) throws Exception {

        int arraySize;
        int wrongCount = 0;
        ArrayList<String> listOfTitles;

        File file = new File("movies.txt");

        listOfTitles = createArrayListOfTitles(file);
        arraySize = listOfTitles.size();

        Random randomNum = new Random();
        int low = 0;
        int high = arraySize;
        int randomResult = randomNum.nextInt(high - low);

        //---- testing random number match title number in txt file
        //System.out.println("Random number = " + randomResult);

        String randomMovie = listOfTitles.get(randomResult - 1) + 1;  // +1 to avoid 0

        //---- testing random movie output
        //System.out.println("Random movie title: " + randomMovie);

        System.out.println("Length of the movie title: " + randomMovie.length());

        System.out.println("\nGuess this title: " + maskMovieTitle(randomMovie));

        String guessesSoFar = " ";
        String wrongGuesses = new String();
        Scanner userInput = new Scanner(System.in);
        String updatedWord;

        //let user guess until they guess 10 times wrong
        while(wrongCount < 10)
        {
            System.out.println("\nGuess a letter: ");
            String letter = userInput.next();

            if(letter.length() > 1) {
                System.out.println("You can only guess one letter! TRY AGAIN.");
            }

            else {
                if (randomMovie.contains(letter)) {
                    //update guessesSoFar with new letter
                    guessesSoFar += letter;
                    //print new masked word with correct guessed letters uncovered
                    updatedWord = randomMovie.replaceAll(
                            "[^"+ guessesSoFar +"]","-");
                    System.out.println(updatedWord);

                    System.out.println("You have guessed (" + wrongCount
                            + ") wrong letter(s): " + wrongGuesses);

                    if(updatedWord.equals(randomMovie)) {
                        System.out.println("*** CONGRATS - You WON!!!! ***");
                        return;
                    }
                }
                else {
                    System.out.println("Letter " +  letter + " is a wrong guess.");
                    wrongCount ++;
                    wrongGuesses = wrongGuesses + " " + letter;
                    System.out.println("You have guessed (" + wrongCount + ") wrong letter(s)." + wrongGuesses);
                }
            }
        }
        System.out.println("*** Sorry you ran out of guesses.  Try again next time. ***");
    }


    //--------------HELPER METHODS----------------
    public static String maskMovieTitle(String movieTitle) {
        //creates the word to be guessed masked by "_"
        String maskedWord = new String();
        //maskedWord = movieTitle.replaceAll(".", "-");
        for( int i = 0; i < movieTitle.length(); i++)
        {
            maskedWord = maskedWord + "-";
        }
        return maskedWord;
    }

    public static ArrayList<String> createArrayListOfTitles(File file) throws FileNotFoundException {
        ArrayList<String> movieArrayList = new ArrayList<>();
        Scanner inputScanner = new Scanner(file);
        //adds every title of movie into movieArrayList and keeps track of count of items in arrayList
        while(inputScanner.hasNextLine())
        {
            String line = inputScanner.nextLine();
            movieArrayList.add(line);
        }
        return movieArrayList;
    }
}
