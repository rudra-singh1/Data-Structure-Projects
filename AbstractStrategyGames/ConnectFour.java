import java.util.Scanner;

// ConnectFour class implements AbstractStrategyGame interface.
// This class represents the Connect Four game, a strategy game 
// for two players within a 7x6 grid.
public class ConnectFour implements AbstractStrategyGame {

    //Fields
    private char[][] board;
    private boolean isXTurn;

    // Behavior:
    //   - Constructs a ConnectFour instance with an initialized 
    //     game board and sets the turn to player 1.
    public ConnectFour(){
        board = new char[][]{{'-', '-', '-', '-', '-', '-', '-'},
                                 {'-', '-', '-', '-', '-', '-', '-'},
                                 {'-', '-', '-', '-', '-', '-', '-'}, 
                                 {'-', '-', '-', '-', '-', '-', '-'},
                                 {'-', '-', '-', '-', '-', '-', '-'},
                                 {'-', '-', '-', '-', '-', '-', '-'},}; 
        isXTurn = true;
    }

    // Behavior:
    //   - Provides instructions on how to play Connect Four.
    // Returns:
    //   - String: string representation of Connect Four rules
    public String instructions() {
        String result = "";
        result += "Player 1 is X and goes first. Choose where to play by entering a \n";
        result += "column number, where 0 is the leftmost column and 6 is the rightmost\n";
        result += "column. Spaces show as a - are empty. The game ends when one player marks\n";
        result += "four spaces in a row vertically, horizontally, or diagonally, in which\n";
        result += "case that player wins, or when the board is full, in which case the game\n";
        result += "ends in a tie.";
        return result;
    }

    // Behavior:
    //   - Creates a representation of the current game state.
    // Returns:
    //   - String: a string representation of the game state
    public String toString(){
        String result = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                result += board[i][j] + " ";
            }
            result += "\n";
        }
        return result;
    }

    // Behavior:
    //   - Determines if the Connect Four game has reached its conclusion
    // Returns:
    //   - boolean: boolean representation of game conclusion status
    public boolean isGameOver() {
        return getWinner() >= 0;
    }

    // Behavior:
    // - Identifies the winner, determines if the Connect Four
    // game has ended in a tie, and if game is not over.
    // Returns:
    // - int: int representation of player who won, of tie occurrence, or
    // of game not being over. The possible values are:
    // - 1: Player 1 (X) won
    // - 2: Player 2 (O) won
    // - 0: The game ended in a tie
    // - -1: The game is not over
    public int getWinner() {
        // check horizontal win
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j <= board[i].length - 4; j++) {
                if (board[i][j] != '-' &&
                        board[i][j] == board[i][j + 1] &&
                        board[i][j] == board[i][j + 2] &&
                        board[i][j] == board[i][j + 3]) {
                    int rowResult = board[i][j] == 'X' ? 1 : 2;
                    if (rowResult != 0) {
                        return rowResult;
                    }
                }
            }
        }

        // check vertical win
        for (int j = 0; j < board[0].length; j++) {
            for (int i = 0; i <= board.length - 4; i++) {
                if (board[i][j] != '-' &&
                        board[i][j] == board[i + 1][j] &&
                        board[i][j] == board[i + 2][j] &&
                        board[i][j] == board[i + 3][j]) {
                    int colResult = board[i][j] == 'X' ? 1 : 2;
                    if (colResult != 0) {
                        return colResult;
                    }
                }
            }
        }

        // check top-left to bottom-right diagonal win
        for (int i = 0; i <= board.length - 4; i++) {
            for (int j = 0; j <= board[i].length - 4; j++) {
                if (board[i][j] != '-' &&
                        board[i][j] == board[i + 1][j + 1] &&
                        board[i][j] == board[i + 2][j + 2] &&
                        board[i][j] == board[i + 3][j + 3]) {
                    int diagResult = board[i][j] == 'X' ? 1 : 2;
                    if (diagResult != 0) {
                        return diagResult;
                    }
                }
            }
        }

        // check bottom-left to top-right diagonal win
        for (int i = board.length - 1; i >= 3; i--) {
            for (int j = 0; j <= board[i].length - 4; j++) {
                if (board[i][j] != '-' &&
                        board[i][j] == board[i - 1][j + 1] &&
                        board[i][j] == board[i - 2][j + 2] &&
                        board[i][j] == board[i - 3][j + 3]) {
                    int diagResult = board[i][j] == 'X' ? 1 : 2;
                    if (diagResult != 0) {
                        return diagResult;
                    }
                }
            }
        }

        // check if board is full
        boolean isFull = true;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '-') {
                    isFull = false;
                }
            }
        }
        if (isFull) {
            return 0; // tie
        }

        return -1; // game not over
    }

    
    // Behavior:
    // - Determines which player's turn is next
    // Returns:
    // - int: int representation of the next player. The possible values are:
    // - 1: Player 1 (X) is next
    // - 2: Player 2 (O) is next
    // - -1: The game is over, no next player
    public int getNextPlayer() {
        if (isGameOver()) {
            return -1;
        }
        return isXTurn ? 1 : 2;
    }

    // Behavior:
    //   - Places the player's token with the provided choice.
    // Parameters:
    //   - input: Scanner object to get input from the user.
    // Exception:
    //   - IllegalArgumentException: thrown if the column is invalid or full
    public void makeMove(Scanner input) {
        char currPlayer = isXTurn ? 'X' : 'O';
    
        System.out.print("Column? ");
        if(!input.hasNextInt()) { //checks for invalid inputs
            input.nextLine();
            throw new IllegalArgumentException("Invalid column input");
        } 
        int col = input.nextInt();
    
        makeMove(col, currPlayer);
        isXTurn = !isXTurn;
    }

    // Behavior:
    //   - Executes a move at the specified location for a player.
    // Parameters:
    //   - col: int of the column where the player wants to make a move.
    //   - player: char representation of the player making the move.
    // Exceptions:
    //   - IllegalArgumentException: thrown if the column is invalid or full
    private void makeMove(int col, char player) {
        if (col < 0 || col >= board[0].length) {
            throw new IllegalArgumentException("Invalid column: " + col);
        }
    
        int row = findLowestEmptyRow(col);
    
        if (row == -1) {
            throw new IllegalArgumentException("Column is full: " + col);
        }
    
        board[row][col] = player;
    }
    
    // Behavior:
    //   - Finds the lowest empty row in a given column.
    // Parameters:
    //   - col: int of the column where the player wants to make a move.
    // Returns: 
    //   - int: int indicating the row index
    private int findLowestEmptyRow(int col) {
        for (int row = board.length - 1; row >= 0; row--) {
            if (board[row][col] == '-') {
                return row;
            }
        }
        return -1; 
    }
}