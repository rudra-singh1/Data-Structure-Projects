package Program Linting;

import java.util.*;
import java.io.*;
import java.util.Scanner;  

//This class analyzes Java files based on a set of specified checks.
public class Linter {
    private List<Check> checks;

    // Behavior: 
    //   - Initializes a Linter object with a list of checks to perform.
    // Parameters:
    //   - checks: a list of Check objects containing the specified checks to be applied.
    public Linter(List<Check> checks){
        this.checks = checks;
    }

    // Behavior:
    //   - Reads a specified file and applies a series of checks to each line to detect errors in Java code.
    // Parameters:
    //   - fileName: a String representing the name of the file to be scanned for linting
    // Returns:
    //   - List<Error>: a list containing errors found during the linting process in the provided file
    // Exceptions:
    //   - FileNotFoundException: thrown if the specified file is not found during processing   
    public List<Error> lint(String fileName) throws FileNotFoundException {
        List<Error> errors = new ArrayList<>();

        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        int lineNumber = 1;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            for (Check check : checks) {
                Optional<Error> error = check.lint(line, lineNumber);
                if (error.isPresent()) {
                    errors.add(error.get()); 
                }
            }
            lineNumber++;
        }
        scanner.close();
        return errors;
    }
}
