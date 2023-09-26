import java.util.Scanner;
public class MineSweeper {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int row, column;

        System.out.println("Welcome to Mine Sweeper Game!");
        System.out.println("Please specify game board size");

        // Collect required data from user to create Game Board size
        System.out.print("Number of rows: ");
        row = input.nextInt();

        System.out.print("Number of columns: ");
        column = input.nextInt();

        System.out.println("Game board size is specified " + row + "x" + column);

        // Create Game and Mined Boards' Matrices
        String [][] gameBoard = new String[row][column];
        String [][] placedBoard = new String[row][column];

        System.out.println("Location of the Mines");
        placer(row, column, placedBoard); // Method that places mines to the Game Board and shows them at first for code check
        boardMaker(row, column, gameBoard); // Method that builds hidden Game Board

        run(row, column, placedBoard, gameBoard); // Runs the game
    }
    
    static void placer(int row, int column, String [][] gameBoard) {
        int mines = row * column / 4;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                gameBoard[i][j] = "-";
            }
        }

        for (int i = 0; i < mines; i++) {
            int mineRow = (int) (Math.random() * row);
            int mineColumn = (int) (Math.random() * column);
            if (gameBoard[mineRow][mineColumn].equals("*")) {
                mines++;
            } else {
                gameBoard[mineRow][mineColumn] = "*";
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (gameBoard[i][j].equals("*")) {
                    System.out.print(gameBoard[i][j] + " ");
                } else {
                    gameBoard[i][j] = "-";
                    System.out.print(gameBoard[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("==================================");
    }

    static void boardMaker(int row, int column, String [][] gameBoard) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                gameBoard[i][j] = "-";
            }
        }
    }

    static void run(int row, int column, String [][] placedBoard, String [][] gameBoard) {
        Scanner input = new Scanner(System.in);
        while (true) {
            int checkGame = 0; // Game checker; if checkGame = 0 game win, else continue
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    if (placedBoard[i][j].equals("-")) {
                        checkGame++;
                    }
                }
            }

            if (checkGame == 0) {
                System.out.println("You win! Congratulations!");
                break;
            } else {
                System.out.print("Enter row: ");
                int guessedRow = input.nextInt();

                System.out.print("Enter Column: ");
                int guessedColumn = input.nextInt();

                if (placedBoard[guessedRow - 1][guessedColumn - 1].equals("-")) {
                    int r = guessedRow - 1;
                    int c = guessedColumn - 1;
                    int count = 0; // Mine counter and control section
                    if (r - 1 >= 0 && c - 1 >= 0 && placedBoard[r - 1][c - 1].equals("*")) {
                        count++;
                    }
                    if (r - 1 >= 0 && placedBoard[r - 1][c].equals("*")) {
                        count++;
                    }
                    if (r - 1 >= 0 && c + 1 <= column - 1 && placedBoard[r - 1][c + 1].equals("*")) {
                        count++;
                    }
                    if (c - 1 >= 0 && placedBoard[r][c - 1].equals("*")) {
                        count++;
                    }
                    if (c + 1 <= column - 1 && placedBoard[r][c + 1].equals("*")) {
                        count++;
                    }
                    if (c - 1 >= 0 && r + 1 <= row - 1 && placedBoard[r + 1][c - 1].equals("*")) {
                        count++;
                    }
                    if (r + 1 <= row - 1 && placedBoard[r + 1][c].equals("*")) {
                        count++;
                    }
                    if (r + 1 <= row - 1 && c + 1 <= column - 1 && placedBoard[r + 1][c + 1].equals("*")) {
                        count++;
                    }
                    gameBoard[r][c] = String.valueOf(count); //Assign the number of mines surrounding to selected index on Game Board
                    placedBoard[r][c] = String.valueOf(count); //Assign the number of mines surrounding to selected index on Placed Board

                    for (int i = 0; i < row; i++) { // Print Game Board's final situation
                        for (int j = 0; j < column; j++) {
                            System.out.print(gameBoard[i][j] + " ");
                        }
                        System.out.println();
                    }
                    System.out.println("==================================");
                } else if (!placedBoard[guessedRow - 1][guessedColumn - 1].equals("-") && !placedBoard[guessedRow - 1][guessedColumn - 1].equals("*")){
                    System.out.println("You can not enter same index!");
                } else {
                    System.out.println("Boooom!!! You stepped on a mine!! Game Over!");
                    break;
                }
            }
        }
    }
}
