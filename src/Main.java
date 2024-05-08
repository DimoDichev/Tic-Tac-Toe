import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final String[][] board = {{"-", "-", "-"}, {"-", "-", "-"}, {"-", "-", "-"}};
    private static int[] coordinates;
    private static String player = "X";

    public static final String WELCOME_MESSAGE = "Welcome to 3x3 Tic Tac Toe.";
    public static final String FIRST_PLAY_MESSAGE = "X will play first. Enter a row and column to place X (Example: 2 1 - will place X on row 2 and column 1)";
    public static final String COORDINATES_OUT_OF_RANGE = "Coordinates is out of range. Please enter valid coordinates.";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean hasWinner = false;

        System.out.println(WELCOME_MESSAGE);
        printBoard();
        System.out.println(FIRST_PLAY_MESSAGE);

        while (!hasWinner) {
            coordinates = Arrays
                    .stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            if (!validateCoordinates()) {
                System.out.println(COORDINATES_OUT_OF_RANGE);
                continue;
            }

            setCoordinates();

            //check for winner

            //change player

            printBoard();
        }
    }

    private static void printBoard() {
        System.out.println("    1   2   3");
        System.out.println("  |---|---|---|");
        System.out.printf("1 | %s | %s | %s |%n", board[0][0], board[0][1], board[0][2]);
        System.out.println("  |---|---|---|");
        System.out.printf("2 | %s | %s | %s |%n", board[1][0], board[1][1], board[1][2]);
        System.out.println("  |---|---|---|");
        System.out.printf("3 | %s | %s | %s |%n", board[2][0], board[2][1], board[2][2]);
        System.out.println("  |---|---|---|");
    }

    private static boolean validateCoordinates() {
        int row = coordinates[0];
        int col = coordinates[1];
        return (row > 0 && row <= 3) && (col > 0 && col <= 3);
    }

    private static void setCoordinates() {
        int row = coordinates[0] - 1;
        int col = coordinates[1] - 1;
        board[row][col] = player;
    }
}