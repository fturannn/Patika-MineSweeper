import java.util.Arrays;
import java.util.Scanner;
public class MineSweeper {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int row, column, guessedRow, guessedColumn;

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
        placer(row, column, placedBoard); // Places mines to the Game Board and shows them
        boardMaker(row, column, gameBoard); // Builds hidden Game Board
        while (true){
            System.out.print("Enter row: ");
            guessedRow = input.nextInt();

            System.out.print("Enter Column: ");
            guessedColumn = input.nextInt();

            if(placedBoard[guessedRow - 1][guessedColumn - 1].equals("-")) {
                writePoint(row, column, guessedRow, guessedColumn, placedBoard, gameBoard);
            } else {
                System.out.println("Game Over!");
                break;
            }
        }
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

    static void writePoint(int row, int column, int guessedRow, int guessedColumn, String [][] placedBoard, String [][] gameBoard) {
        int r = guessedRow - 1;
        int c = guessedColumn - 1;
        int count = 0;
            if (r - 1 >= 0 && c - 1 >= 0 && placedBoard[r - 1][c - 1].equals("*")) {
                count++;
            }
            if (r - 1 >= 0 && placedBoard[r - 1][c].equals("*")) {
                count++;
            }
            if (r - 1 >= 0 && c + 1 < column - 1 && placedBoard[r - 1][c + 1].equals("*")) {
                count++;
            }
            if (c - 1 >= 0 && placedBoard[r][c - 1].equals("*")) {
                count++;
            }
            if (c + 1 < column - 1 && placedBoard[r][c + 1].equals("*")) {
                count++;
            }
            if (c - 1 >= 0 && r + 1 < row - 1 && placedBoard[r + 1][c - 1].equals("*")) {
                count++;
            }
            if (r + 1 < row - 1 && placedBoard[r + 1][c].equals("*")) {
                count++;
            }
            if (r + 1 < row - 1 && c + 1 < column - 1 && placedBoard[r + 1][c + 1].equals("*")) {
                count++;
            }
        gameBoard[r][c] = String.valueOf(count);
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(gameBoard[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("==================================");
    }
}
