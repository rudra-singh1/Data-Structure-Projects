package Absurdle;

import java.util.*;
import java.io.*;

// This class, Absurdle, implements a word-guessing game where players input 
// words to reveal patterns, represented by colored tiles. Eventually, trying to
// guess the word correctly using these colored tiles.
public class Absurdle  {
    public static final String GREEN = "🟩";
    public static final String YELLOW = "🟨";
    public static final String GRAY = "⬜";

    // [[ ALL OF MAIN PROVIDED ]]
    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to the game of Absurdle.");

        System.out.print("What dictionary would you like to use? ");
        String dictName = console.next();

        System.out.print("What length word would you like to guess? ");
        int wordLength = console.nextInt();

        List<String> contents = loadFile(new Scanner(new File(dictName)));
        Set<String> words = pruneDictionary(contents, wordLength);

        List<String> guessedPatterns = new ArrayList<>();
        while (!isFinished(guessedPatterns)) {
            System.out.print("> ");
            String guess = console.next();
            String pattern = record(guess, words, wordLength);
            guessedPatterns.add(pattern);
            System.out.println(": " + pattern);
            System.out.println();
        }
        System.out.println("Absurdle " + guessedPatterns.size() + "/∞");
        System.out.println();
        printPatterns(guessedPatterns);
    }

    // [[ PROVIDED ]]
    // Prints out the given list of patterns.
    // - List<String> patterns: list of patterns from the game
    public static void printPatterns(List<String> patterns) {
        for (String pattern : patterns) {
            System.out.println(pattern);
        }
    }

    // [[ PROVIDED ]]
    // Returns true if the game is finished, meaning the user guessed the word. Returns
    // false otherwise.
    // - List<String> patterns: list of patterns from the game
    public static boolean isFinished(List<String> patterns) {
        if (patterns.isEmpty()) {
            return false;
        }
        String lastPattern = patterns.get(patterns.size() - 1);
        return !lastPattern.contains("⬜") && !lastPattern.contains("🟨");
    }

    // [[ PROVIDED ]]
    // Loads the contents of a given file Scanner into a List<String> and returns it.
    // - Scanner dictScan: contains file contents
    public static List<String> loadFile(Scanner dictScan) {
        List<String> contents = new ArrayList<>();
        while (dictScan.hasNext()) {
            contents.add(dictScan.next());
        }
        return contents;
    }

    // Behavior:
    // - Prunes the dictionary by removing words of incorrect length.
    // Parameters:
    // - List<String> contents: the original dictionary
    // - int wordLength: the desired length of words
    // Returns:
    // - Set<String>: the pruned set of words
    // Exceptions:
    // - if wordLength is less than 1, IllegalArgumentException is thrown
    public static Set<String> pruneDictionary(List<String> contents, int wordLength) {
        if(wordLength < 1){
            throw new IllegalArgumentException();
        }

        Set<String> prunedSet = new HashSet<>();

        for (String word : contents) {
            if(word.length() == wordLength) {
                prunedSet.add(word);
            }
        }

        return prunedSet;
        
    }

    // Behavior:
    // - Records the user's guess, updates the word set, and returns the pattern.
    // Parameters:
    // - String guess: the user's guessed word
    // - Set<String> words: the current set of possible words
    // - int wordLength: the length of the target word
    // Returns:
    // - String: the pattern resulting from the guess
    // Exceptions:
    // - if the word set is empty or the guess length is incorrect, 
    //   IllegalArgumentException is thrown
    public static String record(String guess, Set<String> words, int wordLength) {
      Map<String, Set<String>> counter = new TreeMap<>();
      
      if (words.isEmpty() || guess.length() != wordLength) {
         throw new IllegalArgumentException();
      }
      
      counter = patternSearch(words, guess, counter);
      String popularPattern = "";
      int wordCount = 0;
      Set<String> val = null;

      for (String key : counter.keySet()) {
         if (counter.get(key).size() > wordCount) {
            wordCount = counter.get(key).size();
            popularPattern = key;
            val = counter.get(key);
         }
      }
      
      words.clear();
      words.addAll(val);
      
      return popularPattern;
   }
    
    // Behavior:
    // - Searches for patterns that match the guess and updates the counter.
    // Parameters:
    // - Set<String> totalWords: the set of words to search through
    // - String guess: the user's guessed word
    // - Map<String, Set<String>> counter: the pattern counter
    // Returns:
    // - Map<String, Set<String>>: the updated pattern counter
   public static Map<String, Set<String>> patternSearch(Set<String> totalWords, String guess, Map<String, Set<String>> counter){
      for (String str : totalWords) {

         String pattern = patternFor(str, guess);

         if(!counter.containsKey(pattern)) {
            Set<String> values = new TreeSet<>();
            values.add(str);
            counter.put(pattern, values);
         } else {
            Set<String> values = counter.get(pattern);
            values.add(str);
         }
      }
      return counter;
   }

    // Behavior:
    // - Generates a pattern for a word based on the guess.
    // Parameters:
    // - String word: the target word
    // - String guess: the user's guessed word
    // Returns:
    // - String: the pattern generated
    public static String patternFor(String word, String guess) {

        List<String> list = new ArrayList<>();
        Map<Character, Integer> counter = new TreeMap<>();

        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            list.add(GRAY);
            if(!counter.containsKey(letter)) {
                counter.put(letter, 0);
            }
            counter.put(letter, counter.get(letter) + 1);
        }
        
        for (int i = 0; i < word.length(); i++){
            char letterWord = word.charAt(i);
            char letterGuess = guess.charAt(i);

            Integer number = counter.get(letterWord);

            if(number > 0 && letterWord == letterGuess && number != null ){
                list.set(i, GREEN);
                counter.put(letterWord, number-1);
            }
        }
        
        for (int i = 0; i < word.length(); i++) {
            char letterWord = word.charAt(i);
            char letterGuess = guess.charAt(i);

            if(!list.get(i).equals(GREEN) && counter.get(letterGuess) != null && counter.get(letterGuess) > 0) {
                list.set(i, YELLOW);
                counter.put(letterGuess, counter.get(letterGuess) - 1);
            }
        }

        String result = "";
        for(String element : list) {
            result += element;
        }
        return result;
    }
}
