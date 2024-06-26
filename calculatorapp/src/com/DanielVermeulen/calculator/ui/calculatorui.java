package com.DanielVermeulen.calculator.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.DanielVermeulen.calculator.logic.calculatorlogic;

public class calculatorui extends JFrame {
    private JTextField display;
    private calculatorlogic logic;

    public calculatorui() {
        setTitle("Daniel Calculator");
        setSize(400, 700); // Increased size for better UI
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        logic = new calculatorlogic();

        // Setting a dark background for the frame
        getContentPane().setBackground(Color.BLACK);

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Consolas", Font.BOLD, 30)); // Using a modern font and increased size
        display.setForeground(Color.CYAN); // Neon color for the text
        display.setBackground(Color.BLACK);
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));
        panel.setBackground(Color.BLACK); // Dark background for the panel

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(new ButtonClickListener());
            styleButton(button);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Consolas", Font.BOLD, 30)); // Modern font and increased size
        button.setForeground(Color.WHITE); // Button text color
        button.setBackground(Color.BLACK); // Button background color
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2)); // Neon border
        button.setOpaque(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
                logic.input_number(command.charAt(0));
                update_display(logic.get_current_input());
            } else if (command.charAt(0) == 'C') {
                logic.clear();
                update_display("");
            } else if (command.charAt(0) == '=') {
                logic.calculate();
                update_display(logic.get_expression() + " = " + logic.get_result());
            } else {
                logic.input_operator(command.charAt(0));
                update_display(logic.get_current_input());
            }
        }
    }

    private void update_display(String text) {
        display.setText(text);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            calculatorui calculator = new calculatorui();
            calculator.setVisible(true);
        });
    }
}
