//CaesarShift class extends Substitution class. 
//CaesarShift performs a shift-based encryption on encodable characters.
//Each character is shifted to the right by a specified amount, 
//wrapping around if needed.
public class CaesarShift extends Substitution {
    
    //Fields
    private int shift;
    
    // Behavior:
    //   - Constructs a new CaesarShift with the provided shift value.
    // Parameters:
    //   - shift: the int amount by which to shift characters in the encodable range.
    // Exceptions: 
    //   -IllegalArgumentException: thrown if shift is less than or equal to 0,
    //    the length of the shifter doesn't match the number 
    //    of characters within cipher's encodable range, contains any duplicate 
    //    characters, or any character falls outside the encodable range
    public CaesarShift(int shift){
        if(shift <= 0){
            throw new IllegalArgumentException();
        }
        this.shift = shift;
        super.setShifter(shiftedLetters());
    }

    // Behavior:
    //   - Creates a shifter string by shifting all characters within the 
    //     encodable range to the right shift times.
    //   - If shift exceeds maximum encodable range, the replacement character 
    //     is found by looping back around to the front of the encodable range.
    // Returns:
    //   - String: the shifter string.
    private String shiftedLetters(){
        String shifterAlphabet = "";
        for(int i = super.MIN_CHAR; i <=  super.MAX_CHAR; i++){
            shifterAlphabet += (char) i;
        }

        for(int i = 0; i < shift; i++){
            shifterAlphabet = shifterAlphabet.substring(1) + 
            shifterAlphabet.substring(0,1);
        }
        return shifterAlphabet;
    }
}