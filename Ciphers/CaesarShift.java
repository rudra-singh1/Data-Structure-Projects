public class CaesarShift extends Substitution {
    
    int shift;
    private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public CaesarShift(int shift){
        super();
        if(shift <= 0){
            throw new IllegalArgumentException();
        }

        this.shift = shift;
    }


    private String shiftedLetters(){
        String shifterAlphabet = alphabet;  
        for(int i = 0; i < shift; i++){
            shifterAlphabet = shifterAlphabet.substring(1) + shifterAlphabet.substring(0,1);
        }

        return shifterAlphabet;
    }


    public String encrypt(String input){
        super.setShifter(shiftedLetters());
        return super.encrypt(input);
    }

    public String decrypt(String input){
        super.setShifter(shiftedLetters());
        return super.decrypt(input);
    }
}
