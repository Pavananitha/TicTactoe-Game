import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe {
    private static final String PLAYER_X = "X";
    private static final String PLAYER_O = "O";
    private String currentPlayer = PLAYER_X;
    private JButton[] buttons = new JButton[9];
    private int moveCount = 0;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToe().createGUI());
    }

    public void createGUI() {
        JFrame frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 3));
        frame.setSize(400, 400);

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 60));
            buttons[i].setFocusPainted(false);
            buttons[i].setBackground(Color.WHITE);
            buttons[i].addActionListener(new ButtonClickListener(i));
            frame.add(buttons[i]);
        }

        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        private int index;

        public ButtonClickListener(int index) {
            this.index = index;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (buttons[index].getText().equals("") && !isGameOver()) {
                buttons[index].setText(currentPlayer);
                moveCount++;
                if (checkForWin()) {
                    JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins!");
                } else if (moveCount == 9) {
                    JOptionPane.showMessageDialog(null, "It's a draw!");
                } else {
                    currentPlayer = (currentPlayer.equals(PLAYER_X)) ? PLAYER_O : PLAYER_X;
                }
            }
        }
    }

    private boolean checkForWin() {
        // Check rows, columns, and diagonals for a win
        return (checkLine(0, 1, 2) || checkLine(3, 4, 5) || checkLine(6, 7, 8) || // Rows
                checkLine(0, 3, 6) || checkLine(1, 4, 7) || checkLine(2, 5, 8) || // Columns
                checkLine(0, 4, 8) || checkLine(2, 4, 6)); // Diagonals
    }

    private boolean checkLine(int a, int b, int c) {
        return buttons[a].getText().equals(currentPlayer) &&
               buttons[b].getText().equals(currentPlayer) &&
               buttons[c].getText().equals(currentPlayer);
    }

    private boolean isGameOver() {
        return moveCount >= 9 || checkForWin();
    }
}
