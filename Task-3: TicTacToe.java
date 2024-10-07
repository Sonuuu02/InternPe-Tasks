import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {

    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JButton resetButton = new JButton("Reset");

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;
    int turns = 0;

    TicTacToe() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 25));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel, BorderLayout.CENTER);

        resetButton.setFont(new Font("Arial", Font.BOLD, 20));
        resetButton.setFocusable(false);
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        textPanel.add(resetButton, BorderLayout.EAST);

        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.WHITE);  // Set background color to white for better contrast
                tile.setForeground(Color.BLACK);  // Set text color to black
                tile.setFont(new Font("Arial", Font.BOLD, 100));  // Set font size to 100 for better visibility
                tile.setHorizontalAlignment(SwingConstants.CENTER);  // Center the text horizontally
                tile.setVerticalAlignment(SwingConstants.CENTER);    // Center the text vertically
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText().equals("")) {
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if (!gameOver) {
                                currentPlayer = currentPlayer.equals(playerX) ? playerO : playerX;
                                textLabel.setText(currentPlayer + "'s turn.");
                            }
                        }
                    }
                });
            }
        }
    }

    void checkWinner() {
        // Check horizontal, vertical, and diagonal lines for a winner
        for (int i = 0; i < 3; i++) {
            if (!board[i][0].getText().equals("") && board[i][0].getText().equals(board[i][1].getText()) &&
                    board[i][1].getText().equals(board[i][2].getText())) {
                declareWinner(board[i][0].getText(), board[i][0], board[i][1], board[i][2]);
                return;
            }
            if (!board[0][i].getText().equals("") && board[0][i].getText().equals(board[1][i].getText()) &&
                    board[1][i].getText().equals(board[2][i].getText())) {
                declareWinner(board[0][i].getText(), board[0][i], board[1][i], board[2][i]);
                return;
            }
        }

        // Check diagonals
        if (!board[0][0].getText().equals("") && board[0][0].getText().equals(board[1][1].getText()) &&
                board[1][1].getText().equals(board[2][2].getText())) {
            declareWinner(board[0][0].getText(), board[0][0], board[1][1], board[2][2]);
            return;
        }
        if (!board[0][2].getText().equals("") && board[0][2].getText().equals(board[1][1].getText()) &&
                board[1][1].getText().equals(board[2][0].getText())) {
            declareWinner(board[0][2].getText(), board[0][2], board[1][1], board[2][0]);
            return;
        }
        // Check for a tie
        if (turns == 9) {
            declareTie();
        }
    }

    void declareWinner(String winner, JButton... winningTiles) {
        for (JButton tile : winningTiles) {
            tile.setForeground(Color.green);
            tile.setBackground(Color.gray);
        }
        textLabel.setText(winner + " Wins! Play again.");
        gameOver = true;
    }

    void declareTie() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c].setForeground(Color.red);
                board[r][c].setBackground(Color.gray);
            }
        }
        textLabel.setText("It's a tie! Play again.");
        gameOver = true;
    }

    void resetGame() {
        // Reset the game state
        currentPlayer = playerX;
        turns = 0;
        gameOver = false;
        textLabel.setText("Tic-Tac-Toe");
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c].setText("");
                board[r][c].setForeground(Color.black);
                board[r][c].setBackground(Color.darkGray);
            }
        }
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}

