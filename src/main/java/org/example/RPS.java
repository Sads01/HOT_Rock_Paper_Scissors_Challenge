package org.example;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class RPS extends JFrame implements ActionListener {

    private JButton startGameButton, rockButton, paperButton, scissorsButton, computerButton, playAgainButton;
    private JRadioButton PlayerVsComputer, ComputerVsComputer;
    private JTextField textField, textField2, textField3;

    public static void main(String[] args) {
        RPS paper = new RPS();
        paper.setSize(245,300); // Setting up the pane
        paper.createGUI();/*www.j  av  a 2s  .  co m*/
        paper.show();
    }

    private void createGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());

        PlayerVsComputer = new JRadioButton("Player vs Computer");
        window.add(PlayerVsComputer);
        PlayerVsComputer.setSelected(true);  // initially selected - to prevent non selection
        ComputerVsComputer =new JRadioButton("Computer vs Computer");
        window.add(ComputerVsComputer);
        ButtonGroup bg=new ButtonGroup();
        bg.add(PlayerVsComputer);bg.add(ComputerVsComputer);

        startGameButton = new JButton("         Start Game         ");
        window.add(startGameButton);
        startGameButton.addActionListener(this);

        rockButton = new JButton("Rock");
        window.add(rockButton);
        rockButton.setEnabled(false);
        rockButton.addActionListener(this);

        paperButton = new JButton("Paper");
        window.add(paperButton);
        paperButton.setEnabled(false);
        paperButton.addActionListener(this);

        scissorsButton = new JButton("Scissors");
        window.add(scissorsButton);
        scissorsButton.setEnabled(false);
        scissorsButton.addActionListener(this);

        computerButton = new JButton("Computer Selection");
        window.add(computerButton);
        computerButton.setEnabled(false);
        computerButton.addActionListener(this);

        textField = new JTextField(21);
        window.add(textField);

        textField2 = new JTextField(21);
        window.add(textField2);

        textField3 = new JTextField(21);
        window.add(textField3);

        playAgainButton = new JButton("         Play Again         ");
        window.add(playAgainButton);
        playAgainButton.setEnabled(false);
        playAgainButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        int playerChoice;
        int compChoice;
        String winner;

        Random randomValue = new Random();

        // Acceptance Criteria governed by GUI access

        if (source == startGameButton) {
            // Active listeners determine flow
            ComputerVsComputer.addActionListener(this);
            PlayerVsComputer.addActionListener(this);
            // buttons disabled to remove alternate inputs
            ComputerVsComputer.setEnabled(false);
            PlayerVsComputer.setEnabled(false);
            startGameButton.setEnabled(false);
            // button enabled to allow usr alternate game choice
            playAgainButton.setEnabled(true);
        }
        //initialise random values
        playerChoice = randomValue.nextInt(3);
        compChoice = randomValue.nextInt(3);

        if (ComputerVsComputer.isSelected())  {
            rockButton.setEnabled(false);
            paperButton.setEnabled(false);
            scissorsButton.setEnabled(false);
            computerButton.setEnabled(true);

        } else {
            rockButton.setEnabled(true);
            paperButton.setEnabled(true);
            scissorsButton.setEnabled(true);
        }

        // determines active input
        if (PlayerVsComputer.isSelected()) {

            if (source == rockButton) {
                playerChoice = 0;
            }
            else if (source == paperButton){
                playerChoice = 1;
            } else {
                playerChoice = 2;
            }
        }

        // Values are taken from either random or active input
        if (playerChoice == 0) {
            textField2.setText("Player 1 choice is rock");
        } else if (playerChoice == 1) {
            textField2.setText("Player 1 choice is paper");
        } else {
            textField2.setText("Player 1 choice is scissors");
        }


        // Values are taken from random input
        if (compChoice == 0) {
            textField3.setText("Player 2 (Comp) choice is rock");
        } else if (compChoice == 1) {
            textField3.setText("Player 2 (Comp) choice is paper");
        } else {
            textField3.setText("Player 2 (Comp) choice is scissors");
        }


        winner = findWinner(playerChoice, compChoice);
        textField.setText("Winner is " + winner);

        if (source == playAgainButton)
        {
            // Reset Default starting points
            ComputerVsComputer.setEnabled(true);
            PlayerVsComputer.setEnabled(true);
            startGameButton.setEnabled(true);
            rockButton.setEnabled(false);
            paperButton.setEnabled(false);
            scissorsButton.setEnabled(false);
            computerButton.setEnabled(false);
            playAgainButton.setEnabled(false);

            textField.setText("");
            textField2.setText("");
            textField3.setText("");

            ComputerVsComputer.removeActionListener(this);
            PlayerVsComputer.removeActionListener(this);
        }

    }
    private String findWinner(int playerChoice, int compChoice) {
        String winner;
        if (playerChoice == compChoice) {
            winner = "it's a draw";
        } else if (playerChoice == 0 && compChoice == 1) {
            winner = "Player 2 (Computer)";
        } else if (playerChoice == 1 && compChoice == 2) {
            winner = "Player 2 (Computer)";
        } else if (playerChoice == 2 && compChoice == 0) {
            winner = "Player 2 (Computer)";
        } else {
            winner = "Player 1";
        } return winner;
    }
}