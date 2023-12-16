package MusicPlaylist;

import java.util.Scanner;

// This class manages a music playlist.It facilitates adding songs, 
// playing songs, managing history, and interacting with the playlist.
public class MusicPlaylistMain {
    private static MusicPlaylist playlist;

    public static void main(String[] args) {
        System.out.println("Welcome to the Music Playlist!");

        playlist = new MusicPlaylist();
        Scanner scanner = new Scanner(System.in);
        boolean condition = true;

        while (condition) {
            printMenu();

            String choice = scanner.nextLine().toLowerCase();

            if (!choice.isEmpty()) {
                if ("q".equals(choice)) {
                    condition = false;
                } else {
                    processChoice(choice, scanner);
                }
            } else {
                System.out.println();
            }
        }
        scanner.close();
    }

   // Behavior: 
   //   - Displays the available options in the Music Playlist application menu.
    private static void printMenu() {
        System.out.println("(A) Add song");
        System.out.println("(P) Play song");
        System.out.println("(Pr) Print history");
        System.out.println("(C) Clear history");
        System.out.println("(D) Delete from history");
        System.out.println("(Q) Quit");
        System.out.println();
        System.out.print("Enter your choice: ");
    }


   // Behavior: 
   //   -  Processes the user's choice and performs associated actions.
   // Parameters:
   //   - choice: a String representing the user's selected menu choice.
   //   - scanner: Scanner object for user input.
    private static void processChoice(String choice, Scanner scanner) {
        if ("a".equalsIgnoreCase(choice)) {
            System.out.print("Enter song name: ");
            String songName = scanner.nextLine();
            playlist.addSong(songName);
            System.out.println("Successfully added " + songName);
            System.out.println();
            System.out.println();
        } else if ("p".equalsIgnoreCase(choice)) {
            if (playlist.getPlaylistSize() > 0) {
                playlist.playSong();
                System.out.println();
            } else {
                throw new IllegalStateException();
            }
        } else if ("pr".equalsIgnoreCase(choice)) {
            if (playlist.getHistorySize() > 0) {
                playlist.printHistory();
            } else {
                throw new IllegalStateException();
            }
            System.out.println();
        } else if ("c".equalsIgnoreCase(choice)) {
            playlist.clearHistory();
            System.out.println();
        } else if ("d".equalsIgnoreCase(choice)) {
            System.out.println("A positive number will delete from recent history.");
            System.out.println("A negative number will delete from the beginning of history.");
            System.out.print("Enter number of songs to delete: ");
            int number = Integer.parseInt(scanner.nextLine());
            playlist.deleteFromHistory(number);
            System.out.println();
        } else {
            System.out.println();
        }
    }
}
