public class CaesarKey extends Substitution{

    //Fields
    private String key;
    private String shifter;
    private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public CaesarKey(String key) {
        if(key.length() == 0 || super.isDuplicateCheck(key) || super.isStringEncodableCheck(key)){
            throw new IllegalArgumentException();
        }

        this.key = key;
    }

    private String shiftedLetters(){
        String shiftedAlphabet = key; //BAG

        for(int i = 0; i < alphabet.length(); i++){ 
            char currentChar = alphabet.charAt(i); 
            if (key.indexOf(currentChar) == -1) {
                shiftedAlphabet += currentChar; 
            } 
        }

        return shiftedAlphabet;
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