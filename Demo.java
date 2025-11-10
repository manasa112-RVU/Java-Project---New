import java.util.InputMismatchException;
import java.util.Scanner;

public class Demo {

    private static String player1;
    private static String player2;
    private static char player1Symbol;
    private static char player2Symbol;


    // The grid where the game is played, represented as a 2D array
    private static char[][] grid = new char[3][3];


    // The current player
    private static char currentPlayer;
    
    //  Scoreboard variables
    private static int player1Score = 0;
    private static int player2Score = 0;
    private static int tieScore = 0;

    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //  Outer loop for replaying the game
        boolean playAgain = true;
        while (playAgain) {
            resetGrid();
            chooseSymbols(scanner);

            currentPlayer = player1Symbol;

            while (true) {
                printGrid();
                readMove(scanner);

                if (isGameOver()) {
                    printGrid();
                    if (hasWinner()) {
                        declareWinner();
                    } else {
                        System.out.println("It's a tie!");
                        tieScore++;
                    }

                    displayScoreboard();
                    break;
                }
                   switchPlayer();
            }

            playAgain = askReplay(scanner);
        }
         System.out.println("\nFinal Scoreboard:");
        displayScoreboard();
        System.out.println("Thank you for playing Tic Tac Toe!");
        scanner.close();
    }

           private static void resetGrid() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                     grid[i][j] = ' ';
                }
            }

            System.out.print("Enter Player 1 name: ");
            player1 = scanner.nextLine();

            System.out.print("Enter Player 2 name: ");
            player2 = scanner.nextLine();

            //  Ask Player 1 to choose X or O
            System.out.print(player1 + ", choose your symbol (X or O): ");
            player1Symbol = scanner.next().toUpperCase().charAt(0);
            player2Symbol = (player1Symbol == 'X') ? 'O' : 'X';
            System.out.println(player2 + ", your symbol is " + player2Symbol);

            // Player 1 starts the game
            currentPlayer = player1Symbol;

            // Start the game loop
            while (true) {

                printGrid();

                if (currentPlayer == player1Symbol) {
                      System.out.println(player1 + " (" + player1Symbol + "), enter your move (row, col): ");
                } else {
                      System.out.println(player2 + " (" + player2Symbol + "), enter your move (row, col): ");
                }

                int row, col;

                while (true) {
                row = scanner.nextInt();
                col = scanner.nextInt();

                if (row >= 0 && row < 3 && col >= 0 && col < 3 && grid[row][col] == ' ') {
                grid[row][col] = currentPlayer;
                break;
                } else {
                System.out.println("Invalid move! Please try again.");
                }
}

                // Check if the game is over
                if (isGameOver()) {
                   // Print the final grid
                   printGrid();

                   // Print the winner (if any)
                   if (hasWinner()) {
                      if (currentPlayer == player1Symbol) {
                         System.out.println(player1 + " wins!");
                         player1Score++;
                      } else {
                         System.out.println(player2 + " wins!");
                         player2Score++;
                      }
                   } else {
                         System.out.println("It's a tie!");
                         tieScore++;
                   }
                   displayScoreboard();

                break;
            }

            
            // Switch to the other player
            currentPlayer = (currentPlayer == player1Symbol) ? player2Symbol : player1Symbol;

        }
        //  Ask if players want to replay
            System.out.print("Do you want to play again? (yes/no): ");
            String response = scanner.next().toLowerCase();
            scanner.nextLine(); // consume newline
            if (!response.equals("yes")) {
                playAgain = false;

                // Final scoreboard before exiting
                System.out.println("\nFinal Scoreboard:");
                displayScoreboard();

                System.out.println("Thank you for playing Tic Tac Toe!");
            }
        }

        scanner.close(); // close scanner outside loop
    }


    private static void printGrid() {
        System.out.println("+---+---+---+");
        for (int i = 0; i < 3; i++) {
            System.out.print("|");
            for (int j = 0; j < 3; j++) {
                 System.out.print(" " + grid[i][j] + " |");
            }
            System.out.println();
            System.out.println("+---+---+---+");
         }
    }


    // Check if the game is over (i.e. someone has won or there are no more empty spaces)
    private static boolean isGameOver() {
        return hasWinner() || isFull();
    }

    // Check if there is a winner (i.e. someone has three marks in a row)
    private static boolean hasWinner() {
        // Check for horizontal wins
        for (int i = 0; i < 3; i++) {
            if (isRowWin(i)) {
                return true;
            }
        }

        // Check for vertical wins
        for (int i = 0; i < 3; i++) {
            if (isColWin(i)) {
                return true;
            }
        }

        // Check for diagonal wins
        if (isDiag1Win() || isDiag2Win()) {
            return true;
        }

        // If none of the above checks passed, there is no winner
        return false;
    }

    // Check if the given row has a winning combination
    private static boolean isRowWin(int row) {
        return (grid[row][0] != ' ' && grid[row][0] == grid[row][1] && grid[row][1] == grid[row][2]);
    }

    // Check if the given column has a winning combination
    private static boolean isColWin(int col) {
        return (grid[0][col] != ' ' && grid[0][col] == grid[1][col] && grid[1][col] == grid[2][col]);
    }

    // Check if the first diagonal has a winning combination
    private static boolean isDiag1Win() {
        return (grid[0][0] != ' ' && grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]);
    }

    // Check if the second diagonal has a winning combination
    private static boolean isDiag2Win() {
        return (grid[0][2] != ' ' && grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]);
    }

    // Check if there are no more empty spaces in the grid
    private static boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private static void displayScoreboard() {
        System.out.println("\n===== SCOREBOARD =====");
        System.out.println(player1 + " (" + player1Symbol + "): " + player1Score);
        System.out.println(player2 + " (" + player2Symbol + "): " + player2Score);
        System.out.println("Ties: " + tieScore);
        System.out.println("======================\n");
    }
}

    

