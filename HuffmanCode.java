import java.io.*;
import java.util.*;

//This class creates a Binary Search Tree called 
//HuffmanTree used for lossless compression
public class HuffmanCode {
    //Fields
    private HuffmanNode root;

    //Behavior:
    //  -Constructs a HuffmanCode object given occurence frequency of characters
    //  -if frequency <= 0, excludes those characters in the HuffmanCode object
    //Parameters:
    //  -frequencies: array of frequencies where frequences[i] is the count 
    //   of the character with ASCII value i
    public HuffmanCode(int[] frequencies) {
        Queue<HuffmanNode> pq = new PriorityQueue<>();
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > 0) {
                pq.add(new HuffmanNode(frequencies[i], (char) i));
            }
        }
        root = buildTree(pq);
    }

    //Behaviors:
    //  -Constructs the Huffman tree given a queue of HuffmanNodes
    //Parameters:
    //  -pq: Priority queue containing HuffmanNodes representing characters and their 
    //   frequencies
    //Returns:
    //  -HuffmanNode: the root node of the Huffman tree
    private HuffmanNode buildTree(Queue<HuffmanNode> pq) {
        while (pq.size() > 1) {
            HuffmanNode left = pq.remove();
            HuffmanNode right = pq.remove();
            HuffmanNode newNode = new HuffmanNode(left.frequency + right.frequency, 
            (char)0); 
            newNode.left = left;
            newNode.right = right;
            pq.add(newNode);
        }
        return pq.remove();
    }

    //Behavior:
    // -Constructs a HuffmanCode object by reading in a previously constructed code from a 
    // .code file
    // -Scanner contains data encoded in legal, valid standard format
    // -format will contain pairs of lines
    // -first line in each pair will contain the ASCII code of the character
    // -second line will contain the Huffman encoding for that character.
    //Parameter:
    //  -input: Scanner object representing file formatted in integer, binary path pairs
    public HuffmanCode(Scanner input) {
        HuffmanNode root = new HuffmanNode(0); 
        while (input.hasNextLine()) {
            int frequency = Integer.parseInt(input.nextLine());
            String code = input.nextLine();
            HuffmanNode current = root;
            for (int i = 0; i < code.length()-1; i++) {
                char bit = code.charAt(i);
                if (bit == '0') {
                    if (current.left == null) {
                        current.left = new HuffmanNode(0); 
                    }
                    current = current.left;
                } else {
                    if (current.right == null) {
                        current.right = new HuffmanNode(0); 
                    }
                    current = current.right;
                }
            }

            if(code.charAt(code.length()-1) == '1'){
                current.right = new HuffmanNode(0, (char) frequency); 
            } else{
                current.left = new HuffmanNode(0, (char) frequency); 
            }
        }
        this.root = root;
    }

    //Behavior:
    // -Stores the current Huffman Code to a .code file in legal, valid standard format
    // -format will contain pairs of lines
    // -first line in each pair will contain the ASCII code of the character
    // -second line will contain the Huffman encoding for that character.
    // Parameters:
    //  - output: PrintStream object representing file we are saving to
    public void save(PrintStream output) {
        saveHelper(root, "", output);
    }

    //Behavior:
    //  -Saves the ASCII code and its corresponding binary path pairs to a .code file.
    // Parameters:
    //  - node: HuffmanNode object representing current node
    //  - code: String representing current path
    //  - output: PrintStream object reprsenting file we are saving to
    private void saveHelper(HuffmanNode node, String code, PrintStream output) {
        if (node.left == null && node.right == null) {
            output.println((int) node.character); // Output character as integer
            output.println(code);
        } else {
            saveHelper(node.left, code + "0", output);
            saveHelper(node.right, code + "1", output);
        }
    }

    // Behavior:
    // -Takes a compressed message of bits and converts them to characters
    // Parameters:
    //   - input: a BitInputStream object containing the compressed message
    //   - output: a PrintStream object to write translated message to
    public void translate(BitInputStream input, PrintStream output) {
        HuffmanNode current = root;
        while (input.hasNextBit()) {
            int bit = input.nextBit();
            if (bit == 0) {
                current = current.left;
            } else {
                current = current.right;
            }
            if (current.left == null && current.right == null) {
                output.write(current.character);
                current = root;
            }
        }
    }

    // A comparable node in the Huffman Tree which holds the frequency and character
    private static class HuffmanNode implements Comparable<HuffmanNode> {
        //Fields
        public final int frequency;
        public final char character;
        public HuffmanNode left;
        public HuffmanNode right;

        //Behavior:
        //  -Creates a node with a given frequency and character
        //Parameters:
        //  -frequency: int representing frequency of character
        //  -character: char representing character represented by this node
        public HuffmanNode(int frequency, char character) {
            this.frequency = frequency;
            this.character = character;
            this.left = null;
            this.right = null;
        }

        //Behavior:
        //  -Creates a node with a given frequency and ascii value 0
        //Parameters:
        //  -freq: int representing frequency of character
        public HuffmanNode(int freq) {
            this(freq, (char) 0);
        }

        //Behavior:
        //  -Compares this node to the other node
        //  -Returns a negative number if this node's frequency is less than the other
        //  -Returns a positive number if this node's frequency is greater than the other
        //  -Returns 0 if they're equal
        //Parameter:
        //  -other: HuffmanNode representing node to compare this with
        //Return:
        //  -int: difference in frequencies
        public int compareTo(HuffmanNode other) {
            return this.frequency - other.frequency;
        }
    }
}
