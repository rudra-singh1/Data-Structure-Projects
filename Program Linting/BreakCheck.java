package Program Linting;

import java.util.*;
// This class implements a check interface to check
// for the presence of the 'break' keyword outside of single-line Java comments.
public class BreakCheck implements Check {
    // Behavior: 
    //   - Inspects a given line of code to identify occurrences of the 
    //     'break' keyword outside of single-line comments and flags an error if found.
    // Parameters:
    //   - line: a String representing the line of code to be examined for 'break' keyword
    //   - lineNumber: an integer representing the number corresponding to the line being checked
    // Returns:
    //   - Optional<Error>: returns an optional object containing an error if 'break' is found 
    //     outside of a single-line comment. Otherwise, returns an empty Optional object.
    public Optional<Error> lint(String line, int lineNumber) {
        int commentIndex = line.indexOf("//");
        int breakIndex = line.indexOf("break");
        String messageReturn = "Line contains 'break' outside of a single-line comment";
        
        if (breakIndex != -1 && (commentIndex == -1 || breakIndex < commentIndex)) {
            return Optional.of(new Error(2, lineNumber, messageReturn));
        }
        return Optional.empty();
    }
}