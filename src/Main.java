import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final String[][] board = {{"-", "-", "-"}, {"-", "-", "-"}, {"-", "-", "-"}};
    private static int[] coordinates;
    private static String player = "X";
    private static boolean hasWinner = false;

    public static final String WELCOME_MESSAGE = "Welcome to 3x3 Tic Tac Toe.";
    public static final String FIRST_PLAY_MESSAGE = "X will play first. Enter a row and column to place X (Example: 2 1 - will place X on row 2 and column 1.)";
    public static final String COORDINATES_OUT_OF_RANGE = "Coordinates is out of range. Please enter valid coordinates.";
    public static final String INPUT_COORDINATES_ERROR = "Please enter valid coordinates.";
    public static final String COORDINATES_ALREADY_IN_USE = "Coordinates already in use.";
    public static final String NO_WINNER_MESSAGE = "Nobody won!";


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(WELCOME_MESSAGE);
        System.out.println(FIRST_PLAY_MESSAGE);
        printBoard();

        while (!hasWinner) {
            try {
                coordinates = Arrays
                        .stream(scanner.nextLine().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            } catch (Exception ex) {
                System.out.println(INPUT_COORDINATES_ERROR);
                continue;
            }

            if (!validateCoordinates()) {
                continue;
            }

            setCoordinates();

            if (checkForWinner()) {
                printBoard();
                break;
            }

            if (!checkForBlanks()) {
                break;
            }
            changePlayer();
            System.out.println(player + "'s turn. Enter a row and column number to place " + player + " in:");
            printBoard();

        }

        if (hasWinner) {
            System.out.println(player + " is winner!");
        } else {
            printBoard();
            System.out.println(NO_WINNER_MESSAGE);
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

    private static void setCoordinates() {
        int row = coordinates[0] - 1;
        int col = coordinates[1] - 1;
        board[row][col] = player;
    }

    private static boolean validateCoordinates() {
        if (coordinates.length != 2) {
            System.out.println(COORDINATES_OUT_OF_RANGE);
            return false;
        }
        int row = coordinates[0];
        int col = coordinates[1];
        if (row <= 0 || row > 3 || col <= 0 || col > 3) {
            System.out.println(COORDINATES_OUT_OF_RANGE);
            return false;
        }
        if (!board[row - 1][col - 1].equals("-")) {
            System.out.println(COORDINATES_ALREADY_IN_USE);
            return false;
        }

        return true;
    }

    private static boolean checkForWinner() {
        if ((board[0][0].equals(player) && board[1][0].equals(player) && board[2][0].equals(player)) ||
                (board[0][1].equals(player) && board[1][1].equals(player) && board[2][1].equals(player)) ||
                (board[0][2].equals(player) && board[1][2].equals(player) && board[2][2].equals(player)) ||
                (board[0][0].equals(player) && board[0][1].equals(player) && board[0][2].equals(player)) ||
                (board[1][0].equals(player) && board[1][1].equals(player) && board[1][2].equals(player)) ||
                (board[2][0].equals(player) && board[2][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[2][0].equals(player) && board[1][1].equals(player) && board[0][2].equals(player))) {
            hasWinner = true;
            return true;
        }
        return false;
    }

    private static void changePlayer() {
        if (player.equals("X")) {
            player = "O";
        } else {
            player = "X";
        }
    }

    private static boolean checkForBlanks() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col].equals("-")) {
                    return true;
                }
            }
        }
        return false;
    }
}