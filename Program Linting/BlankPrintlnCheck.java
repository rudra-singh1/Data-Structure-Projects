package Program Linting;

import java.util.*;

// This class implements a check interface to identify occurrences of the pattern 
// 'System.out.println("")' in Java code lines.
public class BlankPrintlnCheck implements Check{
    // Behavior:
    //   - Checks each line for the presence of 'System.out.println("")' and flags an error if found.
    // Parameters:
    //   - line: a String representing the line of code to be evaluated
    //   - lineNumber: a String representing the line number in the file being checked
    // Returns:
    //   - Optional<Error>: returns an optional object containing an error if 
    //     'System.out.println("")' is found; otherwise, returns an empty Optional.

    public Optional<Error> lint(String line, int lineNumber){
        int counter = line.indexOf("System.out.println(\"\")");
        String returnMessage = "Line contains System.out.println(\"\")";

        if(counter != -1){
            return Optional.of(new Error(3, lineNumber, returnMessage));
        }
        return Optional.empty();
    }
}
