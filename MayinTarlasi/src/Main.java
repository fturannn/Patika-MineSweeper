import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int row, column;

        System.out.println("Welcome to Mine Sweeper Game!");
        System.out.println("Please specify game board size");

        // Değerlendirme 7: Aşağıda dizi (matris) boyutu kullanıcı tarafından belirlenir.
        System.out.print("Number of rows: ");
        row = input.nextInt();

        System.out.print("Number of columns: ");
        column = input.nextInt();

        System.out.println("Game board size is specified " + row + "x" + column);
        String [][] gameBoard = new String[row][column];
        String [][] placedBoard = new String[row][column];

        MineSweeper newGame = new MineSweeper(row, column, gameBoard, placedBoard);
        newGame.run();
    }
}
