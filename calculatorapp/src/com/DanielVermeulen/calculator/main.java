package com.DanielVermeulen.calculator;

import javax.swing.SwingUtilities;
import com.DanielVermeulen.calculator.ui.calculatorui;

public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            calculatorui calculator = new calculatorui();
            calculator.setVisible(true);
        });
    }
}
