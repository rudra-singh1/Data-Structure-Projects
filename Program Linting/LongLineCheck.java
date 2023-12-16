package Program Linting;

import java.util.*;

// This class implements a check interface to check for the presence of a given 
// line length being 100 characters or greater.
public class LongLineCheck implements Check{
    // Behavior: 
    //   - Inspects a given line of code to identify occurrences of the 
    //     line length being at 100 characters or more and flags an error if found.
    // Parameters:
    //   - line: a String representing the line of code to be evaluated
    //   - lineNumber: a integer representing the line number in the file being checked
    // Returns:
    //   - Optional<Error>: returns an optional object containing an error if line length 
    //     is equal to or exceeds 100 characters. Otherwise, returns an empty Optional object.

    public Optional<Error> lint(String line, int lineNumber){
        String messageToReturn = "Line length exceeds 100 characters";
        if (line.length() >= 100){
            return Optional.of(new Error(1, lineNumber, messageToReturn));
        }
        return Optional.empty();
    }
}