import java.util.Scanner;
public class MineSweeper { // Değerlendirme 5
    private int row;
    private int column;
    private String [][] gameBoard;
    private String [][] placedBoard;

    MineSweeper (int row, int column, String [][] gameBoard, String [][] placedBoard) {
        this.row = row;
        this.column = column;
        this.gameBoard = gameBoard;
        this.placedBoard = placedBoard;
        int mines = row * column / 4; // Değerlendirme 8: Burada toplam indeks sayısının 1/4'ü kadar mayın oluşturulur.

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                gameBoard[i][j] = "-";
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                placedBoard[i][j] = "-";
            }
        }

        for (int i = 0; i < mines; i++) { // Değerlendirme 8: Burada oluşturulan mayınlar rastgele indekslere yerleştirilir.
            int mineRow = (int) (Math.random() * row);
            int mineColumn = (int) (Math.random() * column);
            if (placedBoard[mineRow][mineColumn].equals("*")) {
                mines++;
            } else {
                placedBoard[mineRow][mineColumn] = "*";
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (placedBoard[i][j].equals("*")) {
                    System.out.print(placedBoard[i][j] + " ");
                } else {
                    placedBoard[i][j] = "-";
                    System.out.print(placedBoard[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("==================================");
    }

    void run() { // Bu metot değerlendirme 6, 9, 10, 11, 12, 13, 14 ve 15'i kapsar.
        Scanner input = new Scanner(System.in);
        while (true) {
            int checkGame = 0;
            for (int i = 0; i < this.row; i++) { // Değerlendirme 14
                for (int j = 0; j < this.column; j++) {
                    if (this.placedBoard[i][j].equals("-")) {
                        checkGame++;
                    }
                }
            }

            if (checkGame == 0) { // Değerlendirme 14: Burada tüm noktalar mayınsız bir şekilde seçilirse oyunu kazanmanın kontrolü yapılır. checkGame = 0 ise win, checkGame != 0 ise oyuna devam et.
                System.out.println("You win! Congratulations!"); // Değerlendirme 15: Burada kullanıcının oyunu kaybetme ya da kazanma durumunda uygun mesajlar kullanıcıya gösterilir.
                break;
            } else {
                System.out.print("Enter row: "); // Değerlendirme 9: Burada kullanıcıdan işaretlemek istediği satır ve sütun bilgisi alınır.
                int guessedRow = input.nextInt();

                System.out.print("Enter Column: ");
                int guessedColumn = input.nextInt();

                // Değerlendirme 10: Burada kullanıcının seçtiği nokta dizinin sınırları içerisinde olup olmadığı kontrol edilir,
                //değilse uyarı mesajı verilir ve tekrar giriş istenir.
                if (guessedRow > this.row || guessedColumn > this.column || guessedColumn < 1 || guessedRow < 1) {
                    System.out.println("You have entered beyond the boundaries of the game board!! Please enter a valid index!");
                } else if (this.placedBoard[guessedRow - 1][guessedColumn - 1].equals("-")) {
                    int r = guessedRow - 1;
                    int c = guessedColumn - 1;
                    int count = 0;
                    if (r - 1 >= 0 && c - 1 >= 0 && this.placedBoard[r - 1][c - 1].equals("*")) {
                        count++;
                    }
                    if (r - 1 >= 0 && this.placedBoard[r - 1][c].equals("*")) {
                        count++;
                    }
                    if (r - 1 >= 0 && c + 1 <= this.column - 1 && this.placedBoard[r - 1][c + 1].equals("*")) {
                        count++;
                    }
                    if (c - 1 >= 0 && this.placedBoard[r][c - 1].equals("*")) {
                        count++;
                    }
                    if (c + 1 <= this.column - 1 && this.placedBoard[r][c + 1].equals("*")) {
                        count++;
                    }
                    if (c - 1 >= 0 && r + 1 <= this.row - 1 && this.placedBoard[r + 1][c - 1].equals("*")) {
                        count++;
                    }
                    if (r + 1 <= this.row - 1 && this.placedBoard[r + 1][c].equals("*")) {
                        count++;
                    }
                    if (r + 1 <= this.row - 1 && c + 1 <= this.column - 1 && this.placedBoard[r + 1][c + 1].equals("*")) {
                        count++;
                    }
                    this.gameBoard[r][c] = String.valueOf(count); // Değerlendirme 11, 12: Buradaki kod satırları ile kullanıcı her hamle yaptığında oyun alanı güncellenir.
                    this.placedBoard[r][c] = String.valueOf(count);

                    for (int i = 0; i < this.row; i++) { // Değerlendirme 11, 12: Burada girilen noktada mayın yoksa etrafındaki mayın sayısı veya 0 değeri yerine yazılır.
                        for (int j = 0; j < this.column; j++) {
                            System.out.print(this.gameBoard[i][j] + " ");
                        }
                        System.out.println();
                    }
                    System.out.println("==================================");
                } else if (!this.placedBoard[guessedRow - 1][guessedColumn - 1].equals("-") && !this.placedBoard[guessedRow - 1][guessedColumn - 1].equals("*")){
                    System.out.println("You can not enter same index!");
                } else { // Burada kullanıcı mayına bastığında oyunu kaybedecek şekilde kontrol yapılır.
                    System.out.println("Boooom!!! You stepped on a mine!! Game Over!"); // // Değerlendirme 15: Burada kullanıcının oyunu kaybetme ya da kazanma durumunda uygun mesajlar kullanıcıya gösterilir.
                    break;
                }
            }
        }
    }
}
