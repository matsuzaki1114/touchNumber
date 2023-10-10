package touchNumber;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class TouchNumberGame {
    private static final int GRID_SIZE = 3; // ゲームボードの行と列の数
    private static final int MAX_NUMBER = 9; // 表示される最大の数字
    private int targetNumber;
    private int score = 0;
    private JButton[][] buttons = new JButton[GRID_SIZE][GRID_SIZE];
    private JLabel scoreLabel;
    private JLabel timeLabel;
    private Set<Integer> generatedNumbers = new HashSet<>();
    
        public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TouchNumberGame game = new TouchNumberGame();
            game.createAndShowGUI();
        });

    }
    
    



    public void createAndShowGUI() {
        JFrame frame = new JFrame("Touch Number Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel gamePanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        scoreLabel = new JLabel("Score: " + score);
        timeLabel = new JLabel();
        
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 20;

            public void run() {

                timeLabel.setText("Time left: " + i);
                i--;

                if (i < 0) {
                    timer.cancel();
                    timeLabel.setText("Time Over");
                }
            }
        }, 0, 1000);
        
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                buttons[i][j] = createNumberButton();
                gamePanel.add(buttons[i][j]);
            }
        }

        mainPanel.add(gamePanel, BorderLayout.CENTER);
        mainPanel.add(scoreLabel, BorderLayout.SOUTH);
        mainPanel.add(timeLabel, BorderLayout.NORTH);

        frame.add(mainPanel);
        frame.setVisible(true);

        startGame();
    }

    private JButton createNumberButton() {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(100, 100));
        button.setFont(new Font("Arial", Font.PLAIN, 24));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton clickedButton = (JButton) e.getSource();
                int clickedNumber = Integer.parseInt(clickedButton.getText());
                if (clickedNumber == targetNumber) {
                    score++;
                    scoreLabel.setText("Score: " + score);
                    generatedNumbers.remove(targetNumber); // クリックされた数字をセットから削除
                    generateRandomTargetNumber();
                }
            }
        });
        return button;
    }

    private void startGame() {
        generateRandomTargetNumber();
    }

    private void generateRandomTargetNumber() {
        Random random = new Random();
        do {
            targetNumber = random.nextInt(MAX_NUMBER + 1);
        } while (generatedNumbers.contains(targetNumber));

        generatedNumbers.add(targetNumber);
        // ランダムな数値をボタンに表示
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                int number;
                do {
                    number = random.nextInt(MAX_NUMBER + 1);
                } while (generatedNumbers.contains(number));
                buttons[i][j].setText(String.valueOf(number));
            }
        }
    }
}


