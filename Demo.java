
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

            System.out.print("Enter Player 1 name: ");
            player1 = scanner.nextLine();

            System.out.print("Enter Player 2 name: ");
            player2 = scanner.nextLine();
        
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

                }

                switchPlayer();
            }

            System.out.print("Play again? (yes/no): ");
            String response = scanner.next().toLowerCase();

            if (!response.equals("yes")) {
                playAgain = false;
                System.out.println("\nFinal Scoreboard:");
                displayScoreboard();
                System.out.println("Thank you for playing!");
            }
        }

        scanner.close();
    }

    private static void resetGrid() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    private static void chooseSymbols(Scanner scanner) {
        while (true) {
            System.out.print(player1 + ", choose your symbol (X or O): ");
            char choice = scanner.next().toUpperCase().charAt(0);

            if (choice == 'X' || choice == 'O') {
                player1Symbol = choice;
                player2Symbol = (choice == 'X') ? 'O' : 'X';
                System.out.println(player2 + ", your symbol is " + player2Symbol);
                break;
            } else {
                System.out.println("Invalid choice. Choose X or O only.");
            }
        }
    }

    private static void printGrid() {
        System.out.println("\nCurrent Board:");
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

    private static void readMove(Scanner scanner) {
        int row, col;

        while (true) {
            if (currentPlayer == player1Symbol) {
                System.out.print(player1 + " (" + currentPlayer + ") enter row & col: ");
            } else {
                System.out.print(player2 + " (" + currentPlayer + ") enter row & col: ");
            }

            row = scanner.nextInt();
            col = scanner.nextInt();

            if (row >= 0 && row < 3 && col >= 0 && col < 3) {
                if (grid[row][col] == ' ') {
                    grid[row][col] = currentPlayer;
                    break;
                } else {
                    System.out.println("Cell already taken! Try again.");
                }
            } else {
                System.out.println("Invalid position! (0-2 allowed). Try again.");
            }
        }
    }

    private static boolean isGameOver() {
        return hasWinner() || isFull();
    }

    private static boolean hasWinner() {

        // Rows
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] != ' ' &&
                grid[i][0] == grid[i][1] &&
                grid[i][1] == grid[i][2])
                return true;
        }

        // Columns
        for (int i = 0; i < 3; i++) {
            if (grid[0][i] != ' ' &&
                grid[0][i] == grid[1][i] &&
                grid[1][i] == grid[2][i])
                return true;
        }

        // Diagonal 1
        if (grid[0][0] != ' ' &&
            grid[0][0] == grid[1][1] &&
            grid[1][1] == grid[2][2])
            return true;

        // Diagonal 2
        if (grid[0][2] != ' ' &&
            grid[0][2] == grid[1][1] &&
            grid[1][1] == grid[2][0])
            return true;

        return false;
    }

    private static boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == ' ')
                    return false;
            }
        }
        return true;
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == player1Symbol) ? player2Symbol : player1Symbol;
    }

    private static void displayScoreboard() {
        System.out.println("\n===== SCOREBOARD =====");
        System.out.println(player1 + " (" + player1Symbol + "): " + player1Score);
        System.out.println(player2 + " (" + player2Symbol + "): " + player2Score);
        System.out.println("Ties: " + tieScore);
        System.out.println("======================\n");
    }
}

//Game Over
    

