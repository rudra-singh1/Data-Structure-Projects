package TodoListManager;
import java.util.*;
import java.io.*;

// This class is tool for efficiently managing a TODO list. 
// It allows users to seamlessly add tasks, mark tasks as done, load tasks from 
// files and save their tasks to a new file. 
// Additionally, it supports an optional feature to include task deadlines. 
// It can be enabled by setting the feature flag to true. 
public class TodoListManager {
    public static final boolean EXTENSION_FLAG = false;

    public static void main(String[] args) throws FileNotFoundException {
        boolean condition = true;
        List<String> list = new ArrayList<>();

        System.out.println("Welcome to your TODO List Manager!");

        String[] options = new String[5];
        options[0] = "a";
        options[1] = "m";
        options[2] = "l";
        options[3] = "s";
        options[4] = "q";

        while (condition) {
            System.out.println("What would you like to do?");
            System.out.print("(A)dd TODO, (M)ark TODO as done, (L)oad TODOs, (S)ave TODOs, (Q)uit? ");

            Scanner input = new Scanner(System.in);
            String choice = input.nextLine();
            boolean unknown = true;

            for (int i = 0; i < options.length; i++) {
                if (options[i].equalsIgnoreCase(choice)) {
                    unknown = false;
                    if (options[i].equals("a")) {
                        addItem(input, list);
                        printTodos(list);
                    } else if (options[i].equals("m")) {
                        markItemAsDone(input, list);
                        printTodos(list);
                    } else if (options[i].equals("l")) {
                        loadItems(input, list);
                        printTodos(list);
                    } else if (options[i].equals("s")) {
                        saveItems(input, list);
                        printTodos(list);
                    } else {
                        condition = false;
                    }
                }
            }
            if (unknown) {
                System.out.println("Unknown input: " + choice);
                printTodos(list);
            }
        }
    }

    // Behavior:
    //   - Displays the current list of todo items with task numbers 
    //     and deadlines (optional feature) in the console.
    // Parameters: 
    //   - todos: a String list containing the user's todo items to be displayed 
    //     in console.
    // Returns: 
    //   - None.
    public static void printTodos(List<String> todos) {
        if(todos.isEmpty()) {
            System.out.println("Today's TODOs:");
            System.out.println("  You have nothing to do yet today! Relax!");
        } else {
            System.out.println("Today's TODOs:");
            for(int i = 0; i < todos.size(); i++) {
                System.out.println("  " + (i+1) + ": " + todos.get(i));
            }
        }
    }

    // Behavior:
    //   - Adds a user-defined todo task at a chosen position in the list of tasks, 
    //     and shifts all other tasks down.
    //   - If the feature flag is enabled (optional), this method also collects the 
    //     deadline for the todo task and accordingly adds it to the list.
    // Parameters: 
    //   - console: a scanner object enabling console-line input from the user regarding 
    //     the item to be added to the list.
    //   - todos: a String list containing the user's todo items (if any) in user dictated order.
    // Returns: 
    //   - None.
    public static void addItem(Scanner console, List<String> todos) {
        String input = "";
        String input2 = "";
        String combined = "";
        int location = 0;

        if (todos.isEmpty()) {
            System.out.print("What would you like to add? ");
            input = console.nextLine();

            if(EXTENSION_FLAG) {
                System.out.print("When is the deadline (Format: MM/DD/YYYY)? ");
                input2 = console.nextLine();
                combined = input + " + Deadline: " + input2;
            }

            if(EXTENSION_FLAG) {
                todos.add(combined);
            } else {
                todos.add(input);
            }

        } else {
            System.out.print("What would you like to add? ");
            input = console.nextLine();

            if(EXTENSION_FLAG) {
                System.out.print("When is the Deadline (Format: MM/DD/YYYY)? ");
                input2 = console.nextLine();
                combined = input + " + Deadline: " + input2;
            }

            System.out.print("Where in the list should it be (1-" + (todos.size()+1) + ")? (Enter for end): ");
            String temp = console.nextLine();   

            if(temp.equals("")) {
                if(EXTENSION_FLAG) {
                    todos.add(todos.size(), combined);
                } else {
                    todos.add(todos.size(), input);
                }
            } else {
                location = Integer.parseInt(temp) - 1;

                if(EXTENSION_FLAG) {
                    // location = Integer.parseInt(temp) - 1;
                    todos.add(location, combined);
                } else {
                    // location = Integer.parseInt(temp) - 1;
                    todos.add(location, input);
                }
            }
        } 
    }

    // Behavior:
    //   - Marks a user-specified todo task as completed and removes it from the list.
    // Parameters: 
    //   - console: a scanner object enabling console-line input from the user 
    //     regarding the item to mark an item as done.
    //   - todos: a String list containing the user's todo items (if any) in user 
    //     dictated order.
    // Returns: 
    //   - None.
    public static void markItemAsDone(Scanner console, List<String> todos) {
        String input = "";
        int location = 0;
        
        if(todos.isEmpty()) {
            System.out.println("All done! Nothing left to mark as done!");
        } else {
            System.out.print("Which item did you complete (1-" + (todos.size()) + ")? ");
            input = console.nextLine();
            location = Integer.parseInt(input) - 1;
            todos.remove(location);
        }
    }

    // Behavior:
    //   - Loads todo items from a file and replaces the current list with the loaded items.
    // Parameters: 
    //   - console: a scanner object enabling console-line input from the user 
    //     regarding the user's choice to load items from a .txt file.
    //   - todos: a String list containing the user's todo items (if any) in user 
    //     dictated order.
    // Returns: 
    //   - None.
    // Exceptions:
    //   - FileNotFoundException: Thrown if the specified file cannot be found or accessed.
    public static void loadItems(Scanner console, List<String> todos)
                                throws FileNotFoundException {
        System.out.println("File name? ");
        String fileName = console.nextLine();

        Scanner fileInput = new Scanner(new File(fileName));

        todos.clear();

        while(fileInput.hasNext()) {
            String token = fileInput.nextLine();
            todos.add(token);
        }
    }

    // Behavior:
    //   - Saves the current todo items to a file, overwriting the existing content.
    // Parameters: 
    //   - console: a scanner object enabling console-line input from the user 
    //     regarding the user's choice to save items to a .txt file.
    //   - todos: a String list containing the user's todo items (if any) in user 
    //     dictated order.
    // Returns: 
    //   - None.
    // Exceptions:
    //   - FileNotFoundException: Thrown if there are issues while creating or writing 
    //     to the specified file.
    public static void saveItems(Scanner console, List<String> todos)
                                throws FileNotFoundException {
        System.out.println("File name? ");
        String fileName = console.nextLine();

        PrintStream output = new PrintStream(new File(fileName));

        for(int i = 0; i < todos.size(); i++) {
            output.println(todos.get(i));
        }
    }
}