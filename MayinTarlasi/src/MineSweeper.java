import java.util.Arrays;
import java.util.Scanner;
public class MineSweeper {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int row, column;

        System.out.println("Please specify game board size");
        System.out.print("Number of rows: ");
        row = input.nextInt();

        System.out.print("Number of columns: ");
        column = input.nextInt();

        int [][] gameBoard = new int[row][column];

        int mineLocRow = Math.round(Math.random());

        System.out.print("Game board size is specified " + row + "x" + column);
    }
}
