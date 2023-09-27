import java.util.Scanner;
public class MineSweeper { // Değerlendirme 5: Proje, MineSweeper sınıfı içerisinde tanımlanır.
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int row, column;

        System.out.println("Welcome to Mine Sweeper Game!");
        System.out.println("Please specify game board size");

        // Değerlendirme 7: Dizi (Matris) boyutu kullanıcı tarafından aşağıdaki kod bloğunda belirlenir.
        System.out.print("Number of rows: ");
        row = input.nextInt();

        System.out.print("Number of columns: ");
        column = input.nextInt();

        System.out.println("Game board size is specified " + row + "x" + column);

        String [][] gameBoard = new String[row][column];
        String [][] placedBoard = new String[row][column];

        System.out.println("Location of the Mines");
        placer(row, column, placedBoard); // Mayınlar istenilen sayıda ve rastgele bölgelere yerleştirilir.
        boardMaker(row, column, gameBoard); // Oyun tahtası oluşturulur.

        run(row, column, placedBoard, gameBoard); // Değerlendirme 6: Bu metot ile oyun başlatılır ve kaybetme durumları kontrol edilir.
    }

    /* Değerlendirme 8: Bu metot diziye toplam indeks sayısının 1/4'ü kadar rastgele bölgelere mayın yerleştirir.
    Rastgele mayın yerleştirirken önceden mayın koyulup koyulmadığının kontrolü yapılır. Eğer bölgede mayın varsa farklı bölgeye yerleştirilir.
     */
    static void placer(int row, int column, String [][] placedBoard) {
        int mines = row * column / 4;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                placedBoard[i][j] = "-";
            }
        }

        for (int i = 0; i < mines; i++) {
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

    static void boardMaker(int row, int column, String [][] gameBoard) { // Değerlendirme 6: Oyun tahtası oluşturulur
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                gameBoard[i][j] = "-";
            }
        }
    }

    // Değerlendirme 6 + 9 + 10 + 11 + 12 + 13 + 14 + 15: Oyun başlatılır ve tüm durumların kontrolü bu metot ile sağlanır.
    static void run(int row, int column, String [][] placedBoard, String [][] gameBoard) {
        Scanner input = new Scanner(System.in);
        while (true) {
            int checkGame = 0; // Eğer checkGame = 0 ise oyun kazanılmıştır. Mayınlı harita üzerinden kontrol sağlanır.
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    if (placedBoard[i][j].equals("-")) {
                        checkGame++;
                    }
                }
            }

            if (checkGame == 0) { // Değerlendirme 14 + 15: Tüm noktalar mayınsız bir şekilde seçilirse oyunu kazanmanın kontrolü burada yapılıyor. Kazanılırsa mesaj yazdırılır.
                System.out.println("You win! Congratulations!");
                break;
            } else {
                System.out.print("Enter row: "); // Değerlendirme 9: Kullanıcıdan işaretlemek istediği satır değeri burada alınır.
                int guessedRow = input.nextInt();

                System.out.print("Enter Column: "); // Değerlendirme 9: Kullanıcıdan işaretlemek istediği sütun değeri burada alınır.
                int guessedColumn = input.nextInt();

                if (guessedRow > row || guessedColumn > column || guessedColumn < 1 || guessedRow < 1) { // Değerlendirme 10: Kullanıcının seçtiği nokta, dizinin sınırları içerisinde olup olmadığı burada kontrol edilir.
                    System.out.println("You have entered beyond the boundaries of the game board!! Please enter a valid index!");
                } else if (placedBoard[guessedRow - 1][guessedColumn - 1].equals("-")) { // Değerlendirme 11: Kullanıcı her hamle yaptığında oyun alanı burada güncelleniyor.
                    int r = guessedRow - 1;
                    int c = guessedColumn - 1;
                    int count = 0; 
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
                    gameBoard[r][c] = String.valueOf(count);
                    placedBoard[r][c] = String.valueOf(count);

                    for (int i = 0; i < row; i++) { // Değerlendirme 12: Girilen noktada mayın yoksa etrafındaki mayın sayısı veya 0 değeri burada yerine yazılıyor.
                        for (int j = 0; j < column; j++) {
                            System.out.print(gameBoard[i][j] + " ");
                        }
                        System.out.println();
                    }
                    System.out.println("==================================");
                } else if (!placedBoard[guessedRow - 1][guessedColumn - 1].equals("-") && !placedBoard[guessedRow - 1][guessedColumn - 1].equals("*")){ // Öncekilerle aynı index değeri girilirse burada uyarı mesajı verilir.
                    System.out.println("You can not enter same index!");
                } else {
                    System.out.println("Boooom!!! You stepped on a mine!! Game Over!"); // Değerlendirme 13 + 15: Kullanıcı mayına bastığında oyunu kaybedecek şekilde kontrol yapılır ve mesaj yazdırılır.
                    break;
                }
            }
        }
    }
}
