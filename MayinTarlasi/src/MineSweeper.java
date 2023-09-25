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
        char [][] gameBoard = new char[row][column];
        char [][] placedBoard = new char[row][column];

        System.out.println("Location of the Mines");
        placer(row, column, placedBoard); // Places mines to the Game Board and shows them
        boardMaker(row, column, gameBoard); // Builds hidden Game Board
        while (true){
            System.out.print("Satır giriniz: ");
            guessedRow = input.nextInt();

            System.out.print("Sütun giriniz: ");
            guessedColumn = input.nextInt();

            writePoint(row, column, guessedRow, guessedColumn, placedBoard, gameBoard);
        }
    }
    
    static void placer(int row, int column, char [][] gameBoard) {
        int mines = row * column / 4;

        for (int i = 0; i < mines; i++) {
            int mineRow = (int) (Math.random() * row);
            int mineColumn = (int) (Math.random() * column);
            if (gameBoard[mineRow][mineColumn] == '*') {
                mines++;
            } else {
                gameBoard[mineRow][mineColumn] = '*';
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (gameBoard[i][j] == '*') {
                    System.out.print(gameBoard[i][j] + " ");
                } else {
                    gameBoard[i][j] = '-';
                    System.out.print(gameBoard[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("==================================");
    }

    static void boardMaker(int row, int column, char [][] gameBoard) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                gameBoard[i][j] = '-';
            }
        }
    }

    static void writePoint(int row, int column, int guessedRow, int guessedColumn, char [][] placedBoard, char [][] gameBoard) {
        int r = guessedRow - 1;
        int c = guessedColumn - 1;
        int count = 0;
        if (placedBoard[r][c] == '-') {
            if (placedBoard[r-1][c-1] == '*') {
                count++;
            }
            if (placedBoard[r-1][c] == '*') {
                count++;
            }
            if (c+1 < column - 1 && placedBoard[r-1][c+1] == '*') {
                count++;
            }
            if (placedBoard[r][c-1] == '*') {
                count++;
            }
            if (c+1 < column - 1 && placedBoard[r][c+1] == '*') {
                count++;
            }
            if (r+1 < row - 1 && placedBoard[r+1][c-1] == '*') {
                count++;
            }
            if (r+1 < row - 1 && placedBoard[r+1][c] == '*') {
                count++;
            }
            if (r+1 < row - 1 && c+1 < column - 1 && placedBoard[r+1][c+1] == '*') {
                count++;
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(gameBoard[i][j] + " ");
            }
            System.out.println();
        }
    }
}
