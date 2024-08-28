// Import component classes from swing package
import javax.swing.*;

// Import layout classes and event-handling interfaces from awt package
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TicTacToe extends JFrame implements ActionListener {
    private String player1; // Name of player 1
    private String player2; // Name of player 2
    private String currentPlayer; // Name of the current player
    private boolean gameOver = false; // Flag indicating if the game is over
    private JButton[][] buttons = new JButton[3][3]; // Buttons for the game board
    private JLabel turnLabel; // Label to display current player's turn

    // Constructor
    public TicTacToe() {
        // Set the title of the game window
        setTitle("Tic Tac Toe");
        // Set the closing operation of the game window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the size of the game window
        int screenWidth = 300;
        int screenHeight = 300;

        setSize(screenWidth, screenHeight);
        // Set the layout of the game window
        setLayout(new BorderLayout());
        // Create panel for player turn label and add it to the frame
        turnLabel = new JLabel(currentPlayer + "'s Turn");
        // Create panel for game board (3 * 3) and add it to the frame
        JPanel panel = new JPanel(new GridLayout(3, 3));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // add buttons
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                buttons[x][y] = new JButton();
                buttons[x][y].addActionListener(this);
                panel.add(buttons[x][y]);
            }
        }

        // Get player names through Dialog container
        player1 = JOptionPane.showInputDialog("Enter Player 1 Name:");
        player2 = JOptionPane.showInputDialog("Enter Player 2 Name:");
        // Set the currentPlayer to player1
        currentPlayer = player1;
        updateTurn();
        add(turnLabel, BorderLayout.NORTH);
        turnLabel.setVisible(true);

        // add panel and set visible
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void initializeBoard() {
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Component[][] buttons;
                buttons[i][j] = new JButton();
                buttons[i][j].addActionListener(this);
                boardPanel.add(buttons[i][j]);
            }
        }
        add(boardPanel, BorderLayout.CENTER);
    }

    private void updateTurn() {
        turnLabel.setText(String.format("Turn: %s", currentPlayer));
    }

    // ActionListener method
    /**
     * Override the action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            // Get the button that triggered the action event
            JButton clickedButton = (JButton) e.getSource();

            // Determine the location of which button was clicked (i.e., grid cell)
            int rowNum = -1;
            int colNum = -1;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (buttons[i][j] == clickedButton) {
                        rowNum = i;
                        colNum = j;
                        break;
                    }
                }

            }

            // Check if the chosen cell is empty or not. If empty:
            // 1. Set the text of the grid cell
            // 2. Check for win
            // 3. Check for draw
            // 4. Switch player and set the text of the 'turnLabel' no win or draw yet!
            if (buttons[rowNum][colNum].getText().equals("")) {
                buttons[rowNum][colNum].setText(currentPlayer);
                if (checkWin()) {
                    gameOver = true;
                    JOptionPane.showMessageDialog(this, currentPlayer + " wins!");
                    resetGame();
                    return;

                }
                if (checkDraw()) {
                    gameOver = true;
                    JOptionPane.showMessageDialog(this, "It's a draw!");
                    resetGame();
                    return;

                }
                currentPlayer = (currentPlayer.equals(player1)) ? player2 : player1;
                turnLabel.setText(currentPlayer + "'s Turn");
                // If not empty, a pop-up window (Dialog) stating the cell is already taken!
            } else {
                JOptionPane.showMessageDialog(this, "This cell is already taken!");
            }

        }

    }

    // Method to check for win
    /**
     * Checks if the game is won
     * 
     * @return win or no win
     */
    private boolean checkWin() {
        // check rows
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(currentPlayer) &&
                    buttons[i][1].getText().equals(currentPlayer) &&
                    buttons[i][2].getText().equals(currentPlayer))
                return true;

        }
        // check columns
        for (int j = 0; j < 3; j++) {
            if (buttons[0][j].getText().equals(currentPlayer) &&
                    buttons[1][j].getText().equals(currentPlayer) &&
                    buttons[2][j].getText().equals(currentPlayer)) {
                return true;

            }

        }

        // check diagonal wins
        if (buttons[0][0].getText().equals(currentPlayer) &&
                buttons[1][1].getText().equals(currentPlayer) &&
                buttons[2][2].getText().equals(currentPlayer)) {
            return true;

        }
        if (buttons[0][2].getText().equals(currentPlayer) &&
                buttons[1][1].getText().equals(currentPlayer) &&
                buttons[2][0].getText().equals(currentPlayer)) {
            return true;

        }
        return false;
    }

    // Method to check for draw
    /**
     * Check if game is drawn.
     * 
     * @return if game is drawn
     */
    private boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }

            }

        }
        return true;

    }

    /**
     * Resets the game variables
     */
    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        currentPlayer = player1;
        gameOver = false;
        turnLabel.setText(currentPlayer + "'s Turn");
    }

    // Main method
    public static void main(String[] args) {
        // Instantiate the TicTacToeTemplate class
        new TicTacToe();
    }
}
