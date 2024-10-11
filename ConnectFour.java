package com.game;

import java.util.Objects;
import java.util.Scanner;

public class ConnectFour {

    /* Global Variables */
    String[][] board;
    Boolean winner;
    Boolean draw;
    int winningPlayer;
    int playerTurn;

    /* CONSTRUCTOR */
    public ConnectFour() {
        winningPlayer = 0;
        draw = false;
        playerTurn = 1;
        board = new String[6][7];
        newBoard();
        displayBoard();
    }

    /* Builds a better looking board in the console */
    private void newBoard() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = " - ";
            }
        }
    }

    /* Displays the board */
    private void displayBoard() {
        System.out.println(" ");
        System.out.println("    ***  CONNECT 4  ***");
        System.out.println(" ");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    /* Validates user input */
    private boolean validInput(String input) {
        return input.equals("1") || input.equals("2") || input.equals("3") ||
                input.equals("4") || input.equals("5") || input.equals("6") || input.equals("7");
    }

    /* Checks if a column is full */
    private boolean isColumnFull(int column) {
        return board[0][column - 1].equals(" X ") || board[0][column - 1].equals(" O ");
    }

    /* Get next available slot in the column */
    private int getNextAvailableSlot(int column) {
        int position = 5;
        while (position >= 0) {
            if (!board[position][column].equals(" X ") && !board[position][column].equals(" O ")) {
                break;
            }
            position--;
        }
        return position;
    }

    /* Swaps the turn to the other player */
    private void swapPlayerTurn() {
        playerTurn = (playerTurn == 1) ? 2 : 1;
    }

    /* Handles the logic for placing a piece on the board */
    private void placePiece() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Player " + playerTurn + " - Please select which column to place your piece (1-7):");
        String input = scanner.nextLine();

        while (!validInput(input) || isColumnFull(Integer.parseInt(input))) {
            if (!validInput(input)) {
                System.out.println("Invalid input - please select a column from 1-7:");
                input = scanner.nextLine();
            } else if (isColumnFull(Integer.parseInt(input))) {
                System.out.println("Column full, choose another column:");
                input = scanner.nextLine();
            }
        }

        int columnChoice = Integer.parseInt(input) - 1; // Convert to zero-based index
        String piece = (playerTurn == 1) ? " X " : " O ";
        board[getNextAvailableSlot(columnChoice)][columnChoice] = piece;
        displayBoard();
        swapPlayerTurn();
    }

    /* Checks if the board is full */
    private boolean isBoardFull() {
        for (int j = 0; j < 7; j++) {
            if (!board[0][j].equals(" X ") && !board[0][j].equals(" O ")) {
                return false;
            }
        }
        return true;
    }

    /* Checks for a vertical winner */
    private String checkVerticalWinner() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 7; j++) {
                if (board[i][j].equals(board[i + 1][j]) &&
                        board[i][j].equals(board[i + 2][j]) &&
                        board[i][j].equals(board[i + 3][j]) &&
                        (board[i][j].equals(" X ") || board[i][j].equals(" O "))) {
                    return board[i][j];
                }
            }
        }
        return null;
    }

    /* Checks for a horizontal winner */
    private String checkHorizontalWinner() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j].equals(board[i][j + 1]) &&
                        board[i][j].equals(board[i][j + 2]) &&
                        board[i][j].equals(board[i][j + 3]) &&
                        (board[i][j].equals(" X ") || board[i][j].equals(" O "))) {
                    return board[i][j];
                }
            }
        }
        return null;
    }

    /* Checks for a left diagonal winner */
    private String checkLeftDiagonalWinner() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j].equals(board[i + 1][j + 1]) &&
                        board[i][j].equals(board[i + 2][j + 2]) &&
                        board[i][j].equals(board[i + 3][j + 3]) &&
                        (board[i][j].equals(" X ") || board[i][j].equals(" O "))) {
                    return board[i][j];
                }
            }
        }
        return null;
    }

    /* Checks for a right diagonal winner */
    private String checkRightDiagonalWinner() {
        for (int i = 0; i < 3; i++) {
            for (int j = 3; j < 7; j++) {
                if (board[i][j].equals(board[i + 1][j - 1]) &&
                        board[i][j].equals(board[i + 2][j - 2]) &&
                        board[i][j].equals(board[i + 3][j - 3]) &&
                        (board[i][j].equals(" X ") || board[i][j].equals(" O "))) {
                    return board[i][j];
                }
            }
        }
        return null;
    }

    /* Checks if there is a winner */
    private int checkForWinner() {
        String symbol = checkVerticalWinner();
        if (symbol == null) symbol = checkHorizontalWinner();
        if (symbol == null) symbol = checkLeftDiagonalWinner();
        if (symbol == null) symbol = checkRightDiagonalWinner();

        if (" X ".equals(symbol)) {
            return 1;
        } else if (" O ".equals(symbol)) {
            return 2;
        }
        return 0;
    }

    /* Checks for a draw */
    private boolean checkForDraw() {
        return isBoardFull() && checkForWinner() == 0;
    }

    /* Displays the winner */
    private void showWinner() {
        System.out.println(" ");
        System.out.println("***************************************");
        System.out.println("                                        ");
        System.out.println("                                        ");
        System.out.println("            PLAYER " + winningPlayer + " WINS !!! ");
        System.out.println("*              *       *               *");
        System.out.println("    *           \\ O /    (+)     '      ");
        System.out.println("        (-)      | |              *    ");
        System.out.println("                 /] [\\                ");
        System.out.println("     *                        *          ");
        System.out.println(" *         '               *          '     ");
        System.out.println("        *              *                *  ");
        System.out.println("****************************************");
    }

    /* Main game loop */
    private void playGame() {
        while (true) {
            winningPlayer = checkForWinner();
            draw = checkForDraw();
            if (winningPlayer == 1 || winningPlayer == 2) {
                showWinner();
                break;
            } else if (draw) {
                System.out.println("It's a draw");
                break;
            }
            placePiece();
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Connect Four!");
        ConnectFour c4 = new ConnectFour();
        c4.playGame();
    }
}






