public class Substitution extends Cipher{

    //Fields
    private String shifter; //aka shifted alphabet

    public Substitution(){
        this.shifter = "";
    }

    public Substitution(String shifter){
        if((shifter.length() != Cipher.TOTAL_CHARS) || (duplicateCheck(shifter)) || isStringEncodable(shifter)){
            throw new IllegalArgumentException();
        }
        this.shifter = shifter;
    }

    public void setShifter(String shifter){
        // if((shifter.length() != Cipher.TOTAL_CHARS) || (duplicateCheck(shifter) == true) || isStringEncodable(shifter)){
        //     throw new IllegalArgumentException();
        // }

        //FOXXXXX BEFORE SUBMITTING
        if(shifter.length() != Cipher.TOTAL_CHARS){
            
            System.out.println("1");
            throw new IllegalArgumentException();
        } else if (duplicateCheck(shifter) == true){
            System.out.println("2");
            throw new IllegalArgumentException();
        } else if (isStringEncodable(shifter)){
            System.out.println("3");
            throw new IllegalArgumentException();
        }

        this.shifter = shifter;
    }

    public String encrypt(String input){
        if(input == null || input.length()==0){
            throw new IllegalStateException ();
        }

        String encrypted = "";
        for (int i = 0; i < input.length(); i++) {
            char c = input.toUpperCase().charAt(i); // char c = b
            int index = (int) c - Cipher.MIN_CHAR; //66-65
            
            encrypted += shifter.charAt(index);
        }

        return encrypted;
    }

    public String decrypt(String input){
        if(input == null || input.length()==0){
            throw new IllegalStateException ();
        }

        String decrypted = "";
        for (int i = 0; i < input.length(); i++) {
            char c = input.toUpperCase().charAt(i);
            int index = shifter.indexOf(c);
            decrypted += (char) (index + MIN_CHAR);
        }
        return decrypted;
    }

    private static boolean duplicateCheck(String s){
        for (int i = 0; i < (s.length() - 1); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isStringEncodable(String input){
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            int charCode = (int) currentChar;
            
            if (charCode < Cipher.MIN_CHAR || charCode > Cipher.MAX_CHAR) {
                return true; // Found a character outside the encodable range
            }
        }
        return false; // All characters are within the encodable range
    }

    public boolean isDuplicateCheck(String s) {
        return duplicateCheck(s);
    }

    public boolean isStringEncodableCheck(String input) {
        return isStringEncodable(input);
    }
}