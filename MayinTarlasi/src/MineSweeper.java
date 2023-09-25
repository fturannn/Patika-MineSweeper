import java.util.Arrays;
import java.util.Scanner;
public class MineSweeper {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int row, column, guessRow, guessColumn;

        System.out.println("Welcome to Mine Sweeper Game!");
        System.out.println("Please specify game board size");

        // Collect required data from user to create Game Board size
        System.out.print("Number of rows: ");
        row = input.nextInt();

        System.out.print("Number of columns: ");
        column = input.nextInt();

        System.out.println("Game board size is specified " + row + "x" + column);

        char [][] gameBoard = new char[row][column];

        System.out.println("Location of the Mines");
        placer(row, column, gameBoard); // This is a method which is place mines to the Game Board
        showMines(row, column, gameBoard);
        
    }

    static void placer(int row, int column, char [][] gameBoard) {
        int numOfMines = row * column / 4;

        for (int i = 0; i < numOfMines; i++) {
            int mineRow = (int) (Math.random() * row);
            int mineColumn = (int) (Math.random() * column);
            if (gameBoard[mineRow][mineColumn] == '*') {
                numOfMines++;
            } else {
                gameBoard[mineRow][mineColumn] = '*';
            }
        }
    }

    static void showMines(int row, int column, char [][] gameBoard) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (gameBoard[i][j] == '*') {
                    continue;
                } else {
                    gameBoard[i][j] = '-';
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(gameBoard[i][j] + " ");
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
}
