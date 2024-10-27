package lld;

import java.util.Scanner;

public class TicTacToe {
    private final int boardSize;
    private String[][] board;
    private final String p1;
    private final String p2;
    private boolean isFirstPlayerTurn;
    private final Scanner scn;

    TicTacToe(int boardSize, String p1, String p2) {
        this.boardSize = boardSize;
        this.board = new String[boardSize][boardSize];
        this.p1 = p1;
        this.p2 = p2;
        this.isFirstPlayerTurn = true;

        this.scn = new Scanner(System.in);
    }

    public void play() {
        // print welcome msg
        System.out.printf("Let's play %s & %s%n", p1, p2);

        // start play loop
        while (true) {
            // display board
            displayBoard();
            // take user input
            var userIn = getUserInput();
            // validate input
            if (isValidInput(userIn)) {
                // update board
                updateBoard(userIn);
                // check for winner
                if (checkWin(userIn)) {
                    System.out.printf("%s WON!!", isFirstPlayerTurn ? p1 : p2);
                    break;
                }
                // check for Draw
                if (checkDraw()) {
                    System.out.println("Match drawn!!");
                    break;
                }
                isFirstPlayerTurn = !isFirstPlayerTurn;
                // move to the next player
            }
        }
    }

    private boolean checkDraw() {
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                if (null == board[r][c]) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkWin(int[] userIn) {
        boolean isRowEq = true, isColEq = true;
        int r = userIn[0], c = userIn[1];
        String curVal = board[r][c];

        for (int i = 0; i < boardSize; i++) {
            if (!curVal.equals(board[i][c])) isColEq = false;
            if (!curVal.equals(board[r][i])) isRowEq = false;
        }

        return isRowEq || isColEq;
    }

    private void updateBoard(int[] userIn) {
        String curVal = isFirstPlayerTurn ? "X" : "O";
        int r = userIn[0], c = userIn[1];
        board[r][c] = curVal;
    }

    private boolean isValidInput(int[] userIn) {
        int r = userIn[0], c = userIn[1];
        if (r < 0 || c < 0 || r >= boardSize || c >= boardSize) {
            System.out.printf("input out of bound, pls enter number in range [1,%s]%n", boardSize);
            return false;
        }

        if (null != board[r][c]) {
            System.out.println("place already taken, pls provide new input");
            return false;
        }

        return true;
    }

    private int[] getUserInput() {
        System.out.printf("%s pls provide input%n", isFirstPlayerTurn ? p1 : p2);
        int r = scn.nextInt();
        int c = scn.nextInt();

        return new int[]{r - 1, c - 1};
    }

    private void displayBoard() {
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                System.out.printf(String.format("%s", board[r][c] == null ? "_" : board[r][c]));
            }
            System.out.println();
        }
    }
}
